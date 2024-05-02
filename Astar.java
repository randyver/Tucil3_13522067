import java.util.*;

public class Astar {
    public static List<String> ladderPath(String start, String end, HashSet<String> dict) {
        if (dict.size() == 0) return new ArrayList<>();
        dict.add(end);

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));
        Map<String, Node> allNodes = new HashMap<>();

        Node startNode = new Node(start, null, 0, heuristic(start, end));
        allNodes.put(start, startNode);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            if (current.word.equals(end)) {
                return buildPath(current);
            }

            for (String neighbor : getNeighbors(current.word, dict)) {
                if (!allNodes.containsKey(neighbor) || current.gCost + 1 < allNodes.get(neighbor).gCost) {
                    Node neighborNode = allNodes.computeIfAbsent(neighbor, n -> new Node(n, current, dict.size(), heuristic(n, end)));
                    neighborNode.cameFrom = current;
                    neighborNode.gCost = current.gCost + 1;
                    neighborNode.fCost = neighborNode.gCost + neighborNode.hCost;
                    openSet.add(neighborNode);
                }
            }
        }

        return new ArrayList<>(); // No ladder found
    }

    private static List<String> buildPath(Node endNode) {
        LinkedList<String> path = new LinkedList<>();
        Node current = endNode;
        while (current != null) {
            path.addFirst(current.word);
            current = current.cameFrom;
        }
        return path;
    }

    private static int heuristic(String word, String target) {
        // Simple heuristic: count the number of differing characters
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

    private static class Node {
        String word;
        Node cameFrom;
        int gCost;
        int hCost;
        int fCost;

        Node(String word, Node cameFrom, int gCost, int hCost) {
            this.word = word;
            this.cameFrom = cameFrom;
            this.gCost = gCost;
            this.hCost = hCost;
            this.fCost = gCost + hCost;
        }
    }

    public static void main(String[] args) {
        HashSet<String> dictionary = new HashSet<>();
        dictionary.add("cat");
        dictionary.add("cot");
        dictionary.add("dot");
        dictionary.add("dog");

        String startWord = "cat";
        String endWord = "dog";

        List<String> path = ladderPath(startWord, endWord, dictionary);
        if (!path.isEmpty()) {
            System.out.println("Shortest ladder path: " + path);
        } else {
            System.out.println("No ladder found.");
        }
    }
}
