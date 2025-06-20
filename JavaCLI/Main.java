import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Smart File Organizer CLI - File Operations");
        System.out.println("Commands: read, write, copy, move, delete, exit");

        while (true) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine();
            String[] parts = input.trim().split("\\s+", 3); // allow content with spaces

            if (parts.length < 1) continue;

            String command = parts[0].toLowerCase();

            try {
                switch (command) {
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
