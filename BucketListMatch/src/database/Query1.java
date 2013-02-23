package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of the Query instance.
 * @author Everly Okorji
 *
 */
class Query1 implements Query {

	String query;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Query1() {
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
				return 0;
			} else {
				query = ("SELECT FirstName FROM CUSTOMER WHERE Username = ?;");
	            pstmt = DB.con.prepareStatement(query);
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					return 1;
				} else {
					return 2;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
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
			
			for (int i = 0; i < DB.USER_ATTRIBUTE_LENGTH; i++) {
				user[i] = rs.getString(i+1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}

	// TODO Implement methods HERE

}
