/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import static pkg629project.MaxBandwithPath.noHeap;
import static pkg629project.MaxBandwithPath.withHeap;

/**
 *
 * @author jimin
 */
public class Main {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    RandomGraph g=new RandomGraph(1000,0.2);
    g.print();
    noHeap(g,2);
    withHeap(g,2);
    

    }
}
