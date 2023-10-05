package com.example.testbankaston.dto;

import com.example.testbankaston.enums.Operation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AccountOperationDto {
    private Long idAccountForTransfer;
    @NotNull
    private Operation operation;
    private Integer pin;
    @Positive
    private BigDecimal deposit;
}
