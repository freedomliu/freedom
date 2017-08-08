package com.simple.freedom.common.util;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCUtil {
	
	public static java.sql.Connection getConn(String proName) throws Exception
	{
		Properties ps = new Properties();
		System.out.println(JDBCUtil.class.getClassLoader());
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream(proName+".properties");
        ps.load(in);
        String pureframeworkDriver = ps.getProperty(proName+"Driver");
        String pureframeworkUrl = ps.getProperty(proName+"Url");
        String pureframeworkUsername = ps.getProperty(proName+"Username");
        String pureframeworkPassword = ps.getProperty(proName+"Password");
        Class.forName(pureframeworkDriver).newInstance();//指定连接类型 
        Connection conn = DriverManager.getConnection(pureframeworkUrl,pureframeworkUsername,pureframeworkPassword);//获取连接  
        return conn;
	}
	
	public static void main(String[] args) throws Exception
	{
		String pureframeworkDriver = "com.mysql.jdbc.Driver";
        String pureframeworkUrl = "jdbc:mysql://localhost:3306/fire?useUnicode=true&characterEncoding=utf8";
        String pureframeworkUsername = "root";
        String pureframeworkPassword = "root";
        Class.forName(pureframeworkDriver).newInstance();//指定连接类型 
        Connection conn = DriverManager.getConnection(pureframeworkUrl,pureframeworkUsername,pureframeworkPassword);//获取连接  
        java.sql.Statement  stmt = conn.createStatement();
        File file=new File("C:/fsConfig/jx");
        File[] files= file.listFiles();
        for(File item :files)
        {
        	if(item.isDirectory())
        	{
        		File[] fileItem= item.listFiles();
        		for(File item1:fileItem)
        		{
        			String sql="insert into area (proName,cityName) values('"+item.getName()+"','"+item1.getName().replace(".txt","")+"')";
        			stmt.execute(sql);
        		}
        	}
        }
		stmt.close();
		conn.close();
	}
}
