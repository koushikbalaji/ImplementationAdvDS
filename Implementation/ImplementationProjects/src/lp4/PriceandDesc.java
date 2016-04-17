package lp4;

import java.util.HashSet;

public class PriceandDesc {
	Long id;
	double price;
	HashSet<Long> description;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public HashSet<Long> getDescription() {
		return description;
	}
	public void setDescription(HashSet<Long> description) {
		this.description = description;
	}
	public PriceandDesc(long id, double price, HashSet<Long> description) {
		super();
		this.id = id;
		this.price = price;
		this.description = description;
	}
	
	
}
