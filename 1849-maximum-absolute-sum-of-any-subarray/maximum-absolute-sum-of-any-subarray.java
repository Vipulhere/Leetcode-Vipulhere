class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxSum = 0, minSum = 0, currentMaxSum = 0, currentMinSum = 0;
        //vipulhere
        // Traverse through the array to find the maximum and minimum subarray sums
        for (int num : nums) {
            // Update the maximum subarray sum at the current point
            currentMaxSum = Math.max(currentMaxSum + num, num);
            maxSum = Math.max(maxSum, currentMaxSum);

            // Update the minimum subarray sum at the current point
            currentMinSum = Math.min(currentMinSum + num, num);
            minSum = Math.min(minSum, currentMinSum);
        }

        // The result is the maximum of the absolute values of the maximum and minimum subarray sums
        return Math.max(Math.abs(maxSum), Math.abs(minSum));
    }
}
