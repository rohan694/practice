package com.practice.datatypes;


public class TreeNode{
	public int data;
	public TreeNode left;
	public TreeNode right;
	public TreeNode(int data) {
		this.data=data;
		this.left=null;
		this.right=null;
	}
    static void preorder(TreeNode root)
    {
        if (root != null)
        {
            System.out.print(root.data+" ") ;
            preorder(root.left);
            preorder(root.right);
        }
    }
}