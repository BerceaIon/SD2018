package businessLogic.exception;

public class InvalidCredentialException extends RuntimeException {
	public InvalidCredentialException() {
		super("Wrong credentials");
	}
}
