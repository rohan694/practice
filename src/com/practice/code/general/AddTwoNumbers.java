package com.practice.code.general;

import com.practice.datatypes.ListNode;

public class AddTwoNumbers {

	public static void main(String args[]) {
		ListNode inp1=new ListNode(2);
		ListNode inp2=new ListNode(3,new ListNode(4));
		ListNode result=addTwoNumbers(inp1,inp2);

		System.out.print(result);
	}
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result=null;
        ListNode temp=null;
        int carry=0;
        while(l1!=null && l2!=null){
            ListNode currNode=null;
            int sum=l1.val+l2.val+carry;
            if(sum<10){
                currNode=new ListNode(sum);
            } else{
                currNode=new ListNode(sum%10);
                carry=sum/10;
            }
            System.out.println(currNode);
            System.out.println(carry);
            if(result==null){
                result=currNode;
                temp=result;
            }else{
                temp.next=currNode;
                temp=temp.next;
            }
            System.out.println(result);

            l1=l1.next;
            l2=l2.next;
        }
        while(l1!=null){
            temp.next=l1;
            l1=l1.next;
            temp=temp.next;
        }
        while(l2!=null){
            temp.next=l2;
            l2=l2.next;
            temp=temp.next;
        }

        return result;
    }
}
