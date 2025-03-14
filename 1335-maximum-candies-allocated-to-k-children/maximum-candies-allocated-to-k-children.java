class Solution {
    public int maximumCandies(int[] candies, long k) {
        //Github -- vipulhere
        // Edge case: If the total number of candies is less than k, return 0
        long totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }
        if (totalCandies < k) return 0;
        
        // Binary search to find the maximum number of candies per child
        int left = 1, right = (int)1e7;
        int result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canDistribute(candies, k, mid)) {
                result = mid; // Update result to the current valid mid
                left = mid + 1; // Try to find a larger valid number
            } else {
                right = mid - 1; // Reduce search space
            }
        }
        
        return result;
    }
    
    // Helper function to check if it's possible to give 'size' candies to each child
    private boolean canDistribute(int[] candies, long k, int size) {
        long count = 0; // Total number of children that can be served
        
        for (int candy : candies) {
            count += candy / size; // Number of children that can be served from this pile
        }
        
        return count >= k; // Return true if we can serve at least k children
    }
}