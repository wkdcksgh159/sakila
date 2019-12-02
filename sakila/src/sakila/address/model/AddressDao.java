package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class AddressDao {
	
	public List<Address> selectJoinList(){
		System.out.println("join method 도착");
		List<Address> list = new ArrayList<Address>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT ad.address_id, ad.address, ad.address2, ad.district, ad.city_id, ad.postal_code, ad.phone, ct.city, c.country_id, c.country, ad.last_update FROM address ad INNER JOIN city ct INNER JOIN country c ON ad.city_id=ct.city_id and ct.country_id=c.country_id ORDER BY ad.address_id DESC limit 100";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Address address = new Address();
				address.setAddressId(rs.getInt("ad.address_id"));
				address.setAddress(rs.getString("ad.address"));
				address.setAddress2(rs.getString("ad.address2"));
				address.setDistrict(rs.getString("ad.district"));
				address.setCity(new City());
				address.getCity().setCityId(rs.getInt("ad.city_id"));
				address.setPostalCode(rs.getString("ad.postal_code"));
				address.setPhone(rs.getString("ad.phone"));
				address.getCity().setCity(rs.getString("ct.city"));
				address.setCountry(new Country());
				address.getCountry().setCountryId(rs.getInt("c.country_id"));
				address.getCountry().setCountry(rs.getString("c.country"));
				address.setLastUpdate(rs.getString("ad.last_update"));
				list.add(address);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
			System.out.println("join 메소드 종료");
		}
		return list;
	}
	
	public int insertAddress(Connection conn, Address address) throws SQLException {
		System.out.println(address);
		System.out.println("method 도착");
		//Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int addressId = 0;
		String sql = "insert into "+""
		+ "address(address,address2,district,city_id,postal_code,phone,last_update)"
		+ " value(?,?,?,?,?,?,now())";
		
		
			//conn = DBHelper.getConnection();//RETURN_GENERATED_KEYS>>기본키 리턴
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//키값을 리턴받는다
				stmt.setString(1, address.getAddress());
				stmt.setString(2, address.getAddress2());
				stmt.setString(3, address.getDistrict());
				stmt.setInt(4,address.getCity().getCityId());
				stmt.setString(5, address.getPostalCode());
				stmt.setString(6, address.getPhone());
				System.out.println(stmt);
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();//리턴값을 받는다,ResultSet 타입
			if(rs.next()) {
				addressId = rs.getInt(1);
			}
		return addressId;
	}
	
	public int selectAddressCount() {
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from address";
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
