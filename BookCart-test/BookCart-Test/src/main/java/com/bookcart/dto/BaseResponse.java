package com.bookcart.dto;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter @Setter
@AllArgsConstructor
@JsonPropertyOrder({"responseCode", "responseMessage", "response"})
public class BaseResponse {

 
    Integer responseCode;

    private String responseMessage;

    private Object response;
    
    HttpStatus httpStatus;

    public BaseResponse() {
        this.responseCode = OK.value();
        this.responseMessage = OK.getReasonPhrase();
        this.httpStatus = HttpStatus.OK;
    }

    public BaseResponse(int code, String message) {
        this.responseCode = code;
        this.responseMessage = message;
    }
    
    public BaseResponse(int code, String message, String response) {
        this(code, message);
        this.response = response;
    }

    public BaseResponse(int code, String message, Object response) {
        this(code, message);
        this.response = response;
    }
    
    public BaseResponse(Object response) {
        this();
        this.response = response;
    }

    @Override
    public String toString() {
        String responseMsg = "";
        if (!StringUtils.isEmpty(responseMessage)) {
            responseMsg = responseMessage;
        } else if (!StringUtils.isEmpty(response)) {
            responseMsg = response.toString();
        }
        return responseMsg;
    }
}
