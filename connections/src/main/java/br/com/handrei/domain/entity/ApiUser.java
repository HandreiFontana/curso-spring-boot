package br.com.handrei.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class ApiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login")
    @NotEmpty(message = "{field.login.required}")
    private String login;

    @Column(name = "password")
    @NotEmpty(message = "{field.password.required}")
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;
}
