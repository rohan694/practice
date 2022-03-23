package com.practice.code.general;

import com.practice.datatypes.ListNode;

public class MergeKSoortedList {
	public static void main(String args[]) {
		ListNode first=new ListNode("1,4,5");
		ListNode second=new ListNode("1,3,4");
		ListNode third=new ListNode("2,6");
		ListNode[] list= {first,second,third};
		System.out.print(mergeKLists(list));
	}
	
	 public static  ListNode mergeKLists(ListNode[] lists) {
        ListNode out=mergeKLists(lists,new ListNode(),null);
        return out;
    }
     public static ListNode mergeKLists(ListNode[] lists,ListNode curr,ListNode prev) {
        
        int ind=-1;
        for(int i=0;i<lists.length;i++){
        	if(lists[i]!=null) {
	            if(ind==-1 || lists[i].val<lists[ind].val){
	                ind=i;
	            }
        	}
        }
        if(ind!=-1){
            curr.val=lists[ind].val;
            if(prev!=null) prev.next=curr;
            lists[ind]=lists[ind].next;
            mergeKLists(lists,new ListNode(),curr);
        }
        return curr;
     }
}