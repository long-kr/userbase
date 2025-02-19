package com.userbase.user.error;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * This class is used to handle exceptions globally.
 */
@ControllerAdvice
public class GlobalExceptionhandler {

        private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionhandler.class);

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                        WebRequest request) {
                LOG.info("======> Invalid Brgument: {}", e.getMessage());

                Map<String, String> errors = e.getBindingResult().getAllErrors().stream()
                                .collect(Collectors.toMap(field -> ((FieldError) field).getField(),
                                                field -> field.getDefaultMessage()));

                ValidationExceptionResponseEntity apiException = new ValidationExceptionResponseEntity(
                                errors,
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ZonedDateTime.now(ZoneId.of("Z")));

                return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
        };

        @ExceptionHandler(value = Exception.class)
        public ResponseEntity<Object> handleAPIRequestException(Throwable e, WebRequest request) {
                LOG.error("======> Exception occurred: {}, Request Details: {}", e.getMessage(),
                                request.getDescription(false),
                                e);

                APIExceptionResponseEntity apiException = new APIExceptionResponseEntity(
                                e.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ZonedDateTime.now(ZoneId.of("Z")));

                return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
        };

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<Object> handleIllegalArugmentException(IllegalArgumentException e, WebRequest request) {
                LOG.error("======> Invalid argument: {}, Request Details: {}", e.getMessage(),
                                request.getDescription(false),
                                e);

                APIExceptionResponseEntity apiException = new APIExceptionResponseEntity(
                                e.getMessage(),
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                ZonedDateTime.now(ZoneId.of("Z")));

                return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
        };

}
