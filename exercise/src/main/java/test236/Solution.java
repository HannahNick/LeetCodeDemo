package test236;

import base.TreeNode;

/**
 * 236.二叉树的最近公共祖先
 * https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @author zwj
 * @date 2021/4/25
 */
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return null;
        }

        if (root.val == p.val || root.val ==q.val){
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left!=null && right!=null){
            return root;
        }

        if (left==null){
            return right;
        }
        if (right==null){
            return left;
        }
        return null;
    }
}
