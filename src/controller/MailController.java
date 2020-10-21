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
 * Servlet implementation class MailController
 */
@WebServlet(urlPatterns="/MailController.do")
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailController() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		response.setCharacterEncoding("utf-8");
		Map<String ,Object> map=new HashMap<String, Object>();
		String mail=request.getParameter("mail");
		
		System.out.println("邮箱地址"+mail);
		UserDao userDao=new UserDao();
		boolean bool=false;
		try {
			bool=userDao.getMail(mail);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		if(mail==null) {
			map.put("info", "邮箱不能为空!");
		}
		else if(bool==true) {
			map.put("info", "该邮箱已被注册！");
		}
		else {
			map.put("info", "该邮箱符合要求！");
		}
		
		String jsonStr=new Gson().toJson(map);
		//字符流输出字符串
		response.setContentType("text/html;chraset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
	}

}
