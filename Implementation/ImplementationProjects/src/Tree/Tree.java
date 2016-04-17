package Tree;


/*** @author Koushik Balaji Venkatesan ***/
/** Program to illustrate effect of calling height/depth recursively from each node of a tree
 *  Sample runs:
 *  > java Tree  (uses default depth of 100)
 *  Answer: 4613 Time: 2 msec.
 *
 *  > java Tree 1000
 *  Answer: 24745 Time: 3 msec.
 *
 *  > java Tree 10000
 *  ## Stack overflow.  Need to ask for a larger stack for program to execute.
 *
 *  > java -Xss256m Tree 10000
 *  Answer: 13783 Time: 14 msec.
 *
 *  > java -Xss256m Tree 100000
 *  Answer: 8811 Time: 1074msec.
 *  
 *  > java -Xss256m Tree 1000000
 *  Answer: 146 Time: 11249msec.
 *
 */


import java.lang.Math;
import java.util.Queue;
import java.util.LinkedList;

public class Tree {
	
    TreeNode root;
    static final long p = 999959;
    Tree() { root = new TreeNode(0); }

    /** binary tree node */
    public class TreeNode {
	long data;
	long depth;
	long height;
	TreeNode left, right, parent;

	
	TreeNode(long x) { data = x;  left = null;  right = null;  parent = null; }

	/** create a new node that is attached to par node as left child if goLeft is true;
	 *  otherwise, the new node is attached to par as right child
	 */
	TreeNode(long x, TreeNode par, boolean goLeft) {
	    data = x;  left = null;  right = null;  parent = par;
	    if (goLeft) { par.left = this; } 
	    else { par.right = this; }
	    
	    //calculate depth of every node
	    if(this.parent==null)
	    	depth=0;
	    else
	    	depth=this.parent.depth+1;
	}
    } 
    // end of TreeNode class


    // If there is a command line argument, it is used as the depth of the tree generated
    public static void main(String[] args) {
	long depth = 1000000;
	if(args.length > 0) 
		depth = Long.parseLong(args[0]);
	
	Tree x = new Tree();

	// Create a tree composed of 2 long paths 
	TreeNode last = x.root;
	for(long i=1; i<=depth; i++) { last = x.new TreeNode(i, last, true); }

	last = x.root;
	for(long i=1; i<=depth; i++) { last = x.new TreeNode(depth+i, last, false); }
	
	
	Timer timer = new Timer();
	// The tree is visited in level order, using a Queue.  Depth and height of each node is computed recursively
	
	long minSum = Long.MAX_VALUE;
	
	//calculate height of every node
	height(x.root);
	
	Queue<TreeNode> Q = new LinkedList<>();
	Q.add(x.root);
	while(!Q.isEmpty()) {
	    TreeNode cur = Q.remove();
	    if(cur != null) {
		minSum = Math.min(minSum, someFunction(cur));
		Q.add(cur.left);  Q.add(cur.right);
	    }
	}
	
	System.out.println("Answer: " + minSum + " " + timer.end());
    }

    static long someFunction(TreeNode cur) {
    	long depthCurr=cur.depth;
    	long heightCurr=cur.height;
    	
	return rotater(rotater(depthCurr*depthCurr)%p + rotater(heightCurr*heightCurr)%p);
	
    }

   static long rotater(long h) {
	h ^= (h >>> 20) ^ (h >>> 12);
	h = (h ^ (h >>> 7) ^ (h >>> 4));
	return h;
    }

    // find the depth of a node 
    //static long depth(TreeNode t) { return t.parent == null ? 0 : 1 + depth(t.parent); }

    // height of a node
      static long height(TreeNode t) { 

    	  //return t == null ? -1 : 1 + Math.max(height(t.left), height(t.right)); 
      
    	  if(t==null)
      {
    	  return -1;
      }
      else
      {
    	  t.height=1 + Math.max(height(t.left), height(t.right));
    	  return t.height;
      }  
      
      }

}