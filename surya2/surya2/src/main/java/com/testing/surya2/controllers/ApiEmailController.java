package com.testing.surya2.controllers;

import com.testing.surya2.models.response.ResponseInfo;
import com.testing.surya2.usecase.EmailUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
public class ApiEmailController {

    @Autowired
    public EmailUsecase emailUsecase;

    @PostMapping("email/send")
    public ResponseEntity<?> sendOTP(
            @RequestHeader(value = "request-id") String requestId,
            @RequestParam("user-id") Long userId){
        ResponseInfo<Object> responseInfo = emailUsecase.sendEmail(requestId, userId);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
}
