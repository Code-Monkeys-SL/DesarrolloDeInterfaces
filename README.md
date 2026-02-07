# Proyecto de Desarrollo de Interfaces

Este repositorio contiene una **herramienta de control de fichaje** desarrollada en **Java 17** utilizando **WindowBuilder** para la interfaz gráfica y **Jasper Reports** para la generación de informes de las horas trabajadas.

---

## Descripción del Proyecto

La herramienta permite a los usuarios realizar fichajes de horas trabajadas. Además, si el usuario tiene privilegios de gerente o permisos especiales, podrá gestionar **usuarios** y **categorías de trabajo**.

---

## Requisitos Previos

- **Java Development Kit (JDK) 17** o superior.
- **Eclipse IDE** con **WindowBuilder** instalado para la interfaz gráfica.
- **JasperReports** para la generación de informes.

---

## Instalación y Ejecución

1. **Clona el repositorio** utilizando el siguiente comando en tu terminal:

   ```bash
   git clone https://github.com/tu\_usuario/tu\_repositorio.git
   ```

2. **Abre el proyecto en Eclipse**.

3. **Crea una carpeta llamada `lib` dentro del proyecto** y copia los archivos JAR de la carpeta `Recursos` en esta carpeta.

4. **Configura el Build Path**:
   - Haz clic derecho en el proyecto > **Build Path** > **Configure Build Path**.
   - Agrega los JAR de la carpeta `lib` como bibliotecas añadidas.

5. **Importa la base de datos**:
   - La base de datos se encuentra en la carpeta **Recursos** del proyecto.
   - Importa la base de datos a tu servidor SQL.

6. **Configura la conexión a la base de datos** modificando el archivo `ConexionSGL.java` con los parámetros de la base de dato.

7. **Compila y ejecuta la aplicación** desde el IDE.

## Funcionalidades

- **Fichajes**: Registro de horas de entrada y salida.
- **Gestión de Usuarios**: Los gerentes pueden añadir, editar o eliminar usuarios.
- **Gestión de Categorías de Trabajo**: Asignación y modificación de categorías de trabajo disponibles.
- **Generación de Informes**: Reportes de horas trabajadas generados mediante Jasper Reports.
