package com.practice.code.general;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class FindWordCount {

	public static void main(String[] args) {

		ArrayList<String> in=new ArrayList<String>();
		in.add("ajay");
		in.add("ajay");

		in.add("rohan");
		in.add("rohan");
		System.out.println(findWordCount(in));
	}
	public static Map findWordCount(ArrayList<String> s){
		Map<String,Integer> out=new HashTable<String,Integer>();
		HashMap<String,Integer> finalout=new HashMap<String,Integer>();
		s.stream().forEach(x->{
			if(out.containsKey(x)) {
				out.put(x,out.get(x)+1);
			}else {
				out.put(x,1);
			}
		});
		for(int i=s.size()-1;i>=0;i--) {
			if(finalout.containsKey(s.get(i))) {
			}else {
				finalout.put(s.get(i),out.get(s.get(i)));
			}
		}

		return finalout;
	}
}
class MyStack{
	String data;
	MyStack[] stack;
	int index;
	MyStack(String s)_{
			this.index=-1;
			this.stack=new MyStack[16];
			this.MyStack[index++]=top;
	}
	push(MyStack s){
		if(index+1<stack.length) {
			this.MyStack[index++]=s;
		}
	}
}

 - create one array which will store the tsack
 - maintain one index for the last element inserted
  - for push :
	  - if index+1< array size{
		  
	  }
 - 		

 1 - 2 - 3 - 4
 
 static TreeNode reverse(TreeNode left,TreeNode right) {
	 TreeNode temp=right.next;
	 right.next=left;
	 
 }