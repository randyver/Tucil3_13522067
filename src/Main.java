import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import algorithm.Gbfs;
import algorithm.Ucs;

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
            List<String> pathUcs = Ucs.ladderPathUcs(startWord, endWord, new HashSet<>(dictionary));
            long endTimeUcs = System.currentTimeMillis();
    
            long startTimeGbfs = System.currentTimeMillis();
            List<String> pathGbfs = Gbfs.ladderPathGbfs(startWord, endWord, new HashSet<>(dictionary));
            long endTimeGbfs = System.currentTimeMillis();
    
            long startTimeAstar = System.currentTimeMillis();
            List<String> pathAstar = Gbfs.ladderPathGbfs(startWord, endWord, new HashSet<>(dictionary));
            long endTimeAstar = System.currentTimeMillis();
    
            System.out.println("---------------------------------- RESULT ----------------------------------");
            System.out.println("Uniform Cost Search");
            printPath(pathUcs, startTimeUcs, endTimeUcs);
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Greedy Best First Search");
            printPath(pathGbfs, startTimeGbfs, endTimeGbfs);
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("A star");
            printPath(pathAstar, startTimeAstar, endTimeAstar);
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
