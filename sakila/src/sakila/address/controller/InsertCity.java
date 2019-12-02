package sakila.address.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.City;
import sakila.address.model.CityDao;
import sakila.address.model.Country;

/**
 * Servlet implementation class InsertCity
 */
@WebServlet("/address/insertCity")
public class InsertCity extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//countryId, city 값 받아오기
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		String city = request.getParameter("city");
		
		System.out.println("insert city:>"+countryId+"+"+ city);
		CityDao cityDao = new CityDao();
		City c = new City();
		c.setCountry(new Country());
		c.setCity(city);
		c.getCountry().setCountryId(countryId);
		cityDao.insertCity(c);
		
		
		
	}

}
