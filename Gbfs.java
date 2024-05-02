import java.util.*;

public class Gbfs {
    public static List<String> ladderPathGbfs(String start, String end, HashSet<String> dict) {

        PriorityQueue<String> wordQueue = new PriorityQueue<>(Comparator.comparingInt(s -> heuristic(s, end)));
        Map<String, String> cameFrom = new HashMap<>();
        Set<String> visited = new HashSet<>();
        wordQueue.add(start);
        visited.add(start);

        while (!wordQueue.isEmpty()) {
            String currWord = wordQueue.poll();
            if (currWord.equals(end)) {
                return buildPath(cameFrom, end);
            }

            for (String neighbor : getNeighbors(currWord, dict)) {
                if (!visited.contains(neighbor)) { 
                    wordQueue.add(neighbor);
                    visited.add(neighbor);
                    cameFrom.put(neighbor, currWord);
                }
            }
        }

        return new ArrayList<>();
    }

    private static List<String> buildPath(Map<String, String> cameFrom, String end) {
        LinkedList<String> path = new LinkedList<>();
        String current = end;
        while (current != null) {
            path.addFirst(current);
            current = cameFrom.get(current);
        }
        return path;
    }

    private static int heuristic(String word, String target) {
        int diffCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) {
                diffCount++;
            }
        }
        return diffCount;
    }

    private static List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            char[] currCharArr = word.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                currCharArr[i] = c;
                String newWord = new String(currCharArr);
                if (dict.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
        }
        return neighbors;
    }

}
