# Spring Boot

- Spring boot is One Approach to develop spring based Application with less configurations
- Spring Boot = Spring FrameWork - XML Configuration 
Here we do not need to make any Configuration in Spring Boot it will automatically do that
- SpringBoot is not Replacement for Spring Core / FrameWork
- SpringBoot is Developed on Top of Spring Framework, to make Things Easier

- Current version of Spring Boot is 4.x on may 25
- Java 17 is Mandatory to work with Spring Boot 3.x version

Note-

- All Spring Framework Concepts is used in SpringBoot

 

Advantages-

- Less Configuration Compared to Spring Core and No XML configuration Required
- We can add POM Starters to simplify dependencies Configuration
    - Spring-boot-starter-web
    - Spring-boot-starter-jpa (replace JDBC)
    - Spring-boot-Starter-Security
    - Spring-boot-Starter-mail

- Before Spring Boot we need to Mention all the Maven dependencies in POM.XML for ex different dependencies for Spring Core, data, mvc and all
- So in Spring Boot in POM.XML we place multiple Dependencies File in One Started named as Starter and that Starter is placed inside the POM.XML
- Auto Configuration, we do not need to Specify dependencies and all
- Contains Embedded Server (Tomcat, Jetty, netty ) reduce manual deployment
- Actuators (It is Production Ready Feature ) - used as for Monitoring and Getting Status of all spring Beans

Note-

- Spring Boot makes it easy to create stand alone, production- grade Spring based application that you can just run

# Spring Boot Architecture

![image.png](../../Images/5.%20Spring_Boot/image.png)

- Presentation Layer (Controller / API )- Front end layer by which User Interact with backend
- Service Layer (Businees Layer)- Used to Represent Business Logic
- Repository (DAO) - Interaction with Database using JPA instead of JDBC
- Spring Boot Core - It is Core of Spring Boot , not spring core, it manages all Auto Config, Starter, etc
- Embedded System - Other thing helps in Deployment like Tomcat

# Spring Boot Application Creation

- We can create Boot Application in 2 ways
    1. Spring Initializer Website (start.spring.io)
    2. Using IDE

## 1. Using Spring Initializr

- Search for Spring Initializr on any Browser
    
    ![image.png](../../Images/5.%20Spring_Boot/image%201.png)
    
- For Configuration it will Similar which is Configured by POM

![image.png](../../Images/5.%20Spring_Boot/image%202.png)

![image.png](../../Images/5.%20Spring_Boot/image%203.png)

- These are the Configurations we need to Set here by combination of Group Id, ArtiFact Id and Version it Create a jar (Java Archive File )
- In dependency add that which you need (if you want to make RestFull Api then select web)
and when you explore in pom.xml you will see spring-boot-starter-web (artiFact) in dependencies
- and Generate it

- This is a File Structure

![image.png](../../Images/5.%20Spring_Boot/image%204.png)

- This File Structure is Created, just Remember all Packages is Generated in that org package and our main class (which contain @SpringBootApplication) is Outside the all packages
- This is a Production Ready Layer

![image.png](../../Images/5.%20Spring_Boot/image%205.png)

- For Understand consider this Structure
- In Spring Boot all things are Annotation Based

Interface MessageService

- Do not need to add any Annotations on Interface

```java
package org.cfs.Boot_P01;

public interface MessageService {
    String sendMessage();
}
```

class EmailService

- This Class Contains Business Logic
- so This will contain Annotation
@Service

```java
package org.cfs.Boot_P01;

@service
public class EmailService implements MessageService{
    @Override
    public String sendMessage() {
        return "Email: You have got a new message! ";
    }
}
```

Class Notification

- It is not a business logic neither a Database Logic then it is a Component
- So we will add Annotation 
@Component

