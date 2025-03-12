class Solution {
    public int maximumCount(int[] nums) {
        int n = nums.length;
        
        // Find first positive index using binary search
        int firstPositiveIdx = findFirstPositive(nums);
        
        // Find last negative index using binary search
        int lastNegativeIdx = findLastNegative(nums);
        
        int posCount = (firstPositiveIdx == -1) ? 0 : n - firstPositiveIdx;
        int negCount = (lastNegativeIdx == -1) ? 0 : lastNegativeIdx + 1;
        
        return Math.max(posCount, negCount);
    }
    
    // Binary search to find first positive number index
    private int findFirstPositive(int[] nums) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] > 0) {
                result = mid; // Store the index
                right = mid - 1; // Move left to find first positive
            } else {
                left = mid + 1;
            }
        }
        
        return result;
    }
    
    // Binary search to find last negative number index
    private int findLastNegative(int[] nums) {
        int left = 0, right = nums.length - 1;
        int result = -1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] < 0) {
                result = mid; // Store the index
                left = mid + 1; // Move right to find last negative
            } else {
                right = mid - 1;
            }
        }
        
        return result;
    }
}
