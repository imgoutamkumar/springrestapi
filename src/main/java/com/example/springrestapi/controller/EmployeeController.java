package com.example.springrestapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springrestapi.model.Department;
import com.example.springrestapi.model.Employee;
import com.example.springrestapi.repository.DepartmentRepository;
import com.example.springrestapi.repository.EmployeeRepository;
import com.example.springrestapi.request.EmployeeRequest;
import com.example.springrestapi.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService eService;

    @Autowired
    private EmployeeRepository eRepo;

    @Autowired
    private DepartmentRepository dRepo;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees(@RequestParam Integer pageNumber,
            @RequestParam Integer pageSize) {
        return new ResponseEntity<List<Employee>>(eService.getemployees(pageNumber, pageSize), HttpStatus.OK);
    }

    // example : localhost:8080/employees/2
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        return new ResponseEntity<Employee>(eService.getSingleEmployee(id), HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody EmployeeRequest eRequest) {

        Employee employee = new Employee(eRequest);
        employee = eRepo.save(employee);
        for (String s : eRequest.getDepartment()) {
            Department d = new Department();
            d.setName(s);
            d.setEmployee(employee);
            dRepo.save(d);
        }
        return new ResponseEntity<String>("Record saves Successfully", HttpStatus.CREATED);
        /*
         * Department dept = new Department();
         * dept.setName(eRequest.getDepartment());
         * dept = dRepo.save(dept);
         * 
         * Employee employee = new Employee(eRequest);
         * employee.setDepartment(dept);
         * eRepo.save(employee);
         * return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
         */

    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        return new ResponseEntity<Employee>(eService.updateEmployee(employee), HttpStatus.OK);
    }

    // example : localhost:8080/employees?id=2
    @DeleteMapping("/employees")
    public ResponseEntity<HttpStatus> deleteEmployee(@RequestParam Long id) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    /*
     * @GetMapping("/employees/{name}")
     * public ResponseEntity<List<Employee>> getEmployeeByDepartment(@PathVariable
     * String name) {
     * // return new
     * //
     * ResponseEntity<List<Employee>>(eRepo.findByDepartmentName(name),HttpStatus.OK
     * );
     * return new ResponseEntity<List<Employee>>(eRepo.getEmployeesByDeptName(name),
     * HttpStatus.OK);
     * }
     */

    /*
     * @GetMapping("/employees/filterByName")
     * public ResponseEntity<List<Employee>> getEmployeesByName(@RequestParam String
     * name) {
     * return new ResponseEntity<List<Employee>>(eService.gEmployeesByName(name),
     * HttpStatus.OK);
     * }
     * 
     * @GetMapping("/employees/filterByNameAndLocation")
     * public ResponseEntity<List<Employee>>
     * getEmployeesByNameAndLocation(@RequestParam String name,
     * 
     * @RequestParam String location) {
     * return new
     * ResponseEntity<List<Employee>>(eService.getEmployeesByNameAndLocation(name,
     * location),
     * HttpStatus.OK);
     * }
     * 
     * @GetMapping("/employees/filterByKeyword")
     * public ResponseEntity<List<Employee>> getEmployeesByKeyword(@RequestParam
     * String name) {
     * return new
     * ResponseEntity<List<Employee>>(eService.getEmployeesByKeyword(name),
     * HttpStatus.OK);
     * }
     * 
     * @GetMapping("/employees/{name}/{location}")
     * public ResponseEntity<List<Employee>> getEmployeesByOrLocation(@PathVariable
     * String name,
     * 
     * @PathVariable String location) {
     * return new
     * ResponseEntity<List<Employee>>(eService.getEmployeesByNameOrLocation(name,
     * location),
     * HttpStatus.OK);
     * }
     * 
     * @DeleteMapping("/employees/delete/{name}")
     * public ResponseEntity<String> deleteEmployeeByName(@PathVariable String name)
     * {
     * return new ResponseEntity<String>(eService.deleteByEmployeeName(name) +
     * "no. od records deleted",
     * HttpStatus.OK);
     * }
     */
}
