package ru.zneik.mapapi.service.base;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.zneik.mapapi.model.User;

public interface UserService extends UserDetailsService {
    User getByUsername(String username);
    User checkUserCredentional(String username, String password);
}
