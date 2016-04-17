package lp4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MDS {
	
	public TreeMap<Long,PriceandDesc> IDtree=new TreeMap<>();
	public HashMap<TreeSet<Long>,Integer> samesameMap=new HashMap<>();
	
    int insert(long id, double price, long[] description, int size) {
	// Description of item is in description[0..size-1].
	// Copy them into your data structure.
    	HashSet<Long> temp=new HashSet<>();
    	for(long a:description)
    		temp.add(a);
    	
    	if(size>=8)
    	{
    		//to add replace during insert in samesame
    		TreeSet<Long> tempTree=new TreeSet<>();
    		for(long a:description)
        		tempTree.add(a);
    		
    		//try catch
    		Integer count=samesameMap.get(tempTree);
    		if(count==null)
    			samesameMap.put(tempTree, 1);
    		else
    			samesameMap.put(tempTree, count++);
    		
    	}
    	if(IDtree.put(id,new PriceandDesc(id,price, temp))==null)
    		return 1;
    	else
    		return 0;
    
    	
    	//should accomodate samesame for deletion
    }

    double find(long id) {
    	
    	try{
    		Double price=IDtree.get(id).price;
    		return price;
    	}
    	catch(Exception e)
    	{
    		return 0.0;
    	}
    }

    long delete(long id) {
    	
    	PriceandDesc temp=IDtree.remove(id);
    	if(temp==null)
    		return 0;
    	else
    	{
    		long sum=0;
    		
    		for(long s:temp.description)
    			sum+=s;
    		
    		TreeSet<Long> tempTree=new TreeSet<>();
    		for(long a:temp.description)
        		tempTree.add(a);
    		samesameMap.remove(tempTree);
    		
    		return sum;
    	}
    }

    double findMinPrice(long des) {
    	
    	double minPrice=0.0;
    	for(Long a:IDtree.keySet())
    	{
    		minPrice=Integer.MAX_VALUE;
    		PriceandDesc temp=IDtree.get(a);
    		if(temp.description.contains(des))
    		{
    		if(temp.price<minPrice)
    			minPrice=temp.price;
    		}
    	}
    	
	return minPrice;
    }

    double findMaxPrice(long des) {
    	
    	double maxPrice=0.0;
    	for(Long a:IDtree.keySet())
    	{
    		maxPrice=Integer.MIN_VALUE;
    		PriceandDesc temp=IDtree.get(a);
    		if(temp.description.contains(des))
    		{
    		if(temp.price>maxPrice)
    			maxPrice=temp.price;
    		}
    	}
    	
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
    	
	return count;
    }

    double priceHike(long minid, long maxid, double rate) {
    	
    	double sum=0.0;
    	for(Long a:IDtree.keySet())
    	{
    		if(a>=minid&&a<=maxid)
    		{
    			PriceandDesc temp=IDtree.get(a);
    			double price=temp.price;
    			double newPrice=(temp.price+((temp.price/100)*rate));
    			temp.setPrice(newPrice);
    			sum=sum+newPrice-price;
    		}
    	}
    	
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
    	
	return count; 
	}

    int samesame() {
    	
    	int count=0;
    	
    	for(TreeSet<Long> a:samesameMap.keySet())
    	{
    		count=count+samesameMap.get(a);
    	}
	return count;
    }
}