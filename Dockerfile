FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Run tests during container runtime so we can mount volumes when running
# Using CMD to allow docker run to override arguments if needed
CMD ["mvn", "-B", "test"]