package com.wudq.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 反射实现通用Excel导入导出工具
 * @author wudq
 *
 */
public class ExcelUtil {
	
	/**
	 * 导出
	 * @param list
	 * @param path
	 * @throws Exception
	 */
	public static <T> void exportExcel(List<T> list,String path) throws Exception {
		WritableWorkbook book = Workbook.createWorkbook(new File(path));
		WritableSheet sheet = book.createSheet("sheet", 0);
		for (int i = 0; i < list.size(); i++) {
			Object ob = list.get(i);
			Class<? extends Object> cl = ob.getClass();
			//申明的属性
			Field[] fields = cl.getDeclaredFields();
			for(int j = 0;j<fields.length;j++){
				//开放private访问权限
				fields[j].setAccessible(true);
//				Label la = new Label(j, i, String.valueOf(fields[j].get(ob)));
				sheet.addCell(new Label(j, i, String.valueOf(fields[j].get(ob))));
			}
		}
		System.gc();
		book.write();
		book.close();
	}
	
	/**
	 * 导入
	 * @param cl
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<Object> importExcel(Class cl,String path) throws Exception {
		List<Object> list = new ArrayList<Object>();
		Workbook book = Workbook.getWorkbook(new File(path));
		Sheet[] sheets = book.getSheets();
		if (sheets == null || sheets.length == 0) {
			return null;
		}
		Field[] fields = cl.getDeclaredFields();
		//实例化实体对象
		Object obj = null;
		Field field = null;
		for (int i = 0; i < sheets.length; i++) {
			Sheet sheet = sheets[i];
			//Excel行
			for (int j = 0; j < sheet.getRows(); j++) {
				obj = cl.newInstance();
				for (int k = 0; k < fields.length; k++) {
					field = fields[k];
					field.setAccessible(true);
					//k列  j行
					String con = sheet.getCell(k,j).getContents();
					if (field.getType().toString().equals("class java.lang.String")) {
						field.set(obj, con);
					} else if(field.getType().toString().equals("int")){
						field.setInt(obj, Integer.valueOf(con));
					} else if(field.getType().toString().equals("float")){
						field.setFloat(obj, Float.valueOf(con));
					}
				}
				list.add(obj);
			}
		}
		book.close();
		return list;
	}
}
