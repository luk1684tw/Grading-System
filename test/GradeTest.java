/**
 * Class GradeTest: 測試 class: Grade的public method.
 * Bugs: 尚未發現.
 * 
 */
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GradeTest {

    private Grade grade1, grade2;
    
    /**
     * Method setUpTest: 初始化設定.
     * 
     * Pseudo code:
     * 1. 新增兩個grade.
     * 
     * Complexity:
     * O(1).
     */
    @Before
    public void setUpTest() {
        grade1 = new Grade("66666666", "賀陳弘", new int[] {50, 60, 70, 80, 40}, new int[] {20, 20, 20, 20, 20});
        grade2 = new Grade("94539453", "賴尚鴻", new int[] {60, 70, 80, 90, 100}, new int[] {20, 20, 20, 20, 20});
    }

    /**
     * Method getIDTest: 測試getID.
     * 
     * Pseudo code:
     * 1. 確認輸出是否為預期ID.
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void getIDTest() {
        assertEquals("66666666", grade1.getID());
        assertEquals("94539453", grade2.getID());
    }
    
    /**
     * Method getNameTest: 測試getName.
     * 
     * Pseudo code:
     * 1. 確認是否為預期Name.
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void getNameTest() {
        assertEquals("賀陳弘", grade1.getName());
        assertEquals("賴尚鴻", grade2.getName());
    }
    
    /**
     * Method getScoresTest: 測試getScores.
     * 
     * Pseudo code:
     * 1. 新增兩個暫時的假資料.
     * 2. 比對getScores是否正確match假資料.
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void getScoresTest() {
        int[] temp1 = {50, 60, 70, 80, 40}, temp2 = {60, 70, 80, 90, 100};
        int[] test1 = grade1.getScores(), test2 = grade2.getScores();
        for (int i = 0; i < 5; i++) {
            assertEquals(temp1[i], test1[i]);
            assertEquals(temp2[i], test2[i]);
        }
    }
    
    /**
     * Method getWeightedScoreTest: 測試getWeightedScore.
     * 
     * Pseudo code:
     * 1. 確認是否能正確抓到Weighted Score.
     * 
     * Complexity:
     * O(1).
     */
    @Test
    public void getWeightedScoreTest() {
        assertEquals(60, grade1.getWeightedScore());
        assertEquals(80, grade2.getWeightedScore());
    }
    
   
}
