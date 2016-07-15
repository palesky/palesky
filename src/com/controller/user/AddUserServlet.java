package com.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.UserDao;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String role=request.getParameter("role");
		UserDao user=new UserDao();
		if(role==null){
			request.getRequestDispatcher("status.jsp").forward(request, response);
		}
		String msg="";
		if(role.equals("产品经理")){
			 msg=user.addProdManager();
		}else if(role.equals("项目经理")){
			 msg=user.addProjManager();
		}else if(role.equals("测试经理")){
			 msg=user.addTestManager();
		}else if(role.equals("测试人员")){
			 msg=user.addTester();
		}else if(role.equals("开发人员")){
			 msg=user.addDeveloper();
		}
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("status.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
