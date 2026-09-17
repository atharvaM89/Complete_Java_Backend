# Spring Core Detail

# PART 1: Introduction to Spring Framework

---

## 1. What is Spring Framework and Why It Was Introduced

### 1.1 What is Spring Framework

The **Spring Framework** is a **lightweight, modular Java framework** designed to simplify the development of **enterprise-level Java applications**.

At its core, Spring provides:

- A **container** that manages objects (called *beans*)
- A mechanism to **wire objects together automatically**
- Infrastructure support for building **loosely coupled, testable, maintainable applications**

Spring does **not replace Java**.

Instead, it **extends and organizes Java** by managing:

- Object creation
- Object dependencies
- Object lifecycle

---

### 1.2 Why Spring Was Introduced (Historical Context)

Before Spring, enterprise Java development mainly relied on:

- Servlets
- JSP
- EJB (Enterprise JavaBeans)

These approaches had serious problems:

- Heavy configuration
- Tight coupling
- Difficult testing
- Complex lifecycle management

Spring was introduced to **solve these architectural and design problems**, not just to reduce code.

---

## 2. Problems with Traditional Java Development (Tight Coupling)

To understand Spring, you **must first understand the problem it solves**.

---

### 2.1 What is Tight Coupling

**Tight coupling** means:

- One class **directly creates and controls** another class
- Any change in one class **forces changes** in dependent classes

### Traditional Java Object Creation

```java
classEngine {
publicvoidstart() {
        System.out.println("Engine started");
    }
}

classCar {
private Engine engine;

publicCar() {
// Tight coupling: Car is responsible for creating Engine
        engine =newEngine();
    }

publicvoiddrive() {
        engine.start();
        System.out.println("Car is moving");
    }
}

```

---

### 2.2 Problems Caused by Tight Coupling

### ❌ Hard to Change Implementation

If tomorrow you want:

- `DieselEngine`
- `ElectricEngine`

You must **modify the Car class**.

### ❌ Hard to Test

You cannot:

- Mock dependencies
- Replace real objects with test doubles

### ❌ Poor Maintainability

- Business logic mixed with object creation logic
- Violates **Single Responsibility Principle**

### ❌ No Central Control

- Every class manages its own dependencies
- No unified lifecycle management

---

### 2.3 Why This Becomes a Big Problem in Real Applications

In real enterprise apps:

- Hundreds of classes
- Deep dependency chains
- Multiple implementations

Manual object creation becomes:

- Error-prone
- Unmanageable
- Impossible to scale cleanly

---

## 3. How Spring Solves These Problems

Spring solves tight coupling by introducing **Inversion of Control (IoC)**.

---

## 4. Core Idea Behind Spring: Inversion of Control (High-Level View)

### 4.1 What Does “Inversion of Control” Mean (Conceptual)

In traditional Java:

> Your code controls object creation
> 

In Spring:

> The framework controls object creation
> 

This is the *inversion*.

You **do not create objects**.

You **describe objects**, and Spring creates them.

---

### 4.2 Object Creation Responsibility Shift

| Traditional Java | Spring Framework |
| --- | --- |
| Developer creates objects using `new` | Spring container creates objects |
| Developer manages dependencies | Spring injects dependencies |
| Manual lifecycle handling | Automatic lifecycle management |

---

## 5. Spring’s Fundamental Strategy

Spring follows three fundamental rules:

1. **Do not create objects directly**
2. **Do not manage dependencies manually**
3. **Declare what you need, not how to get it**

---

## 6. How Spring Achieves This (High-Level Overview)

Spring introduces:

- A **container**
- A **configuration mechanism**
- A **dependency injection engine**

### High-level flow:

1. You define components (classes)
2. You define relationships (dependencies)
3. Spring:
    - Creates objects
    - Injects dependencies
    - Manages lifecycle

---

## 7. Loose Coupling Through Interfaces (Foundation Principle)

Spring strongly encourages **programming to interfaces**, not implementations.

### Example Without Spring (But Spring-Ready Design)

```java
interfaceEngine {
voidstart();
}

classPetrolEngineimplementsEngine {
publicvoidstart() {
        System.out.println("Petrol engine started");
    }
}

classCar {
private Engine engine;

// Dependency injected from outside
publicCar(Engine engine) {
this.engine = engine;
    }

publicvoiddrive() {
        engine.start();
        System.out.println("Car is moving");
    }
}

```

Here:

- `Car` does **not care** which engine it uses
- Dependency is supplied externally
- This design is **Spring-compatible**

Spring automates this injection.

---

## 8. Real Workflow Usage (Why This Matters in Practice)

In real Spring applications:

- Controllers depend on services
- Services depend on repositories
- Repositories depend on data sources

Spring:

- Creates all these objects
- Wires them correctly
- Ensures correct order of initialization

Developer focuses on:

- Business logic
- Domain modeling
- Application behavior

Not:

- Object wiring
- Lifecycle handling
- Dependency resolution

---

## 9. What You Should Understand After This Part

At this stage, you should clearly understand:

- Why traditional Java becomes hard to manage
- What tight coupling is
- Why Spring was introduced
- The **core philosophy** of Spring

# PART 2: Spring Architecture Overview

---

## 1. Spring Architecture – Big Picture

Spring is **not a single monolithic framework**.

It is a **layered, modular architecture** where each module solves a specific problem.

At the center of everything lies the **Core Container**, which is the foundation of Spring Core.

---

## 2. Core Container Concept

### 2.1 What is the Core Container

The **Core Container** is the **heart of Spring**.

It is responsible for:

- Creating objects (beans)
- Managing object lifecycle
- Injecting dependencies
- Maintaining configuration metadata

Without the Core Container:

- Spring cannot manage objects
- No Dependency Injection
- No IoC

---

### 2.2 Modules Inside Core Container (Only Relevant Ones)

From a **real-world usage perspective**, Spring Core mainly includes:

1. **Core Module**
2. **Beans Module**
3. **Context Module**
4. **Expression Language (SpEL)**

> We focus ONLY on these because they are actively used in real applications.
> 

---

## 3. Role of Each Core Module (Practical View)

### 3.1 Core Module

**Responsibility:**

- Fundamental IoC support
- Utilities used internally by Spring

**What it provides conceptually:**

- Dependency Injection engine
- Type conversion
- Resource abstraction

You **do not use this module directly**, but everything else depends on it.

---

### 3.2 Beans Module

**Responsibility:**

- Bean definition
- Bean creation
- Dependency wiring

This module understands:

- What a bean is
- How to create it
- How to inject dependencies

This is where:

- Constructor injection
- Setter injection
- Autowiring logic lives

---

### 3.3 Context Module (Most Important for Developers)

**Responsibility:**

- ApplicationContext
- Bean lifecycle management
- Event propagation
- Resource loading

In real applications:

- You interact with **ApplicationContext**
- Not with low-level BeanFactory

---

### 3.4 Spring Expression Language (SpEL)

**Responsibility:**

- Runtime value resolution
- Dynamic configuration

Used in:

- `@Value`
- Conditional wiring
- Configuration classes

(Details covered later in Part 11)

---

## 4. Role of Spring Core in an Application

Spring Core acts as the **object manager** of your application.

### Without Spring Core:

- Classes create other classes
- Hard-coded dependencies
- No central lifecycle

### With Spring Core:

- Spring creates all objects
- Dependencies are injected automatically
- Single source of truth for configuration

---

## 5. Spring Architecture in a Real Application

Let’s take a **typical backend application**:

```
Controller → Service → Repository → Database

```

### What Spring Core Does Here:

- Creates Controller object
- Creates Service object
- Creates Repository object
- Injects Repository into Service
- Injects Service into Controller

You **never write**:

```java
newServiceImpl();
newRepositoryImpl();

```

Spring does it for you.

---

## 6. Spring and Web Applications (Servlet-Based)

### 6.1 Important Clarification

Spring **does NOT replace Servlets**.

Spring:

- Works **on top of Servlets**
- Uses Servlet container for HTTP handling

---

### 6.2 Servlet Container vs Spring Container

| Aspect | Servlet Container | Spring Container |
| --- | --- | --- |
| Responsibility | HTTP request/response | Object & dependency management |
| Examples | Apache Tomcat | Spring IoC Container |
| Lifecycle | Request-based | Application-based |
| Scope | Web infrastructure | Application logic |

---

## 7. How Spring Works with Servlets (High-Level Flow)

### Step-by-Step Runtime Flow (Conceptual)

1. Browser sends HTTP request
2. Servlet container receives request
3. Spring’s front controller (DispatcherServlet) is invoked
4. DispatcherServlet delegates work to Spring-managed beans
5. Spring Core:
    - Supplies Controller object
    - Injects required dependencies
6. Response is returned back to browser

> Spring Core works behind the scenes, invisible but essential.
> 

---

## 8. Why Spring Core is Independent of Web Layer

Spring Core:

- Can work in:
    - Web applications
    - Desktop applications
    - CLI applications
    - Microservices

This is why:

- Spring Core is **framework-agnostic**
- Web support is an **extension**, not a dependency

---

## 9. Internal Working: How Core Container Fits In

Internally:

- Core Container reads configuration
- Builds a **bean definition registry**
- Instantiates beans
- Resolves dependencies
- Maintains singleton/prototype scope

All higher-level Spring modules:

- Use Core Container internally
- Never bypass it

---

## 10. Real Workflow Usage

In real projects:

