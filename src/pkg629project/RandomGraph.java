/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author jimin
 */
public class RandomGraph { 
int v;
int e;
int degree;
ArrayList<Edge>[] adj;
Random random;
RandomGraph(int vertices,int de){
    this.v=vertices;
    this.degree=de;
    boolean repeat=false;
    do{
    adj = new ArrayList[v];
    for(int i=0;i<v;++i){
        adj[i]=new ArrayList<Edge>();
    }
  //  HashSet<Integer> set=new HashSet<>();
//    int count=0;
//    while(!finish){
    
//    int[] ver=new int[vertices*de];
//    for(int i=0;i<vertices;++i){
//        for(int j=0;j<de;++j){
//            ver[i+j*vertices]=i;
//        }
//    }
//   
//    shuffle(ver);
//    
//    for (int i = 0; i < vertices*de/2; i++) {
//        int v1=ver[2*i];
//        int v2=ver[2*i+1];
//        int weight=random.nextInt(21);
//        adj[v1].add(new Edge(v1,v2,weight));
//        adj[v2].add(new Edge(v2,v1,weight));
//    }
//    finish=works(adj);
//    count++;
//    }
 //   System.out.print("count="+count);
    int count=v;
    //boolean repeat=false;
    random=new Random();
    
    repeat=false;    
  //  count=v;
    for(int i=0;i<v;++i){
        if(adj[i].size()==6) continue;
        System.out.println(i+" "+count+" "+adj[i].size());
        if(count-1<de-adj[i].size()) {
            repeat=true;
            for(int j=0;j<v;++j){
            adj[j]=new ArrayList<Edge>();
            }
           // count=v;
            break;
        }
        
        while(adj[i].size()<de){
            int temp=random.nextInt(v-i)+i;
            if(temp!=i&&!contain(temp,adj[i])&&adj[temp].size()<de){
                Edge edge1=new Edge(i,temp);
                edge1.setWeight(random.nextInt(21));
                adj[i].add(edge1);
                Edge edge2=new Edge(temp,i);
                edge2.setWeight(random.nextInt(21));
                adj[temp].add(edge2);
                System.out.println(i+"  "+temp+"  "+count+" "+(6-adj[i].size()));
                if(adj[temp].size()==de) count--;
                if(adj[i].size()==de)count--;
            }    
        }
        
    } 
    }while(repeat==true);
}
RandomGraph(int vertices,double p){
    this.v=vertices;
    adj = new ArrayList[v];
    random=new Random();
    for(int i=0;i<v;++i){
        adj[i]=new ArrayList<Edge>();
        
    }
    for(int i=0;i<v;++i){
        for(int j=i+i;j<v;++j){
            if(random.nextDouble()<0.2){
                int weight=random.nextInt(21);
                adj[i].add(new Edge(i,j,weight));
                adj[j].add(new Edge(j,i,weight));
            }    
        }
        
    }
//    for(int i=0;i<100;++i){
//    System.out.println(adj[i].size());
//    }
}
public boolean works(ArrayList<Edge>[] adj) {
    HashSet<Integer> set=new HashSet<>();
    for(ArrayList<Edge> list:adj){
        set.clear();
        for(Edge temp:list){
            if(set.contains(temp.v)){
                    for(Edge print:list){
                        System.out.print(print.v+"  ");
                        
                    }
                    System.out.println();
                return false;
            }
            set.add(temp.v);
        }
    }
    return true;
}
public void shuffle(int[] nums){
    random=new Random();
    for(int i=0;i<nums.length;++i){
        int r=i+random.nextInt(nums.length-i);
        int temp=nums[i];
        nums[i]=nums[r];
        nums[r]=temp;        
    }
//    for(int temp:nums){
//    System.out.println(temp);
//    }
}    
public boolean contain(int e, ArrayList<Edge> list){
    for(Edge m:list){
        if(m.getstart()==e||m.getend()==e) return true;
    }
    return false;    
}

public void print(){
    System.out.println(this.v+" "+this.degree);
    for(int i=0;i<v;++i){
        for(Edge e:adj[i]){
            System.out.println(e.w+"-"+e.v+"  "+e.weight);
        }
    }
}

}
