package ru.itis.springdemo.models;

import lombok.*;
import ru.itis.springdemo.dto.UserDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(value = EnumType.STRING)
    private BanState banState;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @Enumerated(value = EnumType.STRING)
    private State state;

    private String confirmCode;

    @OneToMany(mappedBy = "author")
    private List<Article> articles;


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
                .banState(this.banState)
                .state(this.state)
                .build();
    }

    public boolean isBanned() {
        return this.banState == BanState.BANNED;
    }

    public boolean isActive() {
        return this.banState == BanState.ACTIVE;
    }

    public boolean isConfirmed() {
        return this.state == State.CONFIRMED;
    }

    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public enum Role {
        ADMIN("ADMIN"), USER("USER");
        private String role;

        Role(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    public enum BanState {
        ACTIVE("ACTIVE"), BANNED("BANNED");
        private String banState;

        BanState(String role) {
            this.banState = role;
        }

        public String getState() {
            return banState;
        }
    }

    public enum State {
        NOT_CONFIRMED("NOT_CONFIRMED"), CONFIRMED("CONFIRMED");
        private String state;

        State(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }
}

