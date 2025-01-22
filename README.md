# ForoHub

ForoHub es una aplicación Spring Boot para gestionar temas en un foro. Proporciona puntos finales para crear, actualizar, recuperar y eliminar temas.

## Tecnologías Utilizadas
- Java
- Spring Boot
- Spring Security
- JPA (Java Persistence API)
- Maven
- JWT (JSON Web Token)

## Para empezar

### Prerequisitos

- Java 17 o mayor
- Maven 3.6.0 o mayor
- Insomnia 10.3.0 o mayor
- MySQL 8.0.41.0 o mayor

### Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/starman-ai/ForoHubProyecto.git
    cd forohub
    ```

2. Construye el proyecto:
    ```sh
    mvn clean install
    ```

3. Corre la aplicación:
    ```sh
    mvn spring-boot:run
    ```

La aplicación inicializará en `http://localhost:8081`.
Se recomienda utilizar `Insomnia` para probar el CRUD.

## API Endpoints

### Autenticación

- **POST** `/login`
    - Cuerpo del request:
        ```json
        {
            "username": "your-username",
            "password": "your-password"
        }
        ```
    - Respuesta:
        ```json
        {
            "token": "your-jwt-token"
        }
        ```

### Topics

- **GET** `/topics`
    - Recupera todos los topics.

- **GET** `/topics/{id}`
    - Recupera un topic por su ID.

- **POST** `/topics`
    - Crea un nuevo topic.
    - Cuerpo del Request:
        ```json
        {
            "title": "Nuevo Topic",
            "message": "Este es un nuevo topic",
            "status": "open",
            "author": "Autor",
            "course": "Curso"
        }
        ```

- **PUT** `/topics/{id}`
    - Actualiza un topic por su ID.
    - Cuerpo del Request:
        ```json
        {
            "title": "Updated Topic",
            "message": "Este es un topic actualizado",
            "status": "closed",
            "author": "Autor Actualizado",
            "course": "Curso Actualizado"
        }
        ```

- **DELETE** `/topics/{id}`
    - Elimina topic por su ID.

## Seguridad

La aplicación utiliza JWT para la autenticación. Para acceder a los endpoints, es necesario incluir el token JWT en la cabecera `Authorization` de las peticiones.
