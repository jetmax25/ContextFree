import java.util.ArrayList;


public class Node {

	public ArrayList<Node[]> transitions = new ArrayList<Node[]>();
	public ArrayList<String> terminals = new ArrayList<String>();
	private String name;
	
	public Node(String name){
		this.name = name;
	}
	
	public void addTransition(Node A, Node B)
	{
		Node[] x = {A,B};
		transitions.add(x);
	}
	
	public void addTerminal(String a)
	{
		terminals.add(a);
	}
}
