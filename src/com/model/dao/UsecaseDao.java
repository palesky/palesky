package com.model.dao;

//获取时间---自定义时间格式
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.bean.UsecaseBean;


public class UsecaseDao extends BaseDao{
	
	public ArrayList<UsecaseBean> findAllUsecase() {
		
		ArrayList<UsecaseBean> list = new ArrayList<UsecaseBean>();
		String sql = "SELECT * FROM usecase ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				UsecaseBean usecase = new UsecaseBean();
				usecase.setId(rst.getString("id"));
				usecase.setUsecaseLibId(rst.getString("usecaseLibId"));
				usecase.setCreatedBy(rst.getString("createdBy"));
				usecase.setCreatedDate(rst.getString("createdDate"));
				usecase.setSteps(rst.getString("steps"));
				list.add(usecase);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//--------------------------------------------------------------------------------------------------------------
	public ArrayList<UsecaseBean> findUsecaseByTask(String id){
		ArrayList<UsecaseBean> list= new ArrayList<UsecaseBean>();
		String sql="select * from usecase where usecaseId in (select usecaseId from task_usecase where taskId=?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				UsecaseBean usecase = new UsecaseBean();
				usecase.setId(rst.getString("id"));
				usecase.setUsecaseLibId(rst.getString("usecaseLibId"));
				usecase.setCreatedBy(rst.getString("createdBy"));
				usecase.setCreatedDate(rst.getString("createdDate"));
				usecase.setSteps(rst.getString("steps"));
				list.add(usecase);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//-----------------------------------------------------------------------------
	public ArrayList<UsecaseBean> findUsecaseByType(String id){
		ArrayList<UsecaseBean> list= new ArrayList<UsecaseBean>();
		String sql="select * from usecase where usecaseLibId =?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				UsecaseBean usecase = new UsecaseBean();
				usecase.setId(rst.getString("id"));
				usecase.setUsecaseLibId(rst.getString("usecaseLibId"));
				usecase.setCreatedBy(rst.getString("createdBy"));
				usecase.setCreatedDate(rst.getString("createdDate"));
				usecase.setSteps(rst.getString("steps"));
				list.add(usecase);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addUsecase(UsecaseBean usecase) {
		
		String sql = "INSERT INTO usecase(id,usecaseLibId,createdBy,createdDate,steps)VALUES(?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, usecase.getId());
			pstmt.setString(2, usecase.getUsecaseLibId());
			pstmt.setString(3, usecase.getCreatedBy());
			pstmt.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(5, usecase.getSteps());
			pstmt.executeUpdate();
			
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	
	public boolean deleteUsecase(String id) {
		String sql = "DELETE FROM usecase where id=?";
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
	
	
	public boolean updateUsecase(UsecaseBean usecase) {
		String sql = "update usecase set id=?,usecaseLibId=?,steps=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, usecase.getId());
			pstmt.setString(2, usecase.getUsecaseLibId());
			pstmt.setString(3, usecase.getSteps());
			pstmt.setString(4, usecase.getId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	public Object finUsecaseByTask() {
		// TODO Auto-generated method stub
		return null;
	}

	public UsecaseBean getUsecase(String id) {
		UsecaseBean usecase = new UsecaseBean();
		String sql="select * from usecase where id =?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
				usecase.setId(rst.getString("id"));
				usecase.setUsecaseLibId(rst.getString("usecaseLibId"));
				usecase.setCreatedBy(rst.getString("createdBy"));
				usecase.setCreatedDate(rst.getString("createdDate"));
				usecase.setSteps(rst.getString("steps"));
			}
			return usecase;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
