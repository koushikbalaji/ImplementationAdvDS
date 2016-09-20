import java.util.Stack;

public class MyQueue {

	static Stack<Integer> one =new Stack();//old element on top dequeu pops from one 
	static Stack<Integer> two =new Stack(); //on top of stack one
	
	
	public void enQueue(int elem)
	{
		while(!one.isEmpty())
		{
			two.push(one.pop());
		}
		one.push(elem);
		
		while(!two.isEmpty())
		{
			one.push(one.pop());
		}
	}
	
	public int deQueue(int elem)
	{
		if(one.isEmpty())
			System.out.println("no element");
		
		return one.pop();
	}
	
	
}
