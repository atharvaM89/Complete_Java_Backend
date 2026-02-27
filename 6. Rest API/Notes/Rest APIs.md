# Rest API

## Controller

- we Mention @RestController on any Controller
- When we want any method user can access or we need to show implementation of that method so we mention @GetMapping(”/bal”)

## Topic

- It stands for Representational State Transfer
- In Rest API most of the things work on Model(Java Codes) and View (HTML Pages)
- It is State Transfer because if any Model / method return some String then the Output in HTML should be in String
- In Older Model View we need to Return Html pages, but now we can return any thing in the same State

**Note-**

- Rest APIs expose resource via http endpoints like
    - Get / Products
    - post /Products
    - Get /Product/{id} - if we get only one specific product
    - PUT /Product/{id}
    - DELETE /Product/{id}
    - PATCH /Product/{id}

## HTTP Status Code

```java
- 200 ok → Request Success
- 201 Created → Success (Post)
- 204 No Content → Success Without Response Body

- 400 Bad Request → Client Error (When you give Invalid input → Missing field, validation error)
- 401 Unautheorized Error → Not Authenticated
- 403 Forbidden → You are Authenticated but no access to some data
- 404 not found → Resource not found

- 500 internal Server Error → Server-side bug
```

### Small Project → Design Rest API for a Book Store

**Entity -**

- Book-
    - Id
    - title
    - author
    - price

……… Define Restful points …….

| **Operation** | **Http Methods** | **URL** | **Description** | **Status Code** |
| --- | --- | --- | --- | --- |
| - Gell all Books  | GET | /books | - fetch list of all books | - 200 ok  |
| - Get Book by Id | GET | /book/{id} | - Fetch a single book | - 200 ok, 404 |
| - Add new Book | POST | /book | - Create new Book  | - 201, 404 |

Model → Book

- Model Holds data Object

```java
public class Book {
    private Long id;
    private String title;
    private String autor;
    private Double price;

    public Book(){

    }

    public Book(Long id, String title, String autor, Double price) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
```

controller → BookController

- Controller Handles HTTP Request
- Here instead of DB we use List just for understanding

```java
@RestController
@RequestMapping("/books")  //It is Base URL
public class BookController {

    private Map<Long, Book > bookDB= new HashMap<>();

    //Every time when API response us something it is very bad practice to return String
    //Instead of retun String we Wrap that Response in ResponseEntity

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        //RespinseEntity-> It is Noting but a Class in which our API response will be Wrap
        return ResponseEntity.ok(new ArrayList<>(bookDB.values()));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        bookDB.put(book.getId(), book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookbyId(@PathVariable Long id){
        Book book=bookDB.get(id);
        if (book == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(book);
    }
    //When ever you use Path Variable at that time the name of GetMapping{/id} should be same with @PathVariable id

    // put: Updatebook fully -> it will change complete book Object
    //patch : Update only partially -> it will just change some part of obj not completet things of OBJ
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Book book){
        Book existing=bookDB.get(id);
        if (existing == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        bookDB.put(id, book);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 200ok
    }

    @PatchMapping("/{id}/price")
    public ResponseEntity<Book> updatePrice(@PathVariable Long id, @RequestBody Double newPrice){
        Book existing=bookDB.get(id);
        if (existing== null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existing.setPrice(newPrice);
        bookDB.put(id, existing);
        return ResponseEntity.ok(existing); // 200ok
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Book> Delete(@PathVariable Long id){
        Book existing=bookDB.remove(id);
        if (existing== null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
```

- Every time when API response us something it is very bad practice to return String
- Instead of return String we Wrap that Response in ResponseEntity
- we use ResponseEntity → It is Noting but a Class in which our API response will be Wrap

- So, majorly we use ResponseEntity to set our own Status Code, which should be helpful to us
- When ever you use Path Variable at that time the name of GetMapping{/id} should be same with @PathVariable id

## Annotations and Mapping

@RestController

- Combination of:
    - @Controller
    - @ResponseBody
- Marks this class as a **REST API controller**
- Automatically converts Java objects into **JSON response**
- No need to write @ResponseBody on every method

@RequestMapping(”/books”)

- Defines **base URL** for all APIs inside this controller
- All endpoints start with /books

Examples:

- /books
- /books/1
- /books/1/price

ResponseEntity<>

- Wraps:
    - Response body
    - HTTP status code
- Best practice for REST APIs
- Avoids returning plain String
    
    Example:
    
    ```java
    ResponseEntity.ok(data)
    ResponseEntity.status(HttpStatus.CREATED).body(data)
    ```
    

@GetMapping → get all Books

- URL: GET /books
- Returns **all books**
- No request body
- Converts Map values into List
    
    ```java
    new ArrayList<>(bookDB.values())
    ```
    

@PostMapping  → Create Book

```java
@PostMapping
public ResponseEntity<Book> createBook(@RequestBody Book book)
```

**@RequestBody**

- Converts incoming JSON → Book object
- Uses Jackson internally
    
    ```java
    {
      "id": 1,
      "title": "Spring",
      "author": "Rod Johnson",
      "price": 500
    }
    ```
    

**Logic**

- Store book in map
- Return **201 CREATED**

@GetMapping(”/{id}”) → Get Book by ID

```java
@GetMapping("/{id}")
public ResponseEntity<Book> getBookbyId(@PathVariable Long id)
```

**@PathVariable**

- Extracts value from URL
- {id} must match variable name
    
    ```java
    GET /books/1
    ```
    

**Logic**

- If book not found → 404 NOT FOUND
- Else → 200 OK with book

@PutMapping(”/{id}”) - Full Update

```java
@PutMapping("/{id}")
public ResponseEntity<Void> updateBook(@PathVariable Long id, @RequestBody Book book)
```

- Replaces **entire book object**
- Client must send **complete object**
    
    ```java
    {
      "id": 1,
      "title": "Updated Title",
      "author": "Updated Author",
      "price": 600
    }
    ```
    

**HTTP Status**

- 204 NO CONTENT
- Indicates successful update with no response body

@PatchMapping(”/{id}/price”) → Partial Update

```java
@PatchMapping("/{id}/price")
public ResponseEntity<Book> updatePrice(@PathVariable Long id,
                                        @RequestBody Double newPrice)
```

- Used for **partial update**
- Only updates price
- Does not replace entire object

Request body:

```java
a
```

**Flow**

- Fetch existing book
- Update only price
- Return updated object

**@DeleteMapping("/{id}/delete")  → Delete Book**

```java
@DeleteMapping("/{id}/delete")
public ResponseEntity<Book> Delete(@PathVariable Long id)
```

- Removes book from map
- If not found → 404 NOT FOUND
- If deleted → 204 NO CONTENT
    
    ```java
    DELETE /books/1/delete
    ```