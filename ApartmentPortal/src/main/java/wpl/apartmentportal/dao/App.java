package wpl.apartmentportal.dao;

import java.util.Date;

import wpl.apartmentportal.bean.BookAptBean;
 
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Hibernate + MySQL1");
       // Session session1 = HibernateUtil.getSessionFactory().openSession();
        try {
        
        PaymentDao mad = new PaymentDao();
        mad.paymentHistory("stark@gmail.com");
       // System.out.println("Due: "+due);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
 /*       session1.beginTransaction();
        Customer cust1 = new Customer();
 
        cust1.setCustomerName("Raina");
        cust1.setUserName("raina@gmail.com");
        cust1.setIdProof("12345678");
        cust1.setPassword("bravo1234");
        cust1.setPhone(987654321);
        cust1.setCustomerType(1);
       
         session1.save(cust1);
        session1.getTransaction().commit();
        
        System.out.println("Hibernate + MySQL2");
        Session session2 = HibernateUtil.getSessionFactory_instance2().openSession();
        
        session2.beginTransaction();
        Customer cust2 = new Customer();
 
        cust2.setCustomerName("Raina");
        cust2.setUserName("raina@gmail.com");
        cust2.setIdProof("12345678");
        cust2.setPassword("bravo1234");
        cust2.setPhone(987654321);
        cust2.setCustomerType(1);
        
        session2.save(cust2);
        session2.getTransaction().commit();
 */
    }
}