// ---------- ABSTRACT PARENT CLASS (Unit 2: Inheritance) ----------
public abstract class Person {
    // Encapsulation: private fields, accessed via getters/setters (Unit 1)
    private String name;
    private int age;
    private String gender;
    private String contactNumber;

    public Person(String name, int age, String gender, String contactNumber) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contactNumber = contactNumber;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContactNumber() { return contactNumber; }

    // Abstract method -> each subclass must implement its own version
    public abstract void displayDetails();
}
