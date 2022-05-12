//Node class
class Node
{
	int data;
	Node left, right;

	Node(int item)
	{
		data = item;
		left = right = null;
	}
}

class BinaryTree
{
	Node root;
    //Function to duplicate same tree but add it to the left
	void duplicateTree(Node node)
	{
		Node oldleft;

		if (node == null)
			return;

		duplicateTree(node.left);
		duplicateTree(node.right);

		oldleft = node.left;
		node.left = new Node(node.data);
		node.left.left = oldleft;
	}
    //Prints tree in order of traversal from left to right
	void printtree(Node node)
	{
		if (node == null)
			return;
		printtree(node.left);
		System.out.print(node.data + " ");
		printtree(node.right);
	}

	public static void main(String args[])
	{
		/* Binary tree is
			 2
			/ \
		   1   3
		   \
		    4
		*/
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(2);
		tree.root.left = new Node(1);
		tree.root.right = new Node(3);
		tree.root.left.right = new Node(4);

		System.out.println("Original tree is : ");
		tree.printtree(tree.root);
		tree.duplicateTree(tree.root);
		System.out.println("");
		System.out.println("In order of traversal, the duplicate tree is : ");
		tree.printtree(tree.root);
	}
}

