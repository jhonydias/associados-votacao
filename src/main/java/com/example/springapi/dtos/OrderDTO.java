package com.example.springapi.dtos;

import com.example.springapi.config.DateConfig;
import com.example.springapi.entities.OrderItem;
import com.example.springapi.entities.User;
import com.example.springapi.entities.enums.OrderStatus;
import lombok.Data;

import java.time.Instant;
import java.util.List;


@Data
public class OrderDTO {

    private Instant moment = DateConfig.getDateBrazilSpNow();
    private OrderStatus orderStatus;
    private Long clientId;
    private User client;
    private List<OrderItem> orderItems;




}
