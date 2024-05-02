import java.util.*;

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

            for (int i = 0; i < currWord.length(); i++) {
                char[] currCharArr = currWord.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    currCharArr[i] = c;
                    String newWord = new String(currCharArr);
                    if (dict.contains(newWord)) {
                        wordQueue.add(newWord);
                        distanceQueue.add(currDistance + 1);
                        dict.remove(newWord);

                        LinkedList<String> newPath = new LinkedList<>(currentPathQueue);
                        newPath.add(newWord);
                        pathsQueue.add(newPath);
                    }
                }
            }
        }

        if (result < Integer.MAX_VALUE) {
            return solution;
        } else {
            return new ArrayList<>();
        }
    }

}
