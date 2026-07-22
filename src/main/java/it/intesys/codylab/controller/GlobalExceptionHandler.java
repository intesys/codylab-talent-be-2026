package it.intesys.codylab.controller;

import it.intesys.codylab.controller.dto.ProblemApiDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ProblemApiDTO> handleIllegalArgument(IllegalArgumentException e) {
        ProblemApiDTO problem = new ProblemApiDTO()
                .type(URI.create("about:blank"))
                .title("Bad Request")
                .status(400)
                .detail(e.getMessage());
        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemApiDTO> handleValidationError(MethodArgumentNotValidException e) {
        String detail = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .reduce((a, b) -> a + "; " + b)
                .orElse("Validation failed");
        ProblemApiDTO problem = new ProblemApiDTO()
                .type(URI.create("about:blank"))
                .title("Bad Request")
                .status(400)
                .detail(detail);
        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ProblemApiDTO> handleGeneral(Exception e) {
        ProblemApiDTO problem = new ProblemApiDTO()
                .type(URI.create("about:blank"))
                .title("Internal Server Error")
                .status(500)
                .detail(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }
}
