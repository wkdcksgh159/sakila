package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/SelectCountryList")
public class SelectCountryList extends HttpServlet {
	//Dao의 selectCountryList 메서드를 사용하기 위해 객체 선언
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿으로 넘어오나 확인
		System.out.println("/address/selectCountryList");
		//응답형식을 application/json 로 지정
		response.setContentType("application/json;charset=UTF-8");
		
		//페이징을 위해 불러온 currentPage(현재페이지) 값을 변수에 저장
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//Dao 인스턴스화 및 selectCountryList 메서드 불러오기
		countryDao = new CountryDao();
		List<Country> list = countryDao.selectCountryList(currentPage);
		//list값을 json 으로 보내기위해 객체선언 및 변수에 저장
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		//jsonStr 값을 응답
		response.getWriter().write(jsonStr);
		
	}

}
