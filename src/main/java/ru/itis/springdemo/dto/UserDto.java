package ru.itis.springdemo.dto;

import lombok.*;
import ru.itis.springdemo.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private User.Role role;
    private User.BanState banState;
    private User.State state;

    public boolean isAdmin() {
        return this.banState.equals("admin");
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .fullAbout(user.getFullAbout())
                .shortAbout(user.getShortAbout())
                .city(user.getCity())
                .gender(user.getGender())
                .age(user.getAge())
                .countLikes(user.getCountLikes())
                .role(user.getRole())
                .banState(user.getBanState())
                .state(user.getState())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        if (users.isEmpty())
            return new ArrayList<>();
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
