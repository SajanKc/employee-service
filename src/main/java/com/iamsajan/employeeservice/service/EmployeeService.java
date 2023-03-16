package com.iamsajan.employeeservice.service;

import com.iamsajan.employeeservice.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Sajan K.C.
 * @Date March 14, 2023
 */
@Service
public interface EmployeeService {

    ResponseEntity<List<Employee>> getEmployees();

    ResponseEntity<Employee> saveEmployee(Employee employee);

    ResponseEntity<HttpStatus> deleteEmployeeById(Integer id);

    ResponseEntity<Employee> getEmployeeById(Integer id);

    ResponseEntity<Employee> updateEmployee(Integer id, Employee employee);
}
