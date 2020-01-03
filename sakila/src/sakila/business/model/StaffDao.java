package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.db.DBHelper;

public class StaffDao {
	private Staff staff;
	public List<Staff> StaffSelectListAll(Connection conn) {
		System.out.println("StaffSelectListAll Dao 실행 확인! ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		List<Staff> list = new ArrayList<Staff>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select sf.staff_id as staffId, sf.first_name as firstName, sf.last_name as lastName, sf.email as eMail, a.address as address, sf.username as userName " + 
				"from staff sf " + 
				"inner join address a on sf.address_id = a.address_id";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				staff = new Staff();
				staff.setStaffId(rs.getInt("staffId"));
				staff.setFirstName(rs.getString("firstName"));
				staff.setLastName(rs.getString("lastName"));
				staff.seteMail(rs.getString("eMail"));
				staff.setAddress(new Address());
				staff.getAddress().setAddress(rs.getString("address"));
				staff.setUserName(rs.getString("userName"));
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
