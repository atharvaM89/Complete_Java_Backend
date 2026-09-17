# Spring Data JPA Detail

# PART 1: Introduction to ORM

---

## 1. What is ORM (Object Relational Mapping)

ORM is a technique that allows you to **map Java objects to database tables**.

Instead of writing SQL manually, you work with **Java objects**, and ORM handles conversion to SQL behind the scenes.

### 🔁 Mapping Concept

| Java (OOP) | Database (RDBMS) |
| --- | --- |
| Class | Table |
| Object | Row |
| Field | Column |

---

### ✅ Example (Without ORM vs With ORM)

### ❌ Without ORM (JDBC)

```
Connectioncon=DriverManager.getConnection(url,user,pass);

PreparedStatementps=con.prepareStatement(
"INSERT INTO users(name, email) VALUES (?, ?)"
);

ps.setString(1,"Atharva");
ps.setString(2,"atharva@gmail.com");

ps.executeUpdate();
```

👉 Problems:

- Too much boilerplate
- Manual SQL writing
- Error-prone
- Hard to maintain

---

### ✅ With ORM (JPA/Hibernate)

```
Useruser=newUser();
user.setName("Atharva");
user.setEmail("atharva@gmail.com");

userRepository.save(user);
```

👉 ORM automatically:

- Converts object → SQL
- Executes query
- Maps result → object

---

## 2. Why ORM is Needed (Real Problems It Solves)

---

### ❌ Problem 1: Too Much SQL Writing

In real projects:

- Hundreds of queries
- Joins, filters, updates

👉 ORM reduces this drastically

---

### ❌ Problem 2: Tight Coupling with Database

With JDBC:

```
SELECT*FROM usersWHERE name= ?
```

If DB changes → queries break

👉 ORM decouples logic:

- You work with objects
- DB structure is abstracted

---

### ❌ Problem 3: Manual Mapping

Without ORM:

```
Useruser=newUser();
user.setId(rs.getLong("id"));
user.setName(rs.getString("name"));
```

👉 ORM does this automatically

---

### ❌ Problem 4: Boilerplate Code

- Connection handling
- Closing resources
- Exception handling

👉 ORM frameworks manage this internally

---

## 3. JDBC vs ORM (Practical Comparison)

---

### 🔍 1. Development Speed

| JDBC | ORM |
| --- | --- |
| Slow | Fast |
| Manual SQL | Auto-generated |

---

### 🔍 2. Code Maintainability

| JDBC | ORM |
| --- | --- |
| Messy | Clean |
| SQL everywhere | Centralized logic |

---

### 🔍 3. Learning Curve

| JDBC | ORM |
| --- | --- |
| Easy start | Slight learning curve |
| Hard scaling | Easy scaling |

---

### 🔍 4. Real-World Use

| Scenario | Used |
| --- | --- |
| Small script | JDBC |
| Enterprise backend | ORM (JPA + Hibernate) ✅ |

---

## 4. How ORM Works Internally (High-Level)

---

### Step-by-step flow:

1. You create a Java object
2. ORM tracks the object
3. Converts it into SQL
4. Executes SQL on DB
5. Maps result back to object

---

### 🔁 Example Flow

```
userRepository.save(user);
```

👉 Internally:

```
User Object
   ↓
ORM converts
   ↓
INSERT INTO users ...
   ↓
Database
```

---

## 5. Key Idea You MUST Understand

👉 ORM is NOT magic

👉 It is just a **layer over JDBC**

Internally:

- ORM still uses JDBC
- But hides complexity

---

## 6. When ORM is Used in Real Backend Workflow

---

### Typical Spring Boot Flow:

```
Controller → Service → Repository → ORM → Database
```

ORM sits between:

```
Repository ↔ Database
```

---

### Example:

```
// Service layer
userService.createUser(userDTO);

// Repository layer
userRepository.save(user);

// ORM (Hibernate)
// Converts to SQL and executes
```

---

## 7. Important Insight (Industry Level)

👉 You are NOT replacing SQL

👉 You are **abstracting SQL**

You still need:

- DB understanding
- Query thinking
- Relationship knowledge

---

## 8. Limitations of ORM (Realistic View)

---

### ⚠️ Not Always Perfect

- Complex queries → still need custom SQL
- Performance tuning → sometimes manual queries
- Debugging → harder than JDBC

---

### ⚠️ Hidden Queries

Sometimes:

```
user.getOrders();
```

👉 triggers SQL behind the scenes (important later)

---

## 🚀 What You Should Take From This Part

- ORM maps Java ↔ Database
- Reduces boilerplate
- Improves maintainability
- Still relies on SQL internally

# PART 2: Introduction to JPA and Hibernate

---

## 1. What is JPA (Java Persistence API)

---

### 🔹 Definition

**JPA is a specification (set of rules/interfaces)** for ORM in Java.

👉 It does NOT contain implementation

👉 It only defines **how ORM should work**

---

### 🔍 Key Idea

Think of JPA as:

```
"WHAT should be done"
```

NOT:

```
"HOW it is done"
```

---

### 📦 Example

JPA defines:

```
publicinterfaceEntityManager {
voidpersist(Objectentity);
    <T>Tfind(Class<T>entityClass,ObjectprimaryKey);
}
```

👉 But:

- It does NOT implement these methods
- It only defines them

---

### 📌 Important Insight

👉 You NEVER use JPA alone

👉 You ALWAYS need an implementation

---

## 2. What is Hibernate

---

### 🔹 Definition

**Hibernate is an ORM framework that IMPLEMENTS JPA**

👉 It provides:

- Actual logic
- SQL generation
- DB interaction

---

### 🔍 Key Idea

Hibernate is:

```
"HOW things are done"
```

---

### 📦 Example

When you call:

```
entityManager.persist(user);
```

👉 JPA defines it

👉 Hibernate executes it

---

## 3. JPA vs Hibernate (Clear Difference)

---

| Feature | JPA | Hibernate |
| --- | --- | --- |
| Type | Specification | Implementation |
| Provides Code? | ❌ No | ✅ Yes |
| Defines Rules? | ✅ Yes | ❌ No |
| Used Directly? | ❌ No | ✅ Yes |

---

### 🔁 Analogy (Important)

| Concept | Real-world |
| --- | --- |
| JPA | Interface |
| Hibernate | Implementation |

---

### Example:

```
Listlist=newArrayList();
```

- `List` → JPA-like (interface)
- `ArrayList` → Hibernate-like (implementation)

---

## 4. How Spring Boot Uses JPA + Hibernate

---

### 🔁 Full Stack Flow

```
Spring Data JPA → JPA → Hibernate → JDBC → Database
```

---

### 🔍 Layer Breakdown

### 1️⃣ Spring Data JPA

- Provides repository abstraction
- You write interfaces (no implementation)

```
public interface UserRepository extends JpaRepository<User,Long> {
}
```

---

### 2️⃣ JPA

- Defines rules (EntityManager, annotations)

```
@Entity
classUser {}
```

---

### 3️⃣ Hibernate

- Implements JPA
- Generates SQL
- Manages objects

---

### 4️⃣ JDBC

- Executes SQL

---

### 5️⃣ Database

- Stores data

---

## 5. Real Execution Flow (VERY IMPORTANT)

---

### Example:

```
userRepository.save(user);
```

---

### Internally:

```
1. Spring Data JPA receives call
2. Uses JPA EntityManager
3. Hibernate implements logic
4. Hibernate generates SQL
5. JDBC executes SQL
6. Data stored in DB
```

---

## 6. Core Components You Will Use

---

### 🔹 1. Entity

Represents table

```
@Entity
classUser {}
```

---

### 🔹 2. EntityManager (JPA)

Main interface to interact with persistence

```
entityManager.persist(user);
```

---

### 🔹 3. Session (Hibernate)

Hibernate’s internal version of EntityManager

```
Sessionsession=sessionFactory.openSession();
```

👉 In Spring Boot:

- You usually DON’T use Session directly

---

### 🔹 4. Repository (Spring Data JPA)

```
userRepository.save(user);
```

👉 Simplifies everything

---

## 7. Why We Use Spring Data JPA (Important)

---

Without Spring Data:

```
entityManager.persist(user);
entityManager.find(User.class,id);
```

---

With Spring Data:

```
userRepository.save(user);
userRepository.findById(id);
```

---

👉 Benefits:

- No boilerplate
- Auto query generation
- Cleaner code

---

## 8. Key Annotations Provided by JPA

---

These are defined by JPA but implemented by Hibernate:

---

### 🔹 @Entity

Marks class as DB table

```
@Entity
classUser {}
```

---

### 🔹 @Id

Primary key

```
@Id
privateLongid;
```

---

### 🔹 @GeneratedValue

Auto-generates ID

```
@GeneratedValue(strategy=GenerationType.IDENTITY)
```

---

### 🔹 @Column

Maps field to column

```
@Column(name="user_name")
privateStringname;
```

---

👉 We will go deep in Part 3

---

## 9. Important Internal Concept (Must Understand)

---

### 🔁 Persistence Context

👉 This is where Hibernate stores objects temporarily

```
Java Object ↔ Persistence Context ↔ Database
```

---

### Example:

```
Useruser=entityManager.find(User.class,1L);
```

👉 Hibernate:

- Loads from DB
- Stores in memory (Persistence Context)

---

Later:

```
user.setName("Atharva");
```

👉 Hibernate automatically updates DB (no explicit query)

---

## 10. Key Insight (VERY IMPORTANT)

---

👉 You are NOT directly interacting with DB

