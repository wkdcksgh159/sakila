package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.address.model.City;

/**
 * Servlet implementation class InsertAddress
 */
@WebServlet("/insertAddress")
public class InsertAddress extends HttpServlet {
		private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("insert address Servlet도착");
		response.setContentType("application/json;charset=utf-8");
		addressDao = new AddressDao();
		//cityId 값 받아오기
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String Address1 = request.getParameter("Address1");
		String Address2 = request.getParameter("Address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");
		
		Address address = new Address();
		address.setCity(new City());
		address.getCity().setCityId(cityId);
		address.setAddress(Address1);
		address.setAddress2(Address2);
		address.setDistrict(district);
		address.setPostalCode(postalCode);
		address.setPhone(phone);
		
		System.out.println("Servlet address::>"+address);
		//addressDao.insertAddress(Connection conn,address);	
		
	}

}
