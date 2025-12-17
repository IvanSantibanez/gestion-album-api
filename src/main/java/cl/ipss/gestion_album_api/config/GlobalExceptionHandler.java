package cl.ipss.gestion_album_api.config;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import cl.ipss.gestion_album_api.responses.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> validationException(MethodArgumentNotValidException ex) {

    List<String> messages = ex.getBindingResult().getAllErrors()
        .stream()
        .map(error -> error.getDefaultMessage())
        .toList();

    ErrorResponse response = new ErrorResponse();

    response.setStatus(400);
    response.setMessage("Error de validación");
    response.setData(messages);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> notFound(EntityNotFoundException ex) {

    ErrorResponse response = new ErrorResponse();

    response.setStatus(404);
    response.setMessage(ex.getMessage());
    response.setData(null);

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> generalException(Exception ex) {

    ErrorResponse response = new ErrorResponse();

    response.setStatus(500);
    response.setMessage("Error interno del servidor");
    response.setData(null);

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleMethodArgumentNotValidException(ConstraintViolationException ex) {

    ErrorResponse response = new ErrorResponse();

    List<String> messages = ex.getConstraintViolations()
        .stream()
        .map(ConstraintViolation::getMessage)
        .toList();

    response.setStatus(400);
    response.setMessage("Error de validación");
    response.setData(messages);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {

    ErrorResponse response = new ErrorResponse();

    response.setStatus(400);
    response.setMessage("Error de tipo de argumento en el parámetro: " + ex.getName());
    response.setData(null);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

}
