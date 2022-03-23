package com.practice.code.general;

public class Thief {

	public static void main(String args[]) {
		String lastDirection="";
		int x=2,y=1;
		int steps=0;
		while(!(x==0 && y==0)) {
			steps++;
			
			if(x>0 && y>0) {
				if(lastDirection.equals("x")) {
					y--;
					lastDirection="y";
				} else if(lastDirection.equals("y")) {
					x--;
					lastDirection="x";
				} else {
					if(x>y) {
						x--;
						lastDirection="x";
					}else {
						y--;
						lastDirection="y";
					}
				}
			} else if(x==0){
				
			}
		}
			
		System.out.println(steps);
		//System.out.println(findMinWay(8,8,"",0));
	}

	private static int findMinWay(int x, int y,String lastDirection,int steps) {
		// TODO Auto-generated method stub
		int sign=1;
		steps++;
		if(x==1 && y==1) {
			return 2;
		}
		if((x==1 && y==0) || (y==1 && x==0)) return 3;
		if(x<=0 && y<=0) {
			return 0;
		}
		if(x==0 && lastDirection.equals("x")) {
			return steps+findMinWay(x, y-sign*1, "y",steps);
		}

		if(lastDirection.equals("x")) {
			return steps+findMinWay(x, y-sign*1, "y",steps);
		}else if(lastDirection.equals("y")) {
			
			return steps+findMinWay(x-sign*1, y, "y",steps);
		} else {
			return Math.min(steps+findMinWay(x-sign*1, y, "x",steps),steps+findMinWay(x, y-sign*1, "y",steps));
		}

		
	}
}
