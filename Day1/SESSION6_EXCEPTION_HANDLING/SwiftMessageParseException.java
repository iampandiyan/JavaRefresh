package SESSION6_EXCEPTION_HANDLING;

public class SwiftMessageParseException extends SwiftMessageException {
    public SwiftMessageParseException(String messageType, Throwable cause) {
        super("Failed to parse SWIFT message of type " + messageType, messageType, cause);
    }

}
