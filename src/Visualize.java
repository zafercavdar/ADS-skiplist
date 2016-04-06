
public class Visualize {

	/*
	 * In print method, we print the list line by line.
	 * Bottom-most line is the 0th line and top-most is the height'th line.
	 * If height of the key is greater than the height of the line, then print this key.
	 * Else, print dashes instead of key.
	 */
	public static void print(SkipList list){
		System.out.println("Skip List:\n");
		Node s = list.firstNode();
		while(s.getBelow()!= null){
			s = s.getBelow();
		}
		s = s.getRight();


		for (int i= list.getHeight(); i>=0; i--){
			System.out.print("-inf--");
			Node p = s;

			while(p.getKey() != Integer.MAX_VALUE){
				if (p.getKey()%list.getHeight() >= i){
					System.out.print(p.getKey()+"--");
				}
				else{
					System.out.print("----");
				}
				p = p.getRight();
			}

			System.out.println("inf");
		}

		System.out.println();
	}

	/*
	 * printSearchPath prints the keys with a condition.
	 * Left visited node stores the last key which has stars on the right of it.
	 * If searched key is equal or greater than the nextNode of the last printed node &&
	 * nextNode's key is greater than the leftVisited Key, then print * instead of -.
	 * Number of marks are determined by difference of column numbers.
	 */
	public static void printSearchPath(SkipList list,int key){

		System.out.println("Search path of " + key + ":\n");

		int leftVisited = list.firstNode().getKey();
		Node willPrintNode = list.firstNode();
		Node remarkPoint = willPrintNode;

		while(willPrintNode!= null){
			while(willPrintNode.getKey() != Integer.MAX_VALUE){
				willPrintNode.printNode();
				Node nextNode = willPrintNode.getRight();

				int dif = list.columnOf(nextNode.getKey()) - list.columnOf(willPrintNode.getKey());
				int numofMarks = 4*dif-2;
				boolean printStars = false;

				for (int i=0; i < numofMarks;i++){
					if (nextNode.getKey() <= key && leftVisited<nextNode.getKey()){
						System.out.print("*");
						printStars = true;
					}
					else
						System.out.print("-");
				}			

				if (printStars)
					leftVisited = nextNode.getKey();

				willPrintNode = nextNode;
			}
			System.out.println("inf");

			willPrintNode = remarkPoint.getBelow();
			remarkPoint = willPrintNode;
		}
		System.out.println();
	} 
}
