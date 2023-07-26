package br.com.handrei.service.impl;

import br.com.handrei.domain.entity.ApiUser;
import br.com.handrei.domain.repository.ApiUsers;
import br.com.handrei.exception.PasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private ApiUsers repository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Transactional
    public ApiUser create(ApiUser user) {
        return repository.save(user);
    }

    public UserDetails authenticate(ApiUser user) {
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean passwordMatches = passwordEncoder().matches(user.getPassword(), userDetails.getPassword());

        if (passwordMatches) {
            return userDetails;
        }

        throw new PasswordInvalidException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        ApiUser user = repository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        String[] roles = user.isAdmin()
                ? new String[] {"ADMIN", "USER"}
                : new String[] {"USER"};

        return User
                .builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

}
