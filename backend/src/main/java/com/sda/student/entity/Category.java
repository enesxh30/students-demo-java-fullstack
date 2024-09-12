package com.sda.student.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME",unique = true)
    private String name;
    private String description;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Student> students;





}
