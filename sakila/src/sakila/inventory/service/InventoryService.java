package sakila.inventory.service;

import java.sql.Connection;
import java.util.List;

import sakila.db.DBHelper;
import sakila.inventory.model.Inventory;
import sakila.inventory.model.InventoryDao;

public class InventoryService {
	private InventoryDao inventoryDao;
	public List<Inventory> InventoryGetListAll(int currentPage) {
		System.out.println("InventoryGetListAll 서비스 실행 확인!");
		Connection conn = null;
		List<Inventory> list = null;
		try {
			conn = DBHelper.getConnection();
			inventoryDao = new InventoryDao();
			list = inventoryDao.selectinventoryListAll(conn, currentPage);
			System.out.println("list : "+list);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, null, conn);
		}
		return list;
	}
}
