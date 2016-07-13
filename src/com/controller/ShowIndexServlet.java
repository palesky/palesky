package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.BugBean;
import com.model.bean.DemandBean;
import com.model.bean.ProductBean;
import com.model.bean.TaskBean;
import com.model.bean.UserBean;
import com.model.dao.BugDao;
import com.model.dao.DemandDao;
import com.model.dao.ProductDao;
import com.model.dao.TaskDao;

/**
 * Servlet implementation class ShowIndexServlet
 */
@WebServlet("/index")
public class ShowIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Object islogin=(UserBean)request.getSession().getAttribute("user");
		if(islogin==null)
			response.sendRedirect("login.jsp");
		
		DemandDao dd=new DemandDao();
		UserBean user=(UserBean)request.getSession().getAttribute("user");
		BugDao bug=new BugDao();
		ProductDao pro=new ProductDao();
		TaskDao taskd=new TaskDao();
		
		ArrayList<DemandBean> ddlist=dd.findMyChargeDemand(user.getId());
		ArrayList<BugBean> bl=bug.searchMyChargeBug(user.getId());
		ArrayList<ProductBean> prol=pro.findMyChargeProduct(user.getId());
		ArrayList<TaskBean> tl=taskd.findMyChargedTask(user.getId());
		
		request.setAttribute("bugNum", bl.size());
		request.setAttribute("demandNum", ddlist.size());
		request.setAttribute("proNum", prol.size());
		request.setAttribute("taskNum", tl.size());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
