package com.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.ProductBean;
import com.model.dao.ProductDao;

/**
 * Servlet implementation class UpdateProductServlet
 */
@WebServlet("/updateproduct")
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * urlname-》
	 * 只需要修改product->xxxxx
	 * Product-> Xxxxx
	 * 即可
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String status = request.getParameter("status");
		String pro_type = request.getParameter("pro_type");
		String createdBy = request.getParameter("createdBy");
		String endDate = request.getParameter("endDate");
		String explain = request.getParameter("explain");
		String confirmedBy = request.getParameter("confirmedBy");
		String chargeBy = request.getParameter("chargeBy");

		if (id == null || name == null || status == null || pro_type == null || createdBy == null
				|| endDate == null || explain == null || confirmedBy == null || chargeBy == null) {
			System.out.println("错误");
		}
		ProductBean product =new ProductBean();
		product.setId(id);
		product.setName(name);
		product.setStatus(status);
		product.setPro_type(pro_type);
		product.setCreatedBy(createdBy);
		product.setEndDate(endDate);
		product.setExplain(explain);
		product.setConfirmedBy(confirmedBy);
		product.setChargeBy(chargeBy);
		ProductDao dao=new ProductDao();
		if(dao.updateProduct(product)){
			System.out.println("修改成功");
			response.sendRedirect("product");
		}else{
			System.out.println("修改失败");
			response.sendRedirect("product");
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
