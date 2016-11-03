package wpl.apartmentportal.dao.model;


 
/**
 * Model class for Stock
 */
public class Customer implements java.io.Serializable {
 
	private static final long serialVersionUID = 1L;
    private int custID;
	private String password;
	private String customerName;
	private String userName;
	private String idProof;
	private String phone;
	private int customerType;
	
	public Customer() {
	}
 
	public Customer(String customerName, String userName, String password, String idProof, String phone, int customerType ) {
		this.customerName = customerName;
		this.userName = userName;
		this.password = password;
		this.idProof = idProof;
		this.phone = phone;
		this.customerType = customerType;
		
	}

	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdProof() {
		return idProof;
	}

	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone2) {
		this.phone = phone2;
	}

	public int getCustomerType() {
		return customerType;
	}

	public void setCustomerType(int customerType) {
		this.customerType = customerType;
	}
	
	
}