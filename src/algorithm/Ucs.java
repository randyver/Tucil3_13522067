package algorithm;
import java.util.*;
import utils.*;

public class Ucs {
    public static List<String> ladderPathUcs(String start, String end, HashSet<String> dict) {

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

        while (!wordQueue.isEmpty()) {
            String currWord = wordQueue.pop();
            Integer currDistance = distanceQueue.pop();
            LinkedList<String> currentPathQueue = pathsQueue.pop();

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

        if (result < Integer.MAX_VALUE) {
            return solution;
        } else {
            return new ArrayList<>();
        }
    }
}
