import java.io.*;
import java.util.*;

//Graph class to represent directed graph using adjaceny list
class Graph
{
	private int V; // No. of vertices
	private LinkedList<Integer> adj[]; //Adjacency Lists

	// Constructor
	Graph(int v)
	{
		V = v;
		adj = new LinkedList[v];
		for (int i=0; i<v; ++i)
			adj[i] = new LinkedList();
	}

	// Add Edge function
	void addEdge(int v,int w)
	{
		adj[v].add(w);
	}

	void DFSUtil(int v, boolean visited[])
    {
        // marking visited node and printing it
        visited[v] = true;
        System.out.print(v + " ");
 
        // doing the same for all adjacent verticies
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
 
    void DFS(int v)
    {
        // mark vertices as not visited
        boolean visited[] = new boolean[V];
 
        // usint the recursive DFSUtil function to print the DFS travesral
        DFSUtil(v, visited);
    }

	void BFS(int s)
	{
		// mark vertices as not visited
		boolean visited[] = new boolean[V];

		// Create a queue for BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();

		// mark node as visited and add it to the queue
		visited[s]=true;
		queue.add(s);

		while (queue.size() != 0)
		{
			// deque a vertex and print it
			s = queue.poll();
			System.out.print(s+" ");

			
			// get adjacent vericies of the dequeued vertex, if not visited, then mark as visited and add it to queue
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext())
			{
				int n = i.next();
				if (!visited[n])
				{
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}

	public static void main(String args[])
	{
		Graph g = new Graph(5);

		g.addEdge(0, 2);
		g.addEdge(0, 1);
		g.addEdge(1, 3);
		g.addEdge(1, 4);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 4);

		System.out.println("Following is Breadth First Search Traversal: "+
						"(starting from vertex 0)");
		g.BFS(0);
		
		System.out.println("\nFollowing is Depth First Search Traversal:  "+
		"(starting from vertex 0)");
		g.DFS(0);
	}
}
