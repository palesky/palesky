package com.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.ProductBean;
import com.model.bean.Task_testerBean;


/*
 * 只做了增删改 功能
 * 
 * 
 * */
/**
 * Servlet implementation class Task_testerDao
 */
@WebServlet("/Task_testerDao")
public class Task_testerDao extends BaseDao {
	
	public Task_testerBean getTask_tester(String id) {
		String sql = "SELECT * FROM task_tester where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			Task_testerBean product = new Task_testerBean();
			while (rst.next()) {
				product.setId(rst.getInt("id"));
				product.setTaskId(rst.getString("taskId"));
				product.setUserId(rst.getString("userId"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setBugNum(rst.getInt("bugNum"));
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}
	
	//-------------------------------------------------------------------------------
	public boolean addTask_tester(Task_testerBean product) {
		
		String sql = "INSERT INTO task_tester(taskId,userId,createdDate,bugNum)VALUES(?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getTaskId());
			pstmt.setString(2, product.getUserId());
			pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setInt(4, 0);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	//-------------------------------------------------------------
	public boolean deleteTask_tester(String id) {
		String sql = "DELETE FROM task_tester where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	//------------------------------------------------------------------
	public boolean updateTask_tester(Task_testerBean product) {
		String sql = "update task_tester set id=?,name=?,taskId=?,userId=?,bugNum=?  where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getTaskId());
			pstmt.setString(3, product.getUserId());	
			pstmt.setInt(4, product.getBugNum());
			pstmt.setInt(5, product.getId());			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Task_testerBean> findAll(){
		ArrayList<Task_testerBean> list=new ArrayList<Task_testerBean>();
		String sql = "SELECT * FROM task_tester";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				Task_testerBean product = new Task_testerBean();
				product.setId(rst.getInt("id"));
				product.setTaskId(rst.getString("taskId"));
				product.setUserId(rst.getString("userId"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setBugNum(rst.getInt("bugNum"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}
	
}
