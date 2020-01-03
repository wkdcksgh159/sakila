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
		// 디버깅
		System.out.println("StaffSelectListAll 서블릿 실행 확인!");
		// 반환타입 json타입으로 설정
		response.setContentType("application/json;charset=utf-8");
		// 리스트 객체 생성
		List<Staff> list = new ArrayList<Staff>();
		// 서비스 인스턴스 생성
		staffService = new StaffService();
		// 서비스 메소드 실행
		list = staffService.StaffGetListAll();
		// 디버깅
		System.out.println("list : "+list);
		// Gson을 사용 리스트를 json 타입으로 변환해서 요청한곳으로 반환
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		response.getWriter().write(jsonStr);
	}

}
