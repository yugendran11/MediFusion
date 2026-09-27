import javax.swing.*;
import java.awt.*;

public class HospitalGUI extends JFrame {
private PatientRecordSystem patientSystem =
        new PatientRecordSystem();
        private GenericRepository<Appointment> appointmentRepository =
        new GenericRepository<>();
    public HospitalGUI() {

        setTitle("Hospital Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(7, 1, 10, 10));

        JLabel title = new JLabel(
                "Hospital Management System",
                SwingConstants.CENTER
        );

        JButton addPatientButton =
                new JButton("Add New Patient");
                addPatientButton.addActionListener(e -> {

    String patientId = JOptionPane.showInputDialog(
            this, "Enter Patient ID:"
    );
    Patient databasePatient =
        PatientDatabase.searchPatient(patientId);

if (databasePatient != null) {
    JOptionPane.showMessageDialog(
            this,
            "Patient found in MySQL!\n\n" +
            "Patient ID: " + databasePatient.getPatientId() +
            "\nName: " + databasePatient.getName() +
            "\nAge: " + databasePatient.getAge() +
            "\nDisease: " + databasePatient.getDisease()
    );
    return;
}

    String name = JOptionPane.showInputDialog(
            this, "Enter Patient Name:"
    );


    String ageText = JOptionPane.showInputDialog(
            this, "Enter Age:"
    );

    String gender = JOptionPane.showInputDialog(
            this, "Enter Gender:"
    );

    String contact = JOptionPane.showInputDialog(
            this, "Enter Contact Number:"
    );

    String disease = JOptionPane.showInputDialog(
            this, "Enter Disease:"
    );

    String admissionDate = JOptionPane.showInputDialog(
            this, "Enter Admission Date:"
    );

    try {

        int age = Integer.parseInt(ageText);

        if (age <= 0 || age > 120) {
            throw new InvalidAgeException("Invalid age!");
        }

        Patient patient = new Patient(
                patientId,
                name,
                age,
                gender,
                contact,
                disease,
                admissionDate
        );
        patientSystem.addPatientFromGUI(patient);
        PatientDatabase.savePatient(patient);

        JOptionPane.showMessageDialog(
                this,
                "Patient added successfully!"
        );

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Age must be a number!"
        );

    } catch (InvalidAgeException ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage()
        );
    }
});

     

JButton displayPatientButton =
        new JButton("Display All Patients");

        displayPatientButton.addActionListener(e -> {

    String patients =
            patientSystem.getPatientsForGUI();

    JTextArea area = new JTextArea(patients);

    area.setEditable(false);

    JScrollPane scrollPane =
            new JScrollPane(area);

    scrollPane.setPreferredSize(
            new java.awt.Dimension(450, 300)
    );

    JOptionPane.showMessageDialog(
            this,
            scrollPane,
            "All Patients",
            JOptionPane.INFORMATION_MESSAGE
    );

});


        JButton searchPatientButton =
                new JButton("Search Patient");
                searchPatientButton.addActionListener(e -> {

    String patientId = JOptionPane.showInputDialog(
            this,
            "Enter Patient ID:"
    );

    if (patientId == null || patientId.trim().isEmpty()) {
        return;
    }

    String result =
            patientSystem.searchPatientForGUI(patientId);

    JOptionPane.showMessageDialog(
            this,
            result,
            "Search Result",
            JOptionPane.INFORMATION_MESSAGE
    );
});
JButton aiButton =
        new JButton("AI Risk Prediction");

aiButton.addActionListener(e -> {

    String ageText = JOptionPane.showInputDialog(
            this,
            "Enter Patient Age:"
    );

    if (ageText == null) {
        return;
    }

    try {

        int age = Integer.parseInt(ageText);

        String disease = JOptionPane.showInputDialog(
                this,
                "Enter Disease:"
        );

        if (disease == null) {
            return;
        }

        String risk;

        if (age >= 60) {
            risk = "High Risk";
        } else if (age >= 40) {
            risk = "Medium Risk";
        } else {
            risk = "Low Risk";
        }

        JOptionPane.showMessageDialog(
                this,
                "AI/ML Risk Prediction: " + risk,
                "Patient Risk Prediction",
                JOptionPane.INFORMATION_MESSAGE
        );

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Age must be a number!"
        );
    }
});
        JButton appointmentButton =
        new JButton("Appointment Management");

