package wpl.apartmentportal.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.apartmentportal.bean.MaintenanceBean;
import wpl.apartmentportal.dao.MaintenanceDao;

/**
 * Servlet implementation class MaintenanceController
 */
public class MaintenanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MaintenanceController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MaintenanceDao mdao = new MaintenanceDao();
		//List<Object> maintenance = new ArrayList<Object>();
		String maintenance;
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("USER");
		maintenance = mdao.getMaintenanceInfo(username);
		response.sendRedirect("ResidentPortal/Maintenance.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		MaintenanceBean mbean = new MaintenanceBean();
		MaintenanceDao mdao = new MaintenanceDao();
		mbean.setMaintenanceType(request.getParameter("maintenance"));
		mbean.setMaintenanceDesc(request.getParameter("comment"));
		HttpSession session = request.getSession();
		mbean.setUserName(session.getAttribute("USER").toString());
		
		mdao.insertMaintenanceInfo(mbean);
		response.sendRedirect("ResidentPortal/Maintenance.jsp");
	}

}
