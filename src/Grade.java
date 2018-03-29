

public class Grade {
	private String id, name;
	private int[] scores;
	public String[] Subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
	private int weightedScore;
	
	
	
	Grade(String ID, String Name, int[] Score) {
		id = ID;
		name = Name;
		scores = Score;
		weightedScore = -1;
		//test();
	}
	
	void showGrade() {
		StringBuilder builder = new StringBuilder();
		builder.append(name + " ¦¨ÁZ: \r\n");
		for (int i = 0; i < 5; i++) {
			builder.append("   " + Subjects[i] + ": " + scores[i]);
		}
		System.out.println(builder.toString());
	}
	
	String getID() {
		return id;
	}
	String  getName() {
		return name;
	}
}
