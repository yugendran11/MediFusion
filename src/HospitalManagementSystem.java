import java.util.Scanner;
import ML.PatientRiskPredictor;
public class HospitalManagementSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        PatientRecordSystem patientSystem =
        new PatientRecordSystem();
         MedicalRecordOperations system =
          
          patientSystem;
          patientSystem.loadPatientsFromFile();
           PatientRiskPredictor predictor =
    new PatientRiskPredictor();
    GenericRepository<Patient> patientRepository =
        new GenericRepository<>();

GenericRepository<Appointment> appointmentRepository =
        new GenericRepository<>(); 


        int choice;

        do {

            System.out.println("\n================ MAIN MENU ================");
            System.out.println("1. Add New Patient");
            System.out.println("2. Display All Patients");
            System.out.println("3. Search Patient by ID");
            System.out.println("4. AI & ML Patient Risk Prediction");
            System.out.println("5. Appointment Management");
            System.out.println("6. Save Patient Data");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                    system.addPatient();

                    break;

                case 2:

                    system.displayAllPatients();

                    break;

                case 3:

                    System.out.print("Enter Patient ID: ");
                    int searchId = input.nextInt();
                    input.nextLine();

                  system.searchPatient(String.valueOf(searchId));

                    break;

                case 4:

                    System.out.println(
                            "\n--- AI & ML PATIENT RISK PREDICTION ---"
                    );

                    System.out.print("Enter Age: ");
                    int age = input.nextInt();

                    System.out.print("Enter Temperature: ");
                    double temperature = input.nextDouble();

                    System.out.print("Enter Heart Rate: ");
                    int heartRate = input.nextInt();

                    System.out.print("Enter Fever (1 = Yes, 0 = No): ");
                    int fever = input.nextInt();

                    System.out.print("Enter Cough (1 = Yes, 0 = No): ");
                    int cough = input.nextInt();

                    String risk = predictor.predictRisk(
                            age,
                            temperature,
                            heartRate,
                            fever,
                            cough,
                            0,
                            0
                    );

                    System.out.println(
                            "\nPredicted Patient Risk: " + risk
                    );

                    break;

                case 5:

                    appointmentMenu(input);

                    break;

                case 6:
                    if (system instanceof PatientRecordSystem) {
                         PatientRecordSystem prs =
                            (PatientRecordSystem) system;
                              prs.savePatientsToFile();
                               }
                                break;
                case 7:
                     System.out.println(
                                   "\nThank you for using Hospital Management System!"
                                     );
                                      break;
                default:

                    System.out.println(
                            "Invalid choice! Please try again."
                    );
            }

        } while (choice != 7);

        input.close();
    }


    // ==========================================
    // APPOINTMENT MANAGEMENT MENU
    // ==========================================

    public static void appointmentMenu(Scanner input) {

        Appointment appointment = null;

try {
    java.io.BufferedReader reader =
            new java.io.BufferedReader(
                    new java.io.FileReader("appointments.txt"));

    String line = reader.readLine();

    if (line != null) {
        String[] data = line.split(",");

        if (data.length == 6) {
            appointment = new Appointment(
                    data[0],
                    data[1],
                    data[2],
                    java.time.LocalDate.parse(data[3]),
                    data[4]
            );

            appointment.setStatus(data[5]);

            System.out.println(
                    "Appointment data loaded successfully!"
            );
        }
    }

    reader.close();

} catch (java.io.FileNotFoundException e) {

    System.out.println("No appointment data file found.");

} catch (java.io.IOException e) {

    System.out.println(
            "Error loading appointment data: " + e.getMessage()
    );
}
        int choice;

        do {

            System.out.println(
                    "\n========== APPOINTMENT MANAGEMENT =========="
            );

            System.out.println("1. Book New Appointment");
            System.out.println("2. View Appointment");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. Update Appointment Status");
            System.out.println("5. Appointment Reminder");
            System.out.println("6. Back to Main Menu");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {

                case 1:

                        appointment =
                            Appointment.createAppointment(input);
                            AppointmentReminder.checkReminder(appointment);

                    System.out.println(
                            "\nAppointment booked successfully!"
                    );

                    appointment.displayAppointment();
                    try {
    java.io.FileWriter writer =
            new java.io.FileWriter("appointments.txt");

    writer.write(appointment.toFileString()
            + System.lineSeparator());

    writer.close();

    System.out.println(
            "Appointment data saved successfully!"
    );

} catch (java.io.IOException e) {

    System.out.println(
            "Error saving appointment data: "
            + e.getMessage()
    );
}

                    break;


                case 2:

                    if (appointment == null) {

                        System.out.println(
                                "\nNo appointment available."
                        );

                    } else {

                        System.out.println(
                                "\n===== APPOINTMENT DETAILS ====="
                        );

                        appointment.displayAppointment();
                    }

                    break;


                case 3:

                    if (appointment == null) {

                        System.out.println(
                                "\nNo appointment available to cancel."
                        );

                    } else {

                        appointment.setStatus("Cancelled");

                        System.out.println(
                                "\nAppointment cancelled successfully!"
                        );
                    }

                    break;


                case 4:

                    if (appointment == null) {

                        System.out.println(
                                "\nNo appointment available."
                        );

                    } else {

                        System.out.println(
                                "\n1. Confirmed"
                        );

                        System.out.println(
                                "2. Completed"
                        );

                        System.out.println(
                                "3. Cancelled"
                        );

                        System.out.print(
                                "Select new status: "
                        );

                        int statusChoice =
                                input.nextInt();

                        input.nextLine();

                        switch (statusChoice) {

                            case 1:
                                appointment.setStatus("Confirmed");
                                break;

                            case 2:
                                appointment.setStatus("Completed");
                                break;

                            case 3:
                                 if (appointment == null) {
                                       System.out.println("\nNo appointment available to cancel.");
                                          } else {
                                                   appointment.setStatus("Cancelled");
                                                      System.out.println("\nAppointment cancelled successfully!");
                                                            appointment.displayAppointment();
                                                         }
                                                          break;

                            default:
                                System.out.println(
                                        "Invalid status!"
                                );
                                continue;
                        }

                        System.out.println(
                                "Appointment status updated successfully!"
                        );
                    }

                    break;


                case 5:
                    AppointmentReminderThread reminderThread =
                            new AppointmentReminderThread(appointment);
                    reminderThread.start();

                    break;
                    
                case 6:

                    System.out.println(
                            "\nReturning to Main Menu..."
                    );

                    break;

                default:

                    System.out.println(
                            "Invalid choice! Try again."
                    );
            }

        } while (choice != 5);
    }
}