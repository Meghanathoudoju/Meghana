# Portfolio Builder Web Application

A full-stack Java web application to create, manage, and share professional portfolios in multiple templates. 

## Tech Stack
- **Backend:** Java, Servlets, JDBC (DAO Pattern)
- **Frontend:** JSP, HTML, CSS, JavaScript
- **Database:** MySQL
- **Dependencies:** Servlet API, JSP API, MySQL Connector, BCrypt, iText (for PDF Export)
- **Server:** Apache Tomcat 9/10

## Project Structure
- `src/main/java/`: Contains Models, DAOs, Controllers, and utility classes.
- `src/main/webapp/`: Contains all JSP views, static CSS, template files, and `WEB-INF/web.xml`.
- `schema.sql`: Contains the database setup script.
- `pom.xml`: Maven configuration for dependency management.

## Setup Instructions

### 1. Database Setup
1. Open MySQL Workbench or your preferred MySQL client.
2. Run the provided `schema.sql` file to create the `portfolio_builder` database and its tables.
3. If necessary, update your database credentials in `src/main/java/com/portfolio/util/DBUtil.java` (default is root/password).

### 2. Application Deployment
You have two options to run the application:

#### Option A: Using your IDE (IntelliJ IDEA / Eclipse)
1. Open the folder as a Maven project.
2. Configure Apache Tomcat server in the build path.
3. Deploy the application artifact exploded or as a WAR.
4. Run the server. The application will be available at `http://localhost:8080/PortfolioBuilder` (or root context `http://localhost:8080/`).

#### Option B: Using Maven Command Line
1. Run `mvn clean package` to generate a WAR file in the `target/` directory.
2. Copy the `.war` file to the `webapps` folder of your Tomcat installation.
3. Start Tomcat. 

### 3. Usage Guide
1. **Register/Login:** Create a new account. User passwords are automatically encrypted via BCrypt.
2. **Dashboard:** Add your information across 5 modules:
   - Profile Details (including an avatar and choosing a template)
   - Education
   - Experience
   - Projects
   - Skills
3. **Template Selection:** From the Profile tab, you can select between `Minimal`, `Modern`, and `Professional` layouts.
4. **View Live Portfolio:** Click "View Live Portfolio" to see the dynamic output generated from your database records.
5. **PDF Export:** Click "Download PDF" anytime to receive a compiled PDF document of your portfolio content!
