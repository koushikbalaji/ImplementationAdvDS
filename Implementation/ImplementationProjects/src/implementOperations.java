
public class implementOperations {

	public int mult(int a,int b)
	{
		//a*b=b(a+a...)
		
		for(int i=1;i<=b;i++)
			a+=a;
		
		return a;
		
	}
	
	public int divide(int a,int b)
	{
		if(a<b)
			return 0;
		if(b==1)
			return a;
		
		int i=-1;
		int c=0;
		
		while(c<=a)
		{
			c=c+b;
			i++;
		}
		return i;
	}
	
	public int sub(int a,int b)
	{
		//signedInt c=makeNegativeSignedOf(b);
		
//		a// positive signed int
//		
//		a+c;  //a-b
		return 0;
	}
	
	public static void main(String ar[])
	{
		System.out.println(new implementOperations().divide(10,8));
	}
	
}
