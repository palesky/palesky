package com.model.dao;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class BaseDao {
	DataSource dataSource;

	public BaseDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/paleskypms");
		} catch (NamingException ne) {
			System.out.println("Exception:" + ne);
		}
	}

	public Connection getConnection() throws Exception {
		return dataSource.getConnection();
	}
}