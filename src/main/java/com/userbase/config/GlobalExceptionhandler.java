package com.userbase.config;

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

import com.userbase.exception.UserNotFoundException;
import com.userbase.dto.APIReponse;
import com.userbase.exception.InvalidUserDataException;

/**
 * This class is used to handle exceptions globally.
 */
@ControllerAdvice
public class GlobalExceptionhandler {

        private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionhandler.class.getName());

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<APIReponse<Void>> handleValidationExceptions(
                        MethodArgumentNotValidException ex, WebRequest request) {
                Map<String, String> errors = ex.getBindingResult().getAllErrors().stream()
                                .collect(Collectors.toMap(error -> ((FieldError) error).getField(),
                                                error -> error.getDefaultMessage()));

                LOG.error("Validation error: {}", errors);

                APIReponse<Void> response = APIReponse.failure("Validation Failed", errors);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<APIReponse<Void>> handleUserNotFoundException(UserNotFoundException ex,
                        WebRequest request) {
                LOG.error("User not found: {}", ex.getMessage());
                APIReponse<Void> response = APIReponse.failure("User Not Found", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InvalidUserDataException.class)
        public ResponseEntity<APIReponse<Void>> handleInvalidUserDataException(InvalidUserDataException ex,
                        WebRequest request) {
                LOG.error("Invalid user data: {}", ex.getMessage());
                APIReponse<Void> response = APIReponse.failure("Invalid User Data", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(RuntimeException.class)
        public ResponseEntity<APIReponse<Void>> handleRuntimeException(RuntimeException ex, WebRequest request) {
                LOG.error("Runtime exception: {}", ex.getMessage(), ex);
                APIReponse<Void> response = APIReponse.failure("Internal Server Error", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // global exception handler
        @ExceptionHandler(Exception.class)
        public ResponseEntity<APIReponse<Void>> handleGlobalException(Exception ex, WebRequest request) {
                LOG.error("Exception: {}", ex.getMessage(), ex);
                APIReponse<Void> response = APIReponse.failure("Internal Server Error", ex.getMessage());
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
