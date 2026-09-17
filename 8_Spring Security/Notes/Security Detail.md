# Security Detail

# PART 1: Introduction to Spring Security

---

## 🔹 What is Spring Security

**Spring Security** is a powerful and highly customizable **authentication and authorization framework** for Java applications built with Spring Boot.

It sits **between the client (browser/API consumer)** and your application, controlling:

- **Who can access your application** (Authentication)
- **What they are allowed to do** (Authorization)

👉 It is implemented using a **filter-based architecture**, meaning every HTTP request passes through a chain of security filters before reaching your controller.

---

## 🔹 Why Security is Needed in Web Applications

In real-world backend systems, your APIs and endpoints are **publicly exposed over the internet**.

Without security, your application is vulnerable to:

### 1. Unauthorized Access

- Anyone can access sensitive APIs
- Example:
    
    ```
    GET /api/users
    ```
    
    Without security → Anyone can fetch all users
    

---

### 2. Data Breaches

- Sensitive data (passwords, personal info) can be accessed or modified

---

### 3. Identity Spoofing

- Attackers can pretend to be legitimate users

---

### 4. Session Hijacking

- Stealing user sessions to gain access

---

### 5. CSRF Attacks

- Trick users into performing unwanted actions

---

👉 In real applications (banking, e-commerce, SaaS), **security is not optional—it is foundational**.

---

## 🔹 Problems Spring Security Solves

Spring Security provides **ready-to-use solutions** for real-world security needs:

---

### ✅ 1. Authentication (Who are you?)

It verifies user identity using:

- Username + Password
- (Conceptual) Tokens

👉 Example:

```
User enters:
username = atharva
password = 1234
```

Spring Security:

- Fetches stored user data
- Compares encoded password
- If valid → Authenticated

---

### ✅ 2. Authorization (What can you do?)

After authentication, it controls access:

- Admin → Access everything
- User → Limited access

👉 Example:

```
.hasRole("ADMIN")
```

---

### ✅ 3. Protection Against Common Attacks

Out-of-the-box protection for:

- CSRF (Cross-Site Request Forgery)
- Session fixation
- Clickjacking (basic headers)

---

### ✅ 4. Secure Password Handling

- Enforces password encoding (e.g., BCrypt)
- Prevents storing plain text passwords

---

### ✅ 5. Request-Level Security

Controls access at:

- URL level
- Method level

---

## 🔹 Where Spring Security Fits in Real Workflow

### Without Security:

```
Client → Controller → Service → Database
```

### With Spring Security:

```
Client → Security Filters → Controller → Service → Database
```

👉 Every request must pass through:

- Authentication check
- Authorization check

---

## 🔹 Real-World Example (Practical View)

Imagine you're building a **Job Portal Backend**:

| Endpoint | Access |
| --- | --- |
| `/login` | Public |
| `/jobs` | Logged-in users |
| `/admin/jobs` | Admin only |

Spring Security ensures:

- Only authenticated users access `/jobs`
- Only admins access `/admin/jobs`

---

## 🔹 Key Takeaways (Flow Understanding)

- Spring Security is **not a single class**, it's a **complete security system**
- It works using **filters**, not controllers
- It intercepts requests **before they reach your business logic**
- It handles:
    - Authentication
    - Authorization
    - Security vulnerabilities

# PART 2: Spring Security Architecture (VERY IMPORTANT)

---

## 🔹 Core Idea: Everything Works Through Filters

Spring Security is built on a **Filter-based architecture**.

👉 That means:

Every HTTP request passes through a **chain of filters** before reaching your controller.

---

### 🔁 Request Flow Overview

