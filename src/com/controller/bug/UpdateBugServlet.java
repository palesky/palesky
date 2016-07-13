package com.controller.bug;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.BugBean;
import com.model.dao.BugDao;
import com.model.dao.TaskDao;
import com.model.dao.UsecaseDao;

/**
 * Servlet implementation class UpdateBugServlet
 */
@WebServlet("/updatebug")
public class UpdateBugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		String bug_type=request.getParameter("bug_type");
		String os=request.getParameter("os");
		String browser=request.getParameter("browser");
		String foundBy=request.getParameter("foundBy");
		String foundDate=request.getParameter("foundDate");
		String priority=request.getParameter("priority");
		String steps=request.getParameter("steps");
		if(id==null||name==null||status==null||bug_type==null||os==null||browser==null||foundBy==null||priority==null||steps==null){
			UsecaseDao usecaseDao=new UsecaseDao();
			TaskDao taskDao=new TaskDao();
			request.setAttribute("usecaseList",usecaseDao.findAllUsecase());
			request.setAttribute("taskList", taskDao.findAllTask());
			request.getRequestDispatcher("addBug.jsp").forward(request, response);
			return;
		}else{
			//get task_tester id
			
			
			BugDao d=new BugDao();
			BugBean bug=new BugBean();
			bug.setId(id);
			bug.setName(name);
			bug.setStatus(status);
			bug.setBug_type(bug_type);
			bug.setOs(os);
			bug.setBrowser(browser);
			bug.setFoundBy(foundBy);
			bug.setFoundDate(foundDate);
			bug.setPriority(priority);
			bug.setSteps(steps);
			d.addBug(bug);
			request.getRequestDispatcher("bug?q="+id).forward(request, response);
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