- Developers define components
- Spring Core:
    - Scans packages
    - Identifies beans
    - Creates dependency graph
    - Injects dependencies automatically

This allows:

- Clean layered architecture
- Easy testing
- Future scalability

# PART 3: Inversion of Control (IoC)

---

## 1. What is Inversion of Control (IoC)

### 1.1 Traditional Control Flow (Before IoC)

In **traditional Java applications**, the flow is:

- Your code decides **when** to create objects
- Your code decides **which** implementation to use
- Your code decides **how** dependencies are wired

In short:

> Application code controls everything
> 

This leads to:

- Tight coupling
- Rigid architecture
- Poor testability

---

### 1.2 Definition of Inversion of Control

**Inversion of Control (IoC)** is a design principle where:

> Control of object creation and dependency management is transferred from application code to a container/framework
> 

So instead of:

```java
A creates B

```

We get:

```
Container creates A
Container creates B
Container injects B into A

```

The **control is inverted**.

---

## 2. Why IoC is Required (Problem-Oriented Explanation)

### 2.1 Problem 1: Object Creation Logic Pollutes Business Logic

Without IoC:

```java
classOrderService {
privateOrderRepositoryrepository=newOrderRepository();
}

```

Here:

- Business logic is mixed with creation logic
- Violates Single Responsibility Principle

---

### 2.2 Problem 2: Implementation Lock-In

If tomorrow:

- `OrderRepository` changes
- Or you want `JpaOrderRepository`

You must:

- Modify `OrderService`
- Recompile code
- Retest everything

---

### 2.3 Problem 3: Testing Becomes Hard

You cannot easily:

- Inject mocks
- Replace real dependencies
- Write unit tests

---

### 2.4 How IoC Solves All These

With IoC:

- Classes **declare dependencies**
- Container **provides dependencies**
- Implementations can change **without touching dependent code**

---

## 3. Core Principle Behind IoC

### 3.1 “Don’t Call Us, We’ll Call You”

This is the philosophical foundation of IoC.

Instead of:

```java
newDependency();

```

You say:

```java
I need a Dependency

```

Spring responds:

```java
Here it is

```

---

## 4. How Spring Implements IoC

Spring implements IoC using a mechanism called:

> Dependency Injection (DI)
> 

DI is **not separate** from IoC.

It is the **practical implementation** of IoC.

---

## 5. Internal Working of IoC in Spring (Conceptual View)

### 5.1 High-Level Internal Steps

When Spring starts:

1. Reads configuration metadata
2. Creates **Bean Definitions**
3. Registers them inside IoC container
4. Creates objects when required
5. Injects dependencies
6. Manages lifecycle

---

### 5.2 Bean Definition (Important Internal Concept)

A **Bean Definition** contains:

- Class name
- Scope (singleton/prototype)
- Constructor arguments
- Properties to inject
- Init and destroy methods

This metadata drives the entire IoC process.

---

## 6. IoC Container: The Execution Engine

### 6.1 What is IoC Container

The **IoC Container** is:

- A runtime environment
- Responsible for:
    - Creating objects
    - Injecting dependencies
    - Managing lifecycle

Spring provides two main container types:

- BeanFactory (low-level)
- ApplicationContext (enterprise-level)

(Details later in Part 5)

---

## 7. IoC Without Spring (Manual IoC Example)

To understand IoC better, let’s implement **manual IoC**.

```java
interfaceMessageService {
voidsendMessage(String msg);
}

classEmailServiceimplementsMessageService {
publicvoidsendMessage(String msg) {
        System.out.println("Email sent: " + msg);
    }
}

classNotificationService {
private MessageService messageService;

// Dependency injected from outside
publicNotificationService(MessageService messageService) {
this.messageService = messageService;
    }

publicvoidnotifyUser() {
        messageService.sendMessage("Hello User");
    }
}

publicclassApp {
publicstaticvoidmain(String[] args) {
MessageServiceservice=newEmailService();
NotificationServicenotification=
newNotificationService(service);

        notification.notifyUser();
    }
}

```

### What This Demonstrates:

- `NotificationService` does NOT create `EmailService`
- Dependency is injected externally
- Control is inverted

Spring automates this process.

---

## 8. IoC With Spring (Conceptual Flow)

With Spring:

```
You define components
You declare dependencies
Spring container:
  → creates objects
  → injects dependencies
  → manages lifecycle

```

You only focus on:

- What your class needs
- What your class does

---

## 9. Real-Life Analogy (Conceptual)

### Restaurant Analogy

Without IoC:

- You cook food
- You buy ingredients
- You wash utensils

With IoC:

- You order food
- Restaurant handles everything

You only:

- Declare what you want
- Consume the result

---

## 10. Real Workflow Usage in Spring Applications

In a real Spring backend:

- Controllers depend on services
- Services depend on repositories
- Repositories depend on data sources

IoC ensures:

- No layer knows how the lower layer is created
- Changes are localized
- Architecture remains clean

---

## 11. Why IoC Is Non-Negotiable in Spring

Without IoC:

- Spring cannot exist
- DI is impossible
- Loose coupling breaks

IoC is:

- The foundation
- The backbone
- The reason Spring scales

# PART 4: Dependency Injection (DI)

---

## 1. What is Dependency Injection (DI)

### 1.1 Definition

**Dependency Injection (DI)** is a design technique where:

- An object’s **dependencies are provided from outside**
- The object **does not create its own dependencies**

In Spring:

- DI is the **mechanism**
- IoC is the **principle**
- The **Spring Framework IoC container** performs DI automatically

---

### 1.2 What is a Dependency

A **dependency** is any object that another object requires to function.

Example:

- `OrderService` depends on `OrderRepository`
- `UserController` depends on `UserService`

---

## 2. Why Dependency Injection Is Important

### 2.1 Separation of Concerns

Without DI:

- Business logic + object creation logic mixed together

With DI:

- Business logic stays clean
- Object creation is externalized

---

### 2.2 Loose Coupling

DI ensures:

- Classes depend on **abstractions**
- Implementations can change without modifying dependent classes

---

### 2.3 Testability

DI allows:

- Mocking dependencies
- Unit testing without Spring container
- Clean isolation of components

---

## 3. How Spring Performs Dependency Injection Internally

### 3.1 Internal Workflow (Conceptual)

1. Spring scans classes
2. Creates bean definitions
3. Identifies dependencies
4. Resolves matching beans
5. Injects dependencies using:
    - Constructor
    - Setter
    - Field (reflection)

---

### 3.2 Dependency Resolution Happens Before Business Logic

Important rule:

> No Spring bean executes business logic until all dependencies are injected
> 

---

## 4. Types of Dependency Injection in Spring

Spring supports **three DI types**.

All three exist, but **only some are recommended**.

---

## 5. Constructor Injection (Recommended)

### 5.1 What is Constructor Injection

Dependencies are injected:

- Through the **constructor**
- At the time of object creation

---

### 5.2 Why Constructor Injection Is Preferred

Constructor injection ensures:

- Dependency is **mandatory**
- Object is always in **valid state**
- Dependencies are **immutable**
- Easier unit testing
- No reflection hacks

---

### 5.3 Syntax Example (Full Working)

```java
// Dependency interface
publicinterfacePaymentService {
voidprocessPayment();
}

// Implementation
@Component
publicclassCardPaymentServiceimplementsPaymentService {
@Override
publicvoidprocessPayment() {
        System.out.println("Payment processed via card");
    }
}

// Dependent class
@Component
publicclassOrderService {

privatefinal PaymentService paymentService;

// Constructor Injection
@Autowired
publicOrderService(PaymentService paymentService) {
this.paymentService = paymentService;
    }

publicvoidplaceOrder() {
        paymentService.processPayment();
        System.out.println("Order placed successfully");
    }
}

```

### Internal Working:

- Spring sees constructor
- Finds matching `PaymentService` bean
- Injects it while creating `OrderService`

---

### 5.4 Constructor Injection Without `@Autowired` (Modern Spring)

```java
@Component
publicclassOrderService {

privatefinal PaymentService paymentService;

// Single constructor → @Autowired optional
publicOrderService(PaymentService paymentService) {
this.paymentService = paymentService;
    }
}

```

---

## 6. Setter Injection (Optional Dependencies)

### 6.1 What is Setter Injection

Dependencies are injected:

- After object creation
- Using setter methods

---

### 6.2 When Setter Injection Is Used

- Dependency is **optional**
- Dependency can change at runtime
- Legacy integration scenarios

---

### 6.3 Syntax Example

```java
@Component
publicclassNotificationService {

private MessageService messageService;

@Autowired
publicvoidsetMessageService(MessageService messageService) {
this.messageService = messageService;
    }

publicvoidnotifyUser() {
        messageService.sendMessage("Notification sent");
    }
}

```

---

### 6.4 Drawbacks of Setter Injection

- Object can exist without dependency
- Possible `NullPointerException`
- Breaks immutability

---

## 7. Field Injection (Concept + Drawbacks)

### 7.1 What is Field Injection

Dependencies are injected:

- Directly into fields
- Using reflection

```java
@Component
publicclassReportService {

@Autowired
private ReportRepository reportRepository;
}

```

---

### 7.2 How Spring Injects into Private Fields

Internally Spring uses:

- **Java Reflection API**
- `setAccessible(true)`
- Bypasses access modifiers

---

### 7.3 Why Field Injection Is Discouraged

❌ Hidden dependencies

❌ Breaks encapsulation

❌ Hard to test

