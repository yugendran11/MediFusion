import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AppointmentReminder {

    public static void checkReminder(Appointment appointment) {

        if (appointment == null) {
            System.out.println("No appointment available.");
            return;
        }

        LocalDate today = LocalDate.now();
        LocalDate appointmentDate = appointment.getDate();

        long days = ChronoUnit.DAYS.between(today, appointmentDate);

        if (days == 0) {
            System.out.println("Reminder: Appointment is today!");
        } 
        else if (days == 1) {
            System.out.println("Reminder: Appointment is tomorrow!");
        } 
        else if (days > 1) {
            System.out.println(
                "Reminder: Appointment is in " + days + " days."
            );
        } 
        else {
            System.out.println("Appointment date has passed.");
        }
    }
}