import java.time.LocalDate;
import java.util.Scanner;

public class Appointment {

    private String appointmentId;
    private String patientId;
    private String doctorName;
    private LocalDate date;
    private String time;
    private String status;

    public Appointment(String appointmentId,
                       String patientId,
                       String doctorName,
                       LocalDate date,
                       String time) {

        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.status = "Scheduled";
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayAppointment() {

        System.out.println("-----------------------------------------");
        System.out.println("Appointment ID : " + appointmentId);
        System.out.println("Patient ID     : " + patientId);
        System.out.println("Doctor Name    : " + doctorName);
        System.out.println("Date           : " + date);
        System.out.println("Time           : " + time);
        System.out.println("Status         : " + status);
        System.out.println("-----------------------------------------");
    }

    public static Appointment createAppointment(Scanner input) {

        System.out.println("\n===== CREATE APPOINTMENT =====");

        System.out.print("Enter Appointment ID: ");
        String appointmentId = input.nextLine();

        System.out.print("Enter Patient ID: ");
        String patientId = input.nextLine();

        System.out.print("Enter Doctor Name: ");
        String doctorName = input.nextLine();

        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(input.nextLine());

        System.out.print("Enter Appointment Time: ");
        String time = input.nextLine();

        return new Appointment(
                appointmentId,
                patientId,
                doctorName,
                date,
                time
        );
    }

    public String toFileString() {

        return appointmentId + "," +
               patientId + "," +
               doctorName + "," +
               date + "," +
               time + "," +
               status;
    }
}