Instead:

```
You → JPA → Hibernate → DB
```

---

👉 That means:

- Changes in objects = changes in DB
- Managed automatically

---

## 11. Real-World Usage Flow

---

### Example API Flow

```
POST /users
```

---

### Backend Flow

```
Controller → Service → Repository → Hibernate → DB
```

---

### Code Example

```
// Controller
@PostMapping("/users")
publicUsercreateUser(@RequestBodyUseruser) {
returnuserService.save(user);
}
```

---

```
// Service
publicUsersave(Useruser) {
returnuserRepository.save(user);
}
```

---

```
// Repository
publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {}
```

---

# PART 3: Entity Mapping (VERY IMPORTANT)

---

This is the **foundation of everything in JPA + Hibernate**.

If your **entity mapping is wrong → entire backend breaks**

So we go **deep + practical**.

---

## 1. What is an Entity

---

### 🔹 Definition

An **Entity is a Java class mapped to a database table**

👉 Each object = one row in table

---

### 🔁 Mapping Concept

```
User (Java Class)  →  users (Table)
user object        →  row
fields             →  columns
```

---

## 2. @Entity Annotation

---

### 🔹 Purpose

Marks class as a **JPA entity (table)**

---

### ✅ Example

```
importjakarta.persistence.Entity;

@Entity
publicclassUser {
}
```

---

### 🔍 Internal Working

When application starts:

- Hibernate scans classes
- Finds `@Entity`
- Registers it in persistence context
- Maps it to a table

---

### ⚠️ Important Rules

- Class must have **default constructor**
- Must have **@Id field (mandatory)**

---

## 3. @Table Annotation

---

### 🔹 Purpose

Used to specify **table details**

---

### ✅ Example

```
importjakarta.persistence.*;

@Entity
@Table(name="users")
publicclassUser {
}
```

---

### 🔍 Why Needed?

If not used:

```
@Entity
classUser {}
```

👉 Table name defaults to:

```
user
```

---

### 🔧 Customization

```
@Table(
name="users",
schema="public"
)
```

---

## 4. Primary Key Mapping (@Id)

---

### 🔹 Purpose

Defines **primary key**

---

### ✅ Example

```
@Id
privateLongid;
```

---

### 🔍 Why Important?

- Hibernate tracks entities using ID
- Required for:
    - Updates
    - Deletes
    - Caching internally

---

## 5. @GeneratedValue (Auto ID Generation)

---

### 🔹 Purpose

Automatically generates ID values

---

### ✅ Example

```
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;
```

---

### 🔍 Strategies (Real Use)

### 1️⃣ IDENTITY (Most Used)

```
@GeneratedValue(strategy=GenerationType.IDENTITY)
```

👉 DB auto-increments ID

✔ Used in MySQL, PostgreSQL

✔ Simple and common

---

### 2️⃣ AUTO

```
@GeneratedValue(strategy=GenerationType.AUTO)
```

👉 Hibernate decides strategy

---

### ⚠️ Real-World Recommendation

👉 Always use:

```
GenerationType.IDENTITY
```

---

## 6. Basic Field Mapping (@Column)

---

### 🔹 Purpose

Maps Java field → DB column

---

### ✅ Example

```
@Column(name="user_name")
privateStringname;
```

---

### 🔧 Common Attributes

---

### 1️⃣ name

```
@Column(name="user_name")
```

---

### 2️⃣ nullable

```
@Column(nullable=false)
```

👉 NOT NULL constraint

---

### 3️⃣ unique

```
@Column(unique=true)
```

---

### 4️⃣ length

```
@Column(length=100)
```

---

### ✅ Full Example

```
@Column(
name="email",
nullable=false,
unique=true,
length=150
)
privateStringemail;
```

---

## 7. Full Entity Example (Real-World Ready)

---

```
importjakarta.persistence.*;

@Entity// Marks class as table
@Table(name="users")// Table name in DB
publicclassUser {

    @Id// Primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY)// Auto increment
privateLongid;

    @Column(name="name",nullable=false)
privateStringname;

    @Column(name="email",nullable=false,unique=true)
privateStringemail;

    @Column(name="age")
privateintage;

// Default constructor (MANDATORY)
publicUser() {}

// Parameterized constructor
publicUser(Stringname,Stringemail,intage) {
this.name=name;
this.email=email;
this.age=age;
    }

// Getters & Setters
publicLonggetId() {returnid; }

publicStringgetName() {returnname; }
publicvoidsetName(Stringname) {this.name=name; }

publicStringgetEmail() {returnemail; }
publicvoidsetEmail(Stringemail) {this.email=email; }

publicintgetAge() {returnage; }
publicvoidsetAge(intage) {this.age=age; }
}
```

---

## 8. How Hibernate Converts This to Table

---

### 🔁 Generated SQL (Conceptual)

```
CREATETABLE users (
    id BIGINT AUTO_INCREMENTPRIMARYKEY,
    nameVARCHAR(255)NOTNULL,
    emailVARCHAR(255)UNIQUENOTNULL,
    ageINT
);
```

---

👉 You NEVER write this manually in most cases

---

## 9. Field Types Mapping (Important)

---

| Java Type | DB Type |
| --- | --- |
| String | VARCHAR |
| int | INT |
| long | BIGINT |
| boolean | BOOLEAN |
| LocalDate | DATE |
| LocalDateTime | TIMESTAMP |

---

### ✅ Example

```
@Column(name="created_at")
privateLocalDateTimecreatedAt;
```

---

## 10. Transient Fields

---

### 🔹 Purpose

Ignore field (not stored in DB)

---

### ✅ Example

```
@Transient
privateStringtempData;
```

---

👉 Used for:

- Calculated values
- Temporary logic

---

## 11. Enum Mapping

---

### 🔹 Example Enum

```
publicenumRole {
USER,
ADMIN
}
```

---

### ✅ Mapping

```
@Enumerated(EnumType.STRING)
privateRolerole;
```

---

### 🔍 Why STRING?

👉 Safer than ORDINAL

| Type | Problem |
| --- | --- |
| ORDINAL | Breaks if enum order changes |
| STRING | Stable ✅ |

---

## 12. Important Internal Working

---

### 🔁 What Happens on Save

```
userRepository.save(user);
```

---

### Step-by-step:

```
1. Entity object created
2. Hibernate reads annotations
3. Maps fields → columns
4. Generates SQL
5. Executes via JDBC
```

---

## 13. Common Mistakes (VERY IMPORTANT)

---

### ❌ Missing @Id

👉 Application fails to start

---

### ❌ No Default Constructor

👉 Hibernate cannot instantiate object

---

### ❌ Wrong Data Types

👉 DB mismatch errors

---

### ❌ Using Primitive for Nullable

```
privateintage;// ❌
```

👉 Cannot store NULL

✔ Use:

```
privateIntegerage;// ✅
```

---

## 14. Real-World Workflow

---

### Example:

```
Useruser=newUser("Atharva","atharva@gmail.com",21);
userRepository.save(user);
```

---

👉 Hibernate:

- Reads annotations
- Builds SQL
- Inserts into DB

# PART 4: Hibernate Internal Working (Simplified but CRITICAL)

---

This part is where most developers **get confused**.

If you understand this properly:

👉 You will debug issues easily

👉 You will write efficient backend code

👉 You will understand *why Hibernate behaves weird sometimes*

---

## 1. Core Idea Before Starting

---

👉 Hibernate does NOT directly interact with DB every time

👉 It uses an **intermediate layer**

```
Java Object ↔ Persistence Context ↔ Database
```

---

## 2. EntityManager vs Session

---

### 🔹 EntityManager (JPA)

- Defined by JPA
- Standard interface
- Used in Spring Boot internally

---

### 🔹 Session (Hibernate)

- Hibernate-specific
- Implementation of EntityManager

---

### 🔁 Relationship

```
EntityManager → internally uses → Hibernate Session
```

---

### ✅ Example

```
@PersistenceContext
privateEntityManagerentityManager;
```

---

👉 Internally:

```
EntityManager → delegates to → Hibernate Session
```

---

### ⚠️ Real-World Note

👉 In Spring Boot:

- You mostly **DO NOT use EntityManager directly**
- You use:

```
userRepository.save(user);
```

---

## 3. What is Persistence Context (MOST IMPORTANT)

---

### 🔹 Definition

**Persistence Context = First-level cache managed by Hibernate**

👉 It stores:

- Managed entities
- Tracks changes automatically

---

### 🔁 Visual Flow

```
[ Java Object ]
        ↓
[ Persistence Context ]
        ↓
[ Database ]
```

---

## 4. Why Persistence Context Exists

---

### Problem Without It:

```
Every change → direct DB call ❌
```

---

### With Persistence Context:

```
Changes tracked → batched → optimized DB calls ✅
```

---

## 5. How Persistence Context Works

---

### ✅ Example

```
Useruser=entityManager.find(User.class,1L);
```

---

### Internally:

```
1. Check Persistence Context
2. If not found → query DB
3. Store entity in Persistence Context
4. Return object
```

---

### 🔁 Next Time:

```
Useruser2=entityManager.find(User.class,1L);
```

👉 No DB call

👉 Comes from memory

---

## 6. Dirty Checking (VERY IMPORTANT)

---

### 🔹 Concept

Hibernate automatically detects changes in entities and updates DB

---

### ✅ Example

```
Useruser=entityManager.find(User.class,1L);

user.setName("New Name");// No save() call
```

---

### 🔁 Internally:

