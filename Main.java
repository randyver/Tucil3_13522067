import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan kata awal:");
        String startWord = input.nextLine();
        System.out.println("Masukkan kata akhir:");
        String endWord = input.nextLine();
        input.close();

        HashSet<String> dictionary = readDictionaryFromFile("words.txt");

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
        System.out.println("UCS:");
        printPath(pathUcs, startTimeUcs, endTimeUcs);
        System.out.println("GBFS:");
        printPath(pathGbfs, startTimeGbfs, endTimeGbfs);
        System.out.println("Astar:");
        printPath(pathAstar, startTimeAstar, endTimeAstar);
        System.out.println("----------------------------------------------------------------------------");
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
            System.out.println("Shortest ladder path: " + path);
            System.out.println("Length path: " + path.size());
        } else {
            System.out.println("No ladder found.");
        }
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
}
