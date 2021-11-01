package rs.mkgroup.pdvapp.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import rs.mkgroup.pdvapp.config.InvalidJWTokenException;
import rs.mkgroup.pdvapp.exception.types.CompanyDoesNotExistsException;
import rs.mkgroup.pdvapp.exception.types.UserPrincipalNotFoundException;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(CompanyDoesNotExistsException.class)
	public final ResponseEntity<Object> handleCompanyDoesNotExistsException(CompanyDoesNotExistsException ex) {
		
		ExceptionResponse exceptionResponse =  ExceptionResponse.builder()
												.message("Company code error.")
												.timestamp(LocalDateTime.now())
												.details("Company with company code: " + ex.getMessage() + " does not exists.")
												.build();
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserPrincipalNotFoundException.class)
	public final ResponseEntity<Object> handleUserPrincipalNotFoundException(UserPrincipalNotFoundException ex) {
		
		ExceptionResponse exceptionResponse =  ExceptionResponse.builder()
												.message("Logged user error.")
												.timestamp(LocalDateTime.now())
												.details("Problem loading logged user, try to login again and then try to execute operation again.")
												.build();
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(InvalidJWTokenException.class)
	public final ResponseEntity<Object> handleInvalidJWTokenException(InvalidJWTokenException ex) {
		
		ExceptionResponse exceptionResponse =  ExceptionResponse.builder()
												.message("Logged user error.")
												.timestamp(LocalDateTime.now())
												.details("Problem loading logged user, try to login again and then try to execute operation again.")
												.build();
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.FORBIDDEN);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse =  ExceptionResponse.builder()
				.message("Method Argument not valid.")
				.timestamp(LocalDateTime.now())
				.details("Bad Request parameters.")
				.build();

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	

}
