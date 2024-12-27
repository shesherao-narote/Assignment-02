package com.springboot_jpa_crud.repository;

import com.springboot_jpa_crud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT e FROM Employee e WHERE e.isDeleted = false")
    List<Employee> findNonDeletedEmployees();

//    @Query("UPDATE Employee e SET e.is_deleted = true WHERE e.emp_id = :id")
//    void softDeleteById(@Param("id") Long id);

//    @Query("SELECT e FROM Employee e WHERE e.is_deleted = false")
//    List<Employee> findAllActive();
}
