import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Operations {

	/*
	 * Start from the upper and left most Node in the list.
	 * Go to the below until finding NULL.
	 * In the below-most line, visit each node and save its key and height to the file.
	 */
	public static void save(SkipList list, String outputFile){
		
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new FileWriter(outputFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Node s = list.firstNode();
		while(s.getBelow()!= null){
			s = s.getBelow();
		}
		s = s.getRight();
		
		while (s.getKey() != Integer.MAX_VALUE){
			pw.print(s.getKey() + " " + s.getKey()%list.getHeight()+"\r\n");	
			s = s.getRight();
		}
		
		pw.close();
	}
	
	/*
	 * Read the keys and heights in the file.
	 * At the bottom line, start with addAfter -inf.
	 * Find the above node which we can add after.
	 * After adding height times of key,
	 * Start adding after the bottom-most node of that key.
	 * Do it for all keys.
	 */
	public static void load(String input, SkipList list){
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader(input));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Node p = list.firstNode();
		while(p.getBelow()!= null)
			p = p.getBelow();
		
		while(true){
			String pairs = null;
			try {
				pairs = rd.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (pairs == null)
				break;

			StringTokenizer st = new StringTokenizer(pairs, " ");
			int key = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			Node q = p.getRight();
			Node newP = new Node(key);

			q.setLeft(newP);
			newP.setRight(q);
			p.setRight(newP);
			newP.setLeft(p);
			
			for(int i=0; i <height; i++){
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
				newP = n;
			}
			
			/*
			 * Node p is the node which we add after it.
			 * After adding height times the key,
			 * update the p.
			 */
			p = p.getRight();
			while(p.getBelow() != null){
				p = p.getBelow();
			}
		}
		
	}
}
