package wpl.apartmentportal.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wpl.apartmentportal.bean.LoginBean;
import wpl.apartmentportal.dao.LoginDao;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    RequestDispatcher rd;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
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
		
//		String username,password;
//		String error = "";
//		boolean valid = false;
//		LoginBean loginDetailsBean = new LoginBean();	
//		LoginDao loginDetailsDao = new LoginDao();
		
		
		System.out.println("Inside controller");
		try{	
//			username = request.getParameter("username");										//needs to be checked with front end
//			password = request.getParameter("password");
//			loginDetailsBean.setUsername(username);
//			loginDetailsBean.setPassword(password);
			//valid = loginDetailsDao.validateLogin(loginDetailsBean);
			
			if(true){
//				HttpSession session = request.getSession();
//				session.setAttribute("USER", username);
//				response.sendRedirect("ResidentPortal/index.jsp");
				rd = request.getRequestDispatcher("src/main/webapp/ResidentPortal/index.jsp");								//needs to be changed
				rd.forward(request,response);
			}
//			else{
////				rd = request.getRequestDispatcher("/src/main/webapp/LoginRegister/InvalidLoginRegister.jsp");									//needs to be changed
////				rd.forward(request,response);
//				response.sendRedirect("LoginRegister/InvalidLoginRegister.jsp");
//			}
			
		}
		catch(Exception e){
			response.sendRedirect("Error.jsp");
		}
	}

}
