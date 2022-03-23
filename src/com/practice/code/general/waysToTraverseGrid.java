package com.practice.code.general;

import java.util.HashMap;
import java.util.Map;

public class waysToTraverseGrid {

	public static void main(String args[]) {
		int a[][]=new int[6][9];
		Map<String,Long> memory=new HashMap<String, Long>();
		System.out.println(noOfWays(5,5,memory));
	}
	public static long noOfWays(long row,long col,Map<String,Long> memory) {
		if(row==1 && col ==1) return 0;
		if(row==2 && col ==1) return 1;
		if(row==1 && col ==2) return 1;
		if(row<1 ||col <1) return 0;
		long result;
		if(!memory.containsKey(row+","+col)) {
			result=noOfWays(row-1,col,memory)+noOfWays(row,col-1,memory);
			memory.put(row+","+col,result);
			memory.put(col+","+row,result);
			System.out.println("miss");
		}else {
			result= memory.get(row+","+col);
			System.out.println("hit");
		}
		return result;
	}
}
