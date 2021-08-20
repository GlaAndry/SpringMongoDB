package com.glaAndry.esempio.esempioSpring2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@Document
public class Employee implements Serializable {

    @Id
    //String per default altrimenti non possiamo
    //generare ID diversi e si hanno problemi
    //nell'inserimento.
    private String Id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String phone;


    private String code;

    public Employee(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
