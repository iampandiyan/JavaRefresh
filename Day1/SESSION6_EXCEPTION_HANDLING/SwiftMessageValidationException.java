package SESSION6_EXCEPTION_HANDLING;

import java.util.List;

public class SwiftMessageValidationException extends SwiftMessageException {
     private final List<String> validationErrors;
    public SwiftMessageValidationException(String messageType, List<String> errors) {
        super("Validation failed for " + messageType + ": " + errors, messageType, null);
        this.validationErrors = errors;
    }
    public List<String> getValidationErrors() { return validationErrors; }

}
