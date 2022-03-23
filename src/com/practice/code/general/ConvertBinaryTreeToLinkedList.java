package com.practice.code.general;
import java.awt.geom.FlatteningPathIterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.practice.datatypes.TreeNode;


public class ConvertBinaryTreeToLinkedList {
    public static void main(String args[]) {
    }
	public void flatten(TreeNode root) {
    	List<TreeNode> out=new LinkedList<TreeNode>();
    	Queue<TreeNode> temp=new LinkedList<TreeNode>();
    	traverseTree(root,temp,out);
    }
	private void traverseTree(TreeNode root, Queue<TreeNode> temp, List<TreeNode> out) {
		out.add(root);
		if(null!=root.left) {
			traverseTree(root.left, temp, out);
		}
		if(null!=root.right) {
			traverseTree(root.right, temp, out);
		}
	}
	
}
