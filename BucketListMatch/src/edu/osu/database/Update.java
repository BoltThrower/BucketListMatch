package edu.osu.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This is an implementation of the IUpdate instance
 * 
 * @author Everly Okorji
 * 
 */
class Update implements IUpdate {

	Update() {

	}

	public int addUser(String[] user) {

		String sqlstmt = "insert into CUSTOMER (Username, Password, FirstName, "
				+ "LastName, DateOfBirth, State, Country, Email) values (?, ?, ?, ?, ?, ?, ?, ?);";

		String[] values = { user[Enum.C_USERNAME - 1], user[Enum.C_PASS - 1],
				user[Enum.C_FIRST - 1], user[Enum.C_LAST - 1],
				user[Enum.C_DOB - 1], user[Enum.C_STATE - 1],
				user[Enum.C_COUNTRY - 1], user[Enum.C_EMAIL - 1] };

		int temp_result = update(sqlstmt, values, false, -1);

		if (temp_result != 0)
			return temp_result;

		if (user[Enum.C_PROFILE_PIC - 1] != null)
			temp_result = updateUserItem(Enum.UPDATE_PHOTO,
					user[Enum.C_USERNAME - 1], user[Enum.C_PROFILE_PIC - 1],
					null, null);

		if (temp_result != 0)
			return temp_result;

		if (user[Enum.C_DESCR - 1] != null)
			temp_result = updateUserItem(Enum.UPDATE_DESCRIPTION,
					user[Enum.C_USERNAME - 1], user[Enum.C_DESCR - 1], null,
					null);

		if (temp_result != 0)
			return temp_result;

		if (user[Enum.C_CITY - 1] != null)
			temp_result = updateUserItem(Enum.UPDATE_LOCATION,
					user[Enum.C_USERNAME - 1], user[Enum.C_CITY - 1],
					user[Enum.C_STATE - 1], user[Enum.C_COUNTRY - 1]);

		if (temp_result != 0)
			return temp_result;

		if (user[Enum.C_ZIP - 1] != null)
			temp_result = updateUserItem(Enum.UPDATE_ZIPCODE,
					user[Enum.C_USERNAME - 1], user[Enum.C_ZIP - 1], null, null);

		if (temp_result != 0)
			return temp_result;

		if (user[Enum.C_PHONE - 1] != null)
			temp_result = updateUserItem(Enum.UPDATE_PHONE,
					user[Enum.C_USERNAME - 1], user[Enum.C_PHONE - 1], null,
					null);

		return temp_result;
	}

	/*
	 * 
	 * public int changeUserProfilePhoto(String username, String location) {
	 * 
	 * String sql =
	 * "update CUSTOMER set ProfilePicture = ? where Username = ?;"; String[]
	 * values = {location, username}; return update(sql, values, true, 1); }
	 * 
	 * public int updateUserLocation(String username, String city, String state,
	 * String country) { String sql =
	 * "update CUSTOMER set City = ?, State = ?, Country = ? where Username = ?;"
	 * ; String[] values = {city, state, country, username}; return update (sql,
	 * values, false, -1); }
	 * 
	 * public int updateUserDescription(String username, String description) {
	 * String sql = "update CUSTOMER set Description = ? where Username = ?;";
	 * String[] values = {description, username}; return update (sql, values,
	 * false, -1); }
	 * 
	 * public int updateUserZipCode(String username, String zip) { String sql =
	 * "update CUSTOMER set ZipCode = ? where Username = ?;"; String[] values =
	 * {zip, username}; return update (sql, values, false, -1); }
	 * 
	 * public int updateUserPhone(String username, String phone) { String sql =
	 * "update CUSTOMER set Phone = ? where Username = ?;"; String[] values =
	 * {phone, username}; return update (sql, values, false, -1); }
	 */

	public int updateUserItem(int type, String username, String item1,
			String item2, String item3) {

		int result = -1;
		String sql;

		switch (type) {

		case Enum.UPDATE_PHOTO:
			sql = "update CUSTOMER set ProfilePicture = ? where Username = ?;";
			String[] val1 = { item1, username };
			result = update(sql, val1, true, 1);
			break;

		case Enum.UPDATE_LOCATION:
			sql = "update CUSTOMER set City = ?, State = ?, Country = ? where Username = ?;";
			String[] val2 = { item1, item2, item3, username };
			result = update(sql, val2, false, -1);
			break;

		case Enum.UPDATE_DESCRIPTION:
			sql = "update CUSTOMER set Description = ? where Username = ?;";
			String[] val3 = { item1, username };
			result = update(sql, val3, false, -1);
			break;

		case Enum.UPDATE_ZIPCODE:
			sql = "update CUSTOMER set ZipCode = ? where Username = ?;";
			String[] val4 = { item1, username };
			result = update(sql, val4, false, -1);
			break;

		case Enum.UPDATE_PHONE:
			sql = "update CUSTOMER set Phone = ? where Username = ?;";
			String[] val5 = { item1, username };
			result = update(sql, val5, false, -1);
			break;

		default:
			// TODO
			break;

		}

		return result;

	}

	public int addDreamBook(String username, String[] info, boolean privacy) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateDreamBookItem(int type, String username, String item1,
			String item2) {
		// TODO Auto-generated method stub
		return 0;
	}

	// HELPER METHODS

	private int update(String sqlstmt, String[] vars, boolean hasFile,
			int fileIndex) {

		PreparedStatement pstmt;
		try {

			pstmt = DB.con.prepareStatement(sqlstmt);

			for (int i = 1; i <= vars.length; i++) {

				if (hasFile && i == fileIndex) {
					File file = new File(vars[i - 1]);
					FileInputStream fis = new FileInputStream(file);
					int len = (int) file.length();
					pstmt.setBinaryStream(fileIndex, fis, len);

				} else {
					pstmt.setString(i, vars[i - 1]);
				}
			}

			pstmt.executeUpdate();
			return 0;

		} catch (SQLException e) {
			return 1;
		} catch (FileNotFoundException e) {
			return 2;
		}
	}

}
