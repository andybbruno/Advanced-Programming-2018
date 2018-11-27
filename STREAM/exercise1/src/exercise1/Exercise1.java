package exercise1;

import java.util.ArrayList;
import java.util.stream.*;

/**
 *
 * @author Andrea Bruno 585457
 */
public class Exercise1 {

    public static Integer sumOdd(ArrayList<Integer> list) {

        Integer x = list.stream()
                .mapToInt(a -> a)
                .filter(a -> isOdd(a))
                .sum();

        return x;
    }

    public static boolean isOdd(Integer a) {
        return (a % 2) != 0;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i <= 20; i++) {
            list.add(i);
        }

        System.out.println(sumOdd(list));

    }
}
