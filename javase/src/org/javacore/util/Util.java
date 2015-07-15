package org.javacore.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.UnsupportedEncodingException;

/**
 * @author lailulu
 * 2015-7-15 下午04:55:13
 * 工具类
 */
public class Util {
	private static final String OS_LINUX ="linux" ;
	private static final String OS_WIN ="windows" ;
	private static final String OS_WIN98 ="windows98" ;

	/**
	 * 获取操作系统类型
	 * @return
	 */
	public static String getOS(){
		String os = OS_LINUX;
		String osName = System.getProperty("os.name");
		if(osName.toLowerCase().indexOf("windows") > -1){
			
			os = OS_WIN;
			if(os.indexOf("98")> -1){
				os=OS_WIN98;
			}
		}
		return os;
	}
	
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str){
		int strLen;//strLen = str.length();
		if(str == null || (strLen = str.length()) == 0)
			return true;
		for (int i = 0; i < strLen; i++) {
			//此行代表str为不是空字符串。isWhitespace判断的是如果字符为 Java 空白字符，则返回 true；否则返回 false。
			if(!Character.isWhitespace(str.charAt(i)))
				return false;
		}
		return true;	
	}
	
	/**
	 * 获取文件总行数
	 * @param file
	 * @return
	 */
	public static int getTotalLines(File file){
		
		int lines= 0;
		FileReader in = null;
		LineNumberReader reader = null;
		try {
			in = new FileReader(file);
			reader = new LineNumberReader(in);
			String s = reader.readLine();
			while (s != null){
				lines++;
				s = reader.readLine();
			}
		} catch (Exception e) {
			System.out.println("get total line error");
		}finally{
			try {
				if(reader != null)
					reader.close();
				if(in !=null)
					in.close();
			} catch (IOException e2) {
			}
		}
		return lines;
	}
	
	/**
	 * 写文件
	 * @param path
	 * @param context
	 * @return
	 */
	public static String writeFile(String path,String context){
		FileWriter writer = null;
		try {
			File filename = new File(path);
			if(!filename.exists()){
				filename.getParentFile().mkdir();//获取父目录，在当前目录下没有创建则为false，创建了则为true
				filename.createNewFile();//创建了新文件，（boolean）
			}
			writer = new FileWriter(path);
			writer.write(context);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				writer.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return "success";
	}
	
    /**
     * gbk字符串转utf-8	
     * @param gbk
     * @return
     */
	public static String converString(String gbk){
		String utf8 = "";
		try {
			utf8 = new String(gbk2utf8(gbk),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf8;
	}
	
	private static byte[] gbk2utf8(String gbk) {
		char c[] = gbk.toCharArray();
		byte[] fullByte = new byte[3* c.length];
		for (int i = 0; i < c.length; i++) {
			int m = (int)c[i];
			String word = Integer.toBinaryString(m);
			
			StringBuffer sb = new StringBuffer();
			int len = 16 - word.length();
			for (int j = 0; j < len; j++) {
				sb.append("0");
			}
			sb.append(word);
			sb.insert(0, "1110");
			sb.insert(8, "10");
			sb.insert(16, "10");
			
			String s1 = sb.substring(0,8);
			String s2 = sb.substring(8,16);
			String s3 = sb.substring(16);
			
			byte b0 = Integer.valueOf(s1,2).byteValue();
			byte b1 = Integer.valueOf(s2,2).byteValue();
			byte b2 = Integer.valueOf(s3,2).byteValue();
			byte[] bf = new byte[3];
			bf[0] = b0;
			fullByte[i * 3] = bf[0];
			bf[1] = b1;
			fullByte[i * 3 + 1] = bf[1];
			bf[2] = b2;
			fullByte[i * 3 + 2] = bf[2];
		}
		return fullByte;
	}

	public static void main(String[] args) {
//		System.out.println(getOS());
//		System.out.println(isBlank(" s"));
//		System.out.println(getTotalLines(new File("F:/网盘分享网址.txt")));
//		System.out.println(converString("教师节快乐"));
		System.out.println(writeFile("F:\\WriteTest.txt", "ddfdfdfk"));
	}
}