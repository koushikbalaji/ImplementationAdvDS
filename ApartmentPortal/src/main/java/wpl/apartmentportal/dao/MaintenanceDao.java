package wpl.apartmentportal.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import wpl.apartmentportal.bean.MaintenanceBean;
import wpl.apartmentportal.dao.model.Customer;
import wpl.apartmentportal.dao.model.Maintenance;
import wpl.apartmentportal.dao.util.HibernateUtil;

public class MaintenanceDao {
	
	public String insertMaintenanceInfo(MaintenanceBean mb){
		String username=mb.getUserName();
		String main_desc=mb.getMaintenanceDesc();
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Maintenance main = new Maintenance(); 
		Query query1 = session1.createQuery("select custID from Customer where username= :un");
        query1.setString("un",username);
        int custid = (Integer)query1.uniqueResult();
        Query query2 = session1.createQuery("select apt_no from AptCust where cust_id= :cid");
        query2.setLong("cid",custid);
        int aptno = (Integer)query2.uniqueResult();
        
        
        main.setApt_no(aptno);
		main.setMain_type(main_desc);
		main.setReq_date(new Date());
		main.setStatus("open");
		session1.save(main);
		session1.getTransaction().commit();
		
		return "Success";
	}
	
	public String getMaintenanceInfo(String UserName)
	{
		String username=UserName;
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query query1 = session1.createQuery("select custID from Customer where username= :un");
        query1.setString("un",username);
        int cust_id = (Integer)query1.uniqueResult();
        Query query2 = session1.createQuery("select apt_no from AptCust where cust_id= :cid");
        query2.setLong("cid",cust_id);
        int apt_no = (Integer)query2.uniqueResult();
        Query query3 = session1.createQuery("from Maintenance where apt_no=:ano");
        query3.setLong("ano",apt_no);
        List<Maintenance> list = query3.list();
        for(Maintenance arr : list){
            System.out.println(arr.getMaintenance_id()+" "+arr.getMain_type()+" "+arr.getReq_date());
        }
		return "Success";
	}
	
	public String getMaintenanceHistory()
	{
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		Query query1 = session1.createQuery("select apt_no,req_date,status from Maintenance");
		List<Object[]> list = query1.list();
        for(Object[] arr : list){
            System.out.println(Arrays.toString(arr));
        }
		return "Success";
		
	}

}
