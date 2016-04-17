package MultiDimensionalSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

public class MDS5 {
	// the below map is maintain the id along with the price and description of
	// the id
	public TreeMap<Long, PriceandDesc> IDtree = new TreeMap<>();
	// the below is to maintain the samesameMap counts
	public HashMap<ArrayList<Long>, Integer> samesameMap = new HashMap<>();
	// the below is to maintain the map between the description and the price
	public HashMap<Long, TreeMap<Double, Integer>> descriptionMap = new HashMap<>();
	// the below is to maintain the key reference by price for the Range
	// function
	public TreeMap<Double, Integer> priceCountMap = new TreeMap<>();
	int lineNo = 1;

	// the below is to insert into the priceCountMap
	public void addPriceMapping(double price) {
		Integer count = priceCountMap.get(price);
		if (count == null) {
			priceCountMap.put(price, 1);
		} else {
			priceCountMap.put(price, ++count);
		}
	}

	// the below is to update into the priceCountMap
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

	// below is the code for insert O(logN)
	int insert(long id, double price, long[] description, int size) {
		// Price Count Map updation
		// if the description size is 0

		if (size == 0) {
			// update
			if (IDtree.containsKey(id)) {
				updatePriceMapping(IDtree.get(id).price, price);
				IDtree.get(id).setPrice(price);
				return 0;
			} // insert
			else {
				PriceandDesc priceandDesc = new PriceandDesc(id, price, new HashSet<Long>());
				addPriceMapping(price);
				IDtree.put(id, priceandDesc);
				return 1;
			}
		}
		// update the temp
		HashSet<Long> temp = new HashSet<>();
		for (int i = 0; i < size; i++)
			temp.add(description[i]);
		PriceandDesc priceandDesc = new PriceandDesc(id, price, temp);
		// add the id to the TreeMap
		PriceandDesc temp2 = IDtree.put(id, priceandDesc);
		// if new
		if (temp2 == null) {
			// update the samesameMap
			addDescriptionMapEntries(priceandDesc);
			addPriceMapping(price);
			if (size >= 8) {
				// to add replace during insert in samesame
				ArrayList<Long> tempList = new ArrayList<>();
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
		// if update
		else {
			// update the samesameMap for previous description
			updateDescriptionMapEntries(temp2, priceandDesc);
			updatePriceMapping(temp2.price, price);
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
			// update the samesameMap for current description
			if (size >= 8) {
				ArrayList<Long> tempList = new ArrayList<>();
				for (int i = 0; i < size; i++)
					tempList.add(description[i]);

				Collections.sort(tempList);

				int count = 0;
				try {
					// for updates
					count = samesameMap.get(tempList);
					samesameMap.put(tempList, count + 1);
				} catch (Exception e) {
					// for inserts
					samesameMap.put(tempList, 1);
				}

			}
			return 0;
		}
	}

	// below is the code for the find runs in O(logN)
	double find(long id) {
		try {
			Double price = IDtree.get(id).price;
			price *= 100;
			return price;
		} catch (Exception e) {
			// if no id exists
			return 0.0;
		}
	}

	// code for delete runs in O(logN)
	long delete(long id) {
		PriceandDesc temp = IDtree.remove(id);
		if (temp == null) {
			// if the id doesn't exists
			return 0;
		} else {
			// update the map entries for the description
			updateDescriptionMapEntries(temp, null);
			// for the price mapping
			updatePriceMapping(temp.price, -1);
			long sum = 0;
			for (long s : temp.description)
				sum += s;
			// update the samesameMap
			if (temp.description.size() >= 8) {

				ArrayList<Long> tempList = new ArrayList<>();
				for (long a : temp.description)
					tempList.add(a);

				Collections.sort(tempList);
				Integer count = samesameMap.get(tempList);
				if (count == 1)
					samesameMap.remove(tempList);
				else
					// reduce the previous count
					samesameMap.put(tempList, count - 1);
			}
			return sum;
		}
	}

	// below is the code for findMinPrice runs in O(logN)
	double findMinPrice(Long des) {
		// updated
		Double minPrice = descriptionMap.get(des).ceilingKey(0.0);
		if (minPrice == null)
			return 0;
		return minPrice * 100;
	}

	// below is the code for findMaxPrice runs in O(logN)
	double findMaxPrice(long des) {
		// update
		Double maxPrice = descriptionMap.get(des).floorKey(Double.MAX_VALUE);
		if (maxPrice == null)
			return 0;
		return maxPrice * 100;
	}

	// below is the code which runs in O((highPrice - lowPrice)* logN)
	int findPriceRange(long des, double lowPrice, double highPrice) {

		int count = 0;
		NavigableMap<Double, Integer> priceRangeMap = descriptionMap.get(des).subMap(lowPrice, true, highPrice, true);
		// return the total count
		for (Entry<Double, Integer> entry : priceRangeMap.entrySet()) {
			count += entry.getValue();
		}
		return count;
	}

	// below is the code which runs in O((maxid - minid)* logN)
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
		// return the totak sum
		return (double) sum;
	}

	// range runs in O(#counts is(highPrice - lowPrice )* logN)
	int range(double lowPrice, double highPrice) {
		int count = 0;
		NavigableMap<Double, Integer> rangeMap = priceCountMap.subMap(lowPrice, true, highPrice, true);

		for (Entry<Double, Integer> e : rangeMap.entrySet()) {
			count += e.getValue();
		}
		// System.out.println((lineNo++)+": Range: "+count);
		return count;
	}

	// samesame runs in O(D) where D is the unique set of descriptions
	int samesame() {

		int count = 0;

		for (ArrayList<Long> a : samesameMap.keySet()) {
			int value = samesameMap.get(a);
			if (value > 1)
				// consider only the counts which has more than one id in the
				// same set of descriptions
				count = count + value;
		}
		return count;
	}

	// add the description map entries
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
		for (Long description : oldEntry.description) {
			Integer count = descriptionMap.get(description).get(oldEntry.price);
			if (count !=null && count>1)
				descriptionMap.get(description).put(oldEntry.price, --count);
			else
				descriptionMap.get(description).remove(oldEntry.price);
		}
		if (newEntry != null)
			addDescriptionMapEntries(newEntry);
	}
}
