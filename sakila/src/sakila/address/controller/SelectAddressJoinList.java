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

import sakila.address.model.Address;
import sakila.address.model.AddressDao;

/**
 * Servlet implementation class SelectAddressJoinList
 */
@WebServlet("/selectAddressJoinList")
public class SelectAddressJoinList extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("application/json;charset=utf-8");
		System.out.println("도착1");
		
		List<Address> list = new ArrayList<Address>();
		AddressDao addressDao = new AddressDao();
		list = addressDao.selectJoinList();
		
		System.out.println("list : "+list);
		
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		response.getWriter().write(gsonList);
	}

}
