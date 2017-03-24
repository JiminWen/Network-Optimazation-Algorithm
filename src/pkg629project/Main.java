/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

/**
 *
 * @author jimin
 */
public class Main {
 
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // RandomGraph g=new RandomGraph(5000,6);    
    // g.print();
    // RandomGraph h=new RandomGraph(5000,0.2);
    // h.print();
    int[] vertices={1,2,3,4,5,6,7};
    int[] weight={100,65,8,45,2,1,6};
    Heap h=new Heap(vertices,weight);
    h.deletemax();
    h.deletemax();
    h.deletemax();
    for(int i=1;i<=h.size;++i){
    System.out.println(h.H[i]+"  "+h.D[i]);
    }
    }
    
}
