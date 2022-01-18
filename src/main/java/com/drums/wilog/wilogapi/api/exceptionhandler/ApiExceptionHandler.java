package com.drums.wilog.wilogapi.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problem.Field> fields = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()){

            String name = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            fields.add(new Problem.Field(name,message));
        }

        Problem problem = new Problem();
        problem.setStatus(status.value());
        problem.setDateTime(LocalDateTime.now());
        problem.setTitle("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
        problem.setFields(fields);
        return handleExceptionInternal(ex, problem,headers,status,request);
    }
}