```java
package org.cfs.Boot_P01;

public class Notification {
    //Creating obj of MessageService
    private MessageService messageService;

    //default Constructor
    public Notification(){

    }

    //getter
    public MessageService getMessageService() {
        return messageService;
    }

    //setter
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    //Argument Constructor
    public Notification(MessageService messageService){
        this.messageService=messageService;
    }

    public void notifyUser(){
        System.out.println(messageService.sendMessage());
    }
    //DI here it need obj of Class EmailService
}
```

main Class

- In main Class there should be a Annotation 
@SpringBootApplication

```java
package org.cfs.Boot_P01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootP01Application {

	private Notification notification;

	public BootP01Application(){

	}

	public BootP01Application(Notification notification){
		this.notification=notification;
	}

	public static void main(String[] args) {

		SpringApplication.run(BootP01Application.class, args);
	}

	public void run(String... args){
		notification.notifyUser();
	}
}
```

Annotation

- main class - @SpringBootApplication
- Business Logic Class - @Service
- Component Class (neither Business nor database ) - @Component
- DataBase Layer- @Repositery
- Interface- No Annotation

![image.png](../../Images/5.%20Spring_Boot/image%206.png)

- Here Message Service is Interface Implemented by Email Service
- Notification had Dependency of MessageService
- So Indirectly Email service is also Dependent on Notification

How it Works in Background

- In Spring Core we give each dependency in Bean.XML using by name , by type
- But In Spring boot it manage automatically
    - When we give Annotation to class as @service and @Componenet that time it know IOC run these 2 classes in Background
    - and When you specify @Autowire that time it found which class need object of another class
    - for ex we define @Autowire in Notification class then MessageService messageService the it directly inject the dependency for the MessageService Object in the Notification Class

### File Structure

![image.png](../../Images/5.%20Spring_Boot/image%207.png)

- Src/Main/java → TO keep our project source code
                              Application.java
- Src/Main/resource → To keep project configuration files
                                       -Application.properties / yml
- Src/test/java → To keep JUnit code (Unit testing)
                           ApplicationTest.java
- Src/test/resources → Unit testing Related config
- External Library → Dependencies / jar Required for Project
- Pom.XML → maven Configuration file

### Main Class

![image.png](../../Images/5.%20Spring_Boot/Spring%20Boot/image%208.png)

- When ever we run our spring boot application then that application will run from this file
- This is an Entry point for Spring Boot Application

### Run Method (Internal flow of Spring boot App)

- This method is Present in main method of Spring Boot Application
- `SpringApplication.run(BootP02Application.class, args);`
    - When This Method runs this method Creates IOC Container
    - Scan for Annotations like @Component or @RestController
    - Then it will auto-config beans (like Tomcat)
    - Register dispatching Servlet ( Use for deployment of Applications on local host)
    - Start Embedded tomcat Server
    

### @SpringBootApplication

- This Annotation is equal to Below 3 Annotation
    - @SpringBootConfiguration
    - @EnableAutoConfiguration
    - @ComponentScan

**Note-**

- All Spring Annotations life cycle is handled by IOC
- All Annotations tell IOC that they Become Spring Bean So IOC will handle them
- Each Annotations has their own Meaning

**Requirement**

- If we need to represent java class as configuration class then we will use @Configuration

- @SprinbootConfiguration - @SpringBootApplication this Annotation Internally pointing to *@Configuration* this Annotation
- Form this we can say our main class where *@SpringBootApplication* is Present is acting as Configuration Class
- So our Spring Application’s main class will act as Configuration Class due to *@SpringBootApplication*

- In SpringBoot application AutoConfiguration feature will be available because of *@EnableAutoConfiguration ,* It is used to perform auto-config

- *@ComponentScan-* This will starts to base Scan for *@Component, @Repository, @Service* and another Annotations

### Difference Between AutoWiring and AutoConfig

- AutoWiring-
    - When IOC has Different Different classes at that IOC get to know by reading  annotation it found which class require dependency of another class
    - When One class require dependency of another class that at that time which class require dependency we define *@AutoWiring*  in that Class
    - For AutoWiring Bean are Required
