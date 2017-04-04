package pro.gradeSystem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UITest {
	private UI testUI;
	private final InputStream stdin = System.in;
	private final PrintStream stdout = System.out;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		testUI = null;
		System.setIn(stdin);
		System.setOut(stdout);
	}

	@Test
	public void testUI_1() {
		String testCase = "955002056\nE\nQ\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "Welcome 許文馨\n"
				+ "輸入指令\t1) G 顯示成績 (Grade)\n"
				+ "\t2) R 顯示排名 (Rank)\n"
				+ "\t3) A 顯示平均 (Average)\n"
				+ "\t4) W 更新配分 (Weight)\n"
				+ "\t5) E 離開選單 (Exit)\n"
				+ "使用者輸入："
				+ "輸入 ID 或 Q(結束使用)？結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testUI_2() {
		String testCase = "955002056\nR\nE\nQ\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "Welcome 許文馨\n"
				+ "輸入指令\t1) G 顯示成績 (Grade)\n"
				+ "\t2) R 顯示排名 (Rank)\n"
				+ "\t3) A 顯示平均 (Average)\n"
				+ "\t4) W 更新配分 (Weight)\n"
				+ "\t5) E 離開選單 (Exit)\n"
				+ "使用者輸入："
				+ "許文馨排名第9\n"
				+ "使用者輸入："
				+ "輸入 ID 或 Q(結束使用)？結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testNoSuchCommandExceptions_1() {
		String testCase = "955002056\nK\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "Welcome 許文馨\n"
				+ "輸入指令\t1) G 顯示成績 (Grade)\n"
				+ "\t2) R 顯示排名 (Rank)\n"
				+ "\t3) A 顯示平均 (Average)\n"
				+ "\t4) W 更新配分 (Weight)\n"
				+ "\t5) E 離開選單 (Exit)\n"
				+ "使用者輸入："
				+ "指令錯了！\n"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testNoSuchCommandExceptions_2() {
		String testCase = "955002056\nR\nK\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "Welcome 許文馨\n"
				+ "輸入指令\t1) G 顯示成績 (Grade)\n"
				+ "\t2) R 顯示排名 (Rank)\n"
				+ "\t3) A 顯示平均 (Average)\n"
				+ "\t4) W 更新配分 (Weight)\n"
				+ "\t5) E 離開選單 (Exit)\n"
				+ "使用者輸入："
				+ "許文馨排名第9\n"
				+ "使用者輸入："
				+ "指令錯了！\n"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testNoSuchIDExceptions_1() {
		String testCase = "955002050\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "ID錯了！\n"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testNoSuchIDExceptions_2() {
		String testCase = "-1234\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "ID錯了！\n"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}

}
