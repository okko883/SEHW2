package pro.gradeSystem;

import java.io.*;
import java.util.Scanner;

/*
 * class UI
 * ---
 * 負責接受使用者輸入欲查詢的ID，對欲查詢對象接受指令動作，將指令傳給下層的GradeSystems執行
 * 
 * Member Data: 
 * 1. aGradeSystem // 成績系統物件
 * 
 * Member Function: 
 * 1. UI() // 建構子，作爲成績系統的進入點
 * 2. checkID(ID) // 檢查資料庫是否有ID的資料
 * 3. promptCommand(ID) // 取得並執行指令
 * 4. promptID() // 取得ID
 * 5. showFinishMsg() // 顯示系統結束訊息
 * 6. showWelcomeMsg(ID) // 顯示歡迎資訊及指令選單
 * 7. resetWeights() // 重設加權數值
 */
public class UI {
	private GradeSystems aGradeSystem;
	
	/* method UI()
	 * ---
	 * UI物件建構子，建構UI界面並開始與使用者互動
	 * 
	 * Pseudo Code:  
	 * 1. 建立aGradeSystem
	 * 2. while true
	 * 	1. 透過promptID得到ID輸入
	 * 	2. 判斷是否為程式結束(Q)
	 *		1. 若是，則end while；若非，則透過checkID判斷aGradeSystem內有沒有這筆ID
	 * 			1. 若是，則執行showWelcomeMsg和promptCommand
	 * 3. 接收可能的錯誤
	 * 4. 執行showFinishMsg結束程式
	 * 
	 * Time Estimate: (Depends on input)
	 * Example: UI aUI = new UI(); 建構UI界面並開始與使用者互動
	 */
	public UI() {	
		try {
			aGradeSystem = new GradeSystems();
			while (true) {
				String cmdID = promptID().toLowerCase();
				if (cmdID.equals("q")) {
					break;
				} else {
					int ID = Integer.parseInt(cmdID);
					if (checkID(ID)) {
						showWelcomeMsg(ID);
						promptCommand(ID);
					}
					resetWeights();
				}
				System.out.println();
			}
		} catch (NoSuchIDExceptions e) {
			System.out.println("ID錯了！"); 
		} catch (NumberFormatException e) {
			System.out.println("輸入格式內容錯誤！"); 
		} catch (NoSuchCommandExceptions e) {
			System.out.println("指令錯了！");
		} catch (IOException e) {
			System.out.println("project根目錄中不存在gradeinput.txt！");
		} finally {
			showFinishMsg();
		}
	}

	/*
	 * method checkID(ID)
	 * ---
	 * 檢查ID是否在成績系統內
	 * 
	 * @param ID 用來檢查的ID
	 * @return boolean，若true則這筆ID存在於成績系統內
	 * @throws NoSuchIDExceptions 若這筆這筆ID不存在於成績系統內，則丟出此例外
	 * 
	 * Pseudo Code: 
	 * 1. 檢查ID是否在aGradeSystem內
	 * 	1. 若是，則回傳true；若非，則丟出NoSuchIDExceptions
	 * 
	 * Example: checkID(955002056); 回傳true
	 */
	private boolean checkID(int ID) throws NoSuchIDExceptions {
		if (aGradeSystem.containsID(ID)) {
			return true;
		} else {
			throw new NoSuchIDExceptions(ID);
		}
	}

	/* method promptCommand(ID)
	 * ---
	 * 要求使用者輸入指令，並依據指令執行程式
	 * 
	 * @param ID 指令指令的ID對象
	 * @throws NoSuchCommandExceptions 若輸入不存在的指令，則丟出此意外
	 * 
	 * Pseudo Code: 
	 * 1. while true
	 * 	1. 要求使用者輸入指令
	 * 	2. 若指令爲G，則執行aGradeSystem.showGrade(ID);
	 * 	3. 若指令爲R，則執行aGradeSystem.showRank(ID);
	 * 	4. 若指令爲A，則執行aGradeSystem.showAverage();
	 * 	5. 若指令爲W，則執行aGradeSystem.updateWeights();
	 * 	6. 若指令爲E，則end while
	 * 	7. 若非上述指令，則丟出NoSuchCommandExceptions
	 * 
	 * Example: promptCommand(955002056); 要求輸入指令，若輸入R，則顯示排名
	 */
	private void promptCommand(int ID) throws NoSuchCommandExceptions {
		Scanner console = new Scanner(System.in);
		
first:	while (true) {
			System.out.printf("使用者輸入：");
			String cmd = console.nextLine().toUpperCase();
			switch (cmd) {
				case "G": aGradeSystem.showGrade(ID); break;
				case "R": aGradeSystem.showRank(ID); break;
				case "A": aGradeSystem.showAverage(); break;
				case "W": aGradeSystem.updateWeights(ID); break;
				case "E": break first;
				default: throw new NoSuchCommandExceptions("指令錯了！");
			}
		}
	}
	
