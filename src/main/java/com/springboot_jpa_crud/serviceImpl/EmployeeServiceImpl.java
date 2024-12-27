package com.springboot_jpa_crud.serviceImpl;

import com.springboot_jpa_crud.dto.EmployeeDepartmentDTO;
import com.springboot_jpa_crud.dto.EmployeeDto;
import com.springboot_jpa_crud.entity.Department;
import com.springboot_jpa_crud.entity.Employee;
import com.springboot_jpa_crud.entity.Profile;
import com.springboot_jpa_crud.exception.ResourceNotFoundException;
import com.springboot_jpa_crud.repository.ProfileRepository;
import com.springboot_jpa_crud.repository.EmployeeRepository;
import com.springboot_jpa_crud.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProfileRepository departmentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public EmployeeDto getEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Employee", "Id", id));
       if (employee.getDepartment()!=null){
           System.out.println(employee.getDepartment().getName());
       }
        return modelMapper.map(employee,EmployeeDto.class);

    }

    @Override
    public List<EmployeeDto> getEmployees() {

        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }


    public EmployeeDepartmentDTO convertEntityToDTO(Employee employee){
        EmployeeDepartmentDTO employeeDepartmentDTO = new EmployeeDepartmentDTO();
        employeeDepartmentDTO = modelMapper.map(employee, EmployeeDepartmentDTO.class);
        return employeeDepartmentDTO;
    }


    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public void softDelete(Integer id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee", "Id", id));
        employee.setDeleted(true);
        employeeRepository.save(employee);
    }


    @Override
    public List<EmployeeDto> getActiveEmployees() {
        return employeeRepository.findNonDeletedEmployees().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto) {

        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));

        existingEmployee.setName(employeeDto.getName());
        existingEmployee.setEmail(employeeDto.getEmail());
        existingEmployee.setPassword(employeeDto.getPassword());

        Department department = modelMapper.map(employeeDto.getDepartment(), Department.class);
        existingEmployee.getDepartment().setName(employeeDto.getDepartment().getName());

        // Update the profiles (set the employee reference in each profile)
       List<Profile> profiles = employeeDto.getProfiles().stream().map(profileDTO -> {
           return modelMapper.map(profileDTO,Profile.class);
       }).toList();

        if (employeeDto.getProfiles() != null) {
            for (Profile profile : profiles) {
                profile.setEmployee(existingEmployee);
                existingEmployee.addProfile(profile);
            }
        }
        department.setEmployee(existingEmployee);

        Employee savedEmployee = employeeRepository.save(existingEmployee);
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }


    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {

        // Map DTO to entity
        Employee employee = modelMapper.map(employeeDto, Employee.class);

        if (employee.getProfiles() != null) {
            for (Profile profile : employee.getProfiles()) {
                profile.setEmployee(employee);
            }
        }
        Department department = employee.getDepartment();
        employee.setDepartment(department);
        department.setEmployee(employee);

        Employee savedEmployee = employeeRepository.save(employee);

        return modelMapper.map(savedEmployee, EmployeeDto.class);

    }
}
