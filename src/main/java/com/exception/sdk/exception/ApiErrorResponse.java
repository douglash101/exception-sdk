package com.exception.sdk.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Builder
@Getter
@Setter
public class ApiErrorResponse {

    private HttpStatusCode code;

    private String message;

    private String detail;
}
