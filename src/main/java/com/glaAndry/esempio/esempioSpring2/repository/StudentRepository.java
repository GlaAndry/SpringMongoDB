package com.glaAndry.esempio.esempioSpring2.repository;

import com.glaAndry.esempio.esempioSpring2.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

//<Classe, @ID tipologia>
public interface StudentRepository extends MongoRepository<Student, String> {
    //estendendo la classe MongoRepository accediamo a tutti i metodi
    //messi a disposizione da mongo per la ricerca, eliminazione, inserimento
    //delle collezioni all'interno del database.

    Optional<Student> findStudentByEmail();
}
