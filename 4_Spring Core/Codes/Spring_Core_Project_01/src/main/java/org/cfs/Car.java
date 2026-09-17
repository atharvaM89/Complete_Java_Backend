package org.cfs;

public class Car {

    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
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
