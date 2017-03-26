package pro.gradeSystem;

import java.io.*;
import java.util.Scanner;

public class UI {
	private GradeSystems aGradeSystem;
	
	public UI() throws NoSuchIDExceptions, NoSuchCommandExceptions, NumberFormatException, IOException {	
		try{
			aGradeSystem = new GradeSystems();
			while(true){
				String cmdID = promptID();
				if(cmdID.equals("Q")||cmdID.equals("q")){
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
		}finally{
			showFinishMsg();
		}
	}
	
	public boolean checkID(int ID) throws NoSuchIDExceptions{
		if(aGradeSystem.containsID(ID)){
			return true;
		}else{
			throw new NoSuchIDExceptions(ID);
		}
		
	}
	public void promptCommand(int ID) throws NoSuchCommandExceptions{
		Scanner console = new Scanner(System.in);
first:	while(true){
			System.out.println("使用者輸入: ");
			String cmd = console.nextLine();
			switch(cmd){
				case "G": aGradeSystem.showGrade(ID); break;
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
		String cmdForID = console.nextLine();
		return cmdForID;
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
