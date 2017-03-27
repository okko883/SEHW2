package pro.gradeSystem;
import java.io.IOException;

/*
 * method Main
 * 程式進入點，用來創建UI，並透過UI進行之後的操作，同時處理來自下層拋出的例外
 * 資料成員: ui
 * ----------------------
 * Pseudo code:
 * public static void main(String[] args){
 * 		try{
 * 			call UI constructor 建構ui
 * 		}end try
 * 		catch(NoSuchIDEExceptions){ print "不存在的ID!" }	處理輸入錯誤ID
 * 		catch(NoSuchCommandExceptions){ print "指令輸入錯誤!" }	處理輸入錯誤指令
 * 		catch(NumberFormatException){print "格式輸入錯誤" } 處理格式錯誤問題
 * 		catch(IOException){ print "project根目錄中不存在gradeinput.txt!" } 處理讀檔錯誤
 * }
 */
public class Main {

	public static void main(String[] args) {
		try{
			UI ui = new UI();
		} catch (NoSuchIDExceptions e){
			System.out.println("不存在的ID!");
		} catch (NoSuchCommandExceptions e) {
			System.out.println("指令輸入錯誤!");
		}catch(NumberFormatException e){ 
			System.out.println("格式輸入錯誤!");
		}catch (IOException e) {
			System.out.println("project根目錄中不存在gradeinput.txt!");
		}
	}
	
}
/*
 * method NoSuchIDExceptions
 * 例外處理 ─ 當輸入不存在的ID
 */
class NoSuchIDExceptions extends Exception{
	public NoSuchIDExceptions(int ID){
		super("ERROR:"+ID);
	}
}
/*
 * methos NoSuchCommandExceptions
 * 例外處理 ─ 當輸入不存在的指令時
 */

class NoSuchCommandExceptions extends Exception{
	public NoSuchCommandExceptions(String cmd){
		super("ERROR:"+cmd);
	}	
}