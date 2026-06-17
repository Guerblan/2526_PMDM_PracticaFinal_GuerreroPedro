# PideYa

## Descripcion del proyecto
PideYa es una aplicacion Android desarrollada en Kotlin con Jetpack Compose para gestionar pedidos en eventos con recogida en establecimiento.

La aplicacion permite que el usuario inicie sesion o se registre, consulte eventos disponibles, vea los establecimientos de cada evento, seleccione productos, confirme pedidos y consulte su historial.

## Funcionalidades principales
- Splash screen de inicio.
- Login y registro de usuario.
- Navegacion entre pantallas con Navigation Compose.
- Listado de eventos disponibles.
- Visualizacion de establecimientos por evento.
- Visualizacion de productos por establecimiento.
- Carrito de compra y confirmacion de pedido.
- Historial de pedidos.
- Perfil basico de usuario.
- Cambio de idioma entre espanol e ingles.

## Tecnologias utilizadas
- Kotlin
- Jetpack Compose
- Material 3
- Navigation Compose
- Room
- SharedPreferences

## Estructura del proyecto
- `app/`: codigo principal de la aplicacion Android.
- `docs/`: documentacion tecnica del proyecto.
- `gradle/`: configuracion del sistema de build.

## Instalacion y ejecucion
Para abrir el proyecto:

```bash
Abrir la carpeta del proyecto en Android Studio
```

Para compilar en modo debug:

```bash
./gradlew assembleDebug
```

Para compilar en modo release:

```bash
./gradlew assembleRelease
```

APK generados:
- `app/build/outputs/apk/debug/app-debug.apk`
- `app/build/outputs/apk/release/app-release-unsigned.apk`

## Arquitectura
El proyecto mantiene una arquitectura por capas:
- Presentacion: pantallas Compose, `MainActivity`, navegacion y `ViewModel`.
- Dominio: modelos, repositorios y casos de uso.
- Datos: datasources, repositorios, mappers y persistencia local.

## Estado actual
La aplicacion se ha orientado a una version minima de `PideYa`, con un flujo centrado en eventos, establecimientos y pedidos locales, sin depender de servicios externos para su funcionamiento basico. El proyecto compila correctamente en `debug` y `release`.
