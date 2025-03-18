class Solution {
    public int longestNiceSubarray(int[] nums) {
        int left = 0, bitmask = 0, maxLen = 0;
        //Github -- vipulhere
        for (int right = 0; right < nums.length; right++) {
            // If adding nums[right] causes a conflict, shrink window
            while ((bitmask & nums[right]) != 0) {
                bitmask ^= nums[left]; // Remove leftmost element from bitmask
                left++;
            }
            
            // Add nums[right] to the current window
            bitmask |= nums[right];
            
            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}
