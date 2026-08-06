package SESSION4_DESIGNPATTERN.Builder;

public class BuilderDemo {
    public static void main(String[] args) {
        SwiftMessage message = new SwiftMessage.Builder("MT103", "BANKABC")
                .receiver("BANKXYZ")
                .reference("REF-12345")
                .build();

        System.out.println("Message type: " + message.getMessageType());
        System.out.println("Sender: " + message.getSender());
        System.out.println("Receiver: " + message.getReceiver());
        System.out.println("Reference: " + message.getReference());
    }
}
