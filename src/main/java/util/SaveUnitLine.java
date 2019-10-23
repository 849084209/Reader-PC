package main.java.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

import main.java.util.dto.BookDTO;

/**
 * 储存
 * 
 * @author libowen1
 *
 */
public class SaveUnitLine {
	
	private final static String FILE_NAME = "readerBowenHistory";
	
	/**
	 * 保存完成版
	 * @param bookDTOList
	 */
	public static void saveDataToFile(List<BookDTO> books) {
		String data = JSONArray.toJSONString(books);
		BufferedWriter writer = null;
		String url = System.getProperty("user.dir");
		File file = new File(url + "/" + FILE_NAME + ".json");
		// 如果文件不存在，则新建一个
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 写入
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("文件写入成功！");
	}
	/**
	 * 存数据
	 * 
	 * @param fileName
	 * @param data
	 */
	public static void saveDataToFile(String data) {
		BufferedWriter writer = null;
		String url = System.getProperty("user.dir");
		File file = new File(url + "/" + FILE_NAME + ".json");
		// 如果文件不存在，则新建一个
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 写入
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("文件写入成功！");
	}

	/**
	 * 获取book集合
	 * @return
	 */
	public static List<BookDTO> getBooks() {
		String datafromFile = getDatafromFile();
		//如果书架里面没有数据，返回null 的书架集合
		if(datafromFile.isEmpty()) {
			return new ArrayList<BookDTO>();
		}
		return JSONArray.parseArray(datafromFile, BookDTO.class);
	}
	public static void main(String[] args) {
		BookDTO bookDTO = new BookDTO();
		bookDTO.setHistoryUnitLine(123);
		bookDTO.setLastReaderTime(System.currentTimeMillis());
		bookDTO.setName("张三李四王二麻");
		bookDTO.setUrl("D:qwd/123/asd");
		BookDTO bookDTO1 = new BookDTO();
		bookDTO1.setHistoryUnitLine(1234);
		bookDTO1.setLastReaderTime(System.currentTimeMillis());
		bookDTO1.setName("李四");
		bookDTO1.setUrl("D:qwd/123/asd");
		BookDTO bookDTO2 = new BookDTO();
		bookDTO2.setHistoryUnitLine(12345);
		bookDTO2.setLastReaderTime(System.currentTimeMillis());
		bookDTO2.setName("王二麻");
		List<BookDTO> result = new ArrayList<>(3);
		result.add(bookDTO2);
		result.add(bookDTO1);
		result.add(bookDTO);
		saveDataToFile(result);
		List<BookDTO> books = getBooks();
		System.out.println(books.toString());
	}
	/**
	 * 读取数据
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getDatafromFile() {
		String path = System.getProperty("user.dir") + "/" + FILE_NAME + ".json";
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	
}
