package com.example.associadosvotacao.v1.dtos;

public abstract class BaseDto<T> {
    public abstract T convertToModel();
    public abstract T convertToModel(T t);
}
