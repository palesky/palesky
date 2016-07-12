package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

import com.model.bean.BugBean;
import com.model.bean.ProductBean;
import com.model.bean.TaskBean;
import com.model.bean.Task_developerBean;
import com.model.bean.Task_testerBean;
import com.model.bean.UsecaseBean;

/**
 * 未测试
 * @author xj
 * task 中共有13项数据
 * 其中  创建者 创建时间 所属产品编号 不能改 -lk
 */
public class TaskDao extends BaseDao{
	
	//查找 一个任务下 所有的测试小组
	public ArrayList<Task_testerBean> findAllTask_tester(String taskId){
		
		ArrayList<Task_testerBean> list = new ArrayList<Task_testerBean>();
		String sql="select * from task_tester where taskId =?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){
				Task_testerBean task = new Task_testerBean();
				task.setId(rst.getString("id"));
				task.setName(rst.getString("name"));
				task.setTaskId(rst.getString("taskId"));
				task.setUserId(rst.getString("userId"));
				task.setCreatedDate(rst.getString("createdDate"));
				task.setBugNum(rst.getInt("bugNum"));
				list.add(task);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//=---------------------------------------------------------------
	//找出  一个task下  所有的开发小组
    public ArrayList<Task_developerBean> findAllTask_developer(String taskId){
		
		ArrayList<Task_developerBean> list = new ArrayList<Task_developerBean>();
		String sql="select * from task_developer where taskId =?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){
				Task_developerBean task = new Task_developerBean();
				task.setId(rst.getString("id"));
				task.setName(rst.getString("name"));
				task.setTaskId(rst.getString("taskId"));
				task.setUserId(rst.getString("userId"));
				task.setCreatedDate(rst.getString("createdDate"));
				list.add(task);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
    
    //---------------------------------------------------------------------------
    //查找所有一个任务下的bug
    public ArrayList<BugBean> findBugByTask(String id){
    	ArrayList<BugBean> list=new ArrayList<BugBean>();
    	String sql="select * from bug where task_testerId in(select id from task_tester where taskId =?)";
    	try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
    		pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				BugBean bug = new BugBean();
				bug.setId(rst.getString("id"));
				bug.setName(rst.getString("name"));
				bug.setStatus(rst.getString("status"));
				bug.setBug_type(rst.getString("bug_type"));
				bug.setOs(rst.getString("os"));
				bug.setBrowser(rst.getString("browser"));
				bug.setFoundBy(rst.getString("foundBy"));
				bug.setFoundDate(rst.getString("foundDate"));
				bug.setPriority(rst.getString("priority"));
				bug.setSteps(rst.getString("steps"));
				bug.setUsecaseId(rst.getString("usecaseId"));
				bug.setTask_testerId(rst.getString("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
    }
    //---------------------------------------------------------------------------
    //通过task 找 用例
    public ArrayList<UsecaseBean> findUsecaseByTask(String taskId) {
		
		ArrayList<UsecaseBean> list = new ArrayList<UsecaseBean>();
		String sql = "SELECT * FROM usecase where id in(select usecaseId from task_usecase where taskId =? )";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, taskId);
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
    //---------------------------------------------------------------------------
    public ArrayList<UsecaseBean> findUsecaseByUser(String userid){
    	ArrayList<UsecaseBean> list = new ArrayList<UsecaseBean>();
    	String sql="select * from usecase where id in(select usecaseId from task_tester where taskId in(select taskId from task_tester where userId =?))";
    	try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userid);
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
  
    //---------------------------------------------------------------------------
    
    //*************    获取 当前任务的bug总数
    //*************
    //-----------------------------------------------------------------------------
    public int FindTaskBugNum(String id) throws SQLException{
    	int bugNum=0;
    	String sql ="update task set bugNum = "+"(select sum(bugNum) from task_tester where taskId =? )"+"where id=?";
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
    
    //--------------------------------------------------------------------------------
    public ArrayList<TaskBean> SearchTask(String key) {
		
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		String sql = "SELECT * FROM task like ? or name like ? or status like ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1,"%"+key+"%");
			pstmt.setString(2,"%"+key+"%");
			pstmt.setString(3,"%"+key+"%");
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
    //--------------------------------------------------------------------------------
	public ArrayList<TaskBean> findAllTask() {
		
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		String sql = "SELECT * FROM task ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
	
	//----------------------------------------------------------------
	//获取任务信息
	public TaskBean getTask(String id){
		String sql = "SELECT * FROM task where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			TaskBean task = new TaskBean();
			while (rst.next()) {
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
			}
			return task;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}
	
	//-------------------------------------------------------------------------------------
	//增加 任务
	public boolean addTask(TaskBean task) {
//		Date d=new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
//		String dateNowStr =sdf.format(d);
		String sql = "INSERT INTO task(id,name,status,createdBy,createdDate,endDate,`explain`,lastEditedBy,lastEditedDate,confirmedBy,demand_id,chargeBy,bugNum)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, task.getId());
			pstmt.setString(2, task.getName());
			pstmt.setString(3, task.getStatus());
			pstmt.setString(4, task.getCreatedBy());
			pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(7, task.getExplain());
			pstmt.setString(8, task.getLastEditedBy());
			pstmt.setString(9, task.getLastEditedDate());
			pstmt.setString(10, task.getConfirmedBy());
			pstmt.setString(11, task.getDemand_id());
			pstmt.setString(12, task.getChargeBy());
			pstmt.setInt(13, task.getBugNum());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	//-----------------------------------------------------------------
	public ArrayList<TaskBean> findMyCreatedTask(String id){
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		String sql="select * from task where createdBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				TaskBean task = new TaskBean();
				task.setId(rst.getString("id"));
				task.setName(rst.getString("name"));
				task.setStatus(rst.getString("status"));
				task.setCreatedBy(rst.getString("createdBy"));
				task.setCreatedDate(rst.getString("createdDate"));
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
	//------------------------------------------------------------------
	public ArrayList<TaskBean> findMyConfirmedTask(String id){
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		String sql="select * from task where confirmedBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				TaskBean task = new TaskBean();
				task.setId(rst.getString("id"));
				task.setName(rst.getString("name"));
				task.setStatus(rst.getString("status"));
				task.setCreatedBy(rst.getString("createdBy"));
				task.setCreatedDate(rst.getString("createdDate"));
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
	//------------------------------------------------------------------
	public ArrayList<TaskBean> findMyChargedTask(String id){
		ArrayList<TaskBean> list = new ArrayList<TaskBean>();
		String sql="select * from task where chargeBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				TaskBean task = new TaskBean();
				task.setId(rst.getString("id"));
				task.setName(rst.getString("name"));
				task.setStatus(rst.getString("status"));
				task.setCreatedBy(rst.getString("createdBy"));
				task.setCreatedDate(rst.getString("createdDate"));
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
	//------------------------------------------------------------------
	public boolean deleteTask(String id) {
		String sql = "DELETE FROM task where id=?";
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
	
	/**
	 * 已测试
	 * 
	 * @param user
	 * @return
	 */
	public boolean updateTask(TaskBean task) {
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String dateNowStr =sdf.format(d);
		String sql = "update task set id=?,name=?,status=?,endDate=?,`explain`=?,lastEditedBy=?,lastEditedDate=?,confirmedBy=?,chargeBy=?,bugNum=? where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, task.getId());
			pstmt.setString(2, task.getName());
			pstmt.setString(3, task.getStatus());
			pstmt.setString(4, task.getEndDate());
			pstmt.setString(5, task.getExplain());
			pstmt.setString(6, task.getLastEditedBy());
			pstmt.setString(7, dateNowStr);//最后编辑时间
			pstmt.setString(8, task.getConfirmedBy());
			pstmt.setString(9, task.getId());
			pstmt.setString(10,task.getChargeBy());
			pstmt.setInt(11, task.getBugNum());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
}
