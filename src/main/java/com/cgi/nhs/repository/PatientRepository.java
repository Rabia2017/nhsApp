package com.cgi.nhs.repository;

import com.cgi.nhs.domain.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by rabia on 02/07/17.
 */
@Repository
public interface PatientRepository extends CrudRepository<Patient, Integer> {

}
