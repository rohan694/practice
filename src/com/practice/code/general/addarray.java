package com.practice.code.general;

import java.util.stream.IntStream;

public class addarray {
	public static void main(String[] args) {
		int[] out=add(new int[] {1,2,3},456);
		IntStream.of(out).forEach(x-> {
			System.out.println(x);
		});
	}
	
	select dept_name,(select count(*) from employee e where e.dept_id=d.dept_id group by depat_id) from department d
	
	static int[] add(int[] num1,int num2){
		
		int temp=0;
		int index=num1.length-1;
		int storeindex=0;
		int out[] =new int[num1.length+1];
		while(num2>0 ){
			int last=num2%10;
			int sum=num1[index]+last;
			int tempsum=sum;
			num2=num2/10;
			if((tempsum>9)){
				if(temp!=0){
					out[storeindex]=sum%10+temp;
					temp=0;
				}
				
				temp=sum/10;
			} else{
				out[storeindex]=sum;
			}
			storeindex--;
		}
		
		if(temp>0) {
			out[0]=temp;
			
		}else {
			out[0]=0;
		}
		
		return out;
	}
}
