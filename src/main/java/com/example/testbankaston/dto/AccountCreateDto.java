package com.example.testbankaston.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
public class AccountCreateDto {
    @NotBlank
    @Size(min = 1, max = 250)
    private String name;
    @Min(0001)
    @Max(9999)
    private Integer pin;
}
