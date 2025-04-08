class Solution {
    public int minimumOperations(int[] nums) {
        int operations = 0;
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }

        while (true) {
            Set<Integer> set = new HashSet<>(list);
            if (set.size() == list.size()) {
                break; // All elements are distinct
            }

            // Remove first 3 elements (or all if less than 3)
            int toRemove = Math.min(3, list.size());
            for (int i = 0; i < toRemove; i++) {
                list.remove(0);
            }

            operations++;
        }
        return operations;
    }
}
