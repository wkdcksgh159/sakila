package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CustomerDao {
	//customer 전체목록 List
	public List<Customer> selectCustomerListAll(Connection conn){
		System.out.println("selectCustomerListAll Dao 실행 ----------------");
		List<Customer> list = new ArrayList<Customer>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//customer 전체목록 불러오는 쿼리
		String sql = "SELECT customer_id, store_id, first_name, last_name, email, address_id, active, create_date, last_update " + 
				"FROM customer " + 
				"ORDER BY customer_id DESC " + 
				"LIMIT 10";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("customer_id"));
				customer.setStoreId(rs.getInt("store_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setAddressId(rs.getInt("address_id"));
				customer.setActive(rs.getInt("active"));
				customer.setCreateDate(rs.getString("create_date"));
				customer.setLastUpdate(rs.getString("last_update"));
				list.add(customer);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	//customer insert 메소드
	public void insertCustomer(Connection conn, Customer customer) throws Exception{
		System.out.println(customer);
		System.out.println("insert customer method 도착");
		//Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "insert into "+""
		+ "customer(store_id,first_name,last_name,email,address_id,active,create_date,last_update)"
		+ " values(?,?,?,?,?,1,now(),now())";
		
		stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//키값을 리턴받는다
				stmt.setInt(1, customer.getStoreId());
				stmt.setString(2, customer.getFirstName());
				stmt.setString(3, customer.getLastName());
				stmt.setString(4,customer.getEmail());
				stmt.setInt(5, customer.getAddressId());
				System.out.println(stmt);
			stmt.executeUpdate();
		
	}
	
	public int selectCustomerCount() {
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from customer";
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
