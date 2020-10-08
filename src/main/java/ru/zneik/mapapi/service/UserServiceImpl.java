package ru.zneik.mapapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zneik.mapapi.exception.BadCredentionalException;
import ru.zneik.mapapi.exception.NotFoundException;
import ru.zneik.mapapi.model.User;
import ru.zneik.mapapi.repository.UserRepository;
import ru.zneik.mapapi.service.base.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public User checkUserCredentional(String username, String password) {
        User user;
        try {
            user = getByUsername(username);
        } catch (NotFoundException e) {
            throw new BadCredentionalException("Password incorrect");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentionalException("Password incorrect");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        try {
            user = getByUsername(username);
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
