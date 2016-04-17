package MultiDimensionalSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MDS4 {

	public TreeMap<Long, PriceandDesc> IDtree = new TreeMap<>();
	public HashMap<ArrayList<Long>, Integer> samesameMap = new HashMap<>();
	public HashMap<Long, TreeMap<Double, Integer>> descriptionMap = new HashMap<>();
	public TreeMap<Double, Integer> priceCountMap = new TreeMap<>();
	int lineNo = 1;

	public void addPriceMapping(double price) {
		Integer count = priceCountMap.get(price);
		if (count == null) {
			priceCountMap.put(price, 1);
		} else {
			priceCountMap.put(price, ++count);
		}
	}

	public void updatePriceMapping(double oldPrice, double newPrice) {
		Integer count = priceCountMap.get(oldPrice) - 1;
		if (count == 0) {
			priceCountMap.remove(oldPrice);
		} else {
			priceCountMap.put(oldPrice, count);
		}
		if (newPrice != -1)
			addPriceMapping(newPrice);
	}

	int insert(long id, double price, long[] description, int size) {
		// Price Count Map updation

		if (size == 0) {
			if (IDtree.containsKey(id)) {
				updatePriceMapping(IDtree.get(id).price, price);
				IDtree.get(id).setPrice(price);
				return 0;
			} else {
				PriceandDesc priceandDesc = new PriceandDesc(id, price, new HashSet<Long>());
				addPriceMapping(price);
				IDtree.put(id, priceandDesc);
				return 1;
			}
		}
		HashSet<Long> temp = new HashSet<>();

		for (int i = 0; i < size; i++)
			temp.add(description[i]);
		PriceandDesc priceandDesc = new PriceandDesc(id, price, temp);
		PriceandDesc temp2 = IDtree.put(id, priceandDesc);

		if (temp2 == null) {
			addDescriptionMapEntries(priceandDesc);
			addPriceMapping(price);
			// System.out.println((lineNo++)+": Insert: 1");
			if (size >= 8) {
				// to add replace during insert in samesame
				ArrayList<Long> tempList = new ArrayList<>();
				// TreeSet<Long> tempTree = new TreeSet<>();
				for (int i = 0; i < size; i++)
					tempList.add(description[i]);

				Collections.sort(tempList);

				int count = 0;
				try {
					count = samesameMap.get(tempList);
					count = count + 1;
					samesameMap.put(tempList, count);
				} catch (Exception e) {
					samesameMap.put(tempList, 1);
				}

			}

			return 1;
		}

		else {
			updateDescriptionMapEntries(temp2, priceandDesc);
			updatePriceMapping(temp2.price, price);
			// System.out.println((lineNo++)+": Insert: 0");
			if (temp2.description.size() >= 8) {

				ArrayList<Long> tempList = new ArrayList<>();

				for (long a : temp2.description)
					tempList.add(a);

				Collections.sort(tempList);

				int count = samesameMap.get(tempList);

				if (count == 1)
					samesameMap.remove(tempList);
				else
					samesameMap.put(tempList, count - 1);

			}
			if (size >= 8) {
				ArrayList<Long> tempList = new ArrayList<>();
				// TreeSet<Long> tempTree = new TreeSet<>();
				for (int i = 0; i < size; i++)
					tempList.add(description[i]);

				Collections.sort(tempList);

				int count = 0;
				try {
					count = samesameMap.get(tempList);
					samesameMap.put(tempList, count + 1);
				} catch (Exception e) {
					samesameMap.put(tempList, 1);
				}

			}
			return 0;
		}
	}

	double find(long id) {

		try {
			Double price = IDtree.get(id).price;
			price *= 100;
			// System.out.println((lineNo++)+": Find:
			// "+Math.floor((price/100.0)*100)/100);
			return price;
		} catch (Exception e) {
			// System.out.println((lineNo++)+": Find: "+0.0);
			return 0.0;
		}
	}

	long delete(long id) {
		PriceandDesc temp = IDtree.remove(id);
		if (temp == null) {
			// System.out.println((lineNo++)+": Delete: "+0);
			return 0;
		} else {
			updateDescriptionMapEntries(temp, null);
			updatePriceMapping(temp.price, -1);
			long sum = 0;
			for (long s : temp.description)
				sum += s;

			if (temp.description.size() >= 8) {

				ArrayList<Long> tempList = new ArrayList<>();
				// TreeSet<Long> tempTree = new TreeSet<>();
				for (long a : temp.description)
					tempList.add(a);

				Collections.sort(tempList);
				// updated
				Integer count = samesameMap.get(tempList);
				if (count == 1)
					samesameMap.remove(tempList);
				else
					samesameMap.put(tempList, count - 1);
			}
			// System.out.println((lineNo++)+": Delete: "+sum);
			return sum;
		}
	}

	double findMinPrice(Long des) {
		// updated
		Double minPrice = descriptionMap.get(des).ceilingKey(0.0);
		// System.out.println("FindMinPrice : "+minPrice);
		if (minPrice == null)
			return 0;
		return minPrice * 100;
	}

	double findMaxPrice(long des) {
		// update
		Double maxPrice = descriptionMap.get(des).floorKey(Double.MAX_VALUE);
		// System.out.println("FindMaxPrice : "+maxPrice);
		if (maxPrice == null)
			return 0;
		return maxPrice * 100;
	}

	int findPriceRange(long des, double lowPrice, double highPrice) {

		int count = 0;
		NavigableMap<Double, Integer> priceRangeMap = descriptionMap.get(des).subMap(lowPrice, true, highPrice, true);
		for (Entry<Double, Integer> entry : priceRangeMap.entrySet()) {
			count += entry.getValue();
		}
		// System.out.println("FindPriceRange : "+count);
		return count;
	}

	double priceHike(long minid, long maxid, double rate) {
		double sum = 0.0;
		NavigableMap<Long, PriceandDesc> rangeMap = IDtree.subMap(minid, true, maxid, true);

		for (Entry<Long, PriceandDesc> e : rangeMap.entrySet()) {
			double price = e.getValue().price;
			double newPrice = e.getValue().price + ((price / 100) * rate);
			newPrice = Math.floor(newPrice * 100) / 100;
			updateDescriptionMapEntries(e.getValue(), new PriceandDesc(e.getKey(), newPrice, e.getValue().description));
			updatePriceMapping(e.getValue().price, newPrice);
			e.getValue().setPrice(newPrice);
			IDtree.put(e.getKey(), e.getValue());
			sum = sum + (long) (newPrice * 100) - (long) (price * 100);
		}
		/*
		 * long sum = 0; for (Long a : IDtree.keySet()) { if (a >= minid && a <=
		 * maxid) { PriceandDesc temp = IDtree.get(a); double price =
		 * temp.price; double newPrice = temp.price + ((price / 100) * rate);
		 * newPrice = Math.floor(newPrice*100)/100;
		 * updateDescriptionMapEntries(temp, new PriceandDesc(temp.id,newPrice,
		 * temp.description)); temp.setPrice(newPrice); sum = sum +
		 * (long)(newPrice*100) - (long)(price*100); } }
		 */
		// System.out.println((lineNo++)+": PriceHike: "+((double)sum/100.0));

		return (double) sum;
	}

	int range(double lowPrice, double highPrice) {
		int count = 0;
		NavigableMap<Double, Integer> rangeMap = priceCountMap.subMap(lowPrice, true, highPrice, true);
		
		for(Entry<Double, Integer> e : rangeMap.entrySet()){
			count += e.getValue();
		}
		//System.out.println((lineNo++)+": Range: "+count);
		return count;
	}

	int samesame() {

		int count = 0;

		for (ArrayList<Long> a : samesameMap.keySet()) {
			// updated
			int value = samesameMap.get(a);
			if (value > 1)
				count = count + value;
		}
		return count;
	}

	public void addDescriptionMapEntries(PriceandDesc priceandDesc) {
		for (Long description : priceandDesc.description) {
			if (!descriptionMap.containsKey(description)) {
				// Seeing description for the first Time
				TreeMap<Double, Integer> priceMapForDescription = new TreeMap<>();
				priceMapForDescription.put(priceandDesc.price, 1);
				descriptionMap.put(description, priceMapForDescription);
			} else {
				Integer count = descriptionMap.get(description).get(priceandDesc.price);
				if (count == null)
					descriptionMap.get(description).put(priceandDesc.price, 1);
				else
					descriptionMap.get(description).put(priceandDesc.price, ++count);
			}
		}
	}

	// Update is happening. Must change the entries in price for descriptions
	private void updateDescriptionMapEntries(PriceandDesc oldEntry, PriceandDesc newEntry) {
		// TODO Auto-generated method stub
		for (Long description : oldEntry.description) {
			Integer count = descriptionMap.get(description).get(oldEntry.price);
			if (count > 1)
				descriptionMap.get(description).put(oldEntry.price, --count);
			else
				descriptionMap.get(description).remove(oldEntry.price);
		}
		if (newEntry != null)
			addDescriptionMapEntries(newEntry);
	}

}
