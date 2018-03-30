
public class UI {
	UI() { 
		
		
	}
	
	void showWelcomeMsg() {
		System.out.println("��J���O   1) G ��ܦ��Z ");
		System.out.println("      2) R ��ܱƦW ");
		System.out.println("      3) A ��ܥ��� ");
		System.out.println("      4) W ��s�t��");
		System.out.print("      5) E �n�X�t��\n>> ");
	}
	
	void showFinishMsg(boolean flag, String name) {
		if (flag) {
			System.out.println("�U���� ! " + name);
			System.out.print("�п�J�Ǹ��H�n�J�Ϋ�Q���}\n>> ");
		} else {
			System.out.println("Closing GradeSystem... ... ...");
			System.out.println("���Z�t�Τw����");
			System.exit(0);
		}
	}
	
	void showAverage(int[] averages, String[] subjects) {
		StringBuilder builder = new StringBuilder();
		builder.append("�U�����Z����: \r\n");
		for (int i = 0; i < 5; i++) {
			builder.append("   " + subjects[i] + " ����: " + averages[i] + "\r\n");
		}
		System.out.println(builder.toString());
	}
	
	void showRank(int rank, String name) {
		System.out.println(name + "�ƦW: ��" + rank + "�W");
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
		builder.append(currentUser.getName()+ "�����Z: \r\n");
		int[] scores = currentUser.getScores();
		for (int i = 0; i < 5; i++) {
			if (scores[i] < 60) builder.append("   " + Subjects[i] + ": " + scores[i] + "*\r\n");
			else				builder.append("   " + Subjects[i] + ": " + scores[i] + "\r\n");
		}
		builder.append("   " + "Total Score" + ": " + currentUser.getWeightedScore()+ "\r\n");
		System.out.println(builder.toString());
	}
}
