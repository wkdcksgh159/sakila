package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CountryDao {
	//Country의 목록을 출력하는 메소드 (데이터타입 List<Country> 매개변수 currentPage)
	public List<Country> selectCountryList(int currentPage){
		//객체 선언
		List<Country> list = new ArrayList<Country>();
		//db정보를 저장할 변수 선언
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//페이지당 보여줄 개수 제한 지정 (바꾸지않고 고정시킬값이라 final 지정
		final int rowPerPage = 10;
		//페이지당 보여줄항목의 시작
		int beginRow = (currentPage-1)*rowPerPage;
		//쿼리정보입력 (테이블 country의 모든값을 불러오고 새로 저장한값을 보기위해 country_id를 기준으로 내림차순으로 출력하고 페이지당 보여줄값지정
		String sql = "SELECT country_id, country, last_update FROM country ORDER BY country_id DESC LIMIT ?,?";
		//실행
		try {
			//db 정보 입력
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			//Country.java에 만들어둔 오브젝트에 컬럼값 저장
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				c.setLastUpdate(rs.getString("last_update"));
				list.add(c);
			}
		//예외처리
		} catch(Exception e) {
			e.printStackTrace();
		//작업이 끝나고 db 연결 끊기
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		//list값 반환
		return list;
	}
	
	//값을 입력받을 메소드생성 반환타입 void Country 객체에 값을 저장하기위해 매개변수(Country 객체타입, 객체변수)
	public void insertCountry(Country country) {
		//db정보를 저장할 변수 선언
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리정보 입력 country 테이블의 country, last_update 컬럼값 입력 (last_update 는 현재 날짜)
		String sql = "INSERT INTO country (country, last_update) VALUES(?, now())";
		//실행
		try {
			//db정보 입력
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			stmt.executeUpdate();
		//예외처리
		} catch(Exception e) {
			e.printStackTrace();
		//작업이끝나면 db연결끊기	
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return;
	}
	
	//테이블의 값의 총 개수를 가져오는 메소드 생성
	public int selectCount() {
		//가져온 값의 총 개수를 저장할 변수 count 생성
		int count = 0;
		//db정보를 저장할 변수 생성
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//쿼리정보 입력 (테이블의 모든 행의 개수를 불러옴)
		String sql = "SELECT COUNT(*) FROM country";
		//실행
		try {
			//db정보 입력
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			//COUNT(*) 의 값을 변수 count 에 저장
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		//예외처리
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		//count 값 반환
		return count;
	}
}
