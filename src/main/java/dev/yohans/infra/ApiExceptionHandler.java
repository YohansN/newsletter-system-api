package dev.yohans.infra;

import dev.yohans.core.exceptions.*;
import dev.yohans.core.exceptions.email.FailedToSendEmailException;
import dev.yohans.core.exceptions.post.FailedToFindAllPosts;
import dev.yohans.core.exceptions.post.FailedToSavePostException;
import dev.yohans.core.exceptions.post.PostNotFoundException;
import dev.yohans.core.exceptions.subscriber.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    //Custom Exception
    @ExceptionHandler(FailedToFindAllPosts.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(FailedToFindAllPosts ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(FailedToSavePostException.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(FailedToSavePostException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(FailedToSendEmailException.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(FailedToSendEmailException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(PostNotFoundException.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(PostNotFoundException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(SubscriberAlreadyExistsException.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(SubscriberAlreadyExistsException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(FailedToSaveSubscriber.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(FailedToSaveSubscriber ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(FailedToFindAllSubscribers.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(FailedToFindAllSubscribers ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(SubscriberNotFoundException.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(SubscriberNotFoundException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(FailedToFindActiveSubscribersException.class)
    private ResponseEntity<ErrorMessageWrapper> failedToFindAllPosts(FailedToFindActiveSubscribersException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    //Generic Exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> mapErrorFields = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapErrorFields.put(fieldName, errorMessage);
        });

        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.BAD_REQUEST, "Houveram erros no preenchimento dos campos", mapErrorFields);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(ConstraintViolationException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(MethodArgumentTypeMismatchException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.BAD_REQUEST, "Parâmetro inválido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(HttpMessageNotReadableException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.BAD_REQUEST, "Valor inválido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(NoResourceFoundException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.NOT_FOUND, "Página não encontrada.");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(IllegalArgumentException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.BAD_REQUEST, "Valor inválido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(HttpRequestMethodNotSupportedException ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.METHOD_NOT_ALLOWED, "Método não suportado.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<ErrorMessageWrapper> genericExceptionHandler(Exception ex){
        ErrorMessageWrapper response = new ErrorMessageWrapper(HttpStatus.METHOD_NOT_ALLOWED, "Houve uma falha durante a execução.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
