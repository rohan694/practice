package com.practice.code.general;

public class RotatedArraySearch {
	public static void main(String args[]) {
		int arr[]= new int[]{176,188,199,200,210,222,1,10,20,47,59,63,75,88,99,107,120,133,155,162};
		//int arr[]= new int[]{3,4,5,1,2};
		int rotationPoint=0;

		while(rotationPoint< arr.length-1 && arr[rotationPoint]<arr[rotationPoint+1]) {
			rotationPoint++;
		}
		System.out.println(rotationPoint++);
		System.out.println(arr[rotationPoint]);
		System.out.println(binarySearch(arr,rotationPoint,arr.length-1,99));
	}
	public static int binarySearch(int[] arr,int start,int end,int num) {
		int mid=(start+end)/2;
		if(num==arr[mid]) {
			return mid;
		}
		if(start-end==0) {
			return -1;
		}
		if(num>arr[mid]) {
			return binarySearch(arr, mid+1, end, num);
		}else {
			return binarySearch(arr, start, mid-1, num);
		}
	}
	
}
