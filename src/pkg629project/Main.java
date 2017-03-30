/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import java.util.Random;
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
    System.out.println("test heap");
    int[] index=new int[10000];
    Random r=new Random();
    int[] w=new int[10000];
  
    for(int i=0;i<index.length;++i){
        index[i]=i;
        w[i]=i;
    } 
//    for(int temp:w){
//        System.out.println(temp);
//    }
    Heap h=new Heap(index,w);
    int i=1;
    System.out.println("---------");
    while(h.size!=0){
        int pre=h.D[1];
        //System.out.println(pre);
        h.delete(1);
        if(pre<h.D[1]) System.out.println(pre+" "+h.D[1]);
        i++;
    }
    RandomGraph g1=new RandomGraph(5000,6);
    //g.connect();
    RandomGraph g2=new RandomGraph(5000,0.2);
    g1.connect();
    g2.connect();
    noHeap(g1,2,4000);
    withHeap(g1,2,4000);
    Kruskal(g1,2,4000);
    }
}
