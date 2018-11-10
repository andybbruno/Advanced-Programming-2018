package checknpe;

/**
 * Class used to test CheckNPE functionalities
 * @author Andrea Bruno 585457
 */

public class SimpleClass {
    
    private int x;
    private Integer y;
    private boolean test;
    
    public SimpleClass() {
    
    }

    public SimpleClass(int x) {
        this.x = x++;
    }
    
    public SimpleClass(Integer y) {
        this.y = y++;
    }
    
    public SimpleClass(String s1, String s2) {
        test = s1.equals(s2);
    }   

    public Integer simpleMethod1(Integer x) {
        return x++;
    }

    public Integer simpleMethod2(Integer x, Integer y) {
        return x + y;
    }

    public void simpleMethod3(String x) {
        char charAt = x.charAt(1);
    }
}
