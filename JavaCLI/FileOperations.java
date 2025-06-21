import java.io.*;
import java.nio.file.*;

public class FileOperations {

    public static void readFile(String filePath) throws IOException {
        Path path = PathManager.getCurrentPath().resolve(filePath);
        if (!Files.exists(path)) throw new FileNotFoundException("File not found!");

        Files.lines(path).forEach(System.out::println);
    }

    public static void writeFile(String filePath, String content) throws IOException {
        Path path = PathManager.getCurrentPath().resolve(filePath);
        Files.write(path, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        System.out.println("Content written to file.");
    }

    public static void copyFile(String sourcePath, String destPath) throws IOException {
        Path src = PathManager.getCurrentPath().resolve(sourcePath);
        Path dest = PathManager.getCurrentPath().resolve(destPath);
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File copied successfully.");
    }

    public static void moveFile(String sourcePath, String destPath) throws IOException {
        Path src = PathManager.getCurrentPath().resolve(sourcePath);
        Path dest = PathManager.getCurrentPath().resolve(destPath);
        Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("File moved successfully.");
    }

    public static void deleteFile(String filePath) throws IOException {
        Path path = PathManager.getCurrentPath().resolve(filePath);
        Files.delete(path);
        System.out.println("File deleted successfully.");
    }
}
