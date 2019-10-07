package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Country;
import sakila.address.model.CountryDao;


@WebServlet("/address/insertCountry")
public class InsertCountry extends HttpServlet {
	//Dao�� ���� �Է¹��� country���� �ֱ����� ��ü����
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//�Է¹����� ���������� + �����
		String country = request.getParameter("country");
		System.out.println("country : "+country);
		
		//Country ��ü ���� �� �Է¹����� country ����
		Country c = new Country();
		c.setCountry(country);
		//Dao �ν��Ͻ�ȭ �� �Է¹������� �����ϴ� insertCountry�޼ҵ� �ҷ���
		countryDao = new CountryDao();
		countryDao.insertCountry(c);
	}

}
