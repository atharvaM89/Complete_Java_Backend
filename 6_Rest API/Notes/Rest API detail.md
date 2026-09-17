# Rest API detail

# PART 1: Introduction to REST

---

## 1. What is REST?

**REST (Representational State Transfer)** is an architectural style used to design **network-based APIs** where:

- Everything is treated as a **resource**
- Resources are accessed using **HTTP protocols**
- Data is transferred in formats like **JSON (most common in Spring Boot)**

---

### 🔹 Real-world understanding

Think of a REST API as a **bridge between client and server**:

- Client → sends request (mobile app / frontend)
- Server → processes and returns response

Example:

```
GET /users/101
```

👉 Meaning: “Give me the user with ID 101”

---

### 🔹 Resource Concept (VERY IMPORTANT)

In REST, everything is a **resource**, not an action.

| Real World Object | REST Resource |
| --- | --- |
| User | /users |
| Order | /orders |
| Product | /products |

---

## 2. REST Principles (Core Concepts Used in Practice)

These are **not theoretical — they directly affect how you build APIs.**

---

### 2.1 Client–Server Architecture

**Separation of concerns**

- Client → UI / frontend
- Server → business logic + database

### Why this matters:

- You can change frontend without touching backend
- Backend can serve multiple clients (web, mobile, etc.)

---

### 2.2 Statelessness (MOST IMPORTANT)

Every request must contain **all necessary data**.

👉 Server does NOT remember previous requests.

---

### Example:

❌ Wrong thinking:

```
Request 1: Login
Request 2: Get Profile (server assumes user is logged in)
```

✅ Correct REST:

```
Every request includes required data (like token, ID, etc.)
```

---

### Why this matters in real development:

- Easier scaling
- No session management complexity
- Each API is independent

---

### 2.3 Resource-Based Design

APIs should represent **things (nouns)**, not actions (verbs).

---

### ❌ Bad API:

```
/getUsers
/createUser
/deleteUser
```

### ✅ Good REST API:

```
GET    /users
POST   /users
DELETE /users/{id}
```

👉 Action is decided by **HTTP method**, not URL.

---

### 2.4 Uniform Interface

Consistent way to interact with API:

| Operation | HTTP Method | Example |
| --- | --- | --- |
| Fetch | GET | /users |
| Create | POST | /users |
| Update | PUT | /users/1 |
| Delete | DELETE | /users/1 |

---

### 2.5 Representation (Data Format)

Server returns data in a **representation format**:

- JSON (default in Spring Boot)
- XML (rarely used now)

---

### Example JSON response:

```
{
  "id":101,
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

## 3. REST vs SOAP (Practical Comparison Only)

We will ONLY compare what matters in real development.

---

### 🔹 1. Complexity

| REST | SOAP |
| --- | --- |
| Simple | Complex |
| Uses HTTP directly | Uses XML protocol |

👉 REST is used in **99% modern applications**

---

### 🔹 2. Data Format

| REST | SOAP |
| --- | --- |
| JSON (lightweight) | XML (heavy) |

👉 JSON is faster → better performance

---

### 🔹 3. Usage in Industry

| REST | SOAP |
| --- | --- |
| Web apps, mobile apps, startups | Banking, legacy systems |

---

### 🔹 4. Flexibility

| REST | SOAP |
| --- | --- |
| Flexible | Strict |

---

### 🔹 Final Practical Insight

As a backend developer:

- You will almost always build **REST APIs**
- SOAP is rarely used unless working in **legacy enterprise systems**

---

## 4. How REST Fits into Spring Boot

Spring Boot provides **built-in support** for REST APIs:

- Converts Java objects → JSON automatically
- Maps HTTP requests → Java methods
- Handles request/response lifecycle

---

### 🔹 Example (Preview — detailed later)

```
@RestController
publicclassUserController {

    @GetMapping("/users")
publicList<String>getUsers() {
returnList.of("Atharva","Rahul","Sneha");
    }
}
```

👉 Spring Boot automatically:

- Handles HTTP request
- Converts response → JSON
- Sends it back

---

## 5. Real Development Flow (Mental Model)

Whenever you build a REST API, your thinking should be:

```
1. Identify Resource → User
2. Decide URL → /users
3. Choose Method → GET / POST / PUT / DELETE
4. Handle Request Data
5. Process Business Logic
6. Return Response (JSON)
```

---

## 6. Key Takeaways You Must Remember

- REST = **resource-based + HTTP-driven architecture**
- Stateless = **each request independent**
- URL = **noun**, not action
- HTTP method = **defines operation**
- JSON = **standard data format**

# PART 2: HTTP Fundamentals (VERY IMPORTANT)

---

This is the **core foundation of REST APIs**.

If you understand this deeply, everything in Spring Boot REST will become **easy and logical**.

---

# 1. What is HTTP?

**HTTP (HyperText Transfer Protocol)** is the protocol used for communication between:

- Client (frontend / Postman / mobile app)
- Server (Spring Boot backend)

---

### 🔹 Real Flow

```
Client → HTTP Request → Server → HTTP Response → Client
```

---

### 🔹 Example

```
GET /users/101 HTTP/1.1
Host: localhost:8080
```

Server response:

```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "id": 101,
  "name": "Atharva"
}
```

---

# 2. HTTP Methods (CORE OF REST)

Each HTTP method defines **what operation you want to perform**.

---

## 2.1 GET → Fetch Data

Used to **retrieve data** from server.

---

### 🔹 Example

```
GET /users
```

👉 Fetch all users

---

### 🔹 Characteristics

- No request body
- Safe (does not modify data)
- Idempotent (same request → same result)

---

### 🔹 Spring Boot Mapping

```
@GetMapping("/users")
publicList<User>getAllUsers() {
returnuserService.getAllUsers();
}
```

---

## 2.2 POST → Create Data

Used to **create a new resource**

---

### 🔹 Example

```
POST /users
Content-Type: application/json

{
  "name": "Atharva",
  "email": "atharva@gmail.com"
}
```

---

### 🔹 Characteristics

- Has request body
- Not idempotent (calling twice → creates duplicates)

---

### 🔹 Spring Boot Mapping

```
@PostMapping("/users")
publicUsercreateUser(@RequestBodyUseruser) {
returnuserService.save(user);
}
```

---

## 2.3 PUT → Update Entire Resource

Used to **replace full resource**

---

### 🔹 Example

```
PUT /users/101

{
  "name": "Atharva Updated",
  "email": "new@gmail.com"
}
```

---

### 🔹 Characteristics

- Idempotent
- Sends full object

---

### 🔹 Spring Boot Mapping

```
@PutMapping("/users/{id}")
publicUserupdateUser(@PathVariableLongid,
                       @RequestBodyUseruser) {
returnuserService.update(id,user);
}
```

---

## 2.4 PATCH → Partial Update

Used to **update specific fields**

---

### 🔹 Example

```
PATCH /users/101

