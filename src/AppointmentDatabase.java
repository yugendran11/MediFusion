import java.sql.Connection;
import java.sql.PreparedStatement;

public class AppointmentDatabase {

    public static void saveAppointment(Appointment appointment) {

        String sql = "INSERT INTO appointments " +
                "(appointment_id, patient_id, doctor_name, appointment_date, appointment_time, status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, appointment.getAppointmentId());
            statement.setString(2, appointment.getPatientId());
            statement.setString(3, appointment.getDoctorName());
            statement.setDate(4,
                    java.sql.Date.valueOf(appointment.getDate()));
            statement.setString(5, appointment.getTime());
            statement.setString(6, appointment.getStatus());

            statement.executeUpdate();

            System.out.println("Appointment saved to MySQL successfully!");

        } catch (Exception e) {
            System.out.println("Failed to save appointment to MySQL.");
            e.printStackTrace();
        }
    }


    public static void cancelAppointment(String appointmentId) {

        String sql =
                "UPDATE appointments SET status = ? WHERE appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "Cancelled");
            statement.setString(2, appointmentId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Appointment cancelled in MySQL successfully!"
                );
            } else {
                System.out.println(
                        "Appointment not found in MySQL."
                );
            }

        } catch (Exception e) {
            System.out.println(
                    "Failed to cancel appointment in MySQL."
            );
            e.printStackTrace();
        }
    }


    public static void updateAppointmentStatus(
            String appointmentId,
            String status) {

        String sql =
                "UPDATE appointments SET status = ? WHERE appointment_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setString(2, appointmentId);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println(
                        "Appointment status updated in MySQL successfully!"
                );
            } else {
                System.out.println(
                        "Appointment not found in MySQL."
                );
            }

        } catch (Exception e) {
            System.out.println(
                    "Failed to update appointment status in MySQL."
            );
            e.printStackTrace();
        }
    }


    public static String getAllAppointments() {

        StringBuilder result = new StringBuilder();

        String sql = "SELECT * FROM appointments";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            var rs = statement.executeQuery();

            while (rs.next()) {

                result.append("Appointment ID: ")
                      .append(rs.getString("appointment_id"))
                      .append("\n");

                result.append("Patient ID: ")
                      .append(rs.getString("patient_id"))
                      .append("\n");

                result.append("Doctor: ")
                      .append(rs.getString("doctor_name"))
                      .append("\n");

                result.append("Date: ")
                      .append(rs.getString("appointment_date"))
                      .append("\n");

                result.append("Time: ")
                      .append(rs.getString("appointment_time"))
                      .append("\n");

                result.append("Status: ")
                      .append(rs.getString("status"))
                      .append("\n");

                result.append("-------------------------\n");
            }

        } catch (Exception e) {
            return "Failed to load appointments.";
        }

        return result.toString();
    }
}