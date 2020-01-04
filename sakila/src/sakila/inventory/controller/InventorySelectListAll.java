package sakila.inventory.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.Inventory;
import sakila.inventory.service.InventoryService;

/**
 * Servlet implementation class InventorySelectListAll
 */
@WebServlet("/InventorySelectListAll")
public class InventorySelectListAll extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InventorySelectListAll 서블릿 실행 확인!");
		response.setContentType("application/json;charset=utf-8");
		
		List<Inventory> list = new ArrayList<Inventory>();
		InventoryService inventoryService = new InventoryService();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		list = inventoryService.InventoryGetListAll(currentPage);
		
		System.out.println("list : "+list);
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		response.getWriter().write(jsonList);
	}

}
