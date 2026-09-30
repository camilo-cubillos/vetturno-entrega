# VetTurno

VetTurno es una API backend para la Veterinaria Huellitas.

El proyecto busca organizar el registro de propietarios, mascotas, veterinarios y citas, evitando cruces de horario y manteniendo la información almacenada de forma persistente.

## Tecnologías

- Java 17
- Spring Boot
- Maven
- Spring Data JPA
- MySQL
- Spring Security
- Bean Validation

## Cómo ejecutar

1. Tener Java 17 instalado.
2. Tener MySQL en ejecución.
3. Crear una base de datos llamada `vetturno`.
4. Configurar la variable de entorno `DB_PASSWORD`.
5. Ejecutar:

```bash
./mvnw spring-boot:run