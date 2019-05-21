package spellcheck;

public interface ITrie {

	public void add(String word);

	public INode find(String word);

	public int getWordCount();

	public int getNodeCount();

	@Override
	public String toString();
	
	@Override
	public int hashCode();
	
	@Override
	public boolean equals(Object o);

	public interface INode {
		public int getValue();
	}
}
