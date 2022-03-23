package com.practice.code.karat;

import java.util.Arrays;
import java.util.stream.Collectors;

public class WordScramble {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] words = new String[] { "cat", "baby", "dog", "bird", "car", "ax"};
		String string1 = "tcabnihjs";
		String string2 = "tbcanihjs";
		String string3 = "baykkjl";
		String string4 = "bbabylkkj";
		String string5 = "ccc";
		String string6 = "breadmaking";

		System.out.println(solution(words, string6));
	}
	public static String solution(String[] a,String str) {
		String res ="";
		char[] inp=str.toCharArray();
		//String[] cloneDict=Arrays.copyOf(a,a.length);
		for(int i=0;i<a.length;i++) {
			String text=str;
			char[] temp=a[i].toCharArray();
			boolean found=true;
			for(char c: temp) {
				int index=text.indexOf(Character.toString(c));
				if(index!=-1) {
					text=text.replaceFirst(Character.toString(c), "");
				}else {
					found=false;
					break;
				}
			}
			if(found) {
				return a[i];
			}
		}
		return "";
	}
	
}
