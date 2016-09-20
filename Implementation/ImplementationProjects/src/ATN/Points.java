package ATN;

public class Points {

	static class Point2D
    {
    int x,y;
    Point2D()
    {}
    public Point2D (int a,int b)
        {
        
        x=a;
        y=b;
    }
    
    public Double Dist2d (Point2D p)
        {
        
        return Math.sqrt((this.x-p.x)^2+(this.y-p.y)^2);
    }
    
    public void printDistance (double d)
    {
    	System.out.println("2D distance = "+Math.ceil(d));
    }
    
    
}

class Point3D extends Point2D
    {
    
    int x,y,z;
    public Point3D (int a,int b,int c)
        
    {
    	x=a;
    	y=b;
        z=c;
        
    }
    
    public Double Dist3d (Point3D p)
    {
    
    return Math.sqrt((this.x-p.x)^2+(this.y-p.y)^2+(this.z-p.z)^2);
}

public void printDistance (double d)
{
	System.out.println("3D distance = "+Math.ceil(d));
}
    
}
	
public static void main(String ar[])
{

	Point2D p=new Point2D(2,3);
	double d=p.Dist2d(new Point2D(1,2));

}

}
