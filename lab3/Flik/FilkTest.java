import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest {
    
    @Test
    public void testIsSameNumber() {
        
        /* test the method */
        Integer A = 2;
        Integer B = 2;
        assertTrue(Flik.isSameNumber(A, B));

        /* test the method */
        Integer C = 2;
        Integer D = 3;
        assertFalse(Flik.isSameNumber(C, D));

        /* test the method */
        Integer E = 128;
        Integer F = 128;
        assertTrue(Flik.isSameNumber(E, F));
    }
}
