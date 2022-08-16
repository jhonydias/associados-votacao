package com.example.springapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CategoryDTO {
    @NotBlank
    private String name;
}
