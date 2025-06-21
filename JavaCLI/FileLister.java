import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.stream.Collectors;

public class FileLister {

    public static void list(String typeFilter, String sortByRaw, String sortOrderRaw) throws IOException {
        Path path = PathManager.getCurrentPath();

        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IOException("Current path is invalid or not a directory.");
        }

        List<Path> items = Files.list(path)
                .filter(p -> {
                    if ("files".equalsIgnoreCase(typeFilter)) return Files.isRegularFile(p);
                    if ("folders".equalsIgnoreCase(typeFilter)) return Files.isDirectory(p);
                    return true; // both
                })
                .collect(Collectors.toList());

        if (items.isEmpty()) {
            System.out.println("No matching items found.");
            return;
        }

        // Default sorting options
        List<String> sortByList = new ArrayList<>();
        String sortOrder = "asc";

        if (sortByRaw != null && !sortByRaw.isEmpty()) {
            sortByList = Arrays.asList(sortByRaw.split(","));
        } else {
            sortByList.add("name");
        }

        if (sortOrderRaw != null && !sortOrderRaw.isEmpty()) {
            sortOrder = sortOrderRaw.toLowerCase();
        }

        Comparator<Path> comparator = buildComparator(sortByList);
        if ("desc".equals(sortOrder)) comparator = comparator.reversed();

        items.sort(comparator);

        for (Path item : items) {
            BasicFileAttributes attrs = Files.readAttributes(item, BasicFileAttributes.class);
            String type = Files.isDirectory(item) ? "[DIR]" : "[FILE]";
            System.out.printf("%-6s %-30s %-10d %-20s\n",
                    type,
                    item.getFileName(),
                    attrs.size(),
                    attrs.lastModifiedTime());
        }
    }

    private static Comparator<Path> buildComparator(List<String> sortByList) {
        Comparator<Path> comparator = Comparator.comparing(Path::getFileName);

        for (String sortBy : sortByList) {
            switch (sortBy.toLowerCase()) {
                case "name":
                    comparator = comparator.thenComparing(Path::getFileName);
                    break;
                case "size":
                    comparator = comparator.thenComparingLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0; // handle error gracefully
                        }
                    });
                    break;
                case "date":
                    comparator = comparator.thenComparingLong(p -> {
                        try {
                            return Files.getLastModifiedTime(p).toMillis();
                        } catch (IOException e) {
                            return 0L; // handle error gracefully
                        }
                    });
                    break;
                default:
                    System.out.println("Unknown sort option: " + sortBy);
            }
        }

        return comparator;
    }
}