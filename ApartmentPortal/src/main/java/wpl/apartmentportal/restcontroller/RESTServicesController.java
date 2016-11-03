package wpl.apartmentportal.restcontroller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import wpl.apartmentportal.dao.ApartmentDao;
import wpl.apartmentportal.dao.MaintenanceDao;
import wpl.apartmentportal.dao.model.Maintenance;

@Path("/services")
public class RESTServicesController {
	
	@GET
	@Path("/apartmentno/{id}")
	public String getAptNumbers(@PathParam("id") String ipDt){

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		StringBuilder response = new StringBuilder();
		Date date;
		try {
			date = formatter.parse(ipDt);
			System.out.println("Date: "+date);
			//Call Apartment DAO to get list
			ApartmentDao aDao = new ApartmentDao();
			List<Integer> aptNumList = aDao.availableApt(date);
//			Date curDate = formatter.parse("2015-30-04");
			
			if(aptNumList != null && aptNumList.size() != 0){
				response.append("<option value='None'>Choose Apartment</option> \n");
				for(int aptNum : aptNumList){
					response.append("<option value=").append(aptNum).append(">").append(aptNum).append("</option> \n");
				}
			} else{
				response.append("<option>None Available</option>");
			}
			System.out.println("Response:"+response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return response.toString();
	}
	
	@GET
	@Path("/adminalerts")
	public String getAlerts(@Context HttpServletRequest request){
		StringBuilder response = new StringBuilder();
		
		HttpSession session = request.getSession();
		String un = (String)session.getAttribute("USER");
		System.out.println("Admin UserName:"+un);
		//Make DAO calls to get alerts for the admin
		
		List<String> alertList = new ArrayList<String>();
		int i = 0;
		alertList.add("Sample1");
		alertList.add("Sample2");
		if(alertList != null && alertList.size() > 0){
			for(String alert : alertList){
				response.append("<li> \n");
				response.append("<a href='#'> Alert #").append(i).append(" <span class='label label-primary'> ");
				response.append(alert).append("</span></a> \n");
				response.append("</li>");
				i++;
			}
			response.append("<li class='divider'> </li>\n");
			response.append("<li><a href='#'> Close alerts </a></li>");
		} else {
			response.append("<li class='divider'></li> \n");
			response.append(" No alerts");
			
		}
		
		return response.toString();
	}
	
	@GET
	@Path("/getmntreq")
	public String getMaintenanceRequest(@Context HttpServletRequest request){
		StringBuilder response = new StringBuilder();
		System.out.println("Maintenance Controller");
		HttpSession session = request.getSession();
		String un = (String)session.getAttribute("USER");
		MaintenanceDao mDao = new MaintenanceDao();
		//Make DAO calls
		//List<Maintenance> mainList = mDao.getMaintenanceInfo(un);
		List<Maintenance> mainList = new ArrayList<Maintenance>();
		Maintenance m1 = new Maintenance();
		m1.setMain_type("Jimba bumba");
		m1.setReq_date(new Date());
		mainList.add(m1);
		if(mainList != null && mainList.size() > 0){
			response.append("<tr> <th>Date</th> <th>Requests Made</th> </tr>");
			for(Maintenance main : mainList){
				response.append("<tr><td>").append(main.getReq_date())
				.append("</td><td>").append(main.getMain_type()).append("</td></tr>");
			}
		} else {
			response.append("<span style='color:RED'>No Data Available</span>");
		}
		
		return response.toString();
	}
	
	@GET
	@Path("/pymnthist")
	public String getPaymentHistory(@Context HttpServletRequest request){
		StringBuilder response = new StringBuilder();
		System.out.println("Payment History");
		HttpSession session = request.getSession();
		String un = (String)session.getAttribute("USER");
		MaintenanceDao mDao = new MaintenanceDao();
		//Make DAO calls
		//List<Maintenance> mainList = mDao.getMaintenanceInfo(un);
		List<Maintenance> mainList = new ArrayList<Maintenance>();
		Maintenance m = new Maintenance();
		m.setReq_date(new Date());
		m.setMain_type("750$");
		if(mainList != null && mainList.size() > 0){
			response.append("<tr> <th>Month</th> <th>Amount</th> </tr>");
			for(Maintenance main : mainList){
				response.append("<tr><td>").append(main.getReq_date())
				.append("</td><td>").append(main.getMain_type()).append("</td></tr>");
			}
		} else {
			response.append("<span style='color:RED'>No Data Available</span>");
		}
		
		return response.toString();
	}

}
