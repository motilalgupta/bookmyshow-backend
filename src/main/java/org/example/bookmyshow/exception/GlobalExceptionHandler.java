package org.example.bookmyshow.exception;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
        ErrorResponse errorDetails = new ErrorResponse(new Date(), HttpStatus.NOT_FOUND.value(),"Not Found", ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SeatUnavailableException.class)
    private ResponseEntity<?> seatUnavailableException(SeatUnavailableException ex, WebRequest request){
        ErrorResponse errorDeatails = new ErrorResponse(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
                "Bad Request", request.getDescription(false));

        return new ResponseEntity<>(errorDeatails,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request){
        ErrorResponse errorDetails = new ErrorResponse(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(),
                "Internal Server Error", request.getDescription(false));

        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
