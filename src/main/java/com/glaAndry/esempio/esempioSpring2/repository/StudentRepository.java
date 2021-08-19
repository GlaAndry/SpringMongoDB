package com.glaAndry.esempio.esempioSpring2.repository;

import com.glaAndry.esempio.esempioSpring2.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

//<Classe, @ID tipologia>
public interface StudentRepository extends MongoRepository<Student, String> {
    //estendendo la classe MongoRepository accediamo a tutti i metodi
    //messi a disposizione da mongo per la ricerca, eliminazione, inserimento
    //delle collezioni all'interno del database.

    Optional<Student> findStudentByEmail(String email); //L'implementazione è offerta direttamente da MongoRepository

//    @Query("") All'interno dell'annotazione è possibile andare a specificare una rawquery per mongodb
//    void test();
}
