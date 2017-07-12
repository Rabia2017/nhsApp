package com.cgi.nhs.controller;

import com.cgi.nhs.domain.Patient;
import com.cgi.nhs.service.IPatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.hamcrest.Matchers.*;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rabia on 04/07/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IPatientService patientService;

    private Patient patient;

    @Mock
    ResponseEntity<Patient> responseEntity;

    @Autowired
    @InjectMocks
    private PatientController patientController;


    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(patientController)
                .build();
        patient = new Patient();
        patient.setPatientId(1);
    }

    @Test
    public void shouldReturnPatient_whenGetPatientByIdIsCalled() throws Exception {

        doReturn(patient).when(patientService).getPatientById(1);
        //Act and Compare
        MvcResult result = this.mockMvc.perform(get("/nhs/patient").param("id", "1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
//                .andDo(print())
                .andExpect(jsonPath("$.patientId").value(patient.getPatientId()))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void shouldReturnVoid_whenDeletePatientByIsIsCalled() throws Exception{
       //Arrange
        doNothing().when(patientService).deletePatient(1);

        //Act and Compare
        this.mockMvc.perform(delete("/nhs/patient").param("id","1")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isNoContent());

        verify(patientService,atMost(1)).deletePatient(1);
    }

    @Test
    public void shouldReturnPatients_whenGetAllPatientsIsCalled() throws Exception{
        //Arrange
        List<Patient> patients = asList(new Patient(1,"Alpha"));
        doReturn(patients).when(patientService).getAllPatients();

        //Act and Compare
        this.mockMvc.perform(get("/nhs/all-patients")
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].patientId",is(1)))
                 .andExpect(jsonPath("$[0].name",is("Alpha")))
                .andReturn();
        verify(patientService,atMost(1)).getAllPatients();
    }


    @Autowired
    private ObjectMapper mapper;

    @Test
    public void shouldReturnPatient_whenUpdatePatientIsCalled() throws Exception {
        //Arrange
        doNothing().when(patientService).updatePatient(patient);
        String json = mapper.writeValueAsString(patient);

        this.mockMvc.perform(put("/nhs/patient")
                .contentType(APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patientId").value(patient.getPatientId()))
                .andReturn();

        verify(patientService,atMost(1)).updatePatient(patient);
    }



}
