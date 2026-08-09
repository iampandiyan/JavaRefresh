package SESSION6_EXCEPTION_HANDLING;

public abstract class SwiftMessageException extends RuntimeException{
    private final String messageType;
    protected SwiftMessageException(String messageType, String message, Throwable cause) {
        super(message, cause);
        this.messageType = messageType;
    }
    public String getMessageType() {
        return messageType;
    }

}
