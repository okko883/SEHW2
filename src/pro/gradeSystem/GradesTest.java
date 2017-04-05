package pro.gradeSystem;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * class GradesTest
 * ---
 * class Grades的JUnit Test
 * 
 * Member Data: 
 * 1. testGd_1 // Grades測試物件1
 * 2. testGd_2 // Grades測試物件2
 * 3. stdin // 修改前的System.in
 * 4. stdout // 修改前的System.out
 * 5. weight_1 // testGd_1的加權
 * 6. weight_2 // testGd_2的加權
 * 
 * Member Function: 
 * 1. setUp() // 開始測試前環境設置
 * 2. tearDown() // 結束測試後清理
 * 3. testGrades_1() // method Grades()測試1
 * 4. testGrades_2() // method Grades()測試2
 * 5. testCalculateTotalGrade_1() // method calculateTotalGrade(weight)測試1
 * 6. testCalculateTotalGrade_2() // method calculateTotalGrade(weight)測試2 
 * 7. testGetName_1() // method getName()測試1
 * 8. testGetName_2() // method getName()測試2
 * 9. testGetID_1() // method getID()測試1
 * 10. testGetID_2() // method getID()測試2
 * 11. testGetLab1_1() // method getLab1()測試1
 * 12. testGetLab1_2() // method getLab1()測試2
 * 13. testGetLab2_1() // method getLab2()測試1
 * 14. testGetLab2_2() // method getLab2()測試2
 * 15. testGetLab3_1() // method getLab3()測試1
 * 16. testGetLab3_2() // method getLab3()測試2
 * 17. testGetMidTerm_1() // method getMidTerm()測試1
 * 18. testGetMidTerm_2() // method getMidTerm()測試2
 * 19. testGetFinalExam_1() // method getFinalExam()測試1
 * 20. testGetFinalExam_2() // method getFinalExam()測試2
 * 21. testGetTotalGrade_1() // method getTotalGrade()測試1
 * 22. testGetTotalGrade_2() // method getTotalGrade()測試2
 */
public class GradesTest {
	private Grades testGd_1, testGd_2;
	private final InputStream stdin = System.in;
	private final PrintStream stdout = System.out;
	private final float[] weight_1 = {0.12f, 0.34f, 0.56f, 0.78f, 0.90f};
	private final float[] weight_2 = {0.1f, 0.1f, 0.1f, 0.3f, 0.4f};

	/*
	 * method setUp()
	 * ---
	 * 開始測試前環境設置
	 * 
	 * Pseudo Code: 
	 * 1. 創建testGd_1
	 * 2. 創建testGd_2
	 */
	@Before
	public void setUp() throws Exception {
		testGd_1 = new Grades(985002504, "張婉庭", 97, 92, 96, 83, 93, weight_1);
		testGd_2 = new Grades(985002201, "蘇亮", 81, 91, 85, 84, 90, weight_2);
	}

	/*
	 * method tearDown()
	 * ---
	 * 結束測試後清理
	 * 
	 * Pseudo Code: 
	 * 1. 將testGd_1及testGd_2的reference設爲null
	 * 2. 將System.in及System.out回復成預設
	 */
	@After
	public void tearDown() throws Exception {
		testGd_1 = null;
		testGd_2 = null;
		System.setIn(stdin);
		System.setOut(stdout);
	}
	
	/*
	 * method testGrades_1()
	 * ---
	 * method Grades()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 呼叫Grades(985002504, "張婉庭", 97, 92, 96, 83, 93, weight_1)，並將創建出來的物件存爲conGd
	 * 2. 檢查conGd.getName()是否爲"張婉庭"
	 * 3. 檢查conGd.getID()是否爲985002504
	 * 4. 檢查conGd.getLab1()是否爲97
	 * 5. 檢查conGd.getLab2()是否爲92
	 * 6. 檢查conGd.getLab3()是否爲96
	 * 7. 檢查conGd.getMidTerm()是否爲83
	 * 8. 檢查conGd.getFinalExam()是否爲93
	 * 9. 檢查conGd.getTotalGrade()是否爲245
	 */
	@Test
	public void testGrades_1() {
		Grades conGd = new Grades(985002504, "張婉庭", 97, 92, 96, 83, 93, weight_1);
		
		assertEquals("張婉庭", conGd.getName());
		assertEquals(985002504, conGd.getID());
		assertEquals(97, conGd.getLab1());
		assertEquals(92, conGd.getLab2());
		assertEquals(96, conGd.getLab3());
		assertEquals(83, conGd.getMidTerm());
		assertEquals(93, conGd.getFinalExam());
		assertEquals(245, conGd.getTotalGrade());
	}

	/*
	 * method testGrades_2()
	 * ---
	 * method Grades()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 呼叫Grades(985002201, "蘇亮", 81, 91, 85, 84, 90, weight_2)，並將創建出來的物件存爲conGd
	 * 2. 檢查conGd.getName()是否爲"蘇亮"
	 * 3. 檢查conGd.getID()是否爲985002201
	 * 4. 檢查conGd.getLab1()是否爲81
	 * 5. 檢查conGd.getLab2()是否爲91
	 * 6. 檢查conGd.getLab3()是否爲85
	 * 7. 檢查conGd.getMidTerm()是否爲84
	 * 8. 檢查conGd.getFinalExam()是否爲90
	 * 9. 檢查conGd.getTotalGrade()是否爲87
	 */
	@Test
	public void testGrades_2() {
		Grades conGd = new Grades(985002201, "蘇亮", 81, 91, 85, 84, 90, weight_2);
		
		assertEquals("蘇亮", conGd.getName());
		assertEquals(985002201, conGd.getID());
		assertEquals(81, conGd.getLab1());
		assertEquals(91, conGd.getLab2());
		assertEquals(85, conGd.getLab3());
		assertEquals(84, conGd.getMidTerm());
		assertEquals(90, conGd.getFinalExam());
		assertEquals(87, conGd.getTotalGrade());
	}
	
