/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectiontest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;
 

/**
 *
 * @author andrea
 */
public class ReflectionTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();

        try {
            Class test = Class.forName(name);
                        
            System.out.println("************ FIELDS ************");
            for (Field x : test.getFields())
                System.out.println(x.toString());
            
            
            System.out.println("\n************ METHODS ************");
            for (Method x : test.getMethods())
                System.out.println(x.toString());
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
