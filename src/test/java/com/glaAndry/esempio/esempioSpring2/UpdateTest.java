package com.glaAndry.esempio.esempioSpring2;

import com.glaAndry.esempio.esempioSpring2.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.glaAndry.esempio.esempioSpring2.model.Employee;
import org.springframework.util.Assert;

@SpringBootTest
public class UpdateTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    public void updateDocument(){

        String name, name2;
        String id, id2;

        //creazione dell'impiegato
        Employee employee = new Employee("Ale", "a@b2345", "123", "343");

        //aggiunta dell'impiegato nel DB
        employeeService.addEmployee(employee);
        name = employeeService.findEmployeeById(employee.getId()).getName();
        id = employeeService.findEmployeeById(employee.getId()).getId();

        //modifico l'impiegato e richiamo l'update
        employee.setName("Alessio2");
        employeeService.updateEmployee(employee);

        name2 = employeeService.findEmployeeById(employee.getId()).getName();
        id2 = employeeService.findEmployeeById(employee.getId()).getId();

        //assert su nomi diversi e stesso id
        Assert.isTrue(!name.equals(name2));
        Assert.isTrue(id.equals(id2));

        //eliminazione del documento dal db
        employeeService.deleteEmployee(id);

    }

}
