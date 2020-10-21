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

/**
 * Servlet implementation class NameController
 */
@WebServlet(urlPatterns="/NameController.do")
public class NameController extends HttpServlet {
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
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Map<String ,Object> map=new HashMap<String, Object>();
		String userName=request.getParameter("name");
		System.out.println("�û���"+userName);
		if(userName=="") {
			map.put("info", "�û�������Ϊ��!");
			
			String jsonStr=new Gson().toJson(map);
			//�ַ�������ַ���
			response.setContentType("text/html;chraset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(jsonStr);
			out.flush();
			out.close();
			return;
		}
		UserDao userDao=new UserDao();
		boolean bool=false;
		try {
			bool=userDao.getUser(userName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(bool==true) {
			map.put("info", "���û����Ѵ��ڣ�");
		}
		else {
			map.put("info", "���û�������Ҫ��");
		}
		
		String jsonStr=new Gson().toJson(map);
		//�ַ�������ַ���
		response.setContentType("text/html;chraset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
