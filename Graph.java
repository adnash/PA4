import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;


public class Graph {
	private LinkedList<String> vertices = new LinkedList<>();
	private LinkedList<Edge> edges = new LinkedList<>();
	private int[][] adjMatrix;
	
	public LinkedList<String> getVertices(){
		return this.vertices;
	}
	
	public LinkedList<Edge> getEdges(){
		return this.edges;
	}
	
	//Adds a vertex in alphabetical order.
	//If the vertex is already present, does not add and returns false.
	//If the vertex is not present, adds it and returns true.
	public boolean add(String name){
		if(this.contains(name)){		//If the vertex is present, it is not added
			return false;
		}else{
			//If the vertex belongs somewhere in the middle of the list
			for(int i = 0; i<vertices.size(); i++){
				if(vertices.get(i).compareTo(name) > 0){
					vertices.add(i, name);
					return true;
				}
			}
			//If the vertex belongs at the end of the list
			vertices.add(name);
			return true;
		}
	}
	/*
	//returns the vertex with a given filename.
	public Vertex get(String name){
		for(int i = 0; i<vertices.size(); i++){
			if(name.equals(vertices.get(i).getName())){
				return vertices.get(i);
			}
		}
		return null;
	}*/
	
	public int getIndex(String name){
		return vertices.indexOf(name);
	}
	
/*	public int getIndex(Vertex vertex){
		return vertices.indexOf(vertex);
	}*/
	
	//Adds an edge, if it doesn't already exist
	public boolean addEdge(String source, String dest){
		for(int i = 0; i<edges.size(); i++){
			if(edges.get(i).getSource().equals(source)){
				if(edges.get(i).getDest().equals(dest)){
					return false;
				}
			}
		}
		edges.add(new Edge(source, dest));
		return true;
	}
	
//	public boolean addEdge(Vertex source, Vertex dest){
//		return addEdge(source.getName(), dest.getName());
//	}
	
	
	//Precondition:  Source, dest, or edge between them is not present.
	//Postcondition:  Source, dest, and edge from source to dest is present.
	//Returns true if the edge was added.
	public boolean add(String source, String dest){
		if(!this.contains(source)){		//If the source page is absent, it's added.
			this.add(source);
		}
		if(this.contains(dest)){		//If the source and destination are both present, adds the link if necessary
			return addEdge(source, dest);
		}									//Otherwise, adds the link and the destination.
		else{
			//If the vertex belongs somewhere in the middle of the list
			for(int i = 0; i<vertices.size(); i++){
				if(vertices.get(i).compareTo(dest) > 0){
					vertices.add(i, dest);
					addEdge(source, dest);
					return true;
				}
			}
			//If the vertex belongs at the end of the list
			vertices.add(dest);
			addEdge(source, dest);
			return true;
		}
	}
	
	public boolean contains(String name){
		return vertices.contains(name);
	}
	
//	public boolean contains(Vertex vertex){
//		for(int i = 0; i<vertices.size(); i++){
//			if(vertices.get(i).equals(vertex)){
//				return true;
//			}
//		}
//		return false;
//	}
	
	public int inDegree(String filename){
		int inDegree = 0;
		for(int i = 0; i<edges.size(); i++){
			if(edges.get(i).getDest().equals(filename))
				inDegree++;
		}
		return inDegree;
	}
	
	public void generateAdjMat(){
		this.adjMatrix = new int[vertices.size()][vertices.size()];
		for(int i = 0; i<edges.size(); i++){
			adjMatrix[getIndex(edges.get(i).getSource())][getIndex(edges.get(i).getDest())] = 1;
		}
	}
	
	//Prints the adjacency matrix
	public String toString(){
		generateAdjMat();
		String output = "";
		for(int i = 0; i<adjMatrix.length; i++){
			output +=("[");
			for(int j = 0; j<adjMatrix[i].length; j++){
				output += (adjMatrix[i][j]);
				if(j!=adjMatrix[i].length-1)
					output+=(" , ");
			}
			output+=("]:  "+vertices.get(i)+"\n");
		}
		return output;
	}
	
	public void writeDotFile(String outputFile) throws IOException{
		File file = new File(outputFile);
		FileWriter f = new FileWriter(file);
		
		f.write("digraph program 5\n");
		for(int i = 0; i < edges.size(); i++){
			f.write(edges.get(i).toString());
			if(i!=edges.size()-1){
				f.write("\n");
			}
		}
		f.close();
		
	}
	
	public static void main(String[] args){
		Graph g = new Graph();
		g.add("a");
		g.add("a","b");
		g.add("a","c");
		g.add("c","b");
		g.add("abracadabra");
		g.add("abracadabra", "a");
		g.add("abracadabra", "b");
		g.add("abracadabra", "c");
		System.out.println(g.vertices);
		g.generateAdjMat();
		
		try {
			g.writeDotFile("dots");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(g);
		
	}

	
}
