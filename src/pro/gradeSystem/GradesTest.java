package pro.gradeSystem;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradesTest {
	private Grades testGd_1, testGd_2;
	private final InputStream stdin = System.in;
	private final PrintStream stdout = System.out;
	private final float[] weight_1 = {0.12f, 0.34f, 0.56f, 0.78f, 0.90f};
	private final float[] weight_2 = {0.1f, 0.1f, 0.1f, 0.3f, 0.4f};

	@Before
	public void setUp() throws Exception {
		testGd_1 = new Grades(985002504, "張婉庭", 97, 92, 96, 83, 93, weight_1);
		testGd_2 = new Grades(985002201, "蘇亮", 81, 91, 85, 84, 90, weight_2);
	}

	@After
	public void tearDown() throws Exception {
		testGd_1 = null;
		testGd_2 = null;
		System.setIn(stdin);
		System.setOut(stdout);
	}
	
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
	
	@Test
	public void testCalculateTotalGrade_1() {
		float[] weight = {0f, 0f, 0f, 0f, 0f, 0f};
		testGd_1.calculateTotalGrade(weight);
		assertEquals(0, testGd_1.getTotalGrade());
		testGd_1.calculateTotalGrade(weight_1);
	}
	
	@Test
	public void testCalculateTotalGrade_2() {
		testGd_1.calculateTotalGrade(weight_2);
		assertEquals(91, testGd_1.getTotalGrade());
		testGd_1.calculateTotalGrade(weight_1);
	}

	@Test
	public void testGetName_1() {
		assertEquals("張婉庭", testGd_1.getName());
	}
	
	@Test
	public void testGetName_2() {
		assertEquals("蘇亮", testGd_2.getName());
	}

	@Test
	public void testGetID_1() {
		assertEquals(985002504, testGd_1.getID());
	}
	
	@Test
	public void testGetID_2() {
		assertEquals(985002201, testGd_2.getID());
	}

	@Test
	public void testGetLab1_1() {
		assertEquals(97, testGd_1.getLab1());
	}
	
	@Test
	public void testGetLab1_2() {
		assertEquals(81, testGd_2.getLab1());
	}

	@Test
	public void testGetLab2_1() {
		assertEquals(92, testGd_1.getLab2());
	}

	@Test
	public void testGetLab2_2() {
		assertEquals(91, testGd_2.getLab2());
	}
	
	@Test
	public void testGetLab3_1() {
		assertEquals(96, testGd_1.getLab3());
	}
	
	@Test
	public void testGetLab3_2() {
		assertEquals(85, testGd_2.getLab3());
	}

	@Test
	public void testGetMidTerm_1() {
		assertEquals(83, testGd_1.getMidTerm());
	}
	
	@Test
	public void testGetMidTerm_2() {
		assertEquals(84, testGd_2.getMidTerm());
	}

	@Test
	public void testGetFinalExam_1() {
		assertEquals(93, testGd_1.getFinalExam());
	}
	
	@Test
	public void testGetFinalExam_2() {
		assertEquals(90, testGd_2.getFinalExam());
	}

	@Test
	public void testGetTotalGrade_1() {
		assertEquals(245, testGd_1.getTotalGrade());
	}
	
	@Test
	public void testGetTotalGrade_2() {
		assertEquals(87, testGd_2.getTotalGrade());
	}

}
