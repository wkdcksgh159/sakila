package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CountryDao;


@WebServlet("/address/selectCountryCount")
public class SelectCountryCount extends HttpServlet {
	//Dao�� �޼ҵ带 ����ϱ� ���� ��ü ����
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���������� application/json �� ����
		response.setContentType("application/json;charset=UTF-8");
		//Dao��ü �ν��Ͻ�ȭ �� selectCount�޼ҵ��� ���� ���� count �� ����
		countryDao = new CountryDao();
		int count = countryDao.selectCount();
		//Gson��ü���� �� count���� json �������� ��ȯ�Ѱ��� jsonCount������ ���� 
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		//jsonCount�� ������ ����
		response.getWriter().write(jsonCount);
	}

}
