package com.cgi.nhs.service;

import com.cgi.nhs.domain.Patient;
import com.cgi.nhs.repository.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by rabia on 04/07/17.
 *
 */
public class PatientServiceTest {


    @Mock
    private PatientRepository patientRepository;

    @Mock
    private Patient patient;
    @Before
    public void setupMock(){
        MockitoAnnotations.initMocks(this);
     }

    @Test
    public void  shouldReturnPatient_whenGetPatientByIdIsCalled(){
        //Arrange
        when(patientRepository.findOne(1)).thenReturn(patient);
        //Act
        Patient patientRetunred = patientRepository.findOne(1);
        //Assert
        assertThat(patientRetunred,is(equalTo(patient)));
    }

    @Test
    public void  shouldReturnPatient_whenSavePatientIsCalled(){
        //Arrange
        when(patientRepository.save(patient)).thenReturn(patient);
        //Act
        Patient patientSaved = patientRepository.save(patient);
        //Assert
        assertThat(patientSaved,is(equalTo(patient)));
    }

    @Test
    public void shouldCallDeleteMethodOfPatientRepository_whenDeletePatientIsCalled(){
        //Arrange
        doNothing().when(patientRepository).delete(1);
        //Act
        patientRepository.delete(1);
        //Assert
        verify(patientRepository,times(1)).delete(1);
    }

    @Test
    public void shouldCallExistsMethodOfPatientRepository_whenSavePatientIsCalled(){
        //Arrange
        when(patient.getPatientId()).thenReturn(1);
        when(patientRepository.exists(1)).thenReturn(true);
        //Act
        Boolean patientExists = patientRepository.exists(1);
        //Assert
        assertThat(patientExists,is(equalTo(true)));
    }


}
