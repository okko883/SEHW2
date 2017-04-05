package pro.gradeSystem;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * class GradeSystemsTest
 * ---
 * class GradeSystems的JUnit Test
 * 
 * Member Data: 
 * 1. testGS // 測試用的GradeSystems物件
 * 2. stdin // 修改前的System.in
 * 3. stdout // 修改前的System.out
 * 
 * Member Function: 
 * 1. setUp() // 開始測試前的環境設定
 * 2. tearDown() // 結束測試後的清理
 * 3. testGradeSystems_1() // method GradeSystems(parentConsole)測試1
 * 4. testGradeSystems_2() // method GradeSystems(parentConsole)測試2
 * 5. testContainsID_1() // method containsID(ID)測試1
 * 6. testContainsID_2() // method containsID(ID)測試2
 * 7. testShowGrade_1() // method showGrade(ID)測試1
 * 8. testShowGrade_2() // method showGrade(ID)測試2
 * 9. testShowRank_1() // method showRank(ID)測試1
 * 10. testShowRank_2() // method showRank(ID)測試2
 * 11. testShowAverage_1() // method showAverage()測試1
 * 12. testShowAverage_2() // method showAverage()測試2
 * 13. testUpdateWeights_1() // method updateWeights(ID)測試1
 * 14. testUpdateWeights_2() // method updateWeights(ID)測試2
 * 15. testSetWeights_1() // method setWeights(newWeights)測試1
 * 16. testSetWeights_2() // method setWeights(newWeights)測試2
 * 17. testGetThisIDName_1() // method getThisIDName(ID)測試1
 * 18. testGetThisIDName_2() // method getThisIDName(ID)測試2
 */
public class GradeSystemsTest {
	private GradeSystems testGS;
	private final InputStream stdin = System.in;
	private final PrintStream stdout = System.out;

	/*
	 * method setUp()
	 * ---
	 * 開始測試前的環境設定
	 * 
	 * Pseudo Code: 
	 * 1. 使用原始的System.in創建GradeSystems物件，並將其存入testGS
	 */
	@Before
	public void setUp() throws Exception {
		testGS = new GradeSystems(new Scanner(System.in));
	}

	/*
	 * method tearDown()
	 * ---
	 * 結束測試後的清理
	 * 
	 * Pseudo Code: 
	 * 1. 將testGS的reference設爲null
	 * 2. 復原系統預設的System.in及System.out
	 */
	@After
	public void tearDown() throws Exception {
		testGS = null;
		System.setIn(stdin);
		System.setOut(stdout);
	}

	/*
	 * method testGradeSystems_1()
	 * ---
	 * method GradeSystems(parentConsole)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.updateWeights(985002017)
	 * 5. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 10 // lab1新加權
	 * 15 // lab2新加權
	 * 20 // lab3新加權
	 * 25 // midTerm新加權
	 * 30 // finalExam新加權
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testGradeSystems_1() {
		String testCase = "10\n15\n20\n25\n30\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
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
				+ "加權已更新!\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			chWeightGS.updateWeights(985002017);
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}
	
	/*
	 * method testGradeSystems_2()
	 * ---
	 * method GradeSystems(parentConsole)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.updateWeights(985002017)
	 * 5. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 123 // lab1新加權
	 * 456 // lab2新加權
	 * 789 // lab3新加權
	 * 012 // midTerm新加權
	 * 345 // finalExam新加權
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testGradeSystems_2() {
		String testCase = "123\n456\n789\n012\n345\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
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
				+ "\t請確認新配分\n\tlab1\t\t123%\n"
				+ "\tlab2\t\t456%\n"
				+ "\tlab3\t\t789%\n"
				+ "\tmid-term\t12%\n"
				+ "\tfinal exam\t345%\n"
				+ "\t以上正確嗎？Y(Yes)或 N(No)"
				+ "\t調整失敗！第1項超出範圍，請重新輸入\n\n"
				+ "更新加權指令已取消!\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			chWeightGS.updateWeights(985002017);
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}

	/*
	 * method testContainsID_1()
	 * ---
	 * method containsID(ID)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGS.containsID(955002056)是否爲true
	 */
	@Test
	public void testContainsID_1() {
		assertTrue(testGS.containsID(955002056));
	}
	
	/*
	 * method testContainsID_2()
	 * ---
	 * method containsID(ID)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGS.containsID(100000000)是否爲false
	 */
	@Test
	public void testContainsID_2() {
		assertFalse(testGS.containsID(100000000));
	}

