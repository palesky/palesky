package com.controller.bug;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.UserBean;
import com.model.dao.BugDao;
import com.model.dao.DemandDao;
import com.model.dao.ProductDao;
import com.model.dao.ProjectDao;

/**
 * Servlet implementation class ShowBugServlet
 */
@WebServlet("/ShowBugServlet")
public class ShowBugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowBugServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 需要先移除可能存在的值
		request.removeAttribute("itemList");
		request.removeAttribute("list_group_title");
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		BugDao bugDao=new BugDao();

		// 用于关联项目与产品，所以给出产品列表，会在新建或更新时用到
//		ProductDao productDao = new ProductDao();
//		try {
//			request.setAttribute("productList", productDao.findAllProduct());
//			request.setAttribute("teamList", pj.findTeam());
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		String role = "";
		role=user.getRole();
		if (role.equals("产品经理") || role.equals("项目经理")) {
			request.setAttribute("list_group_title", "未修复的bug");
			request.setAttribute("list_group_title2", "已修复的bug");
			request.setAttribute("itemList",);
			request.setAttribute("itemList2",);
			request.setAttribute("itemType", "项目");
			request.setAttribute("itemType2", "需求");
			request.setAttribute("sonurl", "bug");

			request.getRequestDispatcher("bug.jsp").forward(request, response);
		} else if (role.equals("测试人员")) {
			request.setAttribute("list_group_title", "未修复的bug");
			request.setAttribute("list_group_title2", "未确认的bug(需要重新测试)");
			request.setAttribute("itemList",);
			request.setAttribute("itemList2",);
			request.setAttribute("itemType", "项目");
			request.setAttribute("itemType2", "需求");
			request.setAttribute("sonurl", "bug");

			request.getRequestDispatcher("bug.jsp").forward(request, response);
		} else if(role.equals("开发人员")){// 特定的需求
			request.setAttribute("list_group_title", "指派给我的bug");
			request.setAttribute("list_group_title2", "未确认的bug");
			request.setAttribute("itemList",);
			request.setAttribute("itemList2",);
			request.setAttribute("itemType", "项目");
			request.setAttribute("itemType2", "需求");
			request.setAttribute("sonurl", "bug");

			request.getRequestDispatcher("bug.jsp").forward(request, response);
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
