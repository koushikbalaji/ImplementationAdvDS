package wpl.apartmentportal.dao.model;
import java.util.Date;
public class BookingInfo  implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private int booking_id,	apt_no,	cust_id;
	private Date booking_date, valid_till;
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
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
	public Date getBooking_date() {
		return booking_date;
	}
	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}
	public Date getValid_till() {
		return valid_till;
	}
	public void setValid_till(Date valid_till) {
		this.valid_till = valid_till;
	}
	
	
	
}
