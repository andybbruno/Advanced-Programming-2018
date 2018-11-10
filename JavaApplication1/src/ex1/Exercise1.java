/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author andrea
 */
public class Exercise1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer n = 0;
        
        while(n<1){
            System.out.println("How many strings?");
            n = scanner.nextInt();
        }
        
        String [] arr = new String[n];
        
        for(int i = 0; i < n; i++){
            System.out.println("String " + i);
            arr[i] = scanner.next();
        }
        
        System.out.println("Finish!");
        
        Arrays.sort(arr);
        
        LinkedList<String> evenArr = even(arr);
        
        for (String a : evenArr)
            System.out.println(a);

    }
    
    static LinkedList<String> even (String[] str){
        LinkedList<String> tmp = new LinkedList<>();
        
        for(String x : str)
            if((x.length() % 2) == 0)
                tmp.add(x);
        
        return tmp;
    }
    
}
