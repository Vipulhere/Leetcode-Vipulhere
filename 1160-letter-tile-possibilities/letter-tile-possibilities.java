class Solution {
    //vipulhere
    public static int numTilePossibilities(String tiles) {
        HashSet<String> result = new HashSet<>();
        boolean[] visited = new boolean[tiles.length()];
        char[] arr = tiles.toCharArray();
        backtrack(arr, "", result, visited);
        return result.size();
    }

    private static void backtrack(char[] arr, String current, HashSet<String> result, boolean[] visited) {
        if (!current.isEmpty()) {
            result.add(current);
        }
        for (int i = 0; i < arr.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            backtrack(arr, current + arr[i], result, visited);
            visited[i] = false;
        }
    }
}