class Solution {
    public int numRabbits(int[] answers) {
        //github -- vipulhere
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;

        for (int answer : answers) {
            map.put(answer, map.getOrDefault(answer, 0) + 1);
        }

        for (int key : map.keySet()) {
            int groupSize = key + 1;
            int count = map.get(key);
            int groups = (count + key) / groupSize;
            total += groups * groupSize;
        }

        return total;
    }
}