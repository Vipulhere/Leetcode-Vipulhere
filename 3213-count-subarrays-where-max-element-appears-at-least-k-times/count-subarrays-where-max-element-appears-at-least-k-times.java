class Solution {
    public long countSubarrays(int[] nums, int k) {
        //Github - vipulhere
        int n = nums.length;
        int max = 0;
        
        // Step 1: Find the maximum elements
        for (int num : nums) {
            max = Math.max(max, num);
        }

        long res = 0;
        int count = 0;
        int left = 0;

        // Step 2: Sliding window
        for (int right = 0; right < n; right++) {
            if (nums[right] == max) {
                count++;
            }

            while (count >= k) {
                // All subarrays starting from left to end of array and ending at right are valid
                res += (n - right);
                if (nums[left] == max) {
                    count--;
                }
                left++;
            }
        }
        return res;
    }
}
