class Solution {
    public int[] applyOperations(int[] nums) {
        int n = nums.length;

        // Step 1: Perform operations on the array
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] *= 2;   // Multiply nums[i] by 2
                nums[i + 1] = 0; // Set nums[i + 1] to 0
            }
        }

        // Step 2: Shift all zeros to the end of the array
        int[] result = new int[n];
        int index = 0;

        // Move non-zero elements to the front
        for (int num : nums) {
            if (num != 0) {
                result[index++] = num;
            }
        }

        // The remaining positions will naturally be filled with zeros
        return result;
    }
}
