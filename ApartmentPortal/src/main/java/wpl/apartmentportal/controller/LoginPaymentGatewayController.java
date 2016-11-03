package wpl.apartmentportal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.apartmentportal.bean.LoginBean;
import wpl.apartmentportal.dao.LoginDao;


/**
 * Servlet implementation class LoginPaymentGatewayController
 */
public class LoginPaymentGatewayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPaymentGatewayController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username,password;
		String error = "";
		boolean valid = false;
		LoginBean loginDetailsBean = new LoginBean();	
		LoginDao loginDetailsDao = new LoginDao();
		
		
		try{	
			username = request.getParameter("username");										
			password = request.getParameter("password");
			loginDetailsBean.setUsername(username);
			loginDetailsBean.setPassword(password);
			valid = loginDetailsDao.validateLogin(loginDetailsBean);
			
			if(valid){
				String aptNo = request.getParameter("aptNo");
				String selectedDate = request.getParameter("selectedDate");
				HttpSession session = request.getSession();
				session.setAttribute("USER", username);
				session.setAttribute("APT", aptNo);
				session.setAttribute("selectedDate", selectedDate);
				response.sendRedirect("PaymentGateway/PaymentGateway.jsp");
			}
			else{
				response.sendRedirect("LoginRegister/InvalidLoginRegister.jsp");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
