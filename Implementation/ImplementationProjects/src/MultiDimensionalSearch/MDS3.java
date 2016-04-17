package MultiDimensionalSearch;


import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class MDS3 {

	public HashMap<Long, PriceandDesc> IDtree = new HashMap<>();
	public HashMap<TreeSet<Long>, Integer> samesameMap = new HashMap<>();
	int lineNo=1;
	int insert(long id, double price, long[] description, int size) {

		if (size == 0) {
			if (IDtree.containsKey(id)) {
				IDtree.get(id).setPrice(price);
				//System.out.println((lineNo++)+": Insert: 0");
				return 0;
			} else {
				IDtree.put(id, new PriceandDesc(id,price, new HashSet<Long>()));
				//System.out.println((lineNo++)+": Insert: 1");
				return 1;
			}
		}
		HashSet<Long> temp = new HashSet<>();

		for (int i = 0; i < size; i++)
			temp.add(description[i]);

		PriceandDesc temp2 = IDtree.put(id, new PriceandDesc(id,price, temp));

		if (temp2 == null) {
			//System.out.println((lineNo++)+": Insert: 1");

			if (size >= 8) {
				// to add replace during insert in samesame
				TreeSet<Long> tempTree = new TreeSet<>();
				for (int i = 0; i < size; i++)
					tempTree.add(description[i]);

				int count = 0;
				try {
					count = samesameMap.get(tempTree);
					count = count + 1;
					samesameMap.put(tempTree, count);
				} catch (Exception e) {
					samesameMap.put(tempTree, 1);
				}

			}

			return 1;
		}

		else {
			//System.out.println((lineNo++)+": Insert: 0");
			if (temp2.description.size() >= 8) {
				TreeSet<Long> tempTree = new TreeSet<>();
				for (long a : temp2.description)
					tempTree.add(a);
				
				int count = samesameMap.get(tempTree);
				
				if (count == 1)
					samesameMap.remove(tempTree);
				else
					samesameMap.put(tempTree, count-1);

			}
			if (size >= 8) {
				TreeSet<Long> tempTree = new TreeSet<>();
				for (int i = 0; i < size; i++)
					tempTree.add(description[i]);

				int count = 0;
				try {
					count = samesameMap.get(tempTree);
					samesameMap.put(tempTree, count+1);
				} catch (Exception e) {
					samesameMap.put(tempTree, 1);
				}

			}
			return 0;
		}
	}

	double find(long id) {

		try {
			Double price = IDtree.get(id).price;
			price *= 100;
			//System.out.println((lineNo++)+": Find: "+Math.floor((price/100.0)*100)/100);
			return price;
		} catch (Exception e) {
			//System.out.println((lineNo++)+": Find: "+0.0);
			return 0.0;
		}
	}

	long delete(long id) {
		PriceandDesc temp = IDtree.remove(id);
		if (temp == null){
			//System.out.println((lineNo++)+": Delete: "+0);
			return 0;
		}
		else {
			long sum = 0;
			for (long s : temp.description)
				sum += s;

			if (temp.description.size() >= 8) {
				TreeSet<Long> tempTree = new TreeSet<>();
				for (long a : temp.description)
					tempTree.add(a);
				// updated
				Integer count = samesameMap.get(tempTree);
				if (count == 1)
					samesameMap.remove(tempTree);
				else
					samesameMap.put(tempTree, count - 1);
			}
			//System.out.println((lineNo++)+": Delete: "+sum);
			return sum;
		}
	}

	double findMinPrice(long des) {
		// updated
		double minPrice = Double.MAX_VALUE;
		boolean desFound = false;
		for (Long a : IDtree.keySet()) {
			PriceandDesc temp = IDtree.get(a);
			if (temp.description.contains(des)) {
				desFound = true;
				if (temp.price < minPrice) {
					minPrice = temp.price;
				}
			}
		}
		// //System.out.println("FindMinPrice : "+minPrice);
		if(!desFound){
			//System.out.println((lineNo++)+": FindMinPrice: "+0.0);
			return 0.0;
		}
		//System.out.println((lineNo++)+": FindMinPrice: "+minPrice);
		return minPrice*100;
	}

	double findMaxPrice(long des) {
		double maxPrice = Math.floor(0.0*100)/100;;
		for (Long a : IDtree.keySet()) {
			PriceandDesc temp = IDtree.get(a);
			if (temp.description.contains(des)) {
				if (temp.price > maxPrice)
					maxPrice = temp.price;
			}
		}
		//System.out.println((lineNo++)+": FindMaxPrice: "+maxPrice);
		return  maxPrice*100;
	}

	int findPriceRange(long des, double lowPrice, double highPrice) {

		int count = 0;
		for (Long a : IDtree.keySet()) {
			PriceandDesc temp = IDtree.get(a);
			if (temp.description.contains(des)) {
				if (temp.price >= lowPrice && temp.price <= highPrice)
					count++;
			}
		}
		//System.out.println((lineNo++)+": FindPriceRange: "+count);
		return count;
	}

	double priceHike(long minid, long maxid, double rate) {

		long sum = 0;
		for (Long a : IDtree.keySet()) {
			if (a >= minid && a <= maxid) {
				PriceandDesc temp = IDtree.get(a);
				double price = temp.price;
				double newPrice = temp.price + ((price / 100) * rate);
				newPrice = Math.floor(newPrice*100)/100;

				temp.setPrice(newPrice);
				sum = sum + (long)(newPrice*100) - (long)(price*100);
			}
		}
		//System.out.println((lineNo++)+": PriceHike: "+((double)sum/100.0));

		return (double)sum;
	}

	int range(double lowPrice, double highPrice) {
		int count = 0;
		for (Long a : IDtree.keySet()) {
			PriceandDesc temp = IDtree.get(a);
			if (temp.price >= lowPrice && temp.price <= highPrice)
				count++;
		}
		//System.out.println((lineNo++)+": Range: "+count);
		return count;
	}

	int samesame() {

		int count = 0;

		for (TreeSet<Long> a : samesameMap.keySet()) {
			// updated
			int value = samesameMap.get(a);
			if (value > 1)
				count = count + value;
		}
		return count;
	}
}