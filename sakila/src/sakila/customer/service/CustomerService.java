package sakila.customer.service;

import java.sql.Connection;
import java.util.List;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelper;

public class CustomerService {
	private AddressDao addressDao;
	private CustomerDao customerDao;
	public List<Customer> selectCustomerListAll(){
		System.out.println("selectCustomerListAll service 실행 확인!");
		Connection conn = null;
		List<Customer> list = null;
		try {
			conn = DBHelper.getConnection();
			customerDao = new CustomerDao();
			list = customerDao.selectCustomerListAll(conn);
			System.out.println("list : "+list);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, null, conn);
		}
		return list;
	}
	
	public void insertCustomer(Address address, Customer customer){
		System.out.println("Service 도착::"+address+"\n::"+customer);
		Connection conn = null;
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			addressDao = new AddressDao();
			int addressId = addressDao.insertAddress(conn, address);
			customer.setAddressId(addressId);
			System.out.println("addressId : "+addressId);
			customerDao = new CustomerDao();
			customerDao.insertCustomer(conn, customer);
			conn.commit();
		}catch(Exception e) {
			try {
				conn.rollback();
			}catch(Exception e1){
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			DBHelper.close(null, null, conn);
		}
	}
}
