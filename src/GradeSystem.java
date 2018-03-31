/**
 * Class GradeSystem: Manage Login、Logout、Command handling、Score management
 * 
 * Bugs: none known
 * 
 * Structure: Login_Listener -> ConmandListener -> CommandHandler
 *
 * @author: Michael Chung
 */


import java.util.Scanner;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GradeSystem {
	
	private Scanner scanner;
	private LinkedList<Grade> gradeList = new LinkedList<>();
	public boolean locked;
	public UI ui = new UI();
	private Grade currentUser;
	private static int[] defaultWeights = {10, 10, 10, 30, 40};
	private int[] currentWeights;
	private int[] averages = new int[5];
	private String[] Subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
	public static final String FileName = "./src/gradeinput.txt";
	
	/**
	 * Constructor: Construct a GradeSystem
	 * 
	 * Pseudo Code:
	 * 1. 初始化locked為false，表示目前沒有使用者登入
	 * 2. 初始化currentWeights為預設之權重
	 * 
	 * Complexity: O(1)
	 */
	GradeSystem() { //constructor
		scanner = new Scanner(System.in);
		locked= false;
		currentWeights = defaultWeights;
	}
	
	/**
	 * Method: LoginListener: Handle Login, blocks when there's no input for login
	 * 
	 * Pseudo Code:
	 * 1. 顯示登入訊息
	 * 2. 等待輸入
	 * 3. 如果輸入為"Q"，則結束程式
	 * 4. 如果輸入的長度為9，則認知其為使用者ID
	 * 5. 否則視輸入為非法輸入，顯示非法輸入提示訊息
	 * 
	 * Complexity: it takes O(1) to call Exit() or Login()
	 */
	public void LoginListener() {
		System.out.print("請輸入學號以登入或按Q離開\n>> ");
		while(scanner.hasNextLine()) {
			String ID = scanner.nextLine();
			if (ID.equals("Q")) {
				Exit();
			} else if (ID.length() == 9) {
				Login(ID);
				CommandListener();
			} else {
				System.out.print("ID格式錯誤，請確認ID後再輸入\n>> ");
			}
		}
	}
	
	/**
	 * Method CommandListener: Listen for a command, blocks when there's no input
	 * 
	 * Pseudo Code:
	 * 1. 抓取指令並呼叫CommandHandler處理
	 * 2. 如果lock為false，代表目前無使用者登入，退出迴圈並回到LoginListener
	 * 
	 * Complexity: it takes O(1) to call CommandHandler
	 */
	public void CommandListener() {
		String command;
		while(scanner.hasNextLine()) {
			command = scanner.nextLine();
			CommandHandler(command);
			if (!locked) break;
		}
	}
	
	/**
	 * Method CommandHandler: Handles a command by checking which command it is 
	 * 
	 * @param 
	 * command: input program received
	 * 
	 * Pseudo Code:
	 * 1. 用巢狀if-else判斷指令是甚麼
	 * 2. 如果沒有對應到任何關鍵字，顯示"未知的指令"和指令選單
	 * 
	 * Complexity: it takes O(1) to identify which command it is
	 */
	public void CommandHandler(String command) {
		if 		(command.equals("G"))	handleGrade();
		else if (command.equals("R"))	handleRank();
		else if (command.equals("A"))	handleAverage();
		else if (command.equals("W"))	handleWeights();
		else if (command.equals("E"))	Logout();
		else  {
			System.out.println("未知的指令");
			ui.showWelcomeMsg();
		}
														
	}
	
	/**
	 * Method LoadFile: Load all grades in
	 * 
	 * Pseudo Code:
	 * 1. 以UTF-8編碼將檔案存取近來
	 * 2. 將檔案一行一行以String讀取進來，得到每個人的資料
	 * 3. 每行String用空格分隔取出資料並存成
	 * 4. 呼叫LoadSingleData將取出的資料傳進去
	 * 
	 * @throw FileNotFoundException - 給定的路徑找不到檔案
	 * 
	 * @throw IOException - 無法讀取檔案
	 * 
	 * Complexity: it takes O(n) to split file line by line
	 */
	public void LoadFile() {
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader
					(new FileInputStream(FileName),"UTF-8"));
			String line;
			while((line = buffer.readLine()) != null) {
				String[] datas = line.split(" ");
					LoadSingleData(datas);
			}
			buffer.close();
		} catch (FileNotFoundException  e) {
			System.out.println("File not found: " + FileName);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Read file failed: " + FileName);
			System.exit(0);
		}
	}
	
	/**
	 * Method LoadSingleData: Load a single grade in
	 * 
	 * @param:
	 * datas: array of Strings that contain datum processed by LoadFile
	 * 
	 * Pseudo Code:
	 * 1. 宣告一個整數陣列來儲存分數
	 * 2. 透過確認ID長度來處理UTF-8編碼開頭BOM的問題
	 * 3. 將成績的型別由字串轉成數字
	 * 4. 新增一個Grade Class來儲存成績
	 * 5. 將新增好的Grade放到Grade List內
	 * 
	 * Complexity: it takes O(n) to split file line by line
	 */
	public void LoadSingleData(String[] datas) {
		int[] scores = new int [5];
		if (datas[0].length() == 10) {
			datas[0]= datas[0].substring(1, 10);
		}
		for (int i = 0; i < 5; i++) {
			scores[i] = Integer.parseInt(datas[i+2]);
		}
		Grade grade = new Grade(datas[0], datas[1], scores, defaultWeights);
		gradeList.add(grade);
	}
	
	/**
	 * Method Exit: Exit the program when receive command "Q"
	 * 
	 * Pseudo Code:
	 * 1. 當關閉程式時顯示結束訊息
	 * 
	 * Complexity: O(1)
	 */
	public void Exit() {						
		ui.showFinishMsg(locked, " ");
	}
	
	/**
	 * Method handleGrade: show currentUser's Grade when receive command "G"
	 * 
	 * Pseudo Code:
	 * 1. 呼叫ui.showGrade以顯示目前登入系統之使用者的成績
	 * 2. 呼叫ui.showWelcomMsg顯示指令選單
	 * 
	 * Complexity: O(1)
	 */
	public void handleGrade() {					
		ui.showGrade(currentUser, Subjects);
		ui.showWelcomeMsg();
	}
	
	/**
	 * Method handleRank: show currentUser's Rank when receive command "R"
	 * 
	 * Pseudo Code:
	 * 1. 將Rank初始化為一
	 * 2. 將整個gradeList內每筆grade存取一遍，若存取到的grade比使用者高分，就將Rank加一
	 * 3. 呼叫ui.showRank以顯示Rank
	 * 4. 呼叫ui.showWelcomMsg以顯示指令選單
	 * 
	 * Complexity: O(n) to traverse the list
	 */
	public void handleRank() {
		int rank = 1;
		for (Grade grade:gradeList) {
			if (grade.weightedScore > currentUser.weightedScore)
				rank++;
		}
		ui.showRank(rank, currentUser.getName());
		ui.showWelcomeMsg();
	}
	
	/**
	 * Method handleAverage: show all subjects' average grade when receive command "A"
	 * 
	 * Pseudo Code:
	 * 1. 呼叫countAverage算出所有科目的平均
	 * 2. 呼叫ui.showAverage以顯示平均分數
	 * 4. 呼叫ui.showWelcomMsg以顯示指令選單
	 * 
	 * Complexity: O(1)
	 */
	public void handleAverage() {
		countAverage();
		ui.showAverage(averages, Subjects);
		ui.showWelcomeMsg();

	}
	
	/**
	 * Method countAverage: compute all subjects' average grade
	 * 
	 * Pseudo Code:
	 * 1. 將整個gradeList內每筆grade存取一遍，並把所有人每科的分數加到陣列average內
	 * 2. 將陣列average內五個科目的總和分數除以人數，得到所有科目的平均分數
	 * 
	 * Complexity: O(n) to traverse the list
	 */
	public void countAverage() {
		for (Grade grade:gradeList) {
			for (int i = 0; i < 5; i++) {
				averages[i] += grade.getScores()[i];
			}
		}
		for (int i = 0; i < 5; i++) {
			averages[i] /= gradeList.size();
		}
	}
	
	/**
	 * Method handleWeights: handle changing weights when receive input "W"
	 * 
	 * @throws 
	 * InputMismatchException - 使用者輸入的不是數字
	 * NoSuchElementException - 使用者未輸入字元
	 * 
	 * Pseudo Code:
	 * 1. 呼叫ui.showWeights顯示當前的配分
	 * 2. 顯示"請輸入新配分"提示使用者輸入配分
	 * 3. 宣告整數sum紀錄使用者輸入配分的總和
	 * 4. 用一個執行五次的for迴圈提示使用者輸入到哪一項科目並抓取使用者輸入的數字
	 * 5. 用scanner.nextLine()過濾掉換行字元
	 * 6. 若總和不為100，提示使用者輸入錯誤並回到指令選單
	 * 7. 若總和為100則呼叫changeWeights修改配分
	 * 
	 * Complexity: O(1)
	 */
	public void handleWeights() {
		System.out.println("舊配分: ");
		ui.showWeights(currentWeights, Subjects);
		System.out.println("請輸入新配分");
		try {
			int[] newWeights = new int[5];
			int sum = 0;
			for (int i = 0; i < 5; i++) {
				System.out.print("   " + Subjects[i] + ": ");
				newWeights[i] = scanner.nextInt();
				sum += newWeights[i]; 
			}
			scanner.nextLine();
			if (sum != 100) {
				System.out.println("配分總和不是100%，判定為不合法輸入");
			} else {
				changeWeights(newWeights);
			}
			ui.showWelcomeMsg();
		} catch (InputMismatchException e) {
			System.out.println("請輸入合法的數字，回到選單");
			ui.showWelcomeMsg();
		} catch (NoSuchElementException e) {
			System.out.println("偵測到EOF，回到選單");
			ui.showWelcomeMsg();
		}
	}
	
	/**
	 * Method changeWeights: change currentWeights
	 * 
	 * Pseudo Code:
	 * 1. 提示訊息詢問使用者新配分是否正確
	 * 2. 若使用者輸入EOF中止修改配分則顯示"偵測到EOF 回到選單"
	 * 3. 若使用者輸入"Y"則將currentWeights改成使用者輸入的配分，
	 * 	     並呼叫ui.showWeights將修改後的配分顯示出來
	 * 4. 若使用者輸入"N"則顯示"取消修改 回到選單"
	 * 5. 若使用者輸入的不是"Y"也不是"N"則提示輸入不合法，回到選單
	 *  
	 * Complexity: O(1)
	 */
	public void changeWeights(int[] weights) {
		System.out.print("以上正確嗎? 請輸入Y(Yes) 或 N(No)\n>> ");
		if (scanner.hasNextLine()) {
			String command = scanner.nextLine(); 
			if (command.equals("Y")) {
				currentWeights = weights;
				System.out.println("新配分: ");
				ui.showWeights(currentWeights, Subjects);
				System.out.println("修改成功，回到選單");
			} else if (command.equals("N")) {
				System.out.println("取消修改，回到選單");
			} else 
				System.out.println("不合法輸入，回到選單");
		} else {
			System.out.println("偵測到EOF，回到選單");
		}
	}
	
	/**
	 * Method Logout: handle user logout action
	 * 
	 * Pseudo Code:
	 * 1. 呼叫ui.showFinishMsg顯示登出訊息
	 * 2. 將locked設為false，表示當前系統無使用者登入
	 * 
	 * Complexity: O(1)
	 */
	public void Logout() {								
		ui.showFinishMsg(locked, currentUser.getName());
		locked = false;
	}
	
	/**
	 * Method Logout: handle user login action
	 * 
	 * Pseudo Code:
	 * 1. 存取gradeList內每個元素逐一比對每個元素的ID與使用者輸入的ID
	 * 2. 若有元素的ID與使用者輸入的ID相同，將currentUser設為該元素，顯示登入
	 * 	     成功並呼叫ui.showWelcomeMsg顯示指令選單，將locked設為true表示系統
	 * 	     目前有使用者登入
	 * 3. 如沒有元素的ID與使用者輸入的ID相同，顯示登入失敗
	 * 
	 * Complexity: O(n)
	 */
	public void Login(String command) {				
		for (Grade grade: gradeList) {
			if (command.equals(grade.getID())) {
				currentUser = grade;
				System.out.println("歡迎回來!   " + currentUser.getName());
				ui.showWelcomeMsg();
				locked = true;
			}
		}
		if (!locked) {
			System.out.println("登入失敗，請確認ID...");
		}
	}
}
