class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // Step 1: Sort the input array to make divisibility checks easier
        Arrays.sort(nums);
        int n = nums.length;

        // dp[i] will store the length of the largest divisible subset ending with nums[i]
        int[] dp = new int[n];

        // prev[i] stores the index of the previous element in the subset ending at nums[i]
        int[] prev = new int[n];

        // Initialize arrays
        Arrays.fill(dp, 1);       // Every number can at least form a subset of length 1 (itself)
        Arrays.fill(prev, -1);    // -1 indicates no previous element

        int maxIndex = 0; // Index of the last element of the largest divisible subset found

        // Step 2: Build up the dp[] and prev[] arrays
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // Check if nums[i] is divisible by nums[j]
                if (nums[i] % nums[j] == 0) {
                    // Check if this subset would be longer
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }

            // Update maxIndex if we found a longer subset
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        // Step 3: Reconstruct the subset using prev[] array
        List<Integer> result = new ArrayList<>();
        int k = maxIndex;
        while (k >= 0) {
            result.add(nums[k]);
            k = prev[k];
        }

        return result;
    }
}