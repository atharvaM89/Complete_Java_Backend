public class test {
    public static void main(String args[]){
        Engine e=EngineFactory.getEngine("petrol");
        Car car=new Car(e);
        car.Drive();
    }
}
