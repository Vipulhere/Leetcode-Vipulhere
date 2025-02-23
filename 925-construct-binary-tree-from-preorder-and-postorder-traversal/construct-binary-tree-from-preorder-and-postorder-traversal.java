/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return buildTree(preorder, postorder, new int[] { 0 }, new int[] { 0 });
    }

    private TreeNode buildTree(int[] preorder, int[] postorder, int[] preIndex, int[] postIndex) {
        TreeNode root = new TreeNode(preorder[preIndex[0]++]);

        if (root.val != postorder[postIndex[0]]) {
            root.left = buildTree(preorder, postorder, preIndex, postIndex);
        }
        if (root.val != postorder[postIndex[0]]) {
            root.right = buildTree(preorder, postorder, preIndex, postIndex);
        }

        postIndex[0]++; // Move postorder index forward
        return root;
    }
    //vipulhere -- github
}