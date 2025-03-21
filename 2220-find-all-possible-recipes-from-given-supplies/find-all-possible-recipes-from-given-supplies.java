class Solution {
    public List<String> findAllRecipes(
        String[] recipes, List<List<String>> ingredients, String[] supplies) {
        //Github -- vipulhere
        // Initialize a graph where ingredients point to recipes they contribute to
        Map<String, List<String>> graph = new HashMap<>();
        // Initialize a map to track how many ingredients each recipe needs (in-degree)
        Map<String, Integer> indegreeMap = new HashMap<>();
      
        // Build the graph and in-degree map
        for (int i = 0; i < recipes.length; ++i) {
            String recipe = recipes[i];
            indegreeMap.put(recipe, ingredients.get(i).size()); // Number of missing ingredients
            for (String ingredient : ingredients.get(i)) {
                // Add the recipe to the list of recipes that the ingredient can help create
                graph.computeIfAbsent(ingredient, k -> new ArrayList<>()).add(recipe);
            }
        }
      
        // Queue to perform the topological sort (starting with available supplies)
        Deque<String> queue = new ArrayDeque<>(Arrays.asList(supplies));
      
        // List to store the available recipes
        List<String> availableRecipes = new ArrayList<>();
      
        // Process the queue using BFS
        while (!queue.isEmpty()) {
            String ingredient = queue.pollFirst(); // Get the next available ingredient
              
            // Check if this ingredient contributes to any recipes
            if (graph.containsKey(ingredient)) {
                for (String recipe : graph.get(ingredient)) {
                    // Reduce the number of missing ingredients
                    indegreeMap.put(recipe, indegreeMap.get(recipe) - 1);
                  
                    // If the recipe's in-degree reaches 0, it means we can now make it
                    if (indegreeMap.get(recipe) == 0) {
                        availableRecipes.add(recipe);
                        queue.offer(recipe); // Add this new recipe as an ingredient
                    }
                }
            }
        }
      
        // Return all the recipes that can be made
        return availableRecipes;
    }
}
