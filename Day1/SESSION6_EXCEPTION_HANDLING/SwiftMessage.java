package SESSION6_EXCEPTION_HANDLING;

public class SwiftMessage {
    private final String type;
    private final String body;

    public SwiftMessage(String type, String body) {
        this.type = type;
        this.body = body;
    }

    public String getType() { return type; }
    public String getBody() { return body; }
}
