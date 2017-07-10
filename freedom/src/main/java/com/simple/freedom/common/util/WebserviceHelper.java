package com.simple.freedom.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.simple.freedom.common.aop.BaseClass;

/**
 * 向远程服务器发送请求
 * @author liuxiangtao90
 *
 */
public class WebserviceHelper {
	static Logger logger = Logger.getLogger(BaseClass.class);
	/**
	 * 发送get请求
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String setGet(String url, String param) throws IOException {
		String result = "";
		InputStreamReader isr = null;
		BufferedReader in = null;
		HttpURLConnection conn=null;
		try {
			param = URLEncoder.encode(param, "utf-8");
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
		    conn = (HttpURLConnection)realUrl.openConnection();
		    // 默认发get 所以下边可以省略
		    conn.setRequestMethod("GET");
			conn.connect();
			isr = new InputStreamReader(conn.getInputStream());
			in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				System.out.println(inputLine);
			}
			return result;
		} catch (Exception e) {
			logger.error(e);
			return result;
		}
		finally
		{
			if(in!=null)
			{
				in.close();
			}
			if (isr != null) {
				isr.close();
			}
			conn.disconnect();
		}
	}
	
	/**
	 * 发送post请求
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String sendPost(String url,String param) throws IOException
	{
		String result = "";
		InputStreamReader isr = null;
		BufferedReader in = null;
		HttpURLConnection conn=null;
		PrintWriter out=null;
		try {
			URL realUrl = new URL(url);
		    conn = (HttpURLConnection)realUrl.openConnection();
		    // 默认为true  所以上边的get方法中没有写
		    conn.setDoInput(true);
		    // 可以对输出流进行操作
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
			conn.connect();
			
			out=new PrintWriter(conn.getOutputStream());
			out.print(URLEncoder.encode(param, "utf-8"));
			// out.close() 方法默认实现了flush()
			out.flush();
			
			in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				System.out.println(inputLine);
			}
			return result;
		} catch (Exception e) {
			logger.error(e);
			return result;
		}
		finally
		{
			if(in!=null)
			{
				in.close();
			}
			if (isr != null) {
				isr.close();
			}
			if(out!=null)
			{
				out.close();
			}
			conn.disconnect();
		}
	}
}
