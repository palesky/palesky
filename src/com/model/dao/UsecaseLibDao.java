package com.model.dao;

import java.sql.Connection;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.bean.UsecaseLibBean;
/**
 * 未测试
 * @author xj
 * 7/10 第一次测试 lk 
 *
 */
public class UsecaseLibDao extends BaseDao{
	
	public ArrayList<UsecaseLibBean> findAllUsecaseLib() {
		ArrayList<UsecaseLibBean> list = new ArrayList<UsecaseLibBean>();
		String sql = "SELECT * FROM usecaseLib ";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();
			while (rst.next()) {
				UsecaseLibBean usecaseLib = new UsecaseLibBean();
				usecaseLib.setId(rst.getString("id"));
				usecaseLib.setUsecaseLib_type(rst.getString("usecaseLibId"));
				usecaseLib.setCreatedBy(rst.getString("CreatedBy"));
				usecaseLib.setCreatedDate(rst.getString("createdDate"));
				list.add(usecaseLib);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean addUsecaseLib(UsecaseLibBean usecaseLib) {
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
		String dateNowStr =sdf.format(d);
		
		String sql = "INSERT INTO usecaseLib(id,usecaseLib_type,createdBy,createdDate)VALUES(?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, usecaseLib.getId());
			pstmt.setString(2, usecaseLib.getUsecaseLib_type());
			pstmt.setString(3, usecaseLib.getCreatedBy());
			pstmt.setString(4, dateNowStr);
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	
	public boolean deleteUsecaseLib(String id) {
		String sql = "DELETE FROM usecaseLib where id=?";
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
	
	//用例类库的  元素 因为基本不改变的原因 
	//这个更新的功能基本用不上
	public boolean updateUsecaseLib(UsecaseLibBean usecaseLib) {
		String sql = "update usecaseLib set id=?,usecaseLib_type=? where id=?";
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, usecaseLib.getId());
			pstmt.setString(2, usecaseLib.getUsecaseLib_type());
			pstmt.setString(3, usecaseLib.getId());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
}
