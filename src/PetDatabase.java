import java.util.ArrayList;
import java.util.List;

/*
  PetDatabase manages a dynamic list of Pet objects.
  It supports adding, removing, updating, and searching pets.
  Also prints tables in the assignment format.
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
}
