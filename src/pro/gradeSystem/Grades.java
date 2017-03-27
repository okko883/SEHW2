package pro.gradeSystem;

public class Grades {
	private int ID;
	String name;
	int lab1, lab2, lab3, midTerm, finalExam, totalGrade;
	
	public Grades(int ID, String name, int lab1, int lab2, int lab3, int midTerm, int finalExam, float[] weight) {
		this.ID = ID;
		this.name = name;
		this.lab1 = lab1;
		this.lab2 = lab2;
		this.lab3 = lab3;
		this.midTerm = midTerm;
		this.finalExam = finalExam;
		calculateTotalGrade(weight);
	}
	
	public void calculateTotalGrade(float[] weight) {
		float tmp = lab1 * weight[0] + lab2 * weight[1] + lab3 * weight[2] + midTerm * weight[3] + finalExam * weight[4];
		totalGrade = (int)(Math.round(tmp));
	}
	
	public String getName() {
		return name;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getLab1() {
		return lab1;
	}
	
	public int getLab2() {
		return lab2;
	}
	
	public int getLab3() {
		return lab3;
	}
	
	public int getMidTerm() {
		return midTerm;
	}
	
	public int getFinalExam() {
		return finalExam;
	}
	
	public int getTotalGrade() {
		return totalGrade;
	}
}
