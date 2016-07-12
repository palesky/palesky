package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.bean.UserBean;

/**
 * findByKeyword 未测试
 * @author xj
 * 
 * 2016/7/10 - 检查 lk
 * 修改   用户注册   用户信息更新   的合理性
 * 修改  list没有add数据   导致模糊查询失败问题
 */
public class UserDao extends BaseDao {
	
	public ArrayList<UserBean> findAllUser() {
		
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		String sql = "SELECT * FROM user ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
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
				System.out.println(UserBean.toString());
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public UserBean getUser(String account) {
		String sql = "SELECT * FROM user where account=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, account);
			ResultSet rst = pstmt.executeQuery();
			UserBean userBean = new UserBean();
			while (rst.next()) {
				userBean.setId(rst.getString("id"));
				userBean.setAccount(rst.getString("account"));
				userBean.setPassword(rst.getString("password"));
				userBean.setRealname(rst.getString("realname"));
				userBean.setGender(rst.getString("gender"));
				userBean.setRole(rst.getString("role"));
				userBean.setEmail(rst.getString("email"));
				userBean.setPhone(rst.getString("phone"));
				userBean.setIp(rst.getString("lastIp"));
				userBean.setLastLogin(rst.getString("lastLogin"));
				userBean.setVisits(rst.getInt("visit"));
				userBean.setPrivilege(rst.getString("privilege"));
				userBean.setBelongTo(rst.getString("belongTo"));
			}
			return userBean;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}

	//管理员增加用户
	//默认设置登录次数为0  用户第一次登录时进入更新资料界面
	//只给出id 账户名   初始密码  职位等几项数据
	public boolean addUser(UserBean user) {
		String sql = "INSERT INTO user(id,account,password,role,visit,privilege,belongTo)VALUES(?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getAccount());
			pstmt.setString(3, user.getPassword());		
			pstmt.setString(4, user.getRole());			
			pstmt.setInt(5,0);
			pstmt.setString(6, user.getPrivilege());
			pstmt.setString(7, user.getBelongTo());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
		
	}

	
	public boolean deleteUser(String id) {
		String sql = "DELETE FROM user where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	public boolean loginUser(String username, String password){
		String sql="select * from user where account =?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			ResultSet rst = pstmt.executeQuery();
			while(rst.next()){				
				String psw=rst.getString("password");
				if(psw.equals(password))
				{
					System.out.println("成功");
					return true;
				}
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("出错");
		}
		return false;
	}
	
	
	public boolean updateUser(UserBean user) {
		String sql = "update user set account=?,realname=?,gender=?,email=?,phone=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getRealname());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	public boolean setNewPassword(String id,String password) {
		String sql = "update user set password=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, password);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}

	/**
	 * 测试未通过，待修改 d.findByKeyword("account", "a")；通过 和 d.findByKeyword("account",
	 * "p")；不通过 预期结果不同
	 * 
	 * 通过关键词进行模糊搜索
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public ArrayList<UserBean> findByKeyword(String key, String value) 
	{
		ArrayList<UserBean> list = new ArrayList<UserBean>();
		String sql = "SELECT *" + " FROM user WHERE ? like ?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, key);
			pstmt.setString(2, "%" + value + "%");
			try (ResultSet rst = pstmt.executeQuery()) {
				while (rst.next()) {
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
					System.out.println(UserBean.toString());
					list.add(UserBean);//7/10新增
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * author xjy
	 * @param role
	 * @return
	 */
	public ArrayList<UserBean> findAllChargedMan(String role){
		ArrayList<UserBean> list =new ArrayList<UserBean>();
		String sql="SELECT * from user where role=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, role);
			try (ResultSet rst = pstmt.executeQuery()) {
				while (rst.next()) {
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
					list.add(UserBean);//7/10新增
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * 审核只能由产品经理和项目经理
	 * author xjy
	 * @return
	 */
	public ArrayList<UserBean> findAllConfirmeddMan(String role){
		ArrayList<UserBean> list =new ArrayList<UserBean>();
		String sql="SELECT * from user where role=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, role);
			try (ResultSet rst = pstmt.executeQuery()) {
				while (rst.next()) {
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
					list.add(UserBean);//7/10新增
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * author xjy
	 * @param role
	 * @return
	 */
	public ArrayList<UserBean> findAllChargedMan(){
		ArrayList<UserBean> list =new ArrayList<UserBean>();
		String sql="SELECT * from user where role='产品经理' or role='项目经理'";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try (ResultSet rst = pstmt.executeQuery()) {
				while (rst.next()) {
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
					list.add(UserBean);//7/10新增
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
			return null;
		}
		return list;
	}
	
	/**
	 * 审核只能由产品经理和项目经理
	 * author xjy
	 * @return
	 */
	public ArrayList<UserBean> findAllConfirmeddMan(){
		return findAllChargedMan();
	}
}
