package algorithm;
import java.util.*;
import utils.*;

public class Gbfs {
    public static List<String> ladderPathGbfs(String start, String end, HashSet<String> dict) {

        PriorityQueue<String> wordQueue = new PriorityQueue<>(Comparator.comparingInt(s -> Heuristic.heuristic(s, end)));
        Map<String, String> cameFrom = new HashMap<>();
        Set<String> visited = new HashSet<>();
        wordQueue.add(start);
        visited.add(start);

        while (!wordQueue.isEmpty()) {
            String currWord = wordQueue.poll();
            if (currWord.equals(end)) {
                return buildPath(cameFrom, end);
            }

            for (String neighbor : Neighbors.getNeighbors(currWord, dict)) {
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

}
