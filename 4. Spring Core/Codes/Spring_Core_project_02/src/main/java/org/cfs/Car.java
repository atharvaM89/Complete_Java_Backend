package org.cfs;

public class Car {

    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public Car(){

    }

    public void setEngine(Engine engine) {
        System.out.println("SetEngine() method called...");
        this.engine = engine;
    }

    public Car(Engine engine){
        System.out.println("Car Constructor");
        this.engine= engine;
    }

    public void drive(){
        int start= engine.start();
        if(start>=1){
            System.out.println("Let;s Drive");
        }
        else{
            System.out.println("Engine Not Started yet");
        }
    }
}
