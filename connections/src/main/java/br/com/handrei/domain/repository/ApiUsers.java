package br.com.handrei.domain.repository;

import br.com.handrei.domain.entity.ApiUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiUsers extends JpaRepository<ApiUser, Integer> {

    Optional<ApiUser> findByLogin(String login);
}
