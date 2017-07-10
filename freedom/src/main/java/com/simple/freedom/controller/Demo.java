package com.simple.freedom.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.simple.freedom.common.aop.BaseController;
import com.simple.freedom.common.util.ZipHelper;

@Controller
@RequestMapping("/demo")
public class Demo extends BaseController {

	@RequestMapping(value = { "/fileuploadExcel.do" }, method = RequestMethod.POST)
	@ResponseBody
	public String fileuploadExcel(HttpServletRequest request,
			@RequestParam("file") CommonsMultipartFile uploadFile) {

		String filename = uploadFile.getOriginalFilename();
		File fileTemp = new File("D:/" + filename);
		try {
			OutputStream os = new FileOutputStream(fileTemp);
			InputStream is = uploadFile.getInputStream();
			int length = 0;
			byte[] b = new byte[1024];
			while ((length = is.read(b, 0, 1024)) != -1) {
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			logger.error(e);
			return "上传失败";
		}
		return "上传成功";
	}

	/**
	 * 普通下载
	 * 
	 * @throws IOException
	 */
	@RequestMapping("download1")
	public void download1(HttpServletRequest request,HttpServletResponse response) throws IOException 
	{
		FileInputStream fis=null;
		OutputStream os=null;
		try
		{
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition",
					"attachment;fileName=" + URLEncoder.encode("普通下载.txt","utf-8"));// 设置文件名
			
			File fileTemp = new File("D:/普通下载.txt");
			fileTemp.createNewFile();
		    fis=new FileInputStream(fileTemp);
			os= response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i;
			while ((i=fis.read(buffer)) != -1) {
				os.write(buffer);
			}
		}
		catch(Exception e)
		{
			logger.error(this.getClass()+":"+e);
		}
		finally
		{
			fis.close();
			os.close();
		}
	}

	/**
	 * 打zip包下载
	 * @throws IOException 
	 */
	@RequestMapping("download2")
	public void download2(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ZipHelper.zip("D:/test", "D:/test.zip");
		FileInputStream fis=null;
		OutputStream os=null;
		try
		{
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition",
					"attachment;fileName=" + URLEncoder.encode("test.zip","utf-8"));// 设置文件名
			
			File fileTemp = new File("D:/test.zip");
		    fis=new FileInputStream(fileTemp);
			os= response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i;
			while ((i=fis.read(buffer)) != -1) {
				os.write(buffer);
			}
		}
		catch(Exception e)
		{
			logger.error(e);
		}
		finally
		{
			fis.close();
			os.close();
			// 删除临时zip包
		}
	}
}
