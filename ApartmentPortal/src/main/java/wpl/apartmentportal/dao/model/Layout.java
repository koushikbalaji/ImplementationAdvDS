package wpl.apartmentportal.dao.model;

public class Layout  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int apt_type, bhk, sqft, rent;

	public int getApt_type() {
		return apt_type;
	}

	public void setApt_type(int apt_type) {
		this.apt_type = apt_type;
	}

	public int getBhk() {
		return bhk;
	}

	public void setBhk(int bhk) {
		this.bhk = bhk;
	}

	public int getSqft() {
		return sqft;
	}

	public void setSqft(int sqft) {
		this.sqft = sqft;
	}

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}
	
	

}
