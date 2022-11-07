package pl.edu.pw.ee;


import org.junit.Test;
import static org.junit.Assert.*;


public class HashOpenAdressingTest {
    
    public HashOpenAdressingTest() {
    }
    

    @Test
    public void testPut() {
        Object newElem = null;
        HashOpenAdressing instance = new HashOpenAdressingImpl();
        instance.put((Comparable) newElem); 
    }


    @Test
    public void testGetSize() {
        System.out.println("getSize");
        HashOpenAdressing instance = new HashOpenAdressingImpl();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);

    }

    public class HashOpenAdressingImpl extends HashOpenAdressing {

        public int hashFunc(int key, int i) {
            return 0;
        }
    }
    
}
