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

### Default Pages
Some notable Thymeleaf pages under `templates/`:
- `Login.html` — login page
- `Dashboard.html` — main dashboard
- `CourierForm.html`, `view_courier.html`, `view_courier_details.html`, `trackingCourier.html`
- `EmployeeForm.html`, `viewEmployee.html`, `manageEmployee.html`
- `Country.html`, `state.html`, `District.html` and AJAX partials

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

### Acknowledgements
- Spring Boot team and community
- Thymeleaf
- MySQL
- Razorpay
- iTextPDF
