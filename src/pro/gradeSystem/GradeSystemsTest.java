package pro.gradeSystem;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
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
		Scanner console = new Scanner(System.in);
		testGS = new GradeSystems(console);
	}

	@After
	public void tearDown() throws Exception {
		testGS = null;
		System.setIn(stdin);
		System.setOut(stdout);
	}

	@Test
	public void testGradeSystems() {
		fail("Not yet implemented");
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
	public void testUpdateWeights() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetWeights() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetThisIDName() {
		fail("Not yet implemented");
	}

}
