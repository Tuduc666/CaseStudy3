package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Showing;
import utils.OracleQueries;

public class ShowingDAO {

	public Showing getShowing(String user_id, String property_id) throws IOException, SQLException 	{
		Showing showing = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet result = null;
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.GETSHOWING);
			stmt.setString(1, user_id);
			stmt.setString(2, property_id);
			result = stmt.executeQuery();
			if(result.next()) {
				showing = new Showing();
				showing.setUser_id(result.getInt(1));
				showing.setProperty_id(result.getInt(2));
				showing.setUser_message(result.getString(3));					
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			if(result != null) {
				result.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		
		return showing;
	}
	
	public Boolean addShowing(String user_id, String property_id, String user_message) throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer result = null;          
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.ADDSHOWING);    
			stmt.setString(1, user_id);
			stmt.setString(2, property_id);
			stmt.setString(3, user_message);
			result = stmt.executeUpdate();					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return result>0;                    
	}

	public Boolean updateShowing(String user_id, String property_id, String user_message) throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer result = null;           
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.UPDATESHOWING);    
			stmt.setString(1, user_id);
			stmt.setString(2, property_id);
			stmt.setString(3, user_message);
			stmt.setString(4, user_id);
			stmt.setString(5, property_id);         
			result = stmt.executeUpdate();
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return result > 0;                    
	}


	public boolean deleteShowing(String user_id, String property_id) throws IOException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		Integer result = null;           
		
		try {
			conn = OracleConnection.getConnection();
			stmt = conn.prepareStatement(OracleQueries.DELETESHOWING);    
			stmt.setString(1, user_id);
			stmt.setString(2, property_id);         
			result = stmt.executeUpdate();
						
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}
		return result > 0;                    
	}
}
