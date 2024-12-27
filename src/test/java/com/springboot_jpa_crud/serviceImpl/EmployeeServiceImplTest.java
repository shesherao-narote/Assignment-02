package com.springboot_jpa_crud.serviceImpl;

import com.springboot_jpa_crud.dto.DepartmentDto;
import com.springboot_jpa_crud.dto.EmployeeDto;
import com.springboot_jpa_crud.dto.ProfileDto;
import com.springboot_jpa_crud.entity.Department;
import com.springboot_jpa_crud.entity.Employee;
import com.springboot_jpa_crud.entity.Profile;
import com.springboot_jpa_crud.exception.ResourceNotFoundException;
import com.springboot_jpa_crud.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ProfileDto profileDto;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testGetEmployeeById_whenEmployeeExists_thenReturnsEmployeeDto() {

        int employeeId = 1;
        Employee mockEmployee = new Employee();
        mockEmployee.setEmp_id(employeeId);
        mockEmployee.setName("John Doe");

        Department mockDepartment = new Department();
        mockDepartment.setName("Engineering");
        mockEmployee.setDepartment(mockDepartment);

        EmployeeDto mockEmployeeDto = new EmployeeDto();
        mockEmployeeDto.setEmp_id(employeeId);
        mockEmployeeDto.setName("John Doe");

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(mockEmployee));
        when(modelMapper.map(mockEmployee, EmployeeDto.class)).thenReturn(mockEmployeeDto);

        EmployeeDto result = employeeService.getEmployeeById(employeeId);

        assertNotNull(result);
        assertEquals(employeeId, result.getEmp_id());
        assertEquals("John Doe", result.getName());

        verify(employeeRepository, times(1)).findById(employeeId);
        verify(modelMapper, times(1)).map(mockEmployee, EmployeeDto.class);
    }



    @Test
    void testGetEmployees_whenEmployeesExist_thenReturnEmployeeDtoList() {
        // Arrange
        List<Employee> mockEmployees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setEmp_id(1);
        employee1.setName("John Doe");
        mockEmployees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setEmp_id(2);
        employee2.setName("Jane Smith");
        mockEmployees.add(employee2);

        List<EmployeeDto> mockEmployeeDtos = new ArrayList<>();
        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setEmp_id(1);
        employeeDto1.setName("John Doe");
        mockEmployeeDtos.add(employeeDto1);

        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setEmp_id(2);
        employeeDto2.setName("Jane Smith");
        mockEmployeeDtos.add(employeeDto2);

        Mockito.when(employeeRepository.findAll()).thenReturn(mockEmployees);
        Mockito.when(modelMapper.map(employee1, EmployeeDto.class)).thenReturn(employeeDto1);
        Mockito.when(modelMapper.map(employee2, EmployeeDto.class)).thenReturn(employeeDto2);

        List<EmployeeDto> result = employeeService.getEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());

        Mockito.verify(employeeRepository, times(1)).findAll();
        Mockito.verify(modelMapper, times(1)).map(employee1, EmployeeDto.class);
        Mockito.verify(modelMapper, times(1)).map(employee2, EmployeeDto.class);
    }



    @Test
    void testDeleteEmployeeById_whenEmployeeExists_thenDeletesEmployee() {
        // Arrange
        int employeeId = 1;
        Mockito.doNothing().when(employeeRepository).deleteById(employeeId);

        // Act
        employeeService.deleteEmployeeById(employeeId);

        // Assert
        Mockito.verify(employeeRepository, times(1)).deleteById(employeeId);
    }


    @Test
    void testDeleteEmployeeById_whenEmployeeDoesNotExist_thenThrowsException() {

        int employeeId = 1;
        Mockito.doThrow(new EmptyResultDataAccessException(1))
                .when(employeeRepository).deleteById(employeeId);

        assertThrows(EmptyResultDataAccessException.class,
                () -> employeeService.deleteEmployeeById(employeeId));

        Mockito.verify(employeeRepository, times(1)).deleteById(employeeId);
    }



    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExist_thenThrowsException() {

        Integer employeeId = 1;
        EmployeeDto employeeDto = new EmployeeDto();

        Mockito.when(employeeRepository.findById(employeeId))
                .thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
                () -> employeeService.updateEmployee(employeeId, employeeDto));

        assertEquals("Employee not found with Id : " + employeeId, exception.getMessage());

        Mockito.verify(employeeRepository, times(1)).findById(employeeId);
        Mockito.verifyNoInteractions(modelMapper);
        Mockito.verifyNoMoreInteractions(employeeRepository);
    }
}