❌ Cannot enforce immutability

❌ Reflection overhead

**Industry standard:**

> Avoid field injection in production code
> 

---

## 8. Dependency Injection Resolution Rules

Spring resolves dependencies by:

1. **Type**
2. **Qualifier (if ambiguity exists)**
3. **Primary bean (if defined)**

(Detailed in Part 10)

---

## 9. Real Workflow Usage

### Typical Spring Layered Architecture

```
Controller
   ↓ (DI)
Service
   ↓ (DI)
Repository

```

Spring ensures:

- Each layer receives correct dependency
- No manual wiring
- Clean separation of layers

---

## 10. Common DI Mistakes (Conceptual)

- Using field injection everywhere
- Injecting concrete classes instead of interfaces
- Circular dependencies due to poor design
- Overusing setters for mandatory dependencies

# PART 5: Spring Container

---

## 1. What is the Spring Container

### 1.1 Definition

The **Spring Container** is the **runtime engine** of Spring that is responsible for:

- Creating objects (beans)
- Injecting dependencies
- Managing bean lifecycle
- Providing configuration metadata
- Controlling application startup behavior

In simple terms:

> Spring Container is the factory + manager + lifecycle controller of all application objects
> 

---

### 1.2 Why the Container Is Central to Spring

Without the container:

- No IoC
- No Dependency Injection
- No lifecycle management
- No loose coupling

Every Spring feature works **through the container**, not around it.

---

## 2. Responsibilities of Spring Container (Workflow View)

When an application starts, the container performs these steps:

1. Reads configuration metadata
2. Builds bean definitions
3. Creates the dependency graph
4. Instantiates beans
5. Injects dependencies
6. Executes lifecycle callbacks
7. Makes beans available for use

This entire workflow is **automatic** and **declarative**.

---

## 3. Types of Spring Containers

Spring provides **two core container interfaces**:

1. **BeanFactory**
2. **ApplicationContext**

Both implement IoC, but **only one is used in real applications**.

---

## 4. BeanFactory (Low-Level Container)

### 4.1 What is BeanFactory

`BeanFactory` is:

- The **basic IoC container**
- Provides only:
    - Bean creation
    - Dependency injection

It is:

- Lightweight
- Lazy-loaded by default

---

### 4.2 Internal Working of BeanFactory

- Beans are created **only when requested**
- No automatic lifecycle callbacks
- No event handling
- No internationalization support

---

### 4.3 Example Usage (Conceptual Only)

```java
BeanFactoryfactory=newXmlBeanFactory(resource);
MyServiceservice= factory.getBean(MyService.class);

```

> ⚠️ This approach is not used in modern Spring applications
> 

---

### 4.4 Limitations of BeanFactory

❌ No eager initialization

❌ No event publishing

❌ No application-level context

❌ No annotation-driven features by default

BeanFactory is suitable only for:

- Very low-memory environments
- Framework-level internal use

---

## 5. ApplicationContext (Enterprise Container)

### 5.1 What is ApplicationContext

`ApplicationContext` is:

- An **advanced IoC container**
- A **superset of BeanFactory**
- The **default container used in all real projects**

It provides:

- Bean creation & DI
- Lifecycle management
- Event propagation
- Resource loading
- Annotation processing

---

### 5.2 Why ApplicationContext Is Used in Real Projects

ApplicationContext supports everything required for:

- Enterprise applications
- Web applications
- Microservices
- Spring Boot applications

---

## 6. Internal Working of ApplicationContext

### 6.1 Startup-Time Behavior

When ApplicationContext starts:

1. Scans classpath for components
2. Registers bean definitions
3. Creates singleton beans eagerly
4. Resolves dependencies
5. Executes init callbacks

This ensures:

- All required beans are ready before handling requests
- Fail-fast behavior (errors appear at startup)

---

### 6.2 Eager vs Lazy Loading

| Aspect | BeanFactory | ApplicationContext |
| --- | --- | --- |
| Bean creation | Lazy | Eager (singleton) |
| Error detection | Runtime | Startup |
| Production readiness | ❌ | ✅ |

---

## 7. Common ApplicationContext Implementations

### 7.1 AnnotationConfigApplicationContext (Core Focus)

Used for:

- Java-based configuration
- Annotation-driven apps
- Spring Boot internals

```java
ApplicationContextcontext=
newAnnotationConfigApplicationContext(AppConfig.class);

```

---

### 7.2 WebApplicationContext (Web Apps)

Used in:

- Servlet-based applications
- Integrated with web container

(Spring Boot handles this automatically)

---

## 8. How Beans Are Accessed from the Container

### 8.1 Programmatic Access (Rare in Real Apps)

```java
MyServiceservice= context.getBean(MyService.class);

```

Used mainly for:

- Bootstrapping
- Framework-level code

---

### 8.2 Dependency Injection (Preferred)

```java
@Component
publicclassOrderController {

privatefinal OrderService orderService;

publicOrderController(OrderService orderService) {
this.orderService = orderService;
    }
}

```

> Beans are never fetched manually in business code.
> 

---

## 9. Relationship Between Container and Beans

Important rules:

- Container **owns all beans**
- Beans **do not know** about container
- Container controls:
    - Creation
    - Injection
    - Destruction

This keeps application code:

- Clean
- Framework-independent
- Testable

---

## 10. Real Workflow Usage

In real Spring applications:

- ApplicationContext is created at startup
- All required beans are initialized
- Dependencies are resolved
- Controllers/services/repositories become usable
- Application starts serving requests

You focus on:

- Defining components
- Declaring dependencies

Spring Container handles everything else.

# PART 6: Spring Beans

---

## 1. What is a Spring Bean

### 1.1 Definition

A **Spring Bean** is:

- An **object created, managed, and destroyed by the Spring Container**
- A **core building block** of any Spring application

In simple terms:

> Any object whose lifecycle is controlled by Spring is called a Spring Bean
> 

---

### 1.2 How a Normal Java Object Becomes a Spring Bean

A normal Java object:

```java
classUserService { }

```

Becomes a Spring Bean when:

- It is registered in the Spring Container
- Either via annotations, Java config, or XML

Example:

```java
@Component
publicclassUserService {
}

```

Now:

- Spring creates this object
- Spring injects its dependencies
- Spring manages its lifecycle

---

## 2. Why Spring Beans Exist (Design Reason)

Spring Beans exist to solve these problems:

- Centralized object creation
- Dependency management
- Lifecycle control
- Consistency across the application

Without beans:

- Spring Core cannot function
- DI and IoC collapse

---

## 3. How Spring Creates and Manages Beans

### 3.1 High-Level Bean Creation Workflow

When Spring Container starts:

1. Scans for bean definitions
2. Registers bean metadata
3. Instantiates bean objects
4. Injects dependencies
5. Executes lifecycle callbacks
6. Stores bean in container (if singleton)

---

### 3.2 Bean Definition (Internal Metadata)

For every bean, Spring internally stores:

- Bean class
- Scope
- Constructor arguments
- Properties
- Init method
- Destroy method

This metadata drives **everything** Spring does with the bean.

---

## 4. Bean Lifecycle (Core Concept)

The **Bean Lifecycle** defines the **exact sequence of steps** a bean goes through from creation to destruction.

---

## 5. Detailed Spring Bean Lifecycle (Step-by-Step)

### 5.1 Step 1: Bean Instantiation

Spring creates the bean instance:

- Using constructor
- Using reflection

```java
@Component
publicclassProductService {

publicProductService() {
        System.out.println("1. Bean instantiated");
    }
}

```

---

### 5.2 Step 2: Dependency Injection

Spring injects required dependencies.

```java
@Component
publicclassProductService {

privatefinal ProductRepository repository;

publicProductService(ProductRepository repository) {
this.repository = repository;
        System.out.println("2. Dependencies injected");
    }
}

```

---

### 5.3 Step 3: Aware Interfaces (Conceptual)

If bean implements special interfaces:

- `BeanNameAware`
- `ApplicationContextAware`

Spring injects container-related information.

> Used rarely in business logic, mostly in infrastructure beans.
> 

---

### 5.4 Step 4: Bean Post Processors (Internal Hook)

Spring applies:

- `BeanPostProcessor`
- Used for:
    - Proxy creation
    - AOP
    - Annotations like `@Autowired`

This is how:

- DI
- AOP
- Transaction management work internally

---

### 5.5 Step 5: Initialization Callbacks

Spring allows custom initialization logic using:

### Option 1: `@PostConstruct`

```java
@Component
publicclassCacheService {

@PostConstruct
publicvoidinit() {
        System.out.println("3. Bean initialized");
    }
}

```

---

### Option 2: `init-method`

```java
@Component
publicclassCacheService {

publicvoidloadCache() {
        System.out.println("3. Cache loaded");
    }
}

```

```java
@Configuration
publicclassAppConfig {

@Bean(initMethod = "loadCache")
public CacheServicecacheService() {
returnnewCacheService();
    }
}

```

---

### 5.6 Step 6: Bean Ready for Use

At this point:

- Bean is fully initialized
- Dependencies injected
- Ready for business logic execution

---

### 5.7 Step 7: Bean Destruction

When application shuts down:

- Spring destroys beans
- Cleanup logic is executed

---

## 6. Destroy Callbacks

### 6.1 Using `@PreDestroy`

```java
@Component
publicclassCacheService {

@PreDestroy
publicvoidcleanup() {
        System.out.println("4. Cache cleared");
    }
}

```

---

