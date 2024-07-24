# Police and Thieves

## Descripción

"Police and Thieves" es un juego en el que los jugadores pueden controlar personajes de tipo Policía o Ladrón en un tablero generado dinámicamente. El juego permite a los jugadores moverse por el tablero, interactuar con tesoros y jugar en un entorno configurado con Spring Boot y WebSocket.

## Arquitectura

La arquitectura del proyecto está compuesta por varios componentes clave que interactúan para proporcionar la funcionalidad del juego. A continuación se detalla la arquitectura del sistema:

### Componentes Principales

1. **Front-End (Cliente)**
    - **Phaser**: Utilizado para el desarrollo de juegos en 2D. Maneja la representación gráfica del juego y las interacciones del usuario.
    - **React**: Proporciona la interfaz de usuario y la lógica del lado del cliente.

2. **Back-End (Servidor)**
    - **Spring Boot**: Framework principal para la implementación del servidor. Gestiona la lógica del negocio, los servicios y la comunicación con la base de datos y Redis.
    - **WebSocket**: Permite la comunicación en tiempo real entre el cliente y el servidor, permitiendo actualizaciones instantáneas del estado del juego.

### Paquetes y Clases

#### Paquete: `edu.escuelaing.arsw.service`

- **`Table`**: Gestor del tablero de juego. Se encarga de generar y mantener el estado del tablero, incluyendo la creación de caminos y la colocación de tesoros.
- **`TableService`**: Servicio que proporciona acceso a las funcionalidades del tablero, como la obtención y creación de tesoros.
- **`ActorService`**: Servicio que gestiona actores (Policías y Ladrónes), permitiendo la creación y movimiento de los actores en el tablero.

#### Paquete: `edu.escuelaing.arsw.service.actor`

- **`Actor`**: Clase base para todos los actores del juego. Contiene propiedades comunes como ID, posición y métodos para moverse.
- **`Police`**: Extiende de `Actor` y representa a un policía en el juego.
- **`Thief`**: Extiende de `Actor` y representa a un ladrón en el juego.

#### Paquete: `edu.escuelaing.arsw.service.factory`

- **`ActorFactory`**: Factory para la creación de actores. Utiliza el patrón Factory para instanciar actores de tipo Policía o Ladrón.
- **`TreasureFactory`**: Factory para la creación de tesoros. Utiliza reflexión para instanciar diferentes tipos de tesoros.

#### Paquete: `edu.escuelaing.arsw.service.treasure`

- **`Treasure`**: Clase base para todos los tesoros. Define propiedades comunes como puntuación y posición.
- **`GoldTreasure`**, **`SilverTreasure`**, **`DiamondTreasure`**: Clases específicas para diferentes tipos de tesoros, cada una con una puntuación distinta.

#### Paquete: `edu.escuelaing.arsw.controller`

- **`ActorController`**: Controlador REST para gestionar actores, permitiendo la creación, obtención y movimiento de actores.
- **`TableController`**: Controlador REST para gestionar el tablero y los tesoros, incluyendo la creación y obtención de tesoros.
- **`TicketController`**: Controlador REST para obtener tickets de sesión necesarios para la conexión de WebSocket.

#### Paquete: `edu.escuelaing.arsw.repository`

- **`TicketRepository`**: Repositorio que gestiona tickets de sesión utilizando Redis, facilitando la generación y validación de tickets.

#### Paquete: `edu.escuelaing.arsw.websocket`

- **`ActorDecoder`**: Decodificador de mensajes WebSocket para actores, convirtiendo mensajes JSON en objetos de Java.
- **`ActorEncoder`**: Codificador de mensajes WebSocket para actores, convirtiendo objetos de Java en JSON.

### Flujo de Datos

1. **Generación del Tablero**: Cuando se inicializa el tablero, el método `generarLaberinto()` se encarga de crear un laberinto utilizando el algoritmo de Retroceso Recursivo. Luego, se agregan caminos adicionales.

2. **Creación de Actores y Tesoros**: Los actores y tesoros son creados a través de las fábricas correspondientes (`ActorFactory` y `TreasureFactory`). Los actores se colocan en posiciones válidas en el tablero y los tesoros se distribuyen aleatoriamente.

3. **Comunicación en Tiempo Real**: Utilizando WebSocket, el servidor envía y recibe actualizaciones en tiempo real sobre el estado del juego, permitiendo a los jugadores moverse y interactuar con el entorno de manera instantánea.

4. **Interacción del Usuario**: El cliente, basado en Phaser y React, maneja la interfaz de usuario y las interacciones. Las solicitudes para mover actores y obtener información del tablero son enviadas al servidor a través de peticiones REST.

### Configuración

#### Configuración de Redis

`RedisConfig` configura la conexión con Redis usando `LettuceConnectionFactory`.

#### Configuración de Seguridad

`WebSecurityConfig` configura la seguridad web, permitiendo acceso a ciertas rutas y gestionando el inicio de sesión de usuarios en memoria.

#### Configuración de WebSocket

`WebSocketConfig` configura el soporte para WebSocket en la aplicación.

## Tests y Cobertura

El proyecto incluye una suite de pruebas para asegurar la calidad y el correcto funcionamiento de los diferentes componentes. A continuación se presenta un resumen de la cobertura de pruebas por paquete:

| Paquete                              | Cobertura de Instrucciones | Cobertura de Ramas |
|--------------------------------------|----------------------------|---------------------|
| edu.escuelaing.arsw.websocket        | 36%                        | 13%                 |
| edu.escuelaing.arsw.controller       | 0%                         | 0%                  |
| edu.escuelaing.arsw.service          | 88%                        | 97%                 |
| edu.escuelaing.arsw.repository       | 0%                         | 0%                  |
| edu.escuelaing.arsw.configuration    | 73%                        | N/A                 |
| edu.escuelaing.arsw.service.factory  | 89%                        | 83%                 |
| edu.escuelaing.arsw                  | 37%                        | N/A                 |
| edu.escuelaing.arsw.service.treasure | 98%                        | 66%                 |
| edu.escuelaing.arsw.service.actor    | 99%                        | 83%                 |


![image](https://github.com/user-attachments/assets/387a792e-817a-4b86-bbe7-91428781b0bb)


Cobertura total del proyecto: 73% de instrucciones y 68% de ramas.

### Resumen de Pruebas

- Total de líneas: 354
- Métodos probados: 50 de 102
- Clases probadas: 10 de 26

### Áreas de Mejora

Basándonos en estos resultados, se identifican las siguientes áreas que requieren mejora en la cobertura de pruebas:

1. **Controladores (edu.escuelaing.arsw.controller)**: Actualmente sin cobertura, se deben implementar pruebas para los endpoints REST.
2. **WebSocket (edu.escuelaing.arsw.websocket)**: Con una cobertura baja, se necesitan más pruebas para los componentes de comunicación en tiempo real.
3. **Repositorio (edu.escuelaing.arsw.repository)**: Sin cobertura, es necesario añadir pruebas para las operaciones de persistencia.

### Ejecutar Pruebas

Para ejecutar las pruebas y generar un informe de cobertura, utiliza el siguiente comando de Maven:

```bash
mvn test jacoco:report

### Cómo Ejecutar el Proyecto

1. Asegúrate de tener Redis en ejecución.
2. Compila y ejecuta la aplicación usando Maven o tu herramienta de construcción preferida.
   ```bash
   mvn spring-boot:run
