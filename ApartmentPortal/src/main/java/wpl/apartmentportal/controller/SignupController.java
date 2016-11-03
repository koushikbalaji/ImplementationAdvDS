package wpl.apartmentportal.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wpl.apartmentportal.bean.SignupBean;
import wpl.apartmentportal.dao.SignupDao;

/**
 * Servlet implementation class SignupController
 */
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupController() {
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
		
		String name,email,password;
		SignupBean userDetailsBean = new SignupBean();	
		SignupDao userDetailsDao = new SignupDao();
		
		try{
			name = request.getParameter("name");
			email = request.getParameter("email");
			password = request.getParameter("password");
			
			userDetailsBean.setName(name);
			userDetailsBean.setEmail(email);
			userDetailsBean.setPassword(password);
			
			userDetailsDao.insertUserInfo(userDetailsBean);
			
			response.sendRedirect("ResidentPortal/index.jsp");
													
		}
		catch(Exception e){
			response.sendRedirect("Error.jsp");
		}
		
	}

}
