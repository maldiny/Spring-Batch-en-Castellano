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

![alt tag](https://raw.githubusercontent.com/johnpapa/angular-styleguide/master/a1/assets/above-the-fold-1.png)

