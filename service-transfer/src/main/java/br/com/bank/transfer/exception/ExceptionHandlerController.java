package br.com.bank.transfer.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handle(RuntimeException ex, WebRequest request) {
        Map<String, String> mapException = new HashMap<>() {{
            put("message", ex.getMessage());
        }};
        return handleExceptionInternal(ex, mapException, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handle(Exception ex, WebRequest request) {
        Map<String, String> mapException = new HashMap<>() {{
            put("message", ex.getMessage());
        }};
        return handleExceptionInternal(ex, mapException, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
