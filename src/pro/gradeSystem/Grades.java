package pro.gradeSystem;

/*
 * class Grades
 * ---
 * 記錄個人成績
 * 
 * Member Data: 
 * 1. ID // 本項目學生的ID
 * 2. name // 本項目學生的名字
 * 3. lab1 // 本項目學生的lab1成績
 * 4. lab2 // 本項目學生的lab2成績
 * 5. lab3 // 本項目學生的lab3成績
 * 6. midTerm // 本項目學生的midTerm成績
 * 7. finalExam // 本項目學生的finalExam成績
 * 8. totalGrade // 本項目學生的加權成績
 * 
 * Member Function: 
 * 1. Grades(ID, name, lab1, lab2, lab3, midTerm, finalExam, weight) // 建構子，初始化本項目的內容
 * 2. calculateTotalGrade(weight) // 計算加成後的成績
 * 3. getName() // 取得本項目的學生名字
 * 4. getID() // 取得本項目的學生ID
 * 5. getLab1() // 取得本項目學生的lab1成績
 * 6. getLab2() // 取得本項目學生的lab2成績
 * 7. getLab3() // 取得本項目學生的lab3成績
 * 8. getMidTerm() // 取得本項目學生的midTerm成績
 * 9. getFinalExam() // 取得本項目學生的finalExam成績
 * 10. getTotalGrade() // 取得本項目學生的加權成績
 */
public class Grades {
	private int ID;
	private String name;
	private int lab1, lab2, lab3, midTerm, finalExam, totalGrade;
	
	/*
	 * method Grades(ID, name, lab1, lab2, lab3, midTerm, finalExam, weight)
	 * ---
	 * Grades物件建構子，將本項目的內容初始化
	 * 
	 * @param ID 本項目學生的ID
	 * @param name 本項目學生的名字
	 * @param lab1 本項目學生的lab1成績
	 * @param lab2 本項目學生的lab2成績
	 * @param lab3 本項目學生的lab3成績
	 * @param midTerm 本項目學生的midTerm成績
	 * @param finalExam 本項目學生的finalExam成績
	 * @param weight 本項目的加權數值
	 * 
	 * Pseudo Code: 
	 * 1. 將除了weight以外的各項參數複製給對應的private member data
	 * 2. 透過calculateTotalGrade(weight)計算加權成績
	 * 
	 * Time Estimate: O(1)
	 * Example: Grades XiaoMing = new Grades(1234, "小明", 10, 20, 30, 40, 50, {0.1f, 0.15f, 0.2f, 0.25f, 0.3f}); 建構一個項目，此項目記錄的ID爲1234、名字爲小明、lab1成績爲10、lab2成績爲20、lab3成績爲30、midTerm成績爲40、finalExam成績爲50，再計算以lab1: 10%、lab2: 15%、lab3: 20%、midTerm: 25%、finalExam: 30%加權的成績
	 */
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
	
	/*
	 * method calculateTotalGrade(weight)
	 * ---
	 * 計算以weight加權的成績，再賦值給totalGrade
	 * 
	 * @param weight 加權數值
	 * 
	 * Pseudo Code: 
	 * 1. 計算以weight加權的成績
	 * 2. 將結果賦值給totalGrade
	 * 
	 * Time Estimate: O(1)
	 * Example: XiaoMing.calculateTotalGrade({0.1f, 0.15f, 0.2f, 0.25f, 0.3f}); 小明的totalGrade將以lab1: 10%、lab2: 15%、lab3: 20%、midTerm: 25%、finalExam: 30%的加權計算
	 */
	public void calculateTotalGrade(float[] weight) {
		float tmp = lab1 * weight[0] + lab2 * weight[1] + lab3 * weight[2] + midTerm * weight[3] + finalExam * weight[4];
		totalGrade = (int)(Math.round(tmp));
	}
	
	/*
	 * method getName()
	 * ---
	 * 取得本項目的學生姓名
	 * 
	 * @return String，爲本項目的學生姓名
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的name
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getName(); 回傳"小明"
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * method getID()
	 * ---
	 * 取得本項目的學生ID
	 * 
	 * @return Integer，爲本項目的學生ID
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的ID
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getID(); 回傳1234
	 */
	public int getID() {
		return ID;
	}
	
	/*
	 * method getLab1()
	 * ---
	 * 取得本項目的學生lab1成績
	 * 
	 * @return Integer，爲本項目學生的lab1成績
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的lab1
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getLab1(); 回傳10
	 */
	public int getLab1() {
		return lab1;
	}
	
	/*
	 * method getLab2()
	 * ---
	 * 取得本項目的學生lab2成績
	 * 
	 * @return Integer，爲本項目學生的lab2成績
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的lab2
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getLab2(); 回傳20
	 */
	public int getLab2() {
		return lab2;
	}
	
	/*
	 * method getLab3()
	 * ---
	 * 取得本項目的學生lab3成績
	 * 
	 * @return Integer，爲本項目學生的lab3成績
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的lab3
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getLab3(); 回傳30
	 */
	public int getLab3() {
		return lab3;
	}
	
	/*
	 * method getMidTerm()
	 * ---
	 * 取得本項目的學生midTerm成績
	 * 
	 * @return Integer，爲本項目學生的midTerm成績
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的midTerm
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getMidTerm(); 回傳40
	 */
	public int getMidTerm() {
		return midTerm;
	}
	
	/*
	 * method getFinalExam()
	 * ---
	 * 取得本項目的學生finalExam成績
	 * 
	 * @return Integer，爲本項目學生的finalExam成績
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的finalExam
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getFinalExam(); 回傳50
	 */
	public int getFinalExam() {
		return finalExam;
	}
	
	/*
	 * method getTotalGrade()
	 * ---
	 * 取得本項目的學生加成成績
	 * 
	 * @return Integer，爲本項目學生的加成成績
	 * 
	 * Pseudo Code: 
	 * 1. 回傳本物件內的totalGrade
	 * 
	 * Time Estimate： O(1)
	 * Example: XiaoMing.getTotalGrade(); 回傳小明加權後的成績
	 */
	public int getTotalGrade() {
		return totalGrade;
	}
}