```
1. Entity is in Persistence Context
2. Hibernate tracks original state
3. Detects change
4. Generates UPDATE query automatically
```

---

### ⚠️ Important

👉 Happens ONLY if:

- Entity is **managed**
- Inside a transaction

---

## 7. Write Operations Flow

---

### Example:

```
userRepository.save(user);
```

---

### Internally:

```
1. Entity added to Persistence Context
2. NOT immediately inserted
3. On transaction commit → SQL executed
```

---

## 8. Flush vs Commit

---

### 🔹 Flush

- Synchronizes Persistence Context → DB
- Executes SQL
- Does NOT commit

---

### 🔹 Commit

- Finalizes transaction
- Makes changes permanent

---

### 🔁 Flow

```
Changes → Flush → Commit → DB
```

---

### ✅ Example

```
entityManager.persist(user);
entityManager.flush();// Executes SQL
// Still not committed
```

---

## 9. Entity Lifecycle (Intro)

---

Hibernate manages entity states:

```
Transient → Persistent → Detached → Removed
```

---

👉 We will go deep in Part 9

---

## 10. First-Level Cache Behavior

---

### 🔹 Scope

- Per transaction
- Per EntityManager

---

### 🔁 Example

```
Useruser1=entityManager.find(User.class,1L);
Useruser2=entityManager.find(User.class,1L);
```

👉 Only ONE DB call

---

## 11. Important Behavior You MUST Know

---

### ⚠️ Same Object Reference

```
user1==user2// true
```

👉 Same object from persistence context

---

## 12. When DB is Actually Hit

---

Hibernate hits DB when:

---

### ✅ Case 1: Entity not in Persistence Context

```
find()
```

---

### ✅ Case 2: Transaction commit

```
save()
```

---

### ✅ Case 3: Explicit flush

```
entityManager.flush()
```

---

## 13. Real-World Example Flow

---

```
@Transactional
publicvoidupdateUser(Longid) {
Useruser=entityManager.find(User.class,id);

user.setName("Atharva Updated");

// No save() call
}
```

---

### 🔁 Internally:

```
1. Load user → Persistence Context
2. Modify object
3. Hibernate detects change
4. On commit → UPDATE query executed
```

---

## 14. Why Developers Get Bugs Here

---

### ❌ Expecting Immediate DB Update

```
user.setName("X");
```

👉 No immediate SQL

---

### ❌ Working Outside Transaction

```
user.setName("X");// No update happens
```

👉 Because:

- No transaction
- No commit

---

### ❌ Lazy Loading Issues (future topic)

---

## 15. Key Insight (VERY IMPORTANT)

---

👉 Hibernate works on:

```
State changes, not method calls
```

---

NOT:

```
save() → update
```

BUT:

```
Object change → Hibernate detects → update
```

---

# PART 5: Repository Layer (Spring Data JPA)

---

This is the layer you will use **every single day in real backend development**.

👉 You almost NEVER use `EntityManager` directly

👉 You use **Repository interfaces**

---

## 1. What is Repository Layer

---

### 🔹 Definition

Repository is the layer that **handles database operations**

```
Service → Repository → JPA → Hibernate → Database
```

---

### 🔍 Responsibility

- Save data
- Fetch data
- Delete data
- Query data

---

## 2. What is Spring Data JPA

---

### 🔹 Definition

Spring Data JPA is a **library that simplifies JPA usage**

👉 It removes boilerplate code

👉 Provides ready-made methods

👉 Generates queries automatically

---

### 🔁 Without Spring Data JPA

```
Useruser=entityManager.find(User.class,1L);
```

---

### 🔁 With Spring Data JPA

```
userRepository.findById(1L);
```

---

👉 Much cleaner and faster

---

## 3. JpaRepository (CORE INTERFACE)

---

### 🔹 Definition

`JpaRepository` is the **main interface you extend**

---

### ✅ Example

```
importorg.springframework.data.jpa.repository.JpaRepository;

publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {
}
```

---

### 🔍 What This Means

| Part | Meaning |
| --- | --- |
| User | Entity |
| Long | Primary key type |

---

👉 That’s it. No implementation required.

---

## 4. What You Get Automatically

---

When you extend `JpaRepository`, you get:

---

### 🔹 1. Save Data

```
userRepository.save(user);
```

---

### 🔹 2. Find by ID

```
Optional<User>user=userRepository.findById(1L);
```

---

### 🔹 3. Get All Records

```
List<User>users=userRepository.findAll();
```

---

### 🔹 4. Delete

```
userRepository.deleteById(1L);
```

---

### 🔹 5. Check Exists

```
booleanexists=userRepository.existsById(1L);
```

---

## 5. save() Method Deep Dive

---

### 🔹 Behavior

```
userRepository.save(user);
```

---

### 🔁 Internally:

```
IF id == null → INSERT
IF id != null → UPDATE
```

---

### ✅ Example

```
Useruser=newUser();
user.setName("Atharva");

userRepository.save(user);// INSERT
```

---

```
user.setName("Updated Name");

userRepository.save(user);// UPDATE
```

---

👉 Hibernate decides based on entity state

---

## 6. findById() Method

---

### ✅ Example

```
Optional<User>userOpt=userRepository.findById(1L);
```

---

### 🔍 Why Optional?

👉 Avoids `NullPointerException`

---

### Usage

```
Useruser=userOpt.orElseThrow(() ->newRuntimeException("User not found"));
```

---

## 7. delete() Methods

---

### 🔹 Delete by ID

```
userRepository.deleteById(1L);
```

---

### 🔹 Delete Entity

```
userRepository.delete(user);
```

---

### 🔁 Internally:

```
DELETE FROM users WHERE id = ?
```

---

## 8. How Spring Generates Queries Automatically

---

This is one of the most powerful features.

---

### 🔹 Example Method

```
List<User>findByName(String name);
```

---

👉 Spring automatically generates:

```
SELECT*FROM usersWHERE name= ?
```

---

### 🔍 How?

Spring parses method name:

```
find + By + Name
```

---

## 9. Naming Rules (IMPORTANT)

---

### 🔹 Basic Patterns

| Method | Meaning |
| --- | --- |
| findByName | WHERE name = ? |
| findByEmail | WHERE email = ? |
| findByAge | WHERE age = ? |

---

### 🔹 Multiple Conditions

```
List<User>findByNameAndEmail(String name,String email);
```

---

👉 Generates:

```
WHERE name= ?AND email= ?
```

---

### 🔹 OR Condition

```
List<User>findByNameOrEmail(String name,String email);
```

---

### 🔹 Comparison

```
List<User>findByAgeGreaterThan(int age);
```

---

### 🔹 Like (Search)

```
List<User>findByNameContaining(String name);
```

---

👉 SQL:

```
WHERE nameLIKE%?%
```

---

## 10. Real Repository Example

---

```
importorg.springframework.data.jpa.repository.JpaRepository;
importjava.util.List;

publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {

// Find by name
List<User>findByName(Stringname);

// Find by email
UserfindByEmail(Stringemail);

// Multiple condition
List<User>findByNameAndAge(Stringname,intage);

// Search
List<User>findByNameContaining(Stringkeyword);
}
```

---

## 11. Repository in Real Workflow

---

### 🔁 Full Flow

```
Controller → Service → Repository → DB
```

---

### ✅ Example

---

### Controller

```
@PostMapping("/users")
publicUsercreateUser(@RequestBodyUseruser) {
returnuserService.save(user);
}
```

---

### Service

```
@Service
publicclassUserService {

privatefinalUserRepositoryuserRepository;

publicUserService(UserRepositoryuserRepository) {
this.userRepository=userRepository;
    }

publicUsersave(Useruser) {
returnuserRepository.save(user);
    }
}
```

---

### Repository

```
publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {}
```

---

👉 Hibernate handles everything below

---

## 12. Why This Layer is Powerful

---

### ✅ No SQL Writing (Most Cases)

---

### ✅ Less Code

---

### ✅ Cleaner Architecture

---

### ✅ Easy to Scale

---

## 13. Common Mistakes

---

### ❌ Writing unnecessary custom queries

👉 Use method naming first

---

### ❌ Not using Optional properly

---

### ❌ Ignoring transaction behavior

---

## 14. Key Insight (VERY IMPORTANT)

---

👉 Repository does NOT execute SQL directly

```
Repository → JPA → Hibernate → SQL → DB
```

---

👉 It is just an abstraction layer

# PART 6: Custom Queries (VERY IMPORTANT)

---

In real-world projects, **default repository methods are NOT enough**.

👉 You will often need:

- Filtering
- Joins
- Complex conditions
- Optimized queries

---

## 1. When Do You Need Custom Queries

---

### ✅ Use Derived Methods When:

- Simple conditions
- Single/multiple fields

---

### ❌ Use Custom Queries When:

- Complex conditions
- Joins
- Aggregations
- Performance-specific queries

---

```
Simple → findByName() ✅
Complex → @Query ❗
```

---

## 2. Derived Query Methods (Advanced Usage)

---

You already saw basics — now let’s go deeper.

---

### 🔹 Multiple Conditions

```
List<User>findByNameAndAge(String name,int age);
```

---

### 🔹 Comparison Operators

```
List<User>findByAgeGreaterThan(int age);
List<User>findByAgeLessThan(intage);
List<User>findByAgeBetween(int min,int max);
```

---

### 🔹 Null Handling

```
List<User>findByEmailIsNull();
List<User>findByEmailIsNotNull();
```

---

### 🔹 Boolean Fields

