package com.iamsajan.employeeservice.controller;

import com.iamsajan.employeeservice.model.Employee;
import com.iamsajan.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Sajan K.C.
 * @Date March 14, 2023
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<ResponseEntity<Employee>> getEmployeeById(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(value = "id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }

}
