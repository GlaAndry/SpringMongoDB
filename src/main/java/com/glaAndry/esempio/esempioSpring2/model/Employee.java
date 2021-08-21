package com.glaAndry.esempio.esempioSpring2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Data
@Document
public class Employee implements Serializable {


    //String per default altrimenti non possiamo
    //generare ID diversi e si hanno problemi
    //nell'inserimento.
    @Id
    private String id;
    private String name;

    @Indexed(unique = true)
    private String email;
    private String phone;


    private String code;

    public Employee(String name, String email, String phone, String code) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.code = code;
    }
}
