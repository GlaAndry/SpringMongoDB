package com.glaAndry.esempio.esempioSpring2.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.glaAndry.esempio.esempioSpring2.model.Employee;

import java.util.Optional;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

    @Override
    Optional<Employee> findById(String s);
    Optional<Employee> findEmployeeByName(String s);
    void deleteEmployeeByName(String s);
    void deleteEmployeeById(long id);

    Optional<Employee> findEmployeeById(long id);
}
