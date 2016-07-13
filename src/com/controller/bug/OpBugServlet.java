package com.controller.bug;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.dao.BugDao;

/**
 * Servlet implementation class OpBugServlet
 */
@WebServlet("/assignbug")
public class OpBugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpBugServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		BugDao bd=new BugDao();
		String id=request.getParameter("id");
		String chargeBy=request.getParameter("chargeBy");
		String type=request.getParameter("type");
		
		bd.ChangeMan(id, chargeBy);
		if(type.equals("指派")){
			bd.changeStatus("待分配", id);
		}else if(type.equals("分配")){
			bd.changeStatus("进行中", id);
		}else if(type.equals("提交")){
			bd.changeStatus("待审核", id);
		}
		response.sendRedirect("bug?q="+id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
