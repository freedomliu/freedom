package com.simple.freedom.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.simple.freedom.test.beans.User;

public  class ExcelHelper {
	
    /** 
     * 创建新excel. 
     * @param fileDir  excel的路径 
     * @param sheetName 要创建的表格索引 
     * @param titleRow excel的第一行即表格头 
     */  
    public  static<T> void createExcel(String fileDir,String sheetName,String titleRow[],String[] column,List<T> t)throws Exception{  
        //创建workbook  
        HSSFWorkbook  workbook = new HSSFWorkbook();  
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)  
        HSSFSheet sheet1 = workbook.createSheet(sheetName);    
        //新建文件  
        FileOutputStream out = null;  
        try {  
        	int rowNum=0;
            //添加表头  
            HSSFRow row = workbook.getSheet(sheetName).createRow(rowNum++);    //创建第一行    
            for(short i = 0;i < titleRow.length;i++){  
                HSSFCell cell = row.createCell(i);  
                cell.setCellValue(titleRow[i]);  
            }
            for(int beanIndex=0;beanIndex<t.size();beanIndex++)
            {
            	row = workbook.getSheet(sheetName).createRow(rowNum++);
            	for(int cIndex=0;cIndex<column.length;cIndex++)
            	{
            		T beanClass=t.get(beanIndex);
            		String name = column[cIndex].substring(0, 1).toUpperCase() + column[cIndex].substring(1);
            		Method m = beanClass.getClass().getMethod("get" + name);
            		Object value = m.invoke(beanClass); // 调用getter方法获取属性值
                	HSSFCell cell = row.createCell(cIndex);  
                    cell.setCellValue(value+"");  
            	}
            }	
            out = new FileOutputStream(fileDir);  
            workbook.write(out);  
        } catch (Exception e) {  
            throw e;
        } finally {    
            try {    
                out.close();    
			} catch (IOException e) {    
                e.printStackTrace();  
            }    
        }    
    }
    
    /** 
     * 删除文件. 
     * @param fileDir  文件路径 
     */  
    public static boolean deleteExcel(String fileDir) {  
        boolean flag = false;  
        File file = new File(fileDir);  
        // 判断目录或文件是否存在    
        if (!file.exists()) {  // 不存在返回 false    
            return flag;    
        } else {    
            // 判断是否为文件    
            if (file.isFile()) {  // 为文件时调用删除文件方法    
                file.delete();  
                flag = true;  
            }   
        }  
        return flag;  
    }  

      
    public static void main(String[] args) throws Exception {  
    	User test =new User();
    	test.setId(1);
    	test.setUsername("lxt");
    	List<User> listUser=new ArrayList<User>();
    	listUser.add(test);
    	ExcelHelper.createExcel("D:/乱码测试.xls", "乱码测试", new String[]{"id","用户名"},
    			new String[]{"id","username"},listUser);
    }  
}