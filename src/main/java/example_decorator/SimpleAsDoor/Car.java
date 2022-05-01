package example_decorator.SimpleAsDoor;

public class Car {
    String brandName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void go(){
        System.out.println("I'm " + getBrandName() + " and I'm on my way...");
    }
}
