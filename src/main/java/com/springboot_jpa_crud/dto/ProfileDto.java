package com.springboot_jpa_crud.dto;

import lombok.Data;

@Data
public class ProfileDto {

    private int profile_id;
    private long phone;
    private String address;
    private String city;
    private String state;

//    public ProfileDto(int i, String s) {
//
//    }

//    @JsonBackReference
//    private EmployeeDto employeeDto;

}
