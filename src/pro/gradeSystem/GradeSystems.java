package pro.gradeSystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
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
		System.out.printf("%3s成績：\tlab1：\t%s\n", aGrade.getName(), passOrNot(aGrade.getLab1()));
		System.out.printf("\t\tlab2：\t%s\n", passOrNot(aGrade.getLab2()));
		System.out.printf("\t\tlab3：\t%s\n", passOrNot(aGrade.getLab2()));
		System.out.printf("\t\tmid-term：\t%s\n", passOrNot(aGrade.getMidTerm()));
		System.out.printf("\t\tfinal exam：\t%s\n", passOrNot(aGrade.getFinalExam()));
		System.out.printf("\t\ttotal grade：\t%s\n", passOrNot(aGrade.getTotalGrade()));
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
		float[] grades = new float[5];
		for (Grades value: aList.values()) {
			grades[0] += value.getLab1();
			grades[1] += value.getLab2();
			grades[2] += value.getLab3();
			grades[3] += value.getMidTerm();
			grades[4] += value.getFinalExam();
		}
		calculateAndShowAverage(grades);
	}
	private void calculateAndShowAverage(float[] grades) {
		for (int i=0;i<5;i++) {
			grades[i] /= aList.size();
		}
		System.out.printf("班平均：\tlab1：\t%d\n", (int)(Math.round(grades[0])));
		System.out.printf("\tlab2：\t%d\n", (int)(Math.round(grades[1])));
		System.out.printf("\tlab3：\t%d\n", (int)(Math.round(grades[2])));
		System.out.printf("\tmid-term：\t%d\n", (int)(Math.round(grades[3])));
		System.out.printf("\tfinal exam：\t%d\n", (int)(Math.round(grades[4])));
	}
	public void updateWeights(){
		showOldWeights();
		while (true) {
			float[] newWeights = getNewWeights();
			if(checkNewWeights(newWeights)){
				setWeights(newWeights);
				System.out.println("加權已更新!");
				return;
			}
		}	
	}
	private void showOldWeights(){
		System.out.println("\t舊配分");
		System.out.printf("\tlab1\t%d%%\n", (int)(weights[0]*100));
		System.out.printf("\tlab2\t%d%%\n", (int)(weights[1]*100));
		System.out.printf("\tlab3\t%d%%\n", (int)(weights[2]*100));
		System.out.printf("\tmid-term\t%d%%\n", (int)(weights[3]*100));
		System.out.printf("\tfinal exam\t%d%%\n", (int)(weights[4]*100));
	}
	private float[] getNewWeights() {
		Scanner console = new Scanner(System.in);
		float[] newWeights = new float[5];
		
		System.out.println("\t輸入新配分");
		System.out.printf("\tlab1\t");
		newWeights[0] = console.nextFloat();
		System.out.printf("\tlab2\t");
		newWeights[1] = console.nextFloat();
		System.out.printf("\tlab3\t");
		newWeights[2] = console.nextFloat();
		System.out.printf("\tmid-term\t");
		newWeights[3] = console.nextFloat();
		System.out.printf("\tfinal exam\t");
		newWeights[4] = console.nextFloat();
		return newWeights;
	}
	
	private void setWeights(float[] newWeights) {
		for (int i = 0; i < 5; i++) {
			weights[i] = newWeights[i] / 100.0f;
		}
	}
	
	private boolean checkNewWeights(float[] newWeights) {
		Scanner console = new Scanner(System.in);
		
		System.out.printf("\t請確認新配分\n\tlab1\t%d%%\n", (int)(newWeights[0]));
		System.out.printf("\tlab2\t%d%%\n", (int)(newWeights[1]));
		System.out.printf("\tlab3\t%d%%\n", (int)(newWeights[2]));
		System.out.printf("\tmid-term\t%d%%\n", (int)(newWeights[3]));
		System.out.printf("\tfinal exam\t%d%%\n", (int)(newWeights[4]));
		System.out.printf("\t以上正確嗎？Y(Yes) 或 N(No)\n使用者輸入：");
		
		String cmd = console.nextLine();
		if (cmd.equals("Y") || cmd.equals("Yes")) return true;
		else return false;
	}
	
	public String getThisIDName(int ID) {
		return aList.get(ID).getName();
	}
}
