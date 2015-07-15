package org.javacore.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author lailulu
 * 2015-7-15 下午03:08:52
 * 	File操作工具类
 */
public class FileUtil {
	public static void main(String[] args) {
//		boolean flag = FileUtil.getDir("E:\\test");
//		System.out.println(flag);
		
//		FileUtil.copyFile("E:\\test\\b.txt", "E:\\test\\b2.txt");
		
//		FileUtil.copyFileByDir("E:\\test\\copy", "E:\\test\\copy3\\copy2");
		
		FileUtil.reName("E:\\test\\llll.txt");
	}
	
	/**
	 * @param strPath
	 * @return
	 * 文件搜索
	 */
	public static boolean getDir(String strPath){
		boolean flag = false;
		File f = new File(strPath);
		if(f.isDirectory()){//是否是一个目录
			File[] fList = f.listFiles();//目录中的文件
			for (int i = 0; i < fList.length; i++) {
				if(fList[i].isDirectory()){
					System.out.println(fList[i].getPath());//在递归到这个目下寻找
					getDir(fList[i].getPath());//递归搜索文件
				}
			}
			for (int j = 0; j < fList.length; j++) {
				String str = fList[j].getPath();//路径名
				//方法一 
//				if(fList[j].isFile()){//是否是一个标准文件
//					System.out.println(fList[j].getPath());
//					String[] space = str.split("\\.");
//					String need = space[1];
//					System.out.println(need);
//					if("mp3".equals(need)){
//						System.out.println(str + "：found mp3");
//						flag = true;
//					}
//				}
				
				//方法二：
				if(fList[j].isFile() && str.endsWith(".mp3")){
					System.out.println(str + "：found mp3");
					flag = true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * @param pathOld 
	 * @param pathNew
	 *  文件拷贝  注：需要指定具体的文件路径
	 */
	public static void copyFile(String pathOld ,String pathNew){
		File fileOld = new File(pathOld);
		File fileNew = new File(pathNew);
		if(fileOld.exists()){//文件或目录是否存在
			try {
				FileInputStream fis = new FileInputStream(fileOld);
				FileOutputStream fos = new FileOutputStream(fileNew);
				int read = 0 ;
				while ((read = fis.read()) != -1) {//从此输入流中读取下一个数据字节,如果已到达流末尾，则返回 -1
					fos.write(read);//将指定 byte 写入此输出流。
					fos.flush();//刷新此输出流，并强制将所有已缓冲的输出字节写入该流中。
				}
				fos.close();//关闭此输出流并释放与此流有关的所有系统资源。
				fis.close();//关闭此输入流并释放与此流关联的所有系统资源。
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param dirOld 源文件夹
	 * @param dirNew 目的文件夹
	 *  拷贝文件夹内容
	 */
	public static void copyFileByDir(String dirOld ,String dirNew){
		File fileOld = new File(dirOld);
		File fileNew = new File(dirNew);
		File[] files = fileOld.listFiles();
		for (File file : files) {
			String tempto = fileNew.getAbsolutePath();//绝对路径名字符串。
			if(file.isDirectory()){//复制内容为文件夹  创建文件夹  在递归复制里面的文件
				String srcdirname = file.getName();
				System.out.println(srcdirname);
				String copydir = tempto+"\\"+srcdirname;
				File tempFile = new File(copydir);
				tempFile.mkdirs();//创建文件夹
				copyFileByDir(file.getAbsolutePath(), copydir);
			}else{
				System.out.println("源文件："+file.getAbsolutePath());
				String srcfile = file.getAbsolutePath();
				int endindex = srcfile.lastIndexOf("\\");//找到“/”所在的位置
				String copyfile = srcfile.substring(endindex);
				String tarfile = tempto + copyfile;
				System.out.println("目标点："+tarfile);
				copyFile(srcfile,tarfile);
			}
		}
	}
	
	/**
	 * @param path
	 * 重命名文件名
	 */
	public static void reName(String path){
		File file = new File(path);
		if(file.exists()){
			file.renameTo(new File("E:\\test\\ll.txt"));//重新命名此抽象路径名表示的文件。
		}
	}
	
}
