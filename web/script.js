function showMessage(message) {
    alert(message);
}


// ===============================
// SAVE PATIENT
// ===============================

async function savePatient() {

    let patientId = document.getElementById("patientId").value.trim();
    let patientName = document.getElementById("patientName").value.trim();
    let age = document.getElementById("age").value.trim();
    let contact = document.getElementById("contact").value.trim();
    let disease = document.getElementById("disease").value.trim();
    let admissionDate = document.getElementById("admissionDate").value;

    let genderOption =
        document.querySelector('input[name="gender"]:checked');

    if (patientId === "") {
        alert("Please enter Patient ID.");
        return;
    }

    if (patientName === "") {
        alert("Please enter Patient Name.");
        return;
    }

    if (age === "") {
        alert("Please enter Age.");
        return;
    }

    if (genderOption === null) {
        alert("Please select Gender.");
        return;
    }

    if (contact === "") {
        alert("Please enter Contact Number.");
        return;
    }

    if (disease === "") {
        alert("Please enter Disease.");
        return;
    }

    if (admissionDate === "") {
        alert("Please select Admission Date.");
        return;
    }

    let gender = genderOption.value;

    let data =
        "patientId=" + encodeURIComponent(patientId) +
        "&name=" + encodeURIComponent(patientName) +
        "&age=" + encodeURIComponent(age) +
        "&gender=" + encodeURIComponent(gender) +
        "&contact=" + encodeURIComponent(contact) +
        "&disease=" + encodeURIComponent(disease) +
        "&admissionDate=" + encodeURIComponent(admissionDate);

    try {

        let response = await fetch(
            "http://127.0.0.1:8080/api/save-patient",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body: data
            }
        );

        let result = await response.text();

        alert(result);

    } catch (error) {
        alert("Backend connection failed!");
    }
}


// ===============================
// SHOW PATIENTS
// ===============================

async function showPatients() {

    try {

        const response = await fetch(
            "http://127.0.0.1:8080/api/patients"
        );

        const result = await response.text();

        document.getElementById("patientList").innerHTML =
            `
            <div class="form-card">
                <h2>📋 Patient List</h2>
                <pre>${result}</pre>
            </div>
            `;

    } catch (error) {

        alert("Backend connection failed!");
    }
}

// ===============================
// BOOK APPOINTMENT
// ===============================

async function bookAppointment() {

    let appointmentId =
        prompt("Enter Appointment ID:");

    if (!appointmentId) return;

    let patientId =
        prompt("Enter Patient ID:");

    if (!patientId) return;

    let doctorName =
        prompt("Enter Doctor Name:");

    if (!doctorName) return;

    let date =
        prompt("Enter Appointment Date (YYYY-MM-DD):");

    if (!date) return;

    let time =
        prompt("Enter Appointment Time:");

    if (!time) return;

    let data =
        "appointmentId=" + encodeURIComponent(appointmentId) +
        "&patientId=" + encodeURIComponent(patientId) +
        "&doctorName=" + encodeURIComponent(doctorName) +
        "&date=" + encodeURIComponent(date) +
        "&time=" + encodeURIComponent(time);

    try {

        let response = await fetch(
            "http://127.0.0.1:8080/api/save-appointment",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body: data
            }
        );

        let result = await response.text();

        alert(result);

    } catch (error) {

        alert("Backend connection failed!");
    }
}


// ===============================
// VIEW APPOINTMENTS
// ===============================

async function viewAppointments() {

    try {

        let response = await fetch(
            "http://127.0.0.1:8080/api/appointments"
        );

        let result = await response.text();

        alert(result);

    } catch (error) {

        alert("Backend connection failed!");
    }
}


// ===============================
// CANCEL APPOINTMENT
// ===============================

async function cancelAppointment() {

    let appointmentId =
        prompt("Enter Appointment ID to cancel:");

    if (!appointmentId) return;

    try {

        let response = await fetch(
            "http://127.0.0.1:8080/api/cancel-appointment",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body:
                    "appointmentId=" +
                    encodeURIComponent(appointmentId)
            }
        );

        let result = await response.text();

        alert(result);

    } catch (error) {

        alert("Backend connection failed!");
    }
}


// ===============================
// UPDATE APPOINTMENT STATUS
// ===============================

async function updateAppointmentStatus() {

    let appointmentId =
        prompt("Enter Appointment ID:");

    if (!appointmentId) return;

    let status =
        prompt(
            "Enter New Status:\nScheduled / Completed / Cancelled"
        );

    if (!status) return;

    try {

        let response = await fetch(
            "http://127.0.0.1:8080/api/update-appointment",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body:
                    "appointmentId=" +
                    encodeURIComponent(appointmentId) +
                    "&status=" +
                    encodeURIComponent(status)
            }
        );

        let result = await response.text();

        alert(result);

    } catch (error) {

        alert("Backend connection failed!");
    }
}


// ===============================
// AI RISK PREDICTION
// ===============================

async function predictRisk() {

    let age =
        prompt("Enter Patient Age:");

    if (!age) return;

    let disease =
        prompt("Enter Disease:");

    if (!disease) return;

    try {

        let response = await fetch(
            "http://localhost:8080/api/risk-prediction",
            {
                method: "POST",
                headers: {
                    "Content-Type":
                        "application/x-www-form-urlencoded"
                },
                body:
                    "age=" +
                    encodeURIComponent(age) +
                    "&disease=" +
                    encodeURIComponent(disease)
            }
        );

        let result = await response.text();

        alert(result);

    } catch (error) {

        alert("Backend connection failed!");
    }
}