appointmentButton.addActionListener(e -> {

    String[] options = {
            "Book New Appointment",
            "View Appointment",
            "Cancel Appointment",
            "Update Appointment Status",
            "Appointment Reminder"
    };

    String choice = (String) JOptionPane.showInputDialog(
            this,
            "Select Appointment Operation:",
            "Appointment Management",
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
    );

    if (choice == null) {
    return;
}

if (choice.equals("Book New Appointment")) {

    String appointmentId = JOptionPane.showInputDialog(
            this,
            "Enter Appointment ID:"
    );

    String patientId = JOptionPane.showInputDialog(
            this,
            "Enter Patient ID:"
    );

    String doctorName = JOptionPane.showInputDialog(
            this,
            "Enter Doctor Name:"
    );

    String date = JOptionPane.showInputDialog(
            this,
            "Enter Appointment Date (YYYY-MM-DD):"
    );

    String time = JOptionPane.showInputDialog(
            this,
            "Enter Appointment Time:"
    );

    try {

    java.time.LocalDate appointmentDate =
            java.time.LocalDate.parse(date);

    Appointment appointment = new Appointment(
            appointmentId,
            patientId,
            doctorName,
            appointmentDate,
            time
    );
    appointmentRepository.add(appointment);
    AppointmentDatabase.saveAppointment(appointment);

    JOptionPane.showMessageDialog(
            this,
            "Appointment booked successfully!"
    );

} catch (Exception ex) {

    JOptionPane.showMessageDialog(
            this,
            "Invalid appointment date!"
    );
}
}
if (choice.equals("View Appointment")) {

    if (appointmentRepository.size() == 0) {

        JOptionPane.showMessageDialog(
                this,
                "No appointments found."
        );

    } else {

        StringBuilder result = new StringBuilder();

        for (Appointment appointment : appointmentRepository.getAll()) {

            result.append("Appointment ID : ")
                  .append(appointment.getAppointmentId())
                  .append("\n");

            result.append("Patient ID     : ")
                  .append(appointment.getPatientId())
                  .append("\n");

            result.append("Doctor Name    : ")
                  .append(appointment.getDoctorName())
                  .append("\n");

            result.append("Date           : ")
                  .append(appointment.getDate())
                  .append("\n");

            result.append("Time           : ")
                  .append(appointment.getTime())
                  .append("\n");

            result.append("Status         : ")
                  .append(appointment.getStatus())
                  .append("\n");

            result.append("----------------------------\n");
        }

        JTextArea area = new JTextArea(result.toString());
        area.setEditable(false);

        JOptionPane.showMessageDialog(
                this,
                new JScrollPane(area),
                "All Appointments",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
if (choice.equals("Cancel Appointment")) {

    String appointmentId = JOptionPane.showInputDialog(
            this,
            "Enter Appointment ID to cancel:"
    );

    if (appointmentId == null || appointmentId.trim().isEmpty()) {
        return;
    }

    boolean found = false;

    for (Appointment appointment : appointmentRepository.getAll()) {

        if (appointment.getAppointmentId()
                .equalsIgnoreCase(appointmentId.trim())) {

            appointment.setStatus("Cancelled");
            AppointmentDatabase.cancelAppointment(appointmentId);
            found = true;
            break;
        }
    }

    if (found) {

        JOptionPane.showMessageDialog(
                this,
                "Appointment cancelled successfully!"
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Appointment not found."
        );
    }
}
if (choice.equals("Appointment Reminder")) {

    String appointmentId = JOptionPane.showInputDialog(
            this,
            "Enter Appointment ID:"
    );

    if (appointmentId == null || appointmentId.trim().isEmpty()) {
        return;
    }

    boolean found = false;

    for (Appointment appointment : appointmentRepository.getAll()) {

        if (appointment.getAppointmentId()
                .equalsIgnoreCase(appointmentId.trim())) {

            AppointmentReminderThread reminderThread =
                    new AppointmentReminderThread(appointment);

            reminderThread.start();

            found = true;
            break;
        }
    }

    if (!found) {
        JOptionPane.showMessageDialog(
                this,
                "Appointment not found."
        );
    }
}
    if (choice.equals("Update Appointment Status")) {

    String appointmentId = JOptionPane.showInputDialog(
            this,
            "Enter Appointment ID:"
    );

    if (appointmentId == null || appointmentId.trim().isEmpty()) {
        return;
    }

    String[] statuses = {
            "Scheduled",
            "Completed",
            "Cancelled"
    };

    String newStatus = (String) JOptionPane.showInputDialog(
            this,
            "Select New Status:",
            "Update Status",
            JOptionPane.QUESTION_MESSAGE,
            null,
            statuses,
            statuses[0]
    );

    if (newStatus == null) {
        return;
    }

    boolean found = false;

    for (Appointment appointment : appointmentRepository.getAll()) {

        if (appointment.getAppointmentId()
                .equalsIgnoreCase(appointmentId.trim())) {

            appointment.setStatus(newStatus);
            AppointmentDatabase.updateAppointmentStatus(
        appointmentId,
        newStatus
);
            found = true;
            break;
        }
    }

    if (found) {

        JOptionPane.showMessageDialog(
                this,
                "Appointment status updated successfully!"
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "Appointment not found."
        );
    }
    }
});

        JButton exitButton =
                new JButton("Exit");

        add(title);
        add(addPatientButton);
        add(displayPatientButton);
        add(searchPatientButton);
        add(appointmentButton);
        add(aiButton);
        add(exitButton);

        exitButton.addActionListener(e -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            HospitalGUI gui = new HospitalGUI();
            gui.setVisible(true);
        });
    }
}