### 6.2 Using `destroy-method`

```java
@Configuration
publicclassAppConfig {

@Bean(destroyMethod = "shutdown")
public ConnectionPoolconnectionPool() {
returnnewConnectionPool();
    }
}

```

---

## 7. Singleton vs Prototype Lifecycle (Important Difference)

### 7.1 Singleton Bean (Default)

- Created once at startup
- Destroyed at shutdown
- Full lifecycle callbacks applied

---

### 7.2 Prototype Bean

- Created every time requested
- Spring does **not manage destruction**
- `@PreDestroy` is **not called**

```java
@Component
@Scope("prototype")
publicclassTempBean {
}

```

---

## 8. Real Workflow Usage

### Typical Enterprise Scenario

- Database connection pool:
    - Initialized at startup
    - Closed at shutdown
- Cache service:
    - Preloaded at startup
    - Cleared at shutdown

Spring Bean lifecycle ensures:

- Resources are handled safely
- No manual cleanup code scattered

---

## 9. Why Understanding Bean Lifecycle Matters

Understanding lifecycle helps you:

- Place initialization logic correctly
- Avoid premature resource usage
- Prevent memory leaks
- Design reliable startup behavior

# PART 7: Bean Configuration Approaches

---

## 1. Why Bean Configuration Exists

Spring must know:

- **Which classes are beans**
- **How to create them**
- **How to wire dependencies**
- **How to manage lifecycle**

This information is provided through **configuration metadata**.

Over time, Spring evolved **three configuration approaches**.

All three exist today, but **only some are dominant in real projects**.

---

## 2. Overview of Bean Configuration Approaches

Spring supports:

1. **XML-based configuration** (legacy, conceptual understanding)
2. **Annotation-based configuration** (primary approach)
3. **Java-based configuration** (modern, strongly recommended)

> In real-world Spring applications, Annotation + Java config is the standard.
> 

---

## 3. XML-Based Configuration (Conceptual Understanding)

### 3.1 What is XML Configuration

Beans and dependencies are defined inside an XML file.

```xml
<beanid="orderService"class="com.app.OrderService">
<propertyname="paymentService"ref="paymentService"/>
</bean>

<beanid="paymentService"class="com.app.CardPaymentService"/>

```

---

### 3.2 How Spring Uses XML Internally

- Spring reads XML at startup
- Parses `<bean>` definitions
- Registers them as bean metadata
- Creates objects accordingly

---

### 3.3 Why XML Was Used Initially

At the time of Spring’s creation:

- Annotations were limited
- Java reflection was expensive
- Externalized configuration was preferred

---

### 3.4 Why XML Is Rarely Used Today

❌ Verbose

❌ Hard to refactor

❌ No compile-time safety

❌ Poor readability in large apps

> XML is not used in modern Spring Boot or enterprise projects.
> 

---

## 4. Annotation-Based Configuration (Main Focus)

### 4.1 What is Annotation-Based Configuration

Beans are declared:

- Directly on Java classes
- Using annotations

Example:

```java
@Component
publicclassOrderService {
}

```

Spring:

- Scans packages
- Finds annotated classes
- Registers them as beans automatically

---

### 4.2 Component Scanning (Key Mechanism)

Spring uses **classpath scanning** to find beans.

```java
@ComponentScan("com.app")

```

This tells Spring:

- Scan all classes under `com.app`
- Register eligible beans

---

### 4.3 How Spring Identifies Beans

Spring looks for:

- `@Component`
- `@Service`
- `@Repository`
- `@Controller`

All are **specializations of `@Component`**.

---

### 4.4 Example: Annotation-Based Bean Definition

```java
@Component
publicclassInventoryService {

publicvoidcheckStock() {
        System.out.println("Checking inventory");
    }
}

```

Spring behavior:

- Detects class
- Creates bean
- Manages lifecycle
- Makes it injectable

---

## 5. Dependency Injection with Annotations

### Constructor Injection (Recommended)

```java
@Component
publicclassOrderService {

privatefinal InventoryService inventoryService;

publicOrderService(InventoryService inventoryService) {
this.inventoryService = inventoryService;
    }
}

```

Spring:

- Resolves `InventoryService`
- Injects it during object creation

---

## 6. Java-Based Configuration (Modern & Powerful)

### 6.1 What is Java-Based Configuration

Beans are defined using:

- `@Configuration` classes
- `@Bean` methods

This approach:

- Replaces XML
- Provides full Java control
- Is heavily used internally by Spring Boot

---

### 6.2 Basic Java Config Example

```java
@Configuration
publicclassAppConfig {

@Bean
public PaymentServicepaymentService() {
returnnewCardPaymentService();
    }

@Bean
public OrderServiceorderService(PaymentService paymentService) {
returnnewOrderService(paymentService);
    }
}

```

---

### 6.3 How Spring Processes `@Configuration`

Internally:

- Spring creates a proxy of the config class
- Ensures each `@Bean` method:
    - Returns a singleton (by default)
    - Is not called multiple times accidentally

---

### 6.4 Why `@Configuration` Is Special

```java
@Configuration
publicclassAppConfig {
}

```

Means:

- Full container-managed configuration
- Bean methods are intercepted
- Proper lifecycle handling

---

## 7. Mixing Annotation + Java Config (Real-World Pattern)

### Typical Real Project Setup

- Business classes → `@Component`, `@Service`
- Infrastructure beans → `@Bean` methods

Example:

```java
@Configuration
publicclassInfraConfig {

@Bean
public DataSourcedataSource() {
returnnewHikariDataSource();
    }
}

```

This separation ensures:

- Clean architecture
- Clear responsibility boundaries

---

## 8. When to Use Which Configuration

### XML Configuration

- ❌ Avoid in new projects
- ✔️ Only for legacy systems

---

### Annotation-Based Configuration

- ✔️ Default choice for application logic
- ✔️ Clean and readable
- ✔️ Industry standard

---

### Java-Based Configuration

- ✔️ Infrastructure beans
- ✔️ Third-party library beans
- ✔️ Conditional or complex initialization

---

## 9. Internal Working Comparison

| Aspect | XML | Annotations | Java Config |
| --- | --- | --- | --- |
| Readability | ❌ | ✅ | ✅ |
| Type safety | ❌ | ✅ | ✅ |
| Refactoring support | ❌ | ✅ | ✅ |
| Real-world usage | ❌ | ✅ | ✅ |

---

## 10. Real Workflow Usage

In real Spring applications:

- Spring Boot auto-configures many beans using Java config
- Developers define business beans using annotations
- XML is almost completely absent

This combination provides:

- Maximum flexibility
- Minimal configuration
- Clean startup flow

# PART 8: Core Spring Annotations

---

## 1. Why Core Annotations Matter in Spring

Core Spring annotations are the **declarative language** you use to tell the **Spring Framework** container:

- Which classes are beans
- What role a class plays in the application
- How dependencies should be injected
- How values should be supplied at runtime

Without these annotations:

- Component scanning fails
- Dependency injection does not happen
- Spring Core becomes unusable in real projects

---

## 2. `@Component` – The Base Stereotype

### 2.1 What `@Component` Does

`@Component` marks a class as:

- A **candidate for component scanning**
- A **Spring-managed bean**

```java
@Component
publicclassEmailService {
publicvoidsend() {
        System.out.println("Email sent");
    }
}

```

Spring behavior:

- Detects the class during scanning
- Creates a bean
- Manages its lifecycle
- Makes it injectable

---

### 2.2 Internal Working

Internally Spring:

1. Scans classpath
2. Finds `@Component`
3. Creates a `BeanDefinition`
4. Registers it in the container

---

### 2.3 When to Use `@Component`

Use `@Component` when:

- The class does not clearly fit a specific layer
- It is a helper, utility, or generic component

---

## 3. Specialized Stereotypes (Layer Semantics)

Spring provides **specialized versions** of `@Component` to express architectural intent.

All of them:

- Are detected by component scanning
- Behave the same at runtime
- Differ in **semantic meaning**

---

## 4. `@Service` – Service Layer

### 4.1 Purpose

`@Service` represents:

- Business logic layer
- Use-case orchestration
- Transaction boundaries (commonly)

```java
@Service
publicclassOrderService {

publicvoidplaceOrder() {
        System.out.println("Placing order");
    }
}

```

---

### 4.2 Why Not Just `@Component`

Using `@Service`:

- Makes architecture explicit
- Improves readability
- Enables future AOP features (transactions, metrics)

---

### 4.3 Real Workflow Usage

- Controllers depend on services
- Services coordinate repositories and other services
- Business rules live here

---

## 5. `@Repository` – Persistence Layer

### 5.1 Purpose

`@Repository` marks:

- DAO / Repository classes
- Database interaction layer

```java
@Repository
publicclassOrderRepository {

publicvoidsave() {
        System.out.println("Order saved");
    }
}

```

---

### 5.2 Hidden but Critical Feature: Exception Translation

Internally Spring:

- Wraps persistence exceptions
- Converts vendor-specific exceptions
- Throws consistent runtime exceptions

This happens **only** when using `@Repository`.

---

### 5.3 Real Workflow Usage

- Services call repositories
- Repositories talk to database
- Database-specific logic stays isolated

---

## 6. `@Controller` – Web Layer (Conceptual Placement)

### 6.1 Purpose

`@Controller` marks:

- Web entry points
- HTTP request handlers

```java
@Controller
publicclassOrderController {
}

```

> Detailed request handling is covered in Spring MVC, not Spring Core.
> 

