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
		return ImportBook.ImpImportBook( arguments[0].toString());
	}

	/**
	 * 导入书本
	 * @param fileNameUrl  ';'隔开的字符串 位置一是名称 位置二是URL
	 * @return
	 */
	public static Object ImpImportBook(String fileName) {
		String[] split = fileName.split(";");
		String name = split[0];
		String url = split[1];
		//更新读取数据
		List<BookDTO> books = SaveUnitLine.getBooks();
		for (BookDTO bookDTO : books) {
			if(bookDTO.getUrl().equals(url)) {
				return "请勿重复导入，可返回书架查看";
			}
		}
		BookDTO bookDTO = new BookDTO();
		bookDTO.setName(name.replace(".txt", ""));
		bookDTO.setHistoryUnitName("未开始阅读");
		bookDTO.setUrl(url);
		books.add(bookDTO);
		//重新保存数据
		SaveUnitLine.saveDataToFile(books);
		return "导入完成，可去书架查看";
	}

	public static void main(String[] args) {
//		String fileName = "D:/文档/党敏/244797.txt";
//		Object readList = readList(fileName, 2);
//		System.out.println(readList.toString());
	}

}
