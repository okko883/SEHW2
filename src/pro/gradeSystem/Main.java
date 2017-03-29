package pro.gradeSystem;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/*
 * class Main
 * ---
 * 程式進入點，用來創建UI，並透過UI進行之後的操作，同時處理來自下層拋出的例外
 * 
 * Member Function: 
 * 1. main(args) // 程式進入點
 */
public class Main {
	/* method main
	 * ---
	 * 程式進入點，用來創建UI，並透過UI進行之後的操作，同時處理來自下層拋出的例外
	 * 
	 * Pseudo Code: 
	 * 1. 建構aUI進入程式
	 * 2. 接收可能的錯誤
	 * 3. 結束程式
	 */
	public static void main(String[] args) {
		try {
			UI aUI = new UI();
		} catch (IOException e) {
			System.out.println("project根目錄中不存在gradeinput.txt！");
		}
	}
}
