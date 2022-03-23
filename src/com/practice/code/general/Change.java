package com.practice.code.general;

/*
 * 0.01 0.05 0.1 0.25 0.5 1
 * Example:
getChange(5, 0.99) // should return [1,0,0,0,0,4]

getChange(3.14, 1.99) // should return [0,1,1,0,0,1]
getChange(3, 0.01) // should return [4,0,2,1,1,2]
getChange(4, 3.14) // should return [1,0,1,1,1,0]
getChange(0.45, 0.34) // should return [1,0,1,0,0,0]
 */
public class Change {
public static void main(String args[]) {
	System.out.println(getChange(3.14, 1.99));
}
public static int[] getChange(double rec,double price) {
	int[] res=new int[6];
	
	double change=rec-price;//4.01
	double currDivider=100;
	int index=5;
	while (change!=0 && change >= 0.01 && currDivider>=0.01 && index>=0) {
		int temp=(int)(change/currDivider);
		res[index--]=temp;
		change=(float)change-(float)temp;
		currDivider=currDivider/2;
	}
	return res;
}

}
