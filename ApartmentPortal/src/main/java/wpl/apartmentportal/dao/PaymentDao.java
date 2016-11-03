package wpl.apartmentportal.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import wpl.apartmentportal.dao.model.Rent;
import wpl.apartmentportal.dao.util.HibernateUtil;

public class PaymentDao {

	public int paymentDue(String username)
	{
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query query = session1.createQuery("select custID from Customer where userName= :un");
        query.setString("un", username);
        int custid = (Integer)query.uniqueResult();
        
        Query query2 = session1.createQuery("select apt_no from AptCust where cust_id= :cid");
        query2.setLong("cid", custid);
        int aptno = (Integer)query2.uniqueResult();
        
        Query query3=session1.createQuery("select due from Rent where apt_no=:ano and paid_date is null");
        query3.setLong("ano", aptno);
        if((Integer)query3.uniqueResult()==null)
        	return 0;
       
        int due = (Integer)query3.uniqueResult();
        
		return due;
	}
	
	public String paymentHistory(String username)
	{
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query query = session1.createQuery("select custID from Customer where userName= :un");
        query.setString("un", username);
        int custid = (Integer)query.uniqueResult();
        
        Query query2 = session1.createQuery("select apt_no from AptCust where cust_id= :cid");
        query2.setLong("cid", custid);
        int aptno = (Integer)query2.uniqueResult();
        
        Query query3=session1.createQuery("from Rent where apt_no=:ano and paid_date is not null");
        query3.setLong("ano", aptno);
        List<Rent> list = query3.list();
        for(Rent arr : list)
        {
            System.out.println(arr.getApt_no()+" "+arr.getDue());
		
        }
        return "Success!!";
	}
	
}	
