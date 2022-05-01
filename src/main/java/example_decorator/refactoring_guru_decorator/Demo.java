package example_decorator.refactoring_guru_decorator;

public class Demo {
    private static final String PATH = "src/main/java/example_decorator/ractoring_guru_decorator/";
    public static void main(String[] args) {
        String salaryRecords = "Name,Salary\nJohn Smith,100000\nSteven Jobs,912000";
        DataSourceDecorator encoded = new CompressionDecorator(
                new EncryptionDecorator(
                        new FileDataSource(   PATH + "OutputDemo.txt")));
        encoded.writeData(salaryRecords);
        DataSource plain = new FileDataSource(PATH +"OutputDemo.txt");

        System.out.println("- Input ----------------");
        System.out.println(salaryRecords);
        System.out.println("- Encoded --------------");
        System.out.println(plain.readData());
        System.out.println("- Decoded --------------");
        System.out.println(encoded.readData());
    }
}
