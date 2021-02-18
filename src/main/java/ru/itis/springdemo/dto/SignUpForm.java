package ru.itis.springdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private String city;
    private Integer age;
}
