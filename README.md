# Sistema de gestión eficiente de usuarios🌳🔴⚫
## PROYECTO FINAL ADAI
Estudiantes:
- Vanessa Alexandra Durán Mona 2359394-3743
- Juan Damián Cuervo Buitrago 2359143-3743
- Alejandra Osorio Giraldo 2266128-3743
- Manuel Rosero Zuñiga 2176007-3743


Este repositorio contiene una implementación en Java de un **Árbol RojiNegro (Red-Black Tree)**, una estructura de datos auto-balanceada basada en árboles binarios de búsqueda. Este tipo de árbol mantiene su balance automáticamente tras operaciones de **inserción**, **eliminación** y **búsqueda**, garantizando un rendimiento eficiente en todas ellas, para llevar a cabo la gestion eficiente de usuarios activos en una empresa.

## Características del proyecto📌
- Adición y eliminación de usuarios activos de forma eficiente.
- Consultar rápidamente el usuario con el menor identificador mayor o igual a un valor dado (por ejemplo, para emparejar usuarios en una cola de espera).
- Mantiene siempre los datos ordenados por identificador de usuario.
- Garantiza que todas las operaciones (inserción, eliminación, búsqueda) se realicen en el peor de los casos en tiempo logarítmico, sin degradación de rendimiento, incluso bajo inserciones y eliminaciones intensas.
## Características de la estructura de datos implementada (arboles rojinegros)📌
- Inserción de nodos con balanceo automático.
- Búsqueda de claves.
- Búsqueda del mínimo y máximo.
- Estructura extensible con atributos como `name`, `age`, `phone`.
- Métodos para rotaciones izquierda y derecha.
- Validación de casos para mantener las propiedades RojiNegras.
- Eliminación de nodos, manteniendo las propiedades.

## Estructura del Árbol

Cada nodo tiene:

- `key`: clave flotante para ordenamiento.
- `left` y `right`: hijos izquierdo y derecho.
- `parent`: referencia al nodo padre.
- `color`: "red" o "black".
- Atributos adicionales (`name`, `age`, `phone`), útiles para almacenar la información asociada a los usuarios.

## Operaciones Principales ⚙️

- `insertar(nodoBinario nuevoNodo)`: Inserta un nodo y ajusta el árbol para mantener las propiedades RojiNegras.
- `buscar(float key)`: Busca un nodo por su clave.
- `maximo()`: Retorna el nodo con la clave más alta.
- `minimo()`: Retorna el nodo con la clave más baja.
- `rotacionIzquierda(nodoBinario nodo)`: Aplica rotación izquierda.
- `rotacionDerecha(nodoBinario nodo)`: Aplica rotación derecha.
- `eliminar(float key, nodoBinario root)`: Elimina el nodo con clave igual a key si este existe
- `transplantar(nodoBinario root, nodoBinario u, nodoBinario v)`: Transplanta en el nodo binario "root", el nodo u poor el nodo v. 

## Requisitos ✅

- Java 8 o superior.
- Un entorno de desarrollo compatible (Eclipse, IntelliJ IDEA, VS Code, etc.).

## Cómo ejecutar 🧪

1. Clona el repositorio:
```bash
git clone https://github.com/Vane2005/ADAI.git
```
2. Dirigete al editor de tu preferencia y abre la carpeta src, y ejecuta el archivo App.java
3. Realiza las operaciones que quieras

## 🤝 Contribuciones
¡Las contribuciones son bienvenidas!
- Haz un fork del repositorio.
- Crea una rama nueva: git checkout -b nueva-funcionalidad
- Realiza tus cambios y haz commit: git commit -am 'Agregar funcionalidad X'
- Abre un Pull Request.

## 📄 Licencia
Este proyecto está licenciado bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

Desarrollado con 💻 en Java para propósitos académicos y de aprendizaje.
