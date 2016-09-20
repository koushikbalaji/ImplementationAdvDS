package ATN;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
	    public static void main(String args[] ) throws Exception {
	        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
	        Scanner scan=new Scanner(System.in);
	        for(int i=0;i<scan.nextInt();i++)
	        {
	             int num = scan.nextInt();
	            int elements[]=new int[num];
	        
	            if(checkList(elements,num))
	            	System.out.println("YES");
	            else
	            	System.out.println("NO");
	            
	    }
	        
	      
	    }
	    public static Boolean checkList(int elements[],int n)
        {
	    	Stack<Integer> s = new Stack<Integer>();
	    	 
	       
	        int root = Integer.MIN_VALUE;
	 
	       
	        for (int i = 0; i < n; i++) {
	            if (elements[i] < root) {
	                return false;
	            }
	            while (!s.empty() && s.peek() < elements[i]) {
	                root = s.peek();
	                s.pop();
	            }
	 
	            s.push(elements[i]);
	        }
	        return true;
	 
        	
        	
        }
	}
	
	