	/*
	 * method testCalculateTotalGrade_1()
	 * ---
	 * method calculateTotalGrade(weight)測試1
	 * 
	 * Pseudo Code: 
	 * 1. 令testGd_1的totalGrade以{0f, 0f, 0f, 0f, 0f, 0f}計算
	 * 2. 檢查testGd_1.getTotalGrade()是否爲0
	 * 3. 復原testGd_1的totalGrade以利後續測試進行
	 */
	@Test
	public void testCalculateTotalGrade_1() {
		float[] weight = {0f, 0f, 0f, 0f, 0f, 0f};
		testGd_1.calculateTotalGrade(weight);
		assertEquals(0, testGd_1.getTotalGrade());
		testGd_1.calculateTotalGrade(weight_1);
	}
	
	/*
	 * method testCalculateTotalGrade_2()
	 * ---
	 * method calculateTotalGrade(weight)測試2
	 * 
	 * Pseudo Code: 
	 * 1. 令testGd_1的totalGrade以weight_2計算
	 * 2. 檢查testGd_1.getTotalGrade()是否爲91
	 * 3. 復原testGd_1的totalGrade以利後續測試進行
	 */
	@Test
	public void testCalculateTotalGrade_2() {
		testGd_1.calculateTotalGrade(weight_2);
		assertEquals(91, testGd_1.getTotalGrade());
		testGd_1.calculateTotalGrade(weight_1);
	}

	/*
	 * method testGetName_1()
	 * ---
	 * method getName()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getName()是否爲"張婉庭"
	 */
	@Test
	public void testGetName_1() {
		assertEquals("張婉庭", testGd_1.getName());
	}
	
	/*
	 * method testGetName_2()
	 * ---
	 * method getName()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getName()是否爲"蘇亮"
	 */
	@Test
	public void testGetName_2() {
		assertEquals("蘇亮", testGd_2.getName());
	}

	/*
	 * method testGetID_1()
	 * ---
	 * method getID()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getID()是否爲985002504
	 */
	@Test
	public void testGetID_1() {
		assertEquals(985002504, testGd_1.getID());
	}
	
	/*
	 * method testGetID_2()
	 * ---
	 * method getID()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getID()是否爲985002201
	 */
	@Test
	public void testGetID_2() {
		assertEquals(985002201, testGd_2.getID());
	}

	/*
	 * method testGetLab1_1()
	 * ---
	 * method getLab1()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getLab1()是否爲97
	 */
	@Test
	public void testGetLab1_1() {
		assertEquals(97, testGd_1.getLab1());
	}
	
	/*
	 * method testGetLab1_2()
	 * ---
	 * method getLab1()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getLab1()是否爲81
	 */
	@Test
	public void testGetLab1_2() {
		assertEquals(81, testGd_2.getLab1());
	}

	/*
	 * method testGetLab2_1()
	 * ---
	 * method getLab2()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getLab2()是否爲92
	 */
	@Test
	public void testGetLab2_1() {
		assertEquals(92, testGd_1.getLab2());
	}

	/*
	 * method testGetLab2_2()
	 * ---
	 * method getLab2()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getLab2()是否爲91
	 */
	@Test
	public void testGetLab2_2() {
		assertEquals(91, testGd_2.getLab2());
	}
	
	/*
	 * method testGetLab1_1()
	 * ---
	 * method getLab3()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getLab3()是否爲96
	 */
	@Test
	public void testGetLab3_1() {
		assertEquals(96, testGd_1.getLab3());
	}
	
	/*
	 * method testGetLab3_2()
	 * ---
	 * method getLab3()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getLab3()是否爲85
	 */
	@Test
	public void testGetLab3_2() {
		assertEquals(85, testGd_2.getLab3());
	}

	/*
	 * method testGetMidTerm_1()
	 * ---
	 * method getMidTerm()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getMidTerm()是否爲83
	 */
	@Test
	public void testGetMidTerm_1() {
		assertEquals(83, testGd_1.getMidTerm());
	}
	
	/*
	 * method testGetMidTerm_2()
	 * ---
	 * method getMidTerm()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getMidTerm()是否爲84
	 */
	@Test
	public void testGetMidTerm_2() {
		assertEquals(84, testGd_2.getMidTerm());
	}

	/*
	 * method testGetFinalExam_1()
	 * ---
	 * method getFinalExam()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getFinalExam()是否爲93
	 */
	@Test
	public void testGetFinalExam_1() {
		assertEquals(93, testGd_1.getFinalExam());
	}
	
	/*
	 * method testGetFinalExam_2()
	 * ---
	 * method getFinalExam()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getFinalExam()是否爲90
	 */
	@Test
	public void testGetFinalExam_2() {
		assertEquals(90, testGd_2.getFinalExam());
	}

	/*
	 * method testGetTotalGrade_1()
	 * ---
	 * method getTotalGrade()測試1
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_1.getTotalGrade()是否爲245
	 */
	@Test
	public void testGetTotalGrade_1() {
		assertEquals(245, testGd_1.getTotalGrade());
	}
	
	/*
	 * method testGetTotalGrade_2()
	 * ---
	 * method getTotalGrade()測試2
	 * 
	 * Pseudo Code: 
	 * 1. 檢查testGd_2.getTotalGrade()是否爲87
	 */
	@Test
	public void testGetTotalGrade_2() {
		assertEquals(87, testGd_2.getTotalGrade());
	}

}
