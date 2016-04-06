public class Node{

	/*
	 *	Each node stores other Nodes on the right,left,above and below.
	 *	Also its key.
	 *	Setter and getter methods for all private fields.
	 *	PrintNode prints the value of the Node. 
	 */
	
	private Node rightNode;
	private Node belowNode;
	private Node aboveNode;
	private Node leftNode;

	private int key;
	
	public Node(int key){
		setKey(key);
	}

	public void setRight(Node rightNode) {
		this.rightNode = rightNode;
	}

	public void setBelow(Node belowNode) {
		this.belowNode = belowNode;
	}

	public void setAbove(Node aboveNode) {
		this.aboveNode = aboveNode;
	}

	public void setLeft(Node leftNode) {
		this.leftNode = leftNode;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Node getRight(){
		return this.rightNode;
	}

	public Node getBelow(){
		return this.belowNode;
	}

	public Node getAbove(){
		return this.aboveNode;
	}

	public Node getLeft(){
		return this.leftNode;
	}

	public int getKey(){
		return this.key;
	}
	
	/*
	 * If key equals to the Integer.MIN_VALUE it prints -inf.
	 * If key equals to the Integer.MAX_VALUE it prints inf.
	 * Else, print the exact value.
	 */
	
	public void printNode(){
		if (this.getKey() == Integer.MIN_VALUE)
			System.out.print("-inf");
		else if (this.getKey() == Integer.MAX_VALUE){
			System.out.print("inf");
		}
		else
			System.out.print(this.getKey());
	}


}
