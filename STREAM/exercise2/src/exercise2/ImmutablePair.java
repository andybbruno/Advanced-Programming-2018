/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2;

/**
 *
 * @author Andrea Bruno 585457
 */
public class ImmutablePair<T1,T2> {
    
    T1 a;
    T2 b;
    
    ImmutablePair(T1 a, T2 b){
        this.a = a;
        this.b = b;
    }
    
    T1 getFirst(){
        return a;
    }
    
    T2 getSecond(){
        return b;
    }
}
