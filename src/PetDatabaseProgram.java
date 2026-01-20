import java.util.Scanner;

public class PetDatabaseProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabase database = new PetDatabase();

        System.out.println("Pet Database Program.");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Your choice: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                continue;
            }

            switch (choice) {
                case 1:
                    // View all pets
                    database.printAllPets();
                    break;

                case 2:
                    // Add more pets
                    addPets(scanner, database);
                    break;

                case 3:
                case 4:
                case 5:
                case 6:
                    // Not part of Release 1 yet
                    System.out.println("This feature is not implemented in Release 1.");
                    break;

                case 7:
                    // Exit
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        System.out.println("3) Update an existing pet");
        System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit program");
    }

    private static void addPets(Scanner scanner, PetDatabase database) {
        int addedCount = 0;

        while (true) {
            System.out.print("add pet (name, age): ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("done")) {
                break;
            }

            // Expect: "Name Age"
            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter in the format: name age");
                continue;
            }

            String name = parts[0];
            int age;

            try {
                age = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter an integer value for age.");
                continue;
            }

            database.addPet(name, age);
            addedCount++;
        }

        System.out.printf("%d pets added.%n", addedCount);
    }
}
