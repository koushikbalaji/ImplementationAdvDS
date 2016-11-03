package wpl.apartmentportal.dao.model;

public class AptInfo  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int apt_no,	apt_type;
	private String booked_status;
	
	public int getApt_no() {
		return apt_no;
	}
	public void setApt_no(int apt_no) {
		this.apt_no = apt_no;
	}
	public int getApt_type() {
		return apt_type;
	}
	public void setApt_type(int apt_type) {
		this.apt_type = apt_type;
	}
	public String getBooked_status() {
		return booked_status;
	}
	public void setBooked_status(String booked_status) {
		this.booked_status = booked_status;
	}

}
