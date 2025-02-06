# RainNotes Application

A Spring Boot application that provides a web-based note-taking system with user authentication. Users can create, edit, and manage their personal notes through a web interface.

## Features

### 1. **Note Management**

- Create personal notes with title and content
- View list of all user's notes
- Edit existing notes
- Delete notes
- Notes are private and user-specific

### 2. **Authentication & Security**

- User registration and login system
- Password encryption using BCryptPasswordEncoder
- Session-based authentication
- Input validation and error handling

## Technologies Used

**Java**- Core programming language

**Spring Boot**- Web application framework

**Spring Security**- Authentication and authorization

**Spring MVC**- Web layer implementation

**Spring Data JPA**- Database access

**Thymeleaf**- Server-side template engine

**Lombok**- Reduces boilerplate code

**PostgreSQL- Database**

**JUnit 5 & Mockito**- Unit and integration testing frameworks.

**HTML/CSS**- to create a beautiful user interface

**JavaScript**- for creating logic on the frontend

## API Endpoints

**Authentication**

- `GET /register`: Display registration form
- `POST /register`: Create new user account
- `GET /login`: Display login form
- `POST /login`: Authenticate user

**Note Management**

- `GET /list`: Display all notes for logged-in user
- `POST /createNote`: Create a new note
- `GET /editPages`: Display edit form for notes
- `POST /edit`: Update existing note
- `POST /delete`: Delete a note

## Security Features

- **Password constraints:**
  - Minimum length: 6 characters
  - Maximum length: 60 characters

- **Username constraints:**
  - Maximum length: 50 characters
  - Must be unique
  - Cannot be empty


- Secure password storage using BCrypt hashing
- Authentication required for all note operations

**Error Handling**

- Custom exceptions for common scenarios:
  - FailedRegistrationException
  - FailedLoginException
  - FailedCreateNoteException
- Validation for empty note titles
- Username uniqueness validation
- Authentication failure handling

## Future Enhancements

- Note sharing functionality
- Rich text editor support
- Note categories/tags
- Search functionality
- User profile management
- Password reset functionality














