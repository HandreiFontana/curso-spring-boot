package br.com.handrei.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity()
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    @NotEmpty(message = "{field.description.required}")
    private String description;

    @Column(name = "unit_value")
    @NotNull(message = "{field.unit-value.required}")
    private BigDecimal unitValue;
}
