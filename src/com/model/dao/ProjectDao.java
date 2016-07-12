package com.model.dao;

import java.sql.Connection;
import com.model.bean.UserBean;
import java.util.Date;
import java.text.SimpleDateFormat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.bean.DemandBean;
import com.model.bean.ProjectBean;
/**
 * 未测试，以此句为准
 * @author xj
 *
 *
 * 增加 bugNum  7/11  lk
 */
public class ProjectDao extends BaseDao{
	
	
	 public int FindProjectBugNum(String id) throws SQLException{
	    	int bugNum=0;
	    	String sql ="update project set bugNum = "+"(select sum(bugNum) from demand where project_id =? )"+"where id=?";
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
	
	 //-----------------------------------------------------------------------------
	    public ArrayList<String> findTeam(){
	    	ArrayList<String> list = new ArrayList<String>();
	    	String sql="select distinct team from project";
	    	try (Connection conn = dataSource.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rst = pstmt.executeQuery();
				while(rst.next()){
					String team = rst.getString("team");
				    list.add(team);
				}
				return list;
	    	} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	    }
	    //------------------------------------------------------------------------------
	    public ArrayList<UserBean> findTeamMember(String id){
	    	ArrayList<UserBean> list= new ArrayList<UserBean>();
	    	String sql ="select * from where belongTo =?";
	    	try (Connection conn = dataSource.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
	    		pstmt.setString(1, id);
	    		ResultSet rst = pstmt.executeQuery();
	    		while(rst.next()){
	    			UserBean UserBean = new UserBean();
					UserBean.setId(rst.getString("id"));
					UserBean.setAccount(rst.getString("account"));
					UserBean.setPassword(rst.getString("password"));
					UserBean.setRealname(rst.getString("realname"));
					UserBean.setGender(rst.getString("gender"));
					UserBean.setRole(rst.getString("role"));
					UserBean.setEmail(rst.getString("email"));
					UserBean.setPhone(rst.getString("phone"));
					UserBean.setIp(rst.getString("lastIp"));
					UserBean.setLastLogin(rst.getString("lastLogin"));
					UserBean.setVisits(rst.getInt("visit"));
					UserBean.setPrivilege(rst.getString("privilege"));
					UserBean.setBelongTo(rst.getString("belongTo"));
					list.add(UserBean);
	    		}return list;
	    	}catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	    	
	    }
	 //------  关联 项目到 产品
	 public boolean MatchProjectToProduct(String id,String prod_id) {
			
			String sql = "update project prod_id =? where id=?";
			try (Connection conn = dataSource.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ProjectBean project = new ProjectBean();
				pstmt.setString(2, project.getName());
				pstmt.setString(3, project.getStatus());
				pstmt.setString(4, project.getEndDate());
				pstmt.setString(5, project.getExplain());
				pstmt.setString(6, project.getTeam());
				pstmt.setString(7, project.getConfirmedBy());
				pstmt.setString(8, project.getChargeBy());
				pstmt.setString(9, project.getId());
				pstmt.executeUpdate();
				return true;
			} catch (SQLException se) {
				se.printStackTrace();
				return false;
			}
		}
	 
	    //---------------------------------------------------------------------
	    public ArrayList<ProjectBean> SearchProject(String key) {
			ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
			String sql = "SELECT * FROM project where id like ? or name like ? or status like ?";
			try (Connection conn = dataSource.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, "%"+key+"%");
				pstmt.setString(2, "%"+key+"%");
				pstmt.setString(3, "%"+key+"%");
				ResultSet rst = pstmt.executeQuery();
				while (rst.next()) {
					ProjectBean project = new ProjectBean();
					project.setId(rst.getString("id"));
					project.setName(rst.getString("name"));
					project.setStatus(rst.getString("status"));
					project.setCreatedBy(rst.getString("createdBy"));
					project.setCreatedDate(rst.getString("createdDate"));
					project.setEndDate(rst.getString("endDate"));
					project.setExplain(rst.getString("explain"));
					project.setTeam(rst.getString("team"));
					project.setConfirmedBy(rst.getString("confirmedBy"));
					project.setProd_id(rst.getString("prod_id"));
					project.setChargeBy(rst.getString("chargeBy"));
					project.setBugNum(rst.getInt("bugNum"));
					
					System.out.println(project.toString());
					list.add(project);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	 
	    //------------------------------------------------------------------------------
    	public ArrayList<ProjectBean> findAllProject() {
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		String sql = "SELECT * FROM project ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				ProjectBean project = new ProjectBean();
				project.setId(rst.getString("id"));
				project.setName(rst.getString("name"));
				project.setStatus(rst.getString("status"));
				project.setCreatedBy(rst.getString("createdBy"));
				project.setCreatedDate(rst.getString("createdDate"));
				project.setEndDate(rst.getString("endDate"));
				project.setExplain(rst.getString("explain"));
				project.setTeam(rst.getString("team"));
				project.setConfirmedBy(rst.getString("confirmedBy"));
				project.setProd_id(rst.getString("prod_id"));
				project.setChargeBy(rst.getString("chargeBy"));
				project.setBugNum(rst.getInt("bugNum"));
				
				System.out.println(project.toString());
				list.add(project);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	//查找一个项目下的所有需求
	public ArrayList<DemandBean> findDemandByProject(String id){
		ArrayList<DemandBean> list = new ArrayList<DemandBean>();
		String sql="select * from demand where demand_id =?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
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
	//---------------------------------------------------------------------------------
	public ArrayList<ProjectBean> findMyCreatedProject(String id){
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		String sql="select * from project where createdBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				ProjectBean project = new ProjectBean();
				project.setId(rst.getString("id"));
				project.setName(rst.getString("name"));
				project.setStatus(rst.getString("status"));
				project.setCreatedBy(rst.getString("createdBy"));
				project.setCreatedDate(rst.getString("createdDate"));
				project.setEndDate(rst.getString("endDate"));
				project.setExplain(rst.getString("explain"));
                project.setTeam(rst.getString("team"));
				project.setConfirmedBy(rst.getString("confirmedBy"));
                project.setProd_id(rst.getString("prod_id"));
				project.setChargeBy(rst.getString("chargeBy"));
				project.setBugNum(rst.getInt("bugNum"));
				list.add(project);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	//---------------------------------------------------------------------------------
	public ArrayList<ProjectBean> findMyConfirmedProject(String id){
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		String sql="select * from project where confirmedBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				ProjectBean project = new ProjectBean();
				project.setId(rst.getString("id"));
				project.setName(rst.getString("name"));
				project.setStatus(rst.getString("status"));
				project.setCreatedBy(rst.getString("createdBy"));
				project.setCreatedDate(rst.getString("createdDate"));
				project.setEndDate(rst.getString("endDate"));
				project.setExplain(rst.getString("explain"));
                project.setTeam(rst.getString("team"));
				project.setConfirmedBy(rst.getString("confirmedBy"));
                project.setProd_id(rst.getString("prod_id"));
				project.setChargeBy(rst.getString("chargeBy"));
				project.setBugNum(rst.getInt("bugNum"));
				list.add(project);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	//----------------------------------------------------------------------------------
	public ArrayList<ProjectBean> findMyChargeProject(String id){
		ArrayList<ProjectBean> list = new ArrayList<ProjectBean>();
		String sql="select * from project where chargeBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				ProjectBean project = new ProjectBean();
				project.setId(rst.getString("id"));
				project.setName(rst.getString("name"));
				project.setStatus(rst.getString("status"));
				project.setCreatedBy(rst.getString("createdBy"));
				project.setCreatedDate(rst.getString("createdDate"));
				project.setEndDate(rst.getString("endDate"));
				project.setExplain(rst.getString("explain"));
                project.setTeam(rst.getString("team"));
				project.setConfirmedBy(rst.getString("confirmedBy"));
                project.setProd_id(rst.getString("prod_id"));
				project.setChargeBy(rst.getString("chargeBy"));
				project.setBugNum(rst.getInt("bugNum"));
				list.add(project);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
    //通过id获取 项目信息
	public ProjectBean getProject(String id) {
		String sql = "SELECT * FROM project where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			ProjectBean project = new ProjectBean();
			while (rst.next()) {
				project.setId(rst.getString("id"));
				project.setName(rst.getString("name"));
				project.setStatus(rst.getString("status"));
				project.setCreatedBy(rst.getString("createdBy"));
				project.setCreatedDate(rst.getString("createdDate"));
				project.setEndDate(rst.getString("endDate"));
				project.setExplain(rst.getString("explain"));
				project.setTeam(rst.getString("team"));
				project.setConfirmedBy(rst.getString("confirmedBy"));
				project.setProd_id(rst.getString("prod_id"));
				project.setChargeBy(rst.getString("chargeBy"));
				project.setBugNum(rst.getInt("bugNum"));
			}
			return project;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}

	
	public boolean addProject(ProjectBean project) {
		
		String sql = "INSERT INTO project(id,name,status,createdBy,createdDate,endDate,`explain`,team,confirmedBy,prod_id,chargeBy,bugNum)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, project.getId());
			pstmt.setString(2, project.getName());
			pstmt.setString(3, project.getStatus());
			pstmt.setString(4, project.getCreatedBy());
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(7, project.getExplain());
			pstmt.setString(8, project.getTeam());
			pstmt.setString(9, project.getConfirmedBy());
			pstmt.setString(10, project.getChargeBy());
			pstmt.setString(11, project.getProd_id());
			pstmt.setInt(12, 0);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	
	public boolean deleteProject(String id) {
		String sql = "DELETE FROM project where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	//更新产品信息
	/*
	 * 7/10      删除修改id,创建者,创建时间等功能    lk
	 * 
	 * */
	public boolean updateProject(ProjectBean project) {
		
		String sql = "update project set id=?,name=?,status=?,endDate=?,`explain`=?,team=?,confirmedBy=?,chargeBy=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, project.getId());
			pstmt.setString(2, project.getName());
			pstmt.setString(3, project.getStatus());
			pstmt.setString(4, project.getEndDate());
			pstmt.setString(5, project.getExplain());
			pstmt.setString(6, project.getTeam());
			pstmt.setString(7, project.getConfirmedBy());
			pstmt.setString(8, project.getChargeBy());
			pstmt.setString(9, project.getId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

}