---

### 6.2 Why It Still Matters in Core

- Controller beans are still managed by Spring Core
- Dependency injection still applies
- Lifecycle is still controlled by container

---

## 7. `@Autowired` – Dependency Injection Annotation

### 7.1 What `@Autowired` Does

`@Autowired` tells Spring:

> Inject a matching bean here
> 

It can be applied to:

- Constructors
- Setters
- Fields

---

## 8. Constructor Injection with `@Autowired` (Preferred)

```java
@Service
publicclassPaymentService {

privatefinal InvoiceService invoiceService;

@Autowired
publicPaymentService(InvoiceService invoiceService) {
this.invoiceService = invoiceService;
    }
}

```

### Internal Working:

- Spring finds constructor
- Resolves dependency by type
- Injects during bean creation

---

### Modern Rule

If there is **only one constructor**, `@Autowired` is optional:

```java
@Service
publicclassPaymentService {

privatefinal InvoiceService invoiceService;

publicPaymentService(InvoiceService invoiceService) {
this.invoiceService = invoiceService;
    }
}

```

---

## 9. Field Injection with `@Autowired` (Drawbacks Reminder)

```java
@Service
publicclassReportService {

@Autowired
private ReportRepository reportRepository;
}

```

Internally:

- Spring uses **Reflection**
- Access modifiers are bypassed

Problems:

- Hidden dependencies
- Hard to test
- Breaks immutability

> Use only for legacy or quick prototypes.
> 

---

## 10. `@Qualifier` – Resolving Multiple Bean Ambiguity

### 10.1 Problem Scenario

```java
@Component
publicclassSmsServiceimplementsMessageService { }

@Component
publicclassEmailServiceimplementsMessageService { }

```

Now Spring sees:

- Two beans of type `MessageService`

Injection fails without clarification.

---

### 10.2 Using `@Qualifier`

```java
@Service
publicclassNotificationService {

privatefinal MessageService messageService;

publicNotificationService(
@Qualifier("emailService") MessageService messageService) {
this.messageService = messageService;
    }
}

```

Spring:

- Matches by qualifier name
- Injects the correct bean

---

## 11. `@Primary` – Default Bean Selection

### 11.1 Purpose

`@Primary` tells Spring:

> Use this bean when multiple candidates exist
> 

```java
@Component
@Primary
publicclassEmailServiceimplementsMessageService {
}

```

Now:

- Spring injects `EmailService` by default
- No qualifier needed unless overridden

---

## 12. `@Value` – Injecting External Values

### 12.1 Purpose

`@Value` injects:

- Properties
- Configuration values
- Expressions (SpEL basics)

---

### 12.2 Property Injection Example

```java
@Component
publicclassAppInfo {

@Value("${app.name}")
private String appName;

publicvoidprint() {
        System.out.println(appName);
    }
}

```

Spring:

- Reads property source
- Resolves placeholder
- Injects value at runtime

---

### 12.3 Internal Working

- `@Value` is processed by BeanPostProcessor
- Values resolved **before bean is used**
- Supports expressions (covered later)

---

## 13. Real Workflow Usage (Annotation-Driven App)

Typical real-world pattern:

```
@Controller  →  @Service  →  @Repository
        ↑            ↑            ↑
     @Autowired    @Autowired    @Autowired

```

Annotations:

- Declare intent
- Drive container behavior
- Remove boilerplate wiring

# PART 9: Bean Scopes

---

## 1. What is a Bean Scope

### 1.1 Definition

A **bean scope** defines:

- **How many instances** of a bean Spring creates
- **How long** a bean instance lives
- **Where** that instance is shared or isolated

In the **Spring Framework**, scope is part of a bean’s metadata and directly affects runtime behavior.

---

### 1.2 Why Scope Matters

Choosing the correct scope is critical because it impacts:

- Memory usage
- Thread safety
- Data consistency
- Application correctness

Wrong scope ⇒ subtle, hard-to-debug production issues.

---

## 2. Default Scope: Singleton

### 2.1 What is Singleton Scope

**Singleton** means:

- **One instance per Spring container**
- Same instance is reused everywhere
- Default scope for all beans

```java
@Component
publicclassUserService {
}

```

Internally:

- Spring creates **one object**
- Stores it in container cache
- Injects the same instance everywhere

---

### 2.2 Internal Working of Singleton Scope

1. Container starts
2. Bean definition registered
3. Bean instance created eagerly
4. Stored in singleton cache
5. Same instance returned for every injection

---

### 2.3 Example: Singleton Behavior

```java
@Component
publicclassCounterService {
privateintcount=0;

publicvoidincrement() {
        count++;
        System.out.println(count);
    }
}

```

```java
@Service
publicclassTestService {

privatefinal CounterService counterService;

publicTestService(CounterService counterService) {
this.counterService = counterService;
    }

publicvoidtest() {
        counterService.increment();
        counterService.increment();
    }
}

```

Output:

```
1
2

```

The state is **shared**.

---

### 2.4 Real Workflow Usage of Singleton

Used for:

- Services
- Repositories
- Controllers
- Configuration classes

Reason:

- Stateless logic
- Shared infrastructure
- Performance efficiency

---

## 3. Prototype Scope

### 3.1 What is Prototype Scope

**Prototype** means:

- **New instance every time** the bean is requested
- Spring creates the object
- Spring does **not manage destruction**

```java
@Component
@Scope("prototype")
publicclassTempObject {
}

```

---

### 3.2 Internal Working of Prototype Scope

- Spring creates a new instance on each request
- Does **not cache** the instance
- Lifecycle ends after injection

Important:

> Spring does not call @PreDestroy for prototype beans
> 

---

### 3.3 Example: Prototype Behavior

```java
@Component
@Scope("prototype")
publicclassRandomGenerator {

publicRandomGenerator() {
        System.out.println("New instance created");
    }
}

```

Injecting it twice:

```java
@Autowired
private RandomGenerator gen1;

@Autowired
private RandomGenerator gen2;

```

Output:

```
New instance created
New instance created

```

---

### 3.4 Real Workflow Usage of Prototype

Used for:

- Stateful objects
- Temporary processing objects
- Objects tied to a single operation

⚠️ Use carefully:

- Can increase memory usage
- Requires manual cleanup

---

## 4. Request Scope (Web Applications – Conceptual)

### 4.1 What is Request Scope

**Request scope** means:

- One bean instance per HTTP request
- Destroyed after request completes

```java
@Component
@Scope("request")
publicclassRequestData {
}

```

---

### 4.2 Internal Working

- Servlet container starts request
- Spring creates request-scoped bean
- Bean exists for request lifetime
- Destroyed after response is sent

---

### 4.3 Real Workflow Usage

Used for:

- Request metadata
- Per-request context
- Logging correlation IDs

---

## 5. Session Scope (Web Applications – Conceptual)

### 5.1 What is Session Scope

**Session scope** means:

- One bean per HTTP session
- Shared across multiple requests of same user

```java
@Component
@Scope("session")
publicclassUserSession {
}

```

---

### 5.2 Internal Working

- Session created by servlet container
- Spring binds bean to session
- Bean lives until session expires or invalidates

---

### 5.3 Real Workflow Usage

Used for:

- User preferences
- Login state
- Temporary conversational data

---

## 6. Scope and Dependency Injection Interaction

### 6.1 Singleton Injecting Prototype (Important Behavior)

If:

- Singleton bean injects prototype bean

```java
@Service
publicclassOrderService {

privatefinal TempObject tempObject;

publicOrderService(TempObject tempObject) {
this.tempObject = tempObject;
    }
}

```

Then:

- Prototype is created **once**
- Injected at singleton creation time
- Not recreated per use

This surprises many developers.

---

### 6.2 Why This Happens

Because:

- Injection happens during bean creation
- Singleton is created once
- Prototype is resolved once

Special techniques (ObjectFactory, Provider) are needed for dynamic creation (advanced topic).

---

## 7. Scope Selection Guidelines (Workflow-Oriented)

### Singleton:

- Default choice
- Use for stateless logic

### Prototype:

- Use only when new instance is truly required
- Handle cleanup manually

### Request:

- Use for request-bound data
- Web apps only

### Session:

- Use sparingly
- Be careful with memory

---

## 8. Real Workflow Usage Summary (Without Conclusion)

In real Spring applications:

- **95% beans are singleton**
- Prototype is niche
- Request/session used only in web-specific cases

Understanding scope is essential to:

- Avoid shared-state bugs
- Build thread-safe applications
- Design correct lifecycle behavior

# PART 10: Autowiring Internals

---

## 1. What Autowiring Really Means Internally

### 1.1 Definition

**Autowiring** is the internal mechanism by which the **Spring Framework** container:

- Detects a dependency
- Searches for a matching bean
- Resolves ambiguity
- Injects the dependency at runtime

Autowiring is **not magic**.

It follows **strict resolution rules**.

---

## 2. When Autowiring Happens in Spring Lifecycle

Autowiring occurs:

- **After bean instantiation**
- **Before initialization callbacks**
- During dependency injection phase

No business method runs until autowiring is complete.

---

## 3. Dependency Resolution Strategy (High-Level)

When Spring sees a dependency:

```java
publicclassOrderService {
private PaymentService paymentService;
}

```

Spring asks:

1. What is the **required type**?
2. How many beans of that type exist?
3. Can ambiguity be resolved?
4. Which bean should be injected?

---

## 4. Primary Resolution Rule: By Type

