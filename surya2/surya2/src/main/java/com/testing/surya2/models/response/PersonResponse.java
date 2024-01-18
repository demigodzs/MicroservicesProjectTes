package com.testing.surya2.models.response;

import com.testing.surya2.models.Person;
import lombok.Data;

@Data
public class PersonResponse {
    private String status;
    private String code;
    private String message;
    private Person data;
}
