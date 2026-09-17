package org.cfs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");

        System.out.println("------First Call -------");
        Car car1=context.getBean(Car.class);


        car1.drive();
    }
}
