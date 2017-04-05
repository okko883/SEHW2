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
 * class MainTest
 * ---
 * 本系統的JUnit Integration Test
 * 
 * Member Data: 
 * 1. stdin // 修改前的System.in
 * 2. stdout // 修改前的System.out
 * 
 * Member Function: 
 * 1. tearDown() // 結束測試後的清理
 * 2. testIntegration_1() // Integration Test 1
 * 3. testIntegration_2() // Integration Test 2
 */
public class MainTest {
	/**
	 * @uml.property  name="stdin"
	 */
	private final InputStream stdin = System.in;
	/**
	 * @uml.property  name="stdout"
	 */
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
	 * method testIntegration_1()
	 * ---
	 * Integration Test 1
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行Main.main()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 985002201 // 輸入學號
	 * G // 1) G 顯示成績 (Grade)
	 * R // 2) R 顯示排名 (Rank)
	 * A // 3) A 顯示平均 (Average)
	 * W // 4) W 更新配分 (Weight)
	 * 10 // lab1新加權
	 * 15 // lab2新加權
	 * 20 // lab3新加權
	 * 25 // midTerm新加權
	 * 30 // finalExam新加權
	 * No // 取消更新加權
	 * E // 5) E 離開選單 (Exit)
	 * Q // 離開系統
	 */
	@Test
	public void testIntegration_1() {
		String testCase = "985002201\nG\nR\nA\nW\n10\n15\n20\n25\n30\nNo\nE\nQ\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "Welcome 蘇亮\n"
				+ "輸入指令\t1) G 顯示成績 (Grade)\n"
				+ "\t2) R 顯示排名 (Rank)\n"
				+ "\t3) A 顯示平均 (Average)\n"
				+ "\t4) W 更新配分 (Weight)\n"
				+ "\t5) E 離開選單 (Exit)\n"
				+ "使用者輸入："
				+ " 蘇亮成績：lab1：\t\t81\n"
				+ "\tlab2：\t\t91\n"
				+ "\tlab3：\t\t85\n"
				+ "\tmid-term：\t84\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t87\n"
				+ "使用者輸入："
				+ " 蘇亮排名第46\n"
				+ "使用者輸入："
				+ "班平均：\tlab1：\t\t90\n"
				+ "\tlab2：\t\t88\n"
				+ "\tlab3：\t\t89\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t90\n"
				+ "使用者輸入："
				+ "\t舊配分\n"
				+ "\tlab1\t\t10%\n"
				+ "\tlab2\t\t10%\n"
				+ "\tlab3\t\t10%\n"
				+ "\tmid-term\t30%\n"
				+ "\tfinal exam\t40%\n"
				+ "\t輸入新配分(提醒：每項需介於0~100之間，五項總和必須等於100)\n"
				+ "\tlab1            "
				+ "\tlab2            "
				+ "\tlab3            "
				+ "\tmid-term        "
				+ "\tfinal exam      "
				+ "\t請確認新配分\n\tlab1\t\t10%\n"
				+ "\tlab2\t\t15%\n"
				+ "\tlab3\t\t20%\n"
				+ "\tmid-term\t25%\n"
				+ "\tfinal exam\t30%\n"
				+ "\t以上正確嗎？Y(Yes)或 N(No)"
				+ "更新加權指令已取消!\n"
				+ "使用者輸入："
				+ "輸入 ID 或 Q(結束使用)？"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Main.main(null);
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testIntegration_2()
	 * ---
	 * Integration Test 2
	 * 
	 * Pseudo Code: 
	 * 1. 將System.in設爲testCase的InputStream
	 * 2. 將終端輸出導至outContent
	 * 3. 執行Main.main()
	 * 4. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 962001051 // 輸入學號
	 * G // 1) G 顯示成績 (Grade)
	 * R // 2) R 顯示排名 (Rank)
	 * A // 3) A 顯示平均 (Average)
	 * W // 4) W 更新配分 (Weight)
	 * 0 // lab1新加權
	 * 100 // lab2新加權
	 * 0 // lab3新加權
	 * 0 // midTerm新加權
	 * 0 // finalExam新加權
	 * Yes // 確認更新加權
	 * G // 1) G 顯示成績 (Grade)
	 * A // 3) A 顯示平均 (Average)
	 * E // 5) E 離開選單 (Exit)
	 * Q // 離開系統
	 */
	@Test
	public void testIntegration_2() {
		String testCase = "962001051\nG\nR\nA\nW\n0\n100\n0\n0\n0\nYes\nG\nA\nE\nQ\n";
		System.setIn(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "輸入 ID 或 Q(結束使用)？"
				+ "Welcome 李威廷\n"
				+ "輸入指令\t1) G 顯示成績 (Grade)\n"
				+ "\t2) R 顯示排名 (Rank)\n"
				+ "\t3) A 顯示平均 (Average)\n"
				+ "\t4) W 更新配分 (Weight)\n"
				+ "\t5) E 離開選單 (Exit)\n"
				+ "使用者輸入："
				+ "李威廷成績：lab1：\t\t81\n"
				+ "\tlab2：\t\t32*\n"
				+ "\tlab3：\t\t50*\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t93\n"
				+ "\ttotal grade：\t81\n"
				+ "使用者輸入："
				+ "李威廷排名第63\n"
				+ "使用者輸入："
				+ "班平均：\tlab1：\t\t90\n"
				+ "\tlab2：\t\t88\n"
				+ "\tlab3：\t\t89\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t90\n"
				+ "使用者輸入："
				+ "\t舊配分\n"
				+ "\tlab1\t\t10%\n"
				+ "\tlab2\t\t10%\n"
				+ "\tlab3\t\t10%\n"
				+ "\tmid-term\t30%\n"
				+ "\tfinal exam\t40%\n"
				+ "\t輸入新配分(提醒：每項需介於0~100之間，五項總和必須等於100)\n"
				+ "\tlab1            "
				+ "\tlab2            "
				+ "\tlab3            "
				+ "\tmid-term        "
				+ "\tfinal exam      "
				+ "\t請確認新配分\n\tlab1\t\t0%\n"
				+ "\tlab2\t\t100%\n"
				+ "\tlab3\t\t0%\n"
				+ "\tmid-term\t0%\n"
				+ "\tfinal exam\t0%\n"
				+ "\t以上正確嗎？Y(Yes)或 N(No)"
				+ "加權已更新!\n"
				+ "使用者輸入："
				+ "李威廷成績：lab1：\t\t81\n"
				+ "\tlab2：\t\t32*\n"
				+ "\tlab3：\t\t50*\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t93\n"
				+ "\ttotal grade：\t32*\n"
				+ "使用者輸入："
				+ "班平均：\tlab1：\t\t90\n"
				+ "\tlab2：\t\t88\n"
				+ "\tlab3：\t\t89\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t89\n"
				+ "使用者輸入："
				+ "輸入 ID 或 Q(結束使用)？"
				+ "結束了\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Main.main(null);
		assertEquals(assertResult, outContent.toString());
	}

}
