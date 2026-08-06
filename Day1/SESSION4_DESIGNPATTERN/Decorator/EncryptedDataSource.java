package SESSION4_DESIGNPATTERN.Decorator;

public class EncryptedDataSource extends DataSourceDecorator{
    EncryptedDataSource(DataSource wrapped) {
        super(wrapped);
    }

    public String read() {
        String data = wrapped.read();
        // Simulate decryption
        return "Decrypted(" + data + ")";
    }

}
