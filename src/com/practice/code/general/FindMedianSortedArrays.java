package com.practice.code.general;

public class FindMedianSortedArrays {
	public static void main(String[] args) {
		int[] nums1= new int[]{1,2};
		int[] nums2= new int[]{3,4};
		
        int index=(nums1.length+nums2.length)/2;
        int divider=1;
        if((nums1.length+nums2.length)%2==0){
            divider=2;
        }
        double out=0;
        int first=0,second=0,actual=0;;
        while(first<nums1.length && second<nums2.length){
            int elem=0;
            if(nums1[first]<nums2[second]){
                
                elem=nums1[first++];
            }else{
                elem = nums2[second++];
            }
            if(divider==2) {
                if(index-1==actual){
                	out+=elem;
                }
            }
                if(index==actual){
                    out+=elem;
                }
            actual++;
        }
        while(first<nums1.length){
            int elem=nums1[first++];
            if(divider==2) {
                if(index-1==actual){
                	out+=elem;
                }
            }
                if(index==actual){
                    out+=elem;
                }
            actual++;
        }
        while(second<nums2.length){
            int elem=nums2[second++];
            if(divider==2) {
                if(index-1==actual){
                	out+=elem;
                }
            }
                if(index==actual){
                    out+=elem;
                }
            actual++;
        }
        
        System.out.println((double)out/divider);

	}
}
