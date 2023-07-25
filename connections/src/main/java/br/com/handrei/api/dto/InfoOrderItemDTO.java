package br.com.handrei.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoOrderItemDTO {
    private String description;
    private BigDecimal unitValue;
    private Integer quantity;
}
