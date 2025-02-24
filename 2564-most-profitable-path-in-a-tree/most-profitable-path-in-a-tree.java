class Solution {
    int[] bobPath;
    List<List<Integer>> tree;
    boolean[] visited;
    int ans = Integer.MIN_VALUE;
    int[] amount;

    //Finding a path that takes bob to node 0 and storing in bobPath array;
    // -1 represents bob don't visit and values represents the level at which 
    // bob visits that place;
    private boolean findBob(int n, int path){
        if(n == 0){
            bobPath[n] = path;
            return true;
        }

        if(visited[n]) return false;
        visited[n] = true;
        bobPath[n] = path;
        boolean isValid = false;
        for(int i : tree.get(n)){
            if(findBob(i, path+1)){
                isValid = true;
                break;
            }
        }
        if(isValid) return true;
        bobPath[n] = -1;
        return false;   
    }

    //Solving the alice profitable path based/
    private void solve(int n, int alicePath, int aliceAns){

        //If path already visitd by alice.
        if(visited[n]) return;


        //If alice and bob visits path at the same time.
        if(bobPath[n] == alicePath)
            aliceAns += (amount[n]/2);

        //condition at which alice choose to open gate if bob didn't already.
        else if(bobPath[n] == -1 || alicePath < bobPath[n])
            aliceAns += amount[n];

        //base case whenever alice reaches to the leaf node.
        if(n != 0 && tree.get(n).size() == 1){
            ans = Math.max(ans, aliceAns);
            return;
        }
        visited[n] = true;

        //Recursively alice trying to go to other leaf path.
        for(int i : tree.get(n))
            solve(i, alicePath+1, aliceAns);

    }
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        this.bobPath = new int[n];
        this.amount = amount;

        //Creating a tree.
        tree = new ArrayList<>();
        for(int i = 0; i < n; i++)
            tree.add(new ArrayList<>());

        //Adding nodes to tree
        for(int i = 0; i < edges.length; i++){
            tree.get(edges[i][0]).add(edges[i][1]);
            tree.get(edges[i][1]).add(edges[i][0]);
        }

        //Finding Bobs Path and storing on bobPath array
        Arrays.fill(bobPath, -1);
        visited = new boolean[n+1];
        findBob(bob, 0);

        //Solving fot alice.
        visited = new boolean[n+1];
        solve(0, 0, 0);

        return ans;
        //vipulhere-github
    }
}