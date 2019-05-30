package businessLogic.exception;

public class InvalidNoOfBooksException extends RuntimeException{
	public InvalidNoOfBooksException() {
		super("Stoc of books is empty");
	}
}
