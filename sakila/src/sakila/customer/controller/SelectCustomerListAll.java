package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.Customer;
import sakila.customer.service.CustomerService;

/**
 * Servlet implementation class SelectCustomerListAll
 */
@WebServlet("/SelectCustomerListAll")
public class SelectCustomerListAll extends HttpServlet {
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SelectCustomerListAll 서블릿 실행 확인!");
		//응답방식 json타입
		response.setContentType("application/json;charset=utf-8");
		//customer 전체 목록 리스트
		customerService = new CustomerService();
		List<Customer> list = customerService.selectCustomerListAll();
		
		System.out.println("list : "+list);
		
		//리스트를 json 타입으로 변환
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		response.getWriter().write(gsonList);
		
		return;
	}

}
