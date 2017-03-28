package pro.gradeSystem;

import java.io.Console;
import java.io.IOException;
import java.util.Scanner;

/*
 * class Main
 * 程式進入點，用來創建UI，並透過UI進行之後的操作，同時處理來自下層拋出的例外
 * ---
 * Member Function: 
 * 1. main(args) // 程式進入點
 * ---
 * Pseudo Code: 
 * 1. 建構aUI進入程式
 * 2. 接收可能的錯誤
 * 3. 結束程式
 * public static void main(args) {
 * 		try {
 * 			aUI = new UI(); // 建構aUI物件作爲成績系統進入點
 * 		} catch (IOException) { print "project根目錄中不存在gradeinput.txt!" } // 處理讀檔錯誤
 * }
 */

public class Main {
	public static void main(String[] args) {
		try {
			UI aUI = new UI();
		} catch (IOException e) {
			System.out.println("project根目錄中不存在gradeinput.txt！");
		}
	}
}
