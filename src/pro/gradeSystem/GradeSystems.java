package pro.gradeSystem;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/*
 * class GradeSystems
 * ---
 * 爲成績資料庫，透過定義好的method來存取
 * 
 * Member Data: 
 * 1. weights // 本系統的加權數值
 * 2. aList // 本成績資料庫儲存成績資料的物件
 * 3. console // 終端輸入的處理物件
 * 
 * Member Function: 
 * 1. GradeSystems(parentConsole) // 建構子，將讀取gradeinput.txt內的資料建構本成績資料庫
 * 2. containsID(ID) // 查詢本系統是否有ID這筆資料
 * 3. showGrade(ID) // 印出ID這筆資料的成績
 * 4. passOrNot(grade) // 爲印出的成績進行文字裝飾
 * 5. showRank(ID) // 印出ID在本資料庫中的排名
 * 6. showAverage() // 印出本資料庫所有成績的平均
 * 7. calculateAndShowAverage(grades) // 計算並印出本資料庫所有項目的平均
 * 8. updateWeights(ID) // 更新本資料庫的加權數值，並爲ID即時重新計算加權成績
 * 9. showOldWeights() // 印出現有的加權數值
 * 10. getNewWeights() // 要求使用者輸入新的加權數值
 * 11. getSpecificWeight(quiz) // 要求使用者爲quiz輸入新的加權數值
 * 12. setWeights(newWeights) // 將資料庫內的加權數值更新爲newWeights
 * 13. checkNewWeights(newWeights) // 向使用者確認新的加權數值
 * 14. inRangeOrNot(newWeights) // 檢查newWeights是否是合法的加權數值
 * 15. getThisIDName(ID) // 取得ID對應的名字
 */
public class GradeSystems {
	/**
	 * @uml.property  name="weights" multiplicity="(0 -1)" dimension="1"
	 */
	private float[] weights = {0.1f, 0.1f, 0.1f, 0.3f, 0.4f};
	/**
	 * @uml.property  name="aList"
	 * @uml.associationEnd  qualifier="valueOf:java.lang.Integer pro.gradeSystem.Grades"
	 */
	private HashMap<Integer, Grades> aList = new HashMap<>();
	/**
	 * @uml.property  name="console"
	 */
	private Scanner console;
	
	/*
	 * method GradeSystems()
	 * ---
	 * 建構子，將讀取gradeinput.txt內的資料建構本成績資料庫
	 * 
	 * @throws NumberFormatException 若使用者在應該輸入數字的情況下輸入其他字元，則丟出此錯誤
	 * @throws IOException 若找不到gradeinput.txt，則丟出此錯誤
	 * 
	 * Pseudo Code: 
	 * 1. 開啓gradeinput.txt
	 * 2. while gradeinput.txt尚未讀取到結束
	 * 	1. 從gradeinput.txt讀取一行資料，並將各項數值存在tmpArray
	 * 	2. 建立與tmpArray對應的Grades項目
	 * 	3. 將新建好的Grades項目放入aList
	 * 
	 * Time Estimate: O(n)
	 * Example: GradeSystems aGradeSystems = new GradeSystems(); 將創造一個GradeSystems，並讀取gradeinput.txt內的資料建構一個成績資料庫
	 */
	public GradeSystems(Scanner parentConsole) throws NumberFormatException, IOException {
		console = parentConsole;
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
		br.close();
	}
	
	/*
	 * method constainsID(ID)
	 * ---
	 * 查詢是否有ID這筆資料
	 * 
	 * @param ID 欲查詢的ID
	 * @return Boolean，若爲true則有，否則無
	 * 
	 * Pseudo Code: 
	 * 1. 回傳aList.containsKey(ID);
	 * 
	 * Time Estimate: O(1)
	 * Example: aGradeSystem.containsID(955002056); 回傳true
	 */
	public boolean containsID(int ID) {
		return aList.containsKey(ID);
	}
	
	/*
	 * method showGrade(ID)
	 * ---
	 * 顯示ID的各科成績
	 * 
	 * @param ID 欲查詢的ID
	 * 
	 * Pseudo Code: 
	 * 1. 從aList中抓出ID代表的Grades物件
	 * 2. 印出經過文字裝飾的成績訊息
	 * 
	 * Time Estimate: O(1)
	 * Example: aGradeSystem.showGrade(955002056); 印出「許文馨成績：lab1：88...」
	 */
	public void showGrade(int ID) {
		Grades aGrade = aList.get(ID);
		System.out.printf("%3s成績：lab1：\t\t%s\n", aGrade.getName(), passOrNot(aGrade.getLab1()));
		System.out.printf("\tlab2：\t\t%s\n", passOrNot(aGrade.getLab2()));
		System.out.printf("\tlab3：\t\t%s\n", passOrNot(aGrade.getLab3()));
		System.out.printf("\tmid-term：\t%s\n", passOrNot(aGrade.getMidTerm()));
		System.out.printf("\tfinal exam：\t%s\n", passOrNot(aGrade.getFinalExam()));
		System.out.printf("\ttotal grade：\t%s\n", passOrNot(aGrade.getTotalGrade()));
	}
	
