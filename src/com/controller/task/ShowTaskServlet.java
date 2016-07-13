package com.controller.task;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.TmpBean;
import com.model.bean.UserBean;
import com.model.dao.BugDao;
import com.model.dao.DemandDao;
import com.model.dao.ProjectDao;
import com.model.dao.TaskDao;
import com.model.dao.TmpDao;
import com.model.dao.UsecaseDao;

/**
 * Servlet implementation class ShowTaskServlet
 */
@WebServlet("/task")
public class ShowTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowTaskServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stubow
		// 命名为itemList的原因是因为要实现 ./WEB-INF/part/list-group
		// 的复用，所以所有数据传过去，最好都命名为itemList --xjy
		// 需要先移除可能存在的值
		request.removeAttribute("itemList");
		request.removeAttribute("list_group_title");
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		TaskDao taskDao = new TaskDao();
		UsecaseDao usecaseDao = new UsecaseDao();
		BugDao bugDao = new BugDao();

		// 读取记录用户选择的项目
		TmpDao tmpDao = new TmpDao();
		TmpBean tmp = tmpDao.getTmp(user.getId());
		String saveid = tmp.getTask_id();
		if (saveid == null) {
			saveid = "all";
			tmpDao.setTask(user.getId(), saveid);
		}

		// 用于关联任务与需求，所以给出需求列表，会在新建或更新时用到
		DemandDao demandDao = new DemandDao();
		request.setAttribute("demandList", demandDao.findAllDemand());

		String q = "";
		if (request.getParameter("q") == null)
			q = "";
		else
			q = request.getParameter("q");

		if (q.equals("") && !saveid.equals("all") && !saveid.equals("me")) {
			q = tmp.getTask_id();
			request.setAttribute("list_group_title3", "任务列表");
			request.setAttribute("item", taskDao.getTask(q));
			request.setAttribute("usecaseList", usecaseDao.findAllUsecase());
			request.setAttribute("bugList", bugDao.findAllBug());
			request.setAttribute("itemType", "任务");
			request.setAttribute("url", "task");

			request.getRequestDispatcher("taskInfo.jsp").forward(request, response);
		} else if (q.equals("all") || q.equals("")) {
			if(!saveid.equals("all"))
				tmpDao.setTask(user.getId(), "all");
			request.setAttribute("list_group_title", "任务列表");
			request.setAttribute("list_group_title2", "和我有关的测试");
			request.setAttribute("list_group_title3", "和我有关的bug");
			request.setAttribute("itemList", taskDao.findMyChargedTask(user.getId()));
			request.setAttribute("itemList2", usecaseDao.findAllUsecase());
			request.setAttribute("itemType", "任务");
			request.setAttribute("itemType2", "测试");
			request.setAttribute("itemType3", "bug");
			request.setAttribute("url", "task");
			request.setAttribute("url2", "usecase");
			request.setAttribute("url3", "bug");

			request.getRequestDispatcher("task.jsp").forward(request, response);
		} else if (q.equals("me")) {
			if(!saveid.equals("me"))
				tmpDao.setTask(user.getId(), "me");
			request.setAttribute("list_group_title", "和我有关的任务");
			request.setAttribute("list_group_title2", "和我有关的测试");
			request.setAttribute("list_group_title3", "和我有关的bug");
			request.setAttribute("itemList", taskDao.findMyChargedTask(user.getId()));
			request.setAttribute("itemList2", usecaseDao.findAllUsecase());
			request.setAttribute("itemList3", bugDao.findAllBug());
			request.setAttribute("itemType", "任务");
			request.setAttribute("itemType2", "测试");
			request.setAttribute("itemType3", "bug");
			request.setAttribute("url", "task");
			request.setAttribute("url2", "usecase");
			request.setAttribute("url3", "bug");

			request.getRequestDispatcher("task.jsp").forward(request, response);
		} else {// 特定的任务
			tmpDao.setTask(user.getId(), q);
			request.setAttribute("list_group_title3", "任务列表");
			request.setAttribute("item", taskDao.getTask(q));
			request.setAttribute("usecaseList", usecaseDao.findAllUsecase());
			request.setAttribute("bugList", bugDao.findAllBug());
			request.setAttribute("itemType", "任务");
			request.setAttribute("url", "task");

			request.getRequestDispatcher("taskInfo.jsp").forward(request, response);
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
