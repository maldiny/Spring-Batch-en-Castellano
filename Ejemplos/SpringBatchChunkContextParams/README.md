# Context Params

Un método para poder facilitar datos de ejecución entre los distintos steps que conforman un proceso batch es a través de su contexto de ejecución.

**ExecutionContext** representa una colección de elementos clave/valor controlada por el framework en la que el desarrollador puede persistir información a nivel de **Step (StepExecution) o Job (JobExecution)**.

## Codificación

La codificación del job consistirá en una serie de tasklets secuenciales que siguen la siguiente estructura:

```xml
<job id="secuentialControlFlow" xmlns="http://www.springframework.org/schema/batch">
	<step id="stepA" next="stepB">
        	<tasklet ref="stepABean"/>
        </step>
        <step id="stepB" next="stepC">
        	<tasklet ref="stepBBean"/>
        </step>
        <step id="stepC">
        	<tasklet ref="stepCBean"/>
        </step>
</job>
```

* **stepC:** Step que recupera la información de las variables almacenada en el contexto de ejecución.

* **stepA:** Step que realiza la persistencia de la información en el contexto de ejecución del batch. En el caso del ejemplo se almacenarán dos objetos **param1** y **myObject**:

```java
@Override
public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

	System.out.println("Executing step with name " + taskletName);

	String param1 = "value1";
	MyObject myObject = new MyObject(1,"Persona 1", 10);
	System.out.println("[" + taskletName + "] add param: " + param1);
	System.out.println("[" + taskletName + "] add param: " + myObject);
	arg1.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("param1", param1);
	arg1.getStepContext().getStepExecution().getJobExecution().getExecutionContext().put("myObject", myObject);
	
	return RepeatStatus.FINISHED;
}
```

* **stepC:** Step que recupera la información de las variables almacenada en el contexto de ejecución:

```java
@Override
public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {

	System.out.println("Executing step with name " + taskletName);

	String value1 = (String) arg1.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("param1");
	MyObject myObject = (MyObject) arg1.getStepContext().getStepExecution().getJobExecution().getExecutionContext().get("myObject");

	System.out.println("[" + taskletName + "] get param: " + value1);
	System.out.println("[" + taskletName + "] get param: " + myObject);
	
	return RepeatStatus.FINISHED;
}
```

## Ejecución

Para realizar la ejecución del proceso batch realizaremos la ejecución desde la clase Main.

```cmd
INFORMACIÓN: Executing step: [stepA]
Executing step with name stepA
[stepA] add param: value1
[stepA] add param: MyObject [id=1, name=Persona 1, age=10]
sep 06, 2016 3:02:03 AM org.springframework.batch.core.job.SimpleStepHandler handleStep
INFORMACIÓN: Executing step: [stepB]
Executing step with name stepB
sep 06, 2016 3:02:03 AM org.springframework.batch.core.job.SimpleStepHandler handleStep
INFORMACIÓN: Executing step: [stepC]
Executing step with name stepC
[stepC] get param: value1
[stepC] get param: MyObject [id=1, name=Persona 1, age=10]
sep 06, 2016 3:02:03 AM org.springframework.batch.core.launch.support.SimpleJobLauncher$1 run
INFORMACIÓN: Job: [FlowJob: [name=secuentialControlFlow]] completed with the following parameters: [{}] and the following status: [COMPLETED]
Exit Status : COMPLETED
Exit Status : []
Done
```

## Ejecución Standalone

Para poder realizar la ejecución desde un proceso externo, bastará con empaquetar el proyecto generando el jar con el comando **mvn install** en la raiz del proyecto, y posteriormente en la carpeta **target**, ejecutar el siguiente comando:

> java -jar com.maldiny.spring.batch.chunk.context.params.SpringBatchChunkContextParams.1.0.jar

## Ejecución en la base de datos HSQLDB externa

Para poder realizar la ejecución empleando la base de datos HSQLDB externa los pasos a seguir son los siguientes:

* **Iniciar la base de datos HSQLDB:** Emplear el lanzador SpringBatch-HSQL-Server incluido en el proyecto SpringBatchAdminDatabase.
* **Cambiar la configuración de base de datos del proyecto:** Para ello es necesario modificar el fichero src/main/resources/spring/batch/jobs/job-config.xml para descomentar la línea 10 y comentar la línea 11 del documento del siguiente modo:

```xml
<import resource="../config/database-hsqldb-context.xml" /> <!-- External HSQLDB Database -->
<!-- <import resource="../config/database-context.xml" /> --> <!-- Internal HSQLDB Database -->
```

Una vez modificado, lanzamos el proceso batch y accedemos a la url del portal Spring Batch Admin en la siguiente URL:

> http://localhost:8080/SpringBatchAdminWeb/jobs

<p align="center"><img src="..//..//Imagenes//[Maldiny]_Context_params_ejecucion.png"></p>

Como se puede ver en la imagen adjunta, se ha producido tanto la lectura como la escritura de diez elementos.


