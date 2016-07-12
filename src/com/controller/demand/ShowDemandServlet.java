package com.controller.demand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.UserBean;
import com.model.dao.TaskDao;
import com.model.dao.DemandDao;
import com.model.dao.ProjectDao;
import com.model.dao.DemandDao;

/**
 * Servlet implementation class ShowDemandServlet
 */
@WebServlet("/demand")
public class ShowDemandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowDemandServlet() {
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
				DemandDao demandDao = new DemandDao();
				TaskDao taskDao = new TaskDao();
				
				//用于关联项目与产品，所以给出项目列表，会在新建或更新时用到
				ProjectDao projectDao=new ProjectDao();
				request.setAttribute("projectList",projectDao.findAllProject());
				
				String q = "";
				if(request.getParameter("q")==null)
					q="";
				else
					q=request.getParameter("q");
				if (q.equals("all") || q.equals("")) {
					request.setAttribute("list_group_title", "需求列表");
					request.setAttribute("list_group_title2", "和我有关的任务");
					request.setAttribute("itemList", demandDao.findAllDemand());
					request.setAttribute("itemList2", taskDao.findMyChargedTask(user.getId()));
					request.setAttribute("itemType", "需求");
					request.setAttribute("itemType2", "任务");
					request.setAttribute("url", "demand");
					request.setAttribute("url2", "task");
					
					request.getRequestDispatcher("demand.jsp").forward(request, response);
				} else if (q.equals("me")) {
					request.setAttribute("list_group_title", "和我有关的需求");
					request.setAttribute("list_group_title2", "和我有关的任务");
					request.setAttribute("itemList", demandDao.findMyChargeDemand(user.getId()));
					request.setAttribute("itemList2", taskDao.findMyChargedTask(user.getId()));
					request.setAttribute("itemType", "需求");
					request.setAttribute("itemType2", "任务");
					request.setAttribute("url", "demand");
					request.setAttribute("url2", "task");
					
					request.getRequestDispatcher("demand.jsp").forward(request, response);
				} else {//特定的任务
					request.setAttribute("list_group_title3", "任务列表");
					request.setAttribute("item", demandDao.getDemand(q));
					request.setAttribute("itemList", taskDao.findAllTask());
					request.setAttribute("itemType", "需求");
					request.setAttribute("url", "demand");
					request.setAttribute("sonUrl", "task");
					
					request.getRequestDispatcher("demandInfo.jsp").forward(request, response);
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
