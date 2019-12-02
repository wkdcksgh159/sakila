package sakila.address.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.City;
import sakila.address.model.CityDao;

/**
 * Servlet implementation class SelectCityListByCountry
 */
@WebServlet("/SelectCityListByCountry")
public class SelectCityListByCountry extends HttpServlet {
		private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("City Servlet도착");
		
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println("req countryId:>>"+countryId);
		response.setContentType("application/json;charset=utf-8");
		cityDao = new CityDao();
		List<City> list = new ArrayList<City>(); 
		list = cityDao.selectCityByCountry(countryId);
		System.out.println("Servlet: list :>>"+list);
		
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		response.getWriter().write(gsonList);
	}

}