```
List<User>findByActiveTrue();
List<User>findByActiveFalse();
```

---

### 🔹 Sorting

```
List<User>findByNameOrderByAgeDesc(String name);
```

---

### 🔹 Limiting Results

```
UserfindTopByOrderByAgeDesc();
```

---

### ⚠️ Limitation

👉 Derived queries become unreadable:

```
findByNameAndEmailAndAgeAndStatusAndRole(...)
```

👉 That’s where `@Query` comes in

---

## 3. @Query Annotation (JPQL)

---

### 🔹 What is JPQL?

**JPQL = Java Persistence Query Language**

👉 Works with:

- Entity names
- Field names

NOT:

- Table names
- Column names

---

### 🔁 Example Difference

| SQL | JPQL |
| --- | --- |
| SELECT * FROM users | SELECT u FROM User u |

---

## 4. Basic @Query Example

---

```
@Query("SELECT u FROM User u WHERE u.name = :name")
List<User>findUsersByName(Stringname);
```

---

### 🔍 Key Points

- `User` → Entity class
- `u.name` → field
- `:name` → parameter

---

## 5. Named Parameters (Best Practice)

---

### ✅ Example

```
@Query("SELECT u FROM User u WHERE u.email = :email")
UserfindByEmail(@Param("email")Stringemail);
```

---

👉 Clear and safe

---

## 6. Multiple Conditions with @Query

---

```
@Query("SELECT u FROM User u WHERE u.name = :name AND u.age > :age")
List<User>findUsers(Stringname,intage);
```

---

## 7. LIKE Query (Search)

---

```
@Query("SELECT u FROM User u WHERE u.name LIKE %:keyword%")
List<User>searchUsers(Stringkeyword);
```

---

## 8. Sorting with @Query

---

```
@Query("SELECT u FROM User u ORDER BY u.age DESC")
List<User>findAllSorted();
```

---

## 9. Native Queries (SQL)

---

### 🔹 When to Use

- Complex SQL
- DB-specific features
- Performance tuning

---

### ✅ Example

```
@Query(
value="SELECT * FROM users WHERE age > :age",
nativeQuery=true
)
List<User>findUsersAboveAge(intage);
```

---

### ⚠️ Important

👉 Uses:

- Table name (`users`)
- Column names

NOT:

- Entity names

---

## 10. JPQL vs Native Query

---

| Feature | JPQL | Native |
| --- | --- | --- |
| Uses Entity | ✅ | ❌ |
| Uses Table | ❌ | ✅ |
| Portable | ✅ | ❌ |
| Performance Control | Limited | High |

---

### ✅ Recommendation

👉 Use:

- JPQL → 90% cases
- Native → only when needed

---

## 11. Modifying Queries (UPDATE / DELETE)

---

### 🔹 Required Annotation

```
@Modifying
```

---

### ✅ Example

```
@Modifying
@Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
intupdateUserName(Longid,Stringname);
```

---

### ⚠️ Important

👉 Must be inside transaction

```
@Transactional
```

---

## 12. Real Repository Example

---

```
importorg.springframework.data.jpa.repository.*;
importorg.springframework.transaction.annotation.Transactional;
importjava.util.List;

publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {

// JPQL Query
    @Query("SELECT u FROM User u WHERE u.age > :age")
List<User>findUsersOlderThan(intage);

// Search
    @Query("SELECTuFROMUseru WHEREu.nameLIKE %:keyword%")
List<User>searchByName(Stringkeyword);

// Native Query
    @Query(value="SELECT * FROM users WHERE email = :email",nativeQuery=true)
UserfindByEmailNative(Stringemail);

// Update Query
    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.name = :name WHERE u.id = :id")
intupdateName(Longid,Stringname);
}
```

---

## 13. Internal Working

---

### Example:

```
userRepository.searchByName("ath");
```

---

### Internally:

```
1. Spring parses @Query
2. JPA processes query
3. Hibernate converts to SQL
4. JDBC executes
5. Result mapped to objects
```

---

## 14. Real-World Usage Patterns

---

### ✅ Filtering APIs

```
GET/users?age=25
```

---

```
findUsersOlderThan(25);
```

---

---

### ✅ Search APIs

```
GET/users/search?keyword=ath
```

---

```
searchByName("ath");
```

---

---

### ✅ Update Without Fetch

```
updateName(id,"New Name");
```

---

👉 Efficient (no entity loading)

---

## 15. Common Mistakes

---

### ❌ Mixing SQL in JPQL

```
SELECT*FROM users ❌
```

---

### ❌ Forgetting @Modifying

👉 Update fails silently

---

### ❌ Not using @Transactional

👉 Changes not committed

---

### ❌ Overusing Native Queries

👉 Breaks portability

---

## 16. Key Insight (VERY IMPORTANT)

---

👉 Query flow:

```
Repository → JPA → Hibernate → SQL → DB
```

---

👉 You are writing:

```
JPQL → Hibernate converts → SQL
```

# PART 7: Relationships (MOST IMPORTANT TOPIC)

---

This is **the most critical part of JPA + Hibernate**.

👉 Real-world databases are NOT single tables

👉 Everything is connected using relationships

If you master this:

- You can design real backend systems
- You can avoid major bugs (especially lazy loading issues)

---

## 1. Why Relationships are Needed

---

### 🔁 Real-World Example

```
User → Orders
Order → Products
User → Profile
```

---

### 🔍 Problem Without Relationships

You would:

- Manually manage foreign keys ❌
- Write complex joins ❌

👉 JPA solves this using annotations

---

## 2. Types of Relationships

---

| Relationship | Meaning |
| --- | --- |
| OneToOne | One user → One profile |
| OneToMany | One user → Many orders |
| ManyToOne | Many orders → One user |
| ManyToMany | Many students ↔ Many courses |

---

---

# 🔹 3. @OneToOne

---

### 🔍 Use Case

```
User → Profile
```

Each user has exactly one profile

---

### ✅ Example

```
@Entity
publicclassUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringname;

    @OneToOne
    @JoinColumn(name="profile_id")// FK column
privateProfileprofile;
}
```

---

```
@Entity
publicclassProfile {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringbio;
}
```

---

### 🔁 DB Structure

```
users
------
id | name | profile_id (FK)

profiles
---------
id | bio
```

---

### 🔍 Internal Working

- `profile_id` stored in `users`
- Hibernate manages join

---

---

# 🔹 4. @ManyToOne (MOST COMMON)

---

### 🔍 Use Case

```
Many Orders → One User
```

---

### ✅ Example

```
@Entity
publicclassOrder {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringitem;

    @ManyToOne
    @JoinColumn(name="user_id")// FK
privateUseruser;
}
```

---

```
@Entity
publicclassUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringname;
}
```

---

### 🔁 DB Structure

```
orders
--------
id | item | user_id (FK)
```

---

### 🔍 Real Insight

👉 Always:

- Child → @ManyToOne
- Parent → optional mapping

---

---

# 🔹 5. @OneToMany

---

### 🔍 Use Case

```
One User → Many Orders
```

---

### ✅ Example

```
@Entity
publicclassUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringname;

    @OneToMany(mappedBy="user")
privateList<Order>orders;
}
```

---

```
@Entity
publicclassOrder {

    @ManyToOne
    @JoinColumn(name="user_id")
privateUseruser;
}
```

---

### 🔍 Important

👉 `mappedBy = "user"` means:

- Order owns the relationship
- No extra column created

---

---

# 🔹 6. @ManyToMany (Basic)

---

### 🔍 Use Case

```
Students ↔ Courses
```

---

### ✅ Example

```
@Entity
publicclassStudent {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringname;

    @ManyToMany
    @JoinTable(
name="student_course",
joinColumns= @JoinColumn(name="student_id"),
inverseJoinColumns= @JoinColumn(name="course_id")
    )
privateList<Course>courses;
}
```

---

```
@Entity
publicclassCourse {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringtitle;
}
```

---

### 🔁 DB Structure

```
student_course
--------------
student_id | course_id
```

---

### ⚠️ Real-World Note

👉 Avoid direct ManyToMany

👉 Use **intermediate entity** instead (advanced topic later)

---

---

# 🔹 7. Fetch Types (VERY IMPORTANT)

---

## 🔍 What is Fetching?

👉 How related data is loaded

---

## 🔹 1. EAGER Fetch

---

### Behavior

```
Load parent + child immediately
```

---

### Example

```
@OneToOne(fetch=FetchType.EAGER)
```

---

### Problem

```
Unnecessary data loading → performance issues ❌
```

---

---

## 🔹 2. LAZY Fetch (DEFAULT BEST)

---

### Behavior

```
Load only when needed
```

---

### Example

```
@OneToMany(fetch=FetchType.LAZY)
```

---

### 🔁 Example Flow

```
Useruser=userRepository.findById(1L).get();
```

👉 Orders NOT loaded yet

---

```
user.getOrders();
```

👉 Now query executed

---

---

## ⚠️ Lazy Loading Problem

---

```
user.getOrders();
```

👉 If outside transaction:

```
LazyInitializationException ❌
```

---

### ✔ Fix

- Use within transaction
- Or fetch join (later topic)

---

---

# 🔹 8. Cascade Types

---

## 🔍 What is Cascade?

👉 Propagate operations from parent → child

---

## 🔹 Types You Use

---

### 1️⃣ PERSIST

```
cascade=CascadeType.PERSIST
```

👉 Save parent → child saved automatically

---

---

### 2️⃣ REMOVE

```
cascade=CascadeType.REMOVE
```

