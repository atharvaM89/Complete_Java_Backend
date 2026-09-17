# Spring Core

- Spring is a Framework, Unlike Java (Programming Language

# **Framework**

- A framework is a **semi-developed software** that provides **common logic** required for project development.
- It helps developers to **implement more functionality in less time**.
- The code which is used repeatedly is called **boilerplate code**.
- When we use a framework, we can focus **only on business logic**, and the framework handles common tasks.

![image.png](../../Images/4.%20Spring_Core/image.png)

- For an example we have a directory structure in that in one folder we store java files,
in another folder we store Resources
in another folder we store dependency like POM.xml
- so Basically these directory structure will help us to run our program on a local host properly
- later we use some annotations like 
@webServlet- with the help of that annotation we eliminate to much efforts
- like that spring frame work will provide too many methods that help to do our project in very efficient way

### Types of Framework

1. Frontend Framework- Angular
2. Web Framework- Struct (Outdated)
3. ORM Framework- Hibernate (Outdated) → Spring data JPA (Which is build on top of Hibernate)

- By using Struct (It is outdated )we can develop only web layer in the Project (Controllers)
- By Using JPA we can develop only data access layer (Persistent layer)

### Layers

- Whenever any backend application is running that is running in 3 layers
1. Web Layer (Controller)
2. Business Layer (Service Layer)
3. Data Access Layer (Data persistence layer)
    
    ![Screenshot 2026-01-02 at 10.56.12 AM.png](../../Images/4.%20Spring_Core/Screenshot_2026-01-02_at_10.56.12_AM.png)
    
- So this is a basic architecture of a Application, in which how layers work

Working

## 1. Controller Layer

- When ever UI Request some data or request form the Backend then firstly that request goes to Controller layer
    - Similar to servlet when we are requesting some data like /login then firstly we check the URL Mapping, ie which servlet map to which end point
    - Controller layer will contain mapping of components and methods
    - So, basically Controller will check the mapping of which component / method with which end point
- So after checking the mapping it goes directly to the Service and jump to Data Access layer 
after that it goes to Database and from database it get the requested data and send to the UI 
via all these 3 layers

Note

- To Overcome the problem of Struct framework(which is deployed only in web layer) 
spring framework come into market

# Spring Framework

- Spring Framework is called as application development framework
    - By using Spring Framework we can deploy end  to end application
    end to end (means to each layer to end layer and to deployment)
- like in servlet we need Tomcat to deploy our application, but in Spring we do not need such thing it will manage all by it own
- Means Spring Give ur direct URL which we can directly HIT to postman
which is our end to end backend Application, in this we do not need a single extra component in this
- Spring Framework is free and Open Source
- Spring Framework is developed in modular fashion because this modular development it is loosely coupled (System are connected but have minimal dependencies on each other, allowing them to function independently)
    
    ![Screenshot 2026-01-02 at 11.24.51 AM.png](../../Images/4.%20Spring_Core/Screenshot_2026-01-02_at_11.24.51_AM.png)
    
- Like this Spring Core is a Common Module which is used every where
- So, we just need  Spring Core Knowledge to work on spring framework
- We do not need to learn entire things we just need to learn those things which we need

## Spring Modules

- Spring core
- Spring context
- Spring JDBC
- Spring ORM
- Spring AOP
- Spring web MVC
- Spring security
- Spring Batch
- Spring Data JPA
- Spring Rest
- Spring Test
- Spring Cloud
- Spring AI

Note

- Spring is very Flexible Framework
- It will not force to use all modules
- Based on requirement we can pick up particular module and we can use it.
- Spring is Versatile Framework ( it can easily be integrated with other Framework )
- The current version of Spring Framework is 6.X
- Spring Framework is under Licences 0 VM ware Tanza

### 1 .Spring Core

- It is Base Module in Spring Framework, for every module we need spring core
- Spring core module provide fundamental concepts of spring Framework
    - Like IOC Container (Inversion of Control ) it is very imp
    - Dependency Injection
    - Bean Life Cycle Management
    - Bean Scope
    - Auto wiring, etc..

### 2 .Spring Context

- It will deal with configuration required for our Spring Application

### 3. Spring AOP (Aspect Oriented Programming)

- Aspect - Some important parts like object, methods
- [**पहलू](https://www.shabdkosh.com/dictionary/hindi-english/%E0%A4%AA%E0%A4%B9%E0%A4%B2%E0%A5%82/%E0%A4%AA%E0%A4%B9%E0%A4%B2%E0%A5%82-meaning-in-english)  oriented Programming**
- If we want to Implement business logic and some secondary logic then we face maintenance issue of our project then we use AOP to separate business logic and secondary logic

### 4. Spring JDBC

- It is used to simplify database communication logic
- In java JDBC we need to write boiler plate code (Code which is repeated) like below in several classes
    1. LOAD DRIVER
    2. GET CONNECTION
    3. CREATE CONNECTION
    4. EXECUTE QUERRY
    5. CLOSE CONEECTION
- Using Spring JDBC  we can directly execute query, remaining part spring JDBC will take care

### 5. Spring MVC (Model view Controller)

- It is used to develop both (Web Application & Distributed Application)
    - Web Application- Customer to Business (ECOM)
        - This type of Application get available on browse like ECOM
        - Only 1 Server
        - End User Use this using UI
    - Distributed Application - B2B / web services / Restful Services  (IRCTC)
        - Application which depends on multiple Nodes
        - It is a System where components are spread across multiple nodes (system) but work together as a single system
        - like MicroServices
        - Have Multiple Server
        - Not Compulsory to use Via UI, Services talk to other (Micro services talk with each other)

### 6. Spring ORM (Object Relation Mapping)

- Spring Framework having integration with ORM Frameworks
- If we want to make connection with Database
- JDBC will represent data in text format, where as Hibernate ORM will represent data in Objects format

Example using JDBC

```jsx
executeQuery(query)

While(rs.hasNext()){
rs.getString(name)
rs.getInt(id)
}
```

Example using Hibernate

```jsx
Student s=JPA.get()
//reduce mannual efforts
```

### 7. Spring Security

- Using Spring Security we can implement Authentication and Authorization
- Spring Security with Oauth 2.0 (sometimes login with google, some times with Phone no)
- Spring Security with JWT (JSON web Tokens)

### 8. Spring Batch

- Batch means Bulk operation
- When we need some data in huge quantity that time we use Spring Batch
- Reading data from excel and store it into database table
- Sending Monthly Statement to customer in Email
- Sending Reminder to customer as Bulk SMS

### 9. Spring Test

- In automation and testing we need to implement JUNIT
- So, Spring Provide Unit test Framework

# Spring Core Classes

- Spring Core is all about managing dependencies among the classes with loose coupling
- In project we will develop several classes. All those classes we can categorize into 3 types
    1. POJO
    2. Java Bean
    3. Component

## 1. POJO (Plain old java Object)

- Any java class which can be compiled by only using JDK is called a POJO Class
- ex-

```jsx
//Example 1
Class Demo{
	int id;
	String name;
}

//Example 2
Class Demo2 extends Thread{
	int id;
	String name;
}

//Example 3
class Demo3 implements Runnable{
	int id;
	String name;
	
	run(){
	}
}

//Example 4

//This is not a valid POJO
//Java Does not have compatablity to compile this class, for that we need to import library
//Whenever we extends Generic Servlet that time that class is compiled by TomCat not by java 

class Demo3 extends GenericServlet{
	int id;
	String name;
	
	run(){
	}
	
	service(){
	}
}
```

### Java Beans

- Any Java Class which follows bean specification rules is called as Java Beans
    1. Class should implement serializable interface
    2. Class should have private data member (variables)
    3. Every private Variable should have public getter and Setter
    4. Class should have zero- Param (Default) Constructor
- Bean Classes are used to write business logic and to store and retrieve data

### Component

- The Java Classes contains business logic is called as component classes
- ex- Controller, Service

Tight Coupling

- One class is Completely Depended on Another

ex- 

```jsx
public class Car  {
    Engine e=new Engine();

    public void Drive(){
        e.Start();
        System.out.println("car moving..");
    }
}

public class Engine {
    public void Start(){
        System.out.println("Engine Started");
    }
}

public class PetrolEngine {
    public void Start(){
        System.out.println("Engine Started");
    }
}
```

- here car class is tightly coupled with engine class
- We can’t change Engine Type
- we can’t test the car without engine
- No flexibility

```jsx
public interface Engine {
    public void Start();
}

public class Car  {
    Engine e=new DiselEngine();

    public void Drive(){
        e.Start();
        System.out.println("car moving..");
    }
}
```

- In this we use Interface, but still we have to make object hardcoded
- Flexible but still poor

Factory Design Pattern

```jsx
//Class EngineFactory
public class EngineFactory {
    public static Engine getEngine(String type){
        if(type.equalsIgnoreCase("disel")){
            return new DiselEngine();
        }
        else if(type.equalsIgnoreCase("petrol")){
            return new PetrolEngine();
        }
        else{
            throw new IllegalArgumentException("Invalid Engine Type");
        }
    }
}

// class Car
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

//class test
public class test {
    public static void main(String args[]){
        Engine e=EngineFactory.getEngine("petrol");
        Car car=new Car(e);
        car.Drive();
    }
}
```

![Screenshot 2026-01-02 at 4.06.26 PM.png](../../Images/4.%20Spring_Core/Screenshot_2026-01-02_at_4.06.26_PM.png)

- Conside We have 2 class Car and Engine
- Factory gives a object of Engine to Car
- That is Dependency Injection

### Dependency Injection

- When ever any class need a object at that time give object to that class is Known as Dependency Injection
- For ex- Class factory Gives Object to class Car That is Dependency Injection
- The Process of Injecting one class object into another class is called as Dependency Injection
- We can Perform Dependency Injection in 3 Ways
    1. Setter Injection
    2. Constructor Injection
    3. Field Injection

### Need of Spring Core

- Consider like we want dependency Injection but we don’t use factory Methods that so that where Spring Core is Use

# Another Basic Project for Understanding

Class Student

```jsx
package org.example;

public class Student {
    private Course course;  //we need object of Course type

    public void study(){
        int start= course.enroll();
        if(start>=1){
            System.out.println("Journey Started");
        }
        else{
            System.out.println("Payment Failed... ");
        }
    }
}
```

Interface Course

```jsx
package org.example;

public interface Course {

    public int enroll();

}
```

Class DSA_Course

```jsx
package org.example;

public class DSA_Course implements Course{
    @Override
    public int enroll() {
        return 1;
    }
}
```

class Java_Fullstack

```jsx
package org.example;

public class Java_FullStack implements Course{
    @Override
    public int enroll() {
        return 1;
    }
}
```

class APP

```jsx
package org.example;

public class App {

    public static void main(String[] args) {
        Student s=new Student();
        s.study();
    }
}
```

- In this there is Error After running these there will be an error in the Student Class of Null Pointer Exception
- To solve this Problem we use Dependency Injection

# Dependency Injection

## 1. Setter Injection

Class Student

```jsx
package org.example;

public class Student {
    private Course course;  //we need object of Course type

    public void setCourse(Course course) {
        this.course = course;
    }

    public void study(){
        int start= course.enroll();
        if(start>=1){
            System.out.println("Journey Started");
        }
        else{
            System.out.println("Payment Failed... ");
        }
    }
}

```

Interface Course

```jsx
package org.example;

public interface Course {

    public int enroll();

}
```

Class DSA_Course

```jsx
package org.example;

public class DSA_Course implements Course{
    @Override
    public int enroll() {
        return 1;
    }
}
```

class Java_Fullstack

```jsx
package org.example;

public class Java_FullStack implements Course{
    @Override
    public int enroll() {
        return 1;
    }
}
```

class APP

```jsx
package org.example;

public class App {

    public static void main(String[] args) {
        Student s=new Student();

        Course course=new DSA_Course();//There is Dependency Injection
        s.setCourse(course);

        s.study();
    }
}

```

- Here we Define a Setter in Student class and in APP Class we use that setter using object of the Course (DSA Course / Java_fullstack)
- This is Also Known as Manual Injection

- While Programming in Spring Core we Do not need to DO this Manual Dependency Injection, this injection is done Automatically by the Spring Core

## 2. Constructor Injection

Student Class

```jsx
package org.example;

public class Student {
    private Course course;  //we need object of Course type

    //This is for Constructor Dependency
    public Student() {
    }

    public Student(Course course) {
        this.course = course;
    }

    //This is For Setter Dependency
    public void setCourse(Course course) {
        this.course = course;
    }

    public void study(){
        int start= course.enroll();
        if(start>=1){
            System.out.println("Journey Started");
        }
        else{
            System.out.println("Payment Failed... ");
        }
    }
}
```

APP Class

```jsx
package org.example;

public class App {

    public static void main(String[] args) {
        Student s=new Student(new DSA_Course());

        //for Setter Dependency
        /*Course course=new DSA_Course();
        s.setCourse(course);*/

        s.study();
    }
}
```

- In this we Create a Constructor in Student Class and inside App Class Use that Constructor to pass the Object of that class
- This is also a Manual Injection, and we don’t need to implement it manually, Spring core will handle it Automatically

## 3. Field Injection

Class Student

- for this class must be public

```jsx
package org.example;

public class Student {
//Insted of making class private we make it here public to acces the object
    public Course course;  //we need object of Course type

    //This is for Constructor Dependency
    public Student() {
    }

    public Student(Course course) {
        this.course = course;
    }

    //This is For Setter Dependency
    public void setCourse(Course course) {
        this.course = course;
    }

    public void study(){
        int start= course.enroll();
        if(start>=1){
            System.out.println("Journey Started");
        }
        else{
            System.out.println("Payment Failed... ");
        }
    }
}
```

Class APP

```jsx
package org.example;

public class App {

    public static void main(String[] args) {
        Student s=new Student();
        s.course=new DSA_Course();

        //for Setter Dependency
        /*Course course=new DSA_Course();
        s.setCourse(course);*/

        s.study();
    }
}
```

- In manual Injection we need to make variable public otherwise it will not available outside the class
- If we not make it as public then we have only 2 ways
Setter Dependency
Constructor Dependency
- But in Spring Core we do not need to make Field Public, It will handle Automatically internally

- Spring Core Uses JAVA Reflection API to inject dependency, which allows it to access and modify private fields and methods, even though they are marked private.

```jsx
field.setAccessible(true);
```

This line tells the JVM:

> “Ignore Java access modifiers (private/protected) and allow access.”
> 

### Reflection API

**Reflection API** in Java allows a program to **inspect and modify classes, methods, fields, and constructors at runtime**, even if they are marked **private**.

- It is provided by the package **java.lang.reflect**.

---

**Why it is used**

Frameworks like **Spring** use Reflection to:

- Create objects automatically
- Inject dependencies
- Access private fields and methods
- Manage bean lifecycle

---

Reflection bypasses access modifiers using:

```jsx
setAccessible(true)
```

# IOC Container (Inversion of Control)

- It is Responsible for dependency Injection in Spring Application
- Dependency Injection Means Creating and Injecting Dependent Bean Object Into Target Bean Class
The Class Which is Compiler by java is also called as Java Bean class and Object of that is Called as Bean Object

Note-

- IOC Container will manage Life Cycle of Spring Bean
- We need to Provide “Java Classes (POJO Classes) + Bean Configuration (Meta Data)” as a input for IOC then IOC will Perform Dependency Injection and perform Spring Bean which are ready to use

![image.png](../../Images/4.%20Spring_Core/image%201.png)

- So Spring Container Will Get Document (Configuration for Meta Data), It will ready from Java Classes (POJO) and Perform Dependency Injection (Full Configured System)

### Spring Bean

- Any Java Class Whose Life Cycle (Creation to Destruction ) is Managed by IOC (Inversion of Control ) is called as Spring Bean
- We can represent Java Class as a Spring Bean in 2 Ways (Similar to Servlet)
    - XML Approach - Outdated
    - Annotation Approach- Recommended

Note

- In Spring we can use Both XML and Annotation Approaches but Spring Boot Only Support Annotation, Does not Support XML

## Types of IOC Container

1. Bean factory. (Outdated)
2. Application Context (Recommended)

Example- 

```java
Application Context = new ClassPathApplicationContext(String configFile)
```

# First Spring Application Development

Pre-Requisites: 

- JDK
- IDE

1. Create Maven Project in IDE (web Type OR Quick Start) both will work
2. Add Spring Context Dependency in POM.XML File

Go to Browser and Search for Spring Context maven Dependency
Select any Maven Dependency ex 6.2.9 and Copy and Maven Dependency
like this
    
    ![image.png](Spring%20Core/image%202.png)
    

```java
<!-- [https://mvnrepository.com/artifact/org.springframework/spring-context](https://mvnrepository.com/artifact/org.springframework/spring-context) -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-context</artifactId>
<version>6.2.14</version>
</dependency>
```

1. Create Required Java Classes
2. Create Bean Configuration file and Configure bean Defination
(Like After Creating XML file in Servlet we also Give Mapping defn)

For that Search bean Configuration in Spring XML schema

![image.png](../../Images/4.%20Spring_Core/image%203.png)

![image.png](../../Images/4.%20Spring_Core/image%204.png)

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!-- bean definitions here -->

</beans>
```

## Demo

1. Create new Maven Project and Select ArchType as Quick Start
2. Open pom.xml and inside dependency Replace Old Dependency with new Dependency

![image.png](../../Images/4.%20Spring_Core/image%205.png)

- Like in These Dependencies These Dependency is Replaced with new Dependency
    - After that Refresh Maven

![image.png](../../Images/4.%20Spring_Core/image%206.png)

1. After that Inside src/main/java remove that folder and Create your own folder with your name
2. Inside Main Create new Directory Resource (It shows Suggestion)
3. In Resource Create new File Beans.xml
like these Folder Structure is Formed

![image.png](../../Images/4.%20Spring_Core/image%207.png)

- Inside this Beans.xml Paste the XML schema of the spring

![image.png](../../Images/4.%20Spring_Core/image%208.png)

- Lets Consider the Same Example for Dependency Injection of Car

car Class

```java
package org.cfs;

public class Car {

    private Engine engine;

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
```

Engine Interface

```java
package org.cfs;

public interface Engine {

    public int start();
}
```

Petrol Engine Class

```java
package org.cfs;

public class PetrolEngine implements Engine{

    @Override
    public int start() {
        return 1;
    }
}
```

Disel Engine Class

```java
package org.cfs;

public class DiselEngine implements Engine{

    @Override
    public int start() {
        return 1;
    }

}
```

Test Class

```java
package org.cfs;

public class Test {
    public static void main(String[] args) {

    }
}
```

- After this we need to Give mapping inside the Beans.XML
- In <beans> </beans> we will use bean

```java
<beans>

<bean id="petrol"> ….. </bean>

</beans>
```

- This is Also Like JS and HTMl 
for Uniquely Define We add Id  and class  to Identify it
- Id - means Unique name to identify it uniquely
- class - path of the file like (in which directory it is located)
org.cfs.petrolEngine

![image.png](../../Images/4.%20Spring_Core/image%209.png)

- This <bean> is Called as Bean Defination because we are giving it in Bean Configuration

![image.png](../../Images/4.%20Spring_Core/image%2010.png)

- For This We have Completed Bean Configuration and Java POJO
- And we also give the definition of Java POJO Class inside the Bean Configuration class, because spring framework will consider it as a Spring Bean to make mapping of Java POJO and Bean Configuration
- Now this PetrolEngine class will react as Spring Bean or Spring Framework

### Internal Working

- When you give Configuration for beans, then the system get knows there is one POJO class PetrolEngine of Type Engine
- You just define ID i.e petrolEngine and you need to tell spring Configuration that where this object need to map

- Now in Config We Only define One class ie petrol class, so now we also need to add Bean Definition in Configuration, i.e we need to add the definition of Car class also
    
    ![image.png](../../Images/4.%20Spring_Core/image%2011.png)
    

![image.png](../../Images/4.%20Spring_Core/image%2012.png)

![Screenshot 2026-01-05 at 9.56.30 PM.png](../../Images/4.%20Spring_Core/Screenshot_2026-01-05_at_9.56.30_PM.png)

- Now We need to Tell Petrol Engine has need in Car class (Which is Dependency)
    - For that we will use Another tag inside a Car class

## Difference Between Multiple IOC Container

### Bean Factory and Application Context

Bean Factory

- It will Follow Lazy Loading that means when we request the Object then only it will create bean Object

Application Context

- It will follow Eager Loading means Creating Object for Spring Bean when IOC Start

- Eager Loading- Means Creating a Object for Spring Bean When IOC Start
- Lazy Loading - Means Creating Object for Spring Bean when we call getBean() Method

```java
//Any Bean IOC is Creating is Singleton Object
Car car=context.getBean(Car.class)

//jaise hi aap Get Bean Krate ho IOC yeska Object Bna Deta hai
```

- WhenEver some object is Created (Spring Bean) that time it’s Default scope is Singleton

```java
//It is a Way of Writing bean Factory 
BeanFactory factory=new XmlBeanFactory(new ClassPathResource(”your-beans.xml”));
```

- The XmlBeanFactory class in Spring Framework was Deprecated After version 3.1
- And Instead of XML beanFactory ClassPathXmlApplicationContext was introduced

```java
ApplicationContext context=new ClassPathXmlApplicationContext(””);
```

- Now Along With Bean Factory Lazy Loading is also Deprecated means it is not supported ony Eager loading is Supported

## How to Differentiate Setter Injector and Constructor Injector in Bean File

- <property> tag- tag used for Setter Injection
- <constructor-arg> - use for Constructor injection

# Spring Scope (Bean Scope)

- Scope Define how many objects of spring beans are created within the container

## ***Types of Scope***

- Singleton *
- Prototype *
- Request
- Session
- WebSocket

- By default scope is Singleton

***Real World Ex***

### 1. Singleton Scope-

- One Engine for one type of Same Car
- Only One object is Created

### 2. Prototype - New Engine for each car

- How many times you are requesting new object you will get new object

```java
  System.out.println("------First Call -------");
        Car car1=context.getBean(Car.class);

        System.out.println("------Second Call -------");
        Car car2=context.getBean(Car.class);

        System.out.println("same Object" + (car1==car2));
```

- This Means our object is same every time we call it

Note

- Whenever by default singleton object / Spring bean is created then that time Default beans / object get destroyed but the Prototype bean / object is not destroyed by spring IOC
- If Clean up is Need Manual Destruction is Required

### **3. Request Scope**

- For each HTTP request

### **4. Session Scope-**

- For Each session new Object is Created

### **5. Web Socket -**

- It is a bi-directional Communication Between Client and Server
- Almost every Realtime Communication is done with WebSocket
- For Each WebSocket will create new Object

# AutoWiring in Spring Core

- AutoWiring is a way to automatically inject dependencies into a bean without Explicitly specifying <property> tag

```java
//ex
<property name="engine" ref="petrolEngine"></property>
```

- This is how we inject dependency Manually

```java
<bean id="petrolEngine" class="org.cfs.PetrolEngine" />
    <bean id="car" class="org.cfs.Car">
       <property name="engine" ref="petrolEngine"> </property>
    </bean>
```

***Note-***

- It tells the IOC Container, to Inject the Suitable Bean Based on name, Type and Constructor parameter

## Modes of AutoWiring

- Modes means kis trah se kya pahechan ke
- Means how to identify to inject object, it is only a instructions
- For Injecting Objects there are only 3 ways
    - Setter
    - Constructor
    - Field

### 1. By name

- Matches by property name
- Just Consider ki. yeh nam se inject kr deta hai
    
    ```java
    ex
    (engine) (<property name="engine"/>). 
    -> identify using bean id=enginer
    ```
    

for ex

```java
//Among these 3 defn which are dependent
   <bean id="engine" class="org.cfs.PetrolEngine" />
   //here id and Object type should be same 
   //means we create the Object of Engine as engine so name of that object and id should be same
    <bean id="diselEngine" class="org.cfs.DiselEngine" />
	    <bean id="car" class="org.cfs.Car" autowire="byName "/>
    
//Here Car is Dependent on Engine so here we add Autowire in car defn 
```

### 2. By Type

- Matches by class, Interface
- For ex - our object is Engine Type and PetrolEngine is Implementing the Engine Interface

- In this This Inject Dependency based on the type of the Object
- for ex- IOC Create have 3 different objects
obj of Petrol Engine, Disel Engine, Car so among these 3 objects IOC will get Confused
- so IOC will be Confused between Multiple Objects

- If we want object of PetrolEngine for that there is one Property autowire-candidate=”false”

```java
 <bean id="engine" class="org.cfs.PetrolEngine" />
    <bean id="diselEngine" class="org.cfs.DiselEngine" autowire-candidate="false"/>
    //here by setting it by false we tell that do not consider this object , consider another available object
    //<!--<bean id="car" class="org.cfs.Car" autowire="byName"/>-->
    <bean id="car" class="org.cfs.Car" autowire="byType"/>
```

### 3. Constructor

- Inject Dependency via Constructor
    
    ```java
        <bean id="engine" class="org.cfs.PetrolEngine" />
        //<!--<bean id="car" class="org.cfs.Car" autowire="byName"/>-->
        <bean id="car" class="org.cfs.Car" autowire="constructor"/>
        //here we just need to define that we need to autowire by constructor and there should be constructor present in car class
    ```
    

### 4. No AutoWiring

- It is Default AutoWiring
- If specifying AutoWiring as No, it do not make any sense

# Spring Bean Life Cycle

- Journey of Spring Bean Form Creation to Destruction Managed by spring IOC Container

### Steps in LifeCycle

1. Object Creation
2. Property Setting (Dependency Injection )
3. Initialization (Custom Logic)
4. Business Logic
5. Destruction

## Basic Example

class Motor

```java
package org.cfs;

public class Motor {
    public Motor(){
        System.out.println("Motor COnstructor");
    }
    public void start(){
        System.out.println("Motor Started..");
    }
    public void stop(){
        System.out.println("motor Stoped");
    }
    public void doWork(){
        System.out.println("Motor is pumping water..");
    }
}
```

class test

```java
package org.cfs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    public static void main(String[] args) {
        //here in ClassPathXml.. we give the configuration file i.e our Beans.XML
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("Beans.xml");
        
        Motor m=context.getBean(Motor.class); //init Method
        m.doWork();

        context.close();  //Destory Method
    }
}
```

Beans.XML (<bean>)

```java
    //<!-- init method means which methods will run first -->
    //<!-- destroy method means which methods will run last -->
    //<!-- Basically this init method and destroy method will define the complete lifecycel of the Spring Bean   -->
    <bean id="motor" class="org.cfs.Motor" init-method="start" destroy-method="stop"> </bean>
```

output

```java
Motor COnstructor
Motor Started..
Motor is pumping water..
motor Stoped
```

- So here init method used to Specify which method will run at the Start of the Spring Bean
- and Destroy Method will tell which method will run at the end of the Spring Bean