
public class MatrixFind {

	
	public boolean findElem(int[][] x, int elem)
	{
		int row=x.length;
		int col=x[0].length;
		int tempRow=row;
		int tempCol=col;
		while(tempRow<row&&tempCol<col)
		{
			if(x[tempRow-1][tempCol-1]==elem)
			{
				return true;
			}
			else
				if(x[tempRow-1][tempCol-1]<elem)
				{
					tempRow--;
				}
				else
					if(x[tempRow-1][tempCol-1]>elem)
					{
						tempCol--;
					}
			
		}
		
		return false;
	}
	
}
