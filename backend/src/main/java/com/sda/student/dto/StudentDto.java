package com.sda.student.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentDto {


    private long id;

    @NotEmpty(message = "First name must not be empty")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last name must not be empty")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotEmpty(message = "Username must not be empty")
//    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters")
//    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, underscores, periods, or dashes")
    private String username;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    private String email;


    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, message = "Password must be at least 4 characters long")
//    @Pattern.List({@Pattern(regexp = ".*[a-z].*", message = "Password must contain at least one lowercase letter"),
//                   @Pattern(regexp = ".*[A-Z].*", message = "Password must contain at least one uppercase letter"),
//                   @Pattern(regexp = ".*\\d.*", message = "Password must contain at least one digit")})
    private String password;

    //    @Min(value = 10, message = "Age must be at least 18")
//    @Max(value = 120, message = "Age must not exceed 120")
    private int age;

    private List<ReviewDto> reviewDtoList;

    private Long categoryId;

    private String categoryName;

}
