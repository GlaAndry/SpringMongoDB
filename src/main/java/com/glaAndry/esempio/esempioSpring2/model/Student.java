package com.glaAndry.esempio.esempioSpring2.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data //getter and setter automatici tramite lombok
@Document //identifica la classe come collezione all'interno di MongoDB
public class Student implements Serializable {

    @Id
    private String Id;

    private String firstName;
    private String lastName;

    @Indexed(unique = true) //non possono esistere studenti con la stessa email.
    private String email;

    private Gender gender;
    private Address address;
    private List<String> fav_Sub;
    private BigDecimal totalSpentInBooks;
    private LocalDateTime createdAt;

    public Student(String firstName,
                   String lastName,
                   String email,
                   Gender gender,
                   Address address,
                   List<String> fav_Sub,
                   BigDecimal totalSpentInBooks,
                   LocalDateTime createdAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.fav_Sub = fav_Sub;
        this.totalSpentInBooks = totalSpentInBooks;
        this.createdAt = createdAt;
    }
}
