import java.io.*;
import java.nio.file.*;

public class FileOperations {

    public static void readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) throw new FileNotFoundException("File not found!");

        Files.lines(path).forEach(System.out::println);
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("Content written to file.");
    }

    public static void copyFile(String sourcePath, String destPath) throws IOException {
        Path src = Paths.get(sourcePath);
        Path dest = Paths.get(destPath);
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File copied successfully.");
    }

    public static void moveFile(String sourcePath, String destPath) throws IOException {
        Path src = Paths.get(sourcePath);
        Path dest = Paths.get(destPath);
        Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved successfully.");
    }

    public static void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Files.delete(path);
        System.out.println("File deleted successfully.");
    }
}
