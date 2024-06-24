package com.mayo.insurance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private int statusCode;
    private String msg;
    private T data;
}
