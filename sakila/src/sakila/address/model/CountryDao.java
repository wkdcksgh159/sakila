package sakila.address.model;

import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

import java.sql.*;

public class CountryDao {
	public List<Country> selectCountryListAll(){
		List<Country> list = new ArrayList<Country>();
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql="select country,country_id from country order by country_id";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
				while(rs.next()) {
					Country country = new Country();
					country.setCountry(rs.getString("country"));
					country.setCountryId(rs.getInt("country_id"));
					list.add(country);
				}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	public int selectCountryCount() {
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from country";
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
	public List<Country> selectCountryList(){//int currentPage
		List<Country> list = new ArrayList<Country>();
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//int rowPerPage=10;
		//int beginRow = (currentPage-1)*rowPerPage;
		String sql="select * from country order by country_id desc limit 10";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			//stmt.setInt(1, beginRow);
			//stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country country = new Country();
				country.setCountry(rs.getString("country"));
				country.setCountryId(rs.getInt("country_id"));
				country.setLastUpdate(rs.getString("last_update"));
				list.add(country);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	public void insertCountry(Country country) {
		Connection conn=null;
		PreparedStatement stmt = null;
		String sql="insert into country(country, last_update) values(?,now())";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			 stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(null, stmt, conn);	
		}
	}
}
