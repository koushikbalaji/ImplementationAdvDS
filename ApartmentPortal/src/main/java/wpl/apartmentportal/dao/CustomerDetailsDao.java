package wpl.apartmentportal.dao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import wpl.apartmentportal.dao.model.Customer;
import wpl.apartmentportal.dao.util.HibernateUtil;

class CustomerDetailsDao {

	public String getCustomerDetails() 
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session1 = sessionFactory.getCurrentSession();
		Transaction tx = session1.beginTransaction();
		Query query = session1.createQuery("select cust_id from AptCust");
		List<Integer> list = query.list();
		Iterator<Integer> it1 = list.iterator();
		while(it1.hasNext()){
			System.out.println(it1.next());
		}
		Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
        	Query query1=session1.createQuery("select customerName,userName,phone,idProof from Customer where customerType=1 and custID= :cid");
        	int custid = it.next();
            query1.setInteger("cid", custid);
            Object[] obj = (Object[])query1.uniqueResult();
            for(int i=0;i<obj.length;i++)
            System.out.print(obj[i]+" ");
            }
     return "Successful";   
	}
}
