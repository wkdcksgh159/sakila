package sakila.address.controller;

import java.io.IOException;
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
@WebServlet("/address/selectCityList")
public class SelectCityList extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json 타입으로반환함
		response.setContentType("application/json");
		//페이징
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		cityDao = new CityDao();
		List<City> list = cityDao.selectCityList(currentPage);
		
		//list를 자바스크립트의 객체타입으로 보내기 위해 Gson 사용
		Gson gson = new Gson();
		String json = gson.toJson(list);
		//json타입으로 변환한 list를 클라이언트로 보냄
		response.getWriter().write(json);
	}

}
