class Solution {
    public long maximumTripletValue(int[] nums) {
        //Github -- vipulhere
        int n = nums.length;
        long maxValue = 0;

        // Precompute prefix max for left side (i < j)
        int[] prefixMax = new int[n];
        prefixMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
        }

        // Precompute suffix max for right side (k > j)
        int[] suffixMax = new int[n];
        suffixMax[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
        }

        for (int j = 1; j < n - 1; j++) {
            long left = prefixMax[j - 1];
            long right = suffixMax[j + 1];

            long value = (left - nums[j]) * right;
            if (value > 0) {
                maxValue = Math.max(maxValue, value);
            }
        }

        return maxValue;
    }
}
