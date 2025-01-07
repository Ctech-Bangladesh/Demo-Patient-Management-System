package com.pms.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import lombok.ToString;
//import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
//@Builder

//@Entity
@Document(collection = "patients")
public class Patient {
    
    //@GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Id
    private String id;
    private String name;
	private String email;
    private int age;

}
