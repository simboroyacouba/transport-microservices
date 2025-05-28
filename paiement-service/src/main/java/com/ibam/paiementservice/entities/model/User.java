package com.ibam.paiementservice.entities.model;

import lombok.*;

import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {

    private Long id;

    private String nom;
    private Long prenom;
    private Long email;

    private LocalDateTime createdAt;
    private LocalDateTime editedAt;

}
