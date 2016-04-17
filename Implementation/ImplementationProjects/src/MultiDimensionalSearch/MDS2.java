package MultiDimensionalSearch;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class MDS2 {

	public long lineNo=1;
	public HashMap<Long,PriceandDesc> IDtree=new HashMap<>();
	public HashMap<TreeSet<Long>,Integer> samesameMap=new HashMap<>();
	public HashMap<Long,MaxAndMin> maxmin=new HashMap<>();
	
    int insert(long id, double price, long[] description, int size) {
	// Description of item is in description[0..size-1].
	// Copy them into your data structure.
    	MaxAndMin tempo;
    	for (int i=0;i<size;i++)
    	{
    		tempo =maxmin.get(description[i]);
    	if(tempo!=null)
    		if(price>tempo.max)
    			tempo.setMax(price);
    		else
    			tempo.setMin(price);
    	
    	else
    		maxmin.put(description[i], new MaxAndMin(price,price));
    	}
    	
    	if(size==0)
    	{
    		if(IDtree.containsKey(id))
    		{
    	    IDtree.get(id).price = price; 			
    		//System.out.println((lineNo++)+": "+"Insert: "+0);
    		return 0;
    		}
    		else
    		{
    		IDtree.put(id,new PriceandDesc(id,price, new HashSet<Long>()));
    		//System.out.println((lineNo++)+": "+"Insert: 1" );
    		return 1;
    		}
    	}
    	HashSet<Long> temp=new HashSet<>();
        
    	for (int i=0;i<size;i++)
    		temp.add(description[i]);
    	//for(long a:description)
    		//temp.add(a);
    	
    	PriceandDesc temp2=IDtree.put(id,new PriceandDesc(id,price, temp));
    	
//    	////System.out.println(id);
    	if(temp2==null){
    		//////System.out.println("Insert : 1");
    		
        	if(size>=8)
        	{
        		//to add replace during insert in samesame
        		TreeSet<Long> tempTree=new TreeSet<>();
            	for (int i=0;i<size;i++)
            		tempTree.add(description[i]);
        		
        		int count=0;
        		try
        		{
        			count=samesameMap.get(tempTree);
        			
        			count = count + 1;
        			samesameMap.put(tempTree, count);
        		}
        		catch(Exception e)
        		{
        			samesameMap.put(tempTree, 1);
        		}
        	
        	}
        	
        	//////System.out.println(samesameMap.size());
        	//System.out.println((lineNo++)+": "+"Insert: 1" );
    		return 1;
    	}
    		
    	else{
    		
    		//////System.out.println("Insert : 0");
    		if(temp2.description.size()>=8)
    		{
    			TreeSet<Long> tempTree=new TreeSet<>();
        		for(long a:temp2.description)
            		tempTree.add(a);
//        		////System.out.println(temp);
//        		////System.out.println(tempTree);
//        		////System.out.println(samesameMap);
        		
    		int count=0;
			try {
				count = samesameMap.get(tempTree);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				////System.out.println(id);
				e.printStackTrace();
				////System.out.println(tempTree);
			}
    		
    		TreeSet<Long> tempTree2=new TreeSet<>();
        	for (int i=0;i<size;i++)
        		tempTree2.add(description[i]);
    		//for(long a:description)
        		//tempTree2.add(a);
    		count = count - 1;
    		samesameMap.put(tempTree2, count);
    		}
    		//////System.out.println(samesameMap.size());
    		if(size>=8)
        	{
        		//to add replace during insert in samesame
        		TreeSet<Long> tempTree=new TreeSet<>();
            	for (int i=0;i<size;i++)
            		tempTree.add(description[i]);
        		
        		int count=0;
        		try
        		{
        			count=samesameMap.get(tempTree);
        			
        			count = count + 1;
        			samesameMap.put(tempTree, count);
        		}
        		catch(Exception e)
        		{
        			samesameMap.put(tempTree, 1);
        		}
        	
        	}
    		//System.out.println((lineNo++)+": "+"Insert: 0" );
    		return 0;
    	}
    }

    double find(long id) {
    	
    	try{
    		Double price=IDtree.get(id).price;
    		////System.out.println("Find : "+price);
    		//System.out.println((lineNo++)+": "+"Find: "+price);
    		
    		return price;
    		
    	}
    	catch(Exception e)
    	{
    		////System.out.println("Find : "+0.0);
    		//System.out.println((lineNo++)+": "+"Find: "+0.0);
    		return 0.0;
    	}
    }

    long delete(long id) {
    	
    	PriceandDesc temp=IDtree.remove(id);
    	if(temp==null)
    	{	
    		//System.out.println((lineNo++)+": "+"Delete: "+0);
    		return 0;
    	}
    	else
    	{
    		long sum=0;
    		
    		for(long s:temp.description)
    			sum+=s;
    		
    		if(temp.description.size()>=8)
    		{
    		TreeSet<Long> tempTree=new TreeSet<>();
    		for(long a:temp.description)
        		tempTree.add(a);
    		// updated
    		Integer count = samesameMap.get(tempTree);
    		//if (count != null){
        		if (count == 1)
        			samesameMap.remove(tempTree);
        		else
        			samesameMap.put(tempTree, count-1);
    		//}
    		}
    		//System.out.println((lineNo++)+": "+"Delete: "+sum);
    		
    		for(Long a:temp.description)
    		{
    			maxmin.put(a, new MaxAndMin(findMaxPrice2(a),findMinPrice2(a)));
    		}
    		
    		return sum;
    	}
    }

    double findMinPrice(long des) {
    	// updated
    	return maxmin.get(des).min;
    }
    
    double findMinPrice2(long des) {
    	// updated
    	double minPrice=Double.MAX_VALUE;
    	for(Long a:IDtree.keySet())
    	{
    		PriceandDesc temp=IDtree.get(a);
    		if(temp.description.contains(des))
    		{
    		if(temp.price<minPrice){
    			minPrice=temp.price;
    		}
    		}
    	}
    	//System.out.println((lineNo++)+": "+"FindMinPrice: "+minPrice);
	return minPrice;
    }

    double findMaxPrice(long des) {
    	// updated
    return maxmin.get(des).max;
    }

    double findMaxPrice2(long des) {
    	// updated
    	double maxPrice=Double.MIN_VALUE;
    	for(Long a:IDtree.keySet())
    	{
    		PriceandDesc temp=IDtree.get(a);
    		if(temp.description.contains(des))
    		{
    		if(temp.price>maxPrice)
    			maxPrice=temp.price;
    		}
    	}
    	//System.out.println((lineNo++)+": "+"FindMaxPrice: "+maxPrice);
	return maxPrice;
    }
    int findPriceRange(long des, double lowPrice, double highPrice) {
    	
    	int count=0;
    	for(Long a:IDtree.keySet())
    	{
    		PriceandDesc temp=IDtree.get(a);
    		if(temp.description.contains(des))
    		{
    		if(temp.price>=lowPrice&&temp.price<=highPrice)
    			count++;
    		}
    	}
    	//System.out.println((lineNo++)+": "+"FindPriceRange: "+count);
	return count;
    }

    double priceHike(long minid, long maxid, double rate) {
    	
    	double sum=0.0;
    	DecimalFormat df = new DecimalFormat("#.##");
    	for(Long a:IDtree.keySet())
    	{
    		if(a>=minid&&a<=maxid)
    		{
    			PriceandDesc temp=IDtree.get(a);
    			double price=temp.price;
    			double newPrice=temp.price+((temp.price/100.0)*rate);
    			try{
    				//newPrice = (df.format(newPrice);
    			}catch(Exception e){
    				e.printStackTrace();
    			}
    			
    			temp.setPrice(newPrice);
    			sum=sum+newPrice-price;
    		}
    	}
    	//System.out.println((lineNo++)+": "+"PriceHike: "+sum);
    	
	return sum;
    }

    int range(double lowPrice, double highPrice) {
    	int count=0;
    	for(Long a:IDtree.keySet())
    	{
    		PriceandDesc temp=IDtree.get(a);
    		if(temp.price>=lowPrice&&temp.price<=highPrice)
    			count++;
    	}
    	//System.out.println((lineNo++)+": "+"Range: "+count);
	return count; 
	}
   
    
    int samesame() {
    	
    	int count=0;
    	
    	for(TreeSet<Long> a:samesameMap.keySet())
    	{
    		// updated
    		int value = samesameMap.get(a);
    		if (value > 1)
    			count=count+value;
    	}
    	//System.out.println((lineNo++)+": "+"SameSame: "+count);
	return count;
    }
}