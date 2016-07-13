package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//获取时间---自定义时间格式
import java.util.Date;
import java.text.SimpleDateFormat;

import com.model.bean.BugBean;

/**
 * 未测试
 * 
 * @author xj 7/10 第一轮测试 by lk
 */
public class BugDao extends BaseDao {

	public ArrayList<BugBean> findAllBug() {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		String sql = "SELECT * FROM bug ";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
				bug.setTask_testerId(rst.getInt("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param status  待指派 进行中        待审核 已解决 
	 *                            已搁置
	 * @param bugid
	 * @return
	 */
	public boolean changeStatus(String status, String bugid) {
		String sql = "update bug set status=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, status);
			pstmt.setString(2, bugid);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	// 增加 bug时 创建日期为当前日期
	public boolean addBug(BugBean bug) {

		String sql = "INSERT INTO bug(name,status,bug_type,os,browser,foundBy,foundDate,priority,steps,usecaseId,task_testerId,chargeBy)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		String sql2 = "update task bugNum =bugNum+1";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bug.getName());
			pstmt.setString(2, bug.getStatus());
			pstmt.setString(3, bug.getBug_type());
			pstmt.setString(4, bug.getOs());
			pstmt.setString(5, bug.getBrowser());
			pstmt.setString(6, bug.getFoundBy());
			pstmt.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setString(8, bug.getPriority());
			pstmt.setString(9, bug.getSteps());
			pstmt.setString(10, bug.getUsecaseId());
			pstmt.setInt(11, bug.getTask_testerId());
			pstmt.setString(12, bug.getChargeBy());
			pstmt.executeUpdate();

			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	public boolean deleteBug(String id) {
		String sql = "DELETE FROM bug where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	// update --- 有限的数据可以更新
	// 所属用例类型 和 所属测试小组 不可更改
	public boolean updateBug(BugBean bug) {
		String sql = "update bug set name=?,status=?,bug_type=?,os=?,browser=?,priority=?,steps=? ,chargeBy =? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, bug.getName());
			pstmt.setString(2, bug.getStatus());
			pstmt.setString(3, bug.getBug_type());
			pstmt.setString(4, bug.getOs());
			pstmt.setString(5, bug.getBrowser());
			pstmt.setString(6, bug.getPriority());
			pstmt.setString(7, bug.getSteps());
			pstmt.setString(8, bug.getId());
			pstmt.setString(9, bug.getChargeBy());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	/**
	 * 
	 * @param id
	 * @param userid 将任务指派给userid
	 * @return
	 */
	public boolean ChangeMan(String id, String userid) {
		String sql = "update bug set chargeby =? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userid);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}

	}

	public ArrayList<BugBean> searchBugByName(String name) {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		String sql = "select * from bug where name like ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, name);
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
				bug.setTask_testerId(rst.getInt("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public ArrayList<BugBean> searchBugByRole(String role, String id) {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		if (role == "产品经理") {
			String sql = "select * from bug where task_testerId in (select id from task where demand_id in(select id from demand where project_id in (SELECT id from project where prod_id= ?)))";
			try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
					bug.setTask_testerId(rst.getInt("task_testerId"));
					bug.setChargeBy(rst.getString("chargeBy"));
					list.add(bug);
				}

			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		} else if (role == "项目经理") {
			String sql = "select * from bug where task_testerId in (select id from task where demand_id in(select id from demand where project_id=?))";
			try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
					bug.setTask_testerId(rst.getInt("task_testerId"));
					bug.setChargeBy(rst.getString("chargeBy"));
					list.add(bug);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		return list;
	}

	// -----------------------------------------------------------------------------------
	// -----------------------------------------------------------------------------------
	public ArrayList<BugBean> searchBugByStatus(String status) {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		String sql = "select * from bug where status = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, status);
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
				bug.setTask_testerId(rst.getInt("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// ---------------------------------------------------------------------------
	public ArrayList<BugBean> searchMyFoundBug(String id) {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		String sql = "select * from bug where foundBy = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
				bug.setTask_testerId(rst.getInt("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// -----------------------------------------------------------------------------------
	public ArrayList<BugBean> searchMyChargeBug(String id) {
		ArrayList<BugBean> list = new ArrayList<BugBean>();
		String sql = "select * from bug where chargeBy = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
				bug.setTask_testerId(rst.getInt("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
				list.add(bug);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	public BugBean getBug(String id) {
		BugBean bug = new BugBean();
		String sql = "select * from bug where id = ?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			if (rst.next()) {
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
				bug.setTask_testerId(rst.getInt("task_testerId"));
				bug.setChargeBy(rst.getString("chargeBy"));
			}
			return bug;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
