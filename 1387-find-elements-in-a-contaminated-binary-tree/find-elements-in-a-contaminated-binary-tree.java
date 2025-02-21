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
class FindElements {

    //github - vipulhere
    private Set<Integer> values = new HashSet<>();

    public FindElements(TreeNode root) {
        if (root != null) {
            root.val = 0; // Recover root value
            recoverTree(root);
        }
    }

    private void recoverTree(TreeNode node) {
        if (node == null) return;

        values.add(node.val); // Store the recovered value

        if (node.left != null) {
            node.left.val = 2 * node.val + 1; // Recover left child
            recoverTree(node.left);
        }

        if (node.right != null) {
            node.right.val = 2 * node.val + 2; // Recover right child
            recoverTree(node.right);
        }
    }

    public boolean find(int target) {
        return values.contains(target); // Fast lookup in HashSet
    }
}

/**
 * Your FindElements object will be instantiated and called as such:
 * FindElements obj = new FindElements(root);
 * boolean param_1 = obj.find(target);
 */