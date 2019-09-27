package main.java.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.alibaba.fastjson.JSONObject;

import main.java.util.dto.BookDTO;
import main.java.util.dto.UnitDTO;

/**
 * 获取分页章节
 * @author libowen1
 *
 */
@SuppressWarnings("resource")
public class ImportBook extends BrowserFunction {

	/**
	 * 获取所有章节数据
	 * @param browser
	 * @param name
	 */
	public ImportBook(Browser browser, String name) {
		super(browser, name);
	}

	@Override
	public Object function(Object[] arguments) {
		System.out.println(arguments.toString());
		String fileName = arguments[0].toString();
		Object readList = ImportBook.ImpImportBook(fileName);
		return readList;
	}

	/**
	 * 导入书本
	 * @param fileName
	 * @return
	 */
	public static Object ImpImportBook(String fileName) {
		//更新读取数据
		List<BookDTO> books = SaveUnitLine.getBooks();
		List<UnitDTO> result = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));// 使用缓冲区的方法将数据读入到缓冲区中
			String str;
			int i = 1,line = 1, min = (currentPage-1)*10+1,max = currentPage*10;
			while ((str = br.readLine()) != null) {
				boolean isMatch = Pattern.matches(".*[\u7b2c].{1,10}[\u7ae0].*", str);
				if (isMatch) {
					if(i>=min&&i<=max) {
						result.add(new UnitDTO(str.length()>25?str.substring(0, 20)+"...":str,line));
					}
					i = i + 1;
				}
				line++;
			}
		} catch (IOException e) {
			System.out.println("文件找不到或者转换io错误:"+e.getCause());
		}
		return JSONObject.toJSONString(result);
		
	}

	public static void main(String[] args) {
		String fileName = "D:/文档/党敏/244797.txt";
		Object readList = readList(fileName, 2);
		System.out.println(readList.toString());
	}

}
