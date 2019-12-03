package sakila.business.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Staff;
import sakila.business.service.StaffService;

/**
 * Servlet implementation class StaffSelectListAll
 */
@WebServlet("/StaffSelectListAll")
public class StaffSelectListAll extends HttpServlet {
	private StaffService staffService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StaffSelectListAll 서블릿 실행 확인!ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		response.setContentType("application/json;charset=utf-8");
		
		List<Staff> list = new ArrayList<Staff>();
		staffService = new StaffService();
		list = staffService.StaffGetListAll();
		
		Gson gson = new Gson();
		String gsonList = gson.toJson(list);
		response.getWriter().write(gsonList);
	}

}
