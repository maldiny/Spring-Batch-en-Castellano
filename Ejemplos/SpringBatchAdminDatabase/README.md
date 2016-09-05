# Spring Batch Database HSQLDB

El conjunto de ejemplos se complementa con una base de datos externa HSQLDB en la que persistir el resultado de las ejecuciones de los procesos batch para posteriormente consultarlas mediante la web de Spring Batch Admin.

## Codigo

La estructura del proyecto se compone de la siguiente estructura y scripts útiles para poder desplegar la base de datos de Spring Batch para la versión 3.0.X:

* **SpringBatch-HSQL-Server.launch:** Launcher que tras ejecutarlo se iniciará la base de datos HSQL.

<p align="center"><img src="..//..//Imagenes//[Maldiny]_Importar_launcher.png"></p>

<p align="center"><img src="..//..//Imagenes//[Maldiny]_Import_launchers_SpringBatch_HSQL.png"></p>

<p align="center"><img src="..//..//Imagenes//[Maldiny]_Run_HSQLDB_database.png"></p>

* **Instalación del arquetipo en el repositorio maven:** mvn clean install -f target/generated-sources/archetype/
* **Creación del nuevo proyecto utilizando el arquetipo:** Crear un nuevo proyecto maven desde el IDE y seleccionar el arquetipo del siguiente modo:
 
<p align="center"><img src="..//..//Imagenes//[Maldiny]_Nuevo_Proyecto_Con_Arquetipos_Maven.png"></p>
<p align="center"><img src="..//..//Imagenes//[Maldiny]_Crear_proyecto_maven.png"></p>
