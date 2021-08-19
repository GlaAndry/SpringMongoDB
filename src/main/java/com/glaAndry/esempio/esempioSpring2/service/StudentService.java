package com.glaAndry.esempio.esempioSpring2.service;

import com.glaAndry.esempio.esempioSpring2.model.Student;
import com.glaAndry.esempio.esempioSpring2.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    //Service comunica con il repository Layer
    //per offrire la funzionalità di ricerca
    //in MongoDB

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
