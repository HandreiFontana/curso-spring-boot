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

    @NotNull(message = "Customer is required!")
    private Integer customer;

    @NotNull(message = "Order value is required!")
    private BigDecimal orderValue;

    @NotEmptyList(message = "Order items is required!")
    private List<OrderItemDTO> items;
}
