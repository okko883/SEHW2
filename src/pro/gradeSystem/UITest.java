package pro.gradeSystem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * class UITest
 * ---
 * class UI的JUnit Test
 * 
 * Member Data: 
 * 1. stdin // 修改前的System.in
 * 2. stdout // 修改前的System.out
 * 
 * Member Function:
 * 1. tearDown() // 結束測試後的清理
 * 2. testUI_1() // method UI()測試1
 * 3. testUI_2() // method UI()測試2
 * 4. testNoSuchCommandExceptions_1() // NoSuchCommandExceptions.NoSuchCommandExceptions()測試1
 * 5. testNoSuchCommandExceptions_2() // NoSuchCommandExceptions.NoSuchCommandExceptions()測試2
 * 6. testNoSuchIDExceptions_1() // NoSuchIDExceptions.NoSuchIDExceptions()測試1
 * 7. testNoSuchIDExceptions_2() // NoSuchIDExceptions.NoSuchIDExceptions()測試2
 */
public class UITest {
	private final InputStream stdin = System.in;
	private final PrintStream stdout = System.out;

	/*
	 * method tearDown()
	 * ---
	 * 結束測試後的清理
	 * 
	 * Pseudo Code: 
	 * 1. 回復System.in及System.out的原始設定
	 */
	@After
	public void tearDown() throws Exception {
		System.setIn(stdin);
		System.setOut(stdout);
	}

	/*
	 * method testUI_1()
	 * ---
	 * method UI()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行UI()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 955002056 // 輸入學號
	 * E // 5) E 離開選單 (Exit)
	 * Q // 離開系統
	 */
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
		
		UI testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testUI_2()
	 * ---
	 * method UI()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行UI()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 955002056 // 輸入學號
	 * R // 2) R 顯示排名 (Rank)
	 * E // 5) E 離開選單 (Exit)
	 * Q // 離開系統
	 */
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
		
		UI testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testNoSuchCommandExceptions_1()
	 * ---
	 * NoSuchCommandExceptions.NoSuchCommandExceptions()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行UI()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 955002056 // 輸入學號
	 * K // 不存在的指令
	 */
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
		
		UI testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testNoSuchCommandExceptions_2()
	 * ---
	 * NoSuchCommandExceptions.NoSuchCommandExceptions()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行UI()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 955002056 // 輸入學號
	 * R // 2) R 顯示排名 (Rank)
	 * K // 不存在的指令
	 */
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
		
		UI testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testNoSuchIDExceptions_1()
	 * ---
	 * NoSuchIDExceptions.NoSuchIDExceptions()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行UI()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 955002050 // 輸入不存在的學號
	 */
	@Test
	public void testNoSuchIDExceptions_1() {
		String testCase = "955002050\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "ID錯了！\n"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		UI testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testNoSuchIDExceptions_2()
	 * ---
	 * NoSuchIDExceptions.NoSuchIDExceptions()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行UI()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * -1234 // 輸入非預期的格式
	 */
	@Test
	public void testNoSuchIDExceptions_2() {
		String testCase = "-1234\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "ID錯了！\n"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		UI testUI = new UI();
		assertEquals(assertResult, outContent.toString());
	}

}
