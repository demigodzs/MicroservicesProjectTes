package com.testing.surya1.models.response;

import com.testing.surya1.configs.ApplicationConstant;
import com.testing.surya1.exception.CommonException;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseInfo<T> {
    private final Response<T> body = new Response<>();
    private HttpStatus httpStatus;
    private HttpHeaders httpHeaders;

    public void setSuccess() {
        this.httpStatus = HttpStatus.OK;
        body.setStatus(ApplicationConstant.STATUS.ok);
        body.setCode("00");
        body.setMessage("Success");
    }

    public void setSuccess(T data) {
        body.setData(data);
        setSuccess();
    }

    public void setCommonException(CommonException e) {
        if (e.getHttpStatus() != null) {
            this.httpStatus = e.getHttpStatus();
        } else {
            this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        String status = e.getStatus().equals(ApplicationConstant.COMPLETION_STATUS.SYSTEM_ERROR)?"error":"failed";
        body.setStatus(ApplicationConstant.STATUS.valueOf(status));
        body.setCode(e.getCode());
        body.setMessage(e.getDisplayMessage());
    }

    public void setException(Exception e) {
        if (e instanceof CommonException) {
            setCommonException((CommonException) e);
        } else {
            setCommonException(new CommonException(e));
        }
    }

    public void setBadRequestException(String message)
    {
        this.httpStatus = HttpStatus.BAD_REQUEST;
        body.setStatus(ApplicationConstant.STATUS.failed);
        body.setCode("98");
        body.setMessage(message);
    }

    public void setNotFoundException(String message)
    {
        this.httpStatus = HttpStatus.NOT_FOUND;
        body.setStatus(ApplicationConstant.STATUS.failed);
        body.setCode("97");
        body.setMessage(message);
    }

    public void setNoContentException(String message)
    {
        this.httpStatus = HttpStatus.NO_CONTENT;
        body.setStatus(ApplicationConstant.STATUS.failed);
        body.setCode("96");
        body.setMessage(message);
    }
}
