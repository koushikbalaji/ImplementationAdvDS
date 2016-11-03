package wpl.apartmentportal.dao.model;

public class MaintenanceType implements java.io.Serializable{
	

	private static final long serialVersionUID = 1L;
	
 private int main_type, charges;
 private String description;
public int getMain_type() {
	return main_type;
}
public void setMain_type(int main_type) {
	this.main_type = main_type;
}
public int getCharges() {
	return charges;
}
public void setCharges(int charges) {
	this.charges = charges;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
 

}
