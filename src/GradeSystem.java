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
	
	public static final String FileName = "gradeinput.txt";
	GradeSystem() { //constructor
		scanner = new Scanner(System.in);
		locked= false;
		currentWeights = defaultWeights;
	}
	void LoginListener() {
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
	
	void CommandListener() {
		String command;
		while(scanner.hasNextLine()) {
			command = scanner.nextLine();
			CommandHandler(command);
			if (!locked) break;
		}
	}
	
	void CommandHandler(String command) {
		if 		(command.equals("Q"))	Exit();
		else if (command.equals("G"))	handleGrade();
		else if (command.equals("R"))	handleRank();
		else if (command.equals("A"))	handleAverage();
		else if (command.equals("W"))	handleWeights();
		else if (command.equals("E"))	Logout();
		else  {
			System.out.println("未知的指令");
			ui.showWelcomeMsg();
		}
														
	}
	
	void LoadFile() {
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
	
	
	void LoadSingleData(String[] datas) {
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
	
	
	void Exit() {						//finished
		ui.showFinishMsg(locked, currentUser.getName());
	}
	
	
	void handleGrade() {					//
		if (locked) {
			ui.showGrade(currentUser, Subjects);
			ui.showWelcomeMsg();
		} else {
			System.out.println("請先登入!!");
		}
	}
	
	
	void handleRank() {
		if (locked) {
			int rank = 1;
			for (Grade grade:gradeList) {
				if (grade.weightedScore > currentUser.weightedScore)
					rank++;
			}
			ui.showRank(rank, currentUser.getName());
			ui.showWelcomeMsg();
		} else {
			System.out.println("請先登入!!");
		}
	}
	
	
	void handleAverage() {
		if (locked) {
			 countAverage();
			 ui.showAverage(averages, Subjects);
			 ui.showWelcomeMsg();
		} else {
			System.out.println("請先登入!!");
		}
	}
	
	
	void countAverage() {
		for (Grade grade:gradeList) {
			for (int i = 0; i < 5; i++) {
				averages[i] += grade.getScores()[i];
			}
		}
		for (int i = 0; i < 5; i++) {
			averages[i] /= gradeList.size();
		}
	}
	
	void handleWeights() {
		if (locked) {
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
		} else {
			System.out.println("請先登入!!");
		}
	}
	
	void changeWeights(int[] weights) {
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
	
	void Logout() {								//finished
		ui.showFinishMsg(locked, currentUser.getName());
		locked = false;
	}
	
	void Login(String command) {				//finished
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
