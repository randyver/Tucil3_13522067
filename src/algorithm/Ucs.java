package algorithm;
import java.util.*;
import utils.*;

public class Ucs {
    private Integer nodeVisited;
    private List<String> solution;

    public List<String> getSolution(){
        return solution;
    }

    public Integer getNodeVisited(){
        return nodeVisited;
    }

    public void ladderPathUcs(String start, String end, HashSet<String> dict) {

        LinkedList<String> wordQueue = new LinkedList<>();
        LinkedList<Integer> distanceQueue = new LinkedList<>();
        LinkedList<LinkedList<String>> pathsQueue = new LinkedList<>();
        LinkedList<String> solution = new LinkedList<>();

        wordQueue.add(start);
        distanceQueue.add(1);
        dict.remove(start);

        LinkedList<String> path = new LinkedList<>();
        path.add(start);
        pathsQueue.add(path);

        int result = Integer.MAX_VALUE;
        int nodeVisited = 0;

        while (!wordQueue.isEmpty()) {
            String currWord = wordQueue.pop();
            Integer currDistance = distanceQueue.pop();
            LinkedList<String> currentPathQueue = pathsQueue.pop();
            nodeVisited ++;

            if (currWord.equals(end)) {
                if (currDistance < result) {
                    result = currDistance;
                    solution = currentPathQueue;
                }
            }

            List<String> neighbors = Neighbors.getNeighbors(currWord, dict);
            for (String neighbor : neighbors) {
                wordQueue.add(neighbor);
                distanceQueue.add(currDistance + 1);
                dict.remove(neighbor);

                LinkedList<String> newPath = new LinkedList<>(currentPathQueue);
                newPath.add(neighbor);
                pathsQueue.add(newPath);
            }
        }

        this.solution = solution;
        this.nodeVisited = nodeVisited;

    }
}
