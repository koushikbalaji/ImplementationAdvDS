package wpl.apartmentportal.dao.model;

public class ContactType implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int contact_type;
	private String type_desc;
	public int getContact_type() {
		return contact_type;
	}
	public void setContact_type(int contact_type) {
		this.contact_type = contact_type;
	}
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}

}
