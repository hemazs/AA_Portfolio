import java.util.ArrayList;
import java.util.LinkedList;

class Node {

	int data;
	
	Node(int i){
		this.data = i;
	}
}

class GraphAdjList {

	ArrayList<LinkedList<Node>> alist;
	
	GraphAdjList(){
		alist = new ArrayList<>();
	}
	
	public void addNode(Node node) {
		LinkedList<Node> currentList = new LinkedList<>();
		currentList.add(node);
		alist.add(currentList);
	}
	public void addEdge(int src, int dst) {
		LinkedList<Node> currentList = alist.get(src);
		Node dstNode = alist.get(dst).get(0);
		currentList.add(dstNode);
	}
	public boolean checkEdge(int src, int dst) {
		LinkedList<Node> currentList = alist.get(src);
		Node dstNode = alist.get(dst).get(0);
		
		for(Node node : currentList) {
			if(node == dstNode) {
				return true;
			}
		}
		return false;
	}
	public void print() {
		for(LinkedList<Node> currentList : alist) {
			for(Node node : currentList) {
				System.out.print(Character.toString(node.data) + " -> ");
			}
			System.out.println();
		}
	}	
}

public class GraphList {

	public static void main(String[] args) {
		
		GraphAdjList g = new GraphAdjList();
		
		g.addNode(new Node('A'));
		g.addNode(new Node('B'));
		g.addNode(new Node('C'));
		g.addNode(new Node('D'));
		g.addNode(new Node('E'));
		
		g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
		
		g.print();
		
	}
}



