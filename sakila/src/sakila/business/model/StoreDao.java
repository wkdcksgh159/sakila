package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sakila.address.model.Country;
import sakila.db.DBHelper;

public class StoreDao {
	private Store store;
	public List<Map<String, Object>> StoreSelectListAll(Connection conn) {
		System.out.println("StoreSelectListAll Dao 실행 확인! ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select st.store_id, st.manager_staff_id, concat(sf.first_name,' ',sf.last_name) 'staff_name', st.address_id, ad.address, st.last_update " + 
				"from store st inner join staff sf inner join address ad " + 
				"on st.manager_staff_id = sf.staff_id and st.address_id = ad.address_id order by st.store_id desc";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("storeId", rs.getInt("st.store_id"));
				map.put("managerStaffId", rs.getInt("st.manager_staff_id"));
				map.put("staffName", rs.getString("staff_name"));
				map.put("addressId", rs.getInt("st.address_id"));
				map.put("address", rs.getString("ad.address"));
				map.put("lastUpdate", rs.getString("st.last_update"));
				list.add(map);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	public int selectStoreCount() {
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from store";
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
