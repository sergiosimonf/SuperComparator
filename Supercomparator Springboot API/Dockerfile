# Usa una imagen base de OpenJDK 17
FROM openjdk:17-slim

# Crear el usuario de la aplicación
RUN addgroup --system --gid 1000 super && adduser --system --uid 1000 super --ingroup super

# Crear directorios necesarios
RUN mkdir /logs /config /driver && chown super:super /logs /config /driver

# Instalar dependencias necesarias para GeckoDriver y Firefox
RUN apt-get update && apt-get install -y \
    wget \
    curl \
    gnupg \
    firefox-esr \
    && apt-get clean

# Descargar e instalar GeckoDriver
RUN wget -O /driver/geckodriver-v0.34.0-linux64.tar.gz https://github.com/mozilla/geckodriver/releases/download/v0.34.0/geckodriver-v0.34.0-linux64.tar.gz \
    && tar -xzf /driver/geckodriver-v0.34.0-linux64.tar.gz -C /driver \
    && chmod +x /driver/geckodriver \
    && rm /driver/geckodriver-v0.34.0-linux64.tar.gz

# Argumento jar con versión
ARG JAR_FILE

# Copiar el archivo JAR de la aplicación
COPY ${JAR_FILE} app.jar

# Cambiar al usuario 'super'
USER super:super

# Exponer el puerto en el que corre la aplicación
EXPOSE 8080

COPY config/* /config/

# Ejecutar el servicio en modo REST con logs
ENTRYPOINT ["java", "-Dlogging.config=/config/logback.xml", "-jar", "/app.jar"]