package ru.itis.springdemo.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.UserDao;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.models.State;
import ru.itis.springdemo.models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserDao usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> findAll() {
        return usersRepository.findAll().stream()
                .map(User::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findByConfirmCode(String code) {
        Optional<User> user = findUserByConfirmCode(code);
        return Optional.ofNullable(user.get().toUserDto());
    }

    @Override
    public void confirmUser(String code) {
        Optional<User> user = findUserByConfirmCode(code);
        user.ifPresent(user1 -> usersRepository.confirmUser(user1.getId(), State.CONFIRMED.getState()));
    }

    @Override
    public UserDto findById(Integer id) {
        return usersRepository.getOne(id).toUserDto();
    }

    @Override
    public void updatePhoto(String photoName, Integer id) {

    }

    @Override
    public void updateFirstAndLastNameAndCity(String firstName, String lastName, String city, Integer id) {

    }

    @Override
    public void updatePassword(String pass, Integer id) {

    }

    @Override
    public void like(Integer fromUserId, Integer toUserId, Integer countLikes) {

    }

    @Override
    public List<Integer> likedUsers(Integer currentSessionId) {
        return null;
    }

    @Override
    public Optional<List<String>> getNamesWhereLiked(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<UserDto>> getChattingUsers(Integer id) {
        return Optional.empty();
    }

    @Override
    public String getNameByUserId(Integer id) {
        return null;
    }

    @Override
    public boolean checkPass(Integer userId, String pass) {
        return false;
    }

    @Override
    public void updateShortAndFullAbout(Integer id, String shortAbout, String fullAbout) {

    }

    @Override
    public Optional<List<UserDto>> findUsersByFilter(String gender, String fromAge, String toAge, String city) {
        return Optional.empty();
    }

    private Optional<User> findUserByConfirmCode(String code) {
        return usersRepository.findByConfirmCode(code);
    }
}