### 4.1 Default Behavior

Spring resolves dependencies **by type first**.

```java
publicOrderService(PaymentService paymentService)

```

Spring:

- Searches for beans implementing `PaymentService`
- If **one** bean found → injects it

---

### 4.2 Example (Single Candidate)

```java
@Component
publicclassCardPaymentServiceimplementsPaymentService {
}

```

```java
@Service
publicclassOrderService {

privatefinal PaymentService paymentService;

publicOrderService(PaymentService paymentService) {
this.paymentService = paymentService;
    }
}

```

Injection succeeds.

---

## 5. Ambiguity Scenario (Multiple Beans of Same Type)

### 5.1 Problem Example

```java
@Component
publicclassCardPaymentServiceimplementsPaymentService {
}

@Component
publicclassUpiPaymentServiceimplementsPaymentService {
}

```

Now Spring finds:

- 2 beans of type `PaymentService`

Result:

```
NoUniqueBeanDefinitionException

```

---

## 6. Secondary Resolution Rule: By Name

### 6.1 When Name-Based Matching Applies

Spring tries **by-name resolution** when:

- Field name or parameter name matches a bean name

```java
@Service
publicclassOrderService {

privatefinal PaymentService cardPaymentService;

publicOrderService(PaymentService cardPaymentService) {
this.cardPaymentService = cardPaymentService;
    }
}

```

If bean name is:

```java
@Component("cardPaymentService")
publicclassCardPaymentServiceimplementsPaymentService {
}

```

Injection succeeds.

---

## 7. Explicit Resolution with `@Qualifier`

### 7.1 Purpose

`@Qualifier` tells Spring:

> Inject this specific bean
> 

---

### 7.2 Example

```java
@Service
publicclassOrderService {

privatefinal PaymentService paymentService;

publicOrderService(
@Qualifier("upiPaymentService") PaymentService paymentService) {
this.paymentService = paymentService;
    }
}

```

Spring:

- Ignores other candidates
- Injects specified bean

---

## 8. Default Selection with `@Primary`

### 8.1 Purpose

`@Primary` defines a **default candidate**.

```java
@Component
@Primary
publicclassCardPaymentServiceimplementsPaymentService {
}

```

Now:

- Spring selects this bean automatically
- No qualifier needed

---

### 8.2 `@Primary` vs `@Qualifier`

| Aspect | @Primary | @Qualifier |
| --- | --- | --- |
| Scope | Global default | Local choice |
| Use case | Common default | Specific injection |
| Override possible | Yes | Yes |

---

## 9. Constructor vs Field Autowiring (Internal Difference)

### 9.1 Constructor Autowiring

- Happens at object creation
- Ensures fully initialized object
- Preferred approach

---

### 9.2 Field Autowiring

- Happens after object creation
- Uses reflection
- Access modifiers bypassed

```java
@Autowired
private PaymentService paymentService;

```

Internally:

- Spring sets field accessible
- Injects value using reflection

---

## 10. Circular Dependency Scenario

### 10.1 Example Problem

```java
@Service
publicclassA {
publicA(B b) {}
}

@Service
publicclassB {
publicB(A a) {}
}

```

Result:

```
BeanCurrentlyInCreationException

```

---

### 10.2 Why Circular Dependency Happens

- Constructor injection requires full object
- Both beans wait for each other
- Deadlock situation

---

### 10.3 How Spring Handles Circular Dependencies

- Constructor injection → ❌ not supported
- Setter/field injection → ✔️ partially supported

Best practice:

> Fix the design, don’t rely on Spring hacks
> 

---

## 11. Optional Dependencies

### 11.1 Using `required = false` (Rare)

```java
@Autowired(required = false)
private DiscountService discountService;

```

Use only when:

- Dependency is truly optional
- Application can function without it

---

## 12. Real Workflow Usage

In real Spring applications:

- Autowiring is mostly constructor-based
- `@Primary` defines defaults
- `@Qualifier` handles special cases
- Circular dependencies are avoided by design

Autowiring is:

- Predictable
- Deterministic
- Strict by design

# PART 11: Spring Expression Language (SpEL – Essentials Only)

---

## 1. Purpose of SpEL

### 1.1 What is SpEL

**Spring Expression Language (SpEL)** is a **powerful expression language** provided by the **Spring Framework** that allows:

- Reading values from properties
- Accessing bean values at runtime
- Performing simple computations
- Making configuration dynamic

SpEL is **not general-purpose business logic**.

It is a **configuration-time and wiring-time tool**.

---

## 2. Why SpEL Exists (Problem-Oriented)

Without SpEL:

- Configuration values are static
- You cannot derive values dynamically
- Cross-bean configuration becomes verbose

SpEL allows:

- Declarative configuration
- Runtime evaluation
- Cleaner configuration without extra Java code

---

## 3. Where SpEL Is Used in Real Applications

SpEL is commonly used in:

- `@Value`
- Conditional configuration
- Reading system/environment properties
- Accessing other bean values
- Simple boolean conditions

> If you do not use @Value, you will rarely touch SpEL directly.
> 

---

## 4. SpEL Syntax Basics

### 4.1 Expression Delimiters

SpEL expressions are written inside:

```
#{ ... }

```

Property placeholders use:

```
${ ... }

```

They are **different and often combined**.

---

## 5. Property Injection with SpEL

### 5.1 Basic Property Injection

```java
@Component
publicclassAppConfigInfo {

@Value("${app.name}")
private String appName;
}

```

Here:

- `${app.name}` → property placeholder
- Resolved from application properties
- Injected at startup

---

### 5.2 Combining Property + SpEL

```java
@Component
publicclassServerConfig {

@Value("#{${server.port} + 100}")
privateint adminPort;
}

```

Workflow:

1. `${server.port}` resolved first
2. SpEL expression evaluated
3. Final value injected

---

## 6. Accessing Bean Properties Using SpEL

### 6.1 Bean-to-Bean Value Access

```java
@Component
publicclassLimits {

publicintmaxUsers() {
return100;
    }
}

```

```java
@Component
publicclassFeatureConfig {

@Value("#{limits.maxUsers()}")
privateint maxUsers;
}

```

Spring behavior:

- Finds `limits` bean
- Calls `maxUsers()`
- Injects returned value

---

## 7. Using SpEL for Simple Conditions

### 7.1 Boolean Evaluation

```java
@Component
publicclassFeatureToggle {

@Value("#{${feature.enabled} == true}")
privateboolean enabled;
}

```

Used for:

- Feature flags
- Conditional logic in configuration

---

## 8. Accessing System and Environment Properties

### 8.1 System Properties

```java
@Value("#{systemProperties['user.home']}")
private String userHome;

```

---

### 8.2 Environment Variables

```java
@Value("#{systemEnvironment['JAVA_HOME']}")
private String javaHome;

```

These are evaluated:

- At container startup
- Before bean usage

---

## 9. SpEL Internal Working (Conceptual)

Internally Spring:

1. Detects `@Value`
2. Delegates to expression resolver
3. Parses SpEL expression
4. Evaluates against context:
    - Properties
    - Beans
    - System environment
5. Injects final result

All this happens **before initialization callbacks**.

---

## 10. What SpEL Should NOT Be Used For

❌ Business logic

❌ Complex calculations

❌ Cross-layer decision making

❌ Runtime-heavy operations

SpEL is:

- Lightweight
- Declarative
- Configuration-oriented

---

## 11. Real Workflow Usage

In real Spring applications, SpEL is mainly used for:

- Externalized configuration
- Feature toggles
- Environment-specific values
- Small derived configuration values

Most developers:

- Use SpEL indirectly via `@Value`
- Rarely write raw expressions

# PART 12: Loose Coupling in Spring

---

## 1. What is Coupling

### 1.1 Definition

**Coupling** describes:

- The **degree of dependency** between classes
- How strongly one class relies on another’s implementation

In the **Spring Framework**, the primary architectural goal is to **minimize coupling**.

---

## 2. Tight Coupling (Problem Case)

### 2.1 What Tight Coupling Looks Like

In tightly coupled systems:

- Classes create their own dependencies
- Classes depend on concrete implementations

---

### 2.2 Example: Tightly Coupled Code

```java
classFileLogger {
publicvoidlog(String msg) {
        System.out.println("File log: " + msg);
    }
}

classOrderService {

private FileLogger logger;

publicOrderService() {
// Tight coupling
this.logger =newFileLogger();
    }

publicvoidplaceOrder() {
        logger.log("Order placed");
    }
}

```

---

### 2.3 Problems Caused by Tight Coupling

- Cannot replace `FileLogger` easily
- Hard to test
- Hard to extend
- Violates Open/Closed Principle

---

## 3. Loose Coupling (Desired State)

### 3.1 What Loose Coupling Means

Loose coupling means:

- Classes depend on **abstractions (interfaces)**
- Implementations are injected externally
- Changes do not propagate unnecessarily

---

### 3.2 Refactored Example (Loose Coupling)

```java
interfaceLogger {
voidlog(String msg);
}

classFileLoggerimplementsLogger {
publicvoidlog(String msg) {
        System.out.println("File log: " + msg);
    }
}

classOrderService {

privatefinal Logger logger;

publicOrderService(Logger logger) {
this.logger = logger;
    }

publicvoidplaceOrder() {
        logger.log("Order placed");
    }
}

```

This code is:

- Testable
- Flexible
- Spring-friendly

---

## 4. How Spring Enforces Loose Coupling

