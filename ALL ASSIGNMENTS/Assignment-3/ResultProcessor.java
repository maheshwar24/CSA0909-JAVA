import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;

public class ResultProcessor {
    static class ResultTask implements Callable<Integer> {
        private final Path file;
        ResultTask(Path file) { this.file = file; }

        public Integer call() throws Exception {
            int total = 0;
            try (BufferedReader reader = Files.newBufferedReader(file)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.isBlank()) total += Integer.parseInt(line.trim());
                }
            }
            return total;
        }
    }

    public static void main(String[] args) {
        int poolSize = 4;
        if (args.length > 0) {
            try { poolSize = Math.max(1, Integer.parseInt(args[0])); }
            catch (NumberFormatException e) { System.out.println("Using default pool size 4."); }
        }

        ExecutorService pool = Executors.newFixedThreadPool(poolSize);
        List<Future<Integer>> results = new ArrayList<>();
        int success = 0, failed = 0, grandTotal = 0;

        try {
            Path directory = Paths.get("scores");
            if (!Files.isDirectory(directory)) {
                System.out.println("Create a scores folder containing .txt files.");
                return;
            }
            try (DirectoryStream<Path> files = Files.newDirectoryStream(directory, "*.txt")) {
                for (Path file : files) results.add(pool.submit(new ResultTask(file)));
            }

            for (Future<Integer> result : results) {
                try {
                    int total = result.get();
                    grandTotal += total;
                    success++;
                    System.out.println("File total: " + total);
                } catch (ExecutionException e) {
                    failed++;
                    System.out.println("A file failed: " + e.getCause().getMessage());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("Successful files: " + success);
            System.out.println("Failed files: " + failed);
            System.out.println("Grand total of marks: " + grandTotal);
        } catch (IOException e) {
            System.out.println("Directory error: " + e.getMessage());
        } finally {
            pool.shutdown();
        }
    }
}
