# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine
SHELL ["/bin/bash", "-c"]

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR del microservicio en el contenedor
COPY ${projectDir}/build/libs/*-SNAPSHOT.jar /app/app.jar

# Expone el puerto 8060
EXPOSE 8091

# Define el comando de inicio del contenedor
CMD ["java", "-jar", "app.jar"]