	/*
	 * method passOrNot(grade)
	 * ---
	 * 若成績不及格，則在該項目後面加上星號，反之則無
	 * 
	 * @param grade 要經過文字裝飾的原始成績
	 * @return String，將grade轉爲String後，若原始成績不及格，則此String後面會有星號，反之則無
	 * 
	 * Pseudo Code: 
	 * 1. 判斷grade是否及格
	 * 	1. 若是及格，則回傳grade的字串，若非，則在此字串後加上星號
	 * 
	 * Example: passOrNot(59); 則回傳"59*"
	 */
	private String passOrNot(int grade) {
		if (grade >= 60) return Integer.toString(grade);
		else return Integer.toString(grade) + "*";
	}
	
	/*
	 * method showRank(ID)
	 * ---
	 * 印出ID的成績排名
	 * 
	 * @param ID 欲查詢的ID
	 * 
	 * Pseudo Code: 
	 * 1. rank = 0;
	 * 2. 從aList抓出ID代表的Grades物件
	 * 3. 將此Grades物件的加權成績和其他所有的Grades物件的加權成績一一比較
	 * 	1. 若ID的Grades物件的加權成績較某一Grades物件高，則rank遞增1
	 * 4. 印出排名
	 * 
	 * Time Estimate: O(n)
	 * Example: aGradeSystem.showRank(955002056); 印出"許文馨排名第9"
	 */
	public void showRank(int ID) {
		int rank = 1;
		Grades aGrade = aList.get(ID);
		
		for (Grades value: aList.values()) {
			if (aGrade.getTotalGrade() < value.getTotalGrade())
				++rank;
		}
		System.out.printf("%3s排名第%d\n", aGrade.getName(), rank);
	}
	
	/*
	 * method showAverage()
	 * ---
	 * 印出本資料庫所有成績的平均
	 * 
	 * Pseudo Code: 
	 * 1. 創建一個float陣列代表各科所有的成績綜合
	 * 2. 將aList中所有的Grades物件掃過一次，在過程中將Grades物件的各科成績加到對應的float中
	 * 3. 計算平均並印出
	 * 
	 * Time Estimate: O(n)
	 * Example: aGradeSystem.showAverage(); 印出「班平均：	lab1：		90...」
	 */
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
	
	/*
	 * method calculateAndShowAverage(grades)
	 * ---
	 * 計算並印出各科成績的班平均
	 * 
	 * @param grades 各科成績的綜合
	 * 
	 * Pseudo Code: 
	 * 1. 將grades陣列的所有項目除以總人數
	 * 2. 印出各科成績的班平均
	 * 
	 * Example: calculateAndShowAverage({90f, 80f, 70f, 60f, 50f}); 印出「班平均：	lab1：		90...」
	 */
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
	
	/*
	 * method updateWeights(ID)
	 * ---
	 * 更新加權數值
	 * 
	 * @param ID 欲即時更新的ID
	 * 
	 * Pseudo Code: 
	 * 1. 印出現有的加權數值
	 * 2. 向使用者要求輸入新的加權數值
	 * 3. 檢查使用者輸入的數值是否合法
	 * 	1. 若合法，則更新系統內的加權數值，並計算ID的新成績；若不合法，則取消這次的更新
	 * 
	 * Time Estimate: (Depend on input)
	 * Example: aGradeSystem.updateWeights(955002056); 則先印出「舊配分...」並要求使用者輸入數值，若輸入20 20 20 20 20，則在經過確認無誤後，印出「加權已更新!」
	 */
	public void updateWeights(int ID) {
		showOldWeights();
		float[] newWeights = getNewWeights();
		if (checkNewWeights(newWeights)) {
			setWeights(newWeights);
			aList.get(ID).calculateTotalGrade(weights);
			System.out.println("加權已更新!");	
		} else {
			System.out.println("更新加權指令已取消!");
		}
	}
	
	/*
	 * method showOldWeights()
	 * ---
	 * 印出現有的加權數值
	 * 
	 * Pseudo Code: 
	 * 1. 印出現有的加權數值
	 * 
	 * Example: showOldWeights(); 印出「舊配分...lab1...」
	 */
	private void showOldWeights() {
		System.out.println("\t舊配分");
		System.out.printf("\tlab1\t\t%d%%\n", (int)(weights[0]*100));
		System.out.printf("\tlab2\t\t%d%%\n", (int)(weights[1]*100));
		System.out.printf("\tlab3\t\t%d%%\n", (int)(weights[2]*100));
		System.out.printf("\tmid-term\t%d%%\n", (int)(weights[3]*100));
		System.out.printf("\tfinal exam\t%d%%\n", (int)(weights[4]*100));
	}
	
