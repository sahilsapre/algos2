
public class UnionFind {
	private int[] leader; // leader of ith vertex
	private int numComponents;
	
	public UnionFind(int n)
	{
		numComponents = n;
		leader = new int[n];
		
		for (int i = 0; i < n; i ++)
		{
			leader[i] = i;
		}
	}
	
	public int clusters() {
		return numComponents;
	}
	
	public boolean connected(int u, int v) {
		if (leader[u] == leader[v])
		{
			return true;
		}
		return false;
	}
	
	public void merge(int u, int v) {
		if (u == v)
			return;
		
		int uLeader = leader[u];
		int vLeader = leader[v];
		
		if (uLeader == vLeader)
			return;
		for (int i = 0; i < leader.length; i++)
		{
			if (leader[i] == uLeader)
			{
				leader[i] = vLeader;
			}
		}
		numComponents--;
	}
}
