class Solution {
    public String findDifferentBinaryString(String[] nums) {
        // github -- vipulhere
        int n = nums.length;
        Set<String> numSet = new HashSet<>();

        // Store all given binary strings in a set for quick lookup
        for (String num : nums) {
            numSet.add(num);
        }

        // Try all binary numbers of length n
        for (int i = 0; i < (1 << n); i++) { // Generate all numbers from 0 to 2^n - 1
            String candidate = Integer.toBinaryString(i);

            // Pad with leading zeros to match length n
            while (candidate.length() < n) {
                candidate = "0" + candidate;
            }

            // If not in the set, return it
            if (!numSet.contains(candidate)) {
                return candidate;
            }
        }

        return ""; // This case should never happen based on constraints
    }
}