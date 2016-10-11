package com.wudq.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class IoUtil {
	
	public static File getFile() {
		String filePath = "F:\\fengche@2x.jpg";
		File file = new File(filePath);
		return file.exists() ? file : null;
	}
	public static File getFile(String filePath) {
		if (isEmpty(filePath)) {
			return null;
		}
		File file = new File(filePath);
		return file.exists() ? file : null;
	}
	
	public static void getFileStream(String filePath) throws IOException{
		if (isEmpty(filePath)) {
			return;
		}
		File file = new File(filePath);
		FileInputStream fis = new FileInputStream(file);
		fis.close();
	}
	
	public static boolean isEmpty(Object str) {
		return (str == null || "".equals(str));
	}
}
