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
	
	public static final String FileName = "D:\\NTHU\\JAVA\\GradeSystem\\src\\gradeinput.txt";
	GradeSystem() { //constructor
		scanner = new Scanner(System.in);
		locked= false;
		currentWeights = defaultWeights;
	}
	void LoginListener() {
		System.out.print("�п�J�Ǹ��H�n�J�Ϋ�Q���}\n>> ");
		while(scanner.hasNextLine()) {
			String ID = scanner.nextLine();
			if (ID.equals("Q")) {
				Exit();
			} else if (ID.length() == 9) {
				Login(ID);
				CommandListener();
			} else {
				System.out.print("ID�榡���~�A�нT�{ID��A��J\n>> ");
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
			System.out.println("���������O");
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
			System.out.println("�Х��n�J!!");
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
			System.out.println("�Х��n�J!!");
		}
	}
	
	
	void handleAverage() {
		if (locked) {
			 countAverage();
			 ui.showAverage(averages, Subjects);
			 ui.showWelcomeMsg();
		} else {
			System.out.println("�Х��n�J!!");
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
			System.out.println("�°t��: ");
			ui.showWeights(currentWeights, Subjects);
			System.out.println("�п�J�s�t��");
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
					System.out.println("�t���`�M���O100%�A�P�w�����X�k��J");
				} else {
					changeWeights(newWeights);
				}
				ui.showWelcomeMsg();
			} catch (InputMismatchException e) {
				System.out.println("�п�J�X�k���Ʀr�A�^����");
				ui.showWelcomeMsg();
			} catch (NoSuchElementException e) {
				System.out.println("������EOF�A�^����");
				ui.showWelcomeMsg();
			}
		} else {
			System.out.println("�Х��n�J!!");
		}
	}
	
	void changeWeights(int[] weights) {
		System.out.print("�H�W���T��? �п�JY(Yes) �� N(No)\n>> ");
		if (scanner.hasNextLine()) {
			String command = scanner.nextLine(); 
			if (command.equals("Y")) {
				currentWeights = weights;
				System.out.println("�s�t��: ");
				ui.showWeights(currentWeights, Subjects);
				System.out.println("�ק令�\�A�^����");
			} else if (command.equals("N")) {
				System.out.println("�����ק�A�^����");
			} else 
				System.out.println("���X�k��J�A�^����");
		} else {
			System.out.println("������EOF�A�^����");
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
				System.out.println("�w��^��!   " + currentUser.getName());
				ui.showWelcomeMsg();
				locked = true;
			}
		}
		if (!locked) {
			System.out.println("�n�J���ѡA�нT�{ID...");
		}
	}
}
