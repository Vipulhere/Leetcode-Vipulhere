import java.util.*;

class Solution {
    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        if (n < 5) return 0; // Not enough length to have all 5 vowels

        // 1) Build prefix sums for consonants
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (isVowel(word.charAt(i)) ? 0 : 1);
        }

        // 2) Build prefixMap: prefix value -> sorted list of indices
        Map<Integer, List<Integer>> prefixMap = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            prefixMap.computeIfAbsent(prefix[i], x -> new ArrayList<>()).add(i);
        }
        // Sort each list of positions
        for (List<Integer> lst : prefixMap.values()) {
            Collections.sort(lst);
        }

        // 3) Track last occurrence of each vowel. If any is -1, not all vowels are present.
        int[] lastOcc = new int[5]; 
        Arrays.fill(lastOcc, -1);
        // Map vowel char -> index in lastOcc array
        Map<Character, Integer> vowelIndex = Map.of(
            'a', 0, 'e', 1, 'i', 2, 'o', 3, 'u', 4
        );

        long ans = 0;

        // 4) Iterate over R
        for (int R = 0; R < n; R++) {
            char c = word.charAt(R);
            // Update last occurrence if it's a vowel
            if (vowelIndex.containsKey(c)) {
                lastOcc[vowelIndex.get(c)] = R;
            }

            // Check if we have all vowels
            int minLast = Integer.MAX_VALUE;
            for (int idx : lastOcc) {
                if (idx == -1) {
                    minLast = -1; // means missing a vowel
                    break;
                }
                minLast = Math.min(minLast, idx);
            }

            if (minLast == -1) {
                // We don't have all vowels yet
                continue;
            }

            // 5) We have all vowels in [minLast..R]. 
            // We want exactly k consonants => prefix[R+1] - prefix[L] = k => prefix[L] = prefix[R+1] - k
            int needed = prefix[R + 1] - k;
            
            // If needed not in map, skip
            if (!prefixMap.containsKey(needed)) continue;

            // Among prefixMap[needed], count how many positions L are <= minLast
            // i.e. L in [0.. minLast]
            // We'll do a binary search for minLast in that list.
            List<Integer> positions = prefixMap.get(needed);
            // We want the count of L in positions such that L <= minLast
            // => index = upper_bound(positions, minLast)
            // We'll do Collections.binarySearch with (minLast + 1) - 1 approach
            int idx = Collections.binarySearch(positions, minLast + 1);
            // If idx >= 0 => exact match, else negative => insertion point
            if (idx < 0) {
                idx = -idx - 1; 
            }
            // 'idx' is the insertion point for minLast+1 => 
            // => all elements < minLast+1 => means <= minLast
            // => 'idx' is the count of positions <= minLast

            ans += idx;
        }

        return ans;
    }

    // Helper to check if character is vowel
    private boolean isVowel(char c) {
        return "aeiou".indexOf(c) != -1;
    }
}
