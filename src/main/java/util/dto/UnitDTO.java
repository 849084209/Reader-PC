package main.java.util.dto;

/**
 * 章节
 * @author libowen1
 *
 */
public class UnitDTO {
	/**
	 * 章节名称
	 */
	private String unitName;
	/**
	 * 所在行数
	 */
	private int line;
	
	public UnitDTO() {
		super();
	}
	public UnitDTO(String unitName, int line) {
		super();
		this.unitName = unitName;
		this.line = line;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	@Override
	public String toString() {
		return "UnitDTO [unitName=" + unitName + ", line=" + line + "]";
	}
}