Spring enforces loose coupling through:

1. Inversion of Control
2. Dependency Injection
3. Interface-driven design
4. Externalized configuration

---

## 5. Loose Coupling Using Spring (Before vs After)

### 5.1 Without Spring

```java
OrderServiceservice=
newOrderService(newFileLogger());

```

You still manage:

- Object creation
- Dependency wiring

---

### 5.2 With Spring

```java
@Service
publicclassOrderService {

privatefinal Logger logger;

publicOrderService(Logger logger) {
this.logger = logger;
    }
}

```

```java
@Component
publicclassFileLoggerimplementsLogger {
}

```

Spring:

- Creates both objects
- Injects dependency
- Manages lifecycle

---

## 6. Internal Working: How Spring Keeps Classes Decoupled

Internally:

- Beans are registered by type
- Dependencies are resolved by interface
- Concrete implementations are hidden from consumers

This ensures:

- No class knows about concrete creation logic
- No class depends on implementation details

---

## 7. Loose Coupling and Testing

### 7.1 Unit Testing Without Spring

```java
classMockLoggerimplementsLogger {
publicvoidlog(String msg) {
        System.out.println("Mock log: " + msg);
    }
}

OrderServiceservice=
newOrderService(newMockLogger());

```

Because of loose coupling:

- Spring is not required for testing
- Dependencies are easily replaceable

---

## 8. Real-World Workflow Usage

In real Spring applications:

- Controllers depend on service interfaces
- Services depend on repository interfaces
- Repositories hide database details

This ensures:

- Easy refactoring
- Technology replacement without business logic changes
- Long-term maintainability

---

## 9. Why Loose Coupling Is a Non-Negotiable Principle

Loose coupling:

- Enables scalability
- Improves readability
- Makes applications future-proof

Spring Core exists **primarily** to enforce this principle systematically.

# PART 13: Exception Scenarios in Spring Core

---

## 1. Why Exception Scenarios Matter in Spring Core

Spring Core performs most of its work:

- At **startup time**
- During **bean creation and wiring**

Because of this, **misconfiguration errors surface early** and often **prevent application startup**.

Understanding these exceptions helps you:

- Diagnose startup failures quickly
- Fix configuration issues systematically
- Design safer bean graphs

All examples below are based on how the **Spring Framework** container actually behaves at runtime.

---

## 2. Bean Creation Failures

### 2.1 What Is a Bean Creation Failure

A **bean creation failure** occurs when:

- Spring is unable to create a bean instance
- Or fails during dependency injection or initialization

These errors usually appear as:

```
BeanCreationException

```

---

### 2.2 Common Causes

- Missing dependencies
- Incorrect constructor arguments
- Exceptions inside constructors
- Failing init methods

---

### 2.3 Example: Exception Inside Constructor

```java
@Component
publicclassDatabaseClient {

publicDatabaseClient() {
if (true) {
thrownewRuntimeException("DB not reachable");
        }
    }
}

```

Spring behavior:

- Tries to instantiate `DatabaseClient`
- Constructor throws exception
- Application startup fails immediately

---

### 2.4 Example: Exception in Init Method

```java
@Component
publicclassCacheLoader {

@PostConstruct
publicvoidinit() {
thrownewIllegalStateException("Cache load failed");
    }
}

```

Internal flow:

1. Bean instantiated
2. Dependencies injected
3. `@PostConstruct` invoked
4. Exception thrown → startup aborted

---

## 3. NoSuchBeanDefinitionException

### 3.1 What This Exception Means

```
NoSuchBeanDefinitionException

```

Occurs when:

- Spring cannot find **any bean** matching the required dependency

---

### 3.2 Typical Scenario

```java
@Service
publicclassOrderService {

privatefinal PaymentService paymentService;

publicOrderService(PaymentService paymentService) {
this.paymentService = paymentService;
    }
}

```

But:

- No bean implements `PaymentService`

Spring result:

```
NoSuchBeanDefinitionException: No qualifying bean of type 'PaymentService'

```

---

### 3.3 Why This Happens Internally

- Dependency type identified
- Bean registry searched
- Zero candidates found
- Resolution fails

---

### 3.4 Real Workflow Cause

- Missing `@Component` / `@Service`
- Package not included in component scan
- Configuration class not loaded

---

## 4. NoUniqueBeanDefinitionException

### 4.1 What This Exception Means

```
NoUniqueBeanDefinitionException

```

Occurs when:

- **Multiple beans** match the same dependency
- Spring cannot decide which one to inject

---

### 4.2 Example Scenario

```java
@Component
publicclassCardPaymentServiceimplementsPaymentService {
}

@Component
publicclassUpiPaymentServiceimplementsPaymentService {
}

```

```java
@Service
publicclassOrderService {

publicOrderService(PaymentService paymentService) {
    }
}

```

Result:

```
expected single matching bean but found 2

```

---

### 4.3 Internal Resolution Attempt

Spring tries:

1. By type → multiple matches ❌
2. By name → no match ❌
3. @Primary → not defined ❌
4. @Qualifier → not provided ❌

→ Exception thrown

---

## 5. UnsatisfiedDependencyException

### 5.1 What It Represents

```
UnsatisfiedDependencyException

```

This is a **wrapper exception** indicating:

- A dependency could not be resolved
- Root cause is usually one of:
    - NoSuchBeanDefinitionException
    - NoUniqueBeanDefinitionException
    - BeanCreationException

---

### 5.2 Example

```java
@Service
publicclassBillingService {

publicBillingService(TaxService taxService) {
    }
}

```

If `TaxService` is missing:

- `UnsatisfiedDependencyException` is thrown
- Root cause shows missing bean

---

## 6. Circular Dependency Errors

### 6.1 Constructor-Based Circular Dependency

```java
@Service
publicclassA {
publicA(B b) {}
}

@Service
publicclassB {
publicB(A a) {}
}

```

Result:

```
BeanCurrentlyInCreationException

```

---

### 6.2 Why This Happens Internally

- Spring tries to create `A`
- Needs `B`
- Tries to create `B`
- Needs `A`
- Deadlock → failure

---

### 6.3 Important Rule

- Constructor injection + circular dependency → ❌ always fails
- Field/setter injection → partially supported but discouraged

Best practice:

> Break the cycle by redesigning responsibilities
> 

---

## 7. Scope-Related Exceptions

### 7.1 Injecting Request/Session Bean into Singleton

```java
@Component
@Scope("request")
publicclassRequestData {
}

```

```java
@Service
publicclassOrderService {

publicOrderService(RequestData requestData) {
    }
}

```

Result at startup:

```
ScopeNotActiveException

```

---

### 7.2 Why This Happens

- Singleton beans created at startup
- Request scope active only during HTTP request
- No request context available

Solution:

- Use proxies (advanced)
- Redesign dependency flow

---

## 8. BeanDefinitionOverrideException

### 8.1 What This Means

Occurs when:

- Two beans with the **same name** are defined
- Bean overriding is disabled (default in modern Spring)

---

### 8.2 Example

```java
@Component("paymentService")
publicclassCardPaymentService {
}

@Component("paymentService")
publicclassUpiPaymentService {
}

```

Result:

```
BeanDefinitionOverrideException

```

---

## 9. Configuration and Scanning Errors

### 9.1 Missing Component Scan

```java
@Configuration
@ComponentScan("com.app.service")
publicclassAppConfig {
}

```

If repositories are in:

```
com.app.repository

```

Then:

- Repository beans are not registered
- Injection fails later

---

## 10. Real Workflow Debugging Strategy

When Spring Core throws an exception:

1. **Read the root cause**, not just top-level message
2. Identify:
    - Missing bean
    - Multiple beans
    - Lifecycle failure
3. Check:
    - Annotations
    - Package scanning
    - Bean scopes
    - Constructor dependencies

Spring errors are:

- Verbose
- Deterministic
- Always traceable if read carefully

# PART 14: Spring Core Runtime Flow

---

## 1. Why Understanding Runtime Flow Matters

Spring Core does **most of its work automatically** at application startup.

To design, debug, and scale real applications, you must understand:

- **What happens first**
- **What happens next**
- **Who controls what (Spring vs Servlet container)**

This section explains the **exact runtime sequence** used by the **Spring Framework** core.

---

## 2. High-Level Runtime Phases

A Spring application runtime can be divided into **four major phases**:

1. Container bootstrap
2. Bean definition discovery
3. Bean instantiation & dependency injection
4. Bean usage during runtime

Each phase has strict ordering and rules.

---

## 3. Phase 1: Application Startup & Container Bootstrap

### 3.1 Entry Point of a Spring Application

In a non–Spring Boot context (core understanding):

```java
publicstaticvoidmain(String[] args) {
ApplicationContextcontext=
newAnnotationConfigApplicationContext(AppConfig.class);
}

```

This line triggers **everything**.

---

### 3.2 What Happens Internally

Spring performs:

1. Creates the **ApplicationContext**
2. Initializes internal infrastructure beans
3. Prepares environment and property sources
4. Registers configuration classes

At this stage:

- No business beans are created yet
- Only container infrastructure is prepared

---

## 4. Phase 2: Bean Definition Discovery

### 4.1 Component Scanning

Spring scans packages defined by:

- `@ComponentScan`
- Or implicit scanning (e.g., in Spring Boot)

```java
@ComponentScan("com.app")

```

Spring looks for:

- `@Component`
- `@Service`
- `@Repository`
- `@Controller`
- `@Configuration`
- `@Bean` methods

