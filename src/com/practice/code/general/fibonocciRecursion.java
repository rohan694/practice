package com.practice.code.general;

public class fibonocciRecursion {

	public static void main(String args[]) {
		//System.out.println(fibonocci(45));
		int a[][]=new int[8][9];
		System.out.println(fibonoccioptimised(1,1,45));
	}
	
	public static long fibonocci(long num) {
		if(num==3) {
			return 2;
		}
		if(num==2) {
			return 1;
		}
		if(num==1) return 0;
		
		return fibonocci(num-1)+fibonocci(num-2);
	}
	
	public static long fibonoccioptimised(long a,long b,long num) {
		if(num==3) return a+b;
		
		if(num==2) return b;
		
		if(num==1) return a;
		
		return fibonoccioptimised(b,a+b,num-1);
	}

}
