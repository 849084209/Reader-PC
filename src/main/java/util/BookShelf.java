package main.java.util;

import java.util.List;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

import com.alibaba.fastjson.JSONObject;

import main.java.util.dto.BookDTO;

/**
 * 获取分页章节
 * @author libowen1
 *
 */
public class BookShelf extends BrowserFunction {

	/**
	 *  获取所有书籍 无入参
	 * @param browser
	 * @param name
	 */
	public BookShelf(Browser browser, String name) {
		super(browser, name);
	}

	@Override
	public Object function(Object[] arguments) {
		Object readList = BookShelf.getBookShelf();
		return readList;
	}

	/**
	 * 导入书本
	 * @param fileNameUrl  ;隔开的字符串 位置一是名称 位置二是url
	 * @return
	 */
	public static Object getBookShelf() {
		//读取所有书籍信息
		List<BookDTO> books = SaveUnitLine.getBooks();
		return JSONObject.toJSONString(books);
	}

	public static void main(String[] args) {
//		String fileName = "D:/文档/党敏/244797.txt";
//		Object readList = readList(fileName, 2);
//		System.out.println(readList.toString());
//		getBookShelf();
	}

}
