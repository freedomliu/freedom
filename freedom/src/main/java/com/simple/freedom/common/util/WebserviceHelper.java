package com.simple.freedom.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

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
	
	public static void main(String[] args) throws IOException {
		sendPost("https://cbiz.ccc.cmbchina.com/XDGateway/MerchantApplyCpe?Token=cf347fea-009e-423c-bfa0-8bab424e2d53&Version=1.0", "5k/vSKUu5cWnNhzVk8uAS9zTzr6XsquQqFqMuxglbvuLwRPJ1IOtWL4Jd0f4iIOqM+Tqa5znEf+5Jp8xH0CRSnclrKm96HRFNAM1nRhWEb2nRAyoGJsjObDiJ8Fylvg+huX/RVckmPMMu8TSZlCQNWiHMCe6A6/dPDTxXbdev1N1hkTuTbnxeIW9Gx7vY7weQRu7dtZF6BoewhrQVAjkkFWqh0vhoq5UkVfEljdMvIO6drrXOw/djhE7h2EjFdSoowkrJav+pMfghhRf+xh7vlu/qunj8gq8JQ4p5zJly6HBTL1HoT4IDyysV3iI+3rr0dffEQJV2WYUQb9YWZ+DZYja+LcQC51h7SjbXPaBc6yG+VJv3+G+dkd9qVz7Bca4cqaS+DabsagxsKVchcFQ8azIWsiC/2VUpVUen0xPCx/J7sPLVwHH2q4SCP6sjFjBWjuBIR4F3Dh+Zdiwj8tpyVDLDW+PXSulhy4wPlYMDYhLKueVW5dEUarHK/pbPXbZ9NEmQIPuZt/2Tjxz5e5OrhDXgYRtFW4K4JBiOblEh1Z+3UaCqaG9unHH1sGlEvrG8/+HhPKH7GRPmFj0q5m/S2vb31gR2hpQsspeVmuvllYAjNFIM2yWxhw7z/1BAitbzifJl9zEDByJ6LJXnkwQ3rlPBd8aQ37IAZcCuTOwhqUVHpY04bcANrhzFquDi5/o5cC5JYG0JwxLFpfz6nZ/+j2Ue9YturJkvqltGcj6ytAxnpBCd+6hrGb0B3LrXl0+3wWN2MYYyFQ=");
	}
}
