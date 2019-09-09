package main.java;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

@SuppressWarnings("resource")
public class Display_txt {
	// 查看全文（以每10个段落为一页，统计总行数）
	static void ReadAll(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));// 使用缓冲区的方法将数据读入到缓冲区中
		String str;
		int i = 1;
		int a = 0, b = 1;
		while ((str = br.readLine()) != null) {
			System.out.println("[" + i + "]:" + str);
			System.out.println();
			if (i == a + 10) // 分页(以每10个段落为一页)
			{
				System.out.println("------------------------  page " + b + "  ------------------------");
				a = a + 10;
				b = b + 1;
			}
			i = i + 1;
		}
		System.out.println("------------------------  page " + b + "  ------------------------\n");
		i = i - 1;
		System.out.println("本文共有" + i + "行;   共有" + b + "页\n");
	}

	/**
	 * 查询第几章
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	static void ReadList(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));// 使用缓冲区的方法将数据读入到缓冲区中
		String str;
		int i = 1;
		int a = 0, b = 1;
		while ((str = br.readLine()) != null) {
			boolean isMatch = Pattern.matches(".*[\u7b2c].{1,10}[\u7ae0].*", str);
			if (i == a + 10) // 分页(以每10个段落为一页)
			{
				if(isMatch) {
					System.out.println("[" + b + "]:" + str);
					System.out.println();
				}
				a = a + 10;
				b = b + 1;
			}
			i = i + 1;
		}
		i = i - 1;
		System.out.println("本文共有" + i + "行;   共有" + b + "页\n");
	}

	// 查看指定页码，实现上下翻页
	static void getPage(String fileName) throws IOException {
		System.out.println("(提示：输入00返回上一级)");
		System.out.print("请输入查询页码：");
		Scanner sc = new Scanner(System.in);
		int pageNumber = sc.nextInt();
		while (pageNumber != 00) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line = br.readLine();
			int num = 0;
			int startNumber = (pageNumber - 1) * 10 + 1;
			int endNumber = pageNumber * 10;
			while (line != null) {
				if (startNumber == ++num) {

					System.out.println(line);
					int i = startNumber;
					while (i < endNumber) {
						i = i + 1;
						line = br.readLine();
						System.out.println(line);
					}
					System.out.println("-------------------page " + pageNumber + "--------------------");
					System.out.println("(提示：输入+(下一页)；输入-(上一页);输入bye(返回上一级);");
					System.out.print("请输入：");
					Scanner sc1 = new Scanner(System.in);
					String pageNumber1 = sc1.next();
					int pn = pageNumber;
					while (!pageNumber1.equals("bye")) {
						if (pageNumber1.equals("+")) {
							BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
							String line1 = br1.readLine();
							pn = pn + 1;
							int num1 = 0;
							int startNumber1 = (pn - 1) * 10 + 1, endNumber1 = pn * 10;
							while (line1 != null) {
								if (startNumber1 == ++num1) {
									System.out.println(line1);
									int a = startNumber1;
									while (a < endNumber1) {
										a = a + 1;
										line1 = br1.readLine();
										System.out.println(line1);
									}
									System.out.println("-------------------page " + pn + "--------------------");

								}
								line1 = br1.readLine();
							}
						}
						if (pageNumber1.equals("-")) {
							BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
							String line2 = br2.readLine();
							pn = pn - 1;
							int num2 = 0;
							int startNumber2 = (pn - 1) * 10 + 1, endNumber2 = pn * 10;
							while (line2 != null) {
								if (startNumber2 == ++num2) {
									System.out.println("[" + startNumber2 + "]:" + line2);
									int b = startNumber2;
									while (b < endNumber2) {
										b = b + 1;
										line2 = br2.readLine();
										System.out.println("[" + b + "]:" + line2);
									}
									System.out.println("-------------------page " + pn + "--------------------");

								}
								line2 = br2.readLine();
							}
						} else {
						}
						System.out.print("请输入：");
						pageNumber1 = sc1.next();
					}
				}
				line = br.readLine();
			}
			System.out.println("(提示：输入00返回上一级)");
			System.out.print("请输入查询页码：");
			pageNumber = sc.nextInt();
		}
		System.out.println("返回到上一级");
	}
	// 读取指定行,并查看上下行
	static void readxxLine(String fileName) throws IOException {
		System.out.println("(提示：输入00返回上一级)");
		System.out.print("请输入查询行：");
		Scanner sc = new Scanner(System.in);
		int lineNumber = sc.nextInt();
		while (lineNumber != 00) {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));// 使用缓冲区的方法将数据读入到缓冲区中
			String line = br.readLine();
			int num = 0;
			while (line != null) {
				if (lineNumber == ++num) {
					System.out.println("[" + lineNumber + "]:" + line);
					System.out.println("提示：输入+(下一行)；输入-(上一行)；输入bye(返回上一级)");
					System.out.print("请输入：");
					Scanner sc1 = new Scanner(System.in);
					String lineNumber1 = sc1.next();
					int ln = lineNumber;
					while (!lineNumber1.equals("bye")) {
						if (lineNumber1.equals("+")) {
							BufferedReader br1 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
							String line1 = br1.readLine();
							ln = ln + 1;
							int num1 = 0;
							while (line1 != null) {
								if (ln == ++num1) {
									System.out.println("[" + ln + "]:" + line1);
								}
								line1 = br1.readLine();
							}
						}
						if (lineNumber1.equals("-")) {
							BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
							String line2 = br2.readLine();
							ln = ln - 1;
							int num2 = 0;
							while (line2 != null) {
								if (ln == ++num2) {
									System.out.println("[" + ln + "]:" + line2);
								}
								line2 = br2.readLine();
							}
						} else {
						}
						lineNumber1 = sc1.next();
					}
				}
				line = br.readLine();
			}
			System.out.println("(提示：输入00返回上一级)");
			System.out.print("请输入查询行：");
			lineNumber = sc.nextInt();
		}
		System.out.println("返回上一级");
	}
	// 主程序
	public static void main(String[] args) throws IOException {
		String fileName = "D:/文档/党敏/244797.txt";
		System.out.println("提示：输入1(查看全文,统计全文总行数和总页码数)；输入2(读取指定行，并查看上下行)；输入3(查看指定页码的内容，上下翻页)；输入4(查看指定页码的内容，上下翻页)；");
		while (true) {
			System.out.print("请输入：");
			Scanner sc = new Scanner(System.in);
			int j = sc.nextInt();
			if (j == 1) {
				ReadAll(fileName);
				j = 0;
			}
			if (j == 2) {
				readxxLine(fileName);
				j = 0;
			}
			if (j == 3) {
				getPage(fileName);
				j = 0;
			}
			if (j == 4) {
				ReadList(fileName);
				j = 0;
			}
			if (j == 0) {
			} else {
				System.out.println("输入错误");
			}
			System.out.println("提示：输入1(查看全文,统计全文总行数和总页码数)；输入2(读取指定行，并查看上下行)；输入3(查看指定页码的内容，上下翻页)；输入4(查看指定页码的内容，上下翻页)；");
		}
	}
	
	
	
	
	
}