![https://docs.spring.io/spring-security/reference/_images/servlet/architecture/securityfilterchain.png](https://docs.spring.io/spring-security/reference/_images/servlet/architecture/securityfilterchain.png)

![https://miro.medium.com/1%2AoU_09lWZITVrBko_csgT6A.png](https://miro.medium.com/1%2AoU_09lWZITVrBko_csgT6A.png)

![https://docs.spring.io/spring-security/reference/_images/servlet/architecture/multi-securityfilterchain.png](https://docs.spring.io/spring-security/reference/_images/servlet/architecture/multi-securityfilterchain.png)

4

```
Client Request
      ↓
Security Filter Chain
      ↓
DispatcherServlet
      ↓
Controller
      ↓
Response
```

👉 If a request fails security at any point → it **never reaches the controller**

---

## 🔹 What is a Filter?

A **Filter** is a component that:

- Intercepts HTTP requests
- Performs checks (authentication, authorization, etc.)
- Either:
    - Passes request forward
    - Blocks request

---

### Example (Simplified)

```
publicclassCustomFilterimplementsFilter {

    @Override
publicvoiddoFilter(
ServletRequestrequest,
ServletResponseresponse,
FilterChainchain
    )throwsIOException,ServletException {

System.out.println("Request intercepted");

// Continue request
chain.doFilter(request,response);
    }
}
```

---

## 🔹 SecurityFilterChain (Core Component)

This is the **main entry point of Spring Security configuration**.

👉 It defines:

- Which requests are secured
- How they are secured
- Which filters are applied

---

### Key Point

```
SecurityFilterChain = Ordered list of security filters
```

---

## 🔹 Important Filters (Real-World Only)

Spring Security has many filters, but only a few are **critical in real applications**:

---

### 1. UsernamePasswordAuthenticationFilter

👉 Handles login requests

- Reads username & password from request
- Creates authentication object
- Sends it for validation

---

### 2. BasicAuthenticationFilter

👉 Used in basic auth (rare in production, but important concept)

- Extracts credentials from headers

---

### 3. SecurityContextPersistenceFilter

👉 Maintains user session

- Stores authentication in **SecurityContext**
- Retrieves it for future requests

---

### 4. Authorization Filter (FilterSecurityInterceptor)

👉 Checks access permissions

- Verifies roles/authorities
- Decides:
    - Allow request
    - Deny request

---

### 5. ExceptionTranslationFilter

👉 Handles security exceptions

- Authentication failure → Redirect/login
- Authorization failure → 403 response

---

## 🔹 SecurityContext (VERY IMPORTANT)

This is the **heart of Spring Security runtime**.

```
SecurityContext = holds authenticated user info
```

---

### Structure

```
SecurityContext
└──Authentication
            ├──Principal (User)
            ├──Credentials (Password)
            └──Authorities (Roles)
```

---

### Accessing Current User

```
Authenticationauth=SecurityContextHolder.getContext().getAuthentication();

Stringusername=auth.getName();
```

👉 Used everywhere in real apps:

- Logging
- Auditing
- Role-based logic

---

## 🔹 Authentication Object

Represents a **logged-in user**.

---

### Key Fields

```
publicinterfaceAuthentication {

ObjectgetPrincipal();// User
ObjectgetCredentials();// Password
Collection<?>getAuthorities();// Roles
booleanisAuthenticated();// Status
}
```

---

## 🔹 AuthenticationManager

👉 Responsible for validating user credentials

```
AuthenticationManager → Authenticate user
```

---

### Flow

1. Filter creates Authentication object
2. Sends it to AuthenticationManager
3. Manager validates using providers
4. Returns authenticated object

---

## 🔹 AuthenticationProvider

👉 Actual logic of authentication

Example:

- Fetch user from DB
- Compare password
- Return success/failure

---

### Default Provider

```
DaoAuthenticationProvider
```

Used in most real-world apps:

- Uses UserDetailsService
- Uses PasswordEncoder

---

## 🔹 Full Internal Flow (Step-by-Step)

---

### 🧠 Step 1: Request Comes In

```
POST /login
```

---

### 🧠 Step 2: Filter Intercepts

- UsernamePasswordAuthenticationFilter triggers

---

### 🧠 Step 3: Create Authentication Object

```
newUsernamePasswordAuthenticationToken(username,password);
```

---

### 🧠 Step 4: Send to AuthenticationManager

```
authenticationManager.authenticate(auth);
```

---

### 🧠 Step 5: Provider Validates

- Loads user
- Checks password

---

### 🧠 Step 6: Store in SecurityContext

```
SecurityContextHolder.getContext().setAuthentication(auth);
```

---

### 🧠 Step 7: Authorization Check

- Check roles
- Decide access

---

### 🧠 Step 8: Controller Executes (if allowed)

---

## 🔹 Key Real-World Understanding

- Spring Security is **NOT controller-based**
- It works **before your business logic**
- Everything depends on:
    - Filters
    - SecurityContext
    - AuthenticationManager

# PART 3: Basic Security Setup

---

## 🔹 Adding Spring Security Dependency

Spring Security is not included by default in Spring Boot — you must explicitly add it.

---

### ✅ Maven Dependency

```
<!-- Spring Security Starter -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

### ✅ What This Dependency Adds Internally

When you add this starter, Spring Boot auto-configures:

- **SecurityFilterChain**
- Default authentication mechanism
- Default login form
- Password encoding support
- Security filters

👉 You don’t write anything yet, but **security is already active**

---

## 🔹 What Happens When Application Starts

As soon as your app starts:

### 🔐 1. Default User is Created

Spring Boot creates a default user:

```
Username: user
Password: (generated in console)
```

Example console output:

```
Using generated security password: 8f3d2a1b-xyz
```

---

### 🔐 2. All Endpoints Are Secured

👉 Every endpoint requires authentication:

```
GET /api/data → ❌ Unauthorized (401)
```

---

### 🔐 3. Default Login Page is Enabled

If you open your app in browser:

```
http://localhost:8080
```

👉 You will see a **default login form provided by Spring Security**

---

### 🔐 4. Basic Authentication is Enabled

You can also access APIs using:

```
Authorization: Basic base64(username:password)
```

---

## 🔹 Default Behavior Summary

Without writing any code:

| Feature | Enabled |
| --- | --- |
| Authentication | ✅ |
| Authorization | ✅ (all secured) |
| Login Form | ✅ |
| CSRF Protection | ✅ |
| Session Management | ✅ |

---

## 🔹 Internal Working of Default Setup

Let’s connect this with **Part 2 architecture**

---

### 🧠 Step-by-Step Flow

---

### 1. Request Comes In

```
GET /api/data
```

---

### 2. Security Filter Chain Intercepts

- Checks if user is authenticated

---

### 3. No Authentication Found

👉 Redirects to login page

---

### 4. User Logs In

- Username: `user`
- Password: generated password

---

### 5. Authentication Happens

- Password is validated
- Authentication stored in **SecurityContext**

---

### 6. Request is Allowed

Now:

```
GET /api/data → ✅ Allowed
```

---

## 🔹 Minimal Working Example

---

### Controller

```
@RestController
@RequestMapping("/api")
publicclassDemoController {

// Public endpoint (we'll configure later)
    @GetMapping("/hello")
publicStringhello() {
return"Hello User";
    }
}
```

---

### Run Application

👉 Try accessing:

```
http://localhost:8080/api/hello
```

Result:

- Redirect to login page

---

### After Login

👉 You will see:

```
Hello User
```

---

## 🔹 Important Observations (Real-World Insight)

---

### ⚠️ 1. Default Setup is NOT Production Ready

Problems:

- Random password (not usable)
- All endpoints locked
- No role-based control
- No custom users

---

### ⚠️ 2. You MUST Override Default Configuration

In real projects, you always:

- Define custom users
- Configure roles
- Customize login
- Define access rules

---

## 🔹 How to Disable Default Security (Basic)

Sometimes during development, you want to **temporarily disable security**.

---

### Option 1: Permit All Requests

```
@Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throws Exception {

http
.authorizeHttpRequests(auth ->auth
.anyRequest().permitAll()// Allow all requests
        )
.csrf(csrf ->csrf.disable());// Disable CSRF for testing

returnhttp.build();
}
```

---

### What This Does

- Removes authentication requirement
- All endpoints become public

---

## 🔹 Key Understanding

- Adding dependency = **Security ON**
- Default config is **strict and secure**
- You must customize it for real-world apps

# PART 4: Authentication vs Authorization

---

## 🔹 Core Idea

These are the **two most important pillars** of Spring Security:

```
Authentication → Who are you?
Authorization → What are you allowed to do?
```

👉 Every secure application follows this exact sequence:

1. **Authenticate user**
2. **Authorize access**

---

## 🔹 Authentication (Identity Verification)

Authentication is the process of **verifying the identity of a user**.

---

### 🔸 Real-World Example

```
Login Form:
Username: atharva
Password: ******
```

Spring Security:

- Fetches user details
- Verifies password
- If valid → User is authenticated

---

### 🔸 Internal Working (Important)

1. User sends credentials
2. `UsernamePasswordAuthenticationFilter` intercepts
3. Creates Authentication object:
    
    ```
    newUsernamePasswordAuthenticationToken(username,password);
    ```
    
4. Passes to:
    
    ```
    AuthenticationManager.authenticate()
    ```
    
5. Validation happens via `AuthenticationProvider`
6. If success:
    - Stored in `SecurityContext`

---

### 🔸 Key Point

```
Authentication = Identity verification only
```

❌ It does NOT decide access permissions

---

## 🔹 Authorization (Access Control)

Authorization determines **what an authenticated user is allowed to do**.

---

### 🔸 Real-World Example

| User | Role | Access |
| --- | --- | --- |
| Atharva | USER | View jobs |
| Admin | ADMIN | Manage jobs |

---

### 🔸 Example in Code

```
.authorizeHttpRequests(auth ->auth
.requestMatchers("/admin/**").hasRole("ADMIN")
.requestMatchers("/user/**").hasRole("USER")
)
```

---

### 🔸 Internal Working

1. Request reaches authorization filter
2. Spring Security:
    - Extracts user roles from `Authentication`
3. Matches URL pattern
4. Checks required role
5. Allows or denies request

---

### 🔸 Key Point

```
Authorization = Permission checking after login
```

---

## 🔹 Authentication vs Authorization (Clear Difference)

| Feature | Authentication | Authorization |
| --- | --- | --- |
| Purpose | Verify identity | Check permissions |
| Happens when? | First | After authentication |
| Uses | Username & password | Roles / Authorities |
| Output | Logged-in user | Access granted/denied |

---

## 🔹 Combined Real-World Flow

---

### Scenario: Access Admin API

```
GET /admin/dashboard
```

---

### 🧠 Step 1: Authentication

- User logs in
- Credentials validated
- User stored in SecurityContext

---

### 🧠 Step 2: Authorization

- Spring checks:
    
    ```
    Does user have ROLE_ADMIN?
    ```
    
- If YES → allow
- If NO → deny (403)

---

## 🔹 Example: Full Flow in Code Context

---

### Security Configuration

```
@Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throws Exception {

http
.authorizeHttpRequests(auth ->auth
.requestMatchers("/public/**").permitAll()// No auth needed
.requestMatchers("/user/**").hasRole("USER")
.requestMatchers("/admin/**").hasRole("ADMIN")
.anyRequest().authenticated()
        )
.formLogin();// Enables authentication

returnhttp.build();
}
```

---

### Controller

```
@RestController
@RequestMapping("/admin")
publicclassAdminController {

    @GetMapping("/dashboard")
publicStringdashboard() {
return"Admin Dashboard";
    }
}
```

---

### Behavior

| Case | Result |
| --- | --- |
| Not logged in | Redirect to login |
| Logged in as USER | ❌ 403 Forbidden |
| Logged in as ADMIN | ✅ Access granted |

---

## 🔹 Important Real-World Insight

---

### ⚠️ 1. Authentication Without Authorization is Dangerous

- User logs in
- Can access everything ❌

---

### ⚠️ 2. Authorization Without Authentication is Useless

- No identity to verify ❌

---

### ✅ Both Must Work Together

```
Authentication + Authorization = Secure Application
```

---

## 🔹 Where They Fit in Filter Chain

---

```
Request
  ↓
Authentication Filters
  ↓
SecurityContext (user stored)
  ↓
Authorization Filter
  ↓
Controller
```

---

## 🔹 Common Mistake (Very Important)

---

### ❌ Confusing Roles and Authentication

Wrong thinking:

```
"If user is logged in, they can access everything"
```

Correct thinking:

```
"User must be logged in AND must have proper role"
```

# PART 5: Configuring Security (SecurityFilterChain)

---

## 🔹 Core Idea

In modern Spring Boot (Spring Security 5.7+), security is configured using:

```
SecurityFilterChain (Bean-based configuration)
```

👉 This replaces the old `WebSecurityConfigurerAdapter` (deprecated).

---

## 🔹 Why Configuration is Required

Default security:

- Secures everything ❌ (not practical)
- Uses random password ❌
- No roles ❌

👉 In real-world apps, you must define:

- Which endpoints are public
- Which require authentication
- Which require roles

---

## 🔹 Basic Security Configuration (Modern Approach)

---

### ✅ Minimal Working Configuration

```
@Configuration
@EnableWebSecurity
publicclassSecurityConfig {

    @Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throwsException {

http
.authorizeHttpRequests(auth ->auth
.anyRequest().authenticated()// All requests need login
            )
.formLogin();// Enable form login

returnhttp.build();
    }
}
```

---

### 🔸 What Happens Here

- `anyRequest().authenticated()` → every endpoint needs login
- `.formLogin()` → enables default login page
- `http.build()` → creates filter chain

---

## 🔹 Customizing Authorization Rules

---

### ✅ Real-World Configuration

```
@Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throws Exception {

http
.authorizeHttpRequests(auth ->auth
.requestMatchers("/public/**").permitAll()// Public APIs
.requestMatchers("/user/**").hasRole("USER")// USER role
.requestMatchers("/admin/**").hasRole("ADMIN")// ADMIN role
.anyRequest().authenticated()// Everything else secured
        )
.formLogin();

returnhttp.build();
}
```

---

### 🔸 How It Works

| URL Pattern | Access Rule |
| --- | --- |
| `/public/**` | Anyone |
| `/user/**` | Logged-in USER |
| `/admin/**` | ADMIN only |

---

## 🔹 Important Methods (Used in Real Projects)

---

### 🔸 1. `permitAll()`

```
.requestMatchers("/login").permitAll()
```

👉 Allows access without authentication

---

### 🔸 2. `authenticated()`

```
.anyRequest().authenticated()
```

👉 Requires login

---

### 🔸 3. `hasRole()`

```
.hasRole("ADMIN")
```

👉 Checks for `ROLE_ADMIN`

---

### 🔸 4. `hasAuthority()`

```
.hasAuthority("READ_PRIVILEGE")
```

👉 More granular control

---

## 🔹 Disabling Default Features (Very Important)

---

### 🔸 1. Disable CSRF (for APIs)

```
http.csrf(csrf ->csrf.disable());
```

👉 Used when:

- Building REST APIs
- No browser forms

---

### 🔸 2. Disable Frame Options (for H2 Console)

```
http.headers(headers ->headers.frameOptions(frame ->frame.disable()));
```

---

### 🔸 3. Disable Default Login (optional)

```
http.formLogin(form ->form.disable());
```

---

## 🔹 Stateless vs Stateful (Basic Understanding)

---

### 🔸 Stateful (Default)

- Uses session
- Stores user in SecurityContext

---

### 🔸 Stateless (APIs)

```
http.sessionManagement(session ->
session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
);
```

👉 No session → each request must authenticate

---

## 🔹 Full Real-World Configuration Example

---

```
@Configuration
@EnableWebSecurity
publicclassSecurityConfig {

    @Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throwsException {

http
// Disable CSRF for APIs
.csrf(csrf ->csrf.disable())

// Authorization rules
.authorizeHttpRequests(auth ->auth
.requestMatchers("/api/public/**").permitAll()
.requestMatchers("/api/user/**").hasRole("USER")
.requestMatchers("/api/admin/**").hasRole("ADMIN")
.anyRequest().authenticated()
            )

// Enable form login
.formLogin(form ->form
.loginPage("/login")// Custom login page
.permitAll()
            )

// Session management
.sessionManagement(session ->
session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );

returnhttp.build();
    }
}
```

---

## 🔹 Internal Working of This Configuration

---

### 🧠 Step-by-Step

1. Spring Boot starts
2. Detects `SecurityFilterChain` bean
3. Builds filter chain
4. Applies rules:
    - URL matching
    - Authentication rules
    - Authorization rules
5. Attaches filters to application

---

## 🔹 Request Matching (VERY IMPORTANT)

---

### Order Matters!

```
.requestMatchers("/admin/**").hasRole("ADMIN")
.requestMatchers("/**").permitAll()// ❌ This will override above
```

👉 Always put **specific rules first**

---

## 🔹 Common Real-World Pattern

---

```
.requestMatchers("/auth/**").permitAll()
.requestMatchers("/api/**").authenticated()
```

👉 Used in almost every backend system

---

## 🔹 Key Takeaways

- `SecurityFilterChain` = heart of configuration
- You control:
    - Authentication rules
    - Authorization rules
    - Security behavior
- Order of rules matters
- Always customize default config

# PART 6: In-Memory Authentication

---

## 🔹 Core Idea

In-memory authentication means:

```
Users are stored directly in application memory (RAM)
```

👉 No database involved

---

## 🔹 Why It Is Used

In real-world development, in-memory auth is used for:

- ✅ Testing security quickly
- ✅ Learning Spring Security flow
- ✅ Small internal tools (admin panels, prototypes)

---

### ❌ Not used in production because:

- Data is lost on restart
- Not scalable
- No persistence

---

## 🔹 How Spring Security Uses It Internally

Spring Security provides:

```
InMemoryUserDetailsManager
```

👉 It acts as a **UserDetailsService implementation**

---

## 🔹 Creating Users in Memory

---

### ✅ Basic Configuration

```
@Bean
publicUserDetailsServiceuserDetailsService() {

UserDetailsuser=User.builder()
.username("user")
.password(passwordEncoder().encode("1234"))// Encoded password
.roles("USER")
.build();

returnnewInMemoryUserDetailsManager(user);
}
```

---

### 🔸 What Happens Here

- `User.builder()` → creates user object
- `.roles("USER")` → assigns role
- Stored inside memory
- Used during authentication

---

## 🔹 Adding Multiple Users

---

### ✅ Example

```
@Bean
publicUserDetailsServiceuserDetailsService() {

UserDetailsuser=User.builder()
.username("user")
.password(passwordEncoder().encode("1234"))
.roles("USER")
.build();

UserDetailsadmin=User.builder()
.username("admin")
.password(passwordEncoder().encode("admin123"))
.roles("ADMIN")
.build();

returnnewInMemoryUserDetailsManager(user,admin);
}
```

---

### 🔸 Result

| Username | Role |
| --- | --- |
| user | USER |
| admin | ADMIN |

---

## 🔹 Password Encoding Requirement

---

### ⚠️ Important

Spring Security **does NOT allow plain text passwords**

---

### ❌ Wrong

```
.password("1234")
```

---

### ✅ Correct

```
.password(passwordEncoder().encode("1234"))
```

---

👉 We’ll deeply cover encoding in **Part 7**

---

## 🔹 Complete Working Configuration

---

```
@Configuration
@EnableWebSecurity
publicclassSecurityConfig {

// Password Encoder Bean
    @Bean
publicPasswordEncoderpasswordEncoder() {
returnnewBCryptPasswordEncoder();
    }

// In-Memory Users
    @Bean
publicUserDetailsServiceuserDetailsService() {

UserDetailsuser=User.builder()
.username("user")
.password(passwordEncoder().encode("1234"))
.roles("USER")
.build();

UserDetailsadmin=User.builder()
.username("admin")
.password(passwordEncoder().encode("admin123"))
.roles("ADMIN")
.build();

returnnewInMemoryUserDetailsManager(user,admin);
    }

// Security Configuration
    @Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throwsException {

http
.csrf(csrf ->csrf.disable())
.authorizeHttpRequests(auth ->auth
.requestMatchers("/public/**").permitAll()
.requestMatchers("/user/**").hasRole("USER")
.requestMatchers("/admin/**").hasRole("ADMIN")
.anyRequest().authenticated()
            )
.formLogin();

returnhttp.build();
    }
}
```

---

## 🔹 Real Workflow (What Happens During Login)

---

### 🧠 Step-by-Step

1. User submits login form

```
POST /login
```

---

1. `UsernamePasswordAuthenticationFilter` intercepts

---

1. Credentials sent to `AuthenticationManager`

---

1. `DaoAuthenticationProvider` calls:

```
userDetailsService.loadUserByUsername(username);
```

---

1. User fetched from memory

---

1. Password is validated using encoder

---

1. If valid:

```
Authentication success → stored in SecurityContext
```

---

## 🔹 Role Prefix Behavior (IMPORTANT)

---

### Spring Security internally uses:

```
ROLE_USER
ROLE_ADMIN
```

---

### When you write:

```
.roles("USER")
```

Spring converts it to:

```
ROLE_USER
```

---

### That’s why:

```
.hasRole("USER")// ✅ correct
.hasAuthority("ROLE_USER")// also correct
```

---

## 🔹 Common Mistakes (Very Important)

---

### ❌ 1. Forgetting Password Encoding

```
.password("1234")// Will fail
```

---

### ❌ 2. Role Mismatch

```
.roles("USER")
.hasRole("ADMIN")// ❌ mismatch
```

---

### ❌ 3. Wrong Order in Config

If:

```
.requestMatchers("/**").permitAll()
```

👉 All security rules become useless

---

## 🔹 Where It Fits in Real Workflow

---

```
Login Request
   ↓
Authentication Filter
   ↓
UserDetailsService (InMemory)
   ↓
Password Validation
   ↓
SecurityContext Updated
```

# PART 7: Password Encoding

---

## 🔹 Core Idea

```
Password Encoding = Storing passwords in a secure (non-plain text) format
```

👉 In real-world applications:

- Passwords are **NEVER stored as plain text**
- They are always **encoded (hashed)** before storage

---

## 🔹 Why Password Encoding is Required

---

### ❌ Problem with Plain Text Passwords

If passwords are stored like:

```
username: atharva
password: 1234
```

👉 If database is compromised:

- All user accounts are exposed
- Attackers can log in instantly

---

### ✅ Solution: Encoding (Hashing)

Instead of storing:

```
1234
```

Store:

```
$2a$10$8Kj3kJHf9sdf... (hashed value)
```

---

### 🔐 Key Properties of Encoding

- One-way function (cannot reverse)
- Same input → same hash (with same salt logic)
- Secure against attacks

---

## 🔹 PasswordEncoder Interface

Spring Security provides:

```
publicinterfacePasswordEncoder {

Stringencode(CharSequencerawPassword);

booleanmatches(CharSequencerawPassword,StringencodedPassword);
}
```

---

### 🔸 Methods

---

### 1. encode()

```
Stringencoded=passwordEncoder.encode("1234");
```

👉 Converts raw password → hashed password

---

### 2. matches()

```
passwordEncoder.matches("1234",encodedPassword);
```

👉 Compares raw password with stored hash

---

## 🔹 BCryptPasswordEncoder (Most Used)

---

### ✅ Why BCrypt?

- Automatically adds **salt**
- Resistant to brute-force attacks
- Industry standard

---

### ✅ Configuration

```
@Bean
publicPasswordEncoderpasswordEncoder() {
returnnewBCryptPasswordEncoder();
}
```

---

## 🔹 How BCrypt Works Internally (Important)

---

### 🧠 Step-by-Step

---

### 1. Add Salt

```
password = 1234
salt = random
```

---

### 2. Combine & Hash

```
hash = bcrypt(1234 + salt)
```

---

### 3. Store Result

```
$2a$10$ABC...XYZ
```

👉 This contains:

- Algorithm info
- Cost factor
- Salt
- Hash

---

### 🔸 Important Behavior

Even if you encode same password twice:

```
encode("1234")
encode("1234")
```

👉 Output will be **different every time**

---

## 🔹 How Matching Works

---

### Example

```
booleanisValid=passwordEncoder.matches("1234",storedHash);
```

---

### 🧠 Internally:

1. Extract salt from stored hash
2. Re-hash input password
3. Compare results

---

## 🔹 Using Password Encoder in Real Code

---

### ✅ In-Memory Example

```
UserDetailsuser=User.builder()
.username("user")
.password(passwordEncoder().encode("1234"))// Encoding here
.roles("USER")
.build();
```

---

### 🔸 During Login

```
User enters password → 1234
Stored password → $2a$10$XYZ...
```

Spring Security:

```
passwordEncoder.matches("1234","$2a$10$XYZ...");
```

---

## 🔹 Common Encoders (Real Usage Context)

| Encoder | Usage |
| --- | --- |
| BCryptPasswordEncoder | ✅ Default, recommended |
| NoOpPasswordEncoder | ❌ Only for testing |
| Pbkdf2PasswordEncoder | Rarely used |
| SCryptPasswordEncoder | Rare |

👉 In real projects → **always use BCrypt**

---

## 🔹 DelegatingPasswordEncoder (Basic Concept)

---

Spring Security internally uses:

```
{bcrypt}hashed_password
```

👉 Prefix indicates algorithm

---

### Example

```
{bcrypt}$2a$10$XYZ...
```

---

👉 Allows switching encoders without breaking old passwords

---

## 🔹 Real-World Workflow Integration

---

```
User Registration
   ↓
Password → encode()
   ↓
Store in DB
```

---

```
User Login
   ↓
Raw password entered
   ↓
matches() called
   ↓
Authentication success/failure
```

---

## 🔹 Common Mistakes (Very Important)

---

### ❌ 1. Storing Plain Passwords

```
.password("1234")// ❌ insecure
```

---

### ❌ 2. Double Encoding

```
.password(passwordEncoder.encode(
passwordEncoder.encode("1234")
))
```

👉 Will NEVER match

---

### ❌ 3. Using Different Encoders

- Encode with BCrypt
- Match with NoOp ❌

---

### ❌ 4. Encoding During Login

👉 Encoding should happen:

- Only during registration
- NOT during login

---

## 🔹 Where It Fits in Security Flow

---

```
Login Request
   ↓
Authentication Filter
   ↓
UserDetailsService
   ↓
PasswordEncoder.matches()
   ↓
Authentication Result
```

# PART 8: UserDetails & UserDetailsService

---

## 🔹 Core Idea

Spring Security does NOT directly use your database entity.

Instead, it works with a **standard user model**:

```
UserDetails = Standard representation of a user in Spring Security
UserDetailsService = Service to load that user
```

👉 This abstraction allows Spring Security to work with **any data source** (DB, API, memory)

---

## 🔹 What is UserDetails?

`UserDetails` is an **interface** that represents a user in Spring Security.

---

### ✅ Structure

```
publicinterfaceUserDetails {

StringgetUsername();

StringgetPassword();

Collection<?extendsGrantedAuthority>getAuthorities();

booleanisAccountNonExpired();

booleanisAccountNonLocked();

booleanisCredentialsNonExpired();

booleanisEnabled();
}
```

---

### 🔸 Important Fields (Real Usage)

| Method | Purpose |
| --- | --- |
| getUsername() | Login identifier |
| getPassword() | Encoded password |
| getAuthorities() | Roles/permissions |

👉 Other methods are rarely customized in most apps

---

## 🔹 What is UserDetailsService?

This is the **core interface used during authentication**.

---

### ✅ Definition

```
publicinterfaceUserDetailsService {

UserDetailsloadUserByUsername(Stringusername)
throwsUsernameNotFoundException;
}
```

---

### 🔸 Responsibility

```
Given username → return UserDetails
```

---

👉 This method is called during login

---

## 🔹 Where It Fits in Authentication Flow

---

```
Login Request
   ↓
UsernamePasswordAuthenticationFilter
   ↓
AuthenticationManager
   ↓
DaoAuthenticationProvider
   ↓
UserDetailsService.loadUserByUsername()
   ↓
UserDetails returned
   ↓
PasswordEncoder.matches()
```

---

## 🔹 Default Implementation

---

### In-Memory Case

```
InMemoryUserDetailsManager
```

👉 Already implements `UserDetailsService`

---

## 🔹 Custom UserDetails (Real-World Requirement)

In real applications:

- You have a **User entity (from DB)**
- You must convert it into **UserDetails**

---

### Example Entity

```
publicclassUserEntity {

privateStringusername;
privateStringpassword;
privateStringrole;
}
```

---

### ✅ Custom UserDetails Implementation

```
publicclassCustomUserDetailsimplementsUserDetails {

privateUserEntityuser;

publicCustomUserDetails(UserEntityuser) {
this.user=user;
    }

    @Override
publicStringgetUsername() {
returnuser.getUsername();
    }

    @Override
publicStringgetPassword() {
returnuser.getPassword();
    }

    @Override
publicCollection<?extendsGrantedAuthority>getAuthorities() {

returnList.of(
newSimpleGrantedAuthority("ROLE_"+user.getRole())
        );
    }

    @Override
publicbooleanisAccountNonExpired() {
returntrue;
    }

    @Override
publicbooleanisAccountNonLocked() {
returntrue;
    }

    @Override
publicbooleanisCredentialsNonExpired() {
returntrue;
    }

    @Override
publicbooleanisEnabled() {
returntrue;
    }
}
```

---

## 🔹 Custom UserDetailsService (Very Important)

---

### ✅ Implementation

```
@Service
publicclassCustomUserDetailsServiceimplementsUserDetailsService {

// Imagine this is a database repository
privatefinalMap<String,UserEntity>users=newHashMap<>();

publicCustomUserDetailsService() {
users.put("atharva",newUserEntity("atharva","$2a$10$hash","USER"));
    }

    @Override
publicUserDetailsloadUserByUsername(Stringusername)
throwsUsernameNotFoundException {

UserEntityuser=users.get(username);

if (user==null) {
thrownewUsernameNotFoundException("User not found");
        }

returnnewCustomUserDetails(user);
    }
}
```

---

### 🔸 What Happens Here

- Receives username
- Fetches user from data source
- Converts to UserDetails
- Returns to Spring Security

---

## 🔹 How Spring Uses This

---

During login:

```
UserDetailsuser=userDetailsService.loadUserByUsername(username);
```

Then:

```
passwordEncoder.matches(rawPassword,user.getPassword());
```

---

## 🔹 Using Built-in User (Shortcut)

---

Spring provides a built-in implementation:

```
UserDetailsuser=User.builder()
.username("user")
.password(passwordEncoder.encode("1234"))
.roles("USER")
.build();
```

👉 This already implements `UserDetails`

---

## 🔹 Authorities vs Roles (Important)

---

### Role

```
ROLE_USER
ROLE_ADMIN
```

---

### Authority

```
READ_PRIVILEGE
WRITE_PRIVILEGE
```

---

### Example

```
newSimpleGrantedAuthority("ROLE_ADMIN")
```

---

👉 Internally:

```
Roles are just authorities with prefix "ROLE_"
```

---

## 🔹 Real Workflow (End-to-End)

---

```
User enters username
   ↓
UserDetailsService called
   ↓
User fetched from DB
   ↓
Converted to UserDetails
   ↓
Password checked
   ↓
Authentication success
```

---

## 🔹 Common Mistakes (Very Important)

---

### ❌ 1. Returning null

```
returnnull;// ❌ NEVER do this
```

---

### ❌ 2. Not Prefixing Role

```
"ADMIN" ❌
"ROLE_ADMIN" ✅
```

---

### ❌ 3. Plain Password from DB

```
password="1234" ❌
```

---

### ❌ 4. Ignoring Authorities

👉 Without roles:

- Authorization fails

---

## 🔹 Where It Fits in Real Projects

---

### Typical Flow

```
Database (User Table)
   ↓
UserRepository
   ↓
UserDetailsService
   ↓
Spring Security
```

---

👉 This is how **real authentication works in production**

---

# PART 9: Authentication Flow (VERY IMPORTANT)

---

## 🔹 Core Idea

Authentication flow is the **complete runtime process** that happens when a user logs in.

```
Login Request → Filters → AuthenticationManager → UserDetailsService → Password Check → SecurityContext
```

👉 This is the **most important part of Spring Security**

---

## 🔹 High-Level Flow

![https://miro.medium.com/v2/resize%3Afit%3A1400/1%2Af1NZoTCuXYPzZlVAnmKhbA.png](https://miro.medium.com/v2/resize%3Afit%3A1400/1%2Af1NZoTCuXYPzZlVAnmKhbA.png)

![https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/usernamepasswordauthenticationfilter.png](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/usernamepasswordauthenticationfilter.png)

![https://dz2cdn1.dzone.com/storage/temp/17477984-authentication-manager-provider-implementations.jpg](https://dz2cdn1.dzone.com/storage/temp/17477984-authentication-manager-provider-implementations.jpg)

4

```
Client → Filter → AuthenticationManager → Provider → UserDetailsService → PasswordEncoder → SecurityContext
```

---

## 🔹 Step-by-Step Authentication Flow (Deep Dive)

---

### 🧠 Step 1: Login Request

```
POST /login
Content-Type: application/x-www-form-urlencoded

username=atharva&password=1234
```

👉 This is handled automatically by Spring Security

---

### 🧠 Step 2: UsernamePasswordAuthenticationFilter

---

👉 This filter is responsible for processing login requests

---

### What it does:

```
Stringusername=request.getParameter("username");
Stringpassword=request.getParameter("password");
```

---

### Creates Authentication Object

```
AuthenticationauthRequest=
newUsernamePasswordAuthenticationToken(username,password);
```

👉 At this point:

```
isAuthenticated = false
```

---

## 🔹 Step 3: AuthenticationManager

---

```
authenticationManager.authenticate(authRequest);
```

👉 This is the **central authentication engine**

---

### Responsibility

```
Delegate authentication to providers
```

---

## 🔹 Step 4: AuthenticationProvider (DaoAuthenticationProvider)

---

👉 This is where actual validation happens

---

### Internal Flow

---

### 1. Load User

```
UserDetailsuser=
userDetailsService.loadUserByUsername(username);
```

---

### 2. Validate Password

```
passwordEncoder.matches(rawPassword,user.getPassword());
```

---

### 3. If Valid → Create Authenticated Object

```
AuthenticationauthResult=
newUsernamePasswordAuthenticationToken(
user,
null,
user.getAuthorities()
    );
```

👉 Now:

```
isAuthenticated = true
```

---

## 🔹 Step 5: Store in SecurityContext

---

```
SecurityContextHolder.getContext().setAuthentication(authResult);
```

👉 This means:

```
User is now logged in
```

---

## 🔹 Step 6: Session Persistence

---

👉 Spring stores SecurityContext in session

```
HTTP Session → SecurityContext → Authentication
```

---

👉 So next requests:

- No need to login again

---

## 🔹 Step 7: Success / Failure Handling

---

### ✅ Success

- Redirect to home page
- Continue request

---

### ❌ Failure

- Redirect to `/login?error`
- Exception handled by `ExceptionTranslationFilter`

---

## 🔹 Full Flow (Condensed)

---

```
1. User submits login form
2. Filter extracts credentials
3. Authentication object created
4. Sent to AuthenticationManager
5. Provider validates user
6. Password checked
7. Authentication stored in SecurityContext
8. User is authenticated
```

---

## 🔹 Code-Level Flow Mapping

---

### Security Config

```
http.formLogin();
```

👉 Enables:

- Login endpoint `/login`
- UsernamePasswordAuthenticationFilter

---

### Internal Execution

```
// Step 1: Filter
UsernamePasswordAuthenticationFilter

// Step 2: Manager
AuthenticationManager

// Step 3: Provider
DaoAuthenticationProvider

// Step 4: Service
UserDetailsService

// Step 5: Encoder
PasswordEncoder
```

---

## 🔹 Important Classes Summary

| Component | Role |
| --- | --- |
| UsernamePasswordAuthenticationFilter | Extract credentials |
| AuthenticationManager | Orchestrates auth |
| AuthenticationProvider | Validates user |
| UserDetailsService | Loads user |
| PasswordEncoder | Verifies password |
| SecurityContext | Stores logged-in user |

---

## 🔹 Real Debug Understanding (Very Useful)

---

### If Login Fails → Check:

1. Is `UserDetailsService` returning user?
2. Is password encoded correctly?
3. Is encoder same during match?
4. Is username correct?

---

## 🔹 Common Mistakes (Critical)

---

### ❌ 1. Password Not Encoded

```
"1234" vs"$2a$10$..." →mismatch
```

---

### ❌ 2. User Not Found

```
UsernameNotFoundException
```

---

### ❌ 3. Wrong Login URL

Default:

```
/login
```

---

### ❌ 4. CSRF Blocking Request

- POST login requires CSRF token (if enabled)

---

## 🔹 Where This Fits in Full Security Flow

---

```
Request
  ↓
Authentication Filter
  ↓
AuthenticationManager
  ↓
Provider
  ↓
SecurityContext
  ↓
Authorization (next step)
```

# PART 10: Authorization (Access Control)

---

## 🔹 Core Idea

```
Authorization = Controlling what an authenticated user can access
```

👉 After login, every request is checked:

- Does this user have permission?

---

## 🔹 Where Authorization Happens

---

```
Request
  ↓
Authentication (already done)
  ↓
Authorization Filter
  ↓
Controller (if allowed)
```

👉 If authorization fails:

```
403 Forbidden
```

---

## 🔹 How Spring Security Performs Authorization

Spring Security uses:

- **Roles**
- **Authorities**

---

### 🔸 Source of Roles

From:

```
Authentication.getAuthorities()
```

---

### Example

```
[ROLE_USER,ROLE_ADMIN]
```

---

## 🔹 Configuring Authorization Rules

---

### ✅ Basic Configuration

```
http
.authorizeHttpRequests(auth ->auth
.requestMatchers("/public/**").permitAll()
.requestMatchers("/user/**").hasRole("USER")
.requestMatchers("/admin/**").hasRole("ADMIN")
.anyRequest().authenticated()
    );
```

---

### 🔸 How It Works

1. Match request URL
2. Identify required role
3. Compare with user's authorities
4. Allow or deny

---

## 🔹 hasRole() vs hasAuthority()

---

### 🔸 hasRole()

```
.hasRole("ADMIN")
```

👉 Internally checks:

```
ROLE_ADMIN
```

---

### 🔸 hasAuthority()

```
.hasAuthority("ROLE_ADMIN")
```

👉 Direct match

---

### 🔸 Key Difference

| Method | Input | Internally |
| --- | --- | --- |
| hasRole("ADMIN") | ADMIN | ROLE_ADMIN |
| hasAuthority("ROLE_ADMIN") | ROLE_ADMIN | same |

---

## 🔹 Real-World Role Setup

---

### Example Roles

| Role | Access |
| --- | --- |
| USER | Basic APIs |
| ADMIN | Full control |

---

### User Setup

```
User.builder()
.username("admin")
.password(passwordEncoder.encode("admin123"))
.roles("ADMIN")
.build();
```

---

## 🔹 Controller-Level Authorization

---

### Example

```
@RestController
@RequestMapping("/admin")
publicclassAdminController {

    @GetMapping("/dashboard")
publicStringdashboard() {
return"Admin Dashboard";
    }
}
```

---

👉 Access controlled via configuration:

```
.requestMatchers("/admin/**").hasRole("ADMIN")
```

---

## 🔹 Multiple Rules (Order Matters)

---

### ❌ Wrong

```
.requestMatchers("/**").permitAll()
.requestMatchers("/admin/**").hasRole("ADMIN")
```

👉 First rule overrides everything

---

### ✅ Correct

```
.requestMatchers("/admin/**").hasRole("ADMIN")
.requestMatchers("/**").permitAll()
```

---

## 🔹 Combining Conditions

---

### Multiple Roles

```
.requestMatchers("/dashboard/**")
.hasAnyRole("USER","ADMIN")
```

---

### Multiple Authorities

```
.hasAnyAuthority("READ","WRITE")
```

---

## 🔹 Denied Access Flow

---

### Scenario

```
GET /admin/dashboard
```

User:

```
ROLE_USER
```

---

### Result

```
403 Forbidden
```

---

👉 Handled by:

```
ExceptionTranslationFilter
```

---

## 🔹 Custom Access Logic (Basic)

---

### Example

```
.requestMatchers("/special/**")
.access((auth,context) -> {
returnnewAuthorizationDecision(
auth.get().getAuthorities().stream()
.anyMatch(a ->a.getAuthority().equals("ROLE_ADMIN"))
    );
})
```

👉 Used for custom rules (rare but powerful)

---

## 🔹 Real Workflow (End-to-End)

---

```
User logs in
   ↓
Authentication successful
   ↓
User roles stored in SecurityContext
   ↓
Request made to protected endpoint
   ↓
Authorization filter checks roles
   ↓
Access granted or denied
```

---

## 🔹 Common Mistakes (Very Important)

---

### ❌ 1. Role Prefix Issue

```
.roles("ADMIN")
.hasAuthority("ADMIN")// ❌ mismatch
```

---

### ❌ 2. Wrong Order of Rules

👉 Leads to security bypass

---

### ❌ 3. Not Securing Endpoints

```
.anyRequest().permitAll()// ❌ disables security
```

---

### ❌ 4. Missing Roles in User

👉 Authentication works but authorization fails

---

## 🔹 Where It Fits in Full Flow

---

```
Request
  ↓
Authentication (user verified)
  ↓
Authorization (role check)
  ↓
Controller
```

# PART 11: Form Login Configuration

---

## 🔹 Core Idea

```
Form Login = Handling user login using a web form (username + password)
```

👉 Spring Security provides:

- Default login page ✅
- Ability to create custom login page ✅

---

## 🔹 Default Form Login (Auto Enabled)

When you use:

```
http.formLogin();
```

👉 Spring automatically:

- Enables `/login` endpoint
- Provides default login UI
- Handles authentication flow

---

### 🔸 Default Behavior

| Feature | Value |
| --- | --- |
| Login URL | `/login` |
| Method | POST |
| Username param | `username` |
| Password param | `password` |

---

## 🔹 Default Login Flow

---

```
User → /login (GET)
   ↓
Spring shows login page
   ↓
User submits form (POST /login)
   ↓
Authentication filter processes request
```

---

## 🔹 Custom Form Login Configuration

---

### ✅ Basic Customization

```
@Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throws Exception {

http
.authorizeHttpRequests(auth ->auth
.anyRequest().authenticated()
        )
.formLogin(form ->form
.loginPage("/login")// Custom login page
.loginProcessingUrl("/login")// Form POST URL
.defaultSuccessUrl("/home")// On success
.failureUrl("/login?error=true")// On failure
.permitAll()
        );

returnhttp.build();
}
```

---

## 🔹 Custom Login Page (Frontend)

---

### ✅ Example HTML

```
<!-- login.html -->
<formaction="/login"method="post">

<!-- Username -->
<inputtype="text"name="username"placeholder="Username"/>

<!-- Password -->
<inputtype="password"name="password"placeholder="Password"/>

<!-- Submit -->
<buttontype="submit">Login</button>

</form>
```

---

### 🔸 Important

```
"name" attributes must match Spring Security expectations
```

---

## 🔹 Custom Controller for Login Page

---

```
@Controller
publicclassLoginController {

    @GetMapping("/login")
publicStringloginPage() {
return"login";// returns login.html
    }
}
```

---

## 🔹 Customizing Parameters

---

### Example

```
.formLogin(form ->form
.usernameParameter("email")
.passwordParameter("pass")
)
```

---

### HTML Update

```
<inputtype="text"name="email"/>
<inputtype="password"name="pass"/>
```

---

## 🔹 Success Handling

---

### Default Behavior

- Redirects to previously requested URL

---

### Custom Success URL

```
.defaultSuccessUrl("/dashboard",true)
```

👉 `true` means:

- Always redirect here

---

### Custom Success Handler (Advanced but used)

```
.formLogin(form ->form
.successHandler((request,response,authentication) -> {
response.sendRedirect("/home");
    })
)
```

---

## 🔹 Failure Handling

---

### Default

```
/login?error
```

---

### Custom

```
.failureUrl("/login?error=true")
```

---

### HTML Handling

```
<divth:if="${param.error}">
    Invalid username or password
</div>
```

---

## 🔹 Logout Configuration

---

### ✅ Default Logout

```
http.logout();
```

---

### 🔸 Behavior

| Feature | Value |
| --- | --- |
| URL | `/logout` |
| Method | POST |
| Action | Session invalidated |

---

### Custom Logout

```
http.logout(logout ->logout
.logoutUrl("/logout")
.logoutSuccessUrl("/login?logout=true")
);
```

---

## 🔹 CSRF and Login (Important)

---

### 🔸 If CSRF Enabled

Your form MUST include:

```
<inputtype="hidden"name="_csrf"value="TOKEN"/>
```

---

👉 Otherwise:

```
403 Forbidden
```

---

### 🔸 If Disabled

```
http.csrf(csrf ->csrf.disable());
```

👉 No token required (common for APIs)

---

## 🔹 Full Working Example

---

```
@Configuration
@EnableWebSecurity
publicclassSecurityConfig {

    @Bean
publicSecurityFilterChainsecurityFilterChain(HttpSecurityhttp)throwsException {

http
.authorizeHttpRequests(auth ->auth
.requestMatchers("/login","/css/**").permitAll()
.anyRequest().authenticated()
            )
.formLogin(form ->form
.loginPage("/login")
.defaultSuccessUrl("/home")
.failureUrl("/login?error=true")
.permitAll()
            )
.logout(logout ->logout
.logoutSuccessUrl("/login?logout=true")
            );

returnhttp.build();
    }
}
```

---

## 🔹 Real Workflow (End-to-End)

---

```
User opens /login
   ↓
Custom login page displayed
   ↓
User submits credentials
   ↓
UsernamePasswordAuthenticationFilter
   ↓
AuthenticationManager
   ↓
Success → redirect
Failure → error page
```

---

## 🔹 Common Mistakes (Very Important)

---

### ❌ 1. Wrong Form Field Names

```
name="user" ❌
name="username" ✅
```

---

### ❌ 2. Not Permitting Login URL

```
.requestMatchers("/login").permitAll()
```

👉 Without this → infinite redirect

---

### ❌ 3. CSRF Missing

👉 Leads to 403 error

---

### ❌ 4. GET Instead of POST

```
method="get" ❌
method="post" ✅
```

---

## 🔹 Where It Fits in Security Flow

---

```
Login Page
   ↓
Form Submission
   ↓
Authentication Flow (Part 9)
   ↓
SecurityContext Updated
```

# PART 12: Session Management (Basic)

---

## 🔹 Core Idea

```
Session Management = How Spring Security keeps a user logged in across requests
```

👉 After successful authentication:

- User should NOT log in for every request
- Spring Security uses **HTTP Session** to maintain state

---

## 🔹 What is a Session?

A **session** is a server-side storage that holds user data between requests.

---

### 🔸 How It Works

```
Client → Login → Server creates session → Session ID returned → Stored in browser
```

---

### 🔸 Example

```
Set-Cookie: JSESSIONID=ABC123XYZ
```

👉 Browser sends this with every request:

```
Cookie: JSESSIONID=ABC123XYZ
```

---

## 🔹 Where Spring Security Stores User Info

---

```
HTTP Session
   ↓
SecurityContext
   ↓
Authentication
```

👉 This is how Spring remembers:

- Who the user is
- What roles they have

---

## 🔹 Internal Working of Session Flow

---

### 🧠 Step-by-Step

---

### 1. User Logs In

```
POST /login
```

---

### 2. Authentication Success

```
SecurityContextHolder.getContext().setAuthentication(auth);
```

---

### 3. SecurityContext Stored in Session

Handled by:

```
SecurityContextPersistenceFilter
```

---

### 4. Session ID Sent to Client

```
Set-Cookie: JSESSIONID=XYZ123
```

---

### 5. Next Request

```
GET /api/data
Cookie: JSESSIONID=XYZ123
```

---

### 6. Spring Retrieves User

```
Session → SecurityContext → Authentication
```

👉 No need to log in again

---

## 🔹 Session Creation Policy

Spring Security allows you to control session behavior.

---

### ✅ Configuration

```
http.sessionManagement(session ->
session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
);
```

---

### 🔸 Types (Real Usage)

| Policy | Behavior |
| --- | --- |
| ALWAYS | Always create session |
| IF_REQUIRED | Create only if needed (default) |
| NEVER | Do not create, but use if exists |
| STATELESS | No session at all |

---

## 🔹 Stateless vs Stateful (Very Important)

---

### 🔸 Stateful (Default)

```
Uses session (JSESSIONID)
```

✔ Good for:

- Web applications
- Form login

---

### 🔸 Stateless

```
http.sessionManagement(session ->
session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
);
```

✔ Used in:

- REST APIs
- Token-based systems

---

👉 Behavior:

```
No session → Every request must authenticate
```

---

## 🔹 Session Fixation Protection

---

### Problem

Attacker sets session ID before login

---

### Solution (Spring Security Default)

```
New session is created after login
```

---

### Internal Behavior

```
Old Session → Invalidated
New Session → Created
```

---

## 🔹 Concurrent Session Control (Basic)

---

Limit number of sessions per user:

```
http.sessionManagement(session ->
session
.maximumSessions(1)
.maxSessionsPreventsLogin(true)
);
```

---

### 🔸 Behavior

- Only 1 login allowed
- Second login → blocked

---

## 🔹 Logout and Session

---

### Default Logout

```
http.logout();
```

---

### What Happens

```
Session invalidated
SecurityContext cleared
User logged out
```

---

### Custom Logout

```
http.logout(logout ->logout
.logoutUrl("/logout")
.logoutSuccessUrl("/login?logout")
);
```

---

## 🔹 Real Workflow (End-to-End)

---

```
Login
  ↓
Authentication success
  ↓
SecurityContext stored in session
  ↓
Session ID sent to browser
  ↓
Subsequent requests use session
  ↓
User stays logged in
```

---

## 🔹 Common Mistakes (Very Important)

---

### ❌ 1. Using STATELESS with Form Login

```
STATELESS+formLogin❌ conflict
```

---

### ❌ 2. Not Invalidating Session on Logout

👉 Leads to security issues

---

### ❌ 3. Mixing Session and Token Logic

👉 Causes inconsistent behavior

---

### ❌ 4. Not Understanding Session Storage

👉 Leads to debugging confusion

---

## 🔹 Where It Fits in Security Flow

---

```
Login Request
   ↓
Authentication
   ↓
SecurityContext created
   ↓
Stored in Session
   ↓
Future requests authenticated automatically
```

# PART 13: CSRF Protection (Core Only)

---

## 🔹 Core Idea

```
CSRF = Cross-Site Request Forgery
```

👉 It is a type of attack where:

- A user is tricked into performing an action **without their consent**

---

## 🔹 Why CSRF Exists

CSRF exploits **session-based authentication**

---

### 🔸 Key Problem

```
Browser automatically sends cookies (JSESSIONID)
```

👉 Even if the request is coming from a malicious site

---

## 🔹 Real-World Attack Scenario

---

### 🧠 Step-by-Step

---

### 1. User Logs Into Bank App

```
bank.com → login successful
Session stored in browser
```

---

### 2. User Visits Malicious Website

```
evil.com
```

---

### 3. Hidden Request Sent

```
<formaction="https://bank.com/transfer"method="post">
<inputtype="hidden"name="amount"value="10000"/>
</form>

<script>
document.forms[0].submit();
</script>
```

---

### 4. Browser Sends Request

```
POST /transfer
Cookie: JSESSIONID=ABC123
```

---

### 🚨 Problem

- Server sees valid session
- Executes request
- User never intended it

---

## 🔹 How Spring Security Prevents CSRF

---

### 🔐 Solution: CSRF Token

```
Each request must include a secret token
```

---

### 🔸 Flow

---

1. Server generates CSRF token
2. Sends it to client (in form/page)
3. Client sends it back in request
4. Server validates token

---

### If token missing or invalid:

```
403 Forbidden
```

---

## 🔹 CSRF in Spring Security

---

### Enabled by Default

```
http.csrf();
```

👉 Active automatically for:

- Form-based login
- Browser apps

---

## 🔹 How Token is Used in Forms

---

### Example HTML

```
<formaction="/transfer"method="post">

<inputtype="hidden"name="_csrf"value="TOKEN_HERE"/>

<inputtype="text"name="amount"/>
<buttontype="submit">Send</button>

</form>
```

---

👉 Spring validates `_csrf` before processing

---

## 🔹 Where Token Comes From

---

Spring provides token via:

- Thymeleaf (`th:action`)
- JSP tags
- Request attributes

---

### Example (Thymeleaf)

```
<formth:action="@{/transfer}"method="post">
</form>
```

👉 Automatically injects CSRF token

---

## 🔹 When CSRF is Required

---

### ✅ Required

| Scenario | Reason |
| --- | --- |
| Form-based login | Uses session |
| Web apps (browser) | Cookies auto-sent |

---

### ❌ Not Required

| Scenario | Reason |
| --- | --- |
| REST APIs | No browser |
| Stateless APIs | No session |
| Token-based auth | No cookies |

---

## 🔹 Disabling CSRF (Very Common in APIs)

---

### ✅ Configuration

```
http.csrf(csrf ->csrf.disable());
```

---

### 🔸 Why Safe for APIs?

```
APIs do not rely on browser cookies
```

👉 Instead use:

- Authorization headers
- Tokens

---

## 🔹 CSRF Filter in Flow

---

```
Request
  ↓
CSRF Filter
  ↓
Validate token
  ↓
Continue or reject
```

---

## 🔹 Common Errors

---

### ❌ 1. Missing CSRF Token

```
POST request → 403 Forbidden
```

---

### ❌ 2. Disabled CSRF in Web App

👉 Leads to vulnerability

---

### ❌ 3. Mixing API + Form Logic

👉 Causes confusion

---

## 🔹 Real Workflow Integration

---

### With CSRF Enabled

```
User loads form
   ↓
Server sends CSRF token
   ↓
User submits form
   ↓
Token validated
   ↓
Request processed
```

---

### Without CSRF (API)

```
Request
   ↓
No CSRF check
   ↓
Authentication + Authorization only
```

---

## 🔹 Key Understanding

---

```
CSRF protects session-based authentication systems
```

👉 If:

- Using session → ENABLE CSRF
- Using stateless APIs → DISABLE CSRF

---

# PART 14: Security Flow End-to-End (Full Internal Flow)

---

## 🔹 Core Idea

This part connects **everything you’ve learned** into one continuous execution flow:

```
Request → Filters → Authentication → Authorization → Controller → Response
```

👉 This is exactly how Spring Security works in real applications

---

## 🔹 Complete Flow Overview

![https://miro.medium.com/v2/resize%3Afit%3A1400/1%2ARrRf3-3clS1azO_gS2XKBQ.png](https://miro.medium.com/v2/resize%3Afit%3A1400/1%2ARrRf3-3clS1azO_gS2XKBQ.png)

![https://miro.medium.com/v2/resize%3Afit%3A1200/0%2AiAqX0DyX3LRhlUch.png](https://miro.medium.com/v2/resize%3Afit%3A1200/0%2AiAqX0DyX3LRhlUch.png)

![https://substackcdn.com/image/fetch/%24s_%21hnmN%21%2Cf_auto%2Cq_auto%3Agood%2Cfl_progressive%3Asteep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F610c2bdd-9f59-4239-85cf-c4aa40434c46_1200x1487.png](https://substackcdn.com/image/fetch/%24s_%21hnmN%21%2Cf_auto%2Cq_auto%3Agood%2Cfl_progressive%3Asteep/https%3A%2F%2Fsubstack-post-media.s3.amazonaws.com%2Fpublic%2Fimages%2F610c2bdd-9f59-4239-85cf-c4aa40434c46_1200x1487.png)

4

---

## 🔹 Full Execution Flow (Step-by-Step)

---

## 🧠 PHASE 1: Incoming Request

---

### Example

```
GET /admin/dashboard
Cookie: JSESSIONID=XYZ123
```

---

👉 Request enters **Spring Security Filter Chain**

---

## 🧠 PHASE 2: SecurityContext Retrieval

---

Handled by:

```
SecurityContextPersistenceFilter
```

---

### What Happens

```
Session → SecurityContext → Authentication
```

---

### Case 1: User Already Logged In

```
Authentication present → continue
```

---

### Case 2: Not Logged In

```
Authentication = null → go to authentication phase
```

---

## 🧠 PHASE 3: Authentication (If Required)

---

### Scenario: User NOT Logged In

---

### Step 1: Redirect to Login

```
/login
```

---

### Step 2: User Submits Credentials

```
POST /login
username=atharva&password=1234
```

---

### Step 3: Authentication Flow (From Part 9)

---

```
UsernamePasswordAuthenticationFilter
   ↓
AuthenticationManager
   ↓
AuthenticationProvider
   ↓
UserDetailsService
   ↓
PasswordEncoder
```

---

### Step 4: Authentication Success

```
SecurityContextHolder.getContext().setAuthentication(auth);
```

---

👉 User is now authenticated

---

## 🧠 PHASE 4: Authorization

---

Handled by:

```
FilterSecurityInterceptor
```

---

### Example Rule

```
.requestMatchers("/admin/**").hasRole("ADMIN")
```

---

### What Happens

1. Extract roles:

```
ROLE_USER / ROLE_ADMIN
```

1. Match URL:

```
/admin/**
```

1. Compare roles:
- Match → allow
- No match → deny

---

### If Denied

```
403 Forbidden
```

---

## 🧠 PHASE 5: Controller Execution

---

If authorization passes:

```
Request reaches Controller
```

---

### Example

```
@GetMapping("/admin/dashboard")
publicStringdashboard() {
return"Admin Dashboard";
}
```

---

## 🧠 PHASE 6: Response Returned

---

```
Controller → Response → Client
```

---

## 🔹 Flow for Authenticated Request (Most Common Case)

---

```
Client Request
   ↓
Security Filter Chain
   ↓
SecurityContext retrieved from session
   ↓
Authentication already present
   ↓
Authorization check
   ↓
Controller
   ↓
Response
```

---

## 🔹 Flow for First-Time Login

---

```
Request protected endpoint
   ↓
No Authentication
   ↓
Redirect to login
   ↓
User submits credentials
   ↓
Authentication flow executes
   ↓
SecurityContext updated
   ↓
Retry original request
   ↓
Authorization check
   ↓
Controller
```

---

## 🔹 Key Components in Full Flow

---

| Component | Role |
| --- | --- |
| SecurityFilterChain | Entry point |
| Filters | Process request |
| SecurityContext | Stores user |
| AuthenticationManager | Auth engine |
| UserDetailsService | Load user |
| PasswordEncoder | Validate password |
| Authorization Filter | Access control |

---

## 🔹 Internal Execution Order (Simplified)

---

```
1. SecurityContextPersistenceFilter
2. CSRF Filter
3. Authentication Filters
4. Authorization Filter
5. ExceptionTranslationFilter
```

---

## 🔹 Real Debugging View (Very Important)

---

### If request fails → Identify stage

| Issue | Stage |
| --- | --- |
| Redirect to login | Authentication missing |
| 401 Unauthorized | Authentication failed |
| 403 Forbidden | Authorization failed |
| 200 OK | Success |

---

## 🔹 End-to-End Mental Model

---

```
Every request:
   ↓
Is intercepted by filters
   ↓
User identity is checked (Authentication)
   ↓
Permissions are verified (Authorization)
   ↓
Only then business logic runs
```

---

## 🔹 Common Real-World Mistakes

---

### ❌ 1. Thinking Security is Controller-Based

👉 It is filter-based

---

### ❌ 2. Ignoring SecurityContext

👉 Causes confusion in debugging

---

### ❌ 3. Misconfigured Roles

👉 Leads to 403 errors

---

### ❌ 4. Wrong Filter Order

👉 Breaks authentication flow

---

## 🔹 Where Everything Connects

---

```
Form Login → Authentication Flow → SecurityContext
   ↓
Session Management → Persist user
   ↓
Authorization → Control access
   ↓
CSRF → Protect requests
```

---

# FINAL PART — Complete Spring Security Mastery

---

## 🔹 🔁 Complete Spring Security Workflow (End-to-End)

---

### 🧠 Full Flow (Real Application)

```
1. Client sends request
2. Request enters SecurityFilterChain
3. SecurityContext checked (session)
4. If not authenticated → login required
5. Authentication flow executes
6. User validated via UserDetailsService + PasswordEncoder
7. Authentication stored in SecurityContext
8. Authorization rules applied
9. If allowed → Controller executes
10. Response returned
```

---

### 🔁 Visual Mental Model

```
Request
  ↓
Security Filters
  ↓
Authentication (Who are you?)
  ↓
Authorization (What can you do?)
  ↓
Controller
  ↓
Response
```

---

## 🔹 🧠 How to Think About Spring Security (Real Insight)

---

Instead of memorizing classes, think like this:

```
Spring Security = Gatekeeper in front of your application
```

It ensures:

- No one enters without identity
- No one accesses without permission

---

## 🔹 ✅ Best Practices (Real-World)

---

### 🔐 1. Always Encode Passwords

```
.password(passwordEncoder.encode("password"))
```

👉 Use **BCrypt only**

---

### 🔐 2. Follow Proper Role Design

```
ROLE_USER
ROLE_ADMIN
```

👉 Keep roles simple and meaningful

---

### 🔐 3. Secure Endpoints Explicitly

```
.anyRequest().authenticated()
```

👉 Never leave endpoints unprotected unintentionally

---

### 🔐 4. Keep Public APIs Limited

```
.requestMatchers("/auth/**").permitAll()
```

👉 Only allow what's necessary

---

### 🔐 5. Disable CSRF Only for APIs

```
http.csrf(csrf ->csrf.disable());
```

👉 Do NOT disable for web apps

---

### 🔐 6. Separate Concerns

- Security config → only security
- Business logic → controllers/services

---

### 🔐 7. Use Custom UserDetailsService

👉 Always for real applications (DB-based users)

---

## 🔹 ⚠️ Common Mistakes (Critical)

---

### ❌ 1. Forgetting Password Encoding

👉 Causes login failure

---

### ❌ 2. Role Mismatch

```
.roles("USER")
.hasAuthority("USER")// ❌ wrong
```

---

### ❌ 3. Incorrect Rule Order

```
.requestMatchers("/**").permitAll()// ❌ breaks security
```

---

### ❌ 4. Not Permitting Login Endpoint

```
.requestMatchers("/login").permitAll()
```

👉 Missing this → infinite redirect loop

---

### ❌ 5. Mixing Stateless & Stateful

```
Session + Stateless config = conflict
```

---

### ❌ 6. Ignoring CSRF

👉 Leads to:

- 403 errors
- Or security vulnerabilities

---

## 🔹 ⚡ Quick Debug Checklist (Very Practical)

---

When something fails, check:

---

### 🔍 Login Not Working

- User exists?
- Password encoded?
- Correct encoder used?

---

### 🔍 Getting 403

- Role mismatch?
- Endpoint rule correct?

---

### 🔍 Getting Redirect Loop

- `/login` permitted?

---

### 🔍 Getting 401

- Authentication missing?

---

## 🔹 🎯 Interview Questions (Core Spring Security)

---

### 🔸 Basic

1. What is Spring Security?
2. Difference between authentication and authorization?
3. What is SecurityFilterChain?

---

### 🔸 Intermediate

1. Explain authentication flow in Spring Security
2. What is UserDetailsService?
3. How does PasswordEncoder work?
4. What is SecurityContext?

---

### 🔸 Advanced (Core Only)

1. What happens internally when user logs in?
2. Difference between hasRole() and hasAuthority()?
3. How does session management work in Spring Security?
4. What is CSRF and how Spring handles it?

---

## 🔹 ⚡ Quick Revision Notes (Last-Minute)

---

### 🔑 Core Concepts

```
Authentication → Identity
Authorization → Permission
```

---

### 🔑 Important Components

```
SecurityFilterChain → Entry point
UserDetailsService → Load user
PasswordEncoder → Validate password
SecurityContext → Store user
AuthenticationManager → Authenticate
```

---

### 🔑 Flow

```
Login → Authentication → SecurityContext → Authorization → Controller
```

---

### 🔑 Rules

```
Order matters
Roles need prefix ROLE_
Passwords must be encoded
```

---

## 🔹 🚀 Final Real-World Understanding

---

If you truly understand this:

```
Filter Chain → Authentication → Authorization → SecurityContext → Session
```

👉 You can:

- Debug any Spring Security issue
- Build secure backend systems
- Crack any interview on this topic