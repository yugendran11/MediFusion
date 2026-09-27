import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class HospitalServer {

    public static void main(String[] args) throws Exception {

        HttpServer server =
                HttpServer.create(
                        new InetSocketAddress(8080),
                        0
                );

        // ===============================
        // API ROUTES
        // ===============================

        server.createContext(
                "/api",
                HospitalServer::handleRequest
        );

        server.createContext(
                "/api/patients",
                HospitalServer::getPatients
        );

        server.createContext(
                "/api/save-patient",
                HospitalServer::savePatient
        );

        server.createContext(
                "/api/save-appointment",
                HospitalServer::saveAppointment
        );

        server.createContext(
                "/api/appointments",
                HospitalServer::getAppointments
        );

        server.createContext(
                "/api/cancel-appointment",
                HospitalServer::cancelAppointment
        );

        server.createContext(
                "/api/update-appointment",
                HospitalServer::updateAppointment
        );

        server.createContext(
                "/api/risk-prediction",
                HospitalServer::riskPrediction
        );

        server.setExecutor(null);

        server.start();

        System.out.println(
                "Hospital Backend Server started!"
        );

        System.out.println(
                "Running at: http://localhost:8080"
        );
    }


    // ===============================
    // BASIC API
    // ===============================

    private static void handleRequest(
            HttpExchange exchange)
            throws IOException {

        String response =
                "Hospital Backend API is working!";

        sendResponse(exchange, response);
    }


    // ===============================
    // GET ALL PATIENTS
    // ===============================

    private static void getPatients(
            HttpExchange exchange)
            throws IOException {

        String response =
                PatientDatabase.getAllPatients();

        sendResponse(exchange, response);
    }


    // ===============================
    // SAVE PATIENT
    // ===============================

    private static void savePatient(
            HttpExchange exchange)
            throws IOException {

        String body =
                new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        String[] parts = body.split("&");

        String patientId =
                getValue(parts, "patientId");

        String name =
                getValue(parts, "name");

        String ageText =
                getValue(parts, "age");

        String gender =
                getValue(parts, "gender");

        String contact =
                getValue(parts, "contact");

        String disease =
                getValue(parts, "disease");

        String admissionDate =
                getValue(parts, "admissionDate");

        int age =
                Integer.parseInt(ageText);

        Patient patient =
                new Patient(
                        patientId,
                        name,
                        age,
                        gender,
                        contact,
                        disease,
                        admissionDate
                );

        PatientDatabase.savePatient(patient);

        sendResponse(
                exchange,
                "Patient saved successfully!"
        );
    }


    // ===============================
    // SAVE APPOINTMENT
    // ===============================

    private static void saveAppointment(
            HttpExchange exchange)
            throws IOException {

        String body =
                new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        String[] parts = body.split("&");

        String appointmentId =
                getValue(parts, "appointmentId");

        String patientId =
                getValue(parts, "patientId");

        String doctorName =
                getValue(parts, "doctorName");

        String dateText =
                getValue(parts, "date");

        String time =
                getValue(parts, "time");

        java.time.LocalDate date =
                java.time.LocalDate.parse(dateText);

        Appointment appointment =
                new Appointment(
                        appointmentId,
                        patientId,
                        doctorName,
                        date,
                        time
                );

        AppointmentDatabase.saveAppointment(
                appointment
        );

        sendResponse(
                exchange,
                "Appointment saved successfully!"
        );
    }


    // ===============================
    // GET ALL APPOINTMENTS
    // ===============================

    private static void getAppointments(
            HttpExchange exchange)
            throws IOException {

        String response =
                AppointmentDatabase.getAllAppointments();

        sendResponse(exchange, response);
    }


    // ===============================
    // CANCEL APPOINTMENT
    // ===============================

    private static void cancelAppointment(
            HttpExchange exchange)
            throws IOException {

        String body =
                new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        String[] parts = body.split("&");

        String appointmentId =
                getValue(parts, "appointmentId");

        AppointmentDatabase.cancelAppointment(
                appointmentId
        );

        sendResponse(
                exchange,
                "Appointment cancelled successfully!"
        );
    }


    // ===============================
    // UPDATE APPOINTMENT STATUS
    // ===============================

    private static void updateAppointment(
            HttpExchange exchange)
            throws IOException {

        String body =
                new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        String[] parts = body.split("&");

        String appointmentId =
                getValue(parts, "appointmentId");

        String status =
                getValue(parts, "status");

        AppointmentDatabase.updateAppointmentStatus(
                appointmentId,
                status
        );

        sendResponse(
                exchange,
                "Appointment status updated successfully!"
        );
    }


    // ===============================
    // AI RISK PREDICTION
    // ===============================

    private static void riskPrediction(
            HttpExchange exchange)
            throws IOException {

        String body =
                new String(
                        exchange.getRequestBody().readAllBytes(),
                        StandardCharsets.UTF_8
                );

        String[] parts = body.split("&");

        String ageText =
                getValue(parts, "age");

        String disease =
                getValue(parts, "disease");

        int age =
                Integer.parseInt(ageText);

        String risk;

        if (age >= 60) {

            risk = "High Risk";

        } else if (age >= 40) {

            risk = "Medium Risk";

        } else {

            risk = "Low Risk";
        }

        String response =
                "AI/ML Risk Prediction: " +
                risk +
                "\nDisease: " +
                disease;

        sendResponse(exchange, response);
    }


    // ===============================
    // GET FORM VALUE
    // ===============================

    private static String getValue(
            String[] parts,
            String key) {

        for (String part : parts) {

            String[] pair =
                    part.split("=", 2);

            if (pair.length == 2 &&
                    pair[0].equals(key)) {

                return URLDecoder.decode(
                        pair[1],
                        StandardCharsets.UTF_8
                );
            }
        }

        return "";
    }


    // ===============================
    // SEND RESPONSE
    // ===============================

    private static void sendResponse(
            HttpExchange exchange,
            String response)
            throws IOException {

        byte[] responseBytes =
                response.getBytes(
                        StandardCharsets.UTF_8
                );

        exchange.getResponseHeaders().set(
                "Access-Control-Allow-Origin",
                "*"
        );

        exchange.getResponseHeaders().set(
                "Content-Type",
                "text/plain; charset=UTF-8"
        );

        exchange.sendResponseHeaders(
                200,
                responseBytes.length
        );

        exchange.getResponseBody().write(
                responseBytes
        );

        exchange.getResponseBody().close();
    }
}