package sakila.business.service;

import java.sql.Connection;
import java.util.List;

import sakila.business.model.Staff;
import sakila.business.model.StaffDao;
import sakila.db.DBHelper;

public class StaffService {
	private StaffDao staffDao;
	public List<Staff> StaffGetListAll(){
		System.out.println("StaffGetListAll 서비스 실행 확인! ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		Connection conn = null;
		List<Staff> list = null;
		try {
			conn = DBHelper.getConnection();
			staffDao = new StaffDao();
			list = staffDao.StaffSelectListAll(conn);
			System.out.println("list : "+list);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, null, conn);
		}
		return list;
	}
}
