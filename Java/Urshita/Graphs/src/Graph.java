import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Describes a graph with vertices V and edges E
 * @author Andriy Pavlovych
 *
 */
public class Graph {
	protected int V; 		// number of vertices
	protected int E;		// number of edges
	protected Map <String, List<Edge>> adj; //Map (associative array) of adjacency lists
	
	/**
	 * read the graph from a file
	 * file format:
	 * number of vertices
	 * number of edges 
	 * -followed by the edges in format-
	 * From,To,"Distance, km","Time, min"
	 */
	public Graph(String filename) {
		Scanner myReader;
		try {
			myReader = new Scanner(new File(filename)).useDelimiter("[\n\r,]+");
			V = myReader.nextInt();
			E = myReader.nextInt(); 
			this.adj = new HashMap<String, List<Edge>>();

			for (int i = 0; i < E; i++) {
				String str1 = myReader.next();
				String str2 = myReader.next();
				double distance = myReader.nextDouble();
				myReader.nextDouble();//skip the time
				if (!adj.containsKey(str1)) adj.put(str1, new ArrayList<Edge>());
				if (!adj.containsKey(str2)) adj.put(str2, new ArrayList<Edge>());
				adj.get(str1).add(new Edge(new Vertex(str1), new Vertex(str2), distance));
				adj.get(str2).add(new Edge(new Vertex(str2), new Vertex(str1), distance));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}


	public int V() {return V;}
	public int E() {return E;}

	public Iterable<Edge> adj(Vertex v){
		return adj.get(v.name);
	}

	public Iterable<String> vertices(){
		return adj.keySet();
	}

//	public Iterable<Edge> edges (){
//		//TODO
//		return null;
//	}

	public String toString(){
		String s = V + " vertices, " + E + " edges\n";
		for (String vertex : this.adj.keySet()){
			s += vertex + ": ";
			for (Edge w : this.adj.get(vertex))
				s += w + " ";
			s += "\n";
		}
		return s;
	}
}
