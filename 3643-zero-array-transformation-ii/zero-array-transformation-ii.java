class Solution {
    //Github -- vipulhere
    private int n; // Stores the length of the nums array
    private int[] nums; // Stores the input nums array
    private int[][] queries; // Stores the queries array

    public int minZeroArray(int[] nums, int[][] queries) {
        this.nums = nums; // Store nums array in class variable
        this.queries = queries; // Store queries in class variable
        n = nums.length; // Get the size of the nums array
        int m = queries.length; // Get the number of queries
        int l = 0, r = m + 1; // Initialize binary search range

        // Binary search to find the minimum number of queries required
        while (l < r) {
            int mid = (l + r) >> 1; // Find mid-point of search range
            if (check(mid)) { // If it's possible to make nums a zero array within mid queries
                r = mid; // Try reducing the number of queries
            } else {
                l = mid + 1; // Otherwise, increase the query count
            }
        }
        return l > m ? -1 : l; // If l exceeds the number of queries, return -1; otherwise, return l
    }

    // Function to check if it's possible to make nums a zero array within k queries
    private boolean check(int k) {
        int[] d = new int[n + 1]; // Difference array to track range updates efficiently

        // Apply the first k queries to the difference array
        for (int i = 0; i < k; ++i) {
            int l = queries[i][0], r = queries[i][1], val = queries[i][2];
            d[l] += val; // Increment the left index
            d[r + 1] -= val; // Decrement the right index (marking the end of range update)
        }

        // Apply the difference array to check if all elements can be reduced to zero
        for (int i = 0, s = 0; i < n; ++i) {
            s += d[i]; // Compute the prefix sum to apply the decrement
            if (nums[i] > s) { // If the decrement isn't enough to make nums[i] zero
                return false; // Not possible within k queries
            }
        }
        return true; // All elements can be reduced to zero
    }
}
