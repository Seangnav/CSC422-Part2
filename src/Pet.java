/*
  Represents a single pet with a name and age.
  This class uses basic encapsulation and getters/setters.
*/
public class Pet {
    private String name;
    private int age;

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Accessor methods
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // Mutator methods
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
