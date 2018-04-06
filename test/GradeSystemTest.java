/**
 * Class GradeSystemTest: 測試 class: GradeSystem的public method.
 * Bugs: 尚未發現.
 * @author weichu.
 */

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class GradeSystemTest {
    
    private ByteArrayOutputStream output;
    /**
     * Method setUp: 初始化設定.
     * 
     * Pseudo code:
     * 1. new a ByteArrayOutputStream.
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
     * Method getRecordTest: 測試讀取檔案.
     * 
     * Pseudo code:
     * 1. 確認是否能正確load檔案
     * 
     * Complexity:
     * O(n)：n筆資料讀入
     */
    @Test
    public void loadFileTest() {
    	GradeSystem gs1 = new GradeSystem();
    	gs1.LoadFile();
        assertEquals("Loading...\n", output.toString());
    }
    
    /**
     * Method loginListenerTest: 測試Login listener.
     * 
     * Pseudo code:
     * 1. 給定測試輸入
     * 2. 測試是否能照預期輸出文字
     * 3. 清空螢幕buffer，再執行下個測試
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void loginListenerTest() {
    	System.setIn(new ByteArrayInputStream("Q".getBytes()));
    	GradeSystem gs1 = new GradeSystem();
    	gs1.LoginListener();
        assertEquals("請輸入學號以登入或按Q離開\n>> Closing GradeSystem... ... ...\n成績系統已關閉\n", output.toString());
        output.reset();
        
        System.setIn(new ByteArrayInputStream("23333333\r\nQ".getBytes()));
    	GradeSystem gs2 = new GradeSystem();
    	gs2.LoginListener();
    	assertEquals("請輸入學號以登入或按Q離開\n>> ID格式錯誤，請確認ID後再輸入\n>> Closing GradeSystem... ... ...\n成績系統已關閉\n", output.toString());
    	output.reset();
    	
    }
}
