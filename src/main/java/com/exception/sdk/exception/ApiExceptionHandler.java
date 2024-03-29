package com.exception.sdk.exception;

//import io.jsonwebtoken.ClaimJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = createBuilder(status, ex.getMessage()).build();
        return new ResponseEntity<>(apiErrorResponse, status);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErrorResponse apiErrorResponse = createBuilder(status, ex.getMessage()).build();
        return new ResponseEntity<>(apiErrorResponse, status);
    }

//    @ExceptionHandler(ClaimJwtException.class)
//    public final ResponseEntity<Object> handleExpiredJwtException(ClaimJwtException ex, WebRequest request) {
//        HttpStatus status = HttpStatus.UNAUTHORIZED;
//        ApiError apiError = createBuilder(status, "Token expirado.").build();
//        return new ResponseEntity<>(apiError, status);
//    }

    @ExceptionHandler(JwtSignatureException.class)
    public final ResponseEntity<Object> handleSignatureException(JwtSignatureException ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse apiErrorResponse = createBuilder(status, "Token expirado.").build();
        return new ResponseEntity<>(apiErrorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse apiErrorResponse = createBuilder(status, ex.getMessage()).build();
        return new ResponseEntity<>(apiErrorResponse, status);
    }

    private ApiErrorResponse.ApiErrorResponseBuilder createBuilder(
            HttpStatus httpStatus,
            String message
    ) {
        return ApiErrorResponse.builder()
                .code(httpStatus)
                .message(message);
    }
}
