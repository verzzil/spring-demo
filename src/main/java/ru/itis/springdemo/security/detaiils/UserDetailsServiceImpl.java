package ru.itis.springdemo.security.detaiils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.springdemo.dao.UserDao;
import ru.itis.springdemo.models.User;

import java.util.Optional;

@Component("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByEmail(email);
        if (user.isPresent()) {
            return new UserDetailsImpl(user.get());
        }
        throw new UsernameNotFoundException("User not found");
    }
}
