class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        //Github -- Vipulhere
        // Directions: right, down, left, up
        int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}};

        // Result array to store max points for each query
        int[] res = new int[queries.length];

        // Create a new array that stores both the query value and original index
        // so we can sort the queries and later place the result in the correct index
        int[][] indexedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            indexedQueries[i][0] = queries[i]; // query value
            indexedQueries[i][1] = i;          // original index
        }

        // Sort queries by their value so we can process them efficiently
        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        // Visited array to mark which cells have been added to the queue
        boolean[][] visited = new boolean[m][n];

        // Min-heap priority queue to explore smallest grid values first
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // Start from top-left corner if it is valid
        pq.offer(new int[]{grid[0][0], 0, 0}); // [cell value, x, y]
        visited[0][0] = true;

        // Count of how many valid cells we've visited (i.e., collected points from)
        int count = 0;

        // Used to mark cells we've already counted points from
        int[][] counted = new int[m][n];

        // Loop through all queries in increasing order
        for (int i = 0; i < indexedQueries.length; i++) {
            int queryVal = indexedQueries[i][0];
            int originalIdx = indexedQueries[i][1];

            // Process all cells in the priority queue with value less than queryVal
            while (!pq.isEmpty() && pq.peek()[0] < queryVal) {
                int[] curr = pq.poll();
                int val = curr[0], x = curr[1], y = curr[2];

                // If we haven't already counted this cell
                if (counted[x][y] == 0) {
                    counted[x][y] = 1; // Mark it counted
                    count++; // Increase point count

                    // Check all 4 directions for valid adjacent cells
                    for (int[] dir : dirs) {
                        int nx = x + dir[0];
                        int ny = y + dir[1];

                        // If adjacent cell is in bounds and not visited
                        if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            pq.offer(new int[]{grid[nx][ny], nx, ny});
                        }
                    }
                }
            }

            // Store the number of points collected for this query
            res[originalIdx] = count;
        }

        return res;
    }
}
