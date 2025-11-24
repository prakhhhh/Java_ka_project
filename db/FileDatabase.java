import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDatabase {

    private static final String DATA_DIR = "data";

    static {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public static List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(DATA_DIR + File.separator + fileName);
        if (!file.exists()) {
            return lines;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + fileName + ": " + e.getMessage());
        }
        return lines;
    }

    public static void writeLines(String fileName, List<String> lines) {
        File file = new File(DATA_DIR + File.separator + fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing " + fileName + ": " + e.getMessage());
        }
    }

    public static void appendLine(String fileName, String line) {
        File file = new File(DATA_DIR + File.separator + fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(line);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error appending " + fileName + ": " + e.getMessage());
        }
    }
}
