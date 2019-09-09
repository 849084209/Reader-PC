package main.java.util;

import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.BrowserFunction;

/**
 * 获取历史数据
 * @author libowen1
 *
 */
public class GetHistory extends BrowserFunction {

	/**
	 * 获取读取历史line
	 * @param browser
	 * @param name
	 */
	public GetHistory(Browser browser, String name) {
		super(browser, name);
	}

	@Override
	public Object function(Object[] arguments) {
		return SaveUnitLine.getDatafromFile();
	}


	public static void main(String[] args) {
		System.out.println(SaveUnitLine.getDatafromFile());
	}

}
