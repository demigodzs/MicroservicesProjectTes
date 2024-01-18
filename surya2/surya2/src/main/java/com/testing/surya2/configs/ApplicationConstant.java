package com.testing.surya2.configs;

public class ApplicationConstant {
    public static final String SERVICE_GET_PERSON_BY_ID_URI = "http://localhost:9090/api/persons/";

    public enum STATUS {
        ok,
        failed,
        error
    }

    public enum COMPLETION_STATUS {
        SUCCESS,
        BUSINESS_ERROR,
        SYSTEM_ERROR
    }
}
