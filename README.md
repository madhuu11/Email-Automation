# Birthday Email Automation System

A Spring Boot application that automatically sends personalized birthday emails to users on their special day. This system uses scheduled tasks to check for birthdays daily and sends customized email greetings.

## Features

- **Automated Birthday Emails**: Automatically sends emails to users on their birthdays
- **User Management**: Add and view users with their personal details and birth dates
- **Email Template System**: Customizable email templates with dynamic content placeholders
- **Scheduled Execution**: Configurable scheduling using cron expressions
- **Web Interface**: Simple UI for managing users, templates and viewing birthday information
- **Manual Trigger**: Option to manually trigger birthday emails for testing

## Technology Stack

- **Backend**: Java 17, Spring Boot 3.3.4
- **Database**: MySQL
- **ORM**: Spring JPA
- **Email**: Spring Mail
- **Frontend**: Thymeleaf, Bootstrap 5
- **Build Tool**: Maven

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/madhu/BirthdayEmail/
│   │       ├── controller/      # REST and web controllers
│   │       ├── entity/          # JPA entities
│   │       ├── repository/      # Database repositories
│   │       ├── scheduler/       # Cron job scheduler
│   │       └── service/         # Business logic services
│   └── resources/
│       ├── application.properties # Application configuration
│       └── templates/           # Thymeleaf HTML templates
└── pom.xml
```

## Setup Instructions

### Clone the repository

- git clone https://github.com/madhuu11/Email-Automation.git
- cd email-automation

### Configure database
Edit application.properties and set your MySQL connection details:
- spring.datasource.username=your_username
- spring.datasource.password=your_password

### Configure email settings
Add your email server details to application.properties:
- spring.mail.username=your_email@gmail.com
- spring.mail.password=your_app_password

Note: get an app password if using Gmail with 2FA. https://myaccount.google.com/apppasswords

### Build and run
./mvnw clean install
./mvnw spring-boot:run

## Usage

- **Access the web interface**
  Open your browser and navigate to http://localhost:8080/

- **Add users**
  Navigate to "Add New User" and fill in the user's name, email, and date of birth.

- **View all users**
  See all registered users with their information.

- **Check today's birthdays**
  View users who have birthdays today and send them greetings.

- **Add Templates**
  Navigate to "Add New Template" and fill in the template name, subject, and message.

- **View all templates**
  See all templates with mail information.

- **Scheduler configuration**
  The application is configured to check for birthdays every minute by default. You can adjust the scheduling by modifying the cron expression in JobSchedular.java.

## REST API Endpoints

- GET /birthday-email/send
- GET /birthday-email/allUserDetails
- POST /birthday-email/add-user
- GET /birthday-email/user/birthdayToday
- GET /email-template/allTemplateDetails
- POST /email-template/addEmailTemplate

## Contact

For questions or suggestions, please open an issue on the repository.