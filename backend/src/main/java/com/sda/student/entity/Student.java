package com.sda.student.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "STUDENTS")
public class Student {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 2, max = 20, message = "Username length must be between 5 and 20 characters")
//    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, underscores, periods, or dashes")
    @Column(name = "USER_NAME")
    private String username;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    @Column(name = "EMAIL")
    private String email;


    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, message = "Password must be at least 4 characters long")
//    @Pattern.List({@Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter"),
//                   @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
//                   @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")})
    @Column(name = "PASSWORD")
    private String password;

    //    @Min(value = 10, message = "Age must be at least 18")
//    @Max(value = 120, message = "Age must not exceed 120")
    @Column(name = "AGE")
    private int age;


    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}