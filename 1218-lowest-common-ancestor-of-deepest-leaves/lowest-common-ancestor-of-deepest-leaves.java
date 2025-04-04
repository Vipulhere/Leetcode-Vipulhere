/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return dfs(root).node;
    }

    // Helper class to return both depth and node
    private class Result {
        TreeNode node;
        int depth;
        Result(TreeNode n, int d) {
            node = n;
            depth = d;
        }
    }

    private Result dfs(TreeNode root) {
        if (root == null) return new Result(null, 0);

        Result left = dfs(root.left);
        Result right = dfs(root.right);

        if (left.depth > right.depth) return new Result(left.node, left.depth + 1);
        if (right.depth > left.depth) return new Result(right.node, right.depth + 1);

        // If equal depth, this node is LCA of deepest
        return new Result(root, left.depth + 1);
    }
}
