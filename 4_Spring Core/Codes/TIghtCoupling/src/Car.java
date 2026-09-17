public class Car  {
    Engine e;

    public Car(Engine e) {
        this.e = e;
    }

    public void Drive(){
        e.Start();
        System.out.println("car moving..");
    }
}
