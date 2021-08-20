package com.glaAndry.esempio.esempioSpring2;

import com.glaAndry.esempio.esempioSpring2.model.Student;
import com.glaAndry.esempio.esempioSpring2.repository.StudentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class EsempioSpring2Application {

    public static void main(String[] args) {
        SpringApplication.run(EsempioSpring2Application.class, args);
    }

    @Bean
    public CorsFilter corsFilter(){
        //Managing CORS -- Localhost 4200 (Angular) e localhosto 8080 altrimenti non
        //potrebbero comunicare.

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));

        corsConfiguration.setAllowedHeaders(Arrays.asList(
                "Origin",
                "Access-Control-Allow-Origin",
                "Content-Type",
                "Accept",
                "Authorization",
                "Origin, Accept",
                "X-Requested-With",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
        ));

        corsConfiguration.setExposedHeaders(Arrays.asList(
                "Origin",
                "Access-Control-Allow-Origin",
                "Content-Type",
                "Accept",
                "Authorization",
                "Access-Control-Allow-Credentials"
        ));

        corsConfiguration.setAllowedMethods(Arrays.asList(
                "GET",
                "POST",
                "PUT",
                "DELETE",
                "OPTIONS"
        ));

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    /*
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

            //usingMongoTemplateMethod(studentRepository, mongoTemplate, email, student);

            studentRepository.findStudentByEmail(email).ifPresentOrElse(s -> {
                //lo studente esiste
                System.out.println("Ci sono diverse email uguali:" + email);
            }, () -> {
                //altrimenti viene aggiunto
                studentRepository.insert(student);
            });
        };

        }
     */



    private void usingMongoTemplateMethod(StudentRepository studentRepository, MongoTemplate mongoTemplate, String email, Student student) {
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
    }
}
