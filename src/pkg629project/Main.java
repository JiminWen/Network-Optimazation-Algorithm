/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import static pkg629project.MaxBandwithPath.Kruskal;
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
    RandomGraph g1=new RandomGraph(5000,6);
    //g.connect();
    RandomGraph g2=new RandomGraph(5000,0.2);
    g1.connect();
    g2.connect();
//    for(int i=0;i<26;++i){
//    noHeap(g1,2,8);
//    withHeap(g1,2,8);
//    Kruskal(g1,2,8);
//    noHeap(g2,2,8);
//    withHeap(g2,2,8);
//    Kruskal(g2,2,8);
//    }
    }
}
