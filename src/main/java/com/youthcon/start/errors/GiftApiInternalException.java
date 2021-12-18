package com.youthcon.start.errors;

import com.youthcon.start.infra.GiftApi;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GiftApiInternalException extends RuntimeException {
    public GiftApiInternalException(){
    }
}
