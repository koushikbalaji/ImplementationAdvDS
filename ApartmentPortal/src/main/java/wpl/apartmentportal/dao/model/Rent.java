package wpl.apartmentportal.dao.model;

import java.util.Date;

public class Rent implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int apt_no,	due, fine, sno;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	private String paymentType;
	private Date duedate,paiddate;
	public int getApt_no() {
		return apt_no;
	}
	public void setApt_no(int apt_no) {
		this.apt_no = apt_no;
	}
	public int getDue() {
		return due;
	}
	public void setDue(int due) {
		this.due = due;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Date getPaiddate() {
		return paiddate;
	}
	public void setPaiddate(Date paiddate) {
		this.paiddate = paiddate;
	}
	
	
	

}
