# 📄 Documentación Proyecto Final - PMDM

Rellena este documento e incluyelo dentro de la carpeta `/docs` del repositorio.

## 1. Información del Alumno/a

- **Individual/Grupo**: [Individual]
- **Nombre y Apellidos: alumno-1**: [Pedro ]
- **Nombre y Apellidos: alumno-2**: [Guerrero Blanco]
- **Nombre del Proyecto**: [PideYa]
- **URL del Repositorio (Privado)**: [URL del repo en Github]

## 2. Descripción del Proyecto

PideYa es una aplicación móvil de gestión digital de pedidos y reservas para negocios de restauración, diseñada para reducir colas y aglomeraciones en situaciones donde se produce un pico puntual de demanda (festivales, cafeterías en edificios administrativos, estadios durante el descanso, food trucks, etc.).

El problema principal que resuelve es que en estos contextos los clientes pierden mucho tiempo esperando, y los locales sufren colapso operativo, lo que puede degradar la calidad del servicio y del producto. Con PideYa, el usuario puede hacer el pedido y pagar desde el móvil, y recibir un seguimiento del estado del pedido hasta que se le avisa de que está listo. En ese momento, el cliente solo tiene que acercarse al local y recogerlo, evitando esperas innecesarias.

La aplicación va dirigida a:

Clientes que quieren comprar comida/bebida sin perder tiempo en colas.

Negocios de restauración que necesitan mejorar la gestión de pedidos y reservas en momentos de alta demanda.

Su temática principal es la digitalización del proceso de pedido/reserva y recogida, enfocándose en una experiencia rápida y controlada, diferenciándose de apps como Just Eat, Glovo o Uber Eats porque no requiere repartidores: el cliente recoge su pedido directamente en el local (normalmente cercano).

## 3. Características Principales (Features)

- ### 1: Registro de usuario

El usuario puede registrarse introduciendo nombre, email, contraseña y cuenta bancaria, para poder usar la app y asociar pagos al perfil.

- ### Feature 2: Inicio de sesión

El usuario puede iniciar sesión con email + contraseña, con validación y mensajes de error si los datos son incorrectos.

- ### Feature 3: Ver restaurantes disponibles

El usuario puede ver una lista de restaurantes disponibles, viendo al menos nombre y tipo de comida, y acceder a la ficha/detalles del restaurante.

- ### Feature 4: Hacer reserva

El usuario puede reservar seleccionando una hora, y el sistema valida disponibilidad antes de confirmarla.

- ### Feature 5: Realizar pedido con pago

El usuario puede consultar la oferta del restaurante, hacer un pedido y pagarlo desde la app.

- ### Feature 6: Seguimiento del pedido en tiempo real

El usuario puede ver el estado del pedido actualizado, hasta recibir el aviso de “listo para recoger”.

## 4. Diagrama de Flujo de Navegación

flowchart TD
%% --- AUTENTICACIÓN ---
A((Inicio)) --> B[Login]
A --> C[Registro]

    %% --- ENTRADA PRINCIPAL ---
    B --> D[Restaurantes en tu zona]
    C --> D

    %% --- RESTAURANTES ---
    D --> E[Detalle restaurante / Carta]

    %% --- PEDIDO ---
    E --> F[Añadir productos]
    F --> G[Carrito]
    G --> H[Confirmar pedido]
    H --> I[Pago]

    %% --- SEGUIMIENTO DEL PEDIDO ---
    I --> J[Estado: Pedido aceptado]
    J --> K[Estado: En camino]
    K --> L[Estado: Disfruta de la comida]
    L --> D

    %% --- RESERVAS ---
    E --> R[Reserva]
    R --> D

    %% --- PERFIL / CUENTA (accesible desde pantallas principales) ---
    D --> P[Perfil]
    E --> P
    G --> P
    J --> P
    K --> P
    L --> P

    %% --- VOLVER AL HOME DESDE PERFIL ---
    P --> D

## 5. Casos de Uso

[Debes definir al menos 10 casos de uso. Completa la siguiente tabla:].

| ID    | Caso de Uso         | Descripción                               | Prioridad |
| :---- | :------------------ | :---------------------------------------- | :-------- |
| UC-01 | Login de usuario    | El usuario accede con sus credenciales... | Alta      |
| UC-02 | Registro de usuario | El usuario crea una nueva cuenta...       | Alta      |
| UC-03 | ...                 | ...                                       | ...       |
| ...   | ...                 | ...                                       | ...       |

## 6. Arquitectura Técnica

[Describe brevemente cómo has implementado las capas solicitadas en el enunciado]

- **Capa de Presentación (UI)**: [Jetpack Compose, ViewModels, etc.]
- **Capa de Negocio**: [Uso de UseCases, Repositorios]
- **Capa de Datos**: [Room, Retrofit, DataSources]

## 7. Persistencia y Red

- **Persistencia Local**: [Indica qué datos guardas en Room y qué en SharedPreferences/DataStore]
- **API REST**: [Indica la URL de la API que consumes y qué datos obtienes]

## 8. Planificación de Entregas

[Indica qué funcionalidades o tareas te comprometes a entregar en cada fase]

### 📅 Entrega Parcial 1 (13 de Febrero)

- **Hito 1**: [Setup del proyecto, arquitectura base, etc.]
- **Funcionalidades**: [Login/Registro, Pantalla Home...]
- **Otras tareas**: [Navegación base, modelos iniciales...]

### 📅 Entrega Parcial 2 (27 de Febrero)

- **Hito 2**: [Integración de Red/Persistencia, etc.]
- **Funcionalidades**: [Listado de items desde API, guardado en favoritos...]
- **Otras tareas**: [Mappers, Repositorios reales...]

### 📅 Entrega Final (12 de Marzo)

- **Hito Final**: [Pulido de UI, Internalización, etc.]
- **Funcionalidades**: [Ajustes finales, perfil de usuario...]
- **Otras tareas**: [Soporte Multi-idioma (ES/EN), APK generado...]

---

> [!IMPORTANT]
> Recuerda que en cada entrega debes crear un **Release** en Github.
