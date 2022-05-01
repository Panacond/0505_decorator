package example_decorator.SimpleAsDoor;

public class Road {
    public static void main(String[] args) {
        Car mercedes = new Mercedes();
       mercedes.go();
       Car ambulance = new AmbulanceCar(new Mercedes());
       ambulance.go();
    }


}
