package edu.osu.database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An implementation of the IQuery instance.
 * @author Everly Okorji
 *
 */
class Query implements IQuery {
	
	Query() {
		
	}
	
	public int validateUser(String username, String password) {
		
		String sqlstmt;
		PreparedStatement pstmt;
		ResultSet rs;
		
        try {
        	
        	sqlstmt = ("SELECT FirstName FROM CUSTOMER WHERE Username = ? AND Password = ?;");
            pstmt = DB.con.prepareStatement(sqlstmt);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				rs.close();
				return 0;
			} else {
				sqlstmt = ("SELECT FirstName FROM CUSTOMER WHERE Username = ?;");
	            pstmt = DB.con.prepareStatement(sqlstmt);
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

	public String[] fetchUserDetails(String username, String password) {

		String sqlstmt;
		PreparedStatement pstmt;
		ResultSet rs;
		String[] user = new String [Enum.CUSTOMER_LENGTH];
		
		try {
			sqlstmt = ("SELECT * FROM CUSTOMER WHERE Username = ? AND Password = ?;");
            pstmt = DB.con.prepareStatement(sqlstmt);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			rs.next();
			
			for (int i = 0; i <Enum.CUSTOMER_LENGTH; i++) {
				user[i] = rs.getString(i+1);
			}
			
			rs.close();
		} catch (SQLException e) {
			DB.err = e.getMessage();
			return null;
		}
		
		return user;
	}


}
