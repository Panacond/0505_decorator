package support;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static support.XmlTestData.ReadXml;

public class SimpleCode {
    public static void main(String[] args) {
        selectItems();
        System.out.println("SimpleCode.main");
        selectItemsStream1(1, 6);
        selectItemsStream();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> afterLimit = list.stream().limit(4).collect(Collectors.toList());
        List<Integer> afterSkip = list.stream().skip(4).collect(Collectors.toList());

        System.out.println("Limit: " + afterLimit);
        System.out.println("Skip: " + afterSkip);

    }

    private static Object[][] selectItems() {
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData = new ListTestData(ReadXml(path));
        int row = listTestData.getListTestData().size();
        Object[][] data = new Object[row][3];
        for (int i = 0; i < row; i++) {
            TestData item = listTestData.getListTestData().get(i);
            data[i][0] = item.getProduct();
            data[i][1] = item.getBrand();
            data[i][2] = 12;
            System.out.println(data[i][0]);
        }
        return data;
    }

    private static Object[][] selectItemsStream() {
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData = new ListTestData(ReadXml(path));
        return listTestData.getListTestData()
                .stream()
                .map(a -> new Object[]{a.getProduct(), a.getBrand(), a.getMinPrice()})
                .toArray(Object[][]::new);
    }

    private static Object[][] selectItemsStream1(int start, int finish) {
        PropertiesReader properties = new PropertiesReader();
        String path = properties.getInitialListData();
        ListTestData listTestData = new ListTestData(ReadXml(path));
        return listTestData.getListTestData()
                .stream()
                .skip(start).limit(finish - start)
                .map(a -> new Object[]{a.getProduct(), a.getBrand(), a.getMinPrice()})
                .toArray(Object[][]::new);
    }
}
