package com.example.springrestapi.service;

import java.util.List;

import com.example.springrestapi.model.Employee;

public interface EmployeeService {
    List<Employee> getemployees(int pageNumber, int pageSize);

    Employee saveEmployee(Employee employee);

    Employee getSingleEmployee(Long id);

    void deleteEmployee(Long id);

    Employee updateEmployee(Employee employee);

    List<Employee> gEmployeesByName(String name);

    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    List<Employee> getEmployeesByKeyword(String name);

    List<Employee> getEmployeesByNameOrLocation(String name, String location);

    Integer deleteByEmployeeName(String name);
}