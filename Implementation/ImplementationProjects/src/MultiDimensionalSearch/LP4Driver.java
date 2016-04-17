package MultiDimensionalSearch;

import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

/**
 * Sample driver code for Project LP4. Modify as needed. Do not change
 * input/output formats.
 * 
 * @author rbk
 */
public class LP4Driver {
	static long[] description;
	static final int DLENGTH = 100000;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in;
		if (args.length < 0) {
			in = new Scanner(new File(args[0]));
		} else {
			in = new Scanner(System.in);
		}
		String s;
		long rv = 0;
		long centValues = 0;
		description = new long[DLENGTH];
		Timer timer = new Timer();
		MDS5 mds = new MDS5();
		// DecimalFormat df = new DecimalFormat("#.##");
		while (in.hasNext()) {
			s = in.next();
			if (s.charAt(0) == '#') {
				s = in.nextLine();
				continue;
			}
			if (s.equals("Insert")) {
				long id = in.nextLong();
				double price = in.nextDouble();
				long des = in.nextLong();
				int index = 0;
				while (des != 0) {
					description[index++] = des;
					des = in.nextInt();
				}
				description[index] = 0;
				rv += mds.insert(id, price, description, index);
			} else if (s.equals("Find")) {
				long id = in.nextLong();
				centValues += (long) mds.find(id);
			} else if (s.equals("Delete")) {
				long id = in.nextLong();
				rv += (long) mds.delete(id);
			} else if (s.equals("FindMinPrice")) {
				long des = in.nextLong();
				centValues += (long) mds.findMinPrice(des);
			} else if (s.equals("FindMaxPrice")) {
				long des = in.nextLong();
				centValues += (long) mds.findMaxPrice(des);
			} else if (s.equals("FindPriceRange")) {
				long des = in.nextLong();
				double lowPrice = in.nextDouble();
				double highPrice = in.nextDouble();
				rv += (long) mds.findPriceRange(des, lowPrice, highPrice);
			} else if (s.equals("PriceHike")) {
				long minid = in.nextLong();
				long maxid = in.nextLong();
				double rate = in.nextDouble();
				centValues += (long) mds.priceHike(minid, maxid, rate);
			} else if (s.equals("Range")) {
				double lowPrice = in.nextDouble();
				double highPrice = in.nextDouble();
				rv += (long) mds.range(lowPrice, highPrice);
			} else if (s.equals("SameSame")) {
				rv += (long) mds.samesame();
			} else if (s.equals("End")) {
				break;
			} else {
				System.out.println("Houston, we have a problem.\nUnexpected line in input: " + s);
				System.exit(0);
			}
		}
		double finalCent = centValues / 100.00;
		System.out.printf("%f", (double) rv + finalCent);
		System.out.println();
		System.out.println(timer.end());
		in.close();
	}
}