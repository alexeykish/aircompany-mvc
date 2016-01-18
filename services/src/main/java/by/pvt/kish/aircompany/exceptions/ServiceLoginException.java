package by.pvt.kish.aircompany.exceptions;

/**
 * @author Kish Alexey
 */
public class ServiceLoginException extends Throwable {
    /**
     * Constructs a ServiceLoginException with the given detail message.
     * @param message The detail message of the ServiceLoginException.
     */
    public ServiceLoginException(String message) {
        super(message);
    }

    /**
     * Constructs a ServiceLoginException with the given root cause.
     * @param cause The root cause of the ServiceLoginException.
     */
    public ServiceLoginException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a ServiceLoginException with the given detail message and root cause.
     * @param message The detail message of the ServiceLoginException.
     * @param cause The root cause of the ServiceLoginException.
     */
    public ServiceLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
