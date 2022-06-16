//Chetan Goyal
//CWID - 20005334

import java.util.Stack;
import java.util.Random;

public class Treap <E extends Comparable<E>> {
	private static class Node<E>{
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E > left;
		public Node<E > right;

        //Creates a new node with the given data and priority
		public Node(E data, int priority){
			if(data == null)
				throw new NullPointerException("Null data present! Not permitted.");
			else {
			this.left = null;
			this.right = null;
			this.data = data;
			this.priority = priority;
		}}

        //rotateRight() performs a right rotation
		public Node<E > rotateRight() {
			Node<E> rootNode = new Node<E>(data, priority);
			if(this.left!=null){
				rootNode.left = this.left.right;
				rootNode.right = this.right;
				this.priority = this.left.priority;
				this.data = this.left.data;			
				this.left = this.left.left;
				this.right = rootNode;
			}
			return this;
			}

        //rotateLeft() performs a left rotation
		public Node<E > rotateLeft() {
			Node<E> rootNode = new Node<E>(data, priority);
			if(this.right!=null)
			{
				rootNode.left = this.left;
				rootNode.right = this.right.left;
				this.priority = this.right.priority;
				this.data = this.right.data;				
				this.right = this.right.right;
				this.left = rootNode;
			}
			return this;
		}}

	private Node<E> root;
	private Random priorityGenerator;

    //Treap() creates an empty treap
	public Treap(){
		priorityGenerator = new Random();
		root = null;
	}

    //Treap(long seed) creates an empty treap and initializes priorityGenerator using new Random(seed)
	public Treap(long seed){
		priorityGenerator = new Random(seed);
		root = null;
	}

    //To insert the given element into the tree
	boolean add(E key){
		return add(key, priorityGenerator.nextInt());
	}

    //To insert the given element into the tree with priority defined
	boolean add(E key, int priority)
	{
		Node<E > newNode = new Node<E>(key, priority);
		Node<E > tempRoot = root;
		Stack<Node> stack = new Stack<Node>();
		if(root==null)
		{
			root = new Node(key, priority);
			return true;
		}
		if(find(key)){
			return false;
		}
		while(tempRoot!=null)
		{
			stack.push(tempRoot);
			if((key.compareTo(tempRoot.data)) < 0)
				tempRoot = tempRoot.left;
			else
				tempRoot = tempRoot.right;
		}
		
		if((key.compareTo((E)stack.peek().data)) < 0)	
			stack.peek().left = newNode;
		else
		{		
			stack.peek().right = newNode;			
		}
		stack.push(newNode);
		reheap(stack);
		return true;
	}

    //helper function reheap (with appropriate param- eters that should include the stack) to restore the heap invariant
	private void reheap(Stack<Node> stack) {
		Node<E> child = stack.pop();
		Node<E> parent = stack.pop();
		while(parent!= null && child.priority > parent.priority)
		{
			if((child.data.compareTo(parent.data)) > 0)
				parent.rotateLeft();
			else
				parent.rotateRight();
			if(!stack.isEmpty())
				parent = stack.pop();
			else
				parent = null;
		}}

    //boolean delete(E key) deletes the node with the given key from the treap
	public boolean delete(E key){
		Node<E> nodeFound = null ;
		Node<E> lastParent = null;
		Node<E> tempRoot = null;
		if (find(key) == false || root==null)
			return false;
		else
		{
			while(root!= null)
			{
				if(key.compareTo(root.data) < 0){
					tempRoot = root;
					root = root.left;
				}
				else if((key.compareTo(root.data)) > 0){
					tempRoot = root;
					root = root.right;
				}
				else{
					nodeFound = root;
					break;
				}
			}
			while((nodeFound.right!=null)||(nodeFound.left!=null))
			{
				if(nodeFound.right==null )
				{
					lastParent = nodeFound.rotateRight();
					nodeFound = lastParent.right;
				}
				else if(nodeFound.left == null)
				{
					lastParent = nodeFound.rotateLeft();
					nodeFound = lastParent.left;
				}
				else if (nodeFound.left.priority < nodeFound.right.priority)
				{
					lastParent = nodeFound.rotateLeft();
					nodeFound = lastParent.left;
				}
				else if(nodeFound.left.priority > nodeFound.right.priority)
				{
					lastParent = nodeFound.rotateRight();
					nodeFound = lastParent.right;
				}
			}	
			if(lastParent == null)
				lastParent = root;
			if((lastParent.left!= null)&&(lastParent.left.data.compareTo(key))==0)
				lastParent.left = null;
			else
			{
				lastParent.right = null;
			}
			return true;
			}}

    //Finds a node with the given key in the treap rooted at root and returns true if it finds it and false otherwise
	private boolean find(Node<E> root,E key){
		if(root==null)
			return false;
		else if((key.compareTo(root.data))>0)
			return find(root.right, key);
		else if((key.compareTo(root.data))<0)
			return find(root.left, key);
		else 
			return true;
	}

	public boolean find(E key){
		if(key==null) {
			throw new NullPointerException("Key cannot be null");
		}
		return find(root, key);
	}

	public String toString(){
		StringBuilder strbuild = new StringBuilder();
		return getPreOrderTraverse(root, 1, strbuild);
	}

	private String getPreOrderTraverse(Node<E>node, int depth, StringBuilder strbuild){
		for(int i=1; i< depth; i++){
			strbuild.append("  ");
		}		
		if(node==null)
			strbuild.append("null\n");
		else{
			strbuild.append("(key="+node.data+",priority="+node.priority+")\n\n");
			getPreOrderTraverse(node.left, depth + 1,strbuild);
			getPreOrderTraverse(node.right, depth + 1,strbuild);
		}
		return strbuild.toString();
		}

	public static void main(String[] args){
        //1st test
		System.out.println("TEST CASE 1\n");
		Treap<Integer> testTree = new Treap <Integer>();
		testTree.add(4,19);
		testTree.add(2,31);
		testTree.add(6,70);
		testTree.add(1,84);
		testTree.add(3,12);
		testTree.add(5,83);
		testTree.add(7,26);
		System.out.println(testTree.toString());
		System.out.println("Is node with key - 6 present in the treap? "+ testTree.find(6));
		System.out.println("Is node with key - 16 present in the treap? "+ testTree.find(16));
		System.out.println("Node with 6 is deleted: "+ testTree.delete(6));
		System.out.println("Node with key 16 is deleted: "+ testTree.delete(16));
		System.out.println("Is node with key - 6 present in the treap? "+ testTree.find(6));

        //2nd test
		System.out.println("\n\n\nTEST CASE 2\n\n");
		Treap<Character> testTree2 = new Treap<Character>();
		testTree2.add('p',99);
		testTree2.add('g',80);
		testTree2.add('u',75);
		testTree2.add('a',60);
		testTree2.add('j',65);
		testTree2.add('r',40);
		testTree2.add('z',47);
		testTree2.add('w',32);
		testTree2.add('v',21);
		testTree2.add('x',25);
		System.out.println(testTree2.toString());
		testTree2.add('i',93);
		System.out.println("Delete when key with 'z' exists and so the boolean result is: "+ testTree2.delete('z') +"\n");
		System.out.println(testTree2.toString());
		}
	}