package com.cgi.nhs.service;

import com.cgi.nhs.domain.Patient;

import java.util.List;

/**
 * Created by rabia on 02/07/17.
 */
public interface IPatientService {
    List<Patient> getAllPatients();
    Patient getPatientById(int patientId);
    boolean createPatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(int patientId);
}
