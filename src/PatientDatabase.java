import java.sql.Connection;
import java.sql.PreparedStatement;

public class PatientDatabase {

    public static void savePatient(Patient patient) {

        String sql = "INSERT INTO patients " +
                "(patient_id, name, age, gender, contact_number, disease, admission_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getPatientId());
            statement.setString(2, patient.getName());
            statement.setInt(3, patient.getAge());
            statement.setString(4, patient.getGender());
            statement.setString(5, patient.getContactNumber());
            statement.setString(6, patient.getDisease());
            statement.setString(7, patient.getAdmissionDate());

            statement.executeUpdate();

            System.out.println("Patient saved to MySQL successfully!");

        } catch (Exception e) {

            System.out.println("Failed to save patient to MySQL.");
            e.printStackTrace();
        }
    }
    public static Patient searchPatient(String patientId) {

    String sql = "SELECT * FROM patients WHERE patient_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, patientId);

        var result = statement.executeQuery();

        if (result.next()) {
            return new Patient(
                    result.getString("patient_id"),
                    result.getString("name"),
                    result.getInt("age"),
                    result.getString("gender"),
                    result.getString("contact_number"),
                    result.getString("disease"),
                    result.getString("admission_date")
            );
        }

    } catch (Exception e) {
        System.out.println("Failed to search patient from MySQL.");
        e.printStackTrace();
    }

    return null;
}
public static String getAllPatients() {

    StringBuilder result = new StringBuilder();

    String sql = "SELECT * FROM patients";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        var rs = statement.executeQuery();

        while (rs.next()) {

            result.append("Patient ID: ")
                  .append(rs.getString("patient_id"))
                  .append("\n");

            result.append("Name: ")
                  .append(rs.getString("name"))
                  .append("\n");

            result.append("Age: ")
                  .append(rs.getInt("age"))
                  .append("\n");

            result.append("Gender: ")
                  .append(rs.getString("gender"))
                  .append("\n");

            result.append("Disease: ")
                  .append(rs.getString("disease"))
                  .append("\n");

            result.append("-------------------------\n");
        }

    } catch (Exception e) {
        return "Failed to load patients.";
    }

    return result.toString();
}
}