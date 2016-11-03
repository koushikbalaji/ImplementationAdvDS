package wpl.apartmentportal.dao.model;

public class AptCust  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int apt_no,	cust_id;

	public int getApt_no() {
		return apt_no;
	}

	public void setApt_no(int apt_no) {
		this.apt_no = apt_no;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}



}