{
  "email": "updated@gmail.com"
}
```

---

### 🔹 Characteristics

- Partial update
- Not always idempotent (depends on implementation)

---

### 🔹 Spring Boot Mapping

```
@PatchMapping("/users/{id}")
publicUserpartialUpdate(@PathVariableLongid,
                          @RequestBodyMap<String,Object>updates) {
returnuserService.partialUpdate(id,updates);
}
```

---

## 2.5 DELETE → Remove Resource

---

### 🔹 Example

```
DELETE /users/101
```

---

### 🔹 Characteristics

- Idempotent
- No body required

---

### 🔹 Spring Boot Mapping

```
@DeleteMapping("/users/{id}")
publicvoiddeleteUser(@PathVariableLongid) {
userService.delete(id);
}
```

---

# 3. HTTP Status Codes (VERY IMPORTANT)

Status codes tell client **what happened**.

---

## 3.1 Success Codes (2xx)

| Code | Meaning | Usage |
| --- | --- | --- |
| 200 | OK | GET success |
| 201 | Created | POST success |
| 204 | No Content | DELETE success |

---

### 🔹 Example

```
HTTP/1.1 200 OK
```

```
HTTP/1.1 201 Created
```

---

## 3.2 Client Errors (4xx)

| Code | Meaning | Usage |
| --- | --- | --- |
| 400 | Bad Request | Invalid input |
| 401 | Unauthorized | Auth issue |
| 403 | Forbidden | No permission |
| 404 | Not Found | Resource not found |

---

### 🔹 Example

```
HTTP/1.1 404 Not Found
```

---

## 3.3 Server Errors (5xx)

| Code | Meaning |
| --- | --- |
| 500 | Internal Server Error |

---

### 🔹 Example

```
HTTP/1.1 500 Internal Server Error
```

---

# 4. HTTP Request Structure

Every request has **3 main parts**:

---

## 4.1 Request Line

```
POST /users HTTP/1.1
```

---

## 4.2 Headers

Provide **metadata**

```
Content-Type: application/json
Authorization: Bearer token
```

---

### 🔹 Common Headers

| Header | Purpose |
| --- | --- |
| Content-Type | Data format |
| Authorization | Security |
| Accept | Expected response type |

---

## 4.3 Body

Contains actual data (mainly for POST/PUT)

```
{
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

# 5. HTTP Response Structure

---

## 5.1 Status Line

```
HTTP/1.1 200 OK
```

---

## 5.2 Headers

```
Content-Type: application/json
```

---

## 5.3 Body

```
{
  "id":101,
  "name":"Atharva"
}
```

---

# 6. Idempotency (Important in Real APIs)

---

### 🔹 Definition

If calling API multiple times → same result

---

### 🔹 Examples

| Method | Idempotent |
| --- | --- |
| GET | ✅ |
| PUT | ✅ |
| DELETE | ✅ |
| POST | ❌ |

---

### 🔹 Why this matters

- Prevent duplicate operations
- Important for retries (network failure)

---

# 7. Real Workflow Example

---

### 🔹 Create User

```
POST /users
```

Response:

```
201 Created
```

---

### 🔹 Get User

```
GET /users/101
```

Response:

```
200 OK
```

---

### 🔹 Delete User

```
DELETE /users/101
```

Response:

```
204 No Content
```

---

# 8. How Spring Boot Uses HTTP Internally

When request hits Spring Boot:

```
1. DispatcherServlet receives request
2. Finds matching controller
3. Calls method
4. Converts Java → JSON
5. Sends HTTP response
```

---

# 9. Key Developer Insights (Critical)

- HTTP method = **operation**
- Status code = **result**
- Headers = **metadata**
- Body = **actual data**
- Always return **correct status codes** (very important in real APIs)

# PART 3: REST API Design Basics (VERY PRACTICAL)

---

This part is where most developers make mistakes.

A REST API is not just about writing controllers — it’s about **designing clean, scalable, and predictable endpoints**.

---

# 1. Resource-Based Design (Foundation)

---

## 🔹 Core Idea

Design APIs around **resources (nouns)** — not actions.

---

### 🔸 Example Domain

Let’s assume we are building a system for:

- Users
- Orders
- Products

---

### ❌ Wrong Design (Action-Based)

```
/getUsers
/createUser
/deleteUserById
/updateUserDetails
```

👉 Problems:

- Not scalable
- Not consistent
- Hard to maintain

---

### ✅ Correct REST Design (Resource-Based)

```
GET    /users
GET    /users/{id}
POST   /users
PUT    /users/{id}
DELETE /users/{id}
```

👉 Same URL → different behavior based on HTTP method

---

## 🔹 Mental Model

```
Resource = Noun (User)
HTTP Method = Action (GET, POST, PUT, DELETE)
```

---

# 2. URI Naming Conventions (CRITICAL IN REAL PROJECTS)

---

## 2.1 Use Plural Nouns

---

### ❌ Bad

```
/user
/product
/order
```

---

### ✅ Good

```
/users
/products
/orders
```

👉 Why?

- Represents a **collection**
- More scalable

---

## 2.2 Use Lowercase and Hyphens

---

### ❌ Bad

```
/GetUsers
/userDetails
```

---

### ✅ Good

```
/users
/user-details
```

---

## 2.3 Do NOT Use Verbs in URL

---

### ❌ Bad

```
/getUsers
/deleteUser
/createOrder
```

---

### ✅ Good

```
GET    /users
DELETE /users/{id}
POST   /orders
```

---

## 2.4 Use Path Variables for Specific Resources

---

### Example

```
/users/101
/orders/5001
```

👉 This identifies a **specific resource**

---

## 2.5 Use Query Parameters for Filtering

---

### Example

```
/users?age=25
/products?category=electronics
```

---

### 🔹 Spring Boot Mapping

```
@GetMapping("/users")
publicList<User>getUsers(@RequestParamintage) {
returnuserService.getUsersByAge(age);
}
```

---

# 3. Designing Endpoints Properly

---

## 3.1 Collection vs Single Resource

---

### Collection

```
/users
```

---

### Single Resource

```
/users/{id}
```

---

---

## 3.2 Nested Resources (VERY PRACTICAL)

Used when resources are **related**

---

### Example

```
/users/101/orders
```

👉 Meaning:

- Get orders of user 101

---

### Spring Boot Example

```
@GetMapping("/users/{userId}/orders")
publicList<Order>getUserOrders(@PathVariableLonguserId) {
returnorderService.getOrdersByUser(userId);
}
```

---

---

## 3.3 Filtering, Sorting, Pagination

---

### Filtering

```
/users?role=admin
```

---

### Sorting

```
/users?sort=asc
```

---

### Pagination

```
/users?page=0&size=10
```

---

👉 These are **real-world must-have APIs**

---

# 4. Request Design (Client Side Thinking)

---

When designing API, always think:

```
What data does client need?
```

---

### Example

Frontend needs:

- List of users
- Only names and emails

👉 Don’t send unnecessary data

---

---

# 5. Response Design (VERY IMPORTANT)

---

## 5.1 Consistent Response Structure

---

### ❌ Bad

```
{
  "username":"Atharva"
}
```

---

### ✅ Good

```
{
  "id":101,
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

---

## 5.2 Always Return Meaningful Data

---

### Example

### POST Response

```
{
  "id":101,
  "name":"Atharva"
}
```

👉 Return created object (important for frontend)

---

---

## 5.3 Proper Status Code Usage

---

| Operation | Status Code |
| --- | --- |
| GET | 200 |
| POST | 201 |
| DELETE | 204 |
| NOT FOUND | 404 |

---

---

# 6. Versioning (Basic Practical)

---

Even though advanced versioning exists, basic usage is:

```
/api/v1/users
```

---

### Why?

- Future changes won’t break old clients

---

---

# 7. Real Example: Well-Designed API

---

### User API

```
GET    /api/v1/users
GET    /api/v1/users/{id}
POST   /api/v1/users
PUT    /api/v1/users/{id}
DELETE /api/v1/users/{id}
```

---

### Orders API

```
GET    /api/v1/orders
GET    /api/v1/orders/{id}
POST   /api/v1/orders
GET    /api/v1/users/{id}/orders
```

---

# 8. Common Design Mistakes (Avoid These)

---

### ❌ Using verbs in URL

### ❌ Inconsistent naming

### ❌ Not using proper HTTP methods

### ❌ Returning wrong status codes

### ❌ Sending too much or too little data

---

# 9. How This Connects to Spring Boot

---

Spring Boot maps your API design like this:

```
@RestController
@RequestMapping("/api/v1/users")
publicclassUserController {

    @GetMapping
publicList<User>getUsers() {}

    @GetMapping("/{id}")
publicUsergetUser(@PathVariableLongid) {}

    @PostMapping
publicUsercreateUser(@RequestBodyUseruser) {}
}
```

---

👉 Your **design directly becomes your controller structure**

---

# 🔑 Developer Insight (VERY IMPORTANT)

Before writing code, always decide:

```
1. What is the resource?
2. What operations are needed?
3. What should URLs look like?
4. What data will client send?
5. What response should be returned?
```

# PART : Creating REST API using Spring Boot (IMPLEMENTATION STARTS)

---

Now we move from **design → actual coding**.

This is where REST concepts meet **Spring Boot internals**.

---

# 1. How Spring Boot Handles REST APIs

---

When a request comes:

```
Client → DispatcherServlet → Controller → Service → Response → JSON → Client
```

---

### 🔹 Internal Working (IMPORTANT)

1. Request hits **DispatcherServlet**
2. It finds matching **Controller method**
3. Executes method
4. Converts Java object → JSON (using Jackson)
5. Sends HTTP response

---

# 2. @RestController (Core Annotation)

---

## 🔹 What it does

```
@RestController
```

👉 Combines:

- `@Controller`
- `@ResponseBody`

---

### 🔹 Meaning

- Every method returns **data (JSON)** instead of views

---

### 🔹 Example

```
@RestController
publicclassTestController {

    @GetMapping("/hello")
publicStringsayHello() {
return"Hello Atharva";
    }
}
```

---

### 🔹 Output

```
"Hello Atharva"
```

---

# 3. @RequestMapping (Base Mapping)

---

## 🔹 Purpose

Defines **base URL for controller**

---

### 🔹 Example

```
@RestController
@RequestMapping("/api/v1/users")
publicclassUserController {
}
```

---

👉 All endpoints inside this class will start with:

```
/api/v1/users
```

---

# 4. HTTP Method Mappings (CORE ANNOTATIONS)

---

## 4.1 @GetMapping

---

### 🔹 Used for GET requests

```
@GetMapping
publicStringgetUsers() {
return"List of users";
}
```

---

### 🔹 Full URL

```
/api/v1/users
```

---

---

## 4.2 @PostMapping

---

### 🔹 Used for creating data

```
@PostMapping
publicStringcreateUser() {
return"User created";
}
```

---

---

## 4.3 @PutMapping

---

### 🔹 Used for updating

```
@PutMapping("/{id}")
publicStringupdateUser(@PathVariableLongid) {
return"User updated with id: "+id;
}
```

---

---

## 4.4 @DeleteMapping

---

### 🔹 Used for deletion

```
@DeleteMapping("/{id}")
publicStringdeleteUser(@PathVariableLongid) {
return"User deleted with id: "+id;
}
```

---

---

## 4.5 @PatchMapping (Optional but Practical)

---

```
@PatchMapping("/{id}")
publicStringpartialUpdate(@PathVariableLongid) {
return"User partially updated";
}
```

---

# 5. First Complete REST Controller (WORKING EXAMPLE)

---

```
@RestController
@RequestMapping("/api/v1/users")
publicclassUserController {

// GET all users
    @GetMapping
publicList<String>getAllUsers() {
returnList.of("Atharva","Rahul","Sneha");
    }

// GET user by ID
    @GetMapping("/{id}")
publicStringgetUserById(@PathVariableLongid) {
return"User with ID: "+id;
    }

// CREATE user
    @PostMapping
publicStringcreateUser() {
return"User created successfully";
    }

// UPDATE user
    @PutMapping("/{id}")
publicStringupdateUser(@PathVariableLongid) {
return"User updated with ID: "+id;
    }

// DELETE user
    @DeleteMapping("/{id}")
publicStringdeleteUser(@PathVariableLongid) {
return"User deleted with ID: "+id;
    }
}
```

---

# 6. How URL Mapping Works Internally

---

Spring Boot creates a mapping table like:

```
GET    /api/v1/users        → getAllUsers()
GET    /api/v1/users/{id}   → getUserById()
POST   /api/v1/users        → createUser()
PUT    /api/v1/users/{id}   → updateUser()
DELETE /api/v1/users/{id}   → deleteUser()
```

---

👉 This mapping is handled by:

- **HandlerMapping**
- **DispatcherServlet**

---

# 7. Returning Data (Important Behavior)

---

## 🔹 Returning String

```
return"Hello";
```

👉 Returns plain text (converted to JSON string)

---

## 🔹 Returning Object

```
returnnewUser(1,"Atharva");
```

👉 Automatically converted to JSON

---

---

# 8. Creating a Proper Model Class

---

## 🔹 User Model

```
publicclassUser {

privateLongid;
privateStringname;

// Constructor
publicUser(Longid,Stringname) {
this.id=id;
this.name=name;
    }

// Getters
publicLonggetId() {returnid; }
publicStringgetName() {returnname; }
}
```

---

---

## 🔹 Returning Model Data

```
@GetMapping
publicList<User>getUsers() {
returnList.of(
newUser(1L,"Atharva"),
newUser(2L,"Rahul")
    );
}
```

---

### 🔹 JSON Output

```
[
  {
    "id":1,
    "name":"Atharva"
  },
  {
    "id":2,
    "name":"Rahul"
  }
]
```

---

# 9. Key Annotations Summary

---

| Annotation | Purpose |
| --- | --- |
| @RestController | Marks class as REST API |
| @RequestMapping | Base URL |
| @GetMapping | GET request |
| @PostMapping | POST request |
| @PutMapping | UPDATE |
| @DeleteMapping | DELETE |

---

# 10. Real Developer Workflow

---

When building API:

```
1. Create Controller class
2. Define base URL (@RequestMapping)
3. Add HTTP mappings (@GetMapping, etc.)
4. Write method logic
5. Return response (object or string)
```

---

# 11. Important Insights (CRITICAL)

---

- Controller = **entry point of API**
- URL + HTTP method → decides which method runs
- Spring automatically:
    - Maps request
    - Converts JSON
    - Sends response

---

# PART 5: Handling Request Data (VERY IMPORTANT)

---

This is one of the **most important parts in real REST API development**.

Because every API depends on:

👉 **How data comes from client → into your controller**

---

# 1. Types of Data in HTTP Request

---

A client can send data in **3 main ways**:

```
1. Query Parameters   → /users?age=25
2. Path Variables     → /users/101
3. Request Body       → JSON data (POST/PUT)
```

---

# 2. @RequestParam (Query Parameters)

---

## 🔹 When to use

Used for:

- Filtering
- Searching
- Optional inputs

---

### 🔹 Example URL

```
/api/v1/users?age=25
```

---

### 🔹 Spring Boot Code

```
@GetMapping("/users")
publicStringgetUsersByAge(@RequestParamintage) {
return"Users with age: "+age;
}
```

---

### 🔹 Multiple Query Params

```
/users?age=25&city=Pune
```

```
@GetMapping("/users")
publicStringgetUsers(
        @RequestParamintage,
        @RequestParamStringcity) {
return"Age: "+age+", City: "+city;
}
```

---

---

## 🔹 Optional Parameters

---

```
@GetMapping("/users")
publicStringgetUsers(
        @RequestParam(required=false)Stringname) {
returnname!=null?name :"No name provided";
}
```

---

👉 Very useful in real APIs

---

# 3. @PathVariable (URL Path Data)

---

## 🔹 When to use

Used when:

👉 Resource is **uniquely identified**

---

### 🔹 Example URL

```
/api/v1/users/101
```

---

### 🔹 Spring Boot Code

```
@GetMapping("/users/{id}")
publicStringgetUser(@PathVariableLongid) {
return"User ID: "+id;
}
```

---

---

## 🔹 Multiple Path Variables

---

```
/users/101/orders/5001
```

```
@GetMapping("/users/{userId}/orders/{orderId}")
publicStringgetOrder(
        @PathVariableLonguserId,
        @PathVariableLongorderId) {
return"User: "+userId+", Order: "+orderId;
}
```

---

---

## 🔹 Custom Name Mapping

---

```
@GetMapping("/users/{id}")
publicStringgetUser(@PathVariable("id")LonguserId) {
return"User ID: "+userId;
}
```

---

# 4. @RequestBody (MOST IMPORTANT)

---

## 🔹 When to use

Used for:

- Creating data (POST)
- Updating data (PUT/PATCH)

---

### 🔹 Example Request

```
POST /users
Content-Type: application/json

{
  "name": "Atharva",
  "email": "atharva@gmail.com"
}
```

---

## 🔹 Spring Boot Code

```
@PostMapping("/users")
publicUsercreateUser(@RequestBodyUseruser) {
returnuser;
}
```

---

---

## 🔹 How it works internally

```
JSON → Jackson → Java Object → Controller Method
```

---

👉 Spring automatically:

- Reads JSON
- Maps to Java object

---

---

# 5. Creating Model for Request Mapping

---

## 🔹 User Class

```
publicclassUser {

privateStringname;
privateStringemail;

// Default constructor (REQUIRED)
publicUser() {}

// Getters & Setters
publicStringgetName() {returnname; }
publicvoidsetName(Stringname) {this.name=name; }

publicStringgetEmail() {returnemail; }
publicvoidsetEmail(Stringemail) {this.email=email; }
}
```

---

👉 Important:

- Default constructor required
- Getters/Setters required

---

---

# 6. Combining All Types (REAL API)

---

```
@PostMapping("/users/{id}")
publicStringexample(
        @PathVariableLongid,
        @RequestParamStringrole,
        @RequestBodyUseruser) {

return"ID: "+id+
", Role: "+role+
", Name: "+user.getName();
}
```

---

### 🔹 Example Request

```
POST /users/101?role=admin

{
  "name": "Atharva",
  "email": "atharva@gmail.com"
}
```

---

---

# 7. Common Mistakes (VERY IMPORTANT)

---

### ❌ Missing @RequestBody

```
publicUsercreateUser(Useruser)
```

👉 Will NOT map JSON properly

---

---

### ❌ Wrong Data Type

```
@RequestParamintage
```

👉 If client sends string → error

---

---

### ❌ Missing Default Constructor

👉 JSON mapping fails

---

---

# 8. Advanced Tip (Real Development)

---

## 🔹 Use Wrapper Types for Optional Fields

```
@RequestParam(required=false)Integerage
```

👉 Instead of `int` (primitive)

---

---

# 9. Data Flow Understanding (CRITICAL)

---

```
Client JSON
   ↓
HTTP Request
   ↓
Spring Boot (Jackson)
   ↓
Java Object (@RequestBody)
   ↓
Controller Method
```

---

---

# 10. Key Insights (MUST REMEMBER)

---

- `@RequestParam` → Query data
- `@PathVariable` → URL data
- `@RequestBody` → JSON body

---

# PART 6: Response Handling (VERY IMPORTANT)

---

This part is critical because:

👉 **Your API is judged by what it returns**

A good API is not just about logic — it’s about:

- Correct data
- Proper structure
- Correct HTTP status codes

---

# 1. How Spring Boot Returns Responses

---

When your controller returns something:

```
returnobject;
```

Spring Boot automatically:

```
Java Object → Jackson → JSON → HTTP Response
```

---

👉 This conversion is handled by:

- **Jackson (ObjectMapper)**

---

# 2. Returning Data as JSON

---

## 🔹 Example: Returning Object

```
@GetMapping("/user")
publicUsergetUser() {
returnnewUser(1L,"Atharva");
}
```

---

### 🔹 Output

```
{
  "id":1,
  "name":"Atharva"
}
```

---

---

## 🔹 Returning List

```
@GetMapping("/users")
publicList<User>getUsers() {
returnList.of(
newUser(1L,"Atharva"),
newUser(2L,"Rahul")
    );
}
```

---

---

# 3. ResponseEntity (MOST IMPORTANT FOR REAL APIs)

---

## 🔹 Why we need it

Normal return:

```
returnuser;
```

👉 Problem:

- No control over status code
- No control over headers

---

## 🔹 Solution → ResponseEntity

```
returnResponseEntity.ok(user);
```

---

---

## 🔹 Basic Syntax

```
ResponseEntity<T>
```

---

---

## 3.1 Returning with Status Code

---

### 🔹 200 OK

```
@GetMapping("/{id}")
publicResponseEntity<User>getUser(@PathVariableLongid) {
Useruser=newUser(id,"Atharva");
returnResponseEntity.ok(user);
}
```

---

---

### 🔹 201 Created

```
@PostMapping
publicResponseEntity<User>createUser(@RequestBodyUseruser) {
returnnewResponseEntity<>(user,HttpStatus.CREATED);
}
```

---

---

### 🔹 204 No Content

```
@DeleteMapping("/{id}")
publicResponseEntity<Void>deleteUser(@PathVariableLongid) {
returnResponseEntity.noContent().build();
}
```

---

---

### 🔹 404 Not Found

```
@GetMapping("/{id}")
publicResponseEntity<User>getUser(@PathVariableLongid) {

if (id>100) {
returnResponseEntity.notFound().build();
    }

returnResponseEntity.ok(newUser(id,"Atharva"));
}
```

---

---

# 4. Custom Response Body (VERY PRACTICAL)

---

## 🔹 Why needed

Real APIs don’t just return raw data.

They return structured responses like:

```
{
  "status":"success",
  "message":"User created",
  "data": {...}
}
```

---

---

## 🔹 Create Custom Response Class

```
publicclassApiResponse<T> {

privateStringstatus;
privateStringmessage;
privateTdata;

publicApiResponse(Stringstatus,Stringmessage,Tdata) {
this.status=status;
this.message=message;
this.data=data;
    }

// getters
}
```

---

---

## 🔹 Use in Controller

```
@PostMapping
publicResponseEntity<ApiResponse<User>>createUser(@RequestBodyUseruser) {

ApiResponse<User>response=
newApiResponse<>("success","User created",user);

returnnewResponseEntity<>(response,HttpStatus.CREATED);
}
```

---

---

# 5. Sending Headers (Advanced but Useful)

---

```
@GetMapping
publicResponseEntity<User>getUser() {

HttpHeadersheaders=newHttpHeaders();
headers.add("Custom-Header","Value");

returnnewResponseEntity<>(
newUser(1L,"Atharva"),
headers,
HttpStatus.OK
    );
}
```

---

---

# 6. Common Response Patterns (REAL APIs)

---

## 🔹 Success Response

```
{
  "status":"success",
  "data": {...}
}
```

---

## 🔹 Error Response

```
{
  "status":"error",
  "message":"User not found"
}
```

---

---

# 7. Best Practices for Responses

---

## ✅ Always use correct status codes

---

## ✅ Return meaningful data

---

## ✅ Keep response structure consistent

---

## ✅ Avoid exposing internal details

---

---

# 8. Common Mistakes (VERY IMPORTANT)

---

### ❌ Returning null

```
returnnull;
```

👉 Causes issues

---

---

### ❌ Wrong status code

```
returnResponseEntity.ok("User not found");
```

👉 Should be 404

---

---

### ❌ Inconsistent response format

👉 Makes frontend difficult

---

---

# 9. Full Example (REALISTIC)

---

```
@RestController
@RequestMapping("/api/v1/users")
publicclassUserController {

    @GetMapping("/{id}")
publicResponseEntity<ApiResponse<User>>getUser(@PathVariableLongid) {

if (id>100) {
ApiResponse<User>error=
newApiResponse<>("error","User not found",null);

returnnewResponseEntity<>(error,HttpStatus.NOT_FOUND);
        }

Useruser=newUser(id,"Atharva");

ApiResponse<User>success=
newApiResponse<>("success","User fetched",user);

returnResponseEntity.ok(success);
    }
}
```

---

# 10. Internal Flow of Response

---

```
Controller returns object
        ↓
Spring checks type (ResponseEntity / Object)
        ↓
Jackson converts to JSON
        ↓
HTTP Response sent with status + headers + body
```

---

# 11. Key Insights (CRITICAL)

---

- ResponseEntity = **full control**
- Always return:
    - Proper status code
    - Structured data
- JSON conversion is automatic
- Consistency = professional API

# PART 7: CRUD Operations (CORE — FULL WORKING FLOW)

---

This is the **heart of real backend development**.

Now we combine everything:

- HTTP methods
- Request handling
- Response handling
- Clean structure

👉 And build a **complete working CRUD API**

---

# 1. What is CRUD?

---

```
C → Create  → POST
R → Read    → GET
U → Update  → PUT
D → Delete  → DELETE
```

---

# 2. Real Project Structure (IMPORTANT)

---

A clean Spring Boot REST API follows:

```
controller → handles HTTP
service    → business logic
repository → data access (for now, we simulate)
model      → data structure
```

---

# 3. Step 1: Create Model Class

---

```
publicclassUser {

privateLongid;
privateStringname;
privateStringemail;

// Default constructor (required)
publicUser() {}

publicUser(Longid,Stringname,Stringemail) {
this.id=id;
this.name=name;
this.email=email;
    }

// Getters & Setters
publicLonggetId() {returnid; }
publicvoidsetId(Longid) {this.id=id; }

publicStringgetName() {returnname; }
publicvoidsetName(Stringname) {this.name=name; }

publicStringgetEmail() {returnemail; }
publicvoidsetEmail(Stringemail) {this.email=email; }
}
```

---

# 4. Step 2: Service Layer (Business Logic)

---

👉 We simulate DB using `List`

```
importorg.springframework.stereotype.Service;
importjava.util.*;

@Service
publicclassUserService {

privateList<User>users=newArrayList<>();
privateLongidCounter=1L;

// CREATE
publicUsercreateUser(Useruser) {
user.setId(idCounter++);
users.add(user);
returnuser;
    }

// READ ALL
publicList<User>getAllUsers() {
returnusers;
    }

// READ BY ID
publicUsergetUserById(Longid) {
returnusers.stream()
.filter(u ->u.getId().equals(id))
.findFirst()
.orElse(null);
    }

// UPDATE
publicUserupdateUser(Longid,UserupdatedUser) {

for (Useruser :users) {
if (user.getId().equals(id)) {
user.setName(updatedUser.getName());
user.setEmail(updatedUser.getEmail());
returnuser;
            }
        }
returnnull;
    }

// DELETE
publicbooleandeleteUser(Longid) {
returnusers.removeIf(u ->u.getId().equals(id));
    }
}
```

---

# 5. Step 3: Controller Layer (API Layer)

---

```
importorg.springframework.web.bind.annotation.*;
importorg.springframework.http.*;
importjava.util.List;

@RestController
@RequestMapping("/api/v1/users")
publicclassUserController {

privatefinalUserServiceuserService;

// Constructor Injection
publicUserController(UserServiceuserService) {
this.userService=userService;
    }

// CREATE
    @PostMapping
publicResponseEntity<User>createUser(@RequestBodyUseruser) {
Usercreated=userService.createUser(user);
returnnewResponseEntity<>(created,HttpStatus.CREATED);
    }

// READ ALL
    @GetMapping
publicResponseEntity<List<User>>getAllUsers() {
returnResponseEntity.ok(userService.getAllUsers());
    }

// READ BY ID
    @GetMapping("/{id}")
publicResponseEntity<User>getUser(@PathVariableLongid) {

Useruser=userService.getUserById(id);

if (user==null) {
returnResponseEntity.notFound().build();
        }

returnResponseEntity.ok(user);
    }

// UPDATE
    @PutMapping("/{id}")
publicResponseEntity<User>updateUser(
            @PathVariableLongid,
            @RequestBodyUseruser) {

Userupdated=userService.updateUser(id,user);

if (updated==null) {
returnResponseEntity.notFound().build();
        }

returnResponseEntity.ok(updated);
    }

// DELETE
    @DeleteMapping("/{id}")
publicResponseEntity<Void>deleteUser(@PathVariableLongid) {

booleandeleted=userService.deleteUser(id);

if (!deleted) {
returnResponseEntity.notFound().build();
        }

returnResponseEntity.noContent().build();
    }
}
```

---

# 6. API Endpoints Summary

---

```
POST   /api/v1/users        → Create user
GET    /api/v1/users        → Get all users
GET    /api/v1/users/{id}   → Get user by ID
PUT    /api/v1/users/{id}   → Update user
DELETE /api/v1/users/{id}   → Delete user
```

---

# 7. Full Request–Response Flow (REAL UNDERSTANDING)

---

### 🔹 Create User

```
POST /api/v1/users
```

```
{
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

### 🔹 Response

```
201 Created
```

```
{
  "id":1,
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

---

### 🔹 Get User

```
GET /api/v1/users/1
```

```
200 OK
```

---

---

### 🔹 Update User

```
PUT /api/v1/users/1
```

```
{
  "name":"Updated Name",
  "email":"new@gmail.com"
}
```

---

---

### 🔹 Delete User

```
DELETE /api/v1/users/1
```

```
204 No Content
```

---

# 8. Internal Flow (VERY IMPORTANT)

---

```
Request
  ↓
Controller (@RestController)
  ↓
Service (business logic)
  ↓
Data (List / DB)
  ↓
Return to Controller
  ↓
ResponseEntity
  ↓
JSON Response
```

---

# 9. Why This Structure is Important

---

| Layer | Responsibility |
| --- | --- |
| Controller | HTTP handling |
| Service | Logic |
| Model | Data |
| Repository | DB (later) |

---

👉 Keeps code:

- Clean
- Maintainable
- Scalable

---

# 10. Common Mistakes (VERY IMPORTANT)

---

### ❌ Writing logic in controller

👉 Always use service layer

---

### ❌ Not handling null

👉 Always return proper status

---

### ❌ Mixing responsibilities

👉 Follow separation of concerns

---

---

# 11. Key Insights (CRITICAL)

---

- CRUD = **core of 90% APIs**
- Always:
    - Use correct HTTP methods
    - Return correct status codes
    - Separate layers
- Service layer = **brain of application**

# PART 8: Exception Handling (VERY IMPORTANT)

---

In real-world APIs:

👉 Things WILL go wrong:

- Invalid input
- Resource not found
- Server errors

A professional API must:

- Handle errors **gracefully**
- Return **proper status codes**
- Send **structured error responses**

---

# 1. Why Exception Handling is Needed

---

### ❌ Without Handling

```
@GetMapping("/{id}")
publicUsergetUser(@PathVariableLongid) {
returnuserService.getUserById(id);// may return null
}
```

👉 Problems:

- NullPointerException
- Unclear error
- Wrong response to client

---

### ✅ With Proper Handling

```
{
  "status":"error",
  "message":"User not found"
}
```

---

# 2. Types of Errors in REST APIs

---

## 🔹 1. Client Errors (4xx)

- Invalid input
- Resource not found

---

## 🔹 2. Server Errors (5xx)

- Code crashes
- Unexpected failures

---

# 3. Approach 1: @ExceptionHandler (Basic Level)

---

## 🔹 What it does

Handles exceptions **inside a controller**

---

## 🔹 Example: Custom Exception

```
publicclassUserNotFoundExceptionextendsRuntimeException {

publicUserNotFoundException(Stringmessage) {
super(message);
    }
}
```

---

---

## 🔹 Throw Exception

```
publicUsergetUserById(Longid) {

returnusers.stream()
.filter(u ->u.getId().equals(id))
.findFirst()
.orElseThrow(() ->
newUserNotFoundException("User not found with id: "+id)
            );
}
```

---

---

## 🔹 Handle Exception in Controller

```
@ExceptionHandler(UserNotFoundException.class)
publicResponseEntity<String>handleUserNotFound(UserNotFoundExceptionex) {
returnnewResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
}
```

---

👉 Problem:

- Works only inside that controller

---

# 4. Approach 2: @ControllerAdvice (GLOBAL HANDLING — BEST PRACTICE)

---

## 🔹 What it does

Handles exceptions **across entire application**

---

---

## 🔹 Step 1: Create Global Handler

```
importorg.springframework.web.bind.annotation.*;
importorg.springframework.http.*;

@ControllerAdvice
publicclassGlobalExceptionHandler {
```

---

---

## 🔹 Step 2: Handle Specific Exception

```
@ExceptionHandler(UserNotFoundException.class)
publicResponseEntity<String>handleUserNotFound(UserNotFoundExceptionex) {
returnnewResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
}
```

---

---

## 🔹 Step 3: Handle Generic Exception

```
@ExceptionHandler(Exception.class)
publicResponseEntity<String>handleGeneralException(Exceptionex) {
returnnewResponseEntity<>("Something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);
}
```

---

---

# 5. Structured Error Response (VERY IMPORTANT)

---

Instead of returning plain string:

```
return"User not found";
```

---

## 🔹 Create Error Response Class

```
publicclassErrorResponse {

privateStringstatus;
privateStringmessage;

publicErrorResponse(Stringstatus,Stringmessage) {
this.status=status;
this.message=message;
    }

// getters
}
```

---

---

## 🔹 Use in Global Handler

```
@ExceptionHandler(UserNotFoundException.class)
publicResponseEntity<ErrorResponse>handleUserNotFound(UserNotFoundExceptionex) {

ErrorResponseerror=
newErrorResponse("error",ex.getMessage());

returnnewResponseEntity<>(error,HttpStatus.NOT_FOUND);
}
```

---

---

### 🔹 Output

```
{
  "status":"error",
  "message":"User not found with id: 10"
}
```

---

# 6. Handling Common Errors

---

## 🔹 400 Bad Request

---

```
@ExceptionHandler(IllegalArgumentException.class)
publicResponseEntity<ErrorResponse>handleBadRequest(IllegalArgumentExceptionex) {

returnnewResponseEntity<>(
newErrorResponse("error",ex.getMessage()),
HttpStatus.BAD_REQUEST
    );
}
```

---

---

## 🔹 500 Internal Server Error

---

```
@ExceptionHandler(Exception.class)
publicResponseEntity<ErrorResponse>handleServerError(Exceptionex) {

returnnewResponseEntity<>(
newErrorResponse("error","Internal server error"),
HttpStatus.INTERNAL_SERVER_ERROR
    );
}
```

---

---

# 7. Full Example (REAL IMPLEMENTATION)

---

```
@ControllerAdvice
publicclassGlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
publicResponseEntity<ErrorResponse>handleUserNotFound(UserNotFoundExceptionex) {

returnnewResponseEntity<>(
newErrorResponse("error",ex.getMessage()),
HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
publicResponseEntity<ErrorResponse>handleGeneral(Exceptionex) {

returnnewResponseEntity<>(
newErrorResponse("error","Something went wrong"),
HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
```

---

# 8. Internal Flow of Exception Handling

---

```
Controller / Service throws exception
          ↓
Spring detects exception
          ↓
@ControllerAdvice catches it
          ↓
Creates ResponseEntity
          ↓
JSON response sent to client
```

---

---

# 9. Best Practices (CRITICAL)

---

## ✅ Always use global exception handling

## ✅ Use custom exceptions (e.g., UserNotFoundException)

## ✅ Return proper HTTP status codes

## ✅ Use structured error response

---

---

# 10. Common Mistakes (VERY IMPORTANT)

---

### ❌ Returning raw exception message

👉 Security risk

---

### ❌ Not handling exceptions

👉 Crashes API

---

### ❌ Using generic Exception everywhere

👉 Lose control over errors

---

---

# 11. Key Insights

---

- Exception handling = **professional API behavior**
- @ControllerAdvice = **central error handler**
- Always:
    - Catch → Convert → Respond properly

# PART 9: Validation (CORE)

---

Validation ensures:

👉 **Only correct data enters your system**

Without validation:

- Invalid emails get stored
- Null values break logic
- Bad data corrupts database

---

# 1. Why Validation is Critical

---

### ❌ Without Validation

```
{
  "name":"",
  "email":"invalid-email"
}
```

👉 This gets accepted → BAD DATA

---

### ✅ With Validation

```
{
  "status":"error",
  "message":"Email must be valid"
}
```

---

# 2. Validation in Spring Boot (How it Works)

---

Spring Boot uses:

👉 **Jakarta Bean Validation (Hibernate Validator)**

Flow:

```
Client Request
     ↓
@RequestBody Object
     ↓
@Valid triggers validation
     ↓
If invalid → Exception thrown
     ↓
Handled by @ControllerAdvice
```

---

# 3. @Valid Annotation (ENTRY POINT)

---

## 🔹 Usage

```
@PostMapping
publicResponseEntity<User>createUser(
        @Valid @RequestBodyUseruser) {
returnResponseEntity.ok(user);
}
```

---

👉 `@Valid` tells Spring:

- Validate object before method execution

---

# 4. Validation Annotations (MOST USED)

---

## 🔹 Add to Model Class

```
importjakarta.validation.constraints.*;

publicclassUser {

privateLongid;

    @NotBlank(message="Name cannot be empty")
privateStringname;

    @Email(message="Invalid email format")
privateStringemail;

    @NotNull(message="Age is required")
privateIntegerage;

// getters & setters
}
```

---

---

## 🔹 Common Annotations

---

### 4.1 @NotNull

```
@NotNull
privateIntegerage;
```

👉 Field cannot be null

---

---

### 4.2 @NotBlank (BEST FOR STRINGS)

```
@NotBlank
privateStringname;
```

👉 Not null + not empty + not spaces

---

---

### 4.3 @Email

```
@Email
privateStringemail;
```

---

---

### 4.4 @Size

```
@Size(min=3,max=20)
privateStringname;
```

---

---

### 4.5 @Min / @Max

```
@Min(18)
privateintage;
```

---

---

# 5. What Happens on Validation Failure

---

Spring throws:

```
MethodArgumentNotValidException
```

---

👉 We must handle this properly

---

# 6. Handling Validation Errors (IMPORTANT)

---

## 🔹 Global Handler

```
@ExceptionHandler(MethodArgumentNotValidException.class)
publicResponseEntity<ErrorResponse>handleValidation(
MethodArgumentNotValidExceptionex) {

StringerrorMessage=ex.getBindingResult()
.getFieldErrors()
.get(0)
.getDefaultMessage();

returnnewResponseEntity<>(
newErrorResponse("error",errorMessage),
HttpStatus.BAD_REQUEST
    );
}
```

---

---

## 🔹 Output Example

```
{
  "status":"error",
  "message":"Name cannot be empty"
}
```

---

---

# 7. Returning Multiple Validation Errors (ADVANCED PRACTICAL)

---

```
@ExceptionHandler(MethodArgumentNotValidException.class)
publicResponseEntity<Map<String,String>>handleValidationErrors(
MethodArgumentNotValidExceptionex) {

Map<String,String>errors=newHashMap<>();

ex.getBindingResult().getFieldErrors().forEach(error ->
errors.put(error.getField(),error.getDefaultMessage())
    );

returnnewResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
}
```

---

---

## 🔹 Output

```
{
  "name":"Name cannot be empty",
  "email":"Invalid email format"
}
```

---

# 8. Real Controller Example

---

```
@PostMapping
publicResponseEntity<User>createUser(
        @Valid @RequestBodyUseruser) {

returnnewResponseEntity<>(user,HttpStatus.CREATED);
}
```

---

---

# 9. Internal Flow (CRITICAL)

---

```
JSON Request
   ↓
Mapped to Java Object
   ↓
@Valid triggers validation
   ↓
If invalid → Exception thrown
   ↓
@ControllerAdvice handles it
   ↓
Error response returned
```

---

---

# 10. Common Mistakes (VERY IMPORTANT)

---

### ❌ Forgetting @Valid

```
publicUsercreateUser(@RequestBodyUseruser)
```

👉 Validation will NOT run

---

---

### ❌ Using primitive types

```
privateintage;
```

👉 Cannot validate null

---

---

### ❌ Not handling validation errors

👉 Returns ugly default response

---

---

# 11. Best Practices

---

## ✅ Always validate input

## ✅ Use meaningful messages

## ✅ Handle errors globally

## ✅ Use wrapper classes (Integer, not int)

---

---

# 12. Key Insights

---

- Validation = **data protection layer**
- @Valid = **trigger**
- Annotations = **rules**
- Exception handler = **response formatter**

# PART 10: DTO Pattern (VERY PRACTICAL)

---

This is where your API becomes **professional and production-ready**.

👉 Without DTOs:

- You expose internal data
- You lose control over API structure
- You create tight coupling

---

# 1. What is DTO?

---

**DTO = Data Transfer Object**

👉 A separate class used to:

- Receive request data
- Send response data

---

---

## 🔹 Key Idea

```
Client ↔ DTO ↔ Controller ↔ Entity ↔ Database
```

---

👉 Client NEVER directly interacts with Entity

---

# 2. Why DTO is Needed (VERY IMPORTANT)

---

## 2.1 Security

---

### ❌ Without DTO

```
publicclassUser {
privateLongid;
privateStringname;
privateStringemail;
privateStringpassword;// exposed ❌
}
```

👉 Password exposed in API → BAD

---

---

### ✅ With DTO

```
publicclassUserDTO {
privateStringname;
privateStringemail;
}
```

👉 Password hidden → SAFE

---

---

## 2.2 Control Over API

---

You can:

- Change API response without touching DB
- Customize fields

---

---

## 2.3 Loose Coupling

---

Entity changes → DTO remains stable

---

---

# 3. Types of DTOs (Practical)

---

## 🔹 Request DTO

Used for incoming data

```
publicclassUserRequestDTO {

privateStringname;
privateStringemail;

// getters & setters
}
```

---

---

## 🔹 Response DTO

Used for outgoing data

```
publicclassUserResponseDTO {

privateLongid;
privateStringname;
privateStringemail;

// getters & setters
}
```

---

---

# 4. Entity vs DTO (CLEAR DIFFERENCE)

---

| Entity | DTO |
| --- | --- |
| Represents DB | Represents API |
| Contains all fields | Contains required fields only |
| Used internally | Used externally |

---

---

# 5. Mapping Between Entity and DTO

---

## 🔹 Approach: Manual Mapping (MOST IMPORTANT FOR BEGINNERS)

---

### 5.1 Entity → DTO

```
publicUserResponseDTOconvertToDTO(Useruser) {

UserResponseDTOdto=newUserResponseDTO();

dto.setId(user.getId());
dto.setName(user.getName());
dto.setEmail(user.getEmail());

returndto;
}
```

---

---

### 5.2 DTO → Entity

```
publicUserconvertToEntity(UserRequestDTOdto) {

Useruser=newUser();

user.setName(dto.getName());
user.setEmail(dto.getEmail());

returnuser;
}
```

---

---

# 6. Using DTO in Controller (REAL IMPLEMENTATION)

---

```
@PostMapping
publicResponseEntity<UserResponseDTO>createUser(
        @RequestBodyUserRequestDTOrequestDTO) {

// Convert DTO → Entity
Useruser=convertToEntity(requestDTO);

// Save user
UsersavedUser=userService.createUser(user);

// Convert Entity → DTO
UserResponseDTOresponseDTO=convertToDTO(savedUser);

returnnewResponseEntity<>(responseDTO,HttpStatus.CREATED);
}
```

---

---

# 7. Full Flow with DTO

---

```
Client JSON
   ↓
UserRequestDTO
   ↓
Convert → Entity
   ↓
Service Layer
   ↓
Entity
   ↓
Convert → UserResponseDTO
   ↓
JSON Response
```

---

---

# 8. Validation with DTO (BEST PRACTICE)

---

👉 Always apply validation on DTO, NOT entity

---

```
publicclassUserRequestDTO {

    @NotBlank(message="Name required")
privateStringname;

    @Email(message="Invalid email")
privateStringemail;
}
```

---

---

# 9. Common Mistakes (VERY IMPORTANT)

---

### ❌ Using Entity directly in API

👉 Breaks abstraction

---

### ❌ Exposing sensitive fields

👉 Security risk

---

### ❌ Not separating request/response DTO

👉 Less control

---

---

# 10. Best Practices

---

## ✅ Always use DTO for APIs

## ✅ Separate request & response DTO

## ✅ Keep DTO minimal

## ✅ Apply validation on DTO

---

---

# 11. Real Developer Insight (CRITICAL)

---

In real companies:

👉 DTO is ALWAYS used

Because:

- APIs evolve
- Database changes
- Security matters

---

---

# 12. Key Takeaways

---

- DTO = API layer object
- Entity = DB layer object
- Mapping = bridge between them
- Keeps API clean, secure, flexible

# PART 11: API Testing Basics (Postman)

---

Now that your API is built, the next critical step is:

👉 **Testing your APIs like a real developer**

Without testing:

- You don’t know if APIs work
- Bugs go unnoticed
- Integration fails

---

# 1. What is API Testing?

---

API Testing means:

👉 Sending HTTP requests manually and verifying:

- Response data
- Status codes
- Behavior

---

### 🔹 Tools Used

- Postman (MOST POPULAR)
- curl (CLI)
- Swagger (later)

---

---

# 2. Why Postman is Important

---

Postman allows you to:

- Send HTTP requests (GET, POST, PUT, DELETE)
- Add headers
- Send JSON body
- View responses easily

---

---

# 3. Basic Setup

---

## 🔹 Your API Base URL

```
http://localhost:8080/api/v1/users
```

---

👉 Your Spring Boot app must be running

---

---

# 4. Testing Each API (STEP-BY-STEP)

---

# 4.1 Testing GET (Fetch All Users)

---

## 🔹 Request

- Method: **GET**
- URL:

```
http://localhost:8080/api/v1/users
```

---

## 🔹 Expected Response

```
[
  {
    "id":1,
    "name":"Atharva",
    "email":"atharva@gmail.com"
  }
]
```

---

---

# 4.2 Testing GET by ID

---

## 🔹 Request

```
GET http://localhost:8080/api/v1/users/1
```

---

## 🔹 Expected

- Status: **200 OK**
- OR **404 Not Found**

---

---

# 4.3 Testing POST (Create User)

---

## 🔹 Request

- Method: POST
- URL:

```
http://localhost:8080/api/v1/users
```

---

## 🔹 Body (IMPORTANT)

Select:

👉 **Body → raw → JSON**

```
{
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

## 🔹 Expected Response

```
{
  "id":1,
  "name":"Atharva",
  "email":"atharva@gmail.com"
}
```

---

---

# 4.4 Testing PUT (Update User)

---

## 🔹 Request

```
PUT http://localhost:8080/api/v1/users/1
```

---

## 🔹 Body

```
{
  "name":"Updated Name",
  "email":"updated@gmail.com"
}
```

---

---

# 4.5 Testing DELETE

---

## 🔹 Request

```
DELETE http://localhost:8080/api/v1/users/1
```

---

## 🔹 Expected

- Status: **204 No Content**

---

---

# 5. Adding Headers (IMPORTANT)

---

## 🔹 Example

```
Content-Type: application/json
```

---

👉 Required for:

- POST
- PUT

---

---

# 6. Testing Validation Errors

---

## 🔹 Send Invalid Data

```
{
  "name":"",
  "email":"wrong"
}
```

---

## 🔹 Expected Response

```
{
  "name":"Name cannot be empty",
  "email":"Invalid email format"
}
```

---

---

# 7. Testing Error Handling

---

## 🔹 Invalid ID

```
GET /users/999
```

---

## 🔹 Expected

```
{
  "status":"error",
  "message":"User not found"
}
```

---

---

# 8. Real Testing Workflow

---

```
1. Start Spring Boot app
2. Open Postman
3. Select HTTP method
4. Enter URL
5. Add body (if needed)
6. Click Send
7. Check:
   - Status code
   - Response body
```

---

---

# 9. Common Mistakes (VERY IMPORTANT)

---

### ❌ Forgetting Content-Type header

👉 Server can't parse JSON

---

---

### ❌ Wrong HTTP method

👉 Endpoint won't match

---

---

### ❌ Wrong URL

👉 404 error

---

---

### ❌ Not checking status codes

👉 Miss bugs

---

---

# 10. Pro Tips (REAL DEVELOPMENT)

---

## ✅ Test every endpoint individually

## ✅ Test edge cases (invalid input)

## ✅ Test error scenarios

## ✅ Keep sample requests saved in Postman

---

---

# 11. Internal Flow During Testing

---

```
Postman Request
     ↓
Spring Boot Controller
     ↓
Service Layer
     ↓
ResponseEntity
     ↓
JSON Response
     ↓
Postman shows output
```

---

---

# 12. Key Insights

---

- Postman = **your API testing lab**
- Always verify:
    - Data
    - Status code
    - Errors
- Testing = **confidence in your API**

# PART 12: REST API Workflow (End-to-End — COMPLETE SYSTEM UNDERSTANDING)

---

This is where everything connects.

👉 You will now understand:

- How request flows internally
- How Spring Boot processes it
- How response is generated

---

# 1. Complete End-to-End Flow

---

```
Client (Postman / Frontend)
        ↓
HTTP Request
        ↓
DispatcherServlet
        ↓
Controller (@RestController)
        ↓
Service Layer
        ↓
Business Logic
        ↓
ResponseEntity
        ↓
Jackson (Object → JSON)
        ↓
HTTP Response
        ↓
Client
```

---

# 2. Step-by-Step Request Flow (DETAILED)

---

## 🔹 Step 1: Client Sends Request

---

### Example

```
POST /api/v1/users
Content-Type: application/json

{
  "name": "Atharva",
  "email": "atharva@gmail.com"
}
```

---

---

## 🔹 Step 2: DispatcherServlet (ENTRY POINT)

---

👉 This is the **front controller of Spring**

Responsibilities:

- Receives all requests
- Routes to correct controller

---

```
Find matching URL + HTTP method
```

---

---

## 🔹 Step 3: Handler Mapping

---

Spring checks:

```
POST /api/v1/users → createUser()
```

---

👉 Uses annotations like:

- @RequestMapping
- @PostMapping

---

---

## 🔹 Step 4: Request Data Binding

---

Spring converts:

```
JSON → Java Object (UserRequestDTO)
```

---

👉 Using:

- Jackson
- @RequestBody

---

---

## 🔹 Step 5: Validation (If Applied)

---

```
@Valid triggers validation
```

---

If invalid:

- Exception thrown
- Goes to @ControllerAdvice

---

---

## 🔹 Step 6: Controller Execution

---

```
@PostMapping
publicResponseEntity<UserResponseDTO>createUser(
        @Valid @RequestBodyUserRequestDTOdto) {
```

---

👉 Controller:

- Receives data
- Calls service

---

---

## 🔹 Step 7: Service Layer (Business Logic)

---

```
DTO → Entity → Process → Save
```

---

Example:

- Assign ID
- Save to DB
- Apply logic

---

---

## 🔹 Step 8: Return to Controller

---

Service returns:

```
User entity
```

---

Controller converts:

```
Entity → Response DTO
```

---

---

## 🔹 Step 9: Response Creation

---

```
returnnewResponseEntity<>(responseDTO,HttpStatus.CREATED);
```

---

---

## 🔹 Step 10: JSON Conversion

---

```
Java Object → JSON (Jackson)
```

---

---

## 🔹 Step 11: HTTP Response Sent

---

```
HTTP/1.1 201 Created
Content-Type: application/json

{
  "id": 1,
  "name": "Atharva",
  "email": "atharva@gmail.com"
}
```

---

---

# 3. Error Flow (VERY IMPORTANT)

---

## 🔹 Example: Invalid Input

---

```
{
  "name":"",
  "email":"wrong"
}
```

---

### 🔹 Flow

```
Validation fails
      ↓
Exception thrown
      ↓
@ControllerAdvice catches it
      ↓
ErrorResponse created
      ↓
HTTP 400 returned
```

---

---

## 🔹 Example Response

```
{
  "status":"error",
  "message":"Invalid email format"
}
```

---

---

# 4. Internal Components of Spring Boot (IMPORTANT)

---

| Component | Role |
| --- | --- |
| DispatcherServlet | Entry point |
| HandlerMapping | Finds controller |
| Controller | Handles request |
| Service | Business logic |
| Jackson | JSON conversion |
| ExceptionHandler | Error handling |

---

---

# 5. Full Lifecycle Visualization

---

```
Request →
    DispatcherServlet →
        HandlerMapping →
            Controller →
                Service →
            Controller →
        ResponseEntity →
    JSON Conversion →
Response
```

---

---

# 6. Real Developer Thinking (CRITICAL)

---

When building API, always think:

```
1. What request will client send?
2. How will data be received? (@RequestBody / @PathVariable)
3. What validation is needed?
4. What logic will service perform?
5. What response should be returned?
6. What happens if error occurs?
```

---

---

# 7. Common Flow Mistakes

---

### ❌ Skipping service layer

👉 Leads to messy code

---

### ❌ Not handling errors

👉 API crashes

---

### ❌ Not validating input

👉 Bad data

---

---

# 8. Key Insights (FINAL UNDERSTANDING)

---

- REST API = **Flow of data**
- Spring Boot automates:
    - Routing
    - Mapping
    - JSON conversion
- You control:
    - Logic
    - Validation
    - Response

---

---

# 📕 FINAL PART

---

# 1. Complete REST API Flow Summary

---

```
Client → HTTP Request
      → Controller
      → Service
      → Data Processing
      → ResponseEntity
      → JSON Response
      → Client
```

---

---

# 2. Best Practices (CLEAN API DESIGN)

---

## ✅ Use resource-based URLs

## ✅ Use correct HTTP methods

## ✅ Use DTO pattern

## ✅ Validate input

## ✅ Handle exceptions globally

## ✅ Return proper status codes

## ✅ Keep controller thin

## ✅ Keep service logic clean

---

---

# 3. Common Mistakes to Avoid

---

### ❌ Using entities directly in API

### ❌ Returning wrong status codes

### ❌ No validation

### ❌ No exception handling

### ❌ Fat controllers

### ❌ Inconsistent response format

---

---

# 4. Interview Questions (REST + Spring Boot)

---

### 🔹 REST Basics

- What is REST?
- What is statelessness?
- Difference between PUT and PATCH?
- What is idempotency?

---

### 🔹 HTTP

- Difference between 200 and 201?
- What are headers?
- What is request body?

---

### 🔹 Spring Boot

- What is @RestController?
- Difference between @RequestParam and @PathVariable?
- What is ResponseEntity?
- What is @ControllerAdvice?

---

### 🔹 Advanced Practical

- Why DTO is used?
- How validation works?
- How exception handling works?

---

---

# 5. Quick Revision Notes

---

```
REST = Resource-based + HTTP

GET    → Fetch
POST   → Create
PUT    → Update
DELETE → Remove

@RequestParam  → Query
@PathVariable  → URL
@RequestBody   → JSON

@ResponseEntity → control response

@Valid → validation
@ControllerAdvice → global error handling

DTO → safe data transfer
```