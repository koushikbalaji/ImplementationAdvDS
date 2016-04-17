package BigArithmetic;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BigArithmetic {

	public final int B = 10;
	public LinkedList<Long> num;

	public BigArithmetic(String s) {
		num = new LinkedList<Long>();
		if (s.charAt(0) == 'N') {
			num.add((long) 0);
			return;
		}
		for (int i = s.length() - 1; i >= 0; i--)
			num.add(Long.parseLong("" + s.charAt(i)));
	}

	public BigArithmetic(Long num) {
		this.num = new LinkedList<Long>();
		while (num > 0) {
			long lastDigit = num % B;
			this.num.add(lastDigit);
			num /= B;
		}
	}

	public String toString() {
		if (num.get(num.size() - 1) == 0 && num.size() == 1)
			return "0";
		String s = "";
		boolean leadZeroInd = (num.get(num.size() - 1) == 0) ? true : false;
		for (int i = num.size() - 1; i >= 0; i--) {
			if (leadZeroInd && num.get(i) == 0)
				;
			else {
				leadZeroInd = false;
				s += num.get(i).toString();
			}
		}
		return s;
	}

	public BigArithmetic add(BigArithmetic a, BigArithmetic b) {
		Iterator<Long> it1 = a.num.iterator();
		Iterator<Long> it2 = b.num.iterator();
		long carry = 0;
		Long x1 = it1.next();
		Long x2 = it2.next();
		List<Long> c = new LinkedList<Long>();
		while (x1 != null && x2 != null) {
			Long sum = x1 + x2 + carry;
			c.add(sum % B);
			carry = sum / B;
			try {
				x1 = it1.next();
			} catch (NoSuchElementException e) {
				x1 = null;
			}
			try {
				x2 = it2.next();
			} catch (NoSuchElementException e) {
				x2 = null;
			}
		}
		while (x1 != null) {
			Long sum = x1 + carry;
			c.add(sum % B);
			carry = sum / B;
			try {
				x1 = it1.next();
			} catch (NoSuchElementException e) {
				x1 = null;
			}
		}
		while (x2 != null) {
			Long sum = x2 + carry;
			c.add(sum % B);
			carry = sum / B;
			try {
				x2 = it2.next();
			} catch (NoSuchElementException e) {
				x2 = null;
			}
		}
		if (carry > 0)
			c.add(carry);
		String outString = "";
		for (int i = c.size() - 1; i >= 0; i--)
			outString += c.get(i);
		return new BigArithmetic(outString);
	}

	public BigArithmetic subtract(BigArithmetic a, BigArithmetic b) {
		Iterator<Long> it1 = a.num.iterator();
		Iterator<Long> it2 = b.num.iterator();
		boolean carryBit = false;
		Long x1 = it1.next();
		Long x2 = it2.next();
		List<Long> c = new LinkedList<Long>();
		while (x1 != null && x2 != null) {
			if (carryBit) {
				if (x1 == 0)
					x1 = (long) 9;
				else {
					x1 -= 1;
					carryBit = false;
				}
			}
			if (x1 < x2) {
				x1 += B;
				carryBit = true;
			}
			long diff = x1 - x2;
			c.add(diff % B);
			try {
				x1 = it1.next();
			} catch (NoSuchElementException e) {
				x1 = null;
			}
			try {
				x2 = it2.next();
			} catch (NoSuchElementException e) {
				x2 = null;
			}
		}
		if (x1 == null && carryBit || x2 != null)
			return new BigArithmetic("N");
		while (x1 != null) {
			if (carryBit) {
				if (x1 == 0)
					x1 = (long) 9;
				else {
					x1 -= 1;
					carryBit = false;
				}
			}
			c.add(x1);
			try {
				x1 = it1.next();
			} catch (NoSuchElementException e) {
				x1 = null;
			}
		}
		String outString = "";
		for (int i = c.size() - 1; i >= 0; i--)
			outString += c.get(i);
		return new BigArithmetic(outString);
	}

//	public BigArithmetic powerByProductDac(BigArithmetic a, long n) {
//		if (n == 0)
//			return new BigArithmetic("1");
//		else if (n == 1)
//			return a;
//		else {
//			BigArithmetic half = powerByProductDac(a, n / 2);
//			BigArithmetic res = half.productByDAC(half, half);
//			if (n % 2 == 0)
//				return res;
//			else
//				return res.productByDAC(res, a);
//		}
//	}

	public BigArithmetic power(BigArithmetic a, long n) {
		if (n == 0)
			return new BigArithmetic("1");
		else if (n == 1)
			return a;
		else {
			BigArithmetic half = power(a, n / 2);
			BigArithmetic res = half.productByLongMultipy(half, half);
			if (n % 2 == 0)
				return res;
			else
				return res.productByLongMultipy(res, a);
		}
	}

	public BigArithmetic productByLongMultipy(BigArithmetic a, BigArithmetic b) {
		Iterator<Long> it1 = a.num.iterator();
		Iterator<Long> it2 = b.num.iterator();
		Long x1 = it1.next();
		Long x2 = it2.next();

		int leastZeros = 0;
		boolean zeroInd = true;
		BigArithmetic temp = new BigArithmetic("0");
		while (x1 != null) {
			StringBuilder tempString = new StringBuilder();
			for (int i = 1; i <= leastZeros; i++)
				tempString.append("0");
			long carry = 0;
			while (x2 != null) {
				long multiplyResult = x1 * x2;
				if (multiplyResult != 0)
					zeroInd = false;
				multiplyResult += carry;
				tempString.append(multiplyResult % B);
				carry = multiplyResult / B;
				try {
					x2 = it2.next();
				} catch (NoSuchElementException e) {
					x2 = null;
				}
			}
			if (carry != 0)
				tempString.append(carry);
			temp = temp.add(temp, new BigArithmetic(tempString.reverse().toString()));
			leastZeros++;
			it2 = b.num.iterator();
			x2 = it2.next();
			try {
				x1 = it1.next();
			} catch (NoSuchElementException e) {
				x1 = null;
			}
		}
		if (zeroInd)
			return new BigArithmetic("N");
		return temp;
	}

	/*public BigArithmetic productByDAC(BigArithmetic a, BigArithmetic b) {
		if (a.num.size() == 1 || b.num.size() == 1)
			return productByLongMultipy(a, b);
		else {
			int n1, n2;
			Iterator<Long> it1, it2;
			if (a.num.size() >= b.num.size()) {
				n1 = a.num.size();
				n2 = b.num.size();
				it1 = a.num.iterator();
				it2 = b.num.iterator();
			} else {
				n1 = b.num.size();
				n2 = a.num.size();
				it1 = b.num.iterator();
				it2 = a.num.iterator();
			}
			int k = n2 / 2;
			StringBuilder am = new StringBuilder();
			StringBuilder al = new StringBuilder();
			StringBuilder bm = new StringBuilder();
			StringBuilder bl = new StringBuilder();
			for (int i = 1; i <= k; i++) {
				al.append(it1.next());
				bl.append(it2.next());
			}
			while (it1.hasNext())
				am.append(it1.next());
			while (it2.hasNext())
				bm.append(it2.next());
			BigArithmetic amBA = new BigArithmetic(am.reverse().toString());
			BigArithmetic bmBA = new BigArithmetic(bm.reverse().toString());
			BigArithmetic alBA = new BigArithmetic(al.reverse().toString());
			BigArithmetic blBA = new BigArithmetic(bl.reverse().toString());
			BigArithmetic amBybm = productByDAC(amBA, bmBA);
			BigArithmetic amPlusalBybmPlusbl = productByDAC(add(amBA, alBA), add(bmBA, blBA));
			BigArithmetic alBybl = productByDAC(alBA, blBA);
			BigArithmetic operand1 = productByLongMultipy(amBybm, new BigArithmetic((long) Math.pow(B, 2 * k)));
			BigArithmetic operand2 = productByLongMultipy(subtract(subtract(amPlusalBybmPlusbl, amBybm), alBybl),
					new BigArithmetic((long) Math.pow(B, k))); //range overflow issue need to rectify

			BigArithmetic operand3 = alBybl;
			return add(add(operand1, operand2), operand3);
		}
	}
*/
	
	public void printList(){
		Iterator<Long> it1 = num.iterator();
		System.out.print(B+" : ");
		while(it1.hasNext())
		System.out.print(it1.next()+" ");
		}
	
	public static void main(String[] args){
		BigArithmetic bA1 = new BigArithmetic("8735634343434343434343433596497");
		BigArithmetic bA2 = new BigArithmetic("32310353453534656456");
		
		BigArithmetic bA3 = bA2.productByLongMultipy(bA1, bA2);
		BigArithmetic bA4 = bA2.add(bA1, bA2);
		BigArithmetic bA5 = bA2.subtract(bA1, bA2);
		BigArithmetic bA6 = bA2.power(bA1, 30);
		System.out.println(bA3);
		System.out.println(bA4);
		System.out.println(bA5);
		System.out.println(bA6);
		bA3.printList();
	}
}