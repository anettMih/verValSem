package controller;

import exceptions.PatientException;
import model.Consultation;
import model.Patient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoctorControllerTest {

    public static DoctorController doctorController;

    @BeforeEach
    void setUp() {
        Repository repository = new Repository("FilePatients.txt", "FileConsultations.txt");
        doctorController = new DoctorController(repository);
    }

    @Test
    void getPatientList() {
       List<Patient> patients = doctorController.getPatientList();
       File myFile = new File("FilePatients.txt");

       Assertions.assertAll(
               "patients",
               () -> Assertions.assertTrue(myFile.exists()),
               () -> Assertions.assertEquals("1",patients.get(0).getPatient_ID()),
               () -> Assertions.assertEquals("Anna",patients.get(0).getName()),
               () -> Assertions.assertEquals("1234567891234",patients.get(0).getSSN()),
               () -> Assertions.assertEquals("address",patients.get(0).getAddress()),
               () -> Assertions.assertEquals(4,patients.get(0).getConsNum())
               );

    }

    @Test
    void invalidFile() {
        List<Patient> patients = doctorController.getPatientList();

        DoctorController doc = new DoctorController(new Repository("FilePatiesdnts.txt","FilePadtients.txt"));

        Assertions.assertThrows(FileNotFoundException.class, () -> {
            doc.getPatientList();
        });

    }



    @org.junit.jupiter.api.Test
    void getConsultationList() {
    }

    @org.junit.jupiter.api.Test
    void setConsulationList() {
    }

    @org.junit.jupiter.api.Test
    void getPatientBySSN() {
    }

    @org.junit.jupiter.api.Test
    void getConsByID() {
    }

    @org.junit.jupiter.api.Test
    void getRepository() {
    }

    @org.junit.jupiter.api.Test
    void addPatient() {
        Patient patient = new Patient();
        patient.setPatient_ID("1");
        patient.setName("Anna");
        patient.setSSN("1234567891234");
        patient.setAddress("address");
        patient.setConsNum(4);
        try {
            doctorController.addPatient(patient);
        } catch (PatientException e) {
            e.printStackTrace();
        }
        int  i = doctorController.getPatientBySSN("1234567891234");
        Assertions.assertEquals(0,i);
    }

    @Test
    @DisplayName("invalidName Patient")
    void addInvalidPatient() {
        Patient patient = new Patient();
        patient.setPatient_ID("1");
        patient.setName("");
        patient.setSSN("1234567891234");
        patient.setAddress("address");
        patient.setConsNum(4);

        Assertions.assertThrows(PatientException.class, () -> {
            doctorController.addPatient(patient);
        });

    }

    @Test
    void getPatientsWithDisease() {
        try {
           List<Patient> patients =  doctorController.getPatientsWithDisease("natha");
        } catch (PatientException e) {
            e.printStackTrace();
        }
//        for(Patient p : pati)
    }
}