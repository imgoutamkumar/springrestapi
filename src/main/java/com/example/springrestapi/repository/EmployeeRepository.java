package com.example.springrestapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.springrestapi.model.Employee;

@Repository // PagingAndSortingRepository<Employee, Long>,
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByName(String name);

    // select*from table where name="Rohit" and location = "India"
    List<Employee> findByNameAndLocation(String name, String location);

    // select*from table where name like "%ram%"
    List<Employee> findByNameContaining(String keyword, Sort sort);
    // List<Employee> findByNameLike(String "%"+keyword+"%");

    // select*from table where name="balram" or location ="india";
    @Query("FROM Employee WHERE name = :name OR location = :location")
    List<Employee> getEmployeesByNameAndLocation(String name, String location);

    @Query("DELETE FROM Employee WHERE name = :name")
    Integer deleteEmployeeByName(String name);
}