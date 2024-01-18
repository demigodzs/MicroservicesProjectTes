package com.testing.surya1.controllers;

import com.testing.surya1.models.Person;
import com.testing.surya1.models.response.ResponseInfo;
import com.testing.surya1.usecase.PersonUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
public class ApiPersonController {

    @Autowired
    public PersonUsecase personUsecase;

    @PostMapping("persons/add")
    public ResponseEntity<?> addPerson(
        @RequestHeader(value = "request-id") String requestId,
        @RequestBody Person person
    ){
        ResponseInfo<Object> responseInfo = personUsecase.addPerson(requestId, person);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @GetMapping("persons")
    public ResponseEntity<?> getAllPerson(
        @RequestHeader(value = "request-id") String requestId
    ){
        ResponseInfo<Object> responseInfo = personUsecase.getPersons(requestId);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<?> getPersonById(
        @RequestHeader(value = "request-id") String requestId,
        @PathVariable("id") Long id
    ){
        ResponseInfo<Object> responseInfo = personUsecase.getPersonById(requestId, id);
        return new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @PutMapping("persons/edit/{id}")
    public ResponseEntity<?> editPerson(
        @RequestHeader(value = "request-id") String requestId,
        @PathVariable("id") Long id,
        @RequestBody Person person
    ){
        ResponseInfo<Object> responseInfo = personUsecase.editPerson(requestId, id, person);
        return  new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @PutMapping("persons/delete/{id}")
    public ResponseEntity<?> deletePerson(
            @RequestHeader(value = "request-id") String requestId,
            @PathVariable("id") Long id
    ){
        ResponseInfo<Object> responseInfo = personUsecase.deletePerson(requestId, id);
        return  new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }

    @PostMapping("persons/search")
    public ResponseEntity<?> searchPerson(
            @RequestHeader(value = "request-id") String requestId,
            @RequestParam("keyword") String keyword
    ){
        ResponseInfo<Object> responseInfo = personUsecase.searchPersonByKeyword(requestId, keyword);
        return  new ResponseEntity<>(responseInfo.getBody(),
                responseInfo.getHttpHeaders(),
                responseInfo.getHttpStatus());
    }
}
