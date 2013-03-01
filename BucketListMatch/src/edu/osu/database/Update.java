package edu.osu.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is an implementation of the IUpdate instance
 * @author Everly Okorji
 *
 */
class Update implements IUpdate {

	String sqlstmt;
	PreparedStatement pstmt;
	
	Update() {
		sqlstmt = null;
		pstmt = null;
	}

	public int addUser(String[] user) {
		
		try {

			sqlstmt = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = DB.con.prepareStatement(sqlstmt);
			sqlstmt = null;

			for (int i = 0; i < DB.USER_ATTRIBUTE_LENGTH; i++) {
				if (i == 2) {
					
					File file = new File(user[i]);
		            FileInputStream fis = new FileInputStream(file);
		            int len = (int)file.length();
		            
		            // Method used to insert a stream of bytes
		            pstmt.setBinaryStream(i+1, fis, len); 
		            
				} else {
					pstmt.setString(i+1, user[i]);
				}
			}
			
			pstmt.executeUpdate();
			return 0;
		} catch (SQLException e1) {
			return 1;
		} catch (FileNotFoundException e) {
			return 2;
		}
	
	}

	// TODO Test
	public int changeUserProfilePhoto(String username, String location) {
		
		try {
			
			sqlstmt = "update CUSTOMER set ProfilePicture = ? where Username = ?";
			pstmt = DB.con.prepareStatement(sqlstmt);
			sqlstmt = null;
			
			File file = new File(location);
            FileInputStream fis = new FileInputStream(file);
            int len = (int)file.length();
            
            pstmt.setBinaryStream(1, fis, len);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			
			return 0;
		} catch (SQLException e) {
			return 1;
		} catch (FileNotFoundException e) {
			return 2;
		}
	}

	public void updateUserLocation(String username, String city, String state,
			String country) {
		// TODO Auto-generated method stub
		
	}

	public void updateUserDescription(String username, String description) {
		// TODO Auto-generated method stub
		
	}

	public void addBucketListBook(String username, String[] textInfo,
			int[] numInfo, boolean privacy) {
		// TODO Auto-generated method stub
		
	}
	
	// TODO Necessary?
	int update(String sqlstmt, int vars, String[] values) {
		
		try {
			
			pstmt = DB.con.prepareStatement(sqlstmt);
			for (int i = 1; i <= vars; i++) {
				pstmt.setString(i, values[i-1]);
			}
			pstmt.executeUpdate();
			return 0;
		} catch (SQLException e) {
			DB.err = e.getMessage();
			return 1;
		}
		
	}
	void clearStmt(String s) {
		s = null;
	}
	
}
