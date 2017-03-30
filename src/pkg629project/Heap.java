/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg629project;

import java.util.HashMap;

/**
 *
 * @author jimin
 */
public class Heap {
    int size;
    int[] H;  //record the vertices in the heap 
    int[] D;  //record value in the heap
    HashMap<Integer,Integer> mapper; //Map index of a vertex to position in the heap
    Heap(){
        mapper=new HashMap();
        H=new int[1];
        D=new int[1];
        mapper=new HashMap();
    }    
    Heap(int[] vertices,int[] values){
        mapper=new HashMap();
        H=new int[vertices.length+1];
        D=new int[values.length+1];
        size=vertices.length;                                                   //number of items in the heap
        for(int i=1;i<=vertices.length;++i){
            H[i]=vertices[i-1];
            D[i]=values[i-1];
            mapper.put(vertices[i-1],i);
        }
        for(int i=size;i>=1;--i){
            heapfy(i);
        }
    }
    public void updateKey(int key,int weight){
        D[key]=weight;
        heapfy(key);
    }
    public void resize(int cap){
        int[] temp1 = new int[cap];
        int[] temp2= new int[cap];
        for (int i = 1; i <= size; i++) {
            temp1[i] = H[i];
            temp2[i] = D[i];
        }
        H = temp1;
        D = temp2;
    }    
    public int maximum(){
        return H[1];
    }
    public int extractMax(){
        int temp=maximum();
        deletemax();
        return temp;
    }
    public void insert(int v,int weight){
        if(size==H.length-1) resize(2*H.length);
        size++;
        H[size]=v;
        D[size]=weight;
        mapper.put(H[size], size);
        heapfy(size);
    }
    public void deletemax(){
        delete(1);
    }
    public void delete(int h){
        mapper.remove(H[h]);
        H[h]=H[size];
        D[h]=D[size];
        //mapper.remove(H[size]);
        size--;
        mapper.put(H[h],h);
        if ((size > 0) && (size == (H.length - 1) / 4)) resize(H.length  / 2);
        heapfy(h);
    }
    public void heapfy(int k){
    if(k>1&&D[k]>D[k/2]){
        int h=k;
        while(h>1&&D[h]>D[h/2]){
            exch(h,h/2);
            h=h/2;
        }
    }
    else if(k<=size/2){
        int h=k;
        while(h<=size/2){
            int d=2*h;
            if(d<size&&D[d+1]>D[d])d=d+1;
           // int d=D[2*h]<D[2*h+1]?2*h+1:2*h;
            if(D[h]<D[d])exch(h,d);
            h=d;
        }
    }
    }
    public void exch(int i1,int i2){
   // int pos1=mapper.get(i2);
    
    int temp=D[i1];
    D[i1]=D[i2];
    D[i2]=temp;
    int temp2=H[i1];
    H[i1]=H[i2];
    H[i2]=temp2;
    mapper.put(H[i1], i1);
    mapper.put(H[i2], i2);
    }
    public int getPosition(int v){
    return mapper.get(v);
    }
    }
