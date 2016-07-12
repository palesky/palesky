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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/addproduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String status=request.getParameter("status");
		String pro_type=request.getParameter("pro_type");
		String createdBy=request.getParameter("createdBy");
		String endDate=request.getParameter("endDate");
		String explain=request.getParameter("explain");
		String confirmedBy=request.getParameter("confirmedBy");
		String chargeBy=request.getParameter("chargeBy");
		
		ProductBean product=new ProductBean();
		
		product.setId(id);
		product.setName(name);
		product.setStatus(status);
		product.setPro_type(pro_type);
		product.setCreatedBy(createdBy);
		product.setEndDate(endDate);
		product.setExplain(explain);
		product.setConfirmedBy(confirmedBy);
		product.setChargeBy(chargeBy);
		
		System.out.println(product.toString());
		
		ProductDao productDao=new ProductDao();
		productDao.addProduct(product);
		response.sendRedirect("product?q="+id);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
