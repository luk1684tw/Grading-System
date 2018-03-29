public class Main {

	public static void main(String[] args) {
		GradeSystem gradesystem = new GradeSystem(); 
		//UI ui = new UI();
		gradesystem.LoadFile();
		gradesystem.CommandListener();

	}

}
