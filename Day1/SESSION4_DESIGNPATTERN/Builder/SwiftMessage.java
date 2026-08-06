package SESSION4_DESIGNPATTERN.Builder;

public final class SwiftMessage {
    private final String messageType, sender, receiver, reference;
    private SwiftMessage(Builder b) {
         messageType=b.messageType; sender=b.sender; receiver=b.receiver; reference=b.reference; 
        }

    public String getMessageType() {
        return messageType;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getReference() {
        return reference;
    }

    public static class Builder {
        private final String messageType, sender;
        private String receiver, reference;
        public Builder(String messageType, String sender) {
             this.messageType=messageType; this.sender=sender; 
            }
        public Builder receiver(String r) {
             this.receiver=r; return this; 
            }
        public Builder reference(String r) {
             this.reference=r; return this; 
            }
        public SwiftMessage build() {
             return new SwiftMessage(this); 
            }
    }
}