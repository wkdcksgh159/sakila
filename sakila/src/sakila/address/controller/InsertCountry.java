package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/InsertCountry")
public class InsertCountry extends HttpServlet {
	//Dao의 값에 입력받은 country값을 넣기위해 객체선언
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//입력받은값 변수에저장 + 디버깅
		String country = request.getParameter("country");
		System.out.println("country : "+country);
		
		//Country 객체 선언 및 입력받은값 country 저장
		Country c = new Country();
		c.setCountry(country);
		//Dao 인스턴스화 및 입력받은값을 저장하는 insertCountry메소드 불러옴
		countryDao = new CountryDao();
		countryDao.insertCountry(c);
	}

}
