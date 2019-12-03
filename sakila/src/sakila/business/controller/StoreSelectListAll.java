package sakila.business.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.service.StoreService;

/**
 * Servlet implementation class StoreSelectListAll
 */
@WebServlet("/StoreSelectListAll")
public class StoreSelectListAll extends HttpServlet {
	private StoreService storeService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("StoreSelectListAll 서블릿 실행 확인!ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		//json타입으로 반환
		response.setContentType("application/json;charset=utf-8");
		//StoreGetListAll 메소드의 결과를 받을 Map타입 ArrayList 생성
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		//StoreGetListAll 메소드를 실행하기 위해 StoreService 인스턴스 생성
		storeService = new StoreService();
		//실행시킨 StoreGetListAll 메소드의 결과를 list에 저장
		list = storeService.StoreGetListAll();
		//디버깅
		System.out.println("list : "+list);
		//배열을 객체타입으로 변환하기 위해 Gson 인스턴스 생성
		Gson gson = new Gson();
		//gson 의 toJson 메소드를 사용해 list를 객체타입으로 변환해 gsonList에 저장
		String gsonList = gson.toJson(list);
		//변환한 gsonList 를 뷰로 리턴
		response.getWriter().write(gsonList);
	}

}
