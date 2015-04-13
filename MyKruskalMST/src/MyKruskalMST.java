import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class MyKruskalMST {
	private static int numVertices = 0;
	private static int numEdges = 0;
	private static ArrayList<Edge> edges;
	private static int clusters;
	private static final int k = 4; // given in problem statement
	private static UnionFind uf;
	
	// Read from the input file and store the graph
	private static void readFromFile() throws IOException {
		FileInputStream fstream = new FileInputStream("clustering1.txt");

		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		// We know the first line is the number of vertices and edges
		String line = br.readLine();
		StringTokenizer lineTokens = new StringTokenizer(line);
		numVertices = Integer.parseInt(lineTokens.nextToken());
		
		edges = new ArrayList<Edge>();
		
		while ((line = br.readLine()) != null)
		{
			lineTokens = new StringTokenizer(line);
			int u = Integer.parseInt(lineTokens.nextToken());
			int v = Integer.parseInt(lineTokens.nextToken());
			int cost = Integer.parseInt(lineTokens.nextToken());
			
			// Vertex numbers start from 0 instead of 1
			Edge e = new Edge((u - 1), (v - 1), cost);
			edges.add(e);
			numEdges++;
		}
		br.close();
	}
	
	public static void kCluster() {
		uf = new UnionFind(numVertices);
		int edgeCount = 0;
		while (uf.clusters() > k)
		{
			uf.merge(edges.get(edgeCount).u(), edges.get(edgeCount).v());
			edgeCount++;
		}
	}
	
	public static void main(String[] args) throws IOException {	
		readFromFile();
		
		// Sort the edges in ascending order
		Collections.sort(edges);
		
		kCluster();
		
		int minSpacing = Integer.MAX_VALUE;
		
		// find min spacing of k-cluster
		for (int i = 0; i < numEdges; i++)
		{
			Edge e = edges.get(i);
			if (!uf.connected(e.u(), e.v()))
			{
				if (e.cost() < minSpacing)
				{
					minSpacing = e.cost();
				}
			}
		}
		System.out.println("Min spacing: " + minSpacing);
	}
}
