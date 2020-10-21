package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.JdbcUtil;
import vo.User;

public class UserDao {
	
	
	public static Connection getConn(){//得到连接
		//System.out.println(System.getProperty("user.dir"));
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8","root","123456");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public User get(String userName) throws Exception {
		// TODO Auto-generated method stub
		
		//JdbcUtil jdbc=new JdbcUtil();
		Connection con=getConn();
		String sql2="select * from t_user";
		
		PreparedStatement ps2=con.prepareStatement(sql2);
		
		
		ResultSet rs=ps2.executeQuery();
		while(rs.next()) {
			String name=rs.getString("userName");
			
			if(name.equals(userName)) {
				String word=rs.getString("password");
				String chrName=rs.getString("chrName");
				User user1=new User(name,chrName,word);
				return user1;
			}
		}
		ps2.close();
		rs.close();
		con.close();
		return null;
	}
	
	public boolean getUser(String userName) throws Exception {
		//JdbcUtil jdbc=new JdbcUtil();
		Connection con=getConn();
		//Class.forName("com.mysql.jdbc.Driver");
		//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8","root","123456");
		String sql2="select * from t_user";
		PreparedStatement ps2=con.prepareStatement(sql2);
		ResultSet rs=ps2.executeQuery();
		while(rs.next()) {
			String name=rs.getString("userName");
			
			if(name.equals(userName)) {
				return true;
			}
		}
		ps2.close();
		rs.close();
		con.close();
		return false;
	}
	
	public boolean getMail(String Mail) throws Exception {
		//JdbcUtil jdbc=new JdbcUtil();
		Connection con=getConn();
		String sql2="select * from t_user";
		PreparedStatement ps2=con.prepareStatement(sql2);
		ResultSet rs=ps2.executeQuery();
		while(rs.next()) {
			String mail="";
			mail=rs.getString("mail");
			System.out.println(mail);
			if(mail!=null) {
				if(rs.getString("mail").equalsIgnoreCase(Mail)) {
					ps2.close();
					rs.close();
					con.close();
					return true;
				}
			}
		}
		ps2.close();
		rs.close();
		con.close();
		return false;
	}
	
	public boolean Create(User user) throws Exception {
		Connection con=getConn();
		String sql1="insert into t_user(userName,password,chrName,mail,province,city) values(?,?,?,?,?,?)";
		PreparedStatement ps1=con.prepareStatement(sql1);
		
		ps1.setString(1,user.getUserName());
		ps1.setString(2,user.getPassword());
		ps1.setString(3, user.getChrName());
		ps1.setString(4, user.getMail());
		ps1.setString(5,user.getProvince());
		ps1.setString(6, user.getCity());
		
		int counts=ps1.executeUpdate();
		if(counts>0) {
			ps1.close();
			con.close();
			return true;
		}
		ps1.close();
		con.close();
		return false;
	}

}
