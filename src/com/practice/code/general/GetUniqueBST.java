package com.practice.code.general;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.practice.datatypes.*;
public abstract class GetUniqueBST {
    public List<TreeNode> generateTrees(int n) {
        List<Integer> range = IntStream.rangeClosed(1, n)
            .boxed().collect(Collectors.toList());
        List<TreeNode> trees= new ArrayList<TreeNode>();
        
        return generateBST(null,trees,range);
    }

	private List<TreeNode> generateBST(TreeNode root, List<TreeNode> trees, List<Integer> nums) {
		List<TreeNode> result=null;
		for(int i=0;i<nums.size();i++) {
			List<Integer> newNums=new ArrayList(nums);
			int currentElem=nums.get(i);
			newNums.remove(i);
			if(root==null) {
				result=generateBST(new TreeNode(currentElem),trees,newNums);
			} else {
				if(nums.get(i)<root.data) {
					root.left=new TreeNode(currentElem);
					result=generateBST(root.left,trees,newNums);
				} else {
					root.right=new TreeNode(currentElem);
					result=generateBST(root.right,trees,newNums);
				}
			}
		}
		if(result==null) {
			
		}
		return result;
	}

}
