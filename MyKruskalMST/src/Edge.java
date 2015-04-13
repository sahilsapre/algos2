public class Edge implements Comparable<Edge> {
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
	
	@Override
	public int compareTo(Edge otherEdge) {
		Integer otherCost = otherEdge.cost();
		Integer thisCost = this.cost();
		if (thisCost.compareTo(otherCost) == 0)
			return thisCost.compareTo(otherCost);
		return thisCost.compareTo(otherCost);
	}	
}