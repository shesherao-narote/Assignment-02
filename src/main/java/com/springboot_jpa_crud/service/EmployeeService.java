package com.springboot_jpa_crud.service;

import com.springboot_jpa_crud.dto.EmployeeDepartmentDTO;
import com.springboot_jpa_crud.dto.EmployeeDto;
import com.springboot_jpa_crud.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployeeById(int id);
    List<EmployeeDto> getEmployees();
    void softDelete(Integer id);
    void deleteEmployeeById(int id);
    List<EmployeeDto> getActiveEmployees();
    EmployeeDto addEmployee(EmployeeDto employeeDto);
    EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto);

}
