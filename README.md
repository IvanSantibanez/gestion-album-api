# API REST para Gestión de Álbumes y Láminas

API desarrollada en Java Spring Boot para la gestión de colecciones de láminas de álbumes. Permite a los usuarios crear, consultar, actualizar y eliminar álbumes y láminas, así como gestionar el estado de sus colecciones, ver láminas faltantes, repetidas y generar reportes consolidados.

## Características principales

- Gestión de álbumes y láminas (CRUD)
- Carga masiva de láminas
- Listado de láminas faltantes y repetidas
- Reporte consolidado por álbum
- Validaciones de datos y respuestas estructuradas en formato JSON
- Documentación interactiva con Swagger/OpenAPI

## Requisitos previos

- Java 17 o superior
- MySQL
- Maven

## Instalación y ejecución

1. Clona este repositorio.
2. Configura la conexión a tu base de datos en `src/main/resources/application.properties`.
3. Ejecuta los scripts de poblado para crear los datos base.
4. Compila y ejecuta la aplicación con Maven:

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080/` por defecto.

## Documentación interactiva (Swagger UI)

Puedes explorar y probar los endpoints de la API desde la documentación generada automáticamente por Swagger/OpenAPI en:

`http://localhost:8080/swagger-ui/index.html`

## Poblado para base de datos

Ejemplo de datos base para álbumes y láminas:

```sql
INSERT INTO album (nombre, imagen, fecha_lanzamiento, tipo_laminas, activo) VALUES
('Álbum Mundial 2022', 'https://ejemplo.com/album2022.jpg', '2022-11-01', 'Deportivas', true),
('Álbum Animales', 'https://ejemplo.com/album_animales.jpg', '2021-05-15', 'Educativas', true);

INSERT INTO lamina (nombre, imagen, numero, album_id) VALUES
('Messi', 'https://ejemplo.com/messi.jpg', 10, 1),
('Mbappé', 'https://ejemplo.com/mbappe.jpg', 7, 1),
('León', 'https://ejemplo.com/leon.jpg', 1, 2),
('Tigre', 'https://ejemplo.com/tigre.jpg', 2, 2);
```

## Métodos de la API

| Método | Ruta                             | Descripción                             |
| ------ | -------------------------------- | --------------------------------------- |
| POST   | /api/albumes                     | Crear un nuevo álbum                    |
| GET    | /api/albumes                     | Obtener todos los álbumes               |
| GET    | /api/albumes/{id}                | Obtener un álbum por ID                 |
| PUT    | /api/albumes/{id}                | Actualizar un álbum existente           |
| DELETE | /api/albumes/{id}                | Eliminar un álbum por ID                |
| POST   | /api/laminas/masivo              | Carga masiva de láminas                 |
| GET    | /api/laminas/faltantes/{albumId} | Listar láminas faltantes de un álbum    |
| GET    | /api/laminas/repetidas/{albumId} | Listar láminas repetidas de un álbum    |
| GET    | /api/laminas/reporte/{albumId}   | Obtener reporte consolidado de un álbum |

## Ejemplos de body para crear un álbum

```json
{
  "nombre": "Álbum Mundial 2026",
  "totalLaminas": 100,
  "fechaLanzamiento": "2024-12-18T10:00:00.000+00:00"
}
```

## Ejemplo de body para carga masiva de láminas

```json
[
  {
    "numero": 10,
    "nombre": "Lionel Messi",
    "tipoLamina": "Especial",
    "album": { "id": 1 }
  },
  {
    "numero": 10,
    "nombre": "Lionel Messi",
    "tipoLamina": "Especial",
    "album": { "id": 1 }
  },
  {
    "numero": 7,
    "nombre": "Cristiano Ronaldo",
    "tipoLamina": "Normal",
    "album": { "id": 1 }
  }
]
```

## Orden de prueba en Postman

1. **POST** `/api/albumes` (Crear el álbum)
2. **POST** `/api/laminas/masivo` (Subir láminas)
3. **GET** `/api/albumes` (Ver todos los álbumes)
4. **GET** `/api/albumes/{id}` (Ver un álbum por ID)
5. **PUT** `/api/albumes/{id}` (Actualizar un álbum)
6. **DELETE** `/api/albumes/{id}` (Eliminar un álbum)
7. **GET** `/api/laminas/faltantes/{albumId}` (Ver láminas faltantes)
8. **GET** `/api/laminas/repetidas/{albumId}` (Ver láminas repetidas)m
9. **GET** `/api/laminas/reporte/{albumId}` (Ver el reporte final)

----
