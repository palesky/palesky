package com.controller.bug;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.BugBean;
import com.model.bean.UserBean;
import com.model.dao.BugDao;
import com.model.dao.TaskDao;
import com.model.dao.Task_testerDao;
import com.model.dao.UsecaseDao;

/**
 * Servlet implementation class AddBugServlet
 */
@WebServlet("/addbug")
public class AddBugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UserBean user=new UserBean();
		user=(UserBean)request.getSession().getAttribute("user");
		
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		String bug_type=request.getParameter("bug_type");
		String os=request.getParameter("os");
		String browser=request.getParameter("browser");
		String foundBy=user.getId();
		String priority=request.getParameter("priority");
		String steps=request.getParameter("steps");
		String task_tester=request.getParameter("task_testerId");
		
		System.out.println(name);
		System.out.println(status);
		System.out.println(bug_type);
		System.out.println(os);
		System.out.println(browser);
		System.out.println(foundBy);
		System.out.println(priority);
		System.out.println(steps);
		System.out.println(task_tester);
		
		if(name==null||status==null||bug_type==null||os==null||browser==null||foundBy==null||priority==null||steps==null){
			UsecaseDao usecaseDao=new UsecaseDao();
			Task_testerDao tt=new Task_testerDao();
			request.setAttribute("usecaseList",usecaseDao.findAllUsecase());
			request.setAttribute("taskList",tt.findAll());
			request.getRequestDispatcher("addBug.jsp").forward(request, response);
			return;
		}else{
			//get task_tester id
			BugDao d=new BugDao();
			BugBean bug=new BugBean();
			bug.setName(name);
			bug.setStatus(status);
			bug.setBug_type(bug_type);
			bug.setOs(os);
			bug.setBrowser(browser);
			bug.setFoundBy(foundBy);
			bug.setTask_testerId(Integer.parseInt(task_tester));
			bug.setPriority(priority);
			bug.setSteps(steps);
			d.addBug(bug);
			request.getRequestDispatcher("bug").forward(request, response);
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
