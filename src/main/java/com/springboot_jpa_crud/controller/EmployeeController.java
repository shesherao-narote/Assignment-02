package com.springboot_jpa_crud.controller;

import com.springboot_jpa_crud.dto.ApiResponse;
import com.springboot_jpa_crud.dto.EmployeeDto;
import com.springboot_jpa_crud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping()
    public ResponseEntity<List<EmployeeDto>> getEmployees(){
        List<EmployeeDto> employees = employeeService.getEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/active")
    public ResponseEntity<List<EmployeeDto>> getActiveEmployees(){
        List<EmployeeDto> employees = employeeService.getActiveEmployees();
        return ResponseEntity.ok(employees);
    }


    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Integer employeeId){
        EmployeeDto savedEmployee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(savedEmployee);
    }


    @DeleteMapping("/soft/{id}")
    public ResponseEntity<ApiResponse> softDelete(@PathVariable Integer id){
        this.employeeService.softDelete(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Soft deleted successfully!",true),HttpStatus.OK);
    }


    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable Integer employeeId){
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully!",true),HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto){
        EmployeeDto updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        return ResponseEntity.ok(updatedEmployee);
    }
}
