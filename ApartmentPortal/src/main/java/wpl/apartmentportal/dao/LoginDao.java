package wpl.apartmentportal.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import wpl.apartmentportal.bean.LoginBean;
import wpl.apartmentportal.dao.model.Customer;
import wpl.apartmentportal.dao.util.HibernateUtil;

public class LoginDao {
	
	public boolean validateLogin(LoginBean userDetailsBean) throws Exception{
		
		String username=userDetailsBean.getUsername();
		String password=userDetailsBean.getPassword();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
		Query query = session.createQuery(" from Customer where userName= :un and password= :pwd");
        query.setString("un", username);
        query.setString("pwd", password);
        
        Customer cust = (Customer)query.uniqueResult();
        if(cust != null && cust != null){
        	System.out.println("Customer ID exists!: "+cust.getCustomerName());
        	return true;
        } else {
        	System.out.println("No Match Found !!!");
        }
		return false;
	}
	
	public String getName(String username)
	{
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
		Query query1 = session.createQuery("select name from Customer where userName= :un");
        query1.setString("un", username);
        String cname=(String)query1.uniqueResult();
		return cname;
	}
}
