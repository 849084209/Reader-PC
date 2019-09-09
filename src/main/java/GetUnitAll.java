package main.java;

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

import main.java.util.dto.UnitDTO;

/**
 * 获取分页章节
 * @author libowen1
 *
 */
@SuppressWarnings("resource")
public class GetUnitAll extends BrowserFunction {

	public GetUnitAll(Browser browser, String name) {
		super(browser, name);
	}

	@Override
	public Object function(Object[] arguments) {
		System.out.println(arguments.toString());
		String[] param = arguments[0].toString().split(";");
		Object readList = GetUnitAll.ReadList(param[0].toString(),Integer.parseInt(param[1].toString()));
		System.out.println(readList);
		return readList;
	}

	/**
	 * 查询第几章
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static Object ReadList(String fileName,Integer currentPage) {
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
		Object json = JSONObject.toJSONString(result);
		return json;
		
	}

	public static void main(String[] args) {
		String fileName = "D:/文档/党敏/244797.txt";
		Object readList = ReadList(fileName, 2);
		System.out.println(readList.toString());
	}

}
