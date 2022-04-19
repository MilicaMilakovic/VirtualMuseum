package net.etfbl.ip.vm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.etfbl.ip.vm.beans.TransactionBean;
import net.etfbl.ip.vm.beans.UserBean;
import net.etfbl.ip.vm.dao.CreditCardDAO;
import net.etfbl.ip.vm.dto.Transaction;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		String address = "/WEB-INF/pages/login.jsp";
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
//		System.out.println(action);
		if (action == null || action.equals("")) {
			address = "/WEB-INF/pages/login.jsp";
		} else if (action.equals("logout")) {
			session.invalidate();
			address = "/WEB-INF/pages/login.jsp";
		}
		else if(action.equals("login")) {
			
			String cardholder = request.getParameter("cardholder");
			String cardnumber = request.getParameter("cardNumber");
			String password = request.getParameter("password");
			
			UserBean userBean = new UserBean();
			if(userBean.loginUser(cardholder, cardnumber, password)) {
				session.setAttribute("userBean", userBean);
				TransactionBean transactionBean = new TransactionBean();
				
				session.setAttribute("transactionBean", transactionBean);
				
				address = "/WEB-INF/pages/profile.jsp";
			} else 
				System.out.println("Neuspjesna prijava");
		} else if(action.equals("toggle")) {
			UserBean userBean =(UserBean) session.getAttribute("userBean");
			if(userBean == null)
				address = "/WEB-INF/pages/login.jsp";
			else {				
				
				if(CreditCardDAO.updateCardStatus(userBean.getCardId())) {
					userBean.getCard().setActive(!userBean.getCard().isActive());
					session.setAttribute("userBean", userBean);
					address = "/WEB-INF/pages/profile.jsp";
				}
				else 
					address = "/WEB-INF/pages/login.jsp";
			}
		}
		else {
			address = "/WEB-INF/pages/404.jsp";
//			UserBean userBean = (UserBean) session.getAttribute("userBean");
//			if (userBean == null || !userBean.isLoggedIn()) {
//				address = "/WEB-INF/pages/login.jsp";
//			} 
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
