package ru.itis.springdemo.dto;

import lombok.*;
import ru.itis.springdemo.models.State;

@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String fullAbout;
    private String shortAbout;
    private String city;
    private String gender;
    private Integer age;
    private Integer countLikes;
    private String role;
    private State state;

    public boolean isAdmin() {
        return this.role.equals("admin");
    }
}
