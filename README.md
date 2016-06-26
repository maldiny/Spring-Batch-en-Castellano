# Spring Batch

Framework de Spring para el procesamiento de datos.

## Índice
  1. introducción
  2. elementos de un batch
  3. configuración a nivel de job
  4. configuración a nivel de step
  5. itemReaders, itemWriters y itemProcesors
  6. escalado y paralelización
  7. otros

## Introducción

[`Spring Batch`] es un framework ligero enfocado específicamente en la creación de procesos batch. 

Además de marcar unas directrices para el diseño de procesos, Spring Batch proporciona con una gran cantidad de componentes que intentan dar soporte a las diferentes necesidades que suelen surgir a la hora de crear estos programas: trazas, transaccionalidad, contingencia, estadísticas, paralelismo, particionamiento, lectura y escritura de datos, etc…

Los procesos batch (o procesos por lotes) acostumbran a ser aquellos programas que se lanzan  bajo una determinada planificación y por lo tanto no requieren ningún tipo de intervención humana. Suelen ser procesos relativamente pesados, que tratan una gran cantidad de información, por lo que normalemente se ejecutan en horarios con baja carga de trabajo para no influir en el entorno transaccional.

## Elementos de un batch
Spring Batch nos propone un diseño como el que se puede apreciar en la siguiente figura para construir nuestros procesos.
![alt tag](https://github.com/maldiny/Spring-Batch-en-Castellano/blob/737de763d536164092e0e8aeb19558a89a47f5ea/Imagenes/%5BMaldiny%5D_Elementos_de_un_batch.png)
* **JobRepository**: componente encargado de la persistencia de metadatos relativos a los procesos tales como procesos en curso o estados de las ejecuciones.
* **JobLauncher:** componente encargado de lanzar los procesos suministrando los parámetros de entrada deseados.
* **Job:** El Job es la representación del proceso. Un proceso, a su vez, es un contenedor de pasos (steps).
* **Step:** Un step (paso) es un elemento independiente dentro de un Job (un proceso) que representa una de las fases de las que está compuesto dicho proceso. Un proceso (Job) debe tener, al menos, un step.
* **ItemReader, ItemWriter, ItemProcesor:** componentes opcionales para el tratamiento de datos (lectura, escritura y procesado).

### Job (JobInstance, JobParameters, JobExecution)

**Job** 

El Job es la representación del proceso. Un proceso, a su vez, es un contenedor de pasos (steps).

**JobInstance**

Es una representación lógica de un determinado job con ciertos parámetros de ejecución.


**JobParameters**

Es un conjunto de parámetros utilizado para comenzar la ejecución de un Job. Puede usarse para identificar una ejecución o para proporcionar datos a la propia ejecución.


**JobExecution**

Representa la ejecución de un determinada instancia de un job en un determinado instante de tiempo. Identifica una ejecución del job.

### Step (StepExecution)
**Step** encapsula cada una de las fases o **pasos de un batch**. De este modo un batch está compuesto por uno o más Steps.
Un Step podrá ser tan simple o complejo o de la tipología que el desarrollador determine oportuno.

Un **StepExecution** representa cada intento de ejecución de un determinado Step. Cada vez que se ejecuta un Step se creará un nuevo StepExecution.

Cada StepExecution está formado por un **ExecutionContext** que contendrá la información que se determine oportuna persistir durante la ejecución del Step como estadísticas o información necesaria del estado del Batch.
Destacar los siguientes campos relevantes:
* **Status:** Indica el estado en el que se encuentra un Step. Sus valores variarán entre STARTED, FAILED o COMPLETED.
* **exitStatus:** Contiene el código de salida del Step.

### ExecutionContext
**ExecutionContext** representa una colección de elementos clave/valor controlada por el framework en la que el desarrollador puede persistir información a nivel de **Step (StepExecution) o Job (JobExecution)**.

```java
executionContext.putLong(getKey(LINES_READ_COUNT), reader.getPosition());
```

## Configuración a nivel de job

## Configuración a nivel de step

## ItemReaders, itemWriters y itemProcesors

## Escalado y paralelización

## Otros



