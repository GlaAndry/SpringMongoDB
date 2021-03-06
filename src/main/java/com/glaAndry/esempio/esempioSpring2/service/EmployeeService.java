package com.glaAndry.esempio.esempioSpring2.service;

import com.glaAndry.esempio.esempioSpring2.exception.UserNotFoundException;
import com.glaAndry.esempio.esempioSpring2.repository.EmployeeRepository;
import com.glaAndry.esempio.esempioSpring2.model.Employee;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    @Autowired
    private final MongoTemplate mongoTemplate;

    public Employee findEmployeeById(String Id){
        return employeeRepository.findEmployeeById(Id)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found By ID:" + Id));
    }

    public Employee findEmployeeByName(String name){
        return employeeRepository.findEmployeeByName(name)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found By Name:" + name));
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.insert(employee);
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(String id){
        employeeRepository.deleteEmployeeById(id);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

}
