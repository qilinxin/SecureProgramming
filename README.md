
# SecureProgramming — Multi‑Module Project (client / server)

A **Maven multi‑module** project containing two submodules: `client` and `server`.
- **client**: a **Spring Boot 3 / Java 17** REST client that sends HTTP requests to a configurable backend (via `RestClient`).
- **server**: the backend service. Your current layout includes `resources/mappers` (commonly used for MyBatis XML).

> This README has been **updated to match your latest project tree** from the screenshot.

---

## Prerequisites

> Recommended and Spring Boot 3‑compatible ranges. Prefer equal or higher patch versions.

- **JDK**: **Java 17** (recommended 17.0.8+)
  - Check: `java -version`
- **Maven**: **3.9+** (recommended 3.9.6+)
  - Check: `mvn -v`
- **MySQL**: **8.0+** (recommended 8.0.30+)
  - Port: `3306` (default)
  - Charset: `utf8mb4`, timezone recommended `UTC` (or your local)

## Project Structure (Updated)

```text
SecureProgramming/
├─ .idea/                                   # IDE configs (ignore in VCS)
├─ client/                                  # Client submodule (Spring Boot app)
│  ├─ src/
│  │  └─ main/
│  │     ├─ java/
│  │     │  └─ edu/
│  │     │     └─ adelaide/
│  │     │        └─ client/
│  │     └─ resources/
│  ├─ target/                               # Maven build output (ignored)
│  └─ pom.xml
├─ server/                                  # Server submodule (backend service)
│  ├─ src/
│  │  └─ main/
│  │     ├─ java/
│  │     │  └─ edu/
│  │     │     └─ adelaide/
│  │     └─ resources/
│  │        └─ mappers/                     # MyBatis XML (if using MyBatis)
│  ├─ src/
│  │  └─ test/
│  │     └─ java/
│  │        └─ edu/
│  │           └─ adelaide/
│  ├─ target/
│  └─ pom.xml
├─ .gitignore
├─ README.md
├─ DatabaseInfomation.txt
└─ pom.xml                                  # Root POM (aggregates/manages modules)
```

---

## Modules

### client (REST Client)
- **Purpose**: call the backend `server` via HTTP.
- **Typical classes (examples)**:
  - `ClientApplication.java`: Spring Boot entry point.
  - `config/ServerApiProperties.java`: reads `server.api.base-url`.
  - `config/RestClientConfig.java`: creates a `RestClient` bean from the base URL.
  - `controller/**`: your REST controllers (use your real class names).
- **Configuration**: `src/main/resources/application.yml`
  ```yaml
  server:
    api:
      base-url: http://localhost:8080  # Point to the server address
  spring:
    application:
      name: client
  ```

### server (Backend Service)
- **Purpose**: exposes APIs consumed by `client` or other consumers.
- **Key directories**:
  - `src/main/java/edu/adelaide/**`: controllers, services, etc.
  - `src/main/resources/mappers/`: MyBatis mapper XML (if applicable).
  - `src/test/java/edu/adelaide/**`: unit/integration tests.
- **Port**: Spring Boot default `8080` unless you configure otherwise.

---

## Build & Run

> Run these from the repository root (the directory with the aggregating `pom.xml`).

### 1) Full build
```bash
mvn clean package -DskipTests
```

### 2) Build a single module
```bash
# Build only server
mvn -pl server -am clean package -DskipTests

# Build only client
mvn -pl client -am clean package -DskipTests
```

### 3) Run (development)
```bash
# Start server first
cd server
mvn spring-boot:run
# or
java -jar target/server-*.jar

# Then start client
cd ../client
mvn spring-boot:run
# or
java -jar target/client-*.jar
```

> If the server port is not `8080`, update the client's `server.api.base-url` accordingly.

---

## Logging & Error Handling (Recommended)
- Define logging in `resources/logback-spring.xml` (if needed).
- Add a global exception handler (`@RestControllerAdvice`) on both sides to standardize error responses.

---

## `.gitignore` Recommendations

Ignore IDE files and build outputs:

```gitignore
# IntelliJ IDEA
.idea/
*.iml

# VS Code
.vscode/

# Maven
target/

# OS files
.DS_Store
Thumbs.db
```

> If `.idea` was committed already:  
> `git rm -r --cached .idea && git commit -m "chore: untrack .idea"`

---

## Common Maven Commands

```bash
mvn -q -DskipTests package      # Quiet build (skip tests)
mvn test                        # Run tests
mvn spring-boot:run             # Run app (execute inside a submodule)
```

---

## Contribution Guidelines (Optional)

- Branches: `feat/*`, `fix/*`, `chore/*`, `docs/*`
- Commit messages: `<type>(scope): message`, e.g., `feat(user): add create api`

---

## License

Add a `LICENSE` to fit your needs (e.g., MIT / Apache‑2.0).
