package com.testing.surya2.service;

import com.google.gson.Gson;
import com.testing.surya2.configs.ApplicationConstant;
import com.testing.surya2.models.Person;
import com.testing.surya2.models.response.PersonResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonService {
    @Autowired
    private RestTemplate restTemplate;

    public PersonResponse getPersonById(String requestId, Long id) {
        OkHttpClient client = new OkHttpClient();
        String uri = ApplicationConstant.SERVICE_GET_PERSON_BY_ID_URI;
        PersonResponse personResponse = new PersonResponse();

        Request request = new Request.Builder()
                .url(uri+id)
                .header("Content-Type", "application/json")
                .header("request-id", requestId)
                .build();

        try {
            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                Gson gson = new Gson();
                personResponse = gson.fromJson(response.body().charStream(), PersonResponse.class);

                System.out.println("PersonResponse: " + personResponse.toString());
            } else {
                // Print the error message if the response is not successful
                System.out.println("Error: " + response.code() + " - " + response.message());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return personResponse;
    }
}
