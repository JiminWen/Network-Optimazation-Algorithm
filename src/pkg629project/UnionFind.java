/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jimin
 */
public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int count;          //number of unions in the structure
    public UnionFind(int n){
        count=n;
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;++i){
            parent[i]=i;
            rank[i]=0;
        }
    }
    public void Union(int p,int q){
        int rootp=find(p);
        int rootq=find(q);
        if(rootp==rootq) return;
        if(rank[rootp]<rank[rootq])parent[rootp]=rootq;
        else if(rank[rootp]>rank[rootq]) parent[rootq]=rootp;
        else{
            parent[rootp]=rootq;
            rank[rootq]++;
        }
        count--;
    }
    public int count(){
        return count;
    }
    public void validate(int m){
    if(m<0) try {
        throw new Exception("out of bound");
    } catch (Exception ex) {
        Logger.getLogger(UnionFind.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    public int find(int m){
    validate(m);
    while(m!=parent[m]){
        parent[m]=parent[parent[m]];
        m=parent[m];
    }
    return m;
    }
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }    
}
