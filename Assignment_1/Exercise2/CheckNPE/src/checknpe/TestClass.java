package checknpe;

/**
 * Class used to test CheckNPE functionalities
 * @author Andrea Bruno 585457
 */

public class TestClass {

    private byte x;
    private Double y;
    private char chr;

    public TestClass() {
    
    }

    public TestClass(byte x) {
        this.x = x++;
    }

    public TestClass(Double y) {
        this.y = y++;
    }

    public TestClass(String s, Integer x) {
        chr = s.charAt(x);
    }

    public Integer testMethod1(int x) {
        return x++;
    }

    public Integer testMethod2(int x, int y) {
        return x + y;
    }

    public void testMethod3(String x) {
        char charAt = x.charAt(1);
    }
    
    public void testMethod4() {
        
    }
}
