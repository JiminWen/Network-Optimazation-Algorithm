/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    e=0;    
    adj = new ArrayList[v];
    for(int i=0;i<v;++i){
        adj[i]=new ArrayList<Edge>();
    } 
    int count=v;
    random=new Random();    
    repeat=false;    
    for(int i=0;i<v;++i){
        if(adj[i].size()==degree) continue;
        if(count-1<de-adj[i].size()) {
            repeat=true;
            for(int j=0;j<v;++j){
            adj[j]=new ArrayList<Edge>();
            }
            break;
        }
        
        while(adj[i].size()<de){
            int temp=random.nextInt(v-i)+i;
            if(temp!=i&&!contain(temp,adj[i])&&adj[temp].size()<de){
                e++;
                Edge edge1=new Edge(i,temp);
                edge1.setWeight(random.nextInt(1000)+1);
                adj[i].add(edge1);
                Edge edge2=new Edge(temp,i);
                edge2.setWeight(random.nextInt(1000)+1);
                adj[temp].add(edge2);
                if(adj[temp].size()==de) count--;
                if(adj[i].size()==de)count--;
            }    
        }        
    } 
    }while(repeat==true);
}
RandomGraph(int vertices,double p){
    this.v=vertices;
    e=0;
    adj = new ArrayList[v];
    random=new Random();
    for(int i=0;i<v;++i){
        adj[i]=new ArrayList<Edge>();        
    }
    for(int i=0;i<v;++i){
        for(int j=i+i;j<v;++j){
            if(random.nextDouble()<0.2){
                int weight=random.nextInt(1000)+1;
                adj[i].add(new Edge(i,j,weight));
                adj[j].add(new Edge(j,i,weight));
                e++;
            }    
        }
    }
}
public void connect(){
    for(int i=0;i<v-1;++i){
        adj[i].add(new Edge(i,i+1,new Random().nextInt(20)+1));
        e++;
    }
}
public ArrayList<Edge> getNeighbour(int i){
    return adj[i];
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

}    
public boolean contain(int e, ArrayList<Edge> list){
    for(Edge m:list){
        if(m.getstart()==e||m.getend()==e) return true;
    }
    return false;    
}
public boolean findPath(int start, int end,int[] visit,List<Integer> path,int[] bw){
    visit[start]=1;
    if(start==end) {
        path.add(0,start);
        //bw=Math.min();
        return true;
    }
    for(Edge e:adj[start]){
        if(visit[e.v]==0&&findPath(e.v,end,visit,path,bw)==true){
            path.add(0,start);
            //bw[0]=Math.min(bw[0],e.weight);
            if(e.weight<bw[0]){
                bw[0]=e.weight;
                bw[1]=start;
            }
            return true;
        }
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
