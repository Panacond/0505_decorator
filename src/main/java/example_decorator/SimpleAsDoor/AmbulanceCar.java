package example_decorator.SimpleAsDoor;

public class AmbulanceCar extends DecoratorCar{
    public AmbulanceCar(Car decoratorCar) {
        super(decoratorCar);
    }

    @Override
    public void go() {
        System.out.println("... beep-beep-beeeeeep ...");
    }
}
