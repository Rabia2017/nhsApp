package com.cgi.nhs;

import com.cgi.nhs.controller.PatientController;
import com.cgi.nhs.repository.PatientRepository;
import com.cgi.nhs.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by rabia on 04/07/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class NHSApplicationTest {

    @Autowired
    private PatientController patientController;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;


    @Test
    public void contextLoads() throws Exception{
        assertThat(patientController).isNotNull();
        assertThat(patientService).isNotNull();
        assertThat(patientRepository).isNotNull();
    }
}
