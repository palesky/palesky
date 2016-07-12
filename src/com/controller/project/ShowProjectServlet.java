package com.controller.project;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.UserBean;
import com.model.dao.DemandDao;
import com.model.dao.ProductDao;
import com.model.dao.ProjectDao;
import com.model.dao.ProjectDao;

/**
 * Servlet implementation class ShowProjectServlet
 */
@WebServlet("/project")
public class ShowProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stubow
				// 命名为itemList的原因是因为要实现 ./WEB-INF/part/list-group
				// 的复用，所以所有数据传过去，最好都命名为itemList --xjy
				// 需要先移除可能存在的值
				request.removeAttribute("itemList");
				request.removeAttribute("list_group_title");
				UserBean user=(UserBean)request.getSession().getAttribute("user");
				ProjectDao pj = new ProjectDao();
				DemandDao dd = new DemandDao();

				//用于关联项目与产品，所以给出产品列表，会在新建或更新时用到
				ProductDao productDao=new ProductDao();
				try {
					request.setAttribute("productList",productDao.findAllProduct());
					request.setAttribute("teamList", pj.findTeam());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String q = "";
				if(request.getParameter("q")==null)
					q="";
				else
					q=request.getParameter("q");
				if (q.equals("all") || q.equals("")) {
					request.setAttribute("list_group_title", "项目列表");
					request.setAttribute("list_group_title2", "和我有关的需求");
					request.setAttribute("itemList", pj.findAllProject());
					request.setAttribute("itemList2", dd.findMyChargeDemand(user.getId()));
					request.setAttribute("itemType", "项目");
					request.setAttribute("itemType2", "需求");
					request.setAttribute("url", "project");
					request.setAttribute("url2", "demand");
					
					request.getRequestDispatcher("project.jsp").forward(request, response);
				} else if (q.equals("me")) {
					request.setAttribute("list_group_title", "和我有关的项目");
					request.setAttribute("list_group_title2", "和我有关的需求");
					request.setAttribute("itemList", pj.findMyChargeProject(user.getId()));
					request.setAttribute("itemList2", dd.findMyChargeDemand(user.getId()));
					request.setAttribute("itemType", "项目");
					request.setAttribute("itemType2", "需求");
					request.setAttribute("url", "project");
					request.setAttribute("url2", "product");
					
					request.getRequestDispatcher("project.jsp").forward(request, response);
				} else {//特定的需求
					request.setAttribute("list_group_title3", "需求列表");
					request.setAttribute("item", pj.getProject(q));
					request.setAttribute("itemList", dd.findAllDemand());
					request.setAttribute("itemType", "项目");
					request.setAttribute("url", "project");
					request.setAttribute("sonUrl", "product");
					
					request.getRequestDispatcher("projectInfo.jsp").forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
