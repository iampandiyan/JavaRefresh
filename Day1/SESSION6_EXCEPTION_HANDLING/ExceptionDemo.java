package SESSION6_EXCEPTION_HANDLING;

import java.util.Arrays;
import java.util.List;

public class ExceptionDemo {
    static class SwiftMessageProcessor {
        // parse -> may throw low-level exception which we wrap
        SwiftMessage parse(String raw) {
            try {
                if (raw.equals("BADPARSE")) throw new IllegalStateException("I/O during parse");
                // naive parse: first token is message type
                String[] parts = raw.split(":", 2);
                String type = parts.length > 0 ? parts[0] : "UNKNOWN";
                String body = parts.length > 1 ? parts[1] : "";
                return new SwiftMessage(type, body);
            } catch (RuntimeException e) {
                throw new SwiftMessageParseException("MT103", e);
            }
        }

        // validate -> may throw validation exception carrying errors
        void validate(SwiftMessage m) {
            if (m.getBody().contains("BADVAL")) {
                List<String> errs = Arrays.asList("missing field 32A", "invalid date format");
                throw new SwiftMessageValidationException(m.getType(), errs);
            }
        }

        void process(String raw) {
            SwiftMessage m = parse(raw);
            validate(m);
            System.out.println("Processed message type=" + m.getType());
        }
    }

    public static void main(String[] args) {
        SwiftMessageProcessor p = new SwiftMessageProcessor();

        System.out.println("=== Successful processing ===");
        p.process("MT103:some-body");

        System.out.println("\n=== Parse error handling (wrapped) ===");
        try { p.process("BADPARSE"); }
        catch (SwiftMessageParseException ex) {
            System.out.println("Caught SwiftMessageParseException: " + ex.getMessage());
            System.out.println("messageType field: " + ex.getMessageType());
            System.out.println("root cause: " + (ex.getCause() != null ? ex.getCause().getClass().getSimpleName()+": "+ex.getCause().getMessage() : "none"));
        }

        System.out.println("\n=== Validation error handling ===");
        try { p.process("MT103:body-with-BADVAL"); }
        catch (SwiftMessageValidationException vex) {
            System.out.println("Caught SwiftMessageValidationException for " + vex.getMessageType());
            System.out.println("Errors: " + vex.getValidationErrors());
        }

        System.out.println("\n=== Try-with-resources suppressed example (see TryWithResourcesDemo) ===");
        TryWithResourcesDemo.main(new String[0]);
    }
}
