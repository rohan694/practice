package com.practice.code.general;

import java.util.HashMap;
import java.util.HashSet;

public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		String s="abcabcbb";
        int count=0;
        int tempCount=0;
        char[] a=s.toCharArray();
        HashMap<Character,Integer> temp=new HashMap();

        for(int i=0;i<s.length();i++){
            if(!temp.containsKey(a[i])){
                temp.put(a[i],i);
                tempCount++;
            } else{
                if(tempCount>count){
                    count=tempCount;
                }
                tempCount=i-temp.get(a[i]);
                final int tempInt=temp.get(a[i]);
                temp.values().removeIf(value -> value <=tempInt);
                temp.put(a[i],i);
            }
        }
        if(tempCount>count){
            count=tempCount;
        }
		System.out.println(count);
	}

}
