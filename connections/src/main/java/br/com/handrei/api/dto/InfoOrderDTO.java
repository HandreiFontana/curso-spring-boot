package br.com.handrei.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoOrderDTO {
    private Integer code;
    private String cpf;
    private String customerName;
    private BigDecimal orderValue;
    private String orderDate;
    private List<InfoOrderItemDTO> items;
}
