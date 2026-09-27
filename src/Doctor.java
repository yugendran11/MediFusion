// ---------- CHILD CLASS: Doctor (inherits Person) ----------
// Included to demonstrate multiple classes inheriting from the same parent
public class Doctor extends Person {
    private String doctorId;
    private String specialization;

    public Doctor(String doctorId, String name, int age, String gender,
                   String contactNumber, String specialization) {
        super(name, age, gender, contactNumber);
        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    @Override
    public void displayDetails() {
        System.out.println("-----------------------------------------");
        System.out.println("Doctor ID      : " + doctorId);
        System.out.println("Name           : " + getName());
        System.out.println("Specialization : " + specialization);
        System.out.println("Contact Number : " + getContactNumber());
        System.out.println("-----------------------------------------");
    }
}
