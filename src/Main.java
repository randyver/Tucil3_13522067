import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import algorithm.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------- WordLadder Solver ----------------------------");
        Scanner input = new Scanner(System.in);
        HashSet<String> dictionary = readDictionaryFromFile("words.txt");
    
        do {
            String startWord, endWord;
            do {
                System.out.print("Start word    : ");
                startWord = input.nextLine().toLowerCase();
                System.out.print("End word      : ");
                endWord = input.nextLine().toLowerCase();
    
                if (!dictionary.contains(startWord) || !dictionary.contains(endWord)) {
                    System.out.println("Start word or end word is not in dictionary, please input again.");
                    continue;
                }
    
                if (startWord.length() != endWord.length()) {
                    System.out.println("Start word and end word must have same length, please input again.");
                    continue;
                }
    
                break;
            } while (true);
    
            long startTimeUcs = System.currentTimeMillis();
            Ucs SolveUcs = new Ucs();
            SolveUcs.ladderPathUcs(startWord, endWord, new HashSet<>(dictionary));
            long endTimeUcs = System.currentTimeMillis();
            List<String> pathUcs = SolveUcs.getSolution();
    
            long startTimeGbfs = System.currentTimeMillis();
            Gbfs SolveGbfs = new Gbfs();
            SolveGbfs.ladderPathGbfs(startWord, endWord, new HashSet<>(dictionary));
            long endTimeGbfs = System.currentTimeMillis();
            List<String> pathGbfs = SolveGbfs.getSolution();
    
            long startTimeAstar = System.currentTimeMillis();
            Astar SolveAstar = new Astar();
            SolveAstar.ladderPathAstar(startWord, endWord, new HashSet<>(dictionary));
            long endTimeAstar = System.currentTimeMillis();
            List<String> pathAstar = SolveAstar.getSolution();
    
            System.out.println("---------------------------------- RESULT ----------------------------------");
            System.out.println("Uniform Cost Search");
            printPath(pathUcs, startTimeUcs, endTimeUcs);
            System.out.print("Node visited count: ");
            System.out.println(SolveUcs.getNodeVisited());
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Greedy Best First Search");
            printPath(pathGbfs, startTimeGbfs, endTimeGbfs);
            System.out.print("Node visited count: ");
            System.out.println(SolveGbfs.getNodeVisited());
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("A star");
            printPath(pathAstar, startTimeAstar, endTimeAstar);
            System.out.print("Node visited count: ");
            System.out.println(SolveAstar.getNodeVisited());
            System.out.println("----------------------------------------------------------------------------");
    
            System.out.print("Do you want to run the program again? (y/n): ");
            String choice = input.nextLine().toLowerCase();
            if (!choice.equals("y")) {
                break;
            }
        } while (true);
    
        input.close();
    }
    
    

    private static HashSet<String> readDictionaryFromFile(String filename) {
        HashSet<String> dictionary = new HashSet<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                dictionary.add(word);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan.");
            e.printStackTrace();
        }
        return dictionary;
    }

    private static void printPath(List<String> path, long startTime, long endTime) {
        if (!path.isEmpty()) {
            System.out.println();
            for (int i = 0; i < path.size(); i++) {
                System.out.println((i + 1) + ". " + path.get(i));
            }
            System.out.println();
            System.out.println("Length path: " + path.size());
        } else {
            System.out.println("No solution found.");
        }
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
    }
    
}
