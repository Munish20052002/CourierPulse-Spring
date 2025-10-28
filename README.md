## CourierPulse-Spring

A Spring Boot (Thymeleaf + JPA) web application for managing courier operations: shipment booking, tracking, employee and master data (country/state/district, departments, designations), with file uploads, PDF generation, and optional Razorpay integration.

### Tech Stack
- **Spring Boot**: 3.2.x
- **Thymeleaf**: server-side HTML rendering
- **Spring Data JPA**: Hibernate ORM
- **MySQL**: 8.x
- **Java**: JDK 17+ recommended
- **Maven**
- **iTextPDF**, **Razorpay Java SDK**

> Note: `pom.xml` declares Spring Boot 3.2.0 but `java.version` as 1.8. Spring Boot 3+ requires Java 17+. Use JDK 17 or downgrade Spring Boot to a version compatible with your JDK.

### Project Structure
- `src/main/java/com/project/Springframework` — Java source (controllers, services, repositories, entities)
- `src/main/resources/templates` — Thymeleaf templates (HTML pages)
- `src/main/resources/static` — Static assets (CSS/JS/images)
- `src/main/resources/application.properties` — App configuration

### Key Features
- Courier create/view and tracking pages (`trackingCourier.html`, `view_courier.html`, details page)
- Employee management (forms and listings)
- Master data: Countries, States, Districts, Departments, Designations (AJAX helpers included)
- File upload support (multipart enabled)
- PDF generation via iText
- Optional payment flows with Razorpay SDK
- Login page, dashboard, and common layout fragments

### Prerequisites
- **JDK**: 17+
- **Maven**: 3.9+
- **MySQL**: 8.x running locally

### Configuration
The app is configured for a local MySQL database named `courier` and runs under the context path `/myapp` on port `8080`.

`src/main/resources/application.properties` (defaults):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/courier
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.open-in-view=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.servlet.multipart.enabled=true
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=15MB

server.port=8080
server.servlet.context-path=/myapp
```

Update `spring.datasource.*` to match your local credentials. For production, prefer environment variables or a profile-specific properties file.

### Database Setup
1. Create the database:
   ```sql
   CREATE DATABASE courier CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
   ```
2. Create a MySQL user or use `root` and grant access.
3. Ensure the credentials in `application.properties` are correct.

### Build and Run
You can run the app directly or build a WAR for deployment.

#### Option A: Run directly (dev)
```bash
# From project root
mvn spring-boot:run
```
Then open: `http://localhost:8080/myapp`

#### Option B: Build WAR and run as executable
```bash
mvn clean package
java -jar target/finance-0.0.1-SNAPSHOT.war
```
Then open: `http://localhost:8080/myapp`

#### Option C: Deploy WAR to external Tomcat
- Build: `mvn clean package`
- Deploy `target/finance-0.0.1-SNAPSHOT.war` to your Tomcat `webapps/`
- Access via your server host and context path (`/myapp`)

### Default Pages
Some notable Thymeleaf pages under `templates/`:
- `Login.html` — login page
- `Dashboard.html` — main dashboard
- `CourierForm.html`, `view_courier.html`, `view_courier_details.html`, `trackingCourier.html`
- `EmployeeForm.html`, `viewEmployee.html`, `manageEmployee.html`
- `Country.html`, `state.html`, `District.html` and AJAX partials

Common fragments are under `templates/Layout/Fragments/` and `templates/common/`.

### Environment Variables (recommended)
Instead of committing secrets, configure via env vars or a profile:
```bash
# Windows PowerShell
set SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/courier
set SPRING_DATASOURCE_USERNAME=root
set SPRING_DATASOURCE_PASSWORD=******
set SERVER_PORT=8080
set SERVER_SERVLET_CONTEXT_PATH=/myapp
```

### Dependencies of Interest
- `spring-boot-starter-thymeleaf` — view rendering
- `spring-boot-starter-data-jpa` — ORM & repositories
- `mysql-connector-java` — MySQL driver
- `tomcat-embed-jasper` — JSP/EL processing support
- `razorpay-java` — payments integration
- `itextpdf` — PDF exports

### Troubleshooting
- **Java version error**: ensure `java -version` shows 17+ and your IDE uses JDK 17.
- **DB connection errors**: verify MySQL is running, DB exists, and credentials match.
- **Port already in use**: change `server.port` or stop the conflicting process.
- **Static assets not refreshing**: `spring.thymeleaf.cache=false` is already set for dev.

### License
Add your chosen open-source license here (e.g., MIT, Apache-2.0).

### Acknowledgements
- Spring Boot team and community
- Thymeleaf
- MySQL
- Razorpay
- iTextPDF
