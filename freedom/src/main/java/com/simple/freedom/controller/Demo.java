package com.simple.freedom.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.simple.freedom.common.aop.BaseController;
import com.simple.freedom.common.util.ExcelHelper;
import com.simple.freedom.common.util.ZipHelper;
import com.simple.freedom.common.util.activeMqHelper;
import com.simple.freedom.common.util.activeMqHelper2;
import com.simple.freedom.test.beans.User;

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
	public void download1(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName="
					+ URLEncoder.encode("普通下载.txt", "utf-8"));// 设置文件名

			File fileTemp = new File("D:/普通下载.txt");
			fileTemp.createNewFile();
			fis = new FileInputStream(fileTemp);
			os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				os.write(buffer);
			}
		} catch (Exception e) {
			logger.error(this.getClass() + ":" + e);
		} finally {
			fis.close();
			os.close();
		}
	}

	/**
	 * 打zip包下载
	 * 
	 * @throws IOException
	 */
	@RequestMapping("download2")
	public void download2(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		ZipHelper.zip("D:/test", "D:/test.zip");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName="
					+ URLEncoder.encode("test.zip", "utf-8"));// 设置文件名

			File fileTemp = new File("D:/test.zip");
			fis = new FileInputStream(fileTemp);
			os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				os.write(buffer);
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			fis.close();
			os.close();
			// 删除临时zip包
		}
	}

	/**
	 * excel下载
	 * 
	 * @throws IOException
	 */
	@RequestMapping("download3")
	public void download3(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		FileInputStream fis = null;
		OutputStream os = null;
		User test = new User();
		test.setId(1);
		test.setUsername("lxt");
		List<User> listUser = new ArrayList<User>();
		listUser.add(test);
		String path = request.getSession().getServletContext().getRealPath("")
				+ "/乱码测试.xls";
		try {
			ExcelHelper.createExcel(path, "乱码测试", new String[] { "id", "用户名" },
					new String[] { "id", "username" }, listUser);

			response.reset();
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition", "attachment;fileName="
					+ URLEncoder.encode("乱码测试.xls", "utf-8"));// 设置文件名

			File fileTemp = new File(path);
			fis = new FileInputStream(fileTemp);
			os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int i;
			while ((i = fis.read(buffer)) != -1) {
				os.write(buffer);
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		} finally {
			fis.close();
			os.close();
			// 删除临时
			ExcelHelper.deleteExcel(path);
		}

	}

	/**
	 * java 實現activeMQ
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("activemqTest")
	public void activemqTest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		User user = new User();
		user.setId(111111);
		activeMqHelper.setMessage("smsQueue", user);
	}

	/**
	 * 配置文件實現activemmq
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("activemqTest1")
	public void activemqTest1(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		activeMqHelper2.send(new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createObjectMessage(new User());
			}

		});
	}

}