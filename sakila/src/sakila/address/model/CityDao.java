package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CityDao {
	//City의 전체 목록과 country의 나라의 이름, id값을 출력하는 메소드 
	public List<City> selectCityList(int currentPage){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int rowPerPage = 10;
		int beginRow = (currentPage - 1) * rowPerPage;
		String sql = "SELECT ci.city_id, ci.city, co.country_id, co.country, ci.last_update FROM city ci INNER JOIN country co ON ci.country_id = co.country_id ORDER BY ci.city_id DESC LIMIT ?,?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City c = new City();
				c.setCityId(rs.getInt("ci.city_id"));
				c.setCity(rs.getString("ci.city"));
				c.setCountry(new Country());
				c.getCountry().setCountryId(rs.getInt("co.country_id"));
				c.getCountry().setCountry(rs.getString("co.country"));
				c.setLastUpdate(rs.getString("ci.last_update"));
				list.add(c);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
}
