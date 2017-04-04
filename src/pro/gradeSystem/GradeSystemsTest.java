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

public class GradeSystemsTest {
	private GradeSystems testGS;
	private final InputStream stdin = System.in;
	private final PrintStream stdout = System.out;

	@Before
	public void setUp() throws Exception {
		testGS = new GradeSystems(new Scanner(System.in));
	}

	@After
	public void tearDown() throws Exception {
		testGS = null;
		System.setIn(stdin);
		System.setOut(stdout);
	}

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

	@Test
	public void testContainsID_1() {
		assertTrue(testGS.containsID(955002056));
	}
	
	@Test
	public void testContainsID_2() {
		assertFalse(testGS.containsID(100000000));
	}

	@Test
	public void testShowGrade_1() {
		String assertResult = "商揚夏成績：lab1：\t\t85\n"
				+ "\tlab2：\t\t86\n"
				+ "\tlab3：\t\t86\n"
				+ "\tmid-term：\t81\n"
				+ "\tfinal exam：\t88\n"
				+ "\ttotal grade：\t85\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showGrade(965002044);
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testShowGrade_2() {
		String assertResult = " 蘇亮成績：lab1：\t\t81\n"
				+ "\tlab2：\t\t91\n"
				+ "\tlab3：\t\t91\n"
				+ "\tmid-term：\t84\n"
				+ "\tfinal exam：\t90\n"
				+ "\ttotal grade：\t87\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showGrade(985002201);
		assertEquals(assertResult, outContent.toString());
	}

	@Test
	public void testShowRank_1() {
		String assertResult = "周信彰排名第22\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showRank(985002018);
		assertEquals(assertResult, outContent.toString());
	}
	
	@Test
	public void testShowRank_2() {
		String assertResult = "魏秀龍排名第52\n";
		ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		testGS.showRank(985002024);
		assertEquals(assertResult, outContent.toString());
	}

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

	@Test
	public void testGetThisIDName_1() {
		assertEquals("陳柏彰", testGS.getThisIDName(985002021));
	}
	
	@Test
	public void testGetThisIDName_2() {
		assertEquals("蘇亮", testGS.getThisIDName(985002201));
	}

}
