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

import main.java.util.dto.UnitDTO;

/**
 * 获取章节所有内容
 * @param 文件路径
 * @param 当前页数
 * @author libowen1
 *
 */
@SuppressWarnings("resource")
public class GetNextUnit extends BrowserFunction {

	/**
	 * 获取下一章数据
	 * @param browser
	 * @param name
	 */
	public GetNextUnit(Browser browser, String name) {
		super(browser, name);
	}

	@Override
	public Object function(Object[] param) {
		Object[] arguments = param[0].toString().split(";");
		return ReadUnit(arguments[0].toString(),Integer.parseInt(arguments[1].toString())+1);
	}

	/**
	 * 查询目标章节
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	static Object ReadUnit(String fileName,Integer lineTo) {
		List<UnitDTO> result = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));// 使用缓冲区的方法将数据读入到缓冲区中
			String str;
			//当前行
			int line = 1;
			Boolean isSure = false;
			while ((str = br.readLine()) != null) {
				if(line>lineTo) {
					boolean isMatch = Pattern.matches(".*[\u7b2c].{1,10}[\u7ae0].*", str);
					if (isMatch) {
						if(isSure) {
							result.add(new UnitDTO("------------本章完-----------",line));
							break;				
						}else {
							isSure = true;
						}
					}
					if(isSure) {
						result.add(new UnitDTO(str,line));
					}
				}
				line++;
			}
		} catch (IOException e) {
			System.out.println("文件找不到或者转换io错误:"+e.getCause());
		}
		SaveUnitLine.saveDataToFile(result.get(0).getLine()+"");
		return JSONObject.toJSONString(result);
		
	}

	public static void main(String[] args) {
		String fileName = "D:/文档/党敏/244797.txt";
		Object readList = ReadUnit(fileName, 951);
		System.out.println(readList);
	}

}
