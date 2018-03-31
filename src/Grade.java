/**
 * Class Grade: Data Structure for storing students' grade
 * 
 * Bugs: none known
 * 
 * @author Michael Chung
 */

public class Grade {
	private String id, name;
	private int[] scores;
	public String[] Subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
	public int weightedScore;
	
	/**
	 * Constructor: Construct a Grade class to store a student's grade 
	 * @param 
	 * 		ID: Students'ID
	 * 		Name: Students'Name
	 * 		Score: Students'Score
	 * 		Weights: Current GradeSystem Weights
	 * 
	 * Pseudo Code:
	 * 1. 將id, name, scores儲存好
	 * 2. 用一個for迴圈計算加權總分
	 * 3. 將加權後的總分除以100得到加權平均
	 * 
	 * Complexity: O(1)
	 */
	Grade(String ID, String Name, int[] Score, int[] Weights) {
		id = ID;
		name = Name;
		scores = Score;
		for (int i = 0; i < 5 ; i++) {
			weightedScore += scores[i] * Weights[i];
		}
		weightedScore /= 100;
	}
	
	/**
	 * Method getID: Return id
	 * 
	 * @return
	 * 		id: the student's id
	 * 
	 * Pseudo Code:
	 * 1. 回傳該學生的id
	 * 
	 * Complexity: O(1)
	 */
	String getID() {
		return id;
	}
	
	/**
	 * Method getName: Return name
	 * 
	 * @return
	 * 		name: the student's name
	 * 
	 * Pseudo Code:
	 * 1. 回傳該學生的姓名
	 * 
	 * Complexity: O(1)
	 */
	String getName() {
		return name;
	}
	
	/**
	 * Method getScores: Return scores
	 * 
	 * @return
	 * 		scores: array of the student's scores
	 * 
	 * Pseudo Code:
	 * 1. 回傳該學生的各科成績
	 * 
	 * Complexity: O(1)
	 */
	int[] getScores() {
		return scores;
	}
	
	/**
	 * Method getWeightedScore: Return weightedScore
	 * 
	 * @return
	 * 		weightedScore: the students's weightedScore
	 * 
	 * Pseudo Code:
	 * 1. 回傳該學生的加權平均
	 * 
	 * Complexity: O(1)
	 */
	int getWeightedScore() {
		return weightedScore;
	}
}
