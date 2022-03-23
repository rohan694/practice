package com.practice.code.general;

public class countPathsFromSquare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(count(3,3));
		System.out.println(printPaths(3,3));
	}
	static  int count(int row,int col) {
		
		if(row==1 || col==1) {
			return 1;
		}
		return count(row-1,col)+count(row,col-1);
	}
	static  int printPaths(int row,int col) {
		
		if(row==0 || col==0) {
			return 0;
		}
		if(row==1 && col==1) {
			System.out.println("(1,1)");
			return 1;
		}
		System.out.print("("+row+","+col+"),");
		return printPaths(row-1,col)+printPaths(row,col-1);
	}

}
