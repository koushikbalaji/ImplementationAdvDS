package wpl.apartmentportal.dao.model;

public class CustLease  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int cust_id, lease_no;

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public int getLease_no() {
		return lease_no;
	}

	public void setLease_no(int lease_no) {
		this.lease_no = lease_no;
	}
	

}
