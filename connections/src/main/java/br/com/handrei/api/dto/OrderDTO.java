package br.com.handrei.api.dto;

import br.com.handrei.validation.NotEmptyList;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotNull(message = "{field.customer.required}")
    private Integer customer;

    @NotNull(message = "{field.order-value.required}")
    private BigDecimal orderValue;

    @NotEmptyList(message = "{field.order-items.required}")
    private List<OrderItemDTO> items;
}
