/**
 * Class UITest: 測試UI public method.
 * 
 * Bugs: 尚未發現.
 */
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class UITest {

    private ByteArrayOutputStream output;
    
    /**
     * Method setUp: 初始化設定.
     * 
     * Pseudo code:
     * 1. new a ByteArrayOutputStream.
     * 2. redirect output stream.
     * 
     * Complexity:
     * O(1).
     */
    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    /**
     * Method showAverageTest: test showAverage.
     * 
     * Pseudo code:
     * 1. 給定假資料.
     * 2. 確認是否符合預期輸出.
     * 
     * Complexity:
     * O(1);
     */
    @Test
    public void showAverageTest() {
        UI ui1 = new UI();
        int [] averages = {70, 80, 90, 100, 100};
        String [] subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
        ui1.showAverage(averages, subjects);
        
        String expect = "";
    	expect += "各項成績平均: \r\n";
    	expect += "   lab1 平均: 70\r\n";
    	expect += "   lab2 平均: 80\r\n";
    	expect += "   lab3 平均: 90\r\n";
    	expect += "   mid-term 平均: 100\r\n";
    	expect += "   final exam 平均: 100\r\n\n";
    	assertEquals(expect, output.toString());
    }
    
    /**
     * Method showFinishMsgTest: test showFinishMsg.
     * 
     * Pseudo code:
     * 1. 給定假資料.
     * 2. 確認是否符合預期輸出.
     * 
     * Complexity:
     * O(1);
     */
    @Test
    public void showFinishMsgTest() {
    	UI ui1 = new UI();
        ui1.showFinishMsg(true, "桐谷和人");
        
        String expect = "";
    	expect += "下次見 ! 桐谷和人\n";
    	expect += "請輸入學號以登入或按Q離開\n>> ";
    	assertEquals(expect, output.toString());
    	
    	output.reset();
    	UI ui2 = new UI();
        ui2.showFinishMsg(false, "桐谷和人");
        
        String expect2 = "";
    	expect2 += "Closing GradeSystem... ... ...\n";
    	expect2 += "成績系統已關閉\n";
    	assertEquals(expect2, output.toString());
    }
    
    /**
     * Method showGradeTest: test showGrade.
     * 
     * Pseudo code:
     * 1. 給定假資料.
     * 2. 確認是否符合預期輸出.
     * 
     * Complexity:
     * O(1);
     */
    @Test
    public void showGradeTest() {
    	UI ui1 = new UI();        
    	Grade grade1 = new Grade("66666666", "賀陳弘", new int[] {50, 60, 70, 80, 40}, new int[] {20, 20, 20, 20, 20});
    	String[] Subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
    	ui1.showGrade(grade1, Subjects);
    	String expect = "";
    	expect += "賀陳弘的成績: \r\n";
    	expect += "   lab1: 50*\r\n";
    	expect += "   lab2: 60\r\n";
    	expect += "   lab3: 70\r\n";
    	expect += "   mid-term: 80\r\n";
  		expect += "   final exam: 40*\r\n";
  		expect += "   Total Score: 60\r\n\n";

  		assertEquals(expect, output.toString());
    }
  
    /**
     * Method showRankTest: test showRank.
     * 
     * Pseudo code:
     * 1. 給定假資料.
     * 2. 確認是否符合預期輸出.
     * 
     * Complexity:
     * O(1);
     */
    @Test
    public void showRankTest() {
    	UI ui1=new UI();
		ui1.showRank(1, "金城武");
		assertEquals(("金城武排名: 第1名\n"), output.toString());
	}
    /**
     * Method showWeightTest: test showWeight.
     * 
     * Pseudo code:
     * 1. 給定假資料.
     * 2. 確認是否符合預期輸出.
     * 
     * Complexity:
     * O(1);
     */
	@Test
	public void showWeightTest() {
		UI ui1=new UI();
		int []w= {100, 0, 0, 0, 0};
		String[] Subjects = {"lab1", "lab2", "lab3", "mid-term", "final exam"};
		ui1.showWeights(w,Subjects);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			builder.append("   " + Subjects[i] + " : " + w[i] + "%" + "\r\n");
		}
		builder.append("\n");
		assertEquals(builder.toString(), output.toString());
	}
  
	/**
     * Method showWelcomeMsgTest: test showWelcomeMsg.
     * 
     * Pseudo code:
     * 1. 給定假資料.
     * 2. 確認是否符合預期輸出.
     * 
     * Complexity:
     * O(1);
     */
	public void showWelcomeMsgTest() {
		String expect="";
		UI ui1=new UI();
		ui1.showWelcomeMsg();
		expect += "輸入指令   1) G 顯示成績 \n";
		expect += "       2) R 顯示排名 \n";
		expect += "       3) A 顯示平均 \n";
		expect += "       4) W 更新配分\n";
		expect += "       5) E 登出系統\n>> ";
		assertEquals(expect, output.toString());
	}
}
