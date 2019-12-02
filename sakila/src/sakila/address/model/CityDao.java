package sakila.address.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CityDao {
	public List<City> selectCityByCountry(int countryId){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select city_id,city from city where country_id=?";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, countryId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCity(rs.getString("city"));
				city.setCityId(rs.getInt("city_id"));
				System.out.println("DAO City:>>"+city);
				list.add(city);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into city(country_id,city) value(?,?)";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, city.getCountry().getCountryId());
			stmt.setString(2, city.getCity());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
	}
	public int selectCityCount() {
		int count = 0;
		Connection conn=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from city";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
	public List<City> selectCityList(int currentPage){
		System.out.println("method도착");
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		System.out.println("currentPage::"+currentPage);
		int rowPerPage = 10;
		int beginRow = (currentPage-1)*rowPerPage;
		String sql = "select ci.city_id,ci.city,ci.last_update,ci.country_id,co.country from city ci inner join Country co on ci.country_id=co.country_id ORDER BY ci.city_id DESC limit ?,?";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			System.out.println("rs:>>"+rs);
			while(rs.next()) {
				City city = new City();
				
				city.setCityId(rs.getInt("ci.city_id"));
				city.setCity(rs.getString("ci.city"));
				System.out.println("city::"+city.getCity());
			
				city.setCountry(new Country());
				city.getCountry().setCountry(rs.getString("co.country"));
				city.getCountry().setCountryId(rs.getInt("ci.country_id"));
				System.out.println("country:"+city.getCountry());
				city.setLastUpdate(rs.getString("ci.last_update"));
				list.add(city);
				System.out.println("list"+list);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
}
