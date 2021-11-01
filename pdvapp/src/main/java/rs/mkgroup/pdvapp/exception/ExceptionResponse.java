package rs.mkgroup.pdvapp.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class ExceptionResponse {
	
	private String message;
	private LocalDateTime timestamp;
	private String details;

}
