# Use a Java 11 base image
FROM openjdk:11-jdk-slim

# Install Maven
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Run the tests
CMD ["mvn", "clean", "install"]