package pro.gradeSystem;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try{
			UI ui = new UI();
		} catch (NoSuchIDExceptions e){
			System.out.println("不存在的ID!");
		} catch (NoSuchCommandExceptions e) {
			System.out.println("指令輸入錯誤!");
		} catch (NumberFormatException e) {
			System.out.println("格式錯誤!");
		} catch (IOException e) {
			System.out.println("project根目錄中不存在gradeinput.txt!");
		}
	}
	
}

class NoSuchIDExceptions extends Exception{
	public NoSuchIDExceptions(int ID){
		super("ERROR:"+ID);
	}
}

class NoSuchCommandExceptions extends Exception{
	public NoSuchCommandExceptions(String cmd){
		super("ERROR:"+cmd);
	}	
}