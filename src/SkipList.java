/*
 * SkipList class has two main methods.
 * Insert and search.
 * It stores left and upper most Node and its height.
 */

public class SkipList{

	private int maxCapacity;
	private Node start;
	private int height;

	/*
	 * Initializes the SkipList.
	 * Creates number of height Nodes with value -infinity.
	 * Creates number of height Nodes with value +infinity.
	 * Creates the left,right,below and above relations between them.
	 */
	public SkipList(int maxCapacity){
		this.maxCapacity = maxCapacity;
		height = (int) Math.ceil(Math.log(this.maxCapacity)/Math.log(2));

		start = new Node(Integer.MIN_VALUE);
		Node end = new Node(Integer.MAX_VALUE);
		start.setRight(end);
		end.setLeft(start);

		Node currentLeft = start;
		Node currentRight = end;

		for (int i= 0; i < height; i++){
			Node p = new Node(Integer.MIN_VALUE);
			Node q = new Node(Integer.MAX_VALUE);
			currentLeft.setBelow(p);
			currentRight.setBelow(q);
			p.setAbove(currentLeft);
			q.setAbove(currentRight);
			p.setRight(q);
			q.setLeft(p);

			currentLeft = p;
			currentRight = q;
		}

	}
	
	/*
	 * Search for the key.
	 * Search returns exactly the Node with that key if it can find.
	 * If search does not find the key, then returns the Node which has largest key which is smaller than "key" in the bottom line
	 * of Skip List.
	 * If key exists in the SkipList, do not do anything.
	 * Else, calculate how many keys to insert.
	 * Add after return of search(key).
	 * Number of keyHeight times find above nodes which we can add after the "key".
	 */
	public Node insert(int key){
		int keyHeight = key % height;
		Node returnNode;

		Node p = search(key);
		
		if (p.getKey() != key){
			Node q = p.getRight();
			Node newP = new Node(key);

			q.setLeft(newP);
			newP.setRight(q);
			p.setRight(newP);
			newP.setLeft(p);

			returnNode = newP;

			for(int i=0; i <keyHeight; i++){
				while(p.getAbove() == null){
					p = p.getLeft();
				}
				p = p.getAbove();
				Node q2 = p.getRight();
				Node n = new Node(key);

				q2.setLeft(n);
				n.setRight(q2);
				p.setRight(n);
				n.setLeft(p);
				n.setBelow(newP);
				newP.setAbove(n);

				returnNode = n;

				newP = n;
			}
			return returnNode;
		}
		else return p;
	}

	/*
	 * Start from the left and upper most node.
	 * If rightKey is smaller than or equal to the key, go Right.
	 * Else, go below and continue search until getBelow will return NULL.
	 * At the end of the search, node which has biggest key which is smaller than "key" or the node contains "key" 
	 * will be found. Since while terminates itself when getBelow returns NULL, we are sure that returned Node will 
	 * from the bottom most List. 
	 */
	public Node search(int key){
		Node p = start;
		while(p.getBelow()!= null){
			p = p.getBelow();
			while(p.getRight().getKey() <= key){
				p = p.getRight();
			}
		}
				
		return p;
	}

	/*
	 * Returns the left and upper most Node in the List.
	 */
	public Node firstNode(){
		return start;
	}
	
	/*
	 * Returns calculates height in the constructor.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/*
	 * Find in which column the key exists.
	 * This method is used to determine how long the distance is from one Node to another in the same line.
	 */
	public int columnOf(int key){
		Node p = start;
		while(p.getBelow() != null){
			p = p.getBelow();
		}
		int count = 0;
		while (p.getKey() != key){
			if (p.getKey() == Integer.MAX_VALUE)
				break;
			else{
				p = p.getRight();
				count++;
			}
		}
		
		if (p.getKey() == key)
			return count;
		else
			return -1;
		
	}
}
