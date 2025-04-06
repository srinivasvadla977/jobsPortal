# Start from a lightweight Java image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file (adjust the jar name if different)
#COPY target/jobsPortal.jar app.jar
#COPY jobsPortal.jar /app/jobsPortal.jar
COPY target/jobsPortal-0.0.1-SNAPSHOT.jar /app/jobsPortal.jar

#RUN apt-get update && apt-get install -y mysql-client


# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "jobsPortal.jar"]
