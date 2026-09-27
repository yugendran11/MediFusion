
import java.util.Scanner;

// ---------- MANAGER CLASS implementing the interface ----------
public class PatientRecordSystem implements MedicalRecordOperations {
    private GenericRepository<Patient> patientList =
        new GenericRepository<>();
    private Scanner sc = new Scanner(System.in);

    // Interactive method: the USER enters patient details at runtime
    @Override
    public void addPatient() {
        System.out.println("\nEnter Patient Details:");

        System.out.print("Patient ID       : ");
        String id = sc.nextLine();

        System.out.print("Name             : ");
        String name = sc.nextLine();

        System.out.print("Age              : ");
        int age;
         try {
    age = Integer.parseInt(sc.nextLine().trim());

    if (age <= 0 || age > 120) {
        throw new IllegalArgumentException("Invalid age!");
    }

} catch (NumberFormatException e) {

    System.out.println("Age must be a number!");
    return;

} catch (IllegalArgumentException e) {

    System.out.println(e.getMessage());
    return;
}


        System.out.print("Gender           : ");
        String gender = sc.nextLine();

        System.out.print("Contact Number   : ");
        String contact = sc.nextLine();

        System.out.print("Disease/Diagnosis: ");
        String disease = sc.nextLine();

        System.out.print("Admission Date (dd-mm-yyyy): ");
        String admissionDate = sc.nextLine();

        Patient p = new Patient(id, name, age, gender, contact, disease, admissionDate);
        patientList.add(p);

        System.out.println("\nPatient record added successfully!\n");
    }

    @Override
    public void displayAllPatients() {
        if (patientList.size() == 0) {
            System.out.println("\nNo patient records found.\n");
            return;
        }
        System.out.println("\n===== ALL PATIENT RECORDS =====");
        for (Patient p : patientList.getAll()) {
            p.displayDetails();
        }
    }

    @Override
    public void searchPatient(String patientId) {
        for (Patient p : patientList.getAll()) {
            if (p.getPatientId().equalsIgnoreCase(patientId)) {
                System.out.println("\nPatient Found:");
                p.displayDetails();
                return;
            }
        }
        System.out.println("\nNo patient found with ID: " + patientId);
    }

    public int getPatientCount() {
        return patientList.size();
    }
    public void loadPatientsFromFile() {
    try {
        java.io.BufferedReader reader =
                new java.io.BufferedReader(
                        new java.io.FileReader("patients.txt"));

        String line;

        while ((line = reader.readLine()) != null) {

            String[] data = line.split(",");

            if (data.length == 7) {

                Patient p = new Patient(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        data[3],
                        data[4],
                        data[5],
                        data[6]
                );

                patientList.add(p);
            }
        }

        reader.close();

        System.out.println("Patient data loaded successfully!");

    } catch (java.io.FileNotFoundException e) {

        System.out.println("No patient data file found.");

    } catch (java.io.IOException e) {

        System.out.println("Error loading patient data: "
                + e.getMessage());

    } catch (NumberFormatException e) {

        System.out.println("Invalid patient age in file.");
    }
}
    public void savePatientsToFile() {
    try {
        java.io.FileWriter writer =
                new java.io.FileWriter("patients.txt");

        for (Patient p : patientList.getAll()) {
            writer.write(p.toFileString() + System.lineSeparator());
        }

        writer.close();

        System.out.println("\nPatient data saved successfully!");

    } catch (java.io.IOException e) {
        System.out.println("\nError saving patient data: " + e.getMessage());
    }
}
public void addPatientFromGUI(Patient patient) {
    patientList.add(patient);

}
public String getPatientsForGUI() {

    StringBuilder result = new StringBuilder();

    for (Patient p : patientList.getAll()) {

        result.append("Patient ID     : ")
              .append(p.getPatientId()).append("\n");

        result.append("Name           : ")
              .append(p.getName()).append("\n");

        result.append("Age            : ")
              .append(p.getAge()).append("\n");

        result.append("Gender         : ")
              .append(p.getGender()).append("\n");

        result.append("Contact Number : ")
              .append(p.getContactNumber()).append("\n");

        result.append("Disease        : ")
              .append(p.getDisease()).append("\n");

        result.append("Admission Date : ")
              .append(p.getAdmissionDate()).append("\n");

        result.append("----------------------------\n");
    }

    if (result.length() == 0) {
        return "No patients found.";
    }

    return result.toString();
}
public String searchPatientForGUI(String patientId) {

    for (Patient p : patientList.getAll()) {

        if (p.getPatientId().equalsIgnoreCase(patientId)) {

            return "Patient Found!\n\n"
                    + "Patient ID     : " + p.getPatientId() + "\n"
                    + "Name           : " + p.getName() + "\n"
                    + "Age            : " + p.getAge() + "\n"
                    + "Gender         : " + p.getGender() + "\n"
                    + "Contact Number : " + p.getContactNumber() + "\n"
                    + "Disease        : " + p.getDisease() + "\n"
                    + "Admission Date : " + p.getAdmissionDate();
        }
    }

    return "Patient not found.";
}
}
