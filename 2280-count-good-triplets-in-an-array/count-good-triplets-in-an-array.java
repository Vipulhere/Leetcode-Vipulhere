class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[nums2[i]] = i;
        }

        int[] idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = pos[nums1[i]];
        }

        FenwickTree left = new FenwickTree(n);
        FenwickTree right = new FenwickTree(n);

        for (int i = 0; i < n; i++) {
            right.update(idx[i], 1);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            right.update(idx[i], -1);
            long l = left.query(idx[i]);
            long r = right.query(n - 1) - right.query(idx[i]);
            res += l * r;
            left.update(idx[i], 1);
        }

        return res;
    }

    class FenwickTree {
        long[] tree;
        int size;

        FenwickTree(int n) {
            size = n;
            tree = new long[n + 1];
        }

        void update(int i, long delta) {
            i++;
            while (i <= size) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        long query(int i) {
            i++;
            long sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
