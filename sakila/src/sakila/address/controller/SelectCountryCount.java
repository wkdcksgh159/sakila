package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CountryDao;


@WebServlet("/SelectCountryCount")
public class SelectCountryCount extends HttpServlet {
	//Dao의 메소드를 사용하기 위해 객체 생성
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답형식을 application/json 로 지정
		response.setContentType("application/json;charset=UTF-8");
		//Dao객체 인스턴스화 및 selectCount메소드의 값을 변수 count 에 저장
		countryDao = new CountryDao();
		int count = countryDao.selectCount();
		//Gson객체선언 및 count값을 json 형식으로 변환한값을 jsonCount변수에 저장 
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		//jsonCount의 값만을 응답
		response.getWriter().write(jsonCount);
	}

}
