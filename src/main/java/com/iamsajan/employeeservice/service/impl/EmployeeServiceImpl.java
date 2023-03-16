package com.iamsajan.employeeservice.service.impl;

import com.iamsajan.employeeservice.model.Employee;
import com.iamsajan.employeeservice.repository.EmployeeRepository;
import com.iamsajan.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Sajan K.C.
 * @Date March 14, 2023
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ResponseEntity<Employee> saveEmployee(Employee employee) {
        if (employee.getEmail() != null) {
            Optional<Employee> employoeeEmailOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
            if (employoeeEmailOptional.isPresent()) {
                throw new IllegalStateException("Employee with email : " + employee.getEmail() + " already exists.");
            } else {
                try {
                    employeeRepository.save(employee);
                    return new ResponseEntity<>(HttpStatus.CREATED);
                } catch (Exception e) {
                    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        } else {
            // email is required
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(employees, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(optionalEmployee.get(), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Employee> updateEmployee(Integer id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee oldEmployee = optionalEmployee.get();
            if (employee.getName() != null)
                oldEmployee.setName(employee.getName());
            if (employee.getEmail() != null)
                oldEmployee.setEmail(employee.getEmail());
            if (employee.getPhone() != null)
                oldEmployee.setPhone(employee.getPhone());
            if (employee.getDepartment() != null)
                oldEmployee.setDepartment(employee.getDepartment());
            Employee savedEmployee = employeeRepository.save(oldEmployee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEmployeeById(Integer id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + id + " does not exists.");
        } else {
            try {
                employeeRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
