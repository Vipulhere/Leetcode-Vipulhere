class Solution {
    public boolean divideArray(int[] nums) {
        // Create a HashMap to count occurrences of each number
        HashMap<Integer, Integer> map = new HashMap<>();
        
        // Count the occurrences of each number
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Check if all counts are even
        for (int count : map.values()) {
            if (count % 2 != 0) {
                return false;  // If any count is odd, return false
            }
        }
        
        return true;  // All counts are even, return true
    }
}