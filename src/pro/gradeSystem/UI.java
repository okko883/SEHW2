package pro.gradeSystem;

import java.io.*;
import java.util.Scanner;

/*
 * class UI
 * 負責接受使用者輸入欲查詢的ID，對欲查詢對象接受指令動作，將指令傳給下層的GradeSystems執行
 * ---
 * Member Data: 
 * 1. aGradeSystem
 * ---
 * Member Function: 
 * 1. UI() // 建構子，作爲成績系統的進入點
 * 2. checkID(ID) // 檢查資料庫是否有ID的資料
 * 3. promptCommand(ID) // 取得並執行指令
 * 4. promptID() // 取得ID
 * 5. showFinishMsg() // 顯示系統結束訊息
 * 6. showWelcomeMsg(ID) // 顯示歡迎資訊及指令選單
 * ---
 * Pseudo code
 * 1. 建立aGradeSystem
 * 2. 透過promptID得到ID Input，判斷是否為程式結束(Q)
 * 3. 將promptID轉為Integer，透過checkID判斷aGradeSystem內有沒有這筆資料
 * 4. 如果這筆ID存在則執行showWelcomeMsg和promptCommand
 * 5. 執行showFinishMsg結束程式
 * public UI() throws NoSuchIDExceptions, NumberFormatException, IOException {
 * 		try {
 * 			aGradeSystem = new GradeSystem(); // 建構aGradeSystem物件作爲成績資料庫
 * 			while (true) {
 * 				cmdID = promptID(); // 輸入學生ID
 * 				if (`cmdID` is equal to `Q` or `q`) break; // 結束程式
 * 				else {
 * 					if (There is `cmdID` in `aGradeSystem`) {
 * 						showWelcomeMsg(); // 顯示歡迎資訊及指令選單
 * 						promptCommand(); // 取得並執行指令
 * 					}
 * 				}
 * 			}
 * 		} catch (NoSuchIDExceptions) { print "ID錯了！" } // 處理不存在的ID
 *		catch (NumberFormatException) {	print "輸入格式內容錯誤！" } // 處理預料外的輸入格式
 *		catch (NoSuchCommandExceptions) { print "指令錯了！" } // 處理不存在的指令
 * 		finally { showFinishMsg() } // 顯示系統結束
 * }
 */

public class UI {
	private GradeSystems aGradeSystem;
	
	public UI() throws IOException {	
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
				}
				System.out.println();
			}
		} catch (NoSuchIDExceptions e) {
			System.out.println("ID錯了！"); 
		} catch (NumberFormatException e) {
			System.out.println("輸入格式內容錯誤！"); 
		} catch (NoSuchCommandExceptions e) {
			System.out.println("指令錯了！");
		} finally {
			showFinishMsg();
		}
	}
	
	public boolean checkID(int ID) throws NoSuchIDExceptions {
		if (aGradeSystem.containsID(ID)) {
			return true;
		} else {
			throw new NoSuchIDExceptions(ID);
		}
	}

	public void promptCommand(int ID) throws NoSuchCommandExceptions {
		Scanner console = new Scanner(System.in);
		
first:	while (true) {
			System.out.printf("使用者輸入：");
			String cmd = console.nextLine().toUpperCase();
			switch (cmd) {
				case "G": aGradeSystem.showGrade(ID); break;
				case "R": aGradeSystem.showRank(ID); break;
				case "A": aGradeSystem.showAverage(); break;
				case "W": aGradeSystem.updateWeights(); break;
				case "E": break first;
				default: throw new NoSuchCommandExceptions("指令錯了！");
			}
		}
	}
	
	public String promptID() {
		System.out.printf("輸入 ID 或 Q(結束使用)？");
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}
	
	public void showFinishMsg() {
		System.out.println("結束了");
	}
	
	public void showWelcomeMsg(int ID) {
		System.out.printf("Welcome %s\n", aGradeSystem.getThisIDName(ID));
		System.out.println("輸入指令\t1) G 顯示成績 (Grade)");
		System.out.println("\t2) R 顯示排名 (Rank)");
		System.out.println("\t3) A 顯示平均 (Average)");
		System.out.println("\t4) W 更新配分 (Weight)");
		System.out.println("\t5) E 離開選單 (Exit)");
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
	public NoSuchIDExceptions(int ID) {
		super("ERROR >> " + ID);
	}
}