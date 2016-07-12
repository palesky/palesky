package com.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.UserBean;
import com.model.dao.UserDao;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UserDao d=new UserDao();
		UserBean user=(UserBean)request.getSession().getAttribute("user");
		String realname=request.getParameter("realname");
		String email=request.getParameter("email");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String account=request.getParameter("account");
		
		System.out.println(realname);
		
		if(realname==null){realname=user.getRealname();}
		if(email==null){email=user.getEmail();}
		if(gender==null){gender=user.getGender();}
		if(phone==null){phone=user.getPhone();}
		if(account==null){account=user.getAccount();}
		
		user.setRealname(realname);
		user.setEmail(email);
		user.setGender(gender);
		user.setPhone(phone);
		user.setAccount(account);
		
		d.updateUser(user);
		
		response.sendRedirect("product");
		return;
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
