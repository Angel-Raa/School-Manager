package com.github.angel.raa.modules.persistence.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {
    @Serial
    private static final long serialVersionUID = 102918371L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(unique = true)
    private String name;
    private String surname;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String gender;
    private String specialization;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "address_id" , unique = true)
    private Address address;
}
