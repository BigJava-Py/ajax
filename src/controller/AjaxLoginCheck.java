package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.UserDao;
import vo.User;

/**
 * Servlet implementation class AjaxLoginCheck
 */
@WebServlet(urlPatterns="/AjaxLoginCheck.do")
public class AjaxLoginCheck extends HttpServlet {
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
		//���ñ���
		System.out.println("11111");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//��ȡ������
		String userName=request.getParameter("name");
		String password=request.getParameter("password");
		String userCode=request.getParameter("userCode");
		String check=request.getParameter("box");
		System.out.println(userName+password+userCode+check);
		
		//��ȡsession
		HttpSession session = request.getSession();
		//��ȡ��֤��
		String vCode=session.getAttribute("verityCode").toString();
		System.out.println(vCode);
		//��ŷ�����Ϣ��map
		Map<String ,Object> map=new HashMap<String, Object>();
		//�Ƚ��������֤���������ɵ���֤���Ƿ���ͬ
		
		if(!vCode.equalsIgnoreCase(userCode)) {
			//��map�д�ŷ�������
			map.put("code", 1);
			map.put("info", "��֤�벻��ȷ");
		}else {//��֤��ȷ
			UserDao userDao=new UserDao();
			User user=null;
			try {
				user = userDao.get(userName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(user==null) {//�û���������
				map.put("code", 2);
				map.put("info", "�û���������");
			}else {//�û�������
				if(!user.getPassword().equals(password)) {//���벻��ȷ
					//System.out.println(user.getPassword());
					//System.out.println(password);
					map.put("code", 3);
					map.put("info", "���벻��ȷ");
				}else {//�û���������ȷ
					//����Ҫ���ݵ����ݴ����session����Χ�У�һ���Ự�׶ε����Գ��򶼿��Դ��л�ȡ
					session.setAttribute("user1", user);
					session.setAttribute("userName", userName);
					if(check!=null) {//���¼ѡ��
						//����cookie
						Cookie cookie =new Cookie("userName", userName);
				    	cookie.setMaxAge(7 * 24 *24 *24);
					    cookie.setPath("/");
					    response.addCookie(cookie);
					    Cookie cookie2 =new Cookie("password", password);					 
					    cookie2.setMaxAge(7 * 24 *24 *24);
					    cookie2.setPath("/");
					    response.addCookie(cookie2);
					}
					map.put("code", 0);
					map.put("info", "��¼�ɹ�!");
				}
			}
			
		}
		
		//���ùȸ��Gson�⽫map��������ת��Ϊjson�ַ���
		String jsonStr=new Gson().toJson(map);
		//�ַ�������ַ���
		response.setContentType("text/html;chraset=utf-8");
		PrintWriter out=response.getWriter();
		out.print(jsonStr);
		out.flush();
		out.close();
		
	}

}