- AutoConfig-
    - When we Working on SpringBootApplication at time we require multiples jar files which should be present in External Library this is because of Dependency which is Present is Pom.xml
    - When that all jar files comes to Class Path and create bean of all jars and provide to IOC on some Conditions is called as AutoConfiguration

- In External Library we have Multiple Jar (.class) files
- When we define dependency (Spring-boot-starter-web) this contains multiples Jar files
- For ex We are Working on some Class (Web) and this Require dispatcher Servlet at that time We load the bean of TomCat
- If some Where dispatcherServlet is Coming then we load Web-MVC-Configuration
- So AutoConfiguration is Nothing but loading the Required Bean not all beans
- So it loads beans  Automatically

Defining Port for Running Spring Application

- By Default our Spring Boot Application will run on 8080 Port
- if we want to change that port no then go to [application.properties](http://application.properties) and ADD below Line
- server.port=8081

## Stereo Type Annotations

- This is a Spring Managed Component
It tell Spring to Detect and register it as a Bean Inside IOC Container

| **Annotations** | **Purpose** | **Layer** |
| --- | --- | --- |
| @Component  | - Generic Spring Managed Bean  | - Any Layer |
| @Service | - If we want to make Service
- Write Business Logic | - Service Layer |
| @Repository | - To make a DAO ( Data Access Object ) class | - Data Layer / Repo  |
| @Controller | - Mark a Web as MVC Controller (Part of Spring MVC ) | - Web Layer |
| @RestController | - This is a Advanced part of Controller
- Combines @Controller + @Responsebody | - Rest APIs |

**Note-**

- All of them are meta Annotated with @Component
means these all Annotations are part of @Component
- So this is Why Spring Registers Every Annotations on Bean and Give it to the IOC
- This is why Spring Picks them up during @ComponentScan

## URL Mapping

- For ex consider from Servlet when we give any mapping in XML and hit that methods that time that method hit with some Method
- Similar here when Give URL Mapping in SpringBoot
- like Post→ /test
- then this /test must be visible on Browser
- But for that when we Give that Mapping Spring give this to doGet() and doPost() method
- Basically these 2 Methods checks Weather any Method available for that mapping or not
- So for mapping, Method Must be Present

### Methods

1. @GetMapping(”/welcome”)

```java
@RestController
public class StudentController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Atharva";
    }
}
```

![image.png](../../Images/5.%20Spring_Boot/image%209.png)

- This is used to Fetch the Method on Browser using API (This is Rest API)
1. @PostMapping
    - When Ever you want to send data to body, parameter,.. you use this
    - Used to Create new data on Server
    
    ```java
    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
    return "User Created";
    }
    ```
    
2. @DeleteMapping
    - Used to delete Existing data using an Identifier
    
    ```java
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable int id) {
    return "User Deleted";
    }
    ```
    
3. @PatchMapping
    - Used to Partially update Existing data
    
    ```java
    @PatchMapping("/users/{id}")
    public String updateUserName(@PathVariable int id, @RequestBody User user) {
    return "User Updated";
    }
    ```
    

### Basic Example

→ controller

- StudentController

```java
@RestController
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping("/welcome")
    public String dataFetchFromdb(){
        return service.getStudentData();
    }
    //for this Method we need the object of Student Service in that one Method get Student data
    //so we will use AutoWire
```

→ service

- StudentService

```java
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public String getStudentData(){
        return studentRepository.getStudentData();
    }
```

→ repository

- StudentRepository

```java
@Repository
public class StudentRepository {
    public String getStudentData(){
        return "Hello Atharva";
    }
}
```

- so This is a Basic Example in which using Controller we provide API for the Browser
- Using Service we fetch the data from DB
- Using repository we Get the Data from the DB
- Here we use AutoWired to not make our system LooselyCoupled