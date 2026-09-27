// ---------- CHILD CLASS: Patient (inherits Person) ----------
public class Patient extends Person {
    private String patientId;
    private String disease;
    private String admissionDate;

    public Patient(String patientId, String name, int age, String gender,
                    String contactNumber, String disease, String admissionDate) {
        super(name, age, gender, contactNumber); // calls Person constructor
        this.patientId = patientId;
        this.disease = disease;
        this.admissionDate = admissionDate;
    }

    public String getPatientId() { return patientId; }
    public String getDisease() { return disease; }
    public String getAdmissionDate() { return admissionDate; }

    // Method overriding (Runtime Polymorphism)
    @Override
    public void displayDetails() {
        System.out.println("-----------------------------------------");
        System.out.println("Patient ID     : " + patientId);
        System.out.println("Name           : " + getName());
        System.out.println("Age            : " + getAge());
        System.out.println("Gender         : " + getGender());
        System.out.println("Contact Number : " + getContactNumber());
        System.out.println("Disease        : " + disease);
        System.out.println("Admission Date : " + admissionDate);
        System.out.println("-----------------------------------------");
    }
    public String toFileString() {
    return patientId + "," +
           getName() + "," +
           getAge() + "," +
           getGender() + "," +
           getContactNumber() + "," +
           disease + "," +
           admissionDate;
}
}
