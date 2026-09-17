package org.cfs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        //here in ClassPathXml.. we give the configuration file i.e our Beans.XML
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
        Motor m=context.getBean(Motor.class);
        m.doWork();

        context.close();  //Destory Method
    }
}
