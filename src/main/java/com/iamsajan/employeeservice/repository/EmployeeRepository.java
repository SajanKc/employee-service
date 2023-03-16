package com.iamsajan.employeeservice.repository;

import com.iamsajan.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author Sajan K.C.
 * @Date March 14, 2023
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByEmail(String email);
}
