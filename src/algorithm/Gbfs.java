package algorithm;
import java.util.*;
import utils.*;

public class Gbfs {
    private Integer nodeVisited;
    private List<String> solution;

    public List<String> getSolution(){
        return solution;
    }

    public Integer getNodeVisited(){
        return nodeVisited;
    }

    public void ladderPathGbfs(String start, String end, HashSet<String> dict) {

        boolean isFound = false;
        PriorityQueue<String> wordQueue = new PriorityQueue<>(Comparator.comparingInt(s -> Heuristic.heuristic(s, end)));
        Map<String, String> cameFrom = new HashMap<>();
        Set<String> visited = new HashSet<>();
        wordQueue.add(start);
        visited.add(start);

        while (!wordQueue.isEmpty()) {
            String currWord = wordQueue.poll();
            if (currWord.equals(end)) {
                this.solution = buildPath(cameFrom, end);
                isFound = true;
                break;
            }

            for (String neighbor : Neighbors.getNeighbors(currWord, dict)) {
                if (!visited.contains(neighbor)) { 
                    wordQueue.add(neighbor);
                    visited.add(neighbor);
                    cameFrom.put(neighbor, currWord);
                }
            }
        }

        if(!isFound){
            this.solution = new ArrayList<>();
        }

        this.nodeVisited = visited.size();
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
