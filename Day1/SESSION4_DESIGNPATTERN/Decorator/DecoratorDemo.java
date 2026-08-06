package SESSION4_DESIGNPATTERN.Decorator;

public class DecoratorDemo {
    public static void main(String[] args) {
        DataSource rawSource = new DataSource() {
            @Override
            public String read() {
                return "SensitivePayload";
            }
        };

        System.out.println("Raw data source: " + rawSource.read());

        DataSource encryptedSource = new EncryptedDataSource(rawSource);
        System.out.println("Encrypted decorator: " + encryptedSource.read());

        DataSource doubleEncrypted = new EncryptedDataSource(encryptedSource);
        System.out.println("Encrypted decorator wrapped again: " + doubleEncrypted.read());
    }
}
