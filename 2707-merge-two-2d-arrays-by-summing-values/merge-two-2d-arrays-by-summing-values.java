class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        //Github -- vipulhere
        // Add all elements from nums1 to resultMap
        for (int[] num : nums1) {
            resultMap.put(num[0], num[1]);
        }

        // Add all elements from nums2 to resultMap, sum values if id already exists
        for (int[] num : nums2) {
            resultMap.put(num[0], resultMap.getOrDefault(num[0], 0) + num[1]);
        }

        // Create result array with sorted keys (ids)
        List<int[]> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : resultMap.entrySet()) {
            resultList.add(new int[] { entry.getKey(), entry.getValue() });
        }

        // Sort the result list by ids
        resultList.sort((a, b) -> Integer.compare(a[0], b[0]));

        // Convert the list to a 2D array
        return resultList.toArray(new int[resultList.size()][]);
    }
}