package com.vnrg.amq.springbootamqtest.controller;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private int id;
    private String description;

    private int count;
}
