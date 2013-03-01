package edu.osu.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This is an implementation of the Update instance
 * @author Everly Okorji
 *
 */
class Update implements IUpdate {

	String query;
	PreparedStatement pstmt;
	ResultSet rs;
	
	Update() {
		query = null;
		pstmt = null;
		rs = null;
	}

	@Override
	public boolean addUser(String[] user) {
		
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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;		// TODO
	}
	
}
