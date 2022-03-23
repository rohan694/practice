package com.practice.datatypes;

public class ListNode {
	public int val;
	public ListNode next;
	public ListNode() {}
	public ListNode(int val) { this.val = val; }
	public ListNode(String values) {
        ListNode current = this;
        
		String[] s=values.split(",");
		current.val=Integer.parseInt(s[0]);
		current=current.next;
        ListNode prev = this;

		for(int i=1;i<s.length;i++) {
			current=new ListNode(Integer.parseInt(s[i]));
			prev.next=current;
			prev=current;
			current=current.next;
		}
	}
	public String toString() {
        String result = "";
        ListNode current = this;
        while(current != null){
            result += current.val;
            if(current.next != null){
                 result += ", ";
            }
            current = current.next;
        }
        return "List: " + result;
	}
	public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
