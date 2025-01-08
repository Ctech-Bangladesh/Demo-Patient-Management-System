package com.pms.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "patients")
public class Patient {

    @Id
    private String id;
    private String name;
    private String email;
    private int age;

}
