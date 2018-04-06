/**
 * Class UI: Print messages that need to be print repeatedly
 * 
 * Bugs: none known
 * 
 * @author Admin
 */
public class UI {
	
	/**
	 * Constructor: Constructor a UI class
	 */
	UI() {}
	
	/**
	 *Method showWelcomeMsg: show command opinion message
	 *
	 * Pseudo Code:
	 * 1. 顯示登入後指令選項
	 * 
	 * Complexity: O(1)
	 */
	public void showWelcomeMsg() {
		System.out.println("輸入指令   1) G 顯示成績 ");
		System.out.println("       2) R 顯示排名 ");
		System.out.println("       3) A 顯示平均 ");
		System.out.println("       4) W 更新配分");
		System.out.print("       5) E 登出系統\n>> ");
	}
	
	/**
	 *Method showFinishMsg: show logout or exit message
	 *
	 *@param:
	 *		flag: Whether the system has user login now
	 *		name: currentUser's name，will be a space if no user
	 *
	 * Pseudo Code:
	 * 1. 依照flag判斷當前系統是否有使用者登入
	 * 2. 如果有使用者登入則顯示登出訊息
	 * 3. 如果沒有使用者登入則顯示結束程式訊息
	 * 
	 * Complexity: O(1)
	 */
	public void showFinishMsg(boolean flag, String name) {
		if (flag) {
			System.out.println("下次見 ! " + name);
			System.out.print("請輸入學號以登入或按Q離開\n>> ");
		} else {
			System.out.println("Closing GradeSystem... ... ...");
			System.out.println("成績系統已關閉");
			//System.exit(0);
		}
	}
	
	/**
	 *Method showAverage: show Average scores of all subjects
	 *
	 *@param:
	 *		averages: array of average scores of all subjects
	 *		subjects: array of name of all subjects
	 *
	 * Pseudo Code:
	 * 1. 使用StringBuilder將"各項成績平均"與averages內每個元素串在一起
	 * 2. 將串好的builder轉成String並顯示
	 * 
	 * Complexity: O(1)
	 */
	public void showAverage(int[] averages, String[] subjects) {
		StringBuilder builder = new StringBuilder();
		builder.append("各項成績平均: \r\n");
		for (int i = 0; i < 5; i++) {
			builder.append("   " + subjects[i] + " 平均: " + averages[i] + "\r\n");
		}
		System.out.println(builder.toString());
	}
	
	/**
	 *Method showRank: show Rank of currentUser
	 *
	 *@param:
	 *		rank: rank of currentUser
	 *		name: name of currentUser
	 *
	 * Pseudo Code:
	 * 1. 顯示該使用者的名次
	 * 
	 * Complexity: O(1)
	 */
	public void showRank(int rank, String name) {
		System.out.println(name + "排名: 第" + rank + "名");
	}
	
	/**
	 * Method showWeights: show given weights
	 * @param 
	 * 		weights: array of weights system want to print
	 * 		Subjects: name of all subjects
	 * 
	 * Pseudo Code:
	 * 1. 使用StringBuilder將各科目名稱與weights內對應的權重串在一起
	 * 2. 將串好的builder轉成String並顯示
	 * 
	 * Complexity: O(1)
	 */
	public void showWeights(int[] weights, String[] Subjects) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			builder.append("   " + Subjects[i] + " : " + weights[i] + "%" + "\r\n");
		}
		System.out.println(builder.toString());
	}
	
	/**
	 * Method showGrade: show grades of currentUser
	 * 
	 * @param 
	 * 		currentUser: CurrentUser of the system
	 * 		Subjects: name of all subjects
	 * 
	 * Pseudo Code:
	 * 1. 使用StringBuilder將使用者的姓名與各科目的名字與成績串在一起
	 * 2. 如果該科目的成績不及格，則在成績後面加上"*"
	 * 3. 再串上該使用者的加權平均
	 * 4. 將串好的builder轉成String並顯示
	 * 
	 * Complexity: O(1)
	 */
	public void showGrade(Grade currentUser, String[] Subjects) {
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
