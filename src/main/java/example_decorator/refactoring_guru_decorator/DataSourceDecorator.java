package example_decorator.refactoring_guru_decorator;

public class DataSourceDecorator implements DataSource {
    /**
     * Базовый декоратор
     */
    private final DataSource wrappee;

    DataSourceDecorator(DataSource source) {
        this.wrappee = source;
    }

    @Override
    public void writeData(String data) {
        wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return wrappee.readData();
    }
}
