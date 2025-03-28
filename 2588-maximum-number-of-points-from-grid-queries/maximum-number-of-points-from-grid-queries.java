class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length, n = grid[0].length;
        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}}; // directions for traversal
        int[] res = new int[queries.length];

        // Create a list of query indices to sort and map result later
        int[][] indexedQueries = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }
        Arrays.sort(indexedQueries, (a, b) -> Integer.compare(a[0], b[0]));

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[]{grid[0][0], 0, 0}); // [value, x, y]
        visited[0][0] = true;

        int count = 0; // number of cells visited so far
        int[][] temp = new int[m][n];

        while (!pq.isEmpty() && pq.peek()[0] < indexedQueries[0][0]) {
            int[] cur = pq.poll();
            int val = cur[0], x = cur[1], y = cur[2];
            temp[x][y] = 1;
            count++;

            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    pq.offer(new int[]{grid[nx][ny], nx, ny});
                }
            }
        }

        int prevCount = count;
        int qIdx = 0;

        for (int i = 0; i < queries.length; i++) {
            int q = indexedQueries[i][0];
            while (!pq.isEmpty() && pq.peek()[0] < q) {
                int[] cur = pq.poll();
                int val = cur[0], x = cur[1], y = cur[2];
                if (temp[x][y] == 0) {
                    temp[x][y] = 1;
                    count++;

                    for (int[] d : dirs) {
                        int nx = x + d[0], ny = y + d[1];
                        if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            pq.offer(new int[]{grid[nx][ny], nx, ny});
                        }
                    }
                }
            }

            res[indexedQueries[i][1]] = count;
        }

        return res;
    }
}
