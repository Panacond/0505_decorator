package example_decorator.refactoring_guru_decorator;

public interface DataSource {
    /**
     * @param data = "String"
     * Интерфейс, задающий базовые операции чтения и записи данных
     */
    default void writeData(String data) {
    }

    default String readData() {
        return null;
    }
}
