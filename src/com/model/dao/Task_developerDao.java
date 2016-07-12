package com.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.bean.Task_developerBean;

/**
 * Servlet implementation class Task_developerDao
 */
@SuppressWarnings("serial")
@WebServlet("/Task_developerDao")
public class Task_developerDao extends BaseDao {
	
	public Task_developerBean getTask_developer(String id) {
		String sql = "SELECT * FROM task_developer where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			Task_developerBean product = new Task_developerBean();
			while (rst.next()) {
				product.setId(rst.getString("id"));
				product.setName(rst.getString("name"));
				product.setTaskId(rst.getString("taskId"));
				product.setUserId(rst.getString("userId"));
				product.setCreatedDate(rst.getString("createdDate"));
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}
	
	//-------------------------------------------------------------------------------
	public boolean addTask_developer(Task_developerBean product) {
		
		String sql = "INSERT INTO task_developer(id,name,taskId,userId,createdDate)VALUES(?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getTaskId());
			pstmt.setString(4, product.getUserId());
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	//-------------------------------------------------------------
	public boolean deleteTask_developer(String id) {
		String sql = "DELETE FROM task_developer where id=?";
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
	public boolean updateTask_developer(Task_developerBean product) {
		String sql = "update task_developer set id=?,name=?,taskId=?,userId=? where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getTaskId());
			pstmt.setString(4, product.getUserId());	
			pstmt.setString(6, product.getId());			
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
}
