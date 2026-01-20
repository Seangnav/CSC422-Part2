import java.util.ArrayList;
import java.util.List;

public class PetDatabase {

    private List<Pet> pets;

    public PetDatabase() {
        this.pets = new ArrayList<>();
    }

    public void addPet(String name, int age) {
        pets.add(new Pet(name, age));
    }

    public int size() {
        return pets.size();
    }

    public List<Pet> getPets() {
        return pets;
    }

    // Print all pets in the table format required
    public void printAllPets() {
        printTable(pets);
    }

    private void printTable(List<Pet> petsToPrint) {
        String line = "+----------------------+";

        System.out.println(line);
        System.out.printf("| %3s | %-10s | %4s |%n", "ID", "NAME", "AGE");
        System.out.println(line);

        for (int i = 0; i < petsToPrint.size(); i++) {
            Pet p = petsToPrint.get(i);
            System.out.printf("| %3d | %-10s | %4d |%n", i, p.getName(), p.getAge());
        }

        System.out.println(line);
        System.out.printf("%d rows in set.%n", petsToPrint.size());
    }
}
