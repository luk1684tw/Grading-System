

public class Grade {
	private String id, name;
	private int[] scores;
	public String[] Subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
	public int weightedScore;
	
	
	
	Grade(String ID, String Name, int[] Score, int[] Weights) {
		id = ID;
		name = Name;
		scores = Score;
		for (int i = 0; i < 5 ; i++) {
			weightedScore += scores[i] * Weights[i];
		}
		weightedScore /= 100;
	}
	
	String getID() {
		return id;
	}
	String getName() {
		return name;
	}
	int[] getScores() {
		return scores;
	}
	int getWeightedScore() {
		return weightedScore;
	}
}
