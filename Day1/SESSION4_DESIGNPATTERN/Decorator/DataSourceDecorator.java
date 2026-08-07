package SESSION4_DESIGNPATTERN.Decorator;

public abstract class DataSourceDecorator implements DataSource {
    protected final DataSource wrapped;
    DataSourceDecorator(DataSource wrapped) {
        this.wrapped = wrapped;
    }

}
