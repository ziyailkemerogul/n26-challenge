# Use an official OpenJDK image as the base
FROM openjdk:17-jdk-slim

# Install necessary tools
RUN apt-get update && apt-get install -y \
    maven \
    && rm -rf /var/lib/apt/lists/*

# Set the working directory
WORKDIR /app

# Copy the project files into the container
COPY . .

# Install Maven dependencies
RUN mvn clean install -DskipTests

# Command to run the tests
CMD ["mvn", "test"]