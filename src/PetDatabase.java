import java.util.ArrayList;
import java.util.List;

/*
  PetDatabase manages a dynamic list of Pet objects.
  Release 2: supports adding, listing, and searching pets.
*/
public class PetDatabase {

    private List<Pet> pets;

    public PetDatabase() {
        this.pets = new ArrayList<>();
    }

    // Add a new pet to the list 
    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

    // Returns how many pets are currently stored 
    public int size() {
        return pets.size();
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

    // Print pets that match a given name (case-insensitive)
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
}
