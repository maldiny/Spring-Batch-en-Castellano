# Integración con base de datos - Database Readers y Writers

En la mayoría de sistemas corporativos, los datos se alojan en sistemas de persistencia basados en bases de datos. A continuación se detallan los principales componentes disponibles:

```xml
<bean id="itemReader" class="org.spr...JdbcCursorItemReader">
   <property name="dataSource" ref="dataSource" />
   <property name="sql" value="select ID, NAME, CREDIT from CUSTOMER" />
   <property name="rowMapper"> <bean class="org.springframework.batch.sample.domain.CustomerCreditRowMapper" /> </property>
</bean>
```

* **JdbcCursorItemReader:** Lee de un cursor de base de datos a través de JDBC.
* **HibernateCursorItemReader:** Lee de un cursor de base de datos a través de HQL.
* **StoredProcedureItemReader:** Lee de un cursor de base de datos a través de un proceso almacenado (ej: PL/SQL).
* **JdbcPagingItemReader:** A partir de una sentencia SQL, pagina los resultados que pueden leerse sin verse afectada la memoria del proceso ante grandes volúmenes de datos.
* **JpaPagingItemReader:** A partir de una sentencia JSQL, pagina los resultados que pueden leerse sin verse afectada la memoria del proceso ante grandes volúmenes de datos.
* **IbatisPagingItemReader:** A partir de una sentencia iBATIS, pagina los resultados que pueden leerse sin verse afectada la memoria del proceso ante grandes volúmenes de datos.
* **HibernatePagingItemReader:** Lee a partir de una sentencia HQL paginada.
* **MongoItemReader:** A partir de un operador de mongo y una sentencia JSON válida de MongoDB, realiza la lectura de elementos de la base de datos.

Los **ItemWriters** definirán el modo en el que la información tras ser procesada será almacenada en los sistemas de persistencia.

```xml
<bean id="databaseItemWriter" class="org.springframework.batch.item.database.JdbcBatchItemWriter">
	<property name="dataSource" ref="dataSource" />
	<property name="sql">
    		<value>
			<![CDATA[ insert into EXAM_RESULT(STUDENT_NAME, DOB, PERCENTAGE) values (?, ?, ?)]]>
    		</value>
	</property>
	<property name="ItemPreparedStatementSetter">
    		<bean class="com.everis.....CustomItemSetter" />
	</property>
</bean>
```

* **HibernateItemWriter:** Utiliza una sesión de hibernate para manejar la transaccionalidad de la persistencia de la información.
* **JdbcBatchItemWriter:** Utiliza sentencias de tipología PreparedStatement y puede utilizar steps rudimentarios para localizar fallos en la persistencia de la información.
* **JpaItemWriter:** Utiliza un EntityManager de JPA para poder manejar la transaccionalidad en la persistencia de la información. 
* **MongoItemWriter:** A partir de un objeto de tipo MongoOperations, permite realizar la persistencia de la información en bases de datos MongoDB. La escritura de la información se retrasa hasta el último momento antes de realizar la validación de la persistencia de la información.


## Codificación

Para poder realizar un ejemplo de integración con base de datos vamos a emplear la siguiente estructura de job:

```xml
<job id="databaseJob" xmlns="http://www.springframework.org/schema/batch">
	<step id="step1" next="step2">
		<tasklet>
			<chunk reader="itemReader" processor="customProcesor" writer="itemWriter" commit-interval="1" />
		</tasklet>
	</step>
	<step id="step2">
		<tasklet>
			<chunk reader="itemReader" writer="customWriter" commit-interval="1" />
		</tasklet>
	</step>
</job>
```

El **step1** definirá un chunk con la siguiente composición:

* **itemReader:** Bean de la clase **JpaPagingItemReader** que realizará la lectura de usuarios de la base de datos.
* **customProcesor:** Bean que procesará los elementos leidos por el bean anterior y convertirá su nombre a mayúsculas.
* **itemWriter:** Bean de la clase **JpaItemWriter** que realizará la escritura en la base de datos de la información tras ser procesada.

El **step2** definirá un chunk con la siguiente composición:

* **itemReader:** Bean de la clase **JpaPagingItemReader** que realizará la lectura de usuarios de la base de datos.
* **customWriter:** Bean que imprimirá por la salida de consola la información recuperada por el itemReader para comprobar que se ha realizado el procesado de la información correctamente.

## Ejecución

Para realizar la ejecución del proceso batch realizaremos la ejecución desde la clase Main.

```cmd
INFORMACIÓN: Executing step: [step1]
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ limit ?
Hibernate: select users0_.id as id0_0_, users0_.email as email0_0_, users0_.name as name0_0_ from users users0_ where users0_.id=?
Hibernate: update users set email=?, name=? where id=?
Hibernate: update users set email=?, name=? where id=?
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ offset ? limit ?
Hibernate: select users0_.id as id0_0_, users0_.email as email0_0_, users0_.name as name0_0_ from users users0_ where users0_.id=?
Hibernate: update users set email=?, name=? where id=?
Hibernate: update users set email=?, name=? where id=?
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ offset ? limit ?
Hibernate: select users0_.id as id0_0_, users0_.email as email0_0_, users0_.name as name0_0_ from users users0_ where users0_.id=?
Hibernate: update users set email=?, name=? where id=?
Hibernate: update users set email=?, name=? where id=?
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ offset ? limit ?
sep 12, 2016 10:35:50 AM org.springframework.batch.core.job.SimpleStepHandler handleStep
INFORMACIÓN: Executing step: [step2]
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ limit ?
CustomWriter >> [MyObject [id=1, name=EMPLEADO1@MALDINY.COM, email=empleado1]]
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ offset ? limit ?
CustomWriter >> [MyObject [id=2, name=EMPLEADO2@MALDINY.COM, email=empleado2]]
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ offset ? limit ?
CustomWriter >> [MyObject [id=3, name=EMPLEADO3@MALDINY.COM, email=empleado3]]
Hibernate: select users0_.id as id0_, users0_.email as email0_, users0_.name as name0_ from users users0_ offset ? limit ?
sep 12, 2016 10:35:50 AM org.springframework.batch.core.launch.support.SimpleJobLauncher$1 run
INFORMACIÓN: Job: [FlowJob: [name=databaseJob]] completed with the following parameters: [{time=1473669349907}] and the following status: [COMPLETED]
Exit Status : COMPLETED
Exit Status : []
Time (seconds): 794657.8
Done
```

## Ejecución Standalone

Para poder realizar la ejecución desde un proceso externo, bastará con empaquetar el proyecto generando el jar con el comando **mvn install** en la raiz del proyecto, y posteriormente en la carpeta **target**, ejecutar el siguiente comando:

> java -jar com.maldiny.spring.batch.springbatchdatabasereaderwriter.SpringBatchDatabaseReaderWriter.1.0.jar

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

<p align="center"><img src="..//..//Imagenes//[Maldiny]_MultiThreadStep_ejecucion.png"></p>

