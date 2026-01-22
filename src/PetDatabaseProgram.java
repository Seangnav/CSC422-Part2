import java.util.Scanner;

/*
  Main class that handles all user interaction.
  Release 2: view, add, and search pets.
*/
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

            // Validate menu choice is a number
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                continue;
            }

            // Handle each option
            switch (choice) {
                case 1:
                    database.printAllPets();
                    break;

                case 2:
                    addPets(scanner, database);
                    break;

                case 3:
                case 4:
                    System.out.println("This feature is not implemented in this release.");
                    break;

                case 5:
                    searchByName(scanner, database);
                    break;

                case 6:
                    searchByAge(scanner, database);
                    break;

                case 7:
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1–7.");
            }
        }

        scanner.close();
    }

    // Displays the menu options
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

    /*
      Adds pets in a loop until the user types "done".
      Input format: name age
    */
    private static void addPets(Scanner scanner, PetDatabase database) {
        int addedCount = 0;

        while (true) {
            System.out.print("add pet (name, age): ");
            String line = scanner.nextLine().trim();

            // Stop reading
            if (line.equalsIgnoreCase("done")) {
                break;
            }

            String[] parts = line.split("\\s+");

            // Expect exactly two parts: name and age
            if (parts.length != 2) {
                System.out.println("Invalid input. Please enter: name age");
                continue;
            }

            String name = parts[0];
            int age;

            // Ensure age is an integer
            try {
                age = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Must be an integer.");
                continue;
            }

            database.addPet(name, age);
            addedCount++;
        }

        System.out.printf("%d pets added.%n", addedCount);
    }

    // Searches pets by name 
    private static void searchByName(Scanner scanner, PetDatabase database) {
        System.out.print("Enter a name to search: ");
        String name = scanner.nextLine().trim();
        database.printPetsByName(name);
    }

    // Searches pets by age 
    private static void searchByAge(Scanner scanner, PetDatabase database) {
        System.out.print("Enter age to search: ");

        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age.");
            return;
        }

        database.printPetsByAge(age);
    }
}
