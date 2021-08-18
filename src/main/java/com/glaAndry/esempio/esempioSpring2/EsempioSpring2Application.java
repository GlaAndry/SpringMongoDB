package com.glaAndry.esempio.esempioSpring2;

import com.glaAndry.esempio.esempioSpring2.entity.Address;
import com.glaAndry.esempio.esempioSpring2.entity.Gender;
import com.glaAndry.esempio.esempioSpring2.entity.Student;
import com.glaAndry.esempio.esempioSpring2.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class EsempioSpring2Application {

    public static void main(String[] args) {
        SpringApplication.run(EsempioSpring2Application.class, args);
    }

    @Bean
    CommandLineRunner runner(StudentRepository studentRepository, MongoTemplate mongoTemplate) {

        //Repository per query semplici
        //Mongo template per query custom più complesse

        return args -> {
            String email = "kakak";
            Address address = new Address(
                    "England",
                    "London",
                    "00036"
            );
            Student student = new Student(
                    "Alessio",
                    "Mazzola",
                    email,
                    Gender.MALE,
                    address,
                    List.of("Cop"),
                    BigDecimal.TEN,
                    LocalDateTime.now()
			);

            //Query personalizzate
            Query query = new Query();
            query.addCriteria(Criteria.where("email").is(email));
            List<Student> students = mongoTemplate.find(query, Student.class);

            //query di inserimento ed eliminazione dal db
            //direttamente fornite da mongo.
            if(students.isEmpty()){
                //se non ci sono duplicati aggiungiamo
                studentRepository.insert(student);
            } else {
                System.out.println("Ci sono diverse email uguali:" + email);
            }

        };


    }
}
