package com.example.springrestapi.model;

import com.example.springrestapi.request.EmployeeRequest;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_employee")
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name should not be null")
    private String name;

    @JoinColumn(name = "department_id")
    @OneToOne
    private Department department;

    public Employee(EmployeeRequest req) {
        this.name = req.getName();
    }
    /*
     * private Long age = 0L;
     * 
     * private String location;
     * 
     * @Email(message = "please enter the valid email address")
     * private String email;
     * 
     * @NotBlank(message = "department should not be null")
     * private String department;
     * 
     * @CreationTimestamp
     * 
     * @Column(name = "created_at", nullable = false, updatable = false)
     * private Date createdAt;
     * 
     * @UpdateTimestamp
     * 
     * @Column(name = "updated_at")
     * private Date updatedAt;
     */

}
