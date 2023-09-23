# Transfer-Money Service

## How to run:

    - With Docker: 
    docker run -p 8080:8080 victorgiacomo/transfer-money
    Requirements: Docker installed
    
    - With Maven:
    mvn spring-boot:run
    Requirements: JDK 8+ and Maven installed

### Reference Documentation
http://localhost:8080/swagger-ui/index.html

### H2 Console
http://localhost:8080/h2-console

###### TO DO:
- Authentication with Spring Security
- Customize documentation
- CPF and CNPJ validation
- Encript id values in body
- Create microservice to isolate external services