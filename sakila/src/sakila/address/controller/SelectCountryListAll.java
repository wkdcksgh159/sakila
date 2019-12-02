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

import sakila.address.model.Country;
import sakila.address.model.CountryDao;

/**
 * Servlet implementation class SelectCountryListAll
 */
@WebServlet("/selectCountryListAll")
public class SelectCountryListAll extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json 문자열타입으로 리턴
		response.setContentType("application/json;charset=utf-8");
		countryDao = new CountryDao();
		List<Country> list = countryDao.selectCountryListAll();
		System.out.println("Servlet listAll"+list);
		
		Gson gson = new Gson();
		//List 타입에 list를 gson에 String타입 으로 변환
		String jsonStr = gson.toJson(list);
		//write응답코드로 html에 jsonList를 응답
		response.getWriter().write(jsonStr);
		
	}

}
