package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.db.DBHelper;

public class StaffDao {
	private Staff staff;
	public List<Staff> StoreSelectListAll(Connection conn) {
		System.out.println("StoreSelectListAll Dao 실행 확인! ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		List<Staff> list = new ArrayList<Staff>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select staff_id,"
				+ " first_name,"
				+ " last_name,"
				+ " address_id,"
				+ " email,"
				+ " store_id,"
				+ " active,"
				+ " username,"
				+ " password,"
				+ " last_update"
				+ " from staff order by staff_id";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				staff = new Staff();
				staff.setStaffId(rs.getInt("staff_id"));
				staff.setFirstName(rs.getString("first_name"));
				staff.setLastName(rs.getString("last_name"));
				staff.setAddressId(rs.getInt("address_id"));
				staff.seteMail(rs.getString("email"));
				staff.setStoreId(rs.getInt("store_id"));
				staff.setActive(rs.getInt("active"));
				staff.setUserName(rs.getString("username"));
				staff.setPassword(rs.getString("password"));
				staff.setLastUpdate(rs.getString("last_update"));
				list.add(staff);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	public int selectStaffCount() {
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from staff";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
				if(rs.next()) {
					count=rs.getInt(1);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
}
