import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * In this programming problem you'll code up Prim's minimum spanning tree
 * algorithm. Download the text file here. This file describes an undirected
 * graph with integer edge costs. It has the format
 * [number_of_nodes] [number_of_edges]
 * [one_node_of_edge_1] [other_node_of_edge_1] [edge_1_cost]
 * [one_node_of_edge_2] [other_node_of_edge_2] [edge_2_cost]
 * ...
 * For example, the third line of the file is "2 3 -8874", indicating that
 * there is an edge connecting vertex #2 and vertex #3 that has cost -8874. You
 * should NOT assume that edge costs are positive, nor should you assume that 
 * they are distinct.
 *
 * Your task is to run Prim's minimum spanning tree algorithm on this graph.
 * You should report the overall cost of a minimum spanning tree --- an
 * integer, which may or may not be negative --- in the box below.
 *
 * IMPLEMENTATION NOTES: This graph is small enough that the straightforward
 * O(mn) time implementation of Prim's algorithm should work fine. OPTIONAL:
 * For those of you seeking an additional challenge, try implementing a 
 * heap-based version. The simpler approach, which should already give you a
 * healthy speed-up, is to maintain relevant edges in a heap (with keys = edge
 * costs). The superior approach stores the unprocessed vertices in the heap,
 * as described in lecture. Note this requires a heap that supports deletions,
 * and you'll probably need to maintain some kind of mapping between vertices
 * and their positions in the heap.
 */

public class MyPrimMST {
	private static int numVertices = 0;
	private static int numEdges = 0;
	private static ArrayList<Edge> edges;
	private static HashMap<Integer, Boolean> marked;
	
	// Read from the input file and store the graph
	private static void readFromFile() throws IOException {
		FileInputStream fstream = new FileInputStream("edges.txt");

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		// We know the first line is the number of vertices and edges
		String line = br.readLine();
		StringTokenizer lineTokens = new StringTokenizer(line);
		numVertices = Integer.parseInt(lineTokens.nextToken());
		numEdges    = Integer.parseInt(lineTokens.nextToken());
		
		edges = new ArrayList<Edge>();
		
		for (int i = 0; i < numEdges; i++)
		{
			line = br.readLine();
			lineTokens = new StringTokenizer(line);
			int u = Integer.parseInt(lineTokens.nextToken());
			int v = Integer.parseInt(lineTokens.nextToken());
			int cost = Integer.parseInt(lineTokens.nextToken());
			
			// Vertex numbers start from 0 instead of 1
			Edge e = new Edge((u - 1), (v - 1), cost);
			edges.add(e);
		}
		
		// Initialize hash map
		marked = new HashMap<Integer, Boolean>();
		for (int i = 0; i < numVertices; i++)
		{
			marked.put(i, false);
		}
		
		br.close();
	}
	
	public static void main(String[] args) throws IOException {
		readFromFile();
		
		int sourceVertex = 0; // arbitrarily chosen
		marked.put(sourceVertex, true);
		int minimumCost;
		int totalCost = 0;
		
		// Already added 1 vertex, to loop over one less
		for (int i = 0; i < (numVertices - 1); i++)
		{
            minimumCost = Integer.MAX_VALUE;
            int edgeToAdd = Integer.MAX_VALUE;
			for (int j = 0; j < numEdges; j++)
			{
				Edge thisEdge = edges.get(j);
				
				// Check if edge is candidate to add
				if (((marked.get(thisEdge.u()) == true) &&
						(marked.get(thisEdge.v()) == false)) ||
					((marked.get(thisEdge.u()) == false) &&
						(marked.get(thisEdge.v()) == true)))
				{
					int thisCost = thisEdge.cost();
					if (thisCost < minimumCost)
					{
						minimumCost = thisCost;
						edgeToAdd = j;
					}
				}
			}

			Edge minEdge = edges.get(edgeToAdd);
			marked.put(minEdge.u(), true);
			marked.put(minEdge.v(), true);
			totalCost += minEdge.cost();
		}
		
		System.out.println("Total cost of MST: " + totalCost);
	}
}
