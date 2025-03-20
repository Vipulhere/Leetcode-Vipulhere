//Github -- vipulhere
class UnionFind {
    private int[] parent, size;

    // Constructor to initialize Union-Find (DSU)
    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Initially, each node is its own parent
            size[i] = 1;   // Each component starts with size 1
        }
    }

    // Find function with Path Compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path Compression: Flatten the tree
        }
        return parent[x];
    }

    // Union function with Union by Size
    public boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false; // Already in the same component
        
        // Attach the smaller tree under the larger tree
        if (size[pa] > size[pb]) {
            parent[pb] = pa;
            size[pa] += size[pb];
        } else {
            parent[pa] = pb;
            size[pb] += size[pa];
        }
        return true;
    }
}

class Solution {
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] g = new int[n]; // Stores the minimum AND-cost for each component
        Arrays.fill(g, -1); // Initialize with -1 (meaning no value assigned)

        UnionFind uf = new UnionFind(n); // Initialize Union-Find for n nodes

        // Step 1: Union nodes to form connected components
        for (int[] edge : edges) {
            uf.union(edge[0], edge[1]); // Merge the nodes into one component
        }

        // Step 2: Compute the AND-cost for each connected component
        for (int[] edge : edges) {
            int root = uf.find(edge[0]); // Find the representative (root) of the component
            g[root] = (g[root] == -1) ? edge[2] : (g[root] & edge[2]); // Compute AND of all edges in the component
        }

        // Step 3: Answer queries efficiently
        int[] result = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int u = query[i][0], v = query[i][1];

            if (u == v) {
                result[i] = 0; // Cost is 0 if start and end nodes are the same
            } else {
                int a = uf.find(u), b = uf.find(v); // Find the components of u and v
                result[i] = (a == b) ? g[a] : -1; // If in the same component, return the AND-cost; otherwise, return -1
            }
        }

        return result;
    }
}
