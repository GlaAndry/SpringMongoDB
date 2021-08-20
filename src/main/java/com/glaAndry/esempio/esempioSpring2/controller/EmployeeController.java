package com.glaAndry.esempio.esempioSpring2.controller;

import com.glaAndry.esempio.esempioSpring2.service.EmployeeService;
import com.glaAndry.esempio.esempioSpring2.model.Employee;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //fornisce la funzionalità alla classe di effettuare delle richieste HTTP da parte del client
@RequestMapping("api/v1/employee") //specifca dell'url per l'API
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/findAll") //Path url per richiamare la restAPI
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List<Employee> employees = employeeService.findAllEmployees();
        //Ritorno la lista di employees ed anche lo stato della richiesta
        //HTTP, in questo caso OK
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/findById/{id}") //Id è una variabile di Path che viene presa dinamicamente
    //dal metodo tramite @PathVariable
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") String id){
        Employee employees = employeeService.findEmployeeByID(id);
        //Ritorno employees con ID matched ed anche lo stato della richiesta
        //HTTP, in questo caso OK
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //Nomi del mapping devono necessariamente differire, altrimenti
    //la chiamata risulta ambigua e si ha una eccezione da spring
    @GetMapping("/findByName/{name}") //Id è una variabile di Path che viene presa dinamicamente
    //dal metodo tramite @PathVariable
    public ResponseEntity<Employee> getEmployeeByName(@PathVariable("name") String name){
        Employee employees = employeeService.findEmployeeByName(name);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping("/addEmployee")
    //Possiamo accedere all'Employee tramite il Body della richiesta HTTP
    //Accettando come input dati in formato JSON. Test effettuato tramite Postman
    //andando ad effettuare la POST sulla RestAPI esposta
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addOrUpdateEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/updateEmployee")
    //Possiamo accedere all'Employee tramite il Body della richiesta HTTP
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        Employee newEmployee = employeeService.addOrUpdateEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    //Possiamo accedere all'Employee tramite il Body della richiesta HTTP
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") String id){
        employeeService.deleteEmployee(id);
        //ritorno solamente lo stato della richiesta in quanto
        //il metodo delete ritorna VOID
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
