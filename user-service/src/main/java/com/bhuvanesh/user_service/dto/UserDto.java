package com.bhuvanesh.user_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String email;
    private Boolean alerting;
    private double alertingThreshold;
}
