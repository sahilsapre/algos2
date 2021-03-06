public class Edge {
	private final int u; // one endpoint
	private final int v; // other endpoint
	private final int cost;
	
	public Edge(int u, int v, int cost)
	{
		this.u 	  = u;
		this.v 	  = v;
		this.cost = cost;
	}

	public int cost()
	{
		return cost;
	}
	
	public int u()
	{
		return u;
	}
	
	public int v()
	{
		return v;
	}
}
