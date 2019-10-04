package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CountryDao {
	//Country�� ����� ����ϴ� �޼ҵ� (������Ÿ�� List<Country> �Ű����� currentPage)
	public List<Country> selectCountryList(int currentPage){
		//��ü ����
		List<Country> list = new ArrayList<Country>();
		//db������ ������ ���� ����
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//�������� ������ ���� ���� ���� (�ٲ����ʰ� ������ų���̶� final ����
		final int rowPerPage = 10;
		//�������� �������׸��� ����
		int beginRow = (currentPage-1)*rowPerPage;
		//���������Է� (���̺� country�� ��簪�� �ҷ����� ���� �����Ѱ��� �������� country_id�� �������� ������������ ����ϰ� �������� �����ٰ�����
		String sql = "SELECT country_id, country, last_update FROM country ORDER BY country_id DESC LIMIT ?,?";
		//����
		try {
			//db ���� �Է�
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			//Country.java�� ������ ������Ʈ�� �÷��� ����
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				c.setLastUpdate(rs.getString("last_update"));
				list.add(c);
			}
		//����ó��
		} catch(Exception e) {
			e.printStackTrace();
		//�۾��� ������ db ���� ����
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		//list�� ��ȯ
		return list;
	}
	
	//���� �Է¹��� �޼ҵ���� ��ȯŸ�� void Country ��ü�� ���� �����ϱ����� �Ű�����(Country ��üŸ��, ��ü����)
	public void insertCountry(Country country) {
		//db������ ������ ���� ����
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//�������� �Է� country ���̺��� country, last_update �÷��� �Է� (last_update �� ���� ��¥)
		String sql = "INSERT INTO country (country, last_update) VALUES(?, now())";
		//����
		try {
			//db���� �Է�
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			stmt.executeUpdate();
		//����ó��
		} catch(Exception e) {
			e.printStackTrace();
		//�۾��̳����� db�������	
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return;
	}
	
	//���̺��� ���� �� ������ �������� �޼ҵ� ����
	public int selectCount() {
		//������ ���� �� ������ ������ ���� count ����
		int count = 0;
		//db������ ������ ���� ����
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//�������� �Է� (���̺��� ��� ���� ������ �ҷ���)
		String sql = "SELECT COUNT(*) FROM country";
		//����
		try {
			//db���� �Է�
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			//COUNT(*) �� ���� ���� count �� ����
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		//����ó��
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		//count �� ��ȯ
		return count;
	}
}
