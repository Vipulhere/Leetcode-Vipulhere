class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> less = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        //github -- vipulhere
        // Separate the elements into three categories
        for (int num : nums) {
            if (num < pivot) {
                less.add(num);
            } else if (num == pivot) {
                equal.add(num);
            } else {
                greater.add(num);
            }
        }

        // Merge the three lists into one
        less.addAll(equal);
        less.addAll(greater);

        // Convert the result list back into an array
        int[] result = new int[nums.length];
        for (int i = 0; i < less.size(); i++) {
            result[i] = less.get(i);
        }

        return result;
    }
}