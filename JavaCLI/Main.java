import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Smart File Organizer CLI - File Operations");
        System.out.println("Commands: pwd, cd, read, write, copy, move, delete,list(list arg1 arg2 arg3 where arg1 is file or folder; arg2 is category 1,2; arg3 is asc or desc ) exit");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine();
            String[] parts = input.trim().split("\\s+", 3); // allow content with spaces

            if (parts.length < 1) continue;

            String command = parts[0].toLowerCase();

            try {
                switch (command) {
                    case "pwd":
                        System.out.println(PathManager.getCurrentPathAsString());
                        break;
                    case "cd":
                        PathManager.setCurrentPath(parts[1]);
                        System.out.println(PathManager.getCurrentPathAsString());
                        break;
                    case "read":
                        FileOperations.readFile(parts[1]);
                        break;
                    case "write":
                        FileOperations.writeFile(parts[1], parts[2]);
                        break;
                    case "copy":
                        FileOperations.copyFile(parts[1], parts[2]);
                        break;
                    case "move":
                        FileOperations.moveFile(parts[1], parts[2]);
                        break;
                    case "delete":
                        FileOperations.deleteFile(parts[1]);
                        break;

                    case "list":
                        String type = "";
                        String sortBy = "";
                        String order = "";

                        if (parts.length == 2) {
                            // could be: list files
                            type = parts[1];
                        } else if (parts.length == 3) {
                            // could be: list name desc
                            if (parts[1].equalsIgnoreCase("files") || parts[1].equalsIgnoreCase("folders")) {
                                type = parts[1];
                                sortBy = "name";
                                order = parts[2];
                            } else {
                                sortBy = parts[1];
                                order = parts[2];
                            }
                        } else if (parts.length >= 4) {
                            type = parts[1];
                            sortBy = parts[2];
                            order = parts[3];
                        }

                        FileLister.list(type, sortBy, order);
                        break;


                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Unknown command!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
