package com.lopez.app.api.models;

import jakarta.persistence.*;

@Entity
@Table(name = "his_personas")
public class His_Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "peronas_id")
    @ManyToOne
    private Persona persona;
    private String nombre;
    private Integer edad;
}
