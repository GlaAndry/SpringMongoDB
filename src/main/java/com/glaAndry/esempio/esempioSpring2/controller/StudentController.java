package com.glaAndry.esempio.esempioSpring2.controller;

import com.glaAndry.esempio.esempioSpring2.model.Student;
import com.glaAndry.esempio.esempioSpring2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //fornisce la funzionalità alla classe di effettuare delle richieste HTTP da parte del client
@RequestMapping("api/v1/students") //specifca dell'url per l'API
@AllArgsConstructor //fornisce il costruttore della classe direttamente da spring, andando ad eliminare codice
public class StudentController {

    //Il controller comunica con il Service
    //per il livello di API

    private final StudentService studentService;

    @GetMapping //metodo get (esistenza di post/delete/update...)
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

}
