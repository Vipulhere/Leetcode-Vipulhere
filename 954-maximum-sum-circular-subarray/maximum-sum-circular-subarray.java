class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        //github -- vipulhere
        int maxKadane = kadane(nums); // Max subarray sum in normal case

        int totalSum = 0;
        for (int i = 0; i < nums.length; i++) {
            totalSum += nums[i];
            nums[i] = -nums[i]; // Invert array for finding min subarray sum
        }

        int maxWrap = totalSum + kadane(nums); // Max circular subarray sum
        if (maxWrap == 0)
            return maxKadane; // All numbers are negative

        return Math.max(maxKadane, maxWrap);
    }

    private int kadane(int[] nums) {
        int maxSum = nums[0], curSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            curSum = Math.max(nums[i], curSum + nums[i]);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}