class Solution {
    public int[] twoSum(int[] nums, int target) {
    //github - vipulhere
    // Create a HashMap to store numbers and their corresponding indices
    Map<Integer, Integer> map = new HashMap<>();
    
    // Iterate through the array
    for (int i = 0; i < nums.length; i++) {
        // Calculate the complement (the number needed to reach the target)
        int complement = target - nums[i];
        
        // Check if the complement exists in the map
        if (map.containsKey(complement)) {
            // If found, return the indices of the two numbers that sum to target
            return new int[] { map.get(complement), i };
        }
        
        // Store the current number and its index in the HashMap
        map.put(nums[i], i);
    }
    
    // This return statement will never be reached as per the problem constraints
    return new int[] {}; 
}

}