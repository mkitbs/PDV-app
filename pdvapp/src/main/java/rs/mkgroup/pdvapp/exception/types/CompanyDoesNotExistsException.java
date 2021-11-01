package rs.mkgroup.pdvapp.exception.types;

public class CompanyDoesNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 6073300398786373014L;
	
	public CompanyDoesNotExistsException(String message) {
		super(message);
	}
	

}
