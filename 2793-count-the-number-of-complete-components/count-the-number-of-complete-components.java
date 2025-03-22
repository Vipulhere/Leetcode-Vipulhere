class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        //Github -- vipulhere
        // Create adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Fill the graph with the given edges
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        // Traverse each node
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> nodes = new ArrayList<>();
                int[] edgeCount = new int[1];

                // DFS to collect all nodes and count edges in the component
                dfs(i, graph, visited, nodes, edgeCount);

                int nodeCount = nodes.size();
                int actualEdges = edgeCount[0] / 2;

                // Check if the component is complete
                if (actualEdges == nodeCount * (nodeCount - 1) / 2) {
                    count++;
                }
            }
        }

        return count;
    }

    // DFS helper function
    private void dfs(int node, List<List<Integer>> graph, boolean[] visited, List<Integer> nodes, int[] edgeCount) {
        visited[node] = true;
        nodes.add(node);
        for (int neighbor : graph.get(node)) {
            edgeCount[0]++;
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited, nodes, edgeCount);
            }
        }
    }
}
