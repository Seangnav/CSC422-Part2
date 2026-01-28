import java.util.Scanner;

/*
  Main class that handles all user interaction.
  Release 3: Now all features are added (view, add, update, remove, search).
*/
public class PetDatabaseProgram {

    // 🔹 Milestone 3: file where pet data is stored
    private static final String DATA_FILE = "pets.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetDatabase database = new PetDatabase();

        // 🔹 Load existing pets from file (if it exists)
        database.loadFromFile(DATA_FILE);

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
                    updatePet(scanner, database);
                    break;

                case 4:
                    removePet(scanner, database);
                    break;

                case 5:
                    searchByName(scanner, database);
                    break;

                case 6:
                    searchByAge(scanner, database);
                    break;

                case 7:
                    // 🔹 Save pets to file before exiting
                    database.saveToFile(DATA_FILE);
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

    /*
      Updates a single pet based on ID.
      Shows table, asks for ID, then asks for new name/age.
    */
    private static void updatePet(Scanner scanner, PetDatabase database) {
        if (database.size() == 0) {
            System.out.println("No pets in database to update.");
            return;
        }

        database.printAllPets();
        System.out.print("Enter the pet ID you want to update: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        if (id < 0 || id >= database.size()) {
            System.out.println("ID out of range.");
            return;
        }

        // Retrieve old pet for message output
        Pet oldPet = database.getPet(id);

        System.out.print("Enter new name and new age: ");
        String[] parts = scanner.nextLine().trim().split("\\s+");

        if (parts.length != 2) {
            System.out.println("Invalid input. Please enter: name age");
            return;
        }

        String newName = parts[0];
        int newAge;

        // Validate new age
        try {
            newAge = Integer.parseInt(parts[1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid age.");
            return;
        }

        database.updatePet(id, newName, newAge);

        System.out.printf("%s %d changed to %s %d.%n",
                oldPet.getName(), oldPet.getAge(), newName, newAge);
    }

    /*
      Removes a pet based on ID.
      Shows table → asks for ID → removes pet.
    */
    private static void removePet(Scanner scanner, PetDatabase database) {
        if (database.size() == 0) {
            System.out.println("No pets in database to remove.");
            return;
        }

        database.printAllPets();
        System.out.print("Enter the pet ID to remove: ");

        int id;
        try {
            id = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID.");
            return;
        }

        if (id < 0 || id >= database.size()) {
            System.out.println("ID out of range.");
            return;
        }

        Pet removed = database.removePet(id);
        System.out.printf("%s %d is removed.%n", removed.getName(), removed.getAge());
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
