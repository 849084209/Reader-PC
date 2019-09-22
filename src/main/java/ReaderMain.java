package main.java;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import main.java.util.GetHistory;
import main.java.util.GetNextUnit;
import main.java.util.GetUnit;
import main.java.util.GetUnitAll;

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
			new GetUnitAll(browser, "GetUnitAll");
			new GetUnit(browser, "GetUnit");
			new GetNextUnit(browser, "GetNextUnit");
			new GetHistory(browser, "GetHistory");
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
