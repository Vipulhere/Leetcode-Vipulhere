class Solution {
    public int minCapability(int[] nums, int k) {
        int left = Integer.MAX_VALUE, right = Integer.MIN_VALUE;
        
        // Find the range for binary search
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (canRobAtLeastK(nums, k, mid)) {
                right = mid;  // Try to minimize capability
            } else {
                left = mid + 1;  // Increase capability
            }
        }
        
        return left;
    }

    private boolean canRobAtLeastK(int[] nums, int k, int cap) {
        int count = 0;
        int i = 0;
        
        while (i < nums.length) {
            if (nums[i] <= cap) {  // If house can be robbed
                count++;
                if (count >= k) return true;
                i++;  // Skip the next house to avoid adjacency
            }
            i++;
        }
        
        return count >= k;
    }
}
