package sakila.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.inventory.model.Inventory;
import sakila.address.model.Address;
import sakila.business.model.Store;
import sakila.db.DBHelper;

public class InventoryDao {
	public List<Inventory> selectinventoryListAll(Connection conn, int currentPage) {
		System.out.println("selectinventoryListAll DAO 실행 확인!");
		List<Inventory> list = new ArrayList<Inventory>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		int rowPerPage = 20;
		int beginRow = (currentPage -1)*rowPerPage;
		String sql = "select i.inventory_id, i.film_id, f.title, i.store_id, a.address, i.last_update " + 
				"from inventory i " + 
				"inner join film f on i.film_id = f.film_id " + 
				"inner join store s on i.store_id = s.store_id " + 
				"inner join address a on s.address_id = a.address_id limit ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Inventory i = new Inventory();
				i.setInventoryId(rs.getInt("i.inventory_id"));
				i.setFilm(new Film());
				i.getFilm().setFilmId(rs.getInt("i.film_id"));
				i.getFilm().setTitle(rs.getString("f.title"));
				i.setStore(new Store());
				i.getStore().setStoreId(rs.getInt("i.store_id"));
				i.getStore().setAddress(new Address());
				i.getStore().getAddress().setAddress(rs.getString("a.address"));
				i.setLastUpdate(rs.getString("i.last_update"));
				list.add(i);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		
		return list;
	}
	public int selectInventoryCount() {
		int count=0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from inventory";
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
