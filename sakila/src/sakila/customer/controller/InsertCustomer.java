package sakila.customer.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.address.model.City;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.customer.service.CustomerService;

/**
 * Servlet implementation class InsertCustomer
 */
@WebServlet("/insertCustomer")
public class InsertCustomer extends HttpServlet {
	private CustomerService customerService;	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
	//address 받아온다 (데이터),customer 데이터도 받아온다.
		System.out.println("customer Servlet도착");
		
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String Address1 = request.getParameter("Address1");
		String Address2 = request.getParameter("Address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");
		//address값 받아와서 set
		Address address = new Address();
		address.setCity(new City());
		address.getCity().setCityId(cityId);
		address.setAddress(Address1);
		address.setAddress2(Address2);
		address.setDistrict(district);
		address.setPostalCode(postalCode);
		address.setPhone(phone);
		System.out.println("Servlet address::>"+address);
		
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String eMail = request.getParameter("eMail"); 
		//customer값 받아와서 set
		Customer customer = new Customer();
		customer.setStoreId(storeId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(eMail);
		System.out.println("Servlet customer::"+customer);
		
		customerService = new CustomerService();
		customerService.insertCustomer(address, customer);
	}
}
