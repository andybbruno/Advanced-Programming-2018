/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise3;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 *
 * @author Andrea Bruno 585457
 */
public class Exercise3 {

    public static void repl(Object[] xs, Integer n) {

        Stream<Object> objStream = Stream.of(xs);

        objStream.flatMap(x -> Stream.iterate(x, y -> y).limit(n)).forEach(System.out::println);
               
    }

    
    public static void main(String[] args) {
       
        Integer [] arr = {1,2,3,4};
        
        repl(arr, 4);
    }

}
