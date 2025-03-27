class Solution {
    public int minimumIndex(List<Integer> nums) {
        int n = nums.size();
        
        // Step 1: Find the dominant element
        int candidate = -1, count = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }

        // Step 2: Count total occurrences of the dominant element
        int total = 0;
        for (int num : nums) {
            if (num == candidate) total++;
        }

        // Step 3: Try all split points
        int leftCount = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums.get(i) == candidate) {
                leftCount++;
            }
            int leftLength = i + 1;
            int rightLength = n - leftLength;
            int rightCount = total - leftCount;

            if (leftCount * 2 > leftLength && rightCount * 2 > rightLength) {
                return i;
            }
        }

        return -1;
    }
}
