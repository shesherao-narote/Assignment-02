package com.springboot_jpa_crud.service;


import com.springboot_jpa_crud.dto.EmployeeDto;
import com.springboot_jpa_crud.entity.Employee;
import com.springboot_jpa_crud.repository.EmployeeRepository;
import com.springboot_jpa_crud.serviceImpl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Mock
    ModelMapper modelMapper;
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    void addEmployeeShouldAddEmployeeSuccessfully(){

    }
}
