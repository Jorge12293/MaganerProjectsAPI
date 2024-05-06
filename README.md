# Proyecto para manejar estados 

Este es un proyecto contempla para la gestion de proyectos y tareas

## Requisitos Previos

java version "17.0.10" 2024-01-16 LTS
Java(TM) SE Runtime Environment (build 17.0.10+11-LTS-240)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.10+11-LTS-240, mixed mode, sharing)

## El proyecto esta configurado por defecto con la base de datos H2


Al levantar el proyecto se ejecuta los siguientes scripts para crear la base y crear data de prueba

spring.datasource.schema=classpath:db/schema.sql
spring.datasource.data=classpath:db/data.sql

# Puerto por defecto
server.port=9080