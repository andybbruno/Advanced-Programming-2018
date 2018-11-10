/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3;

import java.util.LinkedList;

/**
 *
 * @author andrea
 */
public class MyQueue {
    private LinkedList<String> data = new LinkedList<String>();
 
    public void enqueue(String item) {
        data.addLast(item);
    }
 
    public String dequeue() {
        return data.removeFirst();    
    }
    
}
