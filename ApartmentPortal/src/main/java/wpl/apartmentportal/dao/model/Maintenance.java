package wpl.apartmentportal.dao.model;

import java.util.Date;

public class Maintenance implements java.io.Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int maintenance_id;
	private int apt_no;
	private String main_type;
	
	private String status;
	private Date req_date;
	public int getMaintenance_id() {
		return maintenance_id;
	}
	public void setMaintenance_id(int maintenance_id) {
		this.maintenance_id = maintenance_id;
	}
	public String getMain_type() {
		return main_type;
	}
	public void setMain_type(String main_type) {
		this.main_type = main_type;
	}
	public int getApt_no() {
		return apt_no;
	}
	public void setApt_no(int apt_no) {
		this.apt_no = apt_no;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getReq_date() {
		return req_date;
	}
	public void setReq_date(Date req_date) {
		this.req_date = req_date;
	}
	
	
}
