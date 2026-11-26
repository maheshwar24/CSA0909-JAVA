import java.io.*;
import java.util.*;

public class SalesReport {
    private static final String[] FILES = {"store1.txt", "store2.txt", "store3.txt"};

    static void manualReport() {
        List<BufferedReader> readers = new ArrayList<>();
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("summary_manual.txt"));
            for (String file : FILES) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                readers.add(reader);
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(file + ": " + line);
                    writer.newLine();
                }
            }
            System.out.println("Manual report created.");
        } catch (IOException e) {
            System.out.println("Manual report failed: " + e.getMessage());
        } finally {
            for (BufferedReader reader : readers) {
                try { reader.close(); }
                catch (IOException e) { System.out.println(e.getMessage()); }
            }
            if (writer != null) {
                try { writer.close(); }
                catch (IOException e) { System.out.println(e.getMessage()); }
            }
        }
    }

    static void safeReport() {
        try (BufferedWriter writer =
                 new BufferedWriter(new FileWriter("summary_safe.txt"))) {
            for (String file : FILES) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(file + ": " + line);
                        writer.newLine();
                    }
                }
            }
            System.out.println("Try-with-resources report created.");
        } catch (IOException e) {
            System.out.println("Safe report failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        manualReport();
        safeReport();
    }
}
