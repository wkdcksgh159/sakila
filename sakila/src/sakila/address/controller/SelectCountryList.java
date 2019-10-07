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


@WebServlet("/address/selectCountryList")
public class SelectCountryList extends HttpServlet {
	//Dao�� selectCountryList �޼��带 ����ϱ� ���� ��ü ����
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�������� �Ѿ���� Ȯ��
		System.out.println("/address/selectCountryList");
		//���������� application/json �� ����
		response.setContentType("application/json;charset=UTF-8");
		
		//����¡�� ���� �ҷ��� currentPage(����������) ���� ������ ����
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//Dao �ν��Ͻ�ȭ �� selectCountryList �޼��� �ҷ�����
		countryDao = new CountryDao();
		List<Country> list = countryDao.selectCountryList(currentPage);
		//list���� json ���� ���������� ��ü���� �� ������ ����
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		//jsonStr ���� ����
		response.getWriter().write(jsonStr);
		
	}

}
