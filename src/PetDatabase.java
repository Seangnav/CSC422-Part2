import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
  PetDatabase manages a dynamic list of Pet objects.
  Release 3: Supports adding, listing, searching, updating, and removing pets.
*/
public class PetDatabase {

    private List<Pet> pets;

    // Milestone 4: Database supports only 5 entries
    private static final int MAX_PETS = 5;

    public PetDatabase() {
        this.pets = new ArrayList<>();
    }

    // Add a new pet to the list 
    // Milestone 4: return false if database is full
    public boolean addPet(String name, int age) {
        if (pets.size() >= MAX_PETS) {
            return false;
        }
        pets.add(new Pet(name, age));
        return true;
    }

    // Returns how many pets are currently stored 
    public int size() {
        return pets.size();
    }

    // Returns a pet by index (ID) 
    public Pet getPet(int index) {
        return pets.get(index);
    }

    // Update the name and age of an existing pet
    public void updatePet(int index, String newName, int newAge) {
        Pet p = pets.get(index);
        p.setName(newName);
        p.setAge(newAge);
    }

    // Removes a pet by index and returns the removed pet 
    public Pet removePet(int index) {
        return pets.remove(index);
    }

    // Prints all pets in a formatted table.
    public void printAllPets() {
        String line = "+----------------------+";
        System.out.println(line);
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println(line);

        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            System.out.printf("| %3d | %-10s | %4d |%n", i, p.getName(), p.getAge());
        }

        System.out.println(line);
        System.out.printf("%d rows in set.%n", pets.size());
    }

    // Print pets that match a given name  
    public void printPetsByName(String name) {
        String line = "+----------------------+";
        System.out.println(line);
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println(line);

        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.printf("| %3d | %-10s | %4d |%n", i, p.getName(), p.getAge());
                count++;
            }
        }

        System.out.println(line);
        System.out.printf("%d rows in set.%n", count);
    }

    // Print pets that have a matching age 
    public void printPetsByAge(int age) {
        String line = "+----------------------+";
        System.out.println(line);
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println(line);

        int count = 0;

        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            if (p.getAge() == age) {
                System.out.printf("| %3d | %-10s | %4d |%n", i, p.getName(), p.getAge());
                count++;
            }
        }

        System.out.println(line);
        System.out.printf("%d rows in set.%n", count);
    }

    // 🔹 Milestone 3: Load pets from a text file
    public void loadFromFile(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            // No file yet, start with empty database
            return;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                if (pets.size() >= MAX_PETS) {
                    // Milestone 4: do not load more than 5
                    break;
                }

                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    // Bad line format, skip it
                    continue;
                }

                String name = parts[0];
                int age;

                try {
                    age = Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    // Invalid age in file, skip that line
                    continue;
                }

                // Keep file clean by only loading valid ages 1–20
                if (age < 1 || age > 20) {
                    continue;
                }

                addPet(name, age);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not load pets from file: " + e.getMessage());
        }
    }

    // 🔹 Milestone 3: Save pets to a text file
    public void saveToFile(String filename) {
    File file = new File(filename);

    try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
        for (Pet p : pets) {
            out.println(p.getName() + " " + p.getAge());
        }
    } catch (IOException e) {
        System.out.println("Could not save pets to file: " + e.getMessage());
    }
}
}