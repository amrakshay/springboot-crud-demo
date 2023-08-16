# Use the official OpenJDK 8 image as the base image
FROM openjdk:8-jdk

# Set environment variables for MySQL
ENV MYSQL_ROOT_PASSWORD=root1234
ENV MYSQL_DATABASE=test
ENV MYSQL_PWD=root1234

# Install required packages
RUN apt-get update && \
    apt-get install -y wget git vim && \
    rm -rf /var/lib/apt/lists/*

# Install Maven
ENV MAVEN_VERSION 3.6.3
RUN wget -q "https://archive.apache.org/dist/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz" -O /tmp/maven.tar.gz && \
    tar -xzf /tmp/maven.tar.gz -C /opt && \
    ln -s /opt/apache-maven-$MAVEN_VERSION /opt/maven && \
    rm -f /tmp/maven.tar.gz && \
    ln -s /opt/maven/bin/mvn /usr/local/bin/mvn

# Update repositories
RUN apt-get update -y

# Install MySQL 5.6
RUN apt-get install -y mariadb-server && \
    service mariadb start && \
    mysql -e "CREATE DATABASE springbootdb" && \
    mysql -e "GRANT ALL PRIVILEGES ON springbootdb.* TO 'root'@'localhost' IDENTIFIED BY 'root'"

# Install Vim editor
RUN apt-get update && \
    apt-get install -y vim

RUN mkdir /app

# Set the working directory
WORKDIR /app

# Clone the repository
RUN git clone https://github.com/amrakshay/springboot-crud-demo.git && \
    cd springboot-crud-demo

WORKDIR /app/springboot-crud-demo

RUN echo 'spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb' > src/main/resources/application.properties 

# Write the content to the application.properties file
RUN echo 'spring.datasource.url=jdbc:mysql://localhost:3306/springbootdb' >> src/main/resources/application.properties \
    && echo 'spring.datasource.username=root' >> src/main/resources/application.properties \
    && echo 'spring.datasource.password=root' >> src/main/resources/application.properties \
    && echo 'spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect' >> src/main/resources/application.properties \
    && echo 'spring.jpa.hibernate.ddl-auto=update' >> src/main/resources/application.properties


# Start MySQL service and execute a script or command if needed
CMD service mariadb start && mvn spring-boot:run

