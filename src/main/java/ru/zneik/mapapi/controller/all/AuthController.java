package ru.zneik.mapapi.controller.all;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.zneik.mapapi.model.User;
import ru.zneik.mapapi.service.JwtService;
import ru.zneik.mapapi.service.base.UserService;
import ru.zneik.mapapi.to.TokenTO;
import ru.zneik.mapapi.to.UserAuthTO;

import javax.validation.Valid;

import static ru.zneik.mapapi.controller.all.AuthController.URL;

@RestController
@RequestMapping(value = URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    public static final String URL = "/auth";

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public TokenTO login(@Valid @RequestBody UserAuthTO userAuthTO) {
        User user = userService.checkUserCredentional(userAuthTO.getUsername(),
                userAuthTO.getPassword());
        String token = jwtService.generateToken(user.getUsername());
        return new TokenTO(token);
    }

}
