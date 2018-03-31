/**
 * Class Main: Entry point of program
 * 
 * Bugs: none known
 * 
 * @author: Michael Chung
 */

public class Main {
	
	/**
	 * Constructor: Construct the whole system
	 * 
	 * Pseudo code:
	 * 1. 建立一個gradesystem
	 * 2. 讀取檔案
	 * 3. 等待有使用者登入
	 * 
	 * @param
	 * args: 沒有使用
	 * 
	 * Complexity: O(1)
	 */
	public static void main(String[] args) {
		GradeSystem gradesystem = new GradeSystem(); 
		gradesystem.LoadFile();
		gradesystem.LoginListener();	
	}

}