---

### 4.2 What Spring Registers (Important)

Spring does **not create objects yet**.

Instead, it creates **BeanDefinitions** containing:

- Bean class
- Scope
- Dependencies
- Init/destroy methods
- Autowiring metadata

These are stored in the **Bean Definition Registry**.

---

## 5. Phase 3: Bean Instantiation (Singleton Beans)

### 5.1 When Instantiation Starts

After scanning completes:

- Spring starts creating **singleton beans**
- This happens **eagerly** by default

---

### 5.2 Exact Creation Order (Simplified)

For each singleton bean:

1. Instantiate bean (constructor call)
2. Inject dependencies
3. Apply BeanPostProcessors
4. Call `@PostConstruct` / init-method
5. Mark bean as ready

---

### 5.3 Dependency Graph Resolution

Spring builds a **dependency graph**:

```
Controller
   ↓
Service
   ↓
Repository

```

Rules:

- Lower-level beans created first
- Higher-level beans created after dependencies resolve

If any dependency fails → startup stops.

---

## 6. Phase 4: Dependency Injection in Action

### 6.1 Constructor Injection Timing

Constructor injection happens:

- At object creation time
- Before any other lifecycle callback

```java
@Service
publicclassOrderService {

publicOrderService(PaymentService paymentService) {
// Dependency already resolved here
    }
}

```

If dependency cannot be resolved:

- Bean creation fails immediately
- Application does not start

---

### 6.2 Field / Setter Injection Timing

- Object is created first
- Dependencies injected later using reflection
- Happens before initialization callbacks

---

## 7. Bean Post Processing (Critical Internal Hook)

### 7.1 What Are BeanPostProcessors

These are internal components that:

- Intercept bean creation
- Modify or wrap beans

Used for:

- `@Autowired`
- `@Value`
- AOP proxies
- Transactions
- Security

---

### 7.2 Why This Matters

Without BeanPostProcessors:

- Dependency injection would not work
- Annotations would be ignored
- Proxies would not exist

---

## 8. Application Ready State

After all singleton beans are created:

- Container is fully initialized
- All dependencies are satisfied
- Application is considered **ready**

At this point:

- No more bean creation (unless prototype)
- Business logic can execute safely

---

## 9. Runtime Bean Usage Phase

### 9.1 How Beans Are Used

Beans are **never manually fetched** in real applications.

Instead:

- Beans are injected into other beans
- Methods are invoked during runtime events

Example:

```java
@Controller
publicclassOrderController {

privatefinal OrderService orderService;

publicOrderController(OrderService orderService) {
this.orderService = orderService;
    }
}

```

---

## 10. Spring Core vs Servlet Container (Web Context)

### 10.1 Two Containers, Two Responsibilities

| Container | Responsibility |
| --- | --- |
| Servlet Container (e.g. Apache Tomcat) | HTTP lifecycle |
| Spring Container | Object & dependency lifecycle |

---

### 10.2 How They Work Together (Conceptual Flow)

1. Server starts
2. Servlet container initializes
3. Spring container initializes inside it
4. Spring-managed beans are created
5. HTTP request arrives
6. Servlet delegates to Spring-managed controller
7. Spring executes business logic via injected beans

Spring Core:

- Never handles HTTP directly
- Always supports business logic & DI

---

## 11. Application Shutdown Flow

### 11.1 Shutdown Trigger

Shutdown happens when:

- JVM stops
- Application context closes

---

### 11.2 Destruction Sequence

Spring:

1. Calls `@PreDestroy`
2. Calls destroy-method
3. Releases resources
4. Clears singleton cache

Prototype beans:

- Are **not destroyed** by Spring

---

## 12. Real Workflow Usage (End-to-End)

Complete lifecycle:

```
JVM Start
  ↓
Spring Container Bootstrap
  ↓
Bean Scanning
  ↓
Bean Instantiation
  ↓
Dependency Injection
  ↓
Initialization
  ↓
Application Ready
  ↓
Runtime Execution
  ↓
Shutdown
  ↓
Bean Destruction

```

This flow is **deterministic** and **repeatable**.

---

## 13. Why Runtime Flow Knowledge Is Critical

Understanding runtime flow helps you:

- Place logic in correct lifecycle phase
- Debug startup failures
- Avoid circular dependencies
- Design scalable architectures

# FINAL PART: Consolidation & Mastery Section

*(This is the ONLY section that contains consolidation, best practices, common mistakes, interview content, and revision notes, exactly as requested.)*

---

## 1. Complete Spring Core Workflow (Start → Bean Usage → Shutdown)

This section consolidates **everything you learned** into one **end-to-end execution flow** inside the **Spring Framework**.

---

### 1.1 Application Startup (Bootstrapping Phase)

1. JVM starts
2. Spring `ApplicationContext` is created
3. Environment & property sources are prepared
4. Configuration classes are registered

At this point:

- No business beans exist yet
- Only container infrastructure is ready

---

### 1.2 Bean Discovery Phase

Spring performs:

- Component scanning
- `@Configuration` processing
- `@Bean` method detection

Spring registers **BeanDefinitions** containing:

- Bean class
- Scope
- Dependency metadata
- Lifecycle callbacks

> Important: Objects are not created yet
> 

---

### 1.3 Bean Instantiation & Dependency Injection Phase

For **singleton beans**:

1. Bean instantiated (constructor)
2. Constructor injection performed
3. Field/setter injection (if any)
4. `BeanPostProcessor` logic applied
5. `@PostConstruct` / init-method executed
6. Bean marked as ready

Dependency rules enforced here:

- By-type resolution
- `@Primary`
- `@Qualifier`
- Circular dependency detection

---

### 1.4 Application Ready State

After all singleton beans are created:

- Container is fully initialized
- Application is safe to serve requests
- No lazy surprises for critical beans

---

### 1.5 Runtime Bean Usage

During runtime:

- Beans interact via injected references
- No new singleton creation
- Prototype beans created on demand
- Business logic executes safely

---

### 1.6 Shutdown Phase

When application stops:

1. Spring context closes
2. `@PreDestroy` invoked
3. destroy-method invoked
4. Resources released

Prototype beans:

- Not destroyed by Spring
- Developer-managed cleanup required

---

## 2. Best Practices (Clean, Production-Grade Spring Core)

### 2.1 Dependency Injection Rules

✔ Prefer **constructor injection**

✔ Make dependencies `final`

✔ Inject **interfaces**, not implementations

✔ Avoid field injection in production code

---

### 2.2 Bean Design Rules

✔ Keep beans **stateless** (especially singletons)

✔ One responsibility per bean

✔ Avoid heavy logic in constructors

✔ Use lifecycle hooks only for setup/cleanup

---

### 2.3 Configuration Strategy

✔ Business logic → annotations (`@Service`, `@Component`)

✔ Infrastructure → Java config (`@Configuration`, `@Bean`)

✔ Avoid XML in new projects

✔ Externalize values using properties + `@Value`

---

### 2.4 Scope Usage Rules

✔ Singleton → default choice (95% cases)

✔ Prototype → rare, controlled usage

✔ Request/session → web-only, minimal state

✔ Never inject request/session directly into singleton without proxies

---

## 3. Common Mistakes and How to Avoid Them

### 3.1 Using Field Injection Everywhere

❌ Hidden dependencies

❌ Poor testability

❌ Reflection-based wiring

✔ Fix: use constructor injection

---

### 3.2 Circular Dependencies

❌ Tight bidirectional design

❌ Constructor injection deadlocks

✔ Fix: redesign responsibilities

✔ Introduce intermediate services

---

### 3.3 Multiple Beans Without Qualifiers

❌ `NoUniqueBeanDefinitionException`

✔ Fix:

- Use `@Primary` for defaults
- Use `@Qualifier` for specific cases

---

### 3.4 Putting Business Logic in Lifecycle Methods

❌ Startup becomes fragile

❌ Hard-to-debug failures

✔ Fix: keep lifecycle methods minimal

---

### 3.5 Overusing Prototype Scope

❌ Memory leaks

❌ Manual cleanup burden

✔ Fix: prefer stateless singleton design

---

## 4. Interview Questions (Conceptual + Scenario-Based)

### Conceptual

1. Why is IoC fundamental to Spring?
2. Difference between IoC and DI?
3. Why is constructor injection preferred?
4. How does Spring resolve dependency ambiguity?
5. Why are most Spring beans singleton?

---

### Scenario-Based

1. Two beans implement the same interface. How do you control injection?
2. A Spring app fails at startup with `BeanCreationException`. How do you debug?
3. When would you choose prototype over singleton?
4. How does Spring handle circular dependencies internally?
5. What happens if a `@PostConstruct` method throws an exception?

---

## 5. Quick Revision Notes (High-Signal Takeaways)

- Spring Core = **IoC + DI + Lifecycle Management**
- Spring Container owns **object creation**
- Beans are **metadata-driven**, not manually created
- Constructor injection = safe, clean, testable
- `ApplicationContext` is the real container
- Most work happens **at startup**
- Errors are **fail-fast by design**
- Loose coupling is the ultimate goal

---

## 6. Mastery Checklist (Self-Assessment)

You have **mastered Spring Core** if you can:

✔ Explain Spring startup flow without notes

✔ Debug bean creation errors confidently

✔ Design clean, loosely coupled services

✔ Choose correct scope instinctively

✔ Predict autowiring behavior before runtime

✔ Write Spring code without `new` keyword