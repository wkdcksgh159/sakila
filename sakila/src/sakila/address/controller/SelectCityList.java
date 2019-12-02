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
 * Servlet implementation class SelectCityList
 */
@WebServlet("/address/SelectCityList")
public class SelectCityList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet 도착");
		response.setContentType("Application/json");
		int currentPage=1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		CityDao cityDao = new CityDao();
		List<City> list = new ArrayList<City>();
		list = cityDao.selectCityList(currentPage);
		System.out.println("Servlet list:>>"+list);
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println("Servlet JsonList"+jsonList);
		response.getWriter().write(jsonList);
	}

}
