package com.example.demo.controller;

import com.example.demo.exceptions.EmployeeNotFoundException;
import com.example.demo.model.Employeee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin( "*" )
@RestController
public class EmployeeController {

    private EmployeeRepo employeeRepo;

    public EmployeeController( EmployeeRepo employeeRepo ) {
        this.employeeRepo = employeeRepo;
    }

    @GetMapping( "/employees" )
    ResponseEntity<List<Employeee>> getAllEmployees() {
        final List<Employeee> employees = employeeRepo.findAll();
        return new ResponseEntity<>( employees, HttpStatus.OK );
    }

    @PostMapping( "/employees" )
    Employeee addNewEmployee( @RequestBody Employeee newEmployee ) {
        return employeeRepo.save( newEmployee );
    }

    @GetMapping( "/employees/{id}" )
    Employeee getEmployee( @PathVariable Long id ) {
        return employeeRepo.findById( id )
                .orElseThrow( () -> new EmployeeNotFoundException( id ) );
    }

    @PutMapping( "employees/{id}" )
    Employeee modifyEmployee( @PathVariable Long id,
            @RequestBody Employeee newEmployee ) {

        return employeeRepo.findById( id )
                .map( existingEmployee -> {
                    existingEmployee.setName( newEmployee.getName() );
                    existingEmployee.setRole( newEmployee.getRole() );
                    return employeeRepo.save( existingEmployee );
                } )
                .orElseGet( () -> {
                    newEmployee.setId( id );
                    return employeeRepo.save( newEmployee );
                } );

    }

    @DeleteMapping( "/employees/{id}" )
    void deleteEmployee( @PathVariable Long id ) {
        employeeRepo.deleteById( id );
    }

}
