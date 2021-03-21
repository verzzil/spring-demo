package ru.itis.springdemo.models;

import lombok.*;
import ru.itis.springdemo.dto.UserDto;

import javax.persistence.*;

@EqualsAndHashCode
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String fullAbout;
    private String shortAbout;
    private String city;
    private String gender;
    private Integer age;
    private Integer countLikes;
    private String email;
    private String hashPassword;
    private String role;

    @Enumerated(value = EnumType.STRING)
    private State state;
    private String confirmCode;

    public UserDto toUserDto() {
        return UserDto.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .age(this.age)
                .countLikes(this.countLikes)
                .city(this.city)
                .fullAbout(this.fullAbout)
                .shortAbout(this.shortAbout)
                .gender(this.gender)
                .id(this.id)
                .role(this.role)
                .state(this.state)
                .build();
    }
}

