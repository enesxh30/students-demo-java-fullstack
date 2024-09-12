package com.sda.student.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private long id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotEmpty(message = "Description must not be empty")
    @Size(min = 2, max = 1050, message = "Description length must be between 5 and 1050 characters")
//    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Description can only contain letters, numbers, underscores, periods, or dashes")
    private String description;

//    private StudentDto studentDto;

}
