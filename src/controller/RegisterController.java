package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import vo.User;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(urlPatterns="/RegisterController.do")
public class RegisterController extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		String userName=request.getParameter("name");
		String password=request.getParameter("password");
		String mail=request.getParameter("mail");
		String realname=request.getParameter("realname");
		String provinceid=request.getParameter("province");
		String city=request.getParameter("cityid");
		
		User user=new User(userName,password,realname,mail,provinceid,city);
		UserDao userdao=new UserDao();
		boolean bool=false;
		try {
			bool=userdao.Create(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String ,Object> map=new HashMap<String, Object>();
		if(bool==true) {
			map.put("code", 1);
		}
		else {
			map.put("code", 0);
		}
		String jsonStr=new Gson().toJson(map);
		//×Ö·ûÁ÷Êä³ö×Ö·û´®
		response.setContentType("text/html;chraset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		
	}

}
