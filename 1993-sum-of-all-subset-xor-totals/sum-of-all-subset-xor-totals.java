class Solution {
    public int subsetXORSum(int[] nums) {
        return dfs(nums, 0, 0);
    }

    private int dfs(int[] nums, int index, int currentXor) {
        if (index == nums.length) {
            return currentXor;
        }
        // Include current element
        int with = dfs(nums, index + 1, currentXor ^ nums[index]);
        // Exclude current element
        int without = dfs(nums, index + 1, currentXor);
        return with + without;
    }
}
