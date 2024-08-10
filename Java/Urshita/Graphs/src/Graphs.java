import java.util.HashSet;
import java.util.Set;

public class Graphs {
	/**
	 * Description
	 * @param graph input graph
	 * @param origin starting vertex
	 * @param numberOfEdges
	 * @return Set of vertices within the specified numberOfEdges
	 */
	public static Set<String> nearby(Graph graph, String origin, int numberOfEdges) {
		//CHANGED CODE (STUB) TODO
		Set<String> result = new HashSet<>();
        Set<String> visited = new HashSet<>();
        dfs(graph, origin, numberOfEdges, numberOfEdges, result, visited);
        return result;
	}

	private static void dfs(Graph graph, String current, int originalHops, int hopsLeft, Set<String> result, Set<String> visited) {
        if (hopsLeft < 0) {
            return;
        }

        result.add(current + ", " + (originalHops - hopsLeft)); // Use originalHops

        for (Edge edge : graph.adj(new Vertex(current))) {
            String neighbor = edge.other(new Vertex(current)).name;
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                dfs(graph, neighbor, originalHops, hopsLeft - 1, result, visited);
                visited.remove(neighbor); // Backtrack
            }
        }
    }


	//private methods go here if needed
	
	public static void main(String[] args) {
		String filename = "Note.txt"; // Replace with the actual input file name
        Graph graph = new Graph(filename);
        String origin = "Etobicoke"; // Replace with your desired origin vertex
        int numberOfEdges = 2;

        Set<String> result = nearby(graph, origin, numberOfEdges);
        for (String city : result) {
            System.out.println(city);
        }
    }
}
