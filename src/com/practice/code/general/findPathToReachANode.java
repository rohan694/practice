package com.practice.code.general;

import com.practice.datatypes.TreeNode;

public class findPathToReachANode {

	public static void main(String[] args) {
		TreeNode root=new TreeNode(1);
		TreeNode t1=new TreeNode(2);
		TreeNode t2=new TreeNode(3);
		root.left=t1;
		root.right=t2;
		
		t1.left=new TreeNode(4);
		t1.right=new TreeNode(5);
		t2.left=new TreeNode(6);
		t2.right=new TreeNode(7);
		//TreeNode.printTree(root);
		System.out.println(findPath(root,7,""));
	}

	private static String findPath(TreeNode root, int dest,String path) {
		if(root==null) {
			return null;
		}
		System.out.println(root.data);
		path=path+"-->"+root.data;
		if(root.data==dest) {
			return path;
		}
		if(root.left!=null) {
			String pathinLeft=findPath(root.left,dest,path);
			if(pathinLeft!=null) {
				return pathinLeft;
			}
		}
		
		if(root.right!=null) {
			String pathinright=findPath(root.right,dest,path);
			if(pathinright!=null) {
				return pathinright;
			}
		}
		
		return null;
		
	}

}
