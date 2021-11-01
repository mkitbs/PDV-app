package rs.mkgroup.pdvapp.config;

public class InvalidJWTokenException extends RuntimeException {

	private static final long serialVersionUID = -5799605029633953931L;

	public InvalidJWTokenException(String message) {
		super(message);
	}
	
}
