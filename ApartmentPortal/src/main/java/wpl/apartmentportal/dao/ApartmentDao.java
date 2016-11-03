package wpl.apartmentportal.dao;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import wpl.apartmentportal.bean.BookAptBean;
import wpl.apartmentportal.dao.model.AptCust;
import wpl.apartmentportal.dao.model.BookingInfo;
import wpl.apartmentportal.dao.model.Customer;
import wpl.apartmentportal.dao.model.Layout;
import wpl.apartmentportal.dao.model.LeaseInfo;
import wpl.apartmentportal.dao.model.Rent;
import wpl.apartmentportal.dao.util.HibernateUtil;

public class ApartmentDao {
	
	public String booking(BookAptBean bab) throws Exception
	{
		String username=bab.getEmail();
		int aptno=bab.getAptNumber();
		Date moveindate=bab.getMoveInDate();
		
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query query = session1.createQuery("select custID from Customer where userName= :un");
        query.setString("un", username);
        int custid = (Integer)query.uniqueResult();
        
        AptCust acust = new AptCust();
        acust.setApt_no(aptno);
        acust.setCust_id(custid);
        session1.save(acust);
        
        BookingInfo book = new BookingInfo();
        book.setApt_no(aptno);
        book.setCust_id(custid);
        Date dat = new Date();
        book.setBooking_date(dat);
        Calendar c = new GregorianCalendar();
        c.set(dat.getYear(), dat.getMonth(), dat.getDate());
        c.add(Calendar.MONTH, 1);
        Date d = c.getTime();
        int temp=dat.getMonth();
        if(temp>11)
        d.setYear(dat.getYear()+1);
        else
        d.setYear(dat.getYear());	
        book.setValid_till(d);
        session1.save(book);
        
        Query query4 = session1.createQuery("select booking_id from BookingInfo where cust_id= :cid");
        query4.setLong("cid", custid);
        int bookingid = (Integer)query4.uniqueResult();
        
        LeaseInfo lease = new LeaseInfo();
        lease.setBooking_id(bookingid);
        lease.setFrom_date(new Date());
        Calendar c1 = new GregorianCalendar();
        c1.set(moveindate.getYear(), moveindate.getMonth(), moveindate.getDate());
        c1.add(Calendar.MONTH,6);
        Date d1 = c1.getTime();
        int temp1=moveindate.getMonth();
        if(temp1>5)
        d1.setYear(moveindate.getYear()+1);
        else
        d1.setYear(moveindate.getYear());	
        lease.setTo_date(d1);
        lease.setApt_no(aptno);
        session1.save(lease);
        
        
        Query query1 = session1.createQuery("select apt_type from AptInfo where apt_no= :an");
        query1.setLong("an",aptno);
        int aptType = (Integer)query1.uniqueResult();
        
        Query query2 = session1.createQuery("select rent from Layout where apt_type= :atype");
        query2.setLong("atype",aptType);
        int due= (Integer)query2.uniqueResult();
        
        Rent rent= new Rent();
        rent.setApt_no(aptno);
        rent.setDue(due);
        Date dat1 = new Date();
        Date dat2 = new Date();
        dat2.setMonth(dat1.getMonth()+1);
        rent.setDuedate(dat2);
        rent.setPaiddate(dat1);
        rent.setPaymentType("online");
        session1.save(rent);
        Query query3 = session1.createQuery("update AptInfo set booked_status = 'booked' where apt_no= :apt");
        query3.setLong("apt", aptno);
        int result2 = query3.executeUpdate();
        
        session1.getTransaction().commit();
        
        
	    return "Sucess!!";
	}

	public List<Integer> availableApt(Date selDate)
	{
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query query = session1.createQuery("select apt_no from LeaseInfo where :cdate >= to_date");
		query.setDate("cdate",selDate);
		List<Integer> list = query.list();
		tx.commit();
        /*for(Object arr : list){
            System.out.println(arr);
        }*/
		
		return list;
	}
}
