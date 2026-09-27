# Hospital Patient Record Management System

A Java console project covering:
- **Unit 1**: OOP fundamentals — classes, objects, constructors, encapsulation
- **Unit 2**: Inheritance and interfaces — abstract class `Person`, subclasses `Patient`/`Doctor`, interface `MedicalRecordOperations`

## Folder Structure
```
HospitalManagementSystem/
├── src/
│   ├── Person.java                  (abstract parent class)
│   ├── Patient.java                 (inherits Person)
│   ├── Doctor.java                  (inherits Person)
│   ├── MedicalRecordOperations.java (interface)
│   ├── PatientRecordSystem.java     (implements interface, manages patient list)
│   └── HospitalManagementSystem.java (main class / entry point)
└── README.md
```

## How to Compile & Run

From inside the `src` folder:

```
```
java HospitalManagementSystem
```

## Program Flow
1. Run the program — you'll see a menu.
2. Choose **1** to add a new patient — you will be prompted to type in Patient ID, name, age, gender, contact number, disease, and admission date.
3. Choose **2** to view all patients you've entered.
4. Choose **3** to search a patient by ID.
5. Choose **4** to exit.

No patient data is hardcoded — every record comes from what you type in at runtime.