	/*
	 * method testShowGrade_1()
	 * ---
	 * method showGrade(ID)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 將終端輸出導至outContent
	 * 2. 執行testGS.showGrade(965002044);
	 * 3. 檢查終端輸出是否與預期一致
	 */
	@Test
	public void testShowGrade_1() {
		String assertResult = "商揚夏成績：lab1：\t\t85\n"
				+ "\tlab2：\t\t86\n"
				+ "\tlab3：\t\t80\n"
				+ "\tmid-term：\t81\n"
				+ "\tfinal exam：\t88\n"
				+ "\ttotal grade：\t85\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showGrade(965002044);
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testShowGrade_2()
	 * ---
	 * method showGrade(ID)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 將終端輸出導至outContent
	 * 2. 執行testGS.showGrade(985002201);
	 * 3. 檢查終端輸出是否與預期一致
	 */
	@Test
	public void testShowGrade_2() {
		String assertResult = " 蘇亮成績：lab1：\t\t81\n"
				+ "\tlab2：\t\t91\n"
				+ "\tlab3：\t\t85\n"
				+ "\tmid-term：\t84\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t87\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showGrade(985002201);
		assertEquals(assertResult, outContent.toString());
	}

	/*
	 * method testShowRank_1()
	 * ---
	 * method showRank(ID)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 將終端輸出導至outContent
	 * 2. 執行testGS.showRank(985002018);
	 * 3. 檢查終端輸出是否與預期一致
	 */
	@Test
	public void testShowRank_1() {
		String assertResult = "周信彰排名第22\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showRank(985002018);
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testShowRank_2()
	 * ---
	 * method showRank(ID)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 將終端輸出導至outContent
	 * 2. 執行testGS.showRank(985002024);
	 * 3. 檢查終端輸出是否與預期一致
	 */
	@Test
	public void testShowRank_2() {
		String assertResult = "魏秀龍排名第52\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showRank(985002024);
		assertEquals(assertResult, outContent.toString());
	}

	/*
	 * method testShowAverage_1()
	 * ---
	 * method showAverage()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 將終端輸出導至outContent
	 * 2. 執行testGS.showAverage();
	 * 3. 檢查終端輸出是否與預期一致
	 */
	@Test
	public void testShowAverage_1() {
		String assertResult = "班平均：\tlab1：\t\t90\n"
				+ "\tlab2：\t\t88\n"
				+ "\tlab3：\t\t89\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t90\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showAverage();
		assertEquals(assertResult, outContent.toString());
	}
	
	/*
	 * method testShowAverage_2()
	 * ---
	 * method showAverage()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.updateWeights(985002017);
	 * 5. 執行chWeightGS.showAverage();
	 * 6. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 0 // lab1新加權
	 * 100 // lab2新加權
	 * 0 // lab3新加權
	 * 0 // midTerm新加權
	 * 0 // finalExam新加權
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testShowAverage_2() {
		String testCase = "0\n100\n0\n0\n0\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
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
				+ "班平均：\tlab1：\t\t90\n"
				+ "\tlab2：\t\t88\n"
				+ "\tlab3：\t\t89\n"
				+ "\tmid-term：\t90\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t89\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			chWeightGS.updateWeights(985002017);
			chWeightGS.showAverage();
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}

	/*
	 * method testUpdateWeights_1()
	 * ---
	 * method updateWeights(ID)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.updateWeights(985002017);
	 * 5. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * 0 // lab1新加權
	 * 100 // lab2新加權
	 * 0 // lab3新加權
	 * 0 // midTerm新加權
	 * 0 // finalExam新加權
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testUpdateWeights_1() {
		String testCase = "0\n100\n0\n0\n0\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
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
				+ "加權已更新!\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			chWeightGS.updateWeights(985002017);
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}
	
	/*
	 * method testUpdateWeights_2()
	 * ---
	 * method updateWeights(ID)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.updateWeights(985002017);
	 * 5. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * -100 // lab1新加權
	 * 100 // lab2新加權
	 * 0 // lab3新加權
	 * 0 // midTerm新加權
	 * 0 // finalExam新加權
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testUpdateWeights_2() {
		String testCase = "-100\n100\n0\n0\n0\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
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
				+ "\t請確認新配分\n\tlab1\t\t-100%\n"
				+ "\tlab2\t\t100%\n"
				+ "\tlab3\t\t0%\n"
				+ "\tmid-term\t0%\n"
				+ "\tfinal exam\t0%\n"
				+ "\t以上正確嗎？Y(Yes)或 N(No)"
				+ "\t調整失敗！第1項超出範圍，請重新輸入\n\n"
				+ "更新加權指令已取消!\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			chWeightGS.updateWeights(985002017);
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}

	/*
	 * method testSetWeights_1()
	 * ---
	 * method setWeights(newWeights)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.setWeights({100f, 200f, 300f, 400f, 500f});
	 * 5. 執行chWeightGS.updateWeights(985002017);
	 * 6. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * -100 // lab1新加權-100
	 * 100 // lab2新加權100
	 * 0 // lab3新加權0
	 * 0 // midTerm新加權0
	 * 0 // finalExam新加 0
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testSetWeights_1() {
		String testCase = "-100\n100\n0\n0\n0\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
				+ "\tlab1\t\t100%\n"
				+ "\tlab2\t\t200%\n"
				+ "\tlab3\t\t300%\n"
				+ "\tmid-term\t400%\n"
				+ "\tfinal exam\t500%\n"
				+ "\t輸入新配分(提醒：每項需介於0~100之間，五項總和必須等於100)\n"
				+ "\tlab1            "
				+ "\tlab2            "
				+ "\tlab3            "
				+ "\tmid-term        "
				+ "\tfinal exam      "
				+ "\t請確認新配分\n\tlab1\t\t-100%\n"
				+ "\tlab2\t\t100%\n"
				+ "\tlab3\t\t0%\n"
				+ "\tmid-term\t0%\n"
				+ "\tfinal exam\t0%\n"
				+ "\t以上正確嗎？Y(Yes)或 N(No)"
				+ "\t調整失敗！第1項超出範圍，請重新輸入\n\n"
				+ "更新加權指令已取消!\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			float[] weights = {100f, 200f, 300f, 400f, 500f};
			chWeightGS.setWeights(weights);
			chWeightGS.updateWeights(985002017);
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}
	
	/*
	 * method testSetWeights_2()
	 * ---
	 * method setWeights(newWeights)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 以testCase創建終端輸入測資作爲chWeightTC
	 * 2. 將終端輸出導至outContent
	 * 3. 創建GradeSystems(chWeightTC)作爲chWeightGS
	 * 4. 執行chWeightGS.setWeights({123.4f, 678.9f, 1011.12f, 1314.15f, 1617.18f});
	 * 5. 執行chWeightGS.updateWeights(985002017);
	 * 6. 檢查終端輸出是否與預期一致
	 * 
	 * Terminal Input Test Case: 
	 * -100 // lab1新加權-100
	 * 100 // lab2新加權100
	 * 0 // lab3新加權0
	 * 0 // midTerm新加權0
	 * 0 // finalExam新加 0
	 * Yes // 確認輸入無誤
	 */
	@Test
	public void testSetWeights_2() {
		String testCase = "-100\n100\n0\n0\n0\nYes\n";
		Scanner chWeightTC = new Scanner(new ByteArrayInputStream(testCase.getBytes()));
		
		String assertResult = "\t舊配分\n"
				+ "\tlab1\t\t123%\n"
				+ "\tlab2\t\t678%\n"
				+ "\tlab3\t\t1011%\n"
				+ "\tmid-term\t1314%\n"
				+ "\tfinal exam\t1617%\n"
				+ "\t輸入新配分(提醒：每項需介於0~100之間，五項總和必須等於100)\n"
				+ "\tlab1            "
				+ "\tlab2            "
				+ "\tlab3            "
				+ "\tmid-term        "
				+ "\tfinal exam      "
				+ "\t請確認新配分\n\tlab1\t\t-100%\n"
				+ "\tlab2\t\t100%\n"
				+ "\tlab3\t\t0%\n"
				+ "\tmid-term\t0%\n"
				+ "\tfinal exam\t0%\n"
				+ "\t以上正確嗎？Y(Yes)或 N(No)"
				+ "\t調整失敗！第1項超出範圍，請重新輸入\n\n"
				+ "更新加權指令已取消!\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		try {
			GradeSystems chWeightGS = new GradeSystems(chWeightTC);
			float[] weights = {123.4f, 678.9f, 1011.12f, 1314.15f, 1617.18f};
			chWeightGS.setWeights(weights);
			chWeightGS.updateWeights(985002017);
			assertEquals(assertResult, outContent.toString());
		} catch (IOException ioe) {
			fail("IOException");
		} finally {
			chWeightTC.close();
		}
	}

	/*
	 * method testGetThisIDName_1()
	 * ---
	 * method getThisIDName(ID)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGS.getThisIDName(985002021)是否與"陳柏彰"相同
	 */
	@Test
	public void testGetThisIDName_1() {
		assertEquals("陳柏彰", testGS.getThisIDName(985002021));
	}
	
	/*
	 * method testGetThisIDName_2()
	 * ---
	 * method getThisIDName(ID)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGS.getThisIDName(985002201)是否與"蘇亮"相同
	 */
	@Test
	public void testGetThisIDName_2() {
		assertEquals("蘇亮", testGS.getThisIDName(985002201));
	}

}
