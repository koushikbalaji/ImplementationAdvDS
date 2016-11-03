package wpl.apartmentportal.dao.model;
import java.util.Date;
public class LeaseInfo  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private int lease_no, booking_id, apt_no;
	
	public int getApt_no() {
		return apt_no;
	}
	public void setApt_no(int apt_no) {
		this.apt_no = apt_no;
	}
	private Date from_date, to_date;
	
	public int getLease_no() {
		return lease_no;
	}
	public void setLease_no(int lease_no) {
		this.lease_no = lease_no;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

}
