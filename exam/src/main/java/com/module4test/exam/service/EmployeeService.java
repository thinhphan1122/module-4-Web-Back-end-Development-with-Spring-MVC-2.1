package com.module4test.exam.service;

import com.module4test.exam.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();

    Optional<Employee> findById(long id);

    void save(Employee employee);

    void deleteById(Long id);
}
