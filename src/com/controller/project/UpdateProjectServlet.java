package com.controller.project;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.ProjectBean;
import com.model.dao.ProjectDao;

/**
 * Servlet implementation class UpdateProjectServlet
 */
@WebServlet("/updateproject")
public class UpdateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProjectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
//		String pro_type = request.getParameter("pro_type");
		String createdBy = request.getParameter("createdBy");
		String endDate = request.getParameter("endDate");
		String explain = request.getParameter("explain");
		String confirmedBy = request.getParameter("confirmedBy");
		String chargeBy = request.getParameter("chargeBy");
		String team=request.getParameter("team");
		String prod_id=request.getParameter("pro_id");
		if (id == null || name == null || status == null || createdBy == null ||team==null ||prod_id==null
				|| endDate == null || explain == null || confirmedBy == null || chargeBy == null) {
			System.out.println("错误");
		}
		ProjectBean project =new ProjectBean();
		project.setId(id);
		project.setName(name);
		project.setStatus(status);
//		project.setPro_type(pro_type);
		project.setCreatedBy(createdBy);
		project.setEndDate(endDate);
		project.setExplain(explain);
		project.setConfirmedBy(confirmedBy);
		project.setChargeBy(chargeBy);
		project.setTeam(team);
		project.setProd_id(prod_id);
		ProjectDao dao=new ProjectDao();
		if(dao.updateProject(project)){
			System.out.println("修改成功");
			response.sendRedirect("project");
		}else{
			System.out.println("修改失败");
			response.sendRedirect("project");
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
