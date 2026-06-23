# Orders Service (Backend de Gestión de Pedidos)

Este proyecto es una API REST desarrollada con **Java 25** y **Spring Boot 4.1.0** para la gestión de sucursales, productos y pedidos. El diseño arquitectónico implementa los principios de la **Arquitectura Hexagonal (Puertos y Adaptadores)**, asegurando un bajo acoplamiento, alta cohesión y una clara separación de las reglas de negocio respecto a los detalles tecnológicos externos (frameworks, bases de datos, APIs).

---

## 🚀 Tecnologías y Herramientas

*   **Java 25** (Últimas características de la plataforma Java)
*   **Spring Boot 4.1.0** (Starter Web, Starter Data JPA, Validation)
*   **H2 Database** (Base de datos en memoria para pruebas rápidas y desarrollo)
*   **Project Lombok** (Reducción de código repetitivo)
*   **Maven** (Gestor de dependencias y construcción)

---

## 🏛️ Arquitectura del Proyecto

El proyecto sigue una estructura basada en **Arquitectura Hexagonal**, dividida en tres capas principales:

```
                  ┌─────────────────────────────────────┐
                  │           INFRASTRUCTURE            │
                  │  (REST Controllers, JPA Repos, DTOs)│
                  │                                     │
                  │      ┌───────────────────────┐      │
                  │      │      APPLICATION      │      │
                  │      │  (Ports & Services)   │      │
                  │      │                       │      │
                  │      │      ┌─────────┐      │      │
                  │      │      │ DOMAIN  │      │      │
                  │      │      │ (Models)│      │      │
                  │      │      └─────────┘      │      │
                  │      └───────────────────────┘      │
                  └─────────────────────────────────────┘
```

1.  **Domain (Dominio):** El núcleo del sistema. Contiene los modelos de negocio puramente en Java (sin dependencias de frameworks) y las excepciones de dominio.
2.  **Application (Aplicación):** Define los casos de uso del sistema.
    *   **Ports (Puertos):** Interfaces que definen los contratos para la comunicación externa.
        *   `in` (Input Ports): Casos de uso (Interfaces de Servicios) llamados por la infraestructura de entrada (e.g., REST Controllers).
        *   `out` (Output Ports): Interfaces de persistencia o servicios externos llamados por la aplicación.
    *   **Services (Servicios):** Implementación de la lógica de negocio y los casos de uso, interactuando con los puertos de salida.
3.  **Infrastructure (Infraestructura):** Implementación de los adaptadores de entrada y salida.
    *   `adapter/in/rest`: Controladores REST y sus respectivos DTOs de petición y respuesta.
    *   `adapter/out/persistence`: Entidades de base de datos JPA, repositorios Spring Data JPA y los adaptadores que implementan los puertos de salida.
    *   `config`: Configuraciones de Beans y siembra de base de datos (Database Seeding).

---

## 📂 Estructura de Directorios

A continuación se detalla la estructura del proyecto bajo la raíz del paquete principal `com.alexhiz.hexagonal.orders`:

```text
orders/
│
├── domain/                                     # Capa del Dominio (Core)
│   ├── exception/                              # Excepciones de negocio
│   │   ├── BranchNotFoundException.java
│   │   ├── OrderNotFoundException.java
│   │   └── ProductNotFoundException.java
│   └── model/                                  # Entidades puras del dominio
│       ├── Branch.java
│       ├── Order.java
│       ├── OrderItem.java
│       └── Product.java
│
├── application/                                # Capa de Aplicación (Casos de Uso)
│   ├── port/                                   # Puertos (Contratos)
│   │   ├── in/                                 # Puertos de Entrada
│   │   │   ├── branch/                         # Casos de uso de Sucursales
│   │   │   ├── order/                          # Casos de uso de Pedidos
│   │   │   └── product/                        # Casos de uso de Productos
│   │   └── out/                                # Puertos de Salida (Persistencia)
│   │       ├── BranchRepositoryPort.java
│   │       ├── OrderRepositoryPort.java
│   │       └── ProductRepositoryPort.java
│   └── services/                               # Implementación de Casos de Uso
│       ├── BranchService.java
│       ├── OrderService.java
│       └── ProductService.java
│
└── infrastructure/                             # Capa de Infraestructura (Adaptadores)
    ├── adapter/
    │   ├── in/                                 # Adaptadores de Entrada
    │   │   └── rest/                           # Controlador API REST
    │   │       ├── dto/                        # DTOs de Request y Response
    │   │       ├── BranchController.java
    │   │       ├── OrderController.java
    │   │       └── ProductController.java
    │   └── out/                                # Adaptadores de Salida
    │       └── persistence/                    # Persistencia con JPA e Hibernate
    │           ├── branch/
    │           ├── order/
    │           └── product/
    └── config/                                 # Configuración e inicialización
        ├── BeanConfig.java                     # Declaración manual de Beans de servicios
        └── DatabaseSeeder.java                 # Semilla de datos para pruebas
```

---

## 🛠️ Configuración y Ejecución

### Requisitos previos

*   **Java JDK 25** instalado.
*   **Maven** instalado (o usando el wrapper `mvnw` incluido).

