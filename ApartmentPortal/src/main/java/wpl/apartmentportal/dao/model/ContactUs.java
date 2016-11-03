package wpl.apartmentportal.dao.model;

public class ContactUs implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name,email,message;
	private int id, contact_type;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getContact_type() {
		return contact_type;
	}
	public void setContact_type(int contact_type) {
		this.contact_type = contact_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
