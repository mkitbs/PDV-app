package rs.mkgroup.pdvapp.exception.types;

public class UserPrincipalNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8968873058879033096L;

	public UserPrincipalNotFoundException(String message) {
		super(message);
	}

}
