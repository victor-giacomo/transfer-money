# Transfer-Money Project

## Description:

This project allows money transfers between users. 
Upon receiving a transfer, the user should be notified by email, which is sent asynchronously and makes up to 5 new attempts in case of failure.

## How to run:

    * With Docker: 
    docker compose up
    Requirements: 
        - Docker installed
    
    * With Maven:
    mvn spring-boot:run (on each module)
    Requirements: 
        - JDK 17+ installed
        - Maven installed  
        - Kafka running (default port)
        - Zookepper running (default port)

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