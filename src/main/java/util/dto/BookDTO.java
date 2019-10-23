package main.java.util.dto;

/**
 * 书籍类
 * @author libowen1
 *
 */
public class BookDTO {
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 路径
	 */
	private String url;
	/**
	 * 读到了哪一章对应的行
	 */
	private int historyUnitLine;
	/**
	 * 读到的章节名称
	 */
	private String historyUnitName;
	/**
	 * 一共多少章
	 */
	private int unitAll;
	
	/**
	 * 章节目录的第几页
	 */
	private int currentPage;
	
	private long lastReaderTime;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getHistoryUnitLine() {
		return historyUnitLine;
	}

	public void setHistoryUnitLine(int historyUnitLine) {
		this.historyUnitLine = historyUnitLine;
	}

	public String getHistoryUnitName() {
		return historyUnitName;
	}

	public void setHistoryUnitName(String historyUnitName) {
		this.historyUnitName = historyUnitName;
	}

	public int getUnitAll() {
		return unitAll;
	}

	public void setUnitAll(int unitAll) {
		this.unitAll = unitAll;
	}

	public long getLastReaderTime() {
		return lastReaderTime;
	}

	public void setLastReaderTime(long lastReaderTime) {
		this.lastReaderTime = lastReaderTime;
	}

	@Override
	public String toString() {
		return "BookDTO [name=" + name + ", url=" + url + ", historyUnitLine=" + historyUnitLine + ", historyUnitName=" + historyUnitName + ", unitAll=" + unitAll + ", lastReaderTime="
				+ lastReaderTime + "]";
	}
	
}
