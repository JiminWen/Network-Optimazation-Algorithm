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
public class Heap {
    int size;
    int[] H;   
    int[] D;  
    Heap(int[] vertices,int[] values){
        H=new int[vertices.length+1];
        D=new int[values.length+1];
        size=vertices.length;
        for(int i=1;i<H.length;++i){
            H[i]=vertices[i-1];
            D[i]=values[i-1];
        }
        for(int i=1;i<H.length;++i){
            heapfy(i);
        }
    }
    public int maximum(){
        return H[1];
    }
    public void insert(int weight){
        int[] temp1=new int[H.length+1];
        int[] temp2=new int[D.length+1];
        size++;
        for(int i=1;i<=H.length-1;++i){
            temp1[i]=H[i];
            temp2[i]=D[i];
        }
        temp1[temp1.length-1]=H.length;
        temp2[temp2.length-1]=weight;
        heapfy(temp1.length-1);
    }
    public void delete(int h){
        
        size=size-1;
        int[] temp1=new int[size];
        int[] temp2=new int[size];
        for(int i=1;i<temp1.length;++i){
            temp1[i]=H[i];
            temp2[i]=D[i];
        }
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
    else if(k<size/2&&D[k]<Math.max(D[2*k], D[2*k+1])){
        int h=k;
        while(h<size/2&&D[h]<Math.max(D[2*h],D[2*h+1])){
            int d=D[2*h]<D[2*h+1]?2*h+1:2*h;
            exch(h,d);
            h=d;
        }
    }
    }
    public void exch(int i1,int i2){
    int temp=D[i1];
    D[i1]=D[i2];
    D[i2]=temp;
    int temp2=H[i1];
    H[i1]=H[i2];
    H[i2]=temp2;
    }    
    }
