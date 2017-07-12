package com.cgi.nhs.controller;

import com.cgi.nhs.domain.Patient;
import com.cgi.nhs.service.IPatientService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
/**
 * Created by rabia on 02/07/17.
 */
@Data
@Controller
@RequestMapping("nhs")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @GetMapping("patient")
    public ResponseEntity<Patient> getPatientById(@RequestParam("id") String id){
        Patient patient=patientService.getPatientById(Integer.parseInt(id));
        return new ResponseEntity<Patient>(patient, HttpStatus.OK);
    }

    @DeleteMapping("patient")
    public ResponseEntity<Void> deletePatient (@RequestParam("id") String id){
        patientService.deletePatient(Integer.parseInt(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("patient")
    public ResponseEntity<Void> createPatient(@RequestBody Patient patient, UriComponentsBuilder builder){
        boolean flag=patientService.createPatient(patient);
        if (flag==false){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/patient?id={id}").buildAndExpand(patient.getPatientId()).toUri());
    return new ResponseEntity<Void>(headers,HttpStatus.CREATED);
    }

    @PutMapping("patient")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        patientService.updatePatient(patient);
        return new ResponseEntity<Patient>(patient,HttpStatus.OK);
    }



    @GetMapping("all-patients")
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<List<Patient>>(patients,HttpStatus.OK);
    }
}
