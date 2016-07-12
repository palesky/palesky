package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.model.bean.DemandBean;
import com.model.bean.TaskBean;

/**
 * 未测试
 * @author xj
 * demand 共有12项数据
 * 7/10 合理性修改
 *
 */
public class DemandDao extends BaseDao{
	
	//-----------------------------------------------------------------------------
    public int FindDemandBugNum(String id) throws SQLException{
    	int bugNum=0;
    	String sql ="update demand set bugNum = "+"(select sum(bugNum) from task where demand_id =? )"+"where id=?";
    	try(Connection conn= dataSource.getConnection();
    			PreparedStatement pstmt=conn.prepareStatement(sql)){
    		pstmt.setString(1, id);
    		pstmt.setString(2, id);
    		ResultSet rst =pstmt.executeQuery();
    		bugNum= rst.getInt("bugNum");
    		return bugNum;
    	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			bugNum=0;	
    	}
    	return bugNum;
    }
    
    //--------------------------------------------------------------------------------------------------------
    public ArrayList<TaskBean> findTaskByDemand(String id){
    	ArrayList<TaskBean> list = new ArrayList<TaskBean>();
    	String sql="select * from task where demand_id =?";
    	try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				TaskBean task = new TaskBean();
				task.setId(rst.getString("id"));
				task.setName(rst.getString("name"));
				task.setStatus(rst.getString("status"));
				task.setCreatedBy(rst.getString("createdBy"));
				task.setEndDate(rst.getString("endDate"));
				task.setExplain(rst.getString("explain"));
				task.setLastEditedBy(rst.getString("lastEditedBy"));
				task.setLastEditedDate(rst.getString("lastEditedDate"));
				task.setConfirmedBy(rst.getString("confirmedBy"));
				task.setDemand_id(rst.getString("demand_id"));
				task.setChargeBy(rst.getString("chargeBy"));
				task.setBugNum(rst.getInt("bugNum"));
				list.add(task);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    //---------------------------------------------------------------------------------------------
    public ArrayList<DemandBean> SearchDemand(String key) {
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql = "SELECT * FROM demand where id like ? or name like ? or status like ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,"%"+key+"%");
			pstmt.setString(2,"%"+key+"%");
			pstmt.setString(3,"%"+key+"%");
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				DemandBean demand = new DemandBean();
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setLastEditedDate(rst.getString("lastEditedDate"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
				demand.setProject_id(rst.getString("project_id"));
				demand.setChargeBy(rst.getString("chargeBy"));
				demand.setBugNum(rst.getInt("bugNum"));
				list.add(demand);
				System.out.println(demand.toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
    //---------------------------------------------------------------------------------------------
	public ArrayList<DemandBean> findAllDemand() {
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql = "SELECT * FROM demand ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				DemandBean demand = new DemandBean();
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setLastEditedDate(rst.getString("lastEditedDate"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
				demand.setProject_id(rst.getString("project_id"));
				demand.setChargeBy(rst.getString("chargeBy"));
				demand.setBugNum(rst.getInt("bugNum"));
				list.add(demand);
				System.out.println(demand.toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//-------------------------------------------------------------------------------
	public ArrayList<DemandBean> findMyCreatedDemand(String id){
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql="select * from demand where createdBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				DemandBean demand = new DemandBean();
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
                demand.setProject_id(rst.getString("project_id"));
				demand.setChargeBy(rst.getString("chargeBy"));
				demand.setBugNum(rst.getInt("bugNum"));
				list.add(demand);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	//-------------------------------------------------------------------
	public ArrayList<DemandBean> findMyConfirmedDemand(String id){
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql="select * from demand where confirmedBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				DemandBean demand = new DemandBean();
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
                demand.setProject_id(rst.getString("project_id"));
				demand.setChargeBy(rst.getString("chargeBy"));
				demand.setBugNum(rst.getInt("bugNum"));
				list.add(demand);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	//----------------------------------------------------------------------
	public ArrayList<DemandBean> findMyChargeDemand(String id){
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql="select * from demand where chargeBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				DemandBean demand = new DemandBean();
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
                demand.setProject_id(rst.getString("project_id"));
				demand.setChargeBy(rst.getString("chargeBy"));
				demand.setBugNum(rst.getInt("bugNum"));
				list.add(demand);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
    
	//通过id获取需求信息
	public DemandBean getDemand(String id) {
		String sql = "SELECT * FROM demand where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			DemandBean demand = new DemandBean();
			while (rst.next()) {
				demand.setId(rst.getString("id"));
				demand.setName(rst.getString("name"));
				demand.setStatus(rst.getString("status"));
				demand.setCreatedBy(rst.getString("createdBy"));
				demand.setCreatedDate(rst.getString("createdDate"));
				demand.setEndDate(rst.getString("endDate"));
				demand.setExplain(rst.getString("explain"));
				demand.setLastEditedDate(rst.getString("lastEditedDate"));
				demand.setConfirmedBy(rst.getString("confirmedBy"));
				demand.setProject_id(rst.getString("project_id"));
				demand.setChargeBy(rst.getString("chargeBy"));
				demand.setBugNum(rst.getInt("bugNum"));
			}
			return demand;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}

	
	public boolean addDemand(DemandBean demand) {
//		Date d=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
//		String dateNowStr =sdf.format(d);
		
		String sql = "INSERT INTO demand(id,name,status,createdBy,createdDate,endDate,`explain`,lastEditedDate,confirmedBy,project_id,chargeBy,bugNum)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, demand.getId());
			pstmt.setString(2, demand.getName());
			pstmt.setString(3, demand.getStatus());
			pstmt.setString(4, demand.getCreatedBy());
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(7, demand.getExplain());
			pstmt.setDate(8, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(9, demand.getConfirmedBy());
			pstmt.setString(10, demand.getProject_id());
			pstmt.setString(11, demand.getChargeBy());
			pstmt.setInt(12,0);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteDemand(String id) {
		String sql = "DELETE FROM demand where id=?";
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

	
	public boolean updateDemand(DemandBean demand) {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String dateNowStr =sdf.format(d);
		String sql = "update demand set id=?,name=?,status=?,endDate=?,`explain`=?,lastEditedDate=?,confirmedBy=?, chargeBy=?,bugNum =? where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, demand.getId());
			pstmt.setString(2, demand.getName());
			pstmt.setString(3, demand.getStatus());
			pstmt.setString(4, demand.getEndDate());
			pstmt.setString(5, demand.getExplain());
			pstmt.setString(6, dateNowStr);
			pstmt.setString(7, demand.getConfirmedBy());
			pstmt.setString(8, demand.getId());
			pstmt.setString(9, demand.getChargeBy());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

}
