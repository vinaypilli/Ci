# Use an official OpenJDK runtime as the base image
FROM adoptopenjdk:11-jre-hotspot

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the target directory to the container's working directory
COPY target/spring-boot-jwt-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose the port on which your Spring Boot app listens inside the container (use a different port, e.g., 8081)
EXPOSE 8081

# Set environment variables for MySQL and other configurations
ENV spring.datasource.url=jdbc:mysql://mysql-container:3306/notesdb?createDatabaseIfNotExist=true&sessionVariables=sql_mode='NO_ENGINE_SUBSTITUTION'&jdbcCompliantTruncation=false
ENV spring.datasource.username=root
ENV spring.datasource.password=root
ENV jwt.secret=techgeeknext
ENV spring.mail.host=smtp.gmail.com
ENV spring.mail.username=ceoconnect@teamcomputers.com
ENV spring.mail.password=bahzpkeerlaovlbh
ENV gmail.spring.mail.properties.mail.smtp.auth=true
ENV spring.mail.properties.mail.smtp.socketFactory.port=465
ENV spring.mail.properties.mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
ENV spring.mail.properties.mail.smtp.socketFactory.fallback=false
ENV server.port=8081

# Specify the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
