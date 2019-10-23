package main.java;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import main.java.util.BookShelf;
import main.java.util.GetHistory;
import main.java.util.GetNextUnit;
import main.java.util.GetUnit;
import main.java.util.GetUnitAll;
import main.java.util.ImportBook;

/**
 * @author libowen1
 *
 */
public class ReaderMain extends Shell {

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			String path = System.getProperty("user.dir");
			String url = "file:///" + path + "/index.html";
			System.out.println(url);
			Integer windowHeight = 780;
			Integer windowWeith = 551;
			Display display = new Display();
			Shell shell = new Shell(display);
			shell.setText("Reader");
			shell.setSize(windowHeight + 10, windowWeith + 40);
			final Browser browser = new Browser(shell, SWT.EMBEDDED);
			Browser.clearSessions();
			browser.refresh();
			browser.setUrl(url);
			browser.setBounds(4, 4, windowHeight, windowWeith);
			//获取所有章节数据
			new GetUnitAll(browser, "GetUnitAll");
			//获取当前章节数据
			new GetUnit(browser, "GetUnit");
			//获取下一章数据
			new GetNextUnit(browser, "GetNextUnit");
			//获取读取历史line
			new GetHistory(browser, "GetHistory");
			//导入书籍,入参例子: 武穆遗书;d:/QDQ/武穆遗书.TXT    (书名+分号+URL)
			new ImportBook(browser, "ImportBook");
			//获取书架内容
			new BookShelf(browser, "BookShelf");
			shell.open();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
			display.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * 
	 * @param display
	 */
	public ReaderMain(Display display) {
		super(display, SWT.SHELL_TRIM);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("SWT Application");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
	}

}
