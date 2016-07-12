package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.bean.TmpBean;

/**
 * 
 */
public class TmpDao extends BaseDao {
	
	//该功能 用到的机会不大
	public ArrayList<TmpBean> findAllTmp() {
		
		ArrayList<TmpBean> list = new ArrayList<TmpBean>();
		String sql = "SELECT * FROM tmp ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				TmpBean TmpBean = new TmpBean();
				TmpBean.setUserid(rst.getString("userid"));
				TmpBean.setProd_id(rst.getString("prod_id"));
				TmpBean.setProj_id(rst.getString("proj_id"));
				TmpBean.setDema_id(rst.getString("dema_id"));
				TmpBean.setTask_id(rst.getString("task_id"));
				TmpBean.setBug_id(rst.getString("Bug_id"));
				TmpBean.setCase_id(rst.getString("case_id"));			
				list.add(TmpBean);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public TmpBean getTmp(String userid) {
		String sql = "SELECT * FROM Tmp where userid=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			ResultSet rst = pstmt.executeQuery();
			TmpBean TmpBean = new TmpBean();
			while (rst.next()) {
				TmpBean.setUserid(rst.getString("userid"));
				TmpBean.setProd_id(rst.getString("prod_id"));
				TmpBean.setProj_id(rst.getString("proj_id"));
				TmpBean.setDema_id(rst.getString("dema_id"));
				TmpBean.setTask_id(rst.getString("Task_id"));
				TmpBean.setBug_id(rst.getString("Bug_id"));
				TmpBean.setCase_id(rst.getString("case_id"));				
			}
			return TmpBean;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}

	//可能 功能的实现还有点问题
	public boolean addTmp(String useid) {
		String sql = "INSERT INTO tmp(userid,prod_id,proj_id,dema_id,task_id,bug_id,case_id)VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			String t=null;
			pstmt.setString(1, useid);
			pstmt.setString(2, t);
			pstmt.setString(3, t);
			pstmt.setString(4, t);
			pstmt.setString(5, t);
			pstmt.setString(6, t);
			pstmt.setString(7, t);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteTmp(String userid) {
		String sql = "DELETE FROM tmp where userid=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	
	public boolean updateTmp(TmpBean Tmp) {
		String sql = "update Tmp set prod_id=?,proj_id=?,dema_id=?,task_id=?,bug_id=?,case_id=? where userid=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, Tmp.getProd_id());
			pstmt.setString(2, Tmp.getProj_id());
			pstmt.setString(3, Tmp.getDema_id());
			pstmt.setString(4, Tmp.getTask_id());
			pstmt.setString(5, Tmp.getBug_id());
			pstmt.setString(6, Tmp.getCase_id());
			pstmt.setString(7, Tmp.getUserid());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

}
