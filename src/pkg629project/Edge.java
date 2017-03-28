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
public class Edge {
int w;
int v;
int weight;
Edge(int start,int end,int weight){
    this.w=start;
    this.v=end;
    this.weight=weight;
}
Edge(int start,int end){
    this.w=start;
    this.v=end;
}
public void setWeight(int w){
    this.weight=w;
}

public int getstart(){
   return w;
}
public int getend(){
   return v; 
}
}
