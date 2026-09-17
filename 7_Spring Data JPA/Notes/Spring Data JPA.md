# Spring Data JPA

- Previously Instead of Spring Data JPA we use JDBC
    - In JDBC, we use Manual code, means for everything we write code like Create Connection, get Connection and etc
    - In JDBC, we use Boiler plate (repeated) code

- So After JDBC, there Comes Hibernate in the market it is a Framework which reduces manual effort and uses JDBC Internally to Store Data in DataBase
    - But there are also very flows of Hibernate, using hibernate we still want to write big queries to retrieve data from DB

- So after some time Spring Comes in a role to reduce the Big query to retrieve data form DB, so for this Spring Introduced new Framework know as Spring Data JPA
    - So Spring Data JPA Uses → Hibernate Internally and Hibernate uses → JDBC Internally
    - So if you know JPA, then there is no need of Learning Hibernate
    - Hibernate and Spring JPA is → ORM (Object Relation Mapping Framework )

![image.png](../../Images/6.%20Spring%20Data%20JPA/image.png)

**ORM (Object Relation Mapping )-**

![image.png](../../Images/6.%20Spring%20Data%20JPA/image%201.png)

- Mapping → For Example if we have this type of table in DB then we should also have the same type of Class Structure in our java Code → this is called Mapping
- Then Only our java code and the DB will able to Connect using a Connector
- Object → So when we Create a Class we should able to access all the variable using the Object of that class
- Relation → It define which type of Mapping is this
    - One to One
    - One to Many
    - Many to One
    - Many to Many
    

### Mapping

1. @Entity → If we use the class name as Student in java and use the same name for the table in Db then we use Entity for that class
    
    ```java
    @Entity
    @Table(name="std")
    public class Student 
    {
    @Id
    @GeneratedValue;
    int id;
    
    @Column
    String name;
    
    @Column(name="stdPh")
    String phone;
    }
    ```
    
    - @Entity → If we Didn’t Create a table in DB just only Create a Database then JPA will Create a table Automatically
    - @Table(name=”…”) → If we Define then JPA will create the table inside the JPA exactly with the name you Define in JPA
    - @Id → If you define this then it will reflect the Primary Key of the DB
    - @GeneratedValue → It is for AutoIncrement of Id
        - @GeneratedValue(strategy =”….”)
            - **GeneratedType.AUTO** →
                - so this will choose one identity and based on that it increment that
            - **GeneratedType.IDENTITY** →
                - It is similar to auto_increment like in DB
            - **GeneratedType.SEQUENCE** →
                - it is Used in Old Project (in db We Store the Sequence in which we have to increment the id according to the Sequence which is store in DB)
                - In case of Postgres / Oracle it uses Sequence Automatically
                
                ```java
                //Code to Create a Sequence
                CREATE SEQUENCE Student_Seq
                START WITH 1
                INCREMENT BY 1
                MAXVALUE 999999;
                
                //Use the Sequence to insert a new Record
                INSERT INTO my_table(id, name)
                VALUES(my_sequence.NEXTVAL, 'Athrv')
                
                //Get the Current value of Sequence
                SELECT my_sequence.CURRVAL FROM DUAL;
                ```
                
            - **GeneratedType.Table** →
            - GeneratedType.UUID →
            
            ![image.png](../../Images/6.%20Spring%20Data%20JPA/image%202.png)
            
    - @Column → In this @Column it defines the Column of the Table
    - @Column(name="stdPh") →  This will Generate the Column of Specified name in the DB

1. Foreign key 
    
    ```java
    @Entity
    public class Laptop
    {
    	@ID
    	int id;
    	
    	String brand;
    	
    	@OneToOne
    	@JoinColumn(name="Student_ID")
    	Student student;
    	
    }
    ```
    
    - This is how we define the Foreign key in the JPA
    - Basically we Create the Object of that class
    - Give mapping (OneToOne, …)
    - and JoinColumn(name=”Student_Id”)
        - We use Join Column of that which have Specified Same Attributes in the both class

## Basic Example

- We make a new Java Project and for Dependency we add → web, JPA, my-sql
- create new packages → entity, repo, controller, service
- We create a DataBase inside a DB
- inside entity we create a class called as Student

