import java.nio.file.Path;
import java.nio.file.Paths;

public class PathManager {
    private static Path currentPath = Paths.get(System.getProperty("user.dir"));

    public static Path getCurrentPath(){
        return currentPath;
    }

    public static void setCurrentPath(String newPath) throws Exception {
        Path path = Paths.get(newPath);
        if (!path.isAbsolute()) {
            path = currentPath.resolve(path);            
        }
        path = path.normalize().toAbsolutePath();
        if (!path.toFile().exists() || !path.toFile().isDirectory()) {
            throw new Exception("Ivalid directory path.");
        }
        currentPath = path;
    
    }
    public static String getCurrentPathAsString(){
        return currentPath.toAbsolutePath().toString();
    }
}
