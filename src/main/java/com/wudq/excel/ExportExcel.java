package com.wudq.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wudq.entity.Book;
import com.wudq.utils.ExcelUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcel {

	public static void main(String[] args) {
//		simpleImpl();
//		List<Book> list = new ArrayList<Book>();
//		Book bo = new Book();
//		bo.setPrice(10);
//		bo.setName("月子");
//		bo.setType("生活");
//		Book bo1 = new Book();
//		bo1.setPrice(11);
//		bo1.setName("日子");
//		bo1.setType("生活");
//		list.add(bo);
//		list.add(bo1);
//		try {
//			ExcelUtil.exportExcel(list, "E:\\wudq\\test002.xls");
//			System.out.println("Excel导出成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		try {
			List<Object> listBook = ExcelUtil.importExcel(Book.class, "E:\\wudq\\test002.xls");
			System.out.println(listBook.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void simpleImpl() {
		List<Book> list = new ArrayList<Book>();
		Book bo = new Book();
		bo.setPrice(10);
		bo.setName("月子");
		bo.setType("生活");
		Book bo1 = new Book();
		bo1.setPrice(11);
		bo1.setName("日子");
		bo1.setType("生活");
		list.add(bo);
		list.add(bo1);
		//Excel对象
		WritableWorkbook book = null;
		try {
			book = Workbook.createWorkbook(new File("E:\\wudq\\test001.xls"));
			//创建选项卡
			WritableSheet sheet = book.createSheet("sheet1", 0);
			for (int i = 0; i<list.size(); i++) {
				bo = list.get(i);
				//创建一个单元格对象  列   行  值
				Label la1 = new Label(0, i, bo.getName());
				Label la2 = new Label(1, i, bo.getType());
				Label la3 = new Label(2, i, String.valueOf(bo.getPrice()));
				//将创建好的单元格对象放入 选项卡中
				sheet.addCell(la1);
				sheet.addCell(la2);
				sheet.addCell(la3);
			}
			//写入目标路径
			book.write();
			System.out.println("Excel导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				book.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