```java
package com.avm.JPA.controller;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // to Create a table in DB of name Student
public class Student {
    @Id // for making it as Primary id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;

    public Student(){

    }

    public Student(String name, String email) {
        //In this Constructor we didn't define a Id Because Id is autoIncremented by DB so no need to handle it
        this.name = name;
        this.email = email;
    }

    //we also need Getter and Setter for Id to Retreival and Showing details of ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

- Inside a repo we Create a Interface StudentRepo
- There is 2 methods
    - By extending JpaRepository <kis ke leye , it’s Primary key>
    - By extending CurdRepository <Student, Long>
        - But if we use that so in that we need to mention @Repository annotation above the interface
    - Crud is a basic and Jpa is Advance
        - Jpa is on top of Crud, which include pagination, Searching and Sorting
        
        ```java
        package com.avm.JPA.entity;
        
        import com.avm.JPA.controller.Student;
        import org.springframework.data.jpa.repository.JpaRepository;
        
        public interface StudentRepo extends JpaRepository<Student, Long>
            //Inside this we give 2 inputs -> Kis ke leye bna rhe hai, aur yuski primary key kya hai
        {
        }
        
        ```
        
- Inside a Controller we make StudentController
    
    ```java
    package com.avm.JPA.controller;
    
    import com.avm.JPA.entity.Student;
    import com.avm.JPA.repo.StudentRepo;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @RequestMapping("/students")
    public class StudentController {
        private final StudentRepo studentRepo;
    
        //Case 1 -> if we didn't mention Final then we need to Mention AutoWire Above the objec Creation
    
        /* case 2
        * If we want to mention final then we must initialize*/
        //so here we use Constructor for Dependency Injection
        public StudentController(StudentRepo studentRepo){
            this.studentRepo=studentRepo;
        }
    
        //we just need to do this
        @PostMapping
        public Student createStudent(@RequestBody Student student){
            return studentRepo.save(student);
        }
          //for post we need to save this inside a DB
    
        @GetMapping
        public List<Student> getAllStudent(){
            return studentRepo.findAll();
        }
            //to get that data we need to get all that data from the DB
    }
    ```
    

For Put mapping and Patch Mapping

**Put Mapping**

- Spring Framework simply bind the incoming json data to an object  and override whole row and resource in db

```java
@PutMapping
    public Student updateStudent(@RequestParam Long id, @RequestBody Student student){
        Student s=studentRepo.findAllById(id)
                .orElseThrow(()->new RuntimeException("Student Not Found")); //Defining Exception
        s.setName(student.getName());
        s.setEmail(student.getEmail());

        return studentRepo.save(s);
    }
    
    
 //Another Method
 @PutMapping("/{id}")
public Student updateStudent(@PathVariable long id,
                             @RequestBody Student updatedStudent){

    Optional<Student> optionalStudent = studentRepo.findById(id);

    if(optionalStudent.isPresent()){
        Student existingStudent = optionalStudent.get();

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setAge(updatedStudent.getAge());

        return studentRepo.save(existingStudent);
    }

    return null; // ideally throw exception (later topic)
}
```

**Patch Mapping-**

- It is also similar to that when to didn’t pass attribute at that time it also override
    
    ```java
    
    //Another
    
    @PatchMapping("/{id}")
    public Student patchStudent(@PathVariable long id,
                                @RequestBody Student updatedStudent){
    
        Optional<Student> optionalStudent = studentRepo.findById(id);
    
        if(optionalStudent.isPresent()){
            Student existingStudent = optionalStudent.get();
    
            if(updatedStudent.getName() != null){
                existingStudent.setName(updatedStudent.getName());
            }
    
            if(updatedStudent.getEmail() != null){
                existingStudent.setEmail(updatedStudent.getEmail());
            }
    
            if(updatedStudent.getAge() != 0){
                existingStudent.setAge(updatedStudent.getAge());
            }
    
            return studentRepo.save(existingStudent);
        }
    
        return null;
    }
    ```
    
- In [Application.properties](http://Application.properties) we need to Define the Configuration for the table and database
- This is very important to give configuration for our database
    
    ```java
    spring.datasource.url=jdbc:mysql://localhost:3306/avm
    spring.datasource.username=root
    spring.datasource.password=Atharva@860087
    
    spring.jpa.hibernate.ddl-auto=update
    ```
    
    ## Mappings
    
    1. One to One
    2. Many to One / One to Many
    3. Many to Many

### 1.One to One

Student Class - This is Own Owning class

```java
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(mappedBy ="student", cascade = CascadeType.ALL)
    private Laptop laptop;
```

- In Student class id is Primary key and laptop is Foreign key
- so we Mapped by Student

Laptop Class

```java
@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    @OneToOne
    @JoinColumn(name="student_id", unique = true)
    private Student student;
}

```

- Now where our foreign key is define there we need to mention One to One and need to Join Column

- Now we need to Create Student Repo It is an Interface

```java
public interface StudentRepo extends JpaRepository<Student, Long> {
}
// this is how we will extend that interface

public interface LaptopRepo extends JpaRepository<Laptop, Long> {
}

```

- Now we need to add Controller

```java
@RestController
@RequestMapping("/students")

public class StudentController {
}
```

- Now our data will reflects in Table but for before that we need to add that connection in Application.properties
- So this are the thigs we need to mention in [Application.properties](http://Application.properties) to configure our db

```java
spring.datasource.url=jdbc:mysql://localhost:3306/jpa_demo?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=Atharva@860087

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 2. One to Many / Many to One

Entity

Teacher Class - Consider this as our Owning Class

```java
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "teacher")
    private List<Student> students=new ArrayList<>()

}
```

Student Class

```java
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)// for making datat load faster
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
```

repo

```java
public interface TeacherRepo extends JpaRepository<Teacher, Long> {
}
```

### 3. Many to Many

- In many to many it is not ideal for Production level Application
- because when we map to different classes as many to many at that time between 2 classes a new table is Created fir mapping it is created automatically and this table store all the mapping between two classes
- So this is the reason why we mention @JsonIgnoreProperties to ignore that table

Entity 

Course Class - This is Own Owning Class

```java
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToMany(mappedBy = "course")
    //we are consider our course class as owning property so the mapping will be done by this class
    @JsonIgnoreProperties("course")
    private Set<Student> students=new HashSet<>();
}
```

Student Class

```java
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //ex of many to many
    @ManyToMany
     @JoinTable(
            name="student_course",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )
    @JsonIgnoreProperties("students")
    public Set<Course> courses=new HashSet<>();

```