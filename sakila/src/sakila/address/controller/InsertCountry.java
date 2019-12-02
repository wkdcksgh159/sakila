package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;

/**
 * Servlet implementation class InsertAddress
 */
@WebServlet("/address/InsertCountry")
public class InsertCountry extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//html에서 country값을 받아옴
		String country = request.getParameter("country");
		System.out.println("country Servlet :>>"+country);
		
		countryDao = new CountryDao();
		Country c = new Country();
		c.setCountry(country);
		countryDao.insertCountry(c);
	}

}
