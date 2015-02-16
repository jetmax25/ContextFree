import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**Michael Isasi
 * COT 4210
 * 02/15/15
 * @author Mike
 *
 */

class Node {

	public ArrayList<String> transitions = new ArrayList<String>();
	public ArrayList<Character> terminals = new ArrayList<Character>();
	private Character name;
	
	public Node(String name){
		this.name = name.charAt(0);
	}
	
	public void addTransition(String transition)
	{
		transitions.add(transition);
	}
	
	public void addTerminal(String term)
	{
		terminals.add(term.charAt(0));
	}
	
	public boolean hasTerminal(Character term)
	{
		
		for(Character terminal : terminals)
		{
			if(terminal.equals(term)) return true;
		}
		return false;
	}
	
	public boolean hasPair(Character a, Character b)
	{
		String test = a.toString() + b.toString();
		
		for(String transition : transitions)
		{
			if(test.equals(transition)) return true;
		}
		return false;
	}
	
	public Character getName()
	{
		return this.name;
	}
	
	public ArrayList<String> getVariables()
	{
		return this.transitions;
	}
	
	public ArrayList<Character> getTerminals()
	{
		return this.terminals;
	}
	
}

public class chomsky {
	

	static String box[][];
	static ArrayList<Node> variables;
	
	//finds all combos of variables that exis
	static HashMap exists;
	
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
			System.out.println("Grammar #" + (caseNum+1) + ":");
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
					if(tempString.equals("@")){
						emptyString = true;
						continue;
					}
					
					//if the string is uppercase we know it is a variable, otherwise it is a terminal 
					if(tempString.charAt(0) >= 'A' && tempString.charAt(0) <= 'Z') temp.addTransition(tempString);
					else temp.addTerminal(tempString);
					
				}
				variables.add(temp);
			} // All of the Variables have been initialized and set
			
			exists = new HashMap<String, String>();
			for(Node var : variables)
			{
				for(String s : var.getVariables()){
					Object temp = exists.get(s);
					
					if (temp == null) exists.put(s, var.getName());
					else{
						String string = temp.toString() +  var.getName();
						exists.put(s, string);
					}
					
				}
			}
			
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
			//initialize the string to an empty set
			box[num][i] = "";
			//every column 
			for(int j = 0; j < num; j++)
			{
				//every char in the first box
				for(char a: box[j][i].toCharArray())
				{
					//is checked agains every char in the second
					for(char b: box[num-j-1][j+1+i].toCharArray())
					{
						String temp = "" + a + b;
						
						//if thre exists a variable that contains the two variables
						if(exists.containsKey(temp)) {
							
							temp = exists.get(temp).toString();
							
							//only add chars that have not been added
							for(char c : temp.toCharArray())
							{
								if(box[num][i].indexOf(c) == -1) box[num][i] = box[num][i] + c;
							}
						}
					}
				}
			}
		}
		
	}
}