	/*
	 * method getNewWeights()
	 * ---
	 * 向使用者要求輸入新的加權數值
	 * 
	 * @return Float陣列，爲新的加權數值
	 * 
	 * Pseudo Code: 
	 * 1. 印出貼心提示
	 * 2. 要求使用者輸入每科新加權
	 * 
	 * Example: getNewWeights(); 印出「輸入新配分(提醒：每項需介於0~100之間，五項總和必須等於100)...lab1」，使用者此時輸入10，則lab1的新加權爲10%，後依序輸入15 20 25 30，最後則回傳一個float陣列，內容爲{10, 15, 20, 25, 30}
	 */
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
	
	/*
	 * method getSpecificWeight(quiz)
	 * ---
	 * 向使用者要求輸入特定科目的加權
	 * 
	 * @param quiz 欲更新的科目
	 * @return Float，爲使用者輸入的數字
	 * 
	 * Pseudo Code:
	 * 1. 印出欲更新加權的科目 
	 * 2. 向使用者要求輸入新加權
	 * 3. 回傳使用者輸入的數字
	 * 
	 * Example: getSpecificWeight("lab1"); 印出「lab1」後，使用者輸入10，最後回傳10
	 */
	private float getSpecificWeight(String quiz) {
		System.out.printf("\t%-16s", quiz);
		return Float.parseFloat(console.nextLine());
	}
	
	/*
	 * method setWeights(newWeights)
	 * ---
	 * 將本資料庫的加權數值更新
	 * 
	 * @param newWeights 新的加權數值
	 * 
	 * Pseudo Code: 
	 * 1. 將newWeights/100.0f賦值給weights
	 * 
	 * Time Estimate: O(1)
	 * Example: aGradeSystem.setWeights(newWeights); 本系統的資料成員weights的值會被改爲newWeights的各個數值除以100.0f
	 */
	public void setWeights(float[] newWeights) {
		for (int i = 0; i < 5; i++) {
			weights[i] = newWeights[i] / 100.0f;
		}
	}
	
	/*
	 * method checkNewWeights(newWeights)
	 * ---
	 * 向使用者確認輸入的加權數值無誤，並確認此加權數值合法
	 * 
	 * @param newWeights 使用者先前輸入的加權數值
	 * @return Boolean，若爲true則，使用者確認加權數值無誤，且加權數值合法；若爲false，則使用者認爲加權數值有誤，或加權數值不合法
	 * 
	 * Pseudo Code: 
	 * 1. 印出貼心提示
	 * 2. 逐科印出newWeights的加權
	 * 3. 向使用者確認有無錯誤
	 * 	1. 若無誤，則確認newWeights是否爲合法加權數值；若有誤，則回傳false
	 * 		1. 若newWeights合法，則回傳true；反之則回傳false
	 * 
	 * Example: checkNewWeights({10, 15, 20, 25, 30}); 印出「請確認新配分...」後，使用者輸入Yes，則回傳true
	 */
	private boolean checkNewWeights(float[] newWeights) {
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
	
	/*
	 * method inRangeOrNot(newWeights)
	 * ---
	 * 檢查newWeights是否爲合法的加權數值
	 * 
	 * @param newWeights 欲檢查的加權數值
	 * @return Boolean，若爲true，則newWeights爲合法的加權數值，反之則否
	 * 
	 * Pseudo Code: 
	 * 1. 將newWeights的所有元素加起來，並在過程中檢查是否有元素不在[0, 100]的範圍內
	 * 	1. 若有，則印出「調整失敗！第k項超出範圍，請重新輸入」，並回傳false
	 * 2. 檢查newWeights的元素和是否爲100
	 * 	1. 若是，則回傳true；若非，則印出「調整失敗！五項總和不等於100，請重新輸入」，並回傳false
	 * 
	 * Example: inRangeOrNot({10, 15, 20, 25, 30}); 回傳true
	 */
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

	/*
	 * method getThisIDName(ID)
	 * ---
	 * 取得ID代表的名字
	 * 
	 * @param ID 欲查詢的ID
	 * @return String，爲ID代表的名字
	 * 
	 * Pseudo Code: 
	 * 1. 回傳aList.get(ID).getName();
	 * 
	 * Time Estimate: O(1)
	 * Example: aGradeSystem.getThisIDName(955002056); 回傳"許文馨"
	 */
	public String getThisIDName(int ID) {
		return aList.get(ID).getName();
	}
}