	/*
	 * method promptID()
	 * ---
	 * 要求使用者輸入ID，或者Q（結束）
	 * 
	 * @return String，爲使用者輸入的內容
	 * 
	 * Pseudo Code: 
	 * 1. 要求輸入ID或Q
	 * 2. 回傳下一行輸入內容
	 * 
	 * Example: promptID(); 要求輸入ID，若輸入955002056，則回傳"955002056"
	 */
	private String promptID() {
		System.out.printf("輸入 ID 或 Q(結束使用)？");
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}
	
	/*
	 * method showFinishMsg()
	 * ---
	 * 印出系統結束訊息
	 * 
	 * Pseudo Code: 
	 * 1. 印出系統結束訊息
	 * 
	 * Example: showFinishMsg(); 印出"結束了"
	 */
	private void showFinishMsg() {
		System.out.println("結束了");
	}
	
	/*
	 * method showWelcomeMsg(ID)
	 * ---
	 * 印出歡迎訊息及功能選單
	 * 
	 * @param ID 用來顯示使用者姓名
	 * 
	 * Pseudo Code: 
	 * 1. 印出歡迎訊息及功能選單
	 * 
	 * Example: showWelcomeMsg(955002056); 顯示「Welcome 許文馨...」
	 */
	private void showWelcomeMsg(int ID) {
		System.out.printf("Welcome %s\n", aGradeSystem.getThisIDName(ID));
		System.out.println("輸入指令\t1) G 顯示成績 (Grade)");
		System.out.println("\t2) R 顯示排名 (Rank)");
		System.out.println("\t3) A 顯示平均 (Average)");
		System.out.println("\t4) W 更新配分 (Weight)");
		System.out.println("\t5) E 離開選單 (Exit)");
	}
	
	/*
	 * method resetWeights()
	 * ---
	 * 重設加權數值
	 * 
	 * Pseudo Code: 
	 * 1. 創建一個陣列，內容爲預設的加權
	 * 2. 將本系統的加權更新爲預設加權
	 * 
	 * Time Estimate: O(1)
	 * Example: aUI.resetWeights(); 加權會回復成預設狀態
	 */
	public void resetWeights() {
		float[] newWeights = {10f, 10f, 10f, 30f, 40f};
		aGradeSystem.setWeights(newWeights);
	}
}

/*
 * class NoSuchCommandExceptions
 * 接收指令錯誤的界面
 * ---
 * Member Function: 
 * 1. NoSuchCommandExceptions(cmd) // 建構子
 */
class NoSuchCommandExceptions extends Exception {
	/*
	 * method NoSuchCommandExceptions(cmd)
	 * ---
	 * NoSuchCommandExceptions 建構子
	 * 
	 * @param cmd 含有錯誤的指令
	 * 
	 * Pseudo Code: 
	 * 1. 呼叫Exception建構子
	 * 
	 * Time Estimate: O(1)
	 * Example: throw NoSuchCommandExceptions("J"); 丟出NoSuchCommandExceptions
	 */
	public NoSuchCommandExceptions(String cmd) {
		super("ERROR >> " + cmd);
	}
}

/*
 * class NoSuchIDExceptions
 * 接收ID錯誤的界面
 * ---
 * Member Function: 
 * 1. NoSuchIDExceptions(ID) // 建構子
 */
class NoSuchIDExceptions extends Exception {
	/*
	 * method NoSuchIDExceptions(ID)
	 * ---
	 * NoSuchIDExceptions 建構子
	 * 
	 * @param ID 含有問題的ID
	 * 
	 * Pseudo Code: 
	 * 1. 呼叫Exception建構子
	 * 
	 * Time Estimate: O(1)
	 * Example: throw NoSuchIDExceptions(0000); 丟出NoSuchIDExceptions
	 */
	public NoSuchIDExceptions(int ID) {
		super("ERROR >> " + ID);
	}
}