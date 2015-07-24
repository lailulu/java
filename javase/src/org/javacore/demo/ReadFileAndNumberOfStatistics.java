package org.javacore.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lailulu
 * 2015-7-24 上午10:43:29
 * 
 * 读取指定路径下的文件"F:/a.txt"，并统计文件中名字相同的人数，文本如下：
 *  1，李四，40
	2，张三，40
	3，张三，30
	4，王五，28
	5，李四，25
	6，张三，29
	7，王五，28
	8，李四，27
	9，赵六，30
	10，张三，21
	11，xx，19
	12，yy，20
	13，李四，19
	14，李四，20
	15，aa，20
 */
public class ReadFileAndNumberOfStatistics {
	
	public static void main(String[] args) {
		List<String> list = ReadFileAndNumberOfStatistics.readFile();
		Map map = new HashMap();
		
		//统计每个名字出现的个数，并存储到map中（name,count）---拓展（可以放置到TreeSet中，因为TreeSet放置的元素是不能重复的）
		for (int i = 0; i < list.size(); i++) {
			String str = list.get(i);
			String name = str.split("，")[1];
			if(map.get(name) == null){//迭代出的名字在map中还未匹配到，则存储并次数计为1
				map.put(name, new Integer(1));
			}else{//该名字至少出现过一次以上
				Integer count = (Integer) map.get(name);
				count = new Integer(count.intValue()+1);
				map.put(name, count);
			}
		}
		
		//打印出每个名字及对应出现的次数
		Set set = map.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String key = (String) iterator.next();
			Integer value = (Integer) map.get(key);
			System.out.println(key +":" +value);
		}
	}
	
	public static List<String> readFile(){
		BufferedReader reader = null;
		List<String> list = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("F:/a.txt"),"GBK"));
			list = new ArrayList<String>();
			String line = "";
			while ((line = reader.readLine()) != null) {
				//读取文件每一行 ，并将读取的内容存到list表中
				list.add(line);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
