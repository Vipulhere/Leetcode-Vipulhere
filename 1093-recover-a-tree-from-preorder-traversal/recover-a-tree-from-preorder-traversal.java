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
    public TreeNode recoverFromPreorder(String traversal) {
        return buildTree(traversal, 0);
    }
    //github - vipulhere
    private int index = 0;

    private TreeNode buildTree(String s, int depth) {
        int dashes = 0;
        while (index + dashes < s.length() && s.charAt(index + dashes) == '-') {
            dashes++; // Count leading dashes
        }

        if (dashes != depth)
            return null; // Depth mismatch, return to previous level

        index += dashes; // Move index to the numeric value
        int value = 0;
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            value = value * 10 + (s.charAt(index) - '0'); // Construct node value
            index++;
        }

        TreeNode node = new TreeNode(value); // Create new node

        node.left = buildTree(s, depth + 1); // Recursively construct left child
        node.right = buildTree(s, depth + 1); // Recursively construct right child

        return node;
    }

}