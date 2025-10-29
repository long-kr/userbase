# Copilot Instructions for userbase

## Project Overview
- **userbase** is a Java Spring Boot application for user management, organized by domain-driven design principles.
- Main code is under `src/main/java/com/userbase/user/` with subfolders for `controller`, `service`, `repository`, `entity`, `dto`, `error`, and `utils`.
- The entry point is `UserbaseApplication.java`.

## Architecture & Patterns
- **Controllers** (e.g., `UserController.java`) handle HTTP requests and delegate to services.
- **Services** (e.g., `UserService`, `UserServiceImpl`) contain business logic. Use interfaces for abstraction.
- **Repositories** (e.g., `UserRepository.java`) extend Spring Data JPA interfaces for DB access. Custom queries use method naming conventions.
- **Entities** (e.g., `UserEntity.java`, `RoleEntity.java`) map to database tables.
- **DTOs** (e.g., `UserDto.java`, `ResponseDto.java`) are used for API input/output.
- **Error Handling**: Centralized via `GlobalExceptionhandler.java` and custom exception/response classes in `error/`.
- **Validation**: Custom validators in `utils/` (e.g., `EnumValidatorImpl.java`).

## Developer Workflows
- **Build**: Use `./mvnw clean install` (or `mvnw.cmd` on Windows) from the project root.
- **Run**: `./mvnw spring-boot:run` or run `UserbaseApplication.java` from your IDE.
- **Test**: `./mvnw test` runs tests in `src/test/java/`.
- **Config**: Main config in `src/main/resources/application.properties`.

## Conventions & Tips
- Use constructor injection for services.
- Keep business logic out of controllers.
- Use DTOs for all API boundaries; never expose entities directly.
- Prefer `Optional<T>` for repository query results.
- Exception handling should use project error classes, not raw exceptions.
- Place new domain logic in the appropriate subpackage under `user/`.

## Integration Points
- Uses Spring Data JPA for persistence (see `UserRepository.java`).
- Application configures beans in `config/`.
- Static resources/templates go in `src/main/resources/static` and `templates`.

## Example: Adding a New User Feature
1. Add fields to `UserEntity.java` and update DB if needed.
2. Add to `UserDto.java` and validation logic in `utils/`.
3. Add service methods in `UserService`/`UserServiceImpl`.
4. Expose via `UserController.java`.
5. Add/extend tests in `src/test/java/`.

---
_If any conventions or workflows are unclear, please ask for clarification or suggest updates to this file._
