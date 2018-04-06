/**
 * Class IntegrationTest: 測試使用上的功能。
 * Bugs: 尚未發現.
 */


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class IntegrationTest {

    private ByteArrayOutputStream outContent;
    
    /**
     * Method setUp: this method will run before any test method.
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
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    /**
     * Method show: 測試顯示成績.
     * 
     * Pseudo code:
     * 1. 給定登入、顯示成績的輸入.
     * 2. 確認是否與預期答案ㄧ樣.
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void showGradeTest() {
        System.setIn(new ByteArrayInputStream("955002056\nG".getBytes()));
        GradeSystem gs = new GradeSystem(); 
        gs.LoadFile();
        gs.LoginListener();
        String expected = "";
        expected += "Loading...\n";
        expected += "請輸入學號以登入或按Q離開\n>> ";
        expected += "歡迎回來!   許文馨\n";
        expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";
		expected += "許文馨的成績: \r\n";
    	expected += "   lab1: 88\r\n";
    	expected += "   lab2: 92\r\n";
    	expected += "   lab3: 88\r\n";
    	expected += "   mid-term: 98\r\n";
  		expected += "   final exam: 91\r\n";
  		expected += "   Total Score: 92\r\n\n";
  		expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";
        assertEquals(expected, outContent.toString());
    }
    
    /**
     * Method showRankTest: 測試顯示排名功能.
     * 
     * Pseudo code:
     * 1. 給定登入、顯示排名的輸入
     * 2. 確認是否與預期答案ㄧ樣.
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void showRankTest() {
        System.setIn(new ByteArrayInputStream("955002056\nR".getBytes()));
        GradeSystem gs = new GradeSystem(); 
        gs.LoadFile();
        gs.LoginListener();
        String expected = "";
        expected += "Loading...\n";
        expected += "請輸入學號以登入或按Q離開\n>> ";
        expected += "歡迎回來!   許文馨\n";
        expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";
		expected += "許文馨排名: 第10名\n";
  		expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";
        assertEquals(expected, outContent.toString());
    }

	/**
	 * Method showAverageTest: 測試顯示平均.
	 * 
	 * Pseudo code:
	 * 1. 給定登入、顯示平均的輸入.
	 * 2. 確認輸出是否和預期一致.
	 * 
	 * Complexity:
	 * O(1).
	 */
	@Test
	public void showAverageTest() {
	    System.setIn(new ByteArrayInputStream("955002056\nA".getBytes()));
	    GradeSystem gs = new GradeSystem(); 
	    gs.LoadFile();
	    gs.LoginListener();
	    String expected = "";
	    expected += "Loading...\n";
	    expected += "請輸入學號以登入或按Q離開\n>> ";
	    expected += "歡迎回來!   許文馨\n";
	    expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";

    	expected += "各項成績平均: \r\n";
    	expected += "   lab1 平均: 90\r\n";
    	expected += "   lab2 平均: 87\r\n";
    	expected += "   lab3 平均: 89\r\n";
    	expected += "   mid-term 平均: 89\r\n";
    	expected += "   final exam 平均: 88\r\n\n";
    	
		expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";
	    assertEquals(expected, outContent.toString());
	}
	/**
	 * Method showReweight: 測試更改權重功能.
	 * 
	 * Pseudo code:
	 * 1. 給定登入、更改權重的輸入.
	 * 2. 確認是否能正確顯示狀態.
	 * 
	 * Complexity:
	 * O(1).
	 */
	@Test
	public void showReweight() {
	    System.setIn(new ByteArrayInputStream("955002056\nW\n100\n0\n0\n0\n0\nY\n".getBytes()));
	    GradeSystem gs = new GradeSystem(); 
	    gs.LoadFile();
	    gs.LoginListener();
	    String expected = "";
	    expected += "Loading...\n";
	    expected += "請輸入學號以登入或按Q離開\n>> ";
	    expected += "歡迎回來!   許文馨\n";
	    expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";

    	expected += "舊配分: \n";
    	expected += "   lab1 : 10%\r\n";
    	expected += "   lab2 : 10%\r\n";
    	expected += "   lab3 : 10%\r\n";
    	expected += "   mid-term : 30%\r\n";
    	expected += "   final exam : 40%\r\n\n";
    	
    	expected += "請輸入新配分\n";
    	expected += "   lab1:    lab2:    lab3:    mid-term:    final exam: ";
    	expected += "以上正確嗎? 請輸入Y(Yes) 或 N(No)\n>> ";
    	
    	expected += "新配分: \n";
    	expected += "   lab1 : 100%\r\n";
    	expected += "   lab2 : 0%\r\n";
    	expected += "   lab3 : 0%\r\n";
    	expected += "   mid-term : 0%\r\n";
    	expected += "   final exam : 0%\r\n\n";
    	expected += "修改成功，回到選單\n";
    	
		expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";
	    assertEquals(expected, outContent.toString());
	}
	/**
	 * Method showLogoutTest: 測試登出功能.
	 * 
	 * Pseudo code:
	 * 1. 給定登入、登出指令.
	 * 2. 確認是否能正確顯示狀態.
	 * 
	 * Complexity:
	 * O(1).
	 */
	@Test
	public void showLogoutTest() {
	    System.setIn(new ByteArrayInputStream("955002056\nE\nQ".getBytes()));
	    GradeSystem gs = new GradeSystem(); 
	    gs.LoadFile();
	    gs.LoginListener();
	    String expected = "";
	    expected += "Loading...\n";
	    expected += "請輸入學號以登入或按Q離開\n>> ";
	    expected += "歡迎回來!   許文馨\n";
	    expected += "輸入指令   1) G 顯示成績 \n";
		expected += "       2) R 顯示排名 \n";
		expected += "       3) A 顯示平均 \n";
		expected += "       4) W 更新配分\n";
		expected += "       5) E 登出系統\n>> ";

		expected += "下次見 ! 許文馨\n";
    	expected += "請輸入學號以登入或按Q離開\n>> ";
    	expected += "Closing GradeSystem... ... ...\n";
    	expected += "成績系統已關閉\n";
	    assertEquals(expected, outContent.toString());
	}
}