import java.util.ArrayList;


public class Node {

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
