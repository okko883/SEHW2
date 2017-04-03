package pro.gradeSystem;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class GradeSystems {
	private float[] weights = {0.1f, 0.1f, 0.1f, 0.3f, 0.4f};
	private HashMap<Integer, Grades> aList = new HashMap<>();
	
	public GradeSystems() throws NumberFormatException, IOException {
		String path = "gradeinput.txt";
		File file = new File(path);
		InputStreamReader inReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(inReader);
		String inputLine, tmpLine;
		String[] tmpArray = new String[7];
		
		while((inputLine = br.readLine()) != null){
			tmpLine = inputLine;
			tmpArray = tmpLine.split("\\s");
			
			int ID = Integer.parseInt(tmpArray[0]);
			String name = tmpArray[1];
			int lab1 = Integer.parseInt(tmpArray[2]);
			int lab2 = Integer.parseInt(tmpArray[3]);
			int lab3 = Integer.parseInt(tmpArray[4]);
			int midTerm = Integer.parseInt(tmpArray[5]);
			int finalExam = Integer.parseInt(tmpArray[6]);
			
			Grades aGrade = new Grades(ID, name, lab1, lab2, lab3, midTerm, finalExam, weights);
			aList.put(ID, aGrade);
		}
	}
	public boolean containsID(int ID) {
		return aList.containsKey(ID);
	}
	public void showGrade(int ID) {
		Grades aGrade = aList.get(ID);
		System.out.printf("%3s成績：lab1：\t\t%s\n", aGrade.getName(), passOrNot(aGrade.getLab1()));
		System.out.printf("\tlab2：\t\t%s\n", passOrNot(aGrade.getLab2()));
		System.out.printf("\tlab3：\t\t%s\n", passOrNot(aGrade.getLab2()));
		System.out.printf("\tmid-term：\t%s\n", passOrNot(aGrade.getMidTerm()));
		System.out.printf("\tfinal exam：\t%s\n", passOrNot(aGrade.getFinalExam()));
		System.out.printf("\ttotal grade：\t%s\n", passOrNot(aGrade.getTotalGrade()));
	}
	private String passOrNot(int grade) {
		if (grade >= 60) return Integer.toString(grade);
		else return Integer.toString(grade) + "*";
	}
	
	public void showRank(int ID) {
		int rank = 1;
		Grades aGrade = aList.get(ID);
		
		for (Grades value: aList.values()) {
			if (aGrade.getTotalGrade() < value.getTotalGrade())
				++rank;
		}
		System.out.printf("%3s排名第%d\n", aGrade.getName(), rank);
	}
	
	public void showAverage() {
		float[] grades = new float[6];
		for (Grades value: aList.values()) {
			grades[0] += value.getLab1();
			grades[1] += value.getLab2();
			grades[2] += value.getLab3();
			grades[3] += value.getMidTerm();
			grades[4] += value.getFinalExam();
			grades[5] += value.getTotalGrade();
		}
		calculateAndShowAverage(grades);
	}
	private void calculateAndShowAverage(float[] grades) {
		for (int i = 0; i < 6; i++) {
			grades[i] /= aList.size();
		}
		System.out.printf("班平均：\tlab1：\t\t%d\n", (int)(Math.round(grades[0])));
		System.out.printf("\tlab2：\t\t%d\n", (int)(Math.round(grades[1])));
		System.out.printf("\tlab3：\t\t%d\n", (int)(Math.round(grades[2])));
		System.out.printf("\tmid-term：\t%d\n", (int)(Math.round(grades[3])));
		System.out.printf("\tfinal exam：\t%d\n", (int)(Math.round(grades[4])));
		System.out.printf("\ttotal grade：\t%d\n", (int)(Math.round(grades[5])));
	}
	public void updateWeights(int ID){
		showOldWeights();
		float[] newWeights = getNewWeights();
		if (checkNewWeights(newWeights)) {
			setWeights(newWeights);
			aList.get(ID).calculateTotalGrade(weights);
			System.out.println("加權已更新!");	
		}else{
			System.out.println("更新加權指令已取消!");
		}
	}
	private void showOldWeights(){
		System.out.println("\t舊配分");
		System.out.printf("\tlab1\t\t%d%%\n", (int)(weights[0]*100));
		System.out.printf("\tlab2\t\t%d%%\n", (int)(weights[1]*100));
		System.out.printf("\tlab3\t\t%d%%\n", (int)(weights[2]*100));
		System.out.printf("\tmid-term\t%d%%\n", (int)(weights[3]*100));
		System.out.printf("\tfinal exam\t%d%%\n", (int)(weights[4]*100));
	}
	private float[] getNewWeights() {
		float[] newWeights = new float[5];
		
		System.out.println("\t輸入新配分(提醒：每項需介於0~100之間，五項總和必須等於100)");
		newWeights[0] = getSpecificWeight("lab1");
		newWeights[1] = getSpecificWeight("lab2");
		newWeights[2] = getSpecificWeight("lab3");
		newWeights[3] = getSpecificWeight("mid-term");
		newWeights[4] = getSpecificWeight("final exam");
		return newWeights;
	}
	
	private float getSpecificWeight(String quiz) {
		Scanner console = new Scanner(System.in);
		System.out.printf("\t%-16s", quiz);
		return console.nextFloat();
	}
	
	public void setWeights(float[] newWeights) {
		for (int i = 0; i < 5; i++) {
			weights[i] = newWeights[i] / 100.0f;
		}
	}
	
	private boolean checkNewWeights(float[] newWeights) {
		Scanner console = new Scanner(System.in);
		
		System.out.printf("\t請確認新配分\n\tlab1\t\t%d%%\n", (int)(newWeights[0]));
		System.out.printf("\tlab2\t\t%d%%\n", (int)(newWeights[1]));
		System.out.printf("\tlab3\t\t%d%%\n", (int)(newWeights[2]));
		System.out.printf("\tmid-term\t%d%%\n", (int)(newWeights[3]));
		System.out.printf("\tfinal exam\t%d%%\n", (int)(newWeights[4]));
		System.out.printf("\t以上正確嗎？Y(Yes)或 N(No)");
		
		String cmd = console.nextLine().toLowerCase();
		if (cmd.equals("y") || cmd.equals("yes")) return inRangeOrNot(newWeights);
		else return false;
	}
	
	private boolean inRangeOrNot(float[] newWeights) {
		int total = 0;
		for (int i = 0; i < 5; i++) {
			total += (int)newWeights[i];
			if ((int)newWeights[i] < 0 || (int)newWeights[i] > 100) {
				System.out.printf("\t調整失敗！第%d項超出範圍，請重新輸入\n\n", i+1);
				return false;
			}
		}
		if (total == 100) return true;
		else {
			System.out.print("\t調整失敗！五項總和不等於100，請重新輸入\n\n");
			return false;
		}
}

	public String getThisIDName(int ID) {
		return aList.get(ID).getName();
	}
}
