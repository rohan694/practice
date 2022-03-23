package com.practice.code.general;

import java.util.*;
import java.util.stream.IntStream;
public class SubArraySumEqualK {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[] nums=new int[n];
		IntStream.of(nums).map(x-> sc.nextInt());
		int k=sc.nextInt();
		int out=0;
        for(int i=0;i<nums.length;i++){
        	int sum=nums[i];
            for(int j=0;j<nums.length;j++){
                sum+=nums[j];
                if(sum==k) {
                	out++;
                }
            }
        }

	}
}
