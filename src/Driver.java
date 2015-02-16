import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Driver {

	static String box[][];
	static ArrayList<Node> variables;
	public static void main(String[] args)
	{
		File file = new File("chomsky.in");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int cases = in.nextInt();
		//cases in the program
		for(int caseNum = 0; caseNum < cases; caseNum++)
		{
			System.out.println("Grammer #" + (caseNum+1) + ":");
			//hold variables
			variables = new ArrayList<Node>();
			boolean emptyString = false; 
			int rules = in.nextInt();
			//how many unique variables are in the case
			for(int ruleNum = 0; ruleNum < rules; ruleNum++)
			{
				int states = in.nextInt();

				//create new node
				Node temp = new Node(in.next());

				for(int numStates = 0; numStates < states; numStates++)
				{
					String tempString = in.next();
					
					//if there is a epsilon we can get empty strings 
					if(tempString.equals("@")) emptyString = true;
					
					//if the string is uppercase we know it is a variable, otherwise it is a terminal 
					if(tempString.charAt(0) >= 'A' && tempString.charAt(0) <= 'Z') temp.addTransition(tempString);
					else temp.addTerminal(tempString);
				}
				variables.add(temp);
			} // All of the Variables have been initialized and set
			
			//test every word
			int words = in.nextInt();
			for(int wordNum = 0; wordNum < words; wordNum++ )
			{
				//get the word and its size
				String word = in.next();
				System.out.print(word + ": ");
				if(word.equals("@"))
				{
					if(emptyString) System.out.print("YES\n");
					else System.out.print("NO\n");
					
					continue;
 				}
				int size = word.length();
				
				//set up the two dimensional array
				box= new String[size][size];
				
				
					//set the bottom layer with all terminals that can make the string
					for(int i = 0; i < box[0].length; i++)
					{
						String temp = "";
						for(Node variable : variables)
						{
							if(variable.hasTerminal(word.charAt(i))) temp += variable.getName();
						}
						box[0][i] = temp;
					}
			
				
				
				for(int i = 1; i < size; i++)
				{
					fillBox(i);
				}
			
				if(box[size - 1][0].contains("S")) System.out.print("YES\n");
				else System.out.print("NO\n");
			}
			
			System.out.println();
			
		} // end of a case
	}

	public static void fillBox(int num)
	{

		//each case that is in the 
		for(int i = 0; i < (box[0].length - num); i++)
		{
			box[num][i] = "";
			for(int j = 0; j < num; j++)
			{
				for(char a: box[j][i].toCharArray())
				{
					for(char b: box[num-j-1][j+1+i].toCharArray())
					{
						for(Node variable : variables)
						{
							if(variable.hasPair(a, b)) box[num][i] += variable.getName();
						}
					}
				}
			}
		}
		
	}
}
