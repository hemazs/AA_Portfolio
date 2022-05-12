import java.io.*;
class SpanningTree
{
public static int V = 6;

// Finding the max weight of a vertex from unvisted verticies
static int findMaxVertex(boolean visited[],
						int weights[])
{

	// storing the index of the vertex
	int index = -1;

	// storing the weight of the vertex
	int maxW = Integer.MIN_VALUE;

	for (int i = 0; i < V; i++)
	{

    // if not is not visited and its weight is > maxW
	if (visited[i] == false && weights[i] > maxW)
	{

		// Update maxW
		maxW = weights[i];

		// Update index
		index = i;
	}
	}
	return index;
}

static void printMaximumSpanningTree(int graph[][],
									int parent[])
{

	
	int MST = 0;

	
	for (int i = 1; i < V; i++)
	{

	// Update MST
	MST += graph[i][parent[i]];
	}

	
	System.out.println();
	System.out.println("Edges \tWeight");

	// Print the Edges and weight of
	// maximum spanning tree of a graph
	for (int i = 1; i < V; i++)
	{
	System.out.println( parent[i]  + " <-> " + i + "    "
						+ graph[i][parent[i]]);
	}
    System.out.println("Weight of the maximum Spanning-tree "
					+ MST);
}


static void maximumSpanningTree(int[][] graph)
{

	// Checking if vertex is visited or not
	boolean[] visited = new boolean[V];

	// Stores maximum weight of graph
	int[] weights = new int[V];

	// stores parent node of the vertex
	int[] parent = new int[V];

    // initializing weights as minimum value an int can have and visited[i] as false
	for (int i = 0; i < V; i++) {
	visited[i] = false;
	weights[i] = Integer.MIN_VALUE;
	}

	//add 1st vertex into tree
	weights[0] = Integer.MAX_VALUE;
	parent[0] = -1;

	// build a tree, searching for v-1 verticies
	for (int i = 0; i < V - 1; i++) {

	// find max weight of vertex from set of unvisited verticies
	int maxVertexIndex
		= findMaxVertex(visited, weights);

	// Mark vertex as visited
	visited[maxVertexIndex] = true;

	// Update adjacent vertices of visited vertex
	for (int j = 0; j < V; j++) {

		if (graph[j][maxVertexIndex] != 0
			&& visited[j] == false) {

		
		if (graph[j][maxVertexIndex]
			> weights[j]) {

			weights[j]
			= graph[j][maxVertexIndex];

			parent[j] = maxVertexIndex;
		}
		}
	}
	}

	printMaximumSpanningTree(graph, parent);
}

public static void main(String[] args)
{

	// Given graph
	int[][] graph = { /*  S  A  B  C  D  T */
                /*S*/   { 0, 7, 0, 8, 0, 0 },
		        /*A*/	{ 7, 0, 6, 3, 0, 0 },
		        /*B*/	{ 0, 6, 0, 4, 2, 5 },
		        /*C*/	{ 8, 3, 4, 0, 3, 0 },
		        /*D*/	{ 0, 0, 2, 3, 0, 2 },
                /*T*/   { 0, 0, 5, 0, 2, 0 }};

	// Function call
	maximumSpanningTree(graph);
}
}

