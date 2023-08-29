package br.com.handrei.api.controller;

import br.com.handrei.api.dto.CredentialsDTO;
import br.com.handrei.api.dto.TokenDTO;
import br.com.handrei.domain.entity.ApiUser;
import br.com.handrei.exception.PasswordInvalidException;
import br.com.handrei.security.jwt.JwtService;
import br.com.handrei.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class ApiUserController {

    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiUser create(@RequestBody @Valid ApiUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);

        return userService.create(user);
    }

    @PostMapping("/sessions")
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {
        try {
            ApiUser user = ApiUser.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword())
                    .isAdmin(false)
                    .build();

            userService.authenticate(user);

            String token = jwtService.getToken(user);

            return new TokenDTO(user.getLogin(), token);
        } catch (UsernameNotFoundException | PasswordInvalidException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, exception.getMessage());
        }
    };
}
