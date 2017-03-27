package pro.gradeSystem;
import java.io.*;
import java.util.Scanner;

/*
 * method UI
 * 負責接受使用者輸入欲查詢的ID，對欲查詢對象接受指令動作，將指令傳給下層的GradeSystems執行
 * 資料成員: aGradeSystem
 * -------------------
 * Pseudo code
 * 1.建立aGradeSystem
 * 2.透過promptID得到ID input，判斷是否為Q or q ->if so, 程式結束
 * 3.將promptID轉為Integer，透過checkID判斷aGradeSystem內有沒有這筆資料
 * 4.如果結果為true -> 執行 showWelcomeMsg 和 promptCommand
 * 5.結束程式 -> 執行showFinishMsg
 * public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions, NumberFormatException, IOException{
 * 		try{
 * 			call GradeSystem constructor //建構aGradeSystem
 * 			while(true){
 * 				get cmdID from method promptID //得到command input
 * 				if(cmdID is equal to Q or q) break	//結束程式
 * 				else{
 * 					convert cmdID(String type) to Integer type
 * 					if(method checkID is true){
 * 						call method showWelcomeMsg, promptCommand
 * 					}
 * 				}print '\n'
 * 			}
 * 		}end try
 * 		finally {call method showFinishMsg}
 * }end of UI
 */
public class UI {
	private GradeSystems aGradeSystem;
	
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions, NumberFormatException, IOException {	
		try{
			aGradeSystem = new GradeSystems();
			while(true){
				String cmdID = promptID().toLowerCase();
				if(cmdID.equals("q")){
					break;
				}else{
					int ID = Integer.parseInt(cmdID);
					if(checkID(ID)){
						showWelcomeMsg(ID);
						promptCommand(ID);
					}
				}
				System.out.println();
			}
		}catch(NumberFormatException e){
			System.out.println("請輸入9碼數字ID或是Q!");
		}finally{
			showFinishMsg();
		}
	}
/*
 * 
 */
	public boolean checkID(int ID) throws NoSuchIDExceptions{
		if(aGradeSystem.containsID(ID)){
			return true;
		}else{
			throw new NoSuchIDExceptions(ID);
		}
	}
/*
 * 
 */
	public void promptCommand(int ID) throws NoSuchCommandExceptions{
		Scanner console = new Scanner(System.in);
first:	while(true){
			System.out.println("使用者輸入: ");
			String cmd = console.nextLine().toUpperCase();
			switch(cmd){
				case "G": aGradeSystem.showGrade(ID); 	break;
				case "R": aGradeSystem.showRank(ID);	break;
				case "A": aGradeSystem.showAverage();	break;
				case "W": aGradeSystem.updateWeights();	break;
				case "E": break first;
				default: throw new NoSuchCommandExceptions(cmd);
			}
		}
	}
	public String promptID(){
		System.out.println("輸入 ID 或    Q(結束使用)");
		Scanner console = new Scanner(System.in);
		return console.nextLine();
	}
	public void showFinishMsg(){
		System.out.println("系統已結束");
	}
	public void showWelcomeMsg(int ID){
		System.out.printf("Welcome %s\n", aGradeSystem.getThisIDName(ID));
		System.out.println("輸入指令  1) G 顯示成績 (Grade)");
		System.out.println("      2) R 顯示排名 (Rank)");
		System.out.println("      3) A 顯示平均 (Average)");
		System.out.println("      4) W 更新配分 (Weight)");
		System.out.println("      5) E 離開選單 (Exit))");
	}
}
