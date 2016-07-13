package com.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.bean.UserBean;
import com.model.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 * 已完成 7/11 -xjy
 */
@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getIpAddr(HttpServletRequest request) {      
        String ip = request.getHeader("x-forwarded-for");      
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("WL-Proxy-Client-IP");     
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getRemoteAddr();
        }
         return ip;
    }  


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		request.getSession().removeAttribute("username");//由于粘性表单，所以在sessoin里存了username，所以要删除
		
		
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.print("username");
		System.out.print("password");

		UserDao d = new UserDao();
		UserBean user = d.getUser(account);
		String ip=getIpAddr(request);
		System.out.print(ip);

		request.getSession().setAttribute("chargedByList",d.findAllChargedMan());
		request.getSession().setAttribute("confirmedByList",d.findAllConfirmeddMan());
		request.getSession().setAttribute("developerList", d.findAllDeveloper());
		request.getSession().setAttribute("tester", d.findAllTester());
		
		//System.out.println(user.toString());
		if (user == null||user.getPassword()==null) {
			System.out.println("无该用户");
			response.sendRedirect("login.jsp");
			return;
		}
		if (user.getPassword().equals(password)) {
			System.out.println("验证通过");
			UserDao use = new UserDao();
			use.updateUserLogin(account, ip);
			HttpSession session = request.getSession(true);
			//sesson存入用户信息
			user=use.getUser(account);
			session.setAttribute("user", user);
			if(user.getVisits()==1){
				request.getRequestDispatcher("firstLogin.jsp").forward(request, response);
				return;
			}else{
				response.sendRedirect("product");
				return;
			}
		
		} else {
			request.getSession().setAttribute("username", account);
			System.out.println("密码错误");
			response.sendRedirect("login.jsp");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