👉 Delete parent → child deleted

---

---

### 3️⃣ ALL (Common)

```
cascade=CascadeType.ALL
```

👉 Includes:

- Persist
- Remove
- Merge

---

---

### ✅ Example

```
@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
privateList<Order>orders;
```

---

---

## 🔁 Real Example

```
Useruser=newUser();
Orderorder=newOrder();

order.setUser(user);
user.setOrders(List.of(order));

userRepository.save(user);
```

👉 Order also saved automatically

---

---

# 🔹 9. Owning vs Inverse Side (IMPORTANT)

---

### 🔍 Owning Side

- Has `@JoinColumn`
- Controls relationship

---

### 🔍 Inverse Side

- Uses `mappedBy`
- No DB column

---

### Example

```
@ManyToOne// Owning side
@JoinColumn(name="user_id")
privateUseruser;
```

---

```
@OneToMany(mappedBy="user")// Inverse side
privateList<Order>orders;
```

---

---

## 10. Real-World Design Pattern

---

### ✅ Best Practice

```
Child → @ManyToOne (always)
Parent → @OneToMany (optional)
```

---

### Why?

- Cleaner DB design
- Avoids extra joins
- Better performance

---

---

## 11. Common Mistakes (VERY IMPORTANT)

---

### ❌ Using EAGER everywhere

👉 Performance disaster

---

### ❌ Forgetting mappedBy

👉 Extra columns created

---

### ❌ Not setting both sides

```
order.setUser(user);// but not user.setOrders()
```

👉 Causes inconsistency

---

### ❌ ManyToMany overuse

👉 Hard to manage

---

---

## 12. Key Insight (CRITICAL)

---

👉 Relationships are NOT just annotations

They control:

```
- SQL joins
- Data fetching
- Performance
- DB structure
```

---

---

## 🚀 What You Must Master From This Part

---

- @ManyToOne (most used)
- @OneToMany with mappedBy
- FetchType.LAZY (default choice)
- Cascade basics
- Owning vs inverse side

# PART 8: Transactions (CRITICAL FOR DATA CONSISTENCY)

---

Transactions are **the backbone of any real backend system**.

👉 Without transactions:

- Data becomes inconsistent ❌
- Partial updates happen ❌
- Bugs become impossible to debug ❌

---

## 1. What is a Transaction

---

### 🔹 Definition

A **transaction is a group of operations executed as a single unit**

---

### 🔁 Example

```
Transfer Money:
1. Deduct from Account A
2. Add to Account B
```

---

👉 If step 2 fails:

```
Step 1 must be undone ❗
```

---

## 2. ACID Properties (Only Practical Understanding)

---

### 🔹 Atomicity

```
All or nothing
```

---

### 🔹 Consistency

```
Database remains valid
```

---

### 🔹 Isolation

```
Transactions don't interfere
```

---

### 🔹 Durability

```
Changes are permanent after commit
```

---

👉 You don’t memorize — you **experience these in bugs**

---

## 3. @Transactional Annotation (MOST IMPORTANT)

---

### 🔹 Purpose

Defines a **transaction boundary**

---

### ✅ Example

```
importorg.springframework.transaction.annotation.Transactional;

@Transactional
publicvoidcreateUser(Useruser) {
userRepository.save(user);
}
```

---

### 🔍 What Happens Internally

```
1. Transaction starts
2. Method executes
3. If success → COMMIT
4. If exception → ROLLBACK
```

---

---

## 4. Real Example (VERY IMPORTANT)

---

```
@Transactional
publicvoidtransferMoney(LongfromId,LongtoId,doubleamount) {

Accountfrom=accountRepository.findById(fromId).get();
Accountto=accountRepository.findById(toId).get();

from.setBalance(from.getBalance()-amount);

if (true)thrownewRuntimeException("Failure");

to.setBalance(to.getBalance()+amount);
}
```

---

### 🔁 Without Transaction

```
Money deducted ❌
Money not added ❌
```

---

### 🔁 With Transaction

```
Rollback → No change ✅
```

---

---

## 5. Where to Use @Transactional

---

### ✅ Best Practice

👉 Use at **Service Layer**

---

```
Controller ❌
Service ✅
Repository ❌
```

---

### ✅ Example

```
@Service
publicclassUserService {

    @Transactional
publicUsersave(Useruser) {
returnuserRepository.save(user);
    }
}
```

---

---

## 6. Transaction + Hibernate Behavior

---

### 🔁 Important Flow

```
Transaction Start
   ↓
Persistence Context Active
   ↓
Changes tracked
   ↓
Flush
   ↓
Commit
```

---

👉 No transaction = No persistence context lifecycle

---

---

## 7. Commit vs Rollback

---

### 🔹 Commit

```
Changes permanently saved
```

---

### 🔹 Rollback

```
Undo all changes
```

---

---

## 8. What Triggers Rollback

---

### ✅ By Default

Only:

```
RuntimeException
```

---

### ❌ Not Triggered By:

```
CheckedException
```

---

---

### ✔ Custom Rollback

```
@Transactional(rollbackFor=Exception.class)
```

---

---

## 9. Read-Only Transactions

---

### 🔹 Purpose

Optimize read operations

---

### ✅ Example

```
@Transactional(readOnly=true)
publicList<User>getAllUsers() {
returnuserRepository.findAll();
}
```

---

👉 Hibernate:

- Skips dirty checking
- Improves performance

---

---

## 10. Transaction Propagation (Basic)

---

👉 How transactions behave when calling another method

---

### 🔹 REQUIRED (Default)

```
Join existing OR create new
```

---

---

### 🔹 REQUIRES_NEW

```
Always create new transaction
```

---

👉 Used in:

- Logging
- Audit systems

---

---

## 11. Common Real-World Bugs

---

### ❌ No @Transactional

```
user.setName("X");
```

👉 No DB update

---

---

### ❌ Lazy Loading Error

```
user.getOrders();
```

👉 Outside transaction → Exception

---

---

### ❌ Partial Updates

👉 Without transaction:

- Half data saved
- Half lost

---

---

## 12. Internal Working (VERY IMPORTANT)

---

### Example:

```
@Transactional
publicvoidupdateUser(Longid) {
Useruser=userRepository.findById(id).get();
user.setName("Updated");
}
```

---

### 🔁 Internally:

```
1. Transaction starts
2. Entity loaded → Persistence Context
3. Object modified
4. Hibernate detects change
5. On commit → UPDATE query executed
```

---

👉 No explicit save()

---

---

## 13. Key Insight (CRITICAL)

---

👉 Hibernate needs transaction for:

```
- Dirty checking
- Lazy loading
- Persistence context lifecycle
```

---

👉 Without transaction:

```
Hibernate behaves unpredictably ❌
```

---

# PART 9: Entity Lifecycle (CRITICAL INTERNAL CONCEPT)

---

This is where Hibernate becomes **truly understandable**.

👉 If you master this:

- You’ll know **why updates happen automatically**
- You’ll debug issues like a pro
- You’ll avoid hidden bugs

---

## 1. What is Entity Lifecycle

---

### 🔹 Definition

Entity lifecycle defines **different states of an entity object** during its interaction with Hibernate.

---

### 🔁 States

```
Transient → Persistent → Detached → Removed
```

---

👉 Hibernate behavior depends entirely on **which state your entity is in**

---

## 2. Overview of All States

---

| State | Meaning |
| --- | --- |
| Transient | New object (not tracked) |
| Persistent | Managed by Hibernate |
| Detached | Was managed, now not |
| Removed | Marked for deletion |

---

---

# 🔹 3. Transient State

---

### 🔍 Definition

Object is:

- Created using `new`
- NOT associated with persistence context
- NOT stored in DB

---

### ✅ Example

```
Useruser=newUser();
user.setName("Atharva");
```

---

### 🔍 Behavior

```
- No DB interaction
- Hibernate unaware of this object
```

---

---

# 🔹 4. Persistent State (MOST IMPORTANT)

---

### 🔍 Definition

Object is:

- Managed by Hibernate
- Inside persistence context

---

### ✅ Example

```
Useruser=entityManager.find(User.class,1L);
```

---

### 🔁 What Happens

```
1. Loaded from DB
2. Stored in persistence context
3. Hibernate tracks changes
```

---

---

### 🔥 Dirty Checking (Revisit)

```
user.setName("Updated Name");
```

---

👉 Hibernate detects change → updates DB automatically

---

### ⚠️ Key Rule

```
Persistent entity = Automatic DB sync
```

---

---

# 🔹 5. Detached State

---

### 🔍 Definition

Object was:

- Persistent earlier
- Now NOT managed by Hibernate

---

### ✅ Example

```
Useruser=entityManager.find(User.class,1L);

entityManager.detach(user);

user.setName("New Name");// No update
```

---

### 🔍 Behavior

```
- No tracking
- No automatic update
```

---

---

### 🔁 When Does Detachment Happen?

---

### 1️⃣ Transaction ends

```
Persistence context destroyed
```

---

### 2️⃣ Explicit detach

```
entityManager.detach(user);
```

---

### 3️⃣ Serialization (common in APIs)

---

---

# 🔹 6. Removed State

---

### 🔍 Definition

Entity marked for deletion

---

### ✅ Example

```
Useruser=entityManager.find(User.class,1L);
entityManager.remove(user);
```

---

### 🔁 Internally

```
1. Marked as removed
2. On commit → DELETE query executed
```

---

---

## 7. Lifecycle Flow (IMPORTANT)

---

