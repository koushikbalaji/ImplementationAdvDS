package wpl.apartmentportal.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.apartmentportal.bean.SignupBean;
import wpl.apartmentportal.dao.SignupDao;

/**
 * Servlet implementation class SignUpPaymentGatewayController
 */
public class SignUpPaymentGatewayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpPaymentGatewayController() {
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
		
		String name,password;
		SignupBean userDetailsBean = new SignupBean();	
		SignupDao userDetailsDao = new SignupDao();
		
		try{
			String username = request.getParameter("email");
			password = request.getParameter("password");
			name = request.getParameter("name");
			
			String aptNo = request.getParameter("aptNo1");
			String selectedDate = request.getParameter("selectedDate1");
			HttpSession session = request.getSession();
			session.setAttribute("USER", username);
			session.setAttribute("APT", aptNo);
			session.setAttribute("selectedDate", selectedDate);
			
			userDetailsBean.setName(name);
			userDetailsBean.setEmail(username);
			userDetailsBean.setPassword(password);
			userDetailsDao.insertUserInfo(userDetailsBean);
			
			response.sendRedirect("PaymentGateway/PaymentGateway.jsp");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
