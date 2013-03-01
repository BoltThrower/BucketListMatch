package edu.osu.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of the Query instance.
 * @author Everly Okorji
 *
 */
class Query implements IQuery {

	String query;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Query() {
		query = null;
		pstmt = null;
		rs = null;
	}
	
	public int validateUser(String username, String password) {
		
        try {
        	query = ("SELECT FirstName FROM CUSTOMER WHERE Username = ? AND Password = ?;");
            pstmt = DB.con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				return 0;
			} else {
				query = ("SELECT FirstName FROM CUSTOMER WHERE Username = ?;");
	            pstmt = DB.con.prepareStatement(query);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					rs.close();
					return 1;
				} else {
					rs.close();
					return 2;
				}
			}
			
			
		} catch (SQLException e) {
			DB.err = e.getMessage();
			return 3;
		}

	}

	// TODO TEST
	public String[] fetchUserDetails(String username, String password) {
		
		String[] user = new String [DB.USER_ATTRIBUTE_LENGTH];
		try {
			query = ("SELECT * FROM CUSTOMER WHERE Username = ? AND Password = ?;");
            pstmt = DB.con.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			rs.next();
			
			for (int i = 0; i < DB.USER_ATTRIBUTE_LENGTH; i++) {
				user[i] = rs.getString(i+1);
			}
			
			rs.close();
		} catch (SQLException e) {
			DB.err = e.getMessage();
			return null;
		}
		
		return user;
	}

	// TODO Implement methods HERE

}