```
NEW (Transient)
   ↓ persist()
PERSISTENT
   ↓ detach()
DETACHED
   ↓ remove()
REMOVED
```

---

---

## 8. State Transitions (Real Code)

---

### 🔹 Transient → Persistent

```
Useruser=newUser();
entityManager.persist(user);
```

---

---

### 🔹 Persistent → Detached

```
entityManager.detach(user);
```

---

---

### 🔹 Detached → Persistent (Merge)

```
UsermergedUser=entityManager.merge(user);
```

---

👉 Important:

- Returns new managed object
- Old object remains detached

---

---

### 🔹 Persistent → Removed

```
entityManager.remove(user);
```

---

---

## 9. Real-World Example (VERY IMPORTANT)

---

```
@Transactional
publicvoidupdateUser(Longid) {

Useruser=userRepository.findById(id).get();// Persistent

user.setName("Updated");// Auto update

}
```

---

### 🔁 Flow

```
1. Fetch → Persistent
2. Modify → Dirty checking
3. Commit → UPDATE query
```

---

---

## 10. Detached Entity Problem (VERY COMMON BUG)

---

### ❌ Example

```
Useruser=userRepository.findById(1L).get();

// Transaction ends here

user.setName("New Name");// No update
```

---

👉 Because:

```
Entity is DETACHED ❌
```

---

---

### ✔ Fix

```
@Transactional
publicvoidupdate() {
Useruser=userRepository.findById(1L).get();
user.setName("Updated");
}
```

---

---

## 11. merge() vs persist()

---

| Method | Use |
| --- | --- |
| persist() | New entity |
| merge() | Detached entity |

---

---

### ✅ Example

```
Useruser=newUser();
entityManager.persist(user);// Insert
```

---

```
Useruser=newUser();
user.setId(1L);

entityManager.merge(user);// Update
```

---

---

## 12. Internal Working (Deep Insight)

---

### When Entity is Persistent:

Hibernate stores:

```
Original State → Snapshot
```

---

### When Modified:

```
Compare snapshot vs current state
```

---

### If different:

```
Generate UPDATE query
```

---

---

## 13. Common Mistakes (VERY IMPORTANT)

---

### ❌ Updating Detached Entity

---

### ❌ Expecting save() always required

👉 Not needed for persistent objects

---

### ❌ Misunderstanding merge()

👉 Returns NEW object

---

### ❌ Modifying outside transaction

👉 No update

---

---

## 14. Key Insight (CRITICAL)

---

👉 Hibernate works on:

```
Entity State + Persistence Context
```

---

NOT:

```
Explicit SQL calls
```

---

# PART 10: Pagination and Sorting (VERY PRACTICAL)

---

In real-world backend systems, you **never fetch all data at once**.

👉 Why?

- Performance issues ❌
- Large datasets ❌
- Slow APIs ❌

---

## 1. What is Pagination

---

### 🔹 Definition

Pagination means **fetching data in small chunks (pages)**

---

### 🔁 Example

```
/users?page=0&size=10
```

---

👉 Instead of:

```
Fetch 10,000 users ❌
```

👉 You fetch:

```
Fetch 10 users at a time ✅
```

---

## 2. Spring Data JPA Support

---

Spring provides built-in support using:

- `Pageable`
- `Page`

---

## 3. Pageable Interface

---

### 🔹 Purpose

Defines:

- Page number
- Page size
- Sorting

---

### ✅ Example

```
importorg.springframework.data.domain.Pageable;
```

---

---

## 4. Page Interface

---

### 🔹 Purpose

Represents paginated result

---

### 🔍 Contains:

- Data list
- Total pages
- Total elements
- Current page

---

---

## 5. Basic Pagination Example

---

### 🔹 Repository

```
importorg.springframework.data.domain.Page;
importorg.springframework.data.domain.Pageable;

publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {

Page<User>findAll(Pageablepageable);
}
```

---

---

### 🔹 Service

```
importorg.springframework.data.domain.*;

publicPage<User>getUsers(intpage,intsize) {

Pageablepageable=PageRequest.of(page,size);

returnuserRepository.findAll(pageable);
}
```

---

---

### 🔹 Controller

```
@GetMapping("/users")
publicPage<User>getUsers(
        @RequestParamintpage,
        @RequestParamintsize) {

returnuserService.getUsers(page,size);
}
```

---

---

## 6. Example API Call

---

```
GET /users?page=0&size=5
```

---

### 🔁 Response Structure

```
{
  "content": [...users...],
  "totalPages":10,
  "totalElements":50,
  "size":5,
  "number":0
}
```

---

---

## 7. Sorting Data

---

### 🔹 Using Sort

```
importorg.springframework.data.domain.Sort;
```

---

### ✅ Example

```
Pageablepageable=PageRequest.of(
page,
size,
Sort.by("name").ascending()
);
```

---

---

### 🔹 Descending Order

```
Sort.by("age").descending();
```

---

---

## 8. Pagination + Sorting Together

---

### ✅ Example

```
Pageablepageable=PageRequest.of(
0,
10,
Sort.by("age").descending()
);

Page<User>users=userRepository.findAll(pageable);
```

---

---

## 9. Pagination with Custom Query

---

### ✅ Example

```
@Query("SELECT u FROM User u WHERE u.age > :age")
Page<User>findUsersAboveAge(intage,Pageablepageable);
```

---

👉 Works seamlessly

---

---

## 10. Important Methods of Page

---

### 🔹 Get Content

```
page.getContent();
```

---

### 🔹 Total Pages

```
page.getTotalPages();
```

---

### 🔹 Total Elements

```
page.getTotalElements();
```

---

### 🔹 Current Page

```
page.getNumber();
```

---

---

## 11. Slice (Advanced but Useful)

---

### 🔹 Difference

| Page | Slice |
| --- | --- |
| Counts total records | No count |
| Slower | Faster |

---

### ✅ Example

```
Slice<User>findAll(Pageable pageable);
```

---

👉 Used for:

- Infinite scrolling
- Performance optimization

---

---

## 12. Real-World API Design

---

### 🔹 Best Practice

```
GET /users?page=0&size=10&sort=age,desc
```

---

---

### 🔹 Controller Example

```
@GetMapping("/users")
publicPage<User>getUsers(Pageablepageable) {
returnuserRepository.findAll(pageable);
}
```

---

👉 Spring automatically maps query params → Pageable

---

---

## 13. Internal Working

---

### Example:

```
PageRequest.of(0,5);
```

---

### 🔁 Hibernate generates:

```
SELECT*FROM users
LIMIT5 OFFSET0;
```

---

---

## 14. Common Mistakes

---

### ❌ Fetching all data

```
userRepository.findAll();// Dangerous ❌
```

---

---

### ❌ Large page size

```
size = 10000 ❌
```

---

---

### ❌ Ignoring sorting

👉 Leads to inconsistent results

---

---

## 15. Key Insight (VERY IMPORTANT)

---

👉 Pagination is NOT optional

```
Every production API uses pagination ✅
```

---

👉 Hibernate translates:

```
Pageable → SQL LIMIT/OFFSET
```

---

---

## 🚀 What You Must Master From This Part

---

- Pageable and Page usage
- Sorting integration
- API-level pagination
- Performance impact

# PART 11: Basic Performance Considerations (VERY IMPORTANT)

---

This part is where most real-world backend issues come from.

👉 Your code may work perfectly

👉 But performance can be **terrible if you ignore this**

---

## 1. Why Performance Matters in JPA

---

### 🔁 Real Scenario

```
Load 100 users
Each user has 10 orders
```

---

👉 If done wrong:

```
1 + 100 queries ❌ (VERY BAD)
```

👉 If done right:

```
1 query ✅
```

---

---

# 🔹 2. Lazy Loading (Deep Understanding)

---

## 🔍 What is Lazy Loading

👉 Data is loaded **only when needed**

---

### ✅ Example

```
@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
privateList<Order>orders;
```

---

---

### 🔁 Flow

```
Useruser=userRepository.findById(1L).get();
```

👉 Orders NOT loaded

---

```
user.getOrders();
```

👉 Now query executed

---

---

## 🔹 Why Lazy Loading is Important

---

### ❌ Without Lazy

```
Always load everything ❌
Huge memory usage ❌
Slow queries ❌
```

---

### ✅ With Lazy

```
Load only required data ✅
Better performance ✅
```

---

---

## ⚠️ Lazy Initialization Exception

---

### ❌ Problem

```
Useruser=userRepository.findById(1L).get();

returnuser.getOrders();// ❌ Exception
```

---

### 🔍 Why?

```
Transaction closed → Persistence Context gone
```

---

---

### ✔ Fix

---

### ✅ Option 1: Use inside transaction

```
@Transactional
publicList<Order>getOrders(Longid) {
returnuserRepository.findById(id).get().getOrders();
}
```

---

### ✅ Option 2: Fetch Join (better)

---

---

# 🔹 3. N+1 Problem (MOST IMPORTANT)

---

## 🔍 What is N+1 Problem

---

### Scenario:

```
List<User>users=userRepository.findAll();
```

---

Then:

```
for (Useruser :users) {
user.getOrders();
}
```

---

---

### 🔁 What Happens

```
1 query → fetch users
+ N queries → fetch orders for each user
```

---

👉 Total:

```
1 + N queries ❌
```

---

---

## ⚠️ Why This is Dangerous

---

- Massive DB load
- Slow APIs
- Production failures

---

---

## 🔹 4. Solution: Fetch Join

---

### 🔍 Idea

