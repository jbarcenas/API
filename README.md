## PLAN DE ATENCIÓN DE INCIDENCIAS POR DOTACIONES
###### .

## INTRODUCCIÓN

Se desea realizar la automatización para recuperar los folios de dotaciones de la conta, utilizando un job que procese un archivo de entrada con folios de dotaciones que han estado en incidencía y pendientes para poder ejecutarlo en la conta y generar reportes.

## REQUERIMIENTOS

Archivo txt con  los folios de dotaciones con los siguientes campos y separado con | , además terminar con esta separación al final de la linea:

* Identificador de viajero
* Identificador de dotación o folio


Por ejemplo: Identificador de Viajero|Folio|

* El nombre sera contaDotaciones.txt
* Al final del acrhivo colocar una linea en blanco.

## CONTENIDO

Para la construcción de este job se presenta un pequeño digrama de los pasos a seguir:


     1.0.Archivo con los 
	  folios de Dotaciones
              │  
              ▼
     2.0 Leer el archivo
              │
              ▼                                                         
     3.0 Buscar y realizar un 
	  conteo de los datos 
	  encontrados en la 
	  tabla TAT320_ACEC
             │ si
             ▼
        ¿el conteo es igual a 0?              ────────►           3.1 Escribir los datos en la tabla 
             │si                               no                  TAT285_CTAPERS_BIT con código 1075
             ▼
     4.0 Borrar datos de 
	  tabla TAT248_ASIENT_CONT
             │
             ▼
     5.0 Buscar si existe una 
	  agencía que intervenga en 
	  la dotación
              │
              ▼  
	¿la agencia es diferente 
	 de null y mayor a 0? 		       ────────►           5.1 Colocar la chequera de agencia como 	
              │si                               no                   vacio y el importe de agencia en 0.0
              ▼  
     6.0 Colocar importe de la 
	agencía (transporte)y 
	el del viajero (summa de 
	los demás importes)
	  y la chequera de la agencia.	
              │
              ▼  
     7.0 Ejecutar contabilidad
             │
             ▼
       ¿el codigo de exito 
	   es igual a 0?
             │ si
             ▼
     8.0 Ingresa al paquete 
	  sp_ActFechDepDot para terminar 
	  de generar la contabilidad
             │
             ▼
     9.0 Buscar y realizar un conteo  
	  de los datos encontrados en 
	  la tabla TAT320_ACEC
             │
             ▼
        ¿el conteo es diferente a 0?          ────────►       9.1 Actualizar el estado de la       ────────►    9.2 Escribir los datos en   
             │  si                               no             dotación al que se  tenia                         la tabla TAT285_CTAPERS_BIT 
             ▼                                                  y actualizar  el campo                            con código 1075
     10.0 Buscar y realizar un                                  FH_DEPOSITO  a nul en la 
	  conteo de los datos                                   tabla TAT216_DOTACION
        encontrados en las tablas 
        TAT321_CONTA_ACEC,
	  TAT266_MOV_EXITOSO y 
	  TAT248_ASIENT_CONT
             │
             ▼
        ¿el conteo es mayor a 0?
             │  si                       
             ▼ 
      11.0 Realizar la suma de 
        los importes de debes y 
        haberes de la tablas
	TAT321_CONTA_ACEC y 
	TAT248_ASIENT_CONT
              │
              ▼  
	¿son iguales los debes 
	y haber de las tablas?                   ────────►            11.1 Borrar los datos de las tablas TAT321_CONTA_ACEC, 	
              │si                                 no                  TAT320_ACEC, TAT248_ASIENT_CONT, TAT266_MOV_EXITOSO y 
              ▼                                                       escribir en la tabla TAT285_CTAPERS_BIT con código 1075
     12.0  Escribir los datos en                                  
	  la tabla TAT285_CTAPERS_BIT 
	  con código 1077
             │ 
             ▼
     13.0  Extraer los datos de 
       la tabla TAT285_CTAPERS_BIT 
       en un archivo VIBADOT.txt
       en la carpeta reportes.
