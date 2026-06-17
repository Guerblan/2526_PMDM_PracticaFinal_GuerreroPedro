# LaJuani

## Descripcion del proyecto
LaJuani es una aplicacion Android desarrollada en Kotlin con Jetpack Compose para la gestion simulada de pedidos de comida a domicilio.

La aplicacion permite que el usuario se registre o inicie sesion, consulte un catalogo de restaurantes, vea los productos de cada menu, anada productos al carrito y gestione pedidos. Tambien incluye cambio de idioma entre espanol e ingles y persistencia local de datos.

## Funcionalidades principales
- Splash screen de inicio.
- Login y registro de usuario.
- Navegacion entre pantallas con Navigation Compose.
- Listado de restaurantes por categorias.
- Visualizacion del menu de cada restaurante.
- Carrito de compra con control de productos.
- Confirmacion, pago y seguimiento de pedidos.
- Historial de pedidos.
- Valoracion de restaurantes con estrellas.
- Cambio de idioma entre espanol e ingles.

## Documentacion tecnica
### Tecnologias utilizadas
- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Room
- Firebase Authentication
- SharedPreferences

### Arquitectura
El proyecto sigue una arquitectura por capas inspirada en la propuesta de Google para Android:
- Capa de presentacion: pantallas Compose, `MainActivity`, navegacion y `ViewModel`.
- Capa de negocio: casos de uso que encapsulan la logica de la aplicacion.
- Capa de datos: repositorios, fuentes de datos, mapeadores y persistencia local.

### Estructura general
- `app/src/main/java/com/pedro/pideyaapp/ui`: interfaz, navegacion, componentes, estados y temas.
- `app/src/main/java/com/pedro/pideyaapp/domain`: modelos, repositorios y casos de uso.
- `app/src/main/java/com/pedro/pideyaapp/data`: implementaciones de repositorios, datasource, mappers y clases de acceso a datos.
- `app/src/main/res`: recursos graficos, temas y cadenas en varios idiomas.

### Persistencia
La aplicacion usa dos mecanismos principales de persistencia:
- `SharedPreferences` para idioma, categoria seleccionada y datos basicos de sesion.
- `Room` para carrito, pedidos y valoraciones de restaurantes.

### Autenticacion
La autenticacion se ha preparado con Firebase Authentication. Si Firebase no estuviera disponible o configurado, la aplicacion controla ese caso y muestra el mensaje correspondiente.

### Compilacion y ejecucion
Para ejecutar el proyecto:
1. Abrir el proyecto en Android Studio.
2. Sincronizar Gradle.
3. Ejecutar la configuracion `app` sobre un emulador o dispositivo Android.

## Autor
Pedro Guerrero
