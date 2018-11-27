/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

/**
 *
 * @author Andrea Bruno 585457
 */
public class MainClass {

    public static ImmutablePair<Integer, Double> someCalculation(List<Double> lst) {

        Long first = lst.stream()
                .mapToDouble(a -> a)
                .filter(a -> (0.2 <= a) && (a <= Math.PI))
                .count();

        OptionalDouble second = lst.stream()
                .mapToDouble(a -> a)
                .filter(a -> (10 <= a) && (a <= 100))
                .average();

        ImmutablePair<Integer, Double> x = new ImmutablePair<>(first.intValue(), second.getAsDouble());

        return x;

    }

    public static void main(String[] args) {
        ArrayList<Double> list = new ArrayList<Double>();

        for (double i = 0.2; i < 20 ; i += 0.5) {
            list.add(i);
        }
        
        
        ImmutablePair<Integer, Double> result = someCalculation(list);
        System.out.println(result.getFirst());
        System.out.println(result.getSecond());
    }

}
