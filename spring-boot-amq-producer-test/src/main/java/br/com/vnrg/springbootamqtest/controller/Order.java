package br.com.vnrg.springbootamqtest.controller;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Order {
    private int id;
    private String description;

    private int count;
}
