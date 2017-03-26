package pro.gradeSystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeSystems {
	private float[] weights = {(float) 0.1,(float) 0.1,(float) 0.1,(float) 0.3,(float) 0.4};
	private HashMap<Integer,Grades> aList = new HashMap<>();
	
	public GradeSystems() throws NumberFormatException, IOException{
		String path = "gradeinput.txt";
		File file = new File(path);
		InputStreamReader inReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
		BufferedReader br = new BufferedReader(inReader);
		String inputLine, tmpLine;
		String[] tmpArray = new String[7];
		while((inputLine = br.readLine())!=null){
			tmpLine = inputLine;
			tmpArray = tmpLine.split("\\s");
			int ID = Integer.parseInt(tmpArray[0]);
			String name = tmpArray[1];
			int lab1 = Integer.parseInt(tmpArray[2]);
			int lab2 = Integer.parseInt(tmpArray[3]);
			int lab3 = Integer.parseInt(tmpArray[4]);
			int midTerm = Integer.parseInt(tmpArray[5]);
			int finalExam = Integer.parseInt(tmpArray[6]);
			
			Grades aGrade = new Grades(ID,name,lab1,lab2,lab3,midTerm,finalExam,weights);
			aList.put(ID, aGrade);
		}
		
	}
	public boolean containsID(int ID){
		return aList.containsKey(ID);
	}
	public void showGrade(int ID){
		Grades aGrade = aList.get(ID);
		System.out.printf("%3s成績    lab1:\t\t%s", aGrade.getName(), passOrNot(aGrade.getLab1()));
		System.out.printf("        lab2:\t\t%s", passOrNot(aGrade.getLab2()));
		System.out.printf("        lab3:\t\t%s", passOrNot(aGrade.getLab2()));
		System.out.printf("        mid-term:\t%s", passOrNot(aGrade.getMidTerm()));
		System.out.printf("        final exam:\t%s", passOrNot(aGrade.getFinalExam()));
		System.out.printf("        total grade:\t%s", passOrNot(aGrade.getTotalGrade()));
	}
	private String passOrNot(int grade){
		if(grade>=60)	return Integer.toString(grade) + "\n";
		else	return Integer.toString(grade) + "*\n";
	}
	
	public void showRank(int ID){
		int rank = 1;
		Grades aGrade = aList.get(ID);
		for(Grades value: aList.values()){
			if(aGrade.getTotalGrade()<value.getTotalGrade())
				++rank;
		}
		System.out.printf("%3s排名為第%d\n", aGrade.getName(), rank);
	}
	public void showAverage(){
		float[] grades = new float[5];
		for(Grades value: aList.values()){
			grades[0] += value.getLab1();
			grades[1] += value.getLab2();
			grades[2] += value.getLab3();
			grades[3] += value.getMidTerm();
			grades[4] += value.getFinalExam();
		}
		calculateAndShowAverage(grades);
	}
	private void calculateAndShowAverage(float[] grades){
		for(int i=0;i<5;i++){
			grades[i] /= aList.size();
		}
		System.out.printf("班平均: lab1:\t %d\n", (int)(Math.round(grades[0])));
		System.out.printf("      lab2:\t %d\n", (int)(Math.round(grades[1])));
		System.out.printf("      lab3:\t %d\n", (int)(Math.round(grades[2])));
		System.out.printf("      mid-term:\t %d\n", (int)(Math.round(grades[3])));
		System.out.printf("      final exam: %d\n", (int)(Math.round(grades[4])));
	}
	public void updateWeights(){
		showOldWeights();
		while(true){
			float[] newWeights = getNewWeights();
			if(checkNewWeights(newWeights)){
				setWeights(newWeights);
				System.out.println("加權已更新!");
				return;
			}
		}	
	}
	private void showOldWeights(){
		System.out.println("舊配分");
		System.out.printf("\tlab1\t\t\t%d%%\n", (int)(weights[0]*100));
		System.out.printf("\tlab2\t\t\t%d%%\n", (int)(weights[1]*100));
		System.out.printf("\tlab3\t\t\t%d%%\n", (int)(weights[2]*100));
		System.out.printf("\tmid-term\t\t%d%%\n", (int)(weights[3]*100));
		System.out.printf("\tfinal exam\t\t%d%%\n", (int)(weights[4]*100));
	}
	private float[] getNewWeights(){
		Scanner console = new Scanner(System.in);
		System.out.println("輸入新配分(由左而右依序輸入 lab1 lab2 lab3 mid-term finalexam，以空白分開):");
		float[] newWeights = new float[5];
		for(int i=0;i<5;i++){
			newWeights[i] = console.nextFloat();
		}
		return newWeights;
	}
	private void setWeights(float[] newWeights){
		for(int i=0;i<5;i++){
			weights[i] = newWeights[i];
		}
	}
	private boolean checkNewWeights(float[] newWeights){
		System.out.printf("請確認新配分\n\tlab1\t\t\t%d%%\n", (int)(newWeights[0]));
		System.out.printf("\tlab2\t\t\t%d%%\n", (int)(newWeights[1]));
		System.out.printf("\tlab3\t\t\t%d%%\n", (int)(newWeights[2]));
		System.out.printf("\tmid-term\t\t%d%%\n", (int)(newWeights[3]));
		System.out.printf("\tfinal exam\t\t%d%%\n", (int)(newWeights[4]));
		System.out.print("以上正確嗎？Y(Yes) 或 N(No) (輸入錯誤指令視同重新輸入)");
		Scanner console = new Scanner(System.in);
		String cmd = console.nextLine().toLowerCase();
		if(cmd.equals("y")||cmd.equals("yes"))	return true;
		else	return false;
	}
	public String getThisIDName(int ID){
		return aList.get(ID).getName();
	}
}
