package com.controller.usecase;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.UsecaseBean;
import com.model.bean.UserBean;
import com.model.dao.UsecaseDao;

/**
 * Servlet implementation class AddUsecase
 */
@WebServlet("/addusecase")
public class AddUsecaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUsecaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		UsecaseDao dao=new UsecaseDao();
		UsecaseBean usecase=new UsecaseBean();
		UserBean user=(UserBean)request.getSession().getAttribute("user");
		
		String id=request.getParameter("id");
//		String usecaseLibId=request.getParameter("usecaseLibId");
		String createdBy=request.getParameter("createdBy");
		String createdDate=request.getParameter("createdDate");
		String steps=request.getParameter("steps");
		
		usecase.setId(id);
//		usecase.setUsecaseLibId(usecaseLibId);
		usecase.setCreatedBy(user.getId());
//		usecase.setCreatedDate(createdDate);
		usecase.setSteps(steps);
		
		if(!dao.addUsecase(usecase)){
			System.out.println("新建用例失败");
		}
		response.sendRedirect("usecase");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
