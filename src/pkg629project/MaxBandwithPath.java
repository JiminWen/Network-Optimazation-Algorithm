package pkg629project;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
    public static List<Integer> noHeap(RandomGraph g, int s, int t){
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
    for(Edge temp:edge){
        int v=temp.v;
        dad[v]=s;
        bw[v]=temp.weight;
        status[v]=Status.fringe;
        fringe.add(v);
    }
    while(status[t]!=Status.intree){
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
    List<Integer> path=new ArrayList<>();
    path.add(t);
    while(dad[t]!=-1){
        t=dad[t];
        path.add(t);        
    }    
    Collections.reverse(path);
    System.out.println(bw);
    
    return path;
    }
    public static List<Integer> withHeap(RandomGraph g,int s,int t){ //Generate a maxbandwidth path with heap
        int[] vertices=new int[g.v];                                            //length 5000
        int[] dad=new int[g.v];
        int[] bw=new int[g.v];
        for(int i=0;i<vertices.length;++i){                                     //length 5000
            vertices[i]=i;
        }
        //int[] weight=new int[g.v];
        Arrays.fill(bw, -1);
        bw[s]=Integer.MAX_VALUE;     
        dad[s]=-1;
        Heap h=new Heap(vertices,bw);                                           //Create a heap with 0-4999 vertices and preset bandwidth
        while(h.size!=0){
            //int bandwidth=h.D[h.getPosition(h.maximum())];
            int temp=h.extractMax();                                            //Get vertex with maximum bw            
            if(temp==t) break;
            ArrayList<Edge> neighbour=g.getNeighbour(temp);
            for(Edge e:neighbour){
                int end=e.v;                                                    //vertex at the other end
                if(bw[end]<Math.min(bw[temp],e.weight) ){
                    if(h.mapper.containsKey(end)) h.updateKey(h.mapper.get(end), Math.min(bw[temp],e.weight));
                    bw[end]=Math.min(bw[temp],e.weight);
                    dad[end]=temp;
                }
            }
        }
    List<Integer> path=new ArrayList<>();
    path.add(t);
    while(dad[t]!=-1){
        t=dad[t];
        path.add(t);        
    }    
    Collections.reverse(path);
    System.out.println(bw);
   
    return path;
    }
    public static List<Integer> Kruskal(RandomGraph g, int s, int t){
    UnionFind UF=new UnionFind(g.v);//5000 unions initially
   // Heap h=new Heap();                                //get all the edges
    int cur=0;
    HashMap<Integer,Edge> map=new HashMap<>();          //Use a map to find edge based on index
    int[] index=new int[g.e];
    int[] weight=new int[g.e];    
    for(int i=0;i<g.v;++i){
        for(int j=0;j<g.adj[i].size();++j){
            Edge temp=g.adj[i].get(j);
            if(temp.v>i) {
                map.put(cur,temp);
                weight[cur]=temp.weight;
                index[cur]=cur;
                cur++;
            }
        }
    }
    Heap h=new Heap(index,weight);
    //Edge[] res=new Edge[g.v-1];
    int e=0;
    RandomGraph MST=new RandomGraph(g.v,0);
    while(h.size!=0){
        int temp=h.extractMax();                                                //index of the edge
        Edge edge=map.get(temp);
        int start=edge.w;                                              //the starting point of edge
        int end=edge.v;                                                //the end of the edge
        int W=edge.weight;                                                 
        if(UF.find(start)!=UF.find(end)){
        UF.Union(start, end);
        //res[e++]=map.get(temp);                                                 //Add the edge
        MST.adj[start].add(edge);
        MST.adj[end].add(new Edge(end,start,W));
        }
    }
    System.out.print("hehe");
    int[] visit=new int[g.v];
    List<Integer> path=new ArrayList<Integer>();
    int[] bw=new int[2];
    bw[0]=Integer.MAX_VALUE;
    bw[1]=-1;
    MST.findPath(s, t,visit,path,bw);
    System.out.print("test");
    return path;
}
}