Fetch related data in ONE query

---

### ✅ Example (JPQL)

```
@Query("SELECT u FROM User u JOIN FETCH u.orders")
List<User>findAllUsersWithOrders();
```

---

---

### 🔁 Generated SQL

```
SELECT u.*, o.*
FROM users u
JOIN orders oON u.id= o.user_id;
```

---

👉 Single query ✅

---

---

## 🔹 5. When to Use Fetch Join

---

### ✅ Use When:

- You KNOW you need related data
- Avoid N+1 problem

---

### ❌ Avoid When:

- Large datasets
- Multiple relationships

---

---

## 🔹 6. EAGER vs LAZY (Final Decision)

---

| Type | Use |
| --- | --- |
| EAGER | Almost never ❌ |
| LAZY | Default choice ✅ |

---

👉 Real rule:

```
Always use LAZY, control fetching manually
```

---

---

## 🔹 7. Selecting Only Required Data

---

### ❌ Bad Practice

```
List<User>users=userRepository.findAll();
```

---

👉 Loads ALL columns

---

---

### ✅ Better Approach (Projection idea)

```
@Query("SELECT u.name FROM User u")
List<String>getNames();
```

---

👉 Fetch only required data

---

---

## 🔹 8. Batch Operations (Basic Awareness)

---

### ❌ Bad

```
for (Useruser :users) {
userRepository.save(user);
}
```

---

👉 Multiple queries

---

---

### ✅ Better

```
userRepository.saveAll(users);
```

---

---

## 🔹 9. Transaction Scope Impact

---

### 🔍 Important

```
Short transactions → better performance
```

---

### ❌ Bad

```
Long-running transaction ❌
Locks DB ❌
```

---

---

## 🔹 10. Real-World Performance Pattern

---

### ✅ Pattern

```
- Default → LAZY
- Use Fetch Join when needed
- Use Pagination ALWAYS
- Avoid loading unnecessary data
```

---

---

## 11. Common Mistakes (VERY IMPORTANT)

---

### ❌ Using EAGER everywhere

---

### ❌ Ignoring N+1 problem

---

### ❌ Returning entities directly in APIs

---

### ❌ No pagination

---

---

## 12. Internal Working Insight

---

👉 Hibernate does:

```
Lazy loading → Proxy objects
```

---

👉 When accessed:

```
Proxy triggers SQL
```

---

---

## 13. Key Insight (CRITICAL)

---

👉 Performance depends on:

```
- Fetch strategy
- Query design
- Data loading pattern
```

---

👉 NOT just code correctness

---

---

## 🚀 What You Must Master From This Part

---

- Lazy loading behavior
- N+1 problem and fix
- Fetch join usage
- Avoid EAGER loading
- Efficient data fetching

# PART 12: Integration Flow with Spring Boot (JPA + Hibernate Setup)

---

Now we connect everything you’ve learned to a **real Spring Boot application**.

👉 This is where:

- JPA + Hibernate actually start working
- Database connection is configured
- Entities are scanned
- Repositories are activated

---

## 1. How JPA Fits into Spring Boot

---

### 🔁 Full Integration Flow

```
Spring Boot
   ↓
Spring Data JPA
   ↓
JPA (Specification)
   ↓
Hibernate (Implementation)
   ↓
JDBC
   ↓
Database
```

---

### 🔍 What Spring Boot Does Automatically

When you add dependency:

```
spring-boot-starter-data-jpa
```

👉 Spring Boot:

- Configures Hibernate
- Creates EntityManager
- Scans @Entity classes
- Creates Repository implementations
- Manages transactions

---

---

## 2. Required Dependencies

---

### ✅ Maven Dependency

```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<!-- Database Driver -->
<dependency>
<groupId>com.mysql</groupId>
<artifactId>mysql-connector-j</artifactId>
</dependency>
```

---

---

## 3. application.properties Configuration (VERY IMPORTANT)

---

This is where you connect your app to DB.

---

### ✅ Basic MySQL Configuration

```
# Database URL
spring.datasource.url=jdbc:mysql://localhost:3306/mydb

# DB credentials
spring.datasource.username=root
spring.datasource.password=your_password

# Hibernate dialect (MySQL)
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

# Auto table creation
spring.jpa.hibernate.ddl-auto=update

# Show SQL queries
spring.jpa.show-sql=true

# Format SQL
spring.jpa.properties.hibernate.format_sql=true
```

---

---

## 4. Understanding Each Property

---

### 🔹 DB Connection

```
spring.datasource.url
spring.datasource.username
spring.datasource.password
```

👉 Used by JDBC to connect DB

---

---

### 🔹 ddl-auto (VERY IMPORTANT)

---

```
spring.jpa.hibernate.ddl-auto=update
```

---

### Options:

| Value | Meaning |
| --- | --- |
| create | Drops + creates tables |
| update | Updates schema |
| validate | Checks schema |
| none | No changes |

---

### ✅ Real-World Use

```
Development → update
Production → validate / none
```

---

---

### 🔹 show-sql

```
spring.jpa.show-sql=true
```

👉 Prints SQL in console

---

---

## 5. How Entities are Detected

---

Spring Boot automatically scans:

```
Main class package and sub-packages
```

---

### ✅ Example

```
@SpringBootApplication
publicclassApplication {
publicstaticvoidmain(String[]args) {
SpringApplication.run(Application.class,args);
    }
}
```

---

👉 If entities are inside sub-packages → auto-detected

---

---

## 6. Repository Detection

---

Spring Boot scans:

```
Interfaces extending JpaRepository
```

---

### 🔁 Internally

```
1. Detect interface
2. Generate implementation at runtime
3. Register as Spring Bean
```

---

---

## 7. EntityManager Bean Creation

---

Spring Boot automatically creates:

```
EntityManagerFactory
EntityManager
Transaction Manager
```

---

👉 You don’t configure manually

---

---

## 8. Full Flow When Application Starts

---

```
1. Read application.properties
2. Connect to DB
3. Initialize Hibernate
4. Scan @Entity classes
5. Create tables (ddl-auto)
6. Scan repositories
7. Start application
```

---

---

## 9. Real Example (Complete Setup)

---

### 📁 Project Structure

```
com.project
 ├── controller
 ├── service
 ├── repository
 ├── entity
 └── Application.java
```

---

---

### 🔹 Entity

```
@Entity
@Table(name="users")
publicclassUser {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
privateLongid;

privateStringname;
}
```

---

---

### 🔹 Repository

```
publicinterfaceUserRepositoryextendsJpaRepository<User,Long> {
}
```

---

---

### 🔹 Service

```
@Service
publicclassUserService {

privatefinalUserRepositoryuserRepository;

publicUserService(UserRepositoryuserRepository) {
this.userRepository=userRepository;
    }

publicUsersave(Useruser) {
returnuserRepository.save(user);
    }
}
```

---

---

### 🔹 Controller

```
@RestController
@RequestMapping("/users")
publicclassUserController {

privatefinalUserServiceuserService;

publicUserController(UserServiceuserService) {
this.userService=userService;
    }

    @PostMapping
publicUsercreate(@RequestBodyUseruser) {
returnuserService.save(user);
    }
}
```

---

---

## 10. What Happens on API Call

---

### Request:

```
POST /users
```

---

### 🔁 Flow

```
Controller → Service → Repository → Hibernate → DB
```

---

### Internally:

```
1. JSON → User object
2. save() called
3. Hibernate generates INSERT
4. JDBC executes
5. Response returned
```

---

---

## 11. Common Configuration Mistakes

---

### ❌ Wrong DB URL

👉 App fails to start

---

### ❌ Missing driver dependency

👉 Connection error

---

### ❌ Wrong ddl-auto in production

👉 Data loss ❌

---

### ❌ Entities outside package

👉 Not detected

---

---

## 12. Key Insight (VERY IMPORTANT)

---

👉 Spring Boot removes:

```
- Manual Hibernate setup ❌
- XML configuration ❌
```

---

👉 You only write:

```
Entity + Repository + Config
```

---

👉 Everything else is auto-configured

---

---

## 🚀 What You Must Master From This Part

---

- application.properties configuration
- How Spring Boot integrates JPA
- Auto configuration concept
- Entity + Repository scanning
- Full request flow

---

# PART 13: Exception Scenarios (REAL-WORLD DEBUGGING)

---

This part is **extremely practical**.

👉 In real projects:

- You won’t struggle with writing code
- You WILL struggle with **debugging issues**

---

## 1. Why Exceptions Happen in JPA/Hibernate

---

👉 Because of:

```
- Persistence context behavior
- Lazy loading
- Transactions
- Entity state
```

---

👉 Most errors are NOT syntax errors

👉 They are **lifecycle + transaction mistakes**

---

---

# 🔹 2. LazyInitializationException (MOST COMMON)

---

## 🔍 Problem

```
Useruser=userRepository.findById(1L).get();

returnuser.getOrders();// ❌ Exception
```

---

## 💥 Exception

```
LazyInitializationException:
could not initialize proxy - no Session
```

---

## 🔍 Why It Happens

```
1. Orders are LAZY
2. Transaction already closed
3. Persistence Context destroyed
4. Hibernate cannot fetch data
```

---

---

## ✔ Fix Solutions

---

### ✅ Fix 1: Use @Transactional

```
@Transactional
publicList<Order>getOrders(Longid) {
returnuserRepository.findById(id).get().getOrders();
}
```

---

---

### ✅ Fix 2: Fetch Join (BEST)

```
@Query("SELECT u FROM User u JOIN FETCH u.orders WHERE u.id = :id")
UserfindUserWithOrders(Longid);
```

---

---

### ❌ Bad Practice

```
FetchType.EAGER
```

👉 Leads to performance issues

---

---

# 🔹 3. EntityNotFoundException

---

## 🔍 Problem

```
Useruser=entityManager.getReference(User.class,1L);
```

---

👉 Accessing:

```
user.getName();// ❌ Exception if not exists
```

---

## 🔍 Why

```
getReference() returns proxy
Actual DB hit happens later
If not found → exception
```

---

---

## ✔ Fix

---

### ✅ Use findById()

```
userRepository.findById(id)
```

---

---

# 🔹 4. NoSuchElementException

---

## 🔍 Problem

```
Useruser=userRepository.findById(1L).get();
```

---

👉 If not found:

```
NoSuchElementException ❌
```

---

---

## ✔ Fix

---

### ✅ Safe Handling

```
Useruser=userRepository.findById(1L)
.orElseThrow(() ->newRuntimeException("User not found"));
```

---

---

# 🔹 5. TransactionRequiredException

---

## 🔍 Problem

```
entityManager.persist(user);// ❌
```

---

## 💥 Error

```
No EntityManager with actual transaction available
```

---

---

## 🔍 Why

```
Write operations require transaction
```

---

---

## ✔ Fix

```
@Transactional
publicvoidsave(Useruser) {
entityManager.persist(user);
}
```

---

---

# 🔹 6. Detached Entity Exception

---

## 🔍 Problem

```
Useruser=userRepository.findById(1L).get();

// Transaction ends

entityManager.persist(user);// ❌
```

---

## 💥 Error

```
detached entity passed to persist
```

---

---

## 🔍 Why

```
persist() is for NEW entities only
```

---

---

## ✔ Fix

```
entityManager.merge(user);
```

---

---

# 🔹 7. DataIntegrityViolationException

---

## 🔍 Problem

```
@Column(unique=true)
privateStringemail;
```

---

```
userRepository.save(user);// duplicate email
```

---

## 💥 Error

```
Duplicate entry / constraint violation
```

---

---

## ✔ Fix

- Validate before saving
- Handle exception

---

---

# 🔹 8. N+1 Problem (Performance Issue)

---

## 🔍 Problem

```
List<User>users=userRepository.findAll();

for (Useruser :users) {
user.getOrders();
}
```

---

👉 Leads to:

```
1 + N queries ❌
```

---

---

## ✔ Fix

```
@Query("SELECT u FROM User u JOIN FETCH u.orders")
List<User>findAllUsersWithOrders();
```

---

---

# 🔹 9. Unexpected Update / No Update

---

## 🔍 Problem

```
user.setName("New Name");
```

👉 No update in DB

---

---

## 🔍 Why

```
- Entity is detached
- No transaction
```

---

---

## ✔ Fix

```
@Transactional
```

---

---

# 🔹 10. StackOverflowError (Infinite Loop)

---

## 🔍 Problem

Bidirectional relationship:

```
User→Orders
Order→User
```

---

👉 While returning JSON:

```
Infinite recursion ❌
```

---

---

## ✔ Fix

```
@JsonIgnore
```

OR

```
@JsonManagedReference
@JsonBackReference
```

---

---

# 🔹 11. Debugging Strategy (VERY IMPORTANT)

---

### ✅ Step-by-Step

---

### 1️⃣ Check Transaction

```
Is @Transactional present?
```

---

---

### 2️⃣ Check Entity State

```
Persistent or Detached?
```

---

---

### 3️⃣ Check Fetch Type

```
LAZY causing issue?
```

---

---

### 4️⃣ Check SQL Logs

```
spring.jpa.show-sql=true
```

---

---

### 5️⃣ Understand Flow

```
Controller → Service → Repository → Hibernate
```

---

---

## 12. Real-World Debug Example

---

### Problem:

```
user.getOrders();// Exception
```

---

### Debug:

```
1. LAZY relation
2. Outside transaction
3. Persistence context closed
```

---

### Solution:

```
@Transactional
```

---

---

## 13. Key Insight (CRITICAL)

---

👉 Most JPA errors are due to:

```
- Missing transaction
- Lazy loading misuse
- Wrong entity state
```

---

👉 NOT due to syntax

---

# FINAL PART: Complete JPA + Hibernate Mastery

---

Now we connect everything into a **real backend mental model**.

👉 This is what companies expect you to understand.

---

# 🔹 1. Complete JPA + Hibernate Workflow

---

## 🔁 End-to-End Flow

```
Client Request
   ↓
Controller
   ↓
Service (@Transactional starts)
   ↓
Repository (Spring Data JPA)
   ↓
JPA (EntityManager)
   ↓
Hibernate (ORM Engine)
   ↓
JDBC
   ↓
Database
```

---

## 🔍 Full Execution Example

---

### Step 1: API Call

```
POST /users
```

---

### Step 2: Controller

```
@PostMapping
publicUsercreate(@RequestBodyUseruser) {
returnuserService.save(user);
}
```

---

### Step 3: Service

```
@Transactional
publicUsersave(Useruser) {
returnuserRepository.save(user);
}
```

---

### Step 4: Repository

```
userRepository.save(user);
```

---

### 🔁 Internal Flow

```
1. Transaction starts
2. Entity enters Persistence Context
3. Hibernate analyzes entity
4. SQL generated (INSERT)
5. JDBC executes query
6. Commit → data stored
```

---

---

# 🔹 2. Core Concepts Connected (BIG PICTURE)

---

## 🧠 Everything revolves around THIS:

```
Persistence Context + Entity State + Transaction
```

---

### 🔁 Combined Flow

```
Entity (Java Object)
   ↓
Persistent State
   ↓
Tracked by Hibernate
   ↓
Dirty Checking
   ↓
Flush
   ↓
Commit
   ↓
Database Updated
```

---

---

# 🔹 3. Best Practices (REAL INDUSTRY RULES)

---

## ✅ 1. Entity Design

---

- Always include:

```
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
```

---

- Use:

```
privateIntegerage;// instead of int
```

---

- Avoid:

```
Business logic inside entity ❌
```

---

---

## ✅ 2. Relationships

---

- Always prefer:

```
@ManyToOne (child side)
```

---

- Use:

```
@OneToMany only when needed
```

---

- Avoid:

```
ManyToMany ❌ (use join entity)
```

---

---

## ✅ 3. Fetch Strategy

---

```
Default → LAZY ✅
```

---

```
Use FETCH JOIN when needed
```

---

---

## ✅ 4. Transactions

---

- Always at:

```
Service Layer ✅
```

---

- Never at:

```
Controller ❌
Repository ❌
```

---

---

## ✅ 5. Repository Usage

---

- Use:

```
Derived methods first
```

---

- Then:

```
@Query (JPQL)
```

---

- Native only if needed

---

---

## ✅ 6. Pagination

---

```
Always use Pageable in APIs
```

---

---

## ✅ 7. Performance

---

```
Avoid:
- N+1 problem
- EAGER loading
- Fetching unnecessary data
```

---

---

# 🔹 4. Common Mistakes to Avoid

---

## ❌ Missing @Transactional

👉 No updates happen

---

## ❌ Using EAGER everywhere

👉 Performance crash

---

## ❌ Ignoring Lazy Loading

👉 Runtime exceptions

---

## ❌ Using findById().get()

👉 Crashes if data not present

---

## ❌ Overusing Native Queries

👉 Breaks portability

---

## ❌ Returning Entities directly in APIs

👉 Causes:

- Infinite recursion
- Lazy loading issues

---

---

# 🔹 5. Interview Questions (JPA + Hibernate)

---

## 🔹 Basic

- What is JPA?
- Difference between JPA and Hibernate?
- What is ORM?

---

## 🔹 Core Concepts

- What is Persistence Context?
- What is Entity Lifecycle?
- What is Dirty Checking?

---

## 🔹 Annotations

- @Entity vs @Table
- @OneToMany vs @ManyToOne
- FetchType.LAZY vs EAGER

---

## 🔹 Transactions

- What is @Transactional?
- What triggers rollback?

---

## 🔹 Performance

- What is N+1 problem?
- How to solve it?

---

## 🔹 Advanced Understanding

- Difference between persist() and merge()
- What is LazyInitializationException?
- How Hibernate manages caching?

---

---

# 🔹 6. Quick Revision Notes (FAST RECALL)

---

## 🧠 Core Flow

```
Repository → JPA → Hibernate → SQL → DB
```

---

## 🧠 Entity States

```
Transient → Persistent → Detached → Removed
```

---

## 🧠 Key Annotations

```
@Entity → Table
@Id → Primary Key
@GeneratedValue → Auto ID
@Column → Column Mapping
```

---

## 🧠 Relationships

```
@ManyToOne → Most used
@OneToMany → mappedBy required
```

---

## 🧠 Fetching

```
LAZY → Default ✅
EAGER → Avoid ❌
```

---

## 🧠 Transactions

```
@Transactional → Required for:
- Updates
- Lazy loading
- Dirty checking
```

---

## 🧠 Performance

```
Avoid N+1 → Use Fetch Join
Use Pagination ALWAYS
```

---

---

# 🔥 FINAL INSIGHT (MOST IMPORTANT)

---

👉 JPA + Hibernate is NOT about annotations

👉 It is about understanding:

```
- Object state
- Persistence context
- Transaction lifecycle
```

---

👉 If you understand this:

```
You can build any backend system confidently 🚀
```