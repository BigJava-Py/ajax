package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tools.JdbcUtil;
import vo.City;
import vo.Province;
import vo.User;



public class ProvinceCityDao {
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


	public ArrayList<City> queryCity(String provinceCode) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<City> list=new ArrayList<City>();
		//JdbcUtil jdbc=new JdbcUtil();
		Connection con=getConn();
		//Class.forName("com.mysql.jdbc.Driver");
		//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/excise?useUnicode=true&characterEncoding=utf-8","root","123456");
		String sql2="select * from t_city";
		PreparedStatement ps2=con.prepareStatement(sql2);
		ResultSet rs=ps2.executeQuery();
		while(rs.next()) {
			String pid=rs.getString("pid");
			if(pid.equalsIgnoreCase(provinceCode)) {
				String cityname=rs.getString("cityName");
				String cid=rs.getString("cid");
				
				City city=new City(cid,cityname,pid);
				list.add(city);
			}
			
		}
		ps2.close();
		rs.close();
		con.close();
		return list;
	}

	public ArrayList<Province> queryProvince() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Province> list=new ArrayList<Province>();
		//JdbcUtil jdbc=new JdbcUtil();
		Connection con=getConn();
		String sql2="select * from t_province";
		PreparedStatement ps2=con.prepareStatement(sql2);
		ResultSet rs=ps2.executeQuery();
		while(rs.next()) {
			String pid=rs.getString("pid");
			String province=rs.getString("province");
			Province pro=new Province(pid,province);
			list.add(pro);
		}
		ps2.close();
		rs.close();
		con.close();
		return list;
	}

}
