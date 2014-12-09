
//Represents a hyperlink between web pages (Or a directed edge in a graph)
public class Edge {
	private String source;
	private String dest;

	public Edge(String source, String dest){
		this.source = source;
		this.dest = dest;
	}

	public String toString(){
		return "\""+this.source+"\""+" -> "+"\""+this.dest+"\";";
	}

	public String getSource(){
		return this.source;
	}

	public String getDest(){
		return this.dest;
	}
}
