package com.example.springapi.dtos;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Data
public class ProductDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Double price;
    @NotBlank
    private String imgUrl;
    @NotNull
    private ArrayList<Long> categoriesId;
}
