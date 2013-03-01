package edu.osu.database;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is an implementation of the Update instance
 * @author Everly Okorji
 *
 */
class Update implements IUpdate {

	String query;
	PreparedStatement pstmt;
	
	Update() {
		query = null;
		pstmt = null;
	}

	@Override
	public int addUser(String[] user) {
		
		try {

			query = "INSERT INTO CUSTOMER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			pstmt = DB.con.prepareStatement(query);

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
			
		} catch (SQLException e1) {
			return 1;
		} catch (FileNotFoundException e) {
			return 2;
		}
		
		return 0;
	}
	
}
