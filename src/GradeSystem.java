import java.util.Scanner;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GradeSystem {
	private Scanner scanner;
	private LinkedList<Grade> gradeList = new LinkedList<>();
	public boolean flag;
	public UI ui = new UI();
	
	public static final String FileName = "D:\\NTHU\\JAVA\\GradeSystem\\src\\gradeinput.txt";
	GradeSystem() { //constructor
		scanner = new Scanner(System.in);
		
	}
	
	void CommandListener() {
		String command;
		System.out.println("請輸入學號以登入或按Q離開");
		while(scanner.hasNextLine()) {
			command = scanner.nextLine();
			CommandHandler(command);
			System.out.println("請輸入學號以登入或按Q離開");
		}
	}
	
	void CommandHandler(String command) {
		if 		(command.equals("Q"))	Exit();
		else if (command.equals("G"))	showGrade();
		else if (command.equals("R"))	showRank();
		else if (command.equals("A"))	showAverage();
		else if (command.equals("W"))	showWeights();
		else							Login(command);
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
		Grade grade = new Grade(datas[0], datas[1], scores);
		gradeList.add(grade);
	}
	
	void Exit() {
		System.out.println("Closing GradeSystem...");
		System.out.println("... ... ... ... ...");
		System.out.println("成績系統已關閉");
		System.exit(0);
	}
	void showGrade() {
		
	}
	void showRank() {}
	void showAverage() {}
	void showWeights() {}
	
	void Login(String command) {
		boolean flag = false;
		for (Grade grade: gradeList) {
			if (command.equals(grade.getID())) {
				String name = grade.getName();
				ui.showWelcomeMsg(name);
				flag = true;
			}
		}
		if (!flag) {
			System.out.println("登入失敗，請確認ID...");
		}
	}
}
