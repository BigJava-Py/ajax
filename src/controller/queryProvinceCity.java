package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.ProvinceCityDao;
import vo.City;
import vo.Province;

/**
 * Servlet implementation class queryProvinceCity
 */
@WebServlet(urlPatterns="/queryProvinceCity.do")
public class queryProvinceCity extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String provinceCode=request.getParameter("provinceCode");
		System.out.println("编号"+provinceCode);
		String jsonStr="";
		ProvinceCityDao dao=new ProvinceCityDao();
		if(provinceCode == null)
		{
			ArrayList<Province> list = null;
			try {
				list = dao.queryProvince();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			
			jsonStr = new Gson().toJson(list);
		}
		else {//按provinceCode查询对应的城市的列表
			ArrayList<City> list = null;
			try {
				list = dao.queryCity(provinceCode);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			jsonStr=new Gson().toJson(list);
		}
		//字符流输出字符串
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		System.out.println(jsonStr);
		out.print(jsonStr);
		out.flush();
		out.close();
		
	}

}
