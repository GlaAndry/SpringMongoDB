package com.glaAndry.esempio.esempioSpring2.service;

import com.glaAndry.esempio.esempioSpring2.exception.UserNotFoundException;
import com.glaAndry.esempio.esempioSpring2.repository.EmployeeRepository;
import com.glaAndry.esempio.esempioSpring2.model.Employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee findEmployeeByID(long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User Not Found By ID:" + id));
    }

    public Employee addOrUpdateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(long id){
        employeeRepository.deleteEmployeeById(id);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

}
