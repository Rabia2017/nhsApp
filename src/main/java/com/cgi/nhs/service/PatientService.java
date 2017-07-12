package com.cgi.nhs.service;

import com.cgi.nhs.repository.PatientRepository;
import com.cgi.nhs.domain.Patient;
import lombok.Data;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rabia on 02/07/17.
 */
@Service
@Data
public class PatientService implements IPatientService{
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient getPatientById(int patientId) {
        Patient patient = patientRepository.findOne(patientId);
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return IteratorUtils.toList(patientRepository.findAll().iterator());
    }
    @Override
    public synchronized boolean createPatient(Patient patient) {
        if (patientRepository.exists(patient.getPatientId()))
            return false;
        else{
            patientRepository.save(patient);
        return true;
        }
    }
    @Override
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }
    @Override
    public void deletePatient(int patientId) {
        patientRepository.delete(patientId);
    }
}
