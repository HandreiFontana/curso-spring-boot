package br.com.handrei.api.controller;

import br.com.handrei.domain.entity.ApiUser;
import br.com.handrei.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiUser create(@RequestBody @Valid ApiUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        return userService.create(user);
    }
}
