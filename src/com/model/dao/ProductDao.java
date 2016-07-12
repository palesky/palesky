package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.model.bean.ProductBean;
import com.model.bean.ProjectBean;
import com.sun.org.apache.xerces.internal.util.Status;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 以此为准：全部未测试
 * @author xj
 *
 * 7/11增加 方法 
 */
public class ProductDao extends BaseDao{
	
	public int FindProductBugNum(String id) throws SQLException{
    	int bugNum=0;
    	String sql ="update product set bugNum = "+"(select sum(bugNum) from project where prod_id =? )"+"where id=?";
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

	public ArrayList<ProjectBean> findProjectByProduct(String id){
		ArrayList<ProjectBean> list= new ArrayList<ProjectBean>();
		String sql="select * from project where prod_id = ?";
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
				
				System.out.println(project.toString());
				list.add(project);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
		
	}
	
	//------------------------------------------------------------------
		public ArrayList<ProductBean> SearchProduct(String key) throws ParseException {
			ArrayList<ProductBean> list = new ArrayList<ProductBean>();
			String sql = "SELECT * FROM product where id like ? or name like ? or status like ? or pro_type like ?";
			try (Connection conn = dataSource.getConnection(); 
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1,"%"+key+"%");
				pstmt.setString(2,"%"+key+"%");
				pstmt.setString(3,"%"+key+"%");
				pstmt.setString(4,"%"+key+"%");
				ResultSet rst = pstmt.executeQuery();
				while (rst.next()) {
					java.text.SimpleDateFormat   formatter   = new   SimpleDateFormat( "yyyy-MM-dd ");
					String statu=rst.getString("status");
					String time =rst.getString("endDate");
					Date endTime = formatter.parse(time);
					String a="进行中";
					Date now=new java.sql.Date(new java.util.Date().getTime());
					if(statu.equals(a) && endTime.before(now) )
					{
						statu="已延期";
					}
					ProductBean product = new ProductBean();
					product.setId(rst.getString("id"));
					product.setName(rst.getString("name"));
					product.setStatus(rst.getString("status"));
					product.setPro_type(rst.getString("pro_type"));
					product.setCreatedBy(rst.getString("createdBy"));
					product.setCreatedDate(rst.getString("createdDate"));
					product.setEndDate(rst.getString("endDate"));
					product.setExplain(rst.getString("explain"));
					product.setConfirmedBy(rst.getString("confirmedBy"));
					product.setChargeBy(rst.getString("chargeBy"));
					product.setBugNum(rst.getInt("bugNum"));
					list.add(product);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		
	//------------------------------------------------------------------
		public ArrayList<ProductBean> findAllProduct() throws ParseException {
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		String sql = "SELECT * FROM product ";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rst = pstmt.executeQuery();

			while (rst.next()) {
				java.text.SimpleDateFormat   formatter   = new   SimpleDateFormat( "yyyy-MM-dd ");
				ProductBean product = new ProductBean();
				String statu=rst.getString("status");
				String time =rst.getString("endDate");
				Date endTime = formatter.parse(time);
				String a="进行中";
				Date now=new java.sql.Date(new java.util.Date().getTime());
				if(statu.equals(a) && endTime.before(now) )
				{
					statu="已延期";
				}
				product.setId(rst.getString("id"));
				product.setName(rst.getString("name"));
				product.setStatus(statu);
				product.setPro_type(rst.getString("pro_type"));
				product.setCreatedBy(rst.getString("createdBy"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setEndDate(rst.getString("endDate"));
				product.setExplain(rst.getString("explain"));
				product.setConfirmedBy(rst.getString("confirmedBy"));
				product.setChargeBy(rst.getString("chargeBy"));
				product.setBugNum(rst.getInt("bugNum"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<ProductBean> findMyCreatedProduct(String id){
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		String sql="select * from product where createdBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				ProductBean product = new ProductBean();
				product.setId(rst.getString("id"));
				product.setName(rst.getString("name"));
				product.setStatus(rst.getString("status"));
				product.setPro_type(rst.getString("pro_type"));
				product.setCreatedBy(rst.getString("createdBy"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setEndDate(rst.getString("endDate"));
				product.setExplain(rst.getString("explain"));
				product.setConfirmedBy(rst.getString("confirmedBy"));
				product.setChargeBy(rst.getString("chargeBy"));
				product.setBugNum(rst.getInt("bugNum"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public ArrayList<ProductBean> findMyConfirmedProduct(String id){
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		String sql="select * from product where confirmedBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				ProductBean product = new ProductBean();
				product.setId(rst.getString("id"));
				product.setName(rst.getString("name"));
				product.setStatus(rst.getString("status"));
				product.setPro_type(rst.getString("pro_type"));
				product.setCreatedBy(rst.getString("createdBy"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setEndDate(rst.getString("endDate"));
				product.setExplain(rst.getString("explain"));
				product.setConfirmedBy(rst.getString("confirmedBy"));
				product.setChargeBy(rst.getString("chargeBy"));
				product.setBugNum(rst.getInt("bugNum"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public ArrayList<ProductBean> findMyChargeProduct(String id){
		ArrayList<ProductBean> list = new ArrayList<ProductBean>();
		String sql="select * from product where chargeBy=?";
		try(Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rst =pstmt.executeQuery();
			while(rst.next()){
				ProductBean product = new ProductBean();
				product.setId(rst.getString("id"));
				product.setName(rst.getString("name"));
				product.setStatus(rst.getString("status"));
				product.setPro_type(rst.getString("pro_type"));
				product.setCreatedBy(rst.getString("createdBy"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setEndDate(rst.getString("endDate"));
				product.setExplain(rst.getString("explain"));
				product.setConfirmedBy(rst.getString("confirmedBy"));
				product.setChargeBy(rst.getString("chargeBy"));
				product.setBugNum(rst.getInt("bugNum"));
				list.add(product);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	
	public ProductBean getProduct(String id) {
		String sql = "SELECT * FROM product where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			ResultSet rst = pstmt.executeQuery();
			ProductBean product = new ProductBean();
			while (rst.next()) {
				product.setId(rst.getString("id"));
				product.setName(rst.getString("name"));
				product.setStatus(rst.getString("status"));
				product.setPro_type(rst.getString("pro_type"));
				product.setCreatedBy(rst.getString("createdBy"));
				product.setCreatedDate(rst.getString("createdDate"));
				product.setEndDate(rst.getString("endDate"));
				product.setExplain(rst.getString("explain"));
				product.setConfirmedBy(rst.getString("confirmedBy"));
				product.setChargeBy(rst.getString("chargeBy"));
				product.setBugNum(rst.getInt("bugNum"));
			}
			return product;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("return null");
			return null;
		}
	}
	
	
	public boolean addProduct(ProductBean product) {
		String sql = "INSERT INTO product(id,name,status,pro_type,createdBy,createdDate,endDate,`explain`,confirmedBy,chargeBy,bugNum)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getStatus());
			pstmt.setString(4, product.getPro_type());
			pstmt.setString(5, product.getCreatedBy());
			pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
			pstmt.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
//			pstmt.setString(7, product.getEndDate());
			pstmt.setString(8, product.getExplain());
			pstmt.setString(9, product.getConfirmedBy());
			pstmt.setString(10, product.getChargeBy());
			pstmt.setInt(11, product.getBugNum());
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
	
	
	public boolean deleteProduct(String id) {
		String sql = "DELETE FROM product where id=?";
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
	
	
	public boolean updateProduct(ProductBean product) {
		String sql = "update product set id=?,name=?,status=?,pro_type=?,endDate=?,`explain`=?,confirmedBy=?,chargeBy=? where id=?";
		try (Connection conn = dataSource.getConnection(); 
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setString(3, product.getStatus());
			pstmt.setString(4, product.getPro_type());
			pstmt.setString(5, product.getEndDate());
			pstmt.setString(6, product.getExplain());
			pstmt.setString(7, product.getConfirmedBy());
			pstmt.setString(8, product.getChargeBy());
			pstmt.setString(9, product.getId());
						
			pstmt.executeUpdate();
			return true;
		} catch (SQLException se) {
			se.printStackTrace();
			return false;
		}
	}
}
