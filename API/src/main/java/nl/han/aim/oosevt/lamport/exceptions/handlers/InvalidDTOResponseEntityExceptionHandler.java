package nl.han.aim.oosevt.lamport.exceptions.handlers;

import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;

@ControllerAdvice
public class InvalidDTOResponseEntityExceptionHandler {
    @ExceptionHandler(value = InvalidDTOException.class)
    protected ResponseEntity<HashMap<String, List<String>>> handle(InvalidDTOException exception) {
        return new ResponseEntity<>(
                exception.getErrors(),
                HttpStatus.BAD_REQUEST);
    }
}
