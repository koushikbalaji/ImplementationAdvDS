package lp4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class MDS2 {

	public HashMap<Long, PriceandDesc> IDtree = new HashMap<>();
	public HashMap<TreeSet<Long>, Integer> samesameMap = new HashMap<>();
	public HashMap<Long, TreeMap<Double, Integer>> descriptionMap = new HashMap<>();

	int insert(long id, double price, long[] description, int size) {
		// Description of item is in description[0..size-1].
		// Copy them into your data structure
		HashSet<Long> temp = new HashSet<>();
		for (int i = 0; i < size; i++)
			temp.add(description[i]);
		PriceandDesc priceandDesc = new PriceandDesc(id, price, temp);
		PriceandDesc temp2 = IDtree.put(id, priceandDesc);
		if (temp2 == null) {
			addDescriptionMapEntries(priceandDesc);
			if (size >= 8) {
				// to add replace during insert in samesame
				TreeSet<Long> tempTree = new TreeSet<>();
				for (int i = 0; i < size; i++) {
					tempTree.add(description[i]);
				}
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
		} else {
			updateDescriptionMapEntries(temp2, priceandDesc);
			if (temp2.description.size() >= 8) {
				TreeSet<Long> tempTree = new TreeSet<>();
				for (long a : temp2.description)
					tempTree.add(a);
				int count = 0;
				try {
					count = samesameMap.get(tempTree);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(id);
					e.printStackTrace();
					System.out.println(tempTree);
				}
				TreeSet<Long> tempTree2 = new TreeSet<>();
				for (int i = 0; i < size; i++)
					tempTree2.add(description[i]);
				count = count - 1;
				samesameMap.put(tempTree2, count);
			}
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
			return 0;
		}
		// should accomodate samesame for deletion
	}

	double find(long id) {

		try {
			Double price = IDtree.get(id).price;
			// System.out.println("Find : "+price);
			return price;
		} catch (Exception e) {
			// System.out.println("Find : "+0.0);
			return 0.0;
		}
	}

	long delete(long id) {

		PriceandDesc temp = IDtree.remove(id);
		if (temp == null)
			return 0;
		else {
			updateDescriptionMapEntries(temp, null);
			long sum = 0;

			for (long s : temp.description)
				sum += s;

			if (temp.description.size() >= 8) {
				TreeSet<Long> tempTree = new TreeSet<>();
				for (long a : temp.description)
					tempTree.add(a);
				// updated
				Integer count = samesameMap.get(tempTree);
				// if (count != null){
				if (count == 1)
					samesameMap.remove(tempTree);
				else
					samesameMap.put(tempTree, count - 1);
				// }
			}
			// System.out.println("Delete : "+sum);
			return sum;
		}
	}

	double findMinPrice(Long des) {
		// updated
		Double minPrice = descriptionMap.get(des).ceilingKey(0.0);
		// System.out.println("FindMinPrice : "+minPrice);
		if (minPrice == null)
			return 0;
		return minPrice;
	}

	double findMaxPrice(long des) {
		// update
		Double maxPrice = descriptionMap.get(des).floorKey(Double.MAX_VALUE);
		// System.out.println("FindMaxPrice : "+maxPrice);
		if (maxPrice == null)
			return 0;
		return maxPrice;
	}

	int findPriceRange(long des, double lowPrice, double highPrice) {

		int count = 0;
		NavigableMap<Double, Integer> priceRangeMap = descriptionMap.get(des).subMap(lowPrice, true, highPrice,
				true);
		for(Entry<Double,Integer> entry: priceRangeMap.entrySet()){
			count += entry.getValue();
		}
		// System.out.println("FindPriceRange : "+count);
		return count;
	}

	double priceHike(long minid, long maxid, double rate) {

		double sum = 0.0;
		for (Long a : IDtree.keySet()) {
			if (a >= minid && a <= maxid) {
				PriceandDesc temp = IDtree.get(a);
				double price = temp.price;
				double newPrice = (temp.price + ((temp.price / 100) * rate));
				updateDescriptionMapEntries(temp, new PriceandDesc(temp.id, newPrice, temp.description));
				temp.setPrice(newPrice);
				sum = sum + newPrice - price;
			}
		}
		// System.out.println("PriceHike : "+sum);

		return sum;
	}

	int range(double lowPrice, double highPrice) {
		int count = 0;
		for (Long a : IDtree.keySet()) {
			PriceandDesc temp = IDtree.get(a);
			if (temp.price >= lowPrice && temp.price <= highPrice)
				count++;
		}
		// System.out.println("Range : "+count);
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
		// System.out.println("SameSame : "+count);
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