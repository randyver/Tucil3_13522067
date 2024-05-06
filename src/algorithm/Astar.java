package algorithm;
import java.util.*;
import utils.*;

public class Astar {
    private Integer nodeVisited;
    private List<String> solution;

    public List<String> getSolution(){
        return solution;
    }

    public Integer getNodeVisited(){
        return nodeVisited;
    }

    public void ladderPathAstar(String start, String end, HashSet<String> dict) {

        boolean isFound = false;

        int nodeVisited = 0;
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(n -> n.fCost));
        Map<String, Node> allNodes = new HashMap<>();

        Node startNode = new Node(start, null, 0, Heuristic.heuristic(start, end));
        allNodes.put(start, startNode);
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();
            nodeVisited ++;
            if (current.word.equals(end)) {
                this.solution = buildPath(current);
                isFound = true;
                break;
            }

            for (String neighbor : Neighbors.getNeighbors(current.word, dict)) {
                if (!allNodes.containsKey(neighbor) || current.gCost + 1 < allNodes.get(neighbor).gCost) {
                    Node neighborNode = allNodes.computeIfAbsent(neighbor, n -> new Node(n, current, dict.size(), Heuristic.heuristic(n, end)));
                    neighborNode.cameFrom = current;
                    neighborNode.gCost = current.gCost + 1;
                    neighborNode.fCost = neighborNode.gCost + neighborNode.hCost;
                    openSet.add(neighborNode);
                }
            }
        }

        if(!isFound){
            this.solution = new ArrayList<>();
        }
        this.nodeVisited = nodeVisited;
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
}
