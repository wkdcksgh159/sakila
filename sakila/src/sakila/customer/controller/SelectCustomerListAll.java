package sakila.customer.controller;

import java.io.IOException;
import java.util.ArrayList;
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
		response.setContentType("application/json;charset=utf-8");
		List<Customer> list = new ArrayList<Customer>();
		list = customerService.selectCustomerListAll();
		
		System.out.println("list : "+list);
		
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		response.getWriter().write(gsonList);
	}

}
