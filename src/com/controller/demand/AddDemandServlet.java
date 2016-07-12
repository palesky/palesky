package com.controller.demand;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.DemandBean;
import com.model.dao.DemandDao;

/**
 * Servlet implementation class AddDemandServlet
 */
@WebServlet("/adddemand")
public class AddDemandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDemandServlet() {
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
//		String pro_type=request.getParameter("pro_type");
		String createdBy=request.getParameter("createdBy");
		String endDate=request.getParameter("endDate");
		String explain=request.getParameter("explain");
		String confirmedBy=request.getParameter("confirmedBy");
		String chargeBy=request.getParameter("chargeBy");
		String project_id=request.getParameter("project_id");
		DemandBean demand=new DemandBean();
		
		demand.setId(id);
		demand.setName(name);
		demand.setStatus(status);
//		demand.setPro_type(pro_type);
		demand.setCreatedBy(createdBy);
		demand.setEndDate(endDate);
		demand.setExplain(explain);
		demand.setConfirmedBy(confirmedBy);
		demand.setChargeBy(chargeBy);
		demand.setProject_id(project_id);
		
		System.out.println(demand.toString());
		
		DemandDao demandDao=new DemandDao();
		demandDao.addDemand(demand);
		response.sendRedirect("demand?q="+id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