### Pasos para ejecutar localmente

1.  Clona el repositorio.
2.  Ejecuta el siguiente comando para iniciar el servidor de desarrollo de Spring Boot:
    ```bash
    ./mvnw spring-boot:run
    ```
    *(En Windows, si no usas Bash, puedes usar `.\mvnw.cmd spring-boot:run`)*

3.  La aplicación se levantará por defecto en el puerto **`8081`** (configurado en `src/main/resources/application.yaml`).

### Base de datos H2 en memoria

Para facilitar las pruebas, la base de datos se crea en memoria y se puede inspeccionar mediante la consola web de H2:

*   **URL de la consola:** [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
*   **JDBC URL:** `jdbc:h2:mem:inventorydb`
*   **User Name:** `sa`
*   **Password:** `password`

---

## 📖 Documentación de la API (Swagger / OpenAPI)

La documentación interactiva y detallada de los endpoints de la API se genera automáticamente mediante **springdoc-openapi**:

*   **Swagger UI URL:** [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
*   **OpenAPI JSON:** [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

### Ejemplo de Configuración/Anotaciones

Para documentar los controladores y los objetos de petición (`dtoRequest`), se utilizaron anotaciones OpenAPI 3. A continuación se presenta un ejemplo de cómo se integraron usando **Java Records**:

#### 1. DTO de Petición (`ProductRequest.java`)
Se usa `@Schema` directamente en los componentes del `record` para documentar la estructura y proporcionar ejemplos reales:
```java
@Schema(description = "Modelo de petición para registrar un nuevo producto")
public record ProductRequest(
    @Schema(description = "Descripción o nombre del producto", example = "Monitor Dell 24 pulgadas")
    String description,

    @Schema(description = "Precio unitario del producto", example = "180.00")
    Double price
) {}
```

#### 2. Controlador (`ProductController.java`)
Se utiliza `@Tag` para agrupar endpoints, `@Operation` para describir la acción del endpoint y `@ApiResponse` para detallar las respuestas del servidor. Los accesores del `record` se invocan sin el prefijo `get` (por ejemplo, `productRequest.description()`):
```java
@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Endpoints para la gestión de productos")
public class ProductController {

    private final CreateProductUseCase createProductUseCase;

    @PostMapping
    @Operation(summary = "Crear un nuevo producto", description = "Registra un nuevo producto en el catálogo de la aplicación")
    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente", content = @Content(schema = @Schema(implementation = ProductResponse.class)))
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = Product.builder()
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        // ...
    }
}
```

---

## 🌱 Datos de Prueba Sembrados (Seeding)

Al iniciar la aplicación, la clase `DatabaseSeeder` inserta automáticamente los siguientes registros iniciales para pruebas:

*   **Sucursales:**
    *   `Sucursal Norte`
    *   `Sucursal Sur`
*   **Productos:**
    *   `Laptop Lenovo` (Precio: $1200.00)
    *   `Mouse Logitech` (Precio: $25.00)
    *   `Teclado Mecanico` (Precio: $75.00)

---

## 🔌 API Endpoints Principales

Todos los endpoints usan la base `/api/v1`.

### 1. Pedidos (Orders)

#### **Crear Pedido**
*   **Método:** `POST`
*   **Endpoint:** `/api/v1/orders`
*   **Cuerpo de la Petición (Request JSON):**
    ```json
    {
      "branchId": "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d", // UUID de la sucursal
      "items": [
        {
          "productId": "a5e9dc32-2d88-4db8-9e5b-1b2c3d4e5f6g", // UUID del producto
          "quantity": 2
        }
      ]
    }
    ```
*   **Respuesta Exitosa (201 Created):**
    ```json
    {
      "id": "e457fcd1-12c8-47bc-ad7e-07a829e1eb1c",
      "branchId": "9b1deb4d-3b7d-4bad-9bdd-2b0d7b3dcb6d",
      "orderDate": "2026-06-22T11:45:00",
      "total": 2400.00,
      "items": [
        {
          "id": "11ea2b90-1c3c-4b3f-a35c-cd14757279c6",
          "productId": "a5e9dc32-2d88-4db8-9e5b-1b2c3d4e5f6g",
          "productDescription": "Laptop Lenovo",
          "quantity": 2,
          "unitPrice": 1200.00
        }
      ]
    }
    ```

#### **Obtener Pedido por ID**
*   **Método:** `GET`
*   **Endpoint:** `/api/v1/orders/{id}`
*   **Respuesta Exitosa (200 OK):** Retorna el mismo formato que la creación del pedido.

---

### 2. Sucursales (Branches)

#### **Crear Sucursal**
*   **Método:** `POST`
*   **Endpoint:** `/api/v1/branches`
*   **Cuerpo de la Petición (Request JSON):**
    ```json
    {
      "name": "Sucursal Oeste"
    }
    ```

---

### 3. Productos (Products)

#### **Crear Producto**
*   **Método:** `POST`
*   **Endpoint:** `/api/v1/products`
*   **Cuerpo de la Petición (Request JSON):**
    ```json
    {
      "description": "Monitor Dell 24 pulgadas",
      "price": 180.00
    }
    ```
