package example_decorator.SimpleAsDoor;

public class DecoratorCar extends Car{
    Car decoratorCar;
    public DecoratorCar(Car decoratorCar){
        this.decoratorCar = decoratorCar;
    }

    @Override
    public void go() {
        super.go();
    }
}
