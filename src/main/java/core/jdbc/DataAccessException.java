package core.jdbc;

public class DataAccessException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public DataAccessException() {
		super();
	}
	
	public DataAccessException(String message, Throwable cause, boolean enableSuppression,
			boolean writeableStackTrace) {
		super(message, cause, enableSuppression, writeableStackTrace);
	}
	
	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(Throwable cause) {
		super(cause);
	}
}
