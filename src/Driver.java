import java.util.ArrayList;


public class Driver {

	public static void main(String[] args)
	{
		
		Node A = new Node("A");
		Node B = new Node("B");
		Node S = new Node("S");
		
		//S.addTransition(A, B);
		//S.addTransition(B, B);
		//A.addTransition(B, B);
		
		//A.addTerminal("a");
		//A.addTerminal("b");
		
		//B.addTerminal("b");
		//B.addTerminal("c");
		
		S.addTransition(A, B);
		A.addTransition(A, A);
		B.addTransition(B, B);
		A.addTerminal("x");
		B.addTerminal("y");
		
		ArrayList<String> printList = printAll(S, 6);
		
		for(String p : printList){
			System.out.println(p);
		}

	}
	public static ArrayList<String> printAll(Node x, int num)
	{
		
		
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<String> temp1 = new ArrayList<String>();
		ArrayList<String> temp2 = new ArrayList<String>();
		
		if(num == 1) return temp;
		
		temp.addAll(x.terminals);
		
		
	
		for(Node[] y : x.transitions)
		{
			temp1 = printAll(y[0], num - 1);
			temp2 = printAll(y[1], num - 1);
			
			for(String a : temp1)
			{
				for(String b : temp2)
				{
					temp.add(a + b);
				}
			}
		} 
		
		return temp;
		
	}

}
