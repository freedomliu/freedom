package com.simple.freedom.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.alibaba.fastjson.JSONObject;

public class pushWeixinTemplet {
	private static final String APPID = "wxd4feb5a089d12e91";
	private static final String APPSECRET = "ecc032c6470bfb2d7ab3853b54df5be3";

	public static String getAccessToken() throws IOException
	{
		return setGet("https://api.weixin.qq.com/cgi-bin/token","grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET);
	}
	
	public static void main(String[] args) throws Exception {
		JSONObject tokenJson= (JSONObject) JSONObject.parse(getAccessToken());
		String token= tokenJson.get("access_token").toString();
		
		
		JSONObject jb=new JSONObject();
		jb.put("touser", "oe4lX0SqdkmynfLo_qOFDZ7qDOUQ");
		jb.put("template_id", "EHFnMdgLFRKrHuvl4QoVn79CwnHqftAWSJKMW45DrHY");
		jb.put("url", "www.baidu.com");
		
		/*JSONObject m=new JSONObject();
		m.put("appid", APPID);
		m.put("pagepath", "index");
		
		jb.put("miniprogram",m);*/
		
		JSONObject d=new JSONObject();
		JSONObject t=new JSONObject();
		t.put("value", "ceshi");
		t.put("color","#173177" );
		d.put("productType", t);
		t=new JSONObject();
		t.put("value", "ceshi");
		t.put("color","#173177" );
		d.put("name", t);
		t=new JSONObject();
		t.put("value", "ceshi");
		t.put("color","#173177" );
		d.put("number", t);
		t=new JSONObject();
		t.put("value", "ceshi");
		t.put("color","#173177" );
		d.put("expDate", t);
		t=new JSONObject();
		t.put("value", "ceshi");
		t.put("color","#173177" );
		d.put("remark", t);
		t=new JSONObject();
		t.put("value", "ceshi");
		t.put("color","#173177" );
		jb.put("data", d);
		
		push(token,jb);
	}
	
	public static String setGet(String url, String param) throws IOException {
		String result = "";
		InputStreamReader isr = null;
		BufferedReader in = null;
		HttpsURLConnection conn=null;
		try {
			//param = URLEncoder.encode(param, "utf-8");
			String urlName = url + "?" + param;
			URL realUrl = new URL(urlName);
		    conn = (HttpsURLConnection)realUrl.openConnection();
		    // 默认发get 所以下边可以省略
		    conn.setRequestMethod("GET");
			conn.connect();
			isr = new InputStreamReader(conn.getInputStream());
			in = new BufferedReader(isr);
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				result+=inputLine;
			}
			return result;
		} catch (Exception e) {
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
		HttpsURLConnection conn=null;
		PrintWriter out=null;
		try {
			URL realUrl = new URL(url);
		    conn = (HttpsURLConnection)realUrl.openConnection();
		    // 默认为true  所以上边的get方法中没有写
		    conn.setDoInput(true);
		    // 可以对输出流进行操作
		    conn.setDoOutput(true);
		    conn.setRequestMethod("POST");
			conn.connect();
			
			out=new PrintWriter(conn.getOutputStream());
			//out.print(URLEncoder.encode(param, "utf-8"));
			out.print(param);
			// out.close() 方法默认实现了flush()
			out.flush();
			
			in=new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
			String inputLine;
			while (null != (inputLine = in.readLine())) {
				result+=inputLine;
			}
			return result;
		} catch (Exception e) {
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
	public static String push(String token,JSONObject jb) throws IOException
	{
		String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token;
		return sendPost(url,jb.toString());
	}
}
