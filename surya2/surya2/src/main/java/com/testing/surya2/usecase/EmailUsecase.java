package com.testing.surya2.usecase;

import com.testing.surya2.models.Person;
import com.testing.surya2.models.response.PersonResponse;
import com.testing.surya2.models.response.ResponseInfo;
import com.testing.surya2.service.PersonService;
import jakarta.mail.internet.MimeMessage;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUsecase {
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    PersonService personService;

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseInfo<Object> sendEmail(String requestId, Long userId)
    {
        ResponseInfo responseInfo = ResponseInfo.builder().build();

        PersonResponse person = personService.getPersonById(requestId, userId);

        if(person.getData().getEmail() != null || !person.getData().getEmail().equals(""))
        {
            try {
                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(fromEmail);
                mimeMessageHelper.setTo(person.getData().getEmail());
                mimeMessageHelper.setSubject("testing");
                mimeMessageHelper.setText("for testing only");

                javaMailSender.send(mimeMessage);

                responseInfo.setSuccess();
            }catch(Exception e) {
                responseInfo.setException(e);
            }
        }
        else
        {
            responseInfo.setNotFoundException("Email not sent, person is null");
        }

        return responseInfo;
    }
}
