package pkg629project;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jimin
 */
public class MaxBandwithPath {
    public enum Status {
    intree,
    fringe,
    unseen;
    }
    public static void noHeap(RandomGraph g, int s){
    ArrayList<Integer> fringe=new ArrayList<>();
    int[] vertices=new int[g.v];//length 5000
    for(int i=0;i<vertices.length;++i){
        vertices[i]=i; //0-4999
    }
    int count=0;//num of vertices in the tree
    int[] bw=new int[g.v];//length 5000
    int[] dad=new int[g.v];//length 5000
    Status[] status=new Status[g.v];//length 5000
    Arrays.fill(status, Status.unseen);
    status[s]=Status.intree;
    bw[s]=Integer.MAX_VALUE;
    dad[s]=-1;
    count++;
    ArrayList<Edge> edge=g.getNeighbour(s);                                     //relax adj of source
    // System.out.print();
    for(Edge temp:edge){
        int v=temp.v;
        dad[v]=s;
        bw[v]=temp.weight;
        status[v]=Status.fringe;
        fringe.add(v);
    }
    //   System.out.print(fringe);
    while(count<g.v&&fringe.size()!=0){
        Collections.sort(fringe, (a, b) -> bw[b]-bw[a]);                        //pick the v with max bw
        int max=fringe.get(0);
        status[max]=Status.intree;
        count++;
        fringe.remove(0);
        ArrayList<Edge> edges=g.getNeighbour(max);
        for(Edge temp:edges){                                                   //relax each vertices connected to max
            int v=temp.v;
            if(status[v]==Status.unseen){                                       //Unseen vertices
                dad[v]=max;
                status[v]=Status.fringe;
                bw[v]=Math.min(bw[max], temp.weight);
                fringe.add(v);
            }
            if(status[v]==Status.fringe&&bw[v]<Math.min(bw[max],temp.weight) ){
                dad[v]=max;
                bw[v]=Math.min(bw[max], temp.weight);
            }
        }
    }
    
    System.out.println(bw);
    
    }
    public static void withHeap(RandomGraph g,int s){ //Generate a maxbandwidth path with heap
        int[] vertices=new int[g.v];                                            //length 5000
        int[] dad=new int[g.v];
        int[] bw=new int[g.v];
        for(int i=0;i<vertices.length;++i){                                     //length 5000
            vertices[i]=i;
        }
        //int[] weight=new int[g.v];
        Arrays.fill(bw, -1);
        bw[s]=Integer.MAX_VALUE;                                                                    
        Heap h=new Heap(vertices,bw);                                           //Create a heap with 0-4999 vertices and preset bandwidth
        while(h.size!=0){
            //int bandwidth=h.D[h.getPosition(h.maximum())];
            int temp=h.extractMax();                                            //Get vertex with maximum bw            
            ArrayList<Edge> neighbour=g.getNeighbour(temp);
            for(Edge e:neighbour){
                int end=e.v;                                                    //vertex at the other end
                if(bw[end]<Math.min(bw[temp],e.weight) ){
                    if(h.mapper.containsKey(end)) h.updateKey(h.mapper.get(end), Math.min(bw[temp],e.weight));
                    bw[end]=Math.min(bw[temp],e.weight);
                }
            }
        }
        //bw[0]=0;
        System.out.print(bw);
    }
    
    
}
