package br.com.dg.exceptionsdk.handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.OffsetDateTime;

@Builder
@Getter
@Setter
public class ApiErrorResponse {

    private HttpStatusCode code;

    private String message;

    private String detail;

    private OffsetDateTime time;
}
