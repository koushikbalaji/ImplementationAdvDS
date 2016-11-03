package wpl.apartmentportal.dao;

import org.hibernate.Session;

import wpl.apartmentportal.bean.SignupBean;
import wpl.apartmentportal.dao.model.Customer;
import wpl.apartmentportal.dao.util.HibernateUtil;

public class SignupDao {
		
	public String insertUserInfo(SignupBean userDetails){
		
		String name=userDetails.getName();
		String username=userDetails.getEmail();
		String password=userDetails.getPassword();
		String phone= "4693854258";
		int customertype=1;
		String idproof="3524845";
		
		   Session session1 = HibernateUtil.getSessionFactory().openSession();
	        session1.beginTransaction();
	        Customer cust1 = new Customer();
	        cust1.setCustomerName(name);
	        cust1.setUserName(username);
	        cust1.setIdProof(idproof);
	        cust1.setPassword(password);
	        cust1.setPhone(phone);
	        cust1.setCustomerType(customertype);
	       
	         session1.save(cust1);
	        session1.getTransaction().commit();
		
		return "Success!!";
	}
}
