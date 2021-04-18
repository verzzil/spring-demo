package ru.itis.springdemo.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.UserDao;
import ru.itis.springdemo.dao.repositories.LikesRepo;
import ru.itis.springdemo.dao.repositories.UserRepo;
import ru.itis.springdemo.dto.UserDto;
import ru.itis.springdemo.models.Article;
import ru.itis.springdemo.models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.itis.springdemo.dto.UserDto.from;

@Component
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private LikesRepo likesRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean isCurrentProfileIsLiked(Integer idSessionUser, Integer idProfileUser) {
        return likesRepo.hasUserToUserLike(idSessionUser, idProfileUser);
    }

    @Override
    public List<Integer> idsOfLikedArticlesOfTheCurrentProfile(Integer idSessionUser, Integer idProfileUser) {
        return likesRepo.getAllArticlesWhereUserLikedFromUser(idSessionUser, idProfileUser).stream()
                .map(Article::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersWithLimit6(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 6);
        Page<User> usersPage = userDao.getUsersWithLimit6(pageRequest);
        return from(usersPage.getContent());
    }

    @Override
    public List<Integer> getLikedUsersIdsFromUserId(Integer id) {
        return userRepo.getAllLikedUsersFromUserId(id).stream()
                .map(User::getId)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getLikedUsersFromUserId(Integer id) {
        return from(userRepo.getAllLikedUsersFromUserId(id));
    }

    @Override
    public List<UserDto> findAll() {
        return userDao.findAll().stream()
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
        user.ifPresent(user1 -> userDao.confirmUser(user1.getId(), User.State.CONFIRMED.getState()));
    }

    @Override
    public UserDto findById(Integer id) {
        return userDao.getOne(id).toUserDto();
    }

    @Override
    public void updatePhoto(String photoName, Integer id) {

    }

    @Override
    public void updateFirstAndLastNameAndCity(String firstName, String lastName, String city, Integer id) {
        userRepo.updateFirstAndLastNameAndCity(firstName, lastName, city, id);
    }

    @Override
    public void updatePassword(String pass, Integer id) {
        userRepo.updatePassword(passwordEncoder.encode(pass), id);
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
        User user = userDao.getOne(userId);

        return passwordEncoder.matches(pass, user.getHashPassword());
    }

    @Override
    public void updateShortAndFullAbout(Integer id, String shortAbout, String fullAbout) {
        userRepo.updateShortAndFullAbout(id, shortAbout, fullAbout);
    }

    @Override
    public Optional<List<UserDto>> findUsersByFilter(String gender, String fromAge, String toAge, String city) {
        return Optional.empty();
    }

    private Optional<User> findUserByConfirmCode(String code) {
        return userDao.findByConfirmCode(code);
    }
}
