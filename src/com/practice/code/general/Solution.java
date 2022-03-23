package com.practice.code.general;
import java.util.*;
import java.util.stream.IntStream;

public class Solution{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int T=sc.nextInt();
        for(int i=1;i<=T;i++){
            int d=sc.nextInt(),n=sc.nextInt(),k=sc.nextInt();
            int[][] happiness=new int[n][3];
            
            for(int j=0;j<n;j++){
                happiness[j][0]=sc.nextInt();
                happiness[j][1]=sc.nextInt();
                happiness[j][2]=sc.nextInt();
            }
            int maxHap=0;
            for(int j=0;j<d;j++){
                int[] temp=new int[k]; 
                Arrays.fill(temp, 0);
                int count=0;
                for(int hi=0;hi<n;hi++){
                    if(happiness[hi][1]<=(j+1) && happiness[hi][2]>=(j+1)){
                        Arrays.sort(temp);
                        temp[0]=happiness[hi][0];
                    }
                }
                int sum=IntStream.of(temp).sum();
                if(sum>maxHap)    
	                maxHap=sum;
            }
            System.out.println("Case #"+i+": "+maxHap);
        }

    }
}