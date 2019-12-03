package sakila.business.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import sakila.business.model.StoreDao;
import sakila.db.DBHelper;

public class StoreService {
	private StoreDao storeDao;
	public List<Map<String, Object>> StoreGetListAll(){
		System.out.println("StoreGetListAll 서비스 실행 확인! ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		Connection conn = null;
		List<Map<String, Object>> list = null;
		try {
			conn = DBHelper.getConnection();
			storeDao = new StoreDao();
			list = storeDao.StoreSelectListAll(conn);
			System.out.println("list : "+list);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, null, conn);
		}
		return list;
	}
}
