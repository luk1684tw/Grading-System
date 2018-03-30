
public class UI {
	UI() { 
		
		
	}
	
	void showWelcomeMsg() {
		System.out.println("輸入指令   1) G 顯示成績 ");
		System.out.println("      2) R 顯示排名 ");
		System.out.println("      3) A 顯示平均 ");
		System.out.println("      4) W 更新配分");
		System.out.print("      5) E 登出系統\n>> ");
	}
	
	void showFinishMsg(boolean flag, String name) {
		if (flag) {
			System.out.println("下次見 ! " + name);
			System.out.print("請輸入學號以登入或按Q離開\n>> ");
		} else {
			System.out.println("Closing GradeSystem... ... ...");
			System.out.println("成績系統已關閉");
			System.exit(0);
		}
	}
	
	void showAverage(int[] averages, String[] subjects) {
		StringBuilder builder = new StringBuilder();
		builder.append("各項成績平均: \r\n");
		for (int i = 0; i < 5; i++) {
			builder.append("   " + subjects[i] + " 平均: " + averages[i] + "\r\n");
		}
		System.out.println(builder.toString());
	}
	
	void showRank(int rank, String name) {
		System.out.println(name + "排名: 第" + rank + "名");
	}
	
	void showWeights(int[] weights, String[] Subjects) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			builder.append("   " + Subjects[i] + " : " + weights[i] + "%" + "\r\n");
		}
		System.out.println(builder.toString());
	}
	
	
	void showGrade(Grade currentUser, String[] Subjects) {
		StringBuilder builder = new StringBuilder();
		builder.append(currentUser.getName()+ "的成績: \r\n");
		int[] scores = currentUser.getScores();
		for (int i = 0; i < 5; i++) {
			if (scores[i] < 60) builder.append("   " + Subjects[i] + ": " + scores[i] + "*\r\n");
			else				builder.append("   " + Subjects[i] + ": " + scores[i] + "\r\n");
		}
		builder.append("   " + "Total Score" + ": " + currentUser.getWeightedScore()+ "\r\n");
		System.out.println(builder.toString());
	}
}
