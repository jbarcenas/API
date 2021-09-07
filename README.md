## PLAN DE ATENCIÓN DE INCIDENCIAS POR LIQUIDACIONES
###### .

## INTRODUCCIÓN

Se desea realizar la automatización para recuperar los folios de liquidaciones de la conta, utilizando un job que procese un archivo de entrada con folios de liquidacion en estado de "incidencias cuentas personales" para poder ejecutarlo en la conta y generar reportes.

## REQUERIMIENTOS

Archivo txt con  los folios que estan en estado "incidencias cuentas personales” con los siguientes campos y separado con | y terminar con esta separación al final de la linea:

* Identificador de viajero
* Identificador de liquidación o folio


Por ejemplo: Identificador de Viajero|Folio|

* El nombre sera contaLiquidaciones.txt
* Al final del acrhivo colocar una linea en blanco.

## CONTENIDO

Para la construcción de este job se presenta un pequeño digrama de los pasos a seguir:


     1.0.Archivo con los 
	 folios de Liquidaciones
              │  
              ▼
     2.0 Leer el archivo
              │
              ▼
     3.0 Buscar en la tabla 
	   TAT231_LIQUIDACION 
	   el estado del folio
              │
              ▼
      ¿el folio tiene estado de       	 ────────►             3.1 Escribir los datos en la tabla 
      "incidencias cuentas             	  no                  	  TAT285_CTAPERS_BIT con código 1078
        personales" ?
        
              │ si
              ▼
     4.0 Buscar y realizar un 
	  conteo de las notas 
	  encontradas en la tabla 
	  TAT232_NOTA_LIQ
              │
              ▼
       ¿el conteo es igual a 0?           ────────►             4.1 Escribir los datos en la tabla 
                                           no                    TAT285_CTAPERS_BIT con código 1076
              │ si                       
              ▼                                                            
     5.0 Buscar y realizar un 
	  conteo de los datos 
	  encontrados en la 
	  tabla TAT320_ACEC
             │ 
             ▼
        ¿el conteo es igual a 0?          ────────►              5.1 Escribir los datos en la tabla 
                                             no                    TAT285_CTAPERS_BIT con código 1075
             │si
             ▼
     6.0 Actualizar el estado 
	  del folio a "pendiente 
	  cuentas personales" en la 
	  tabla TAT231_LIQUIDACION
             │
             ▼
     7.0 Borrar datos de tabla 
	   TAT248_ASIENT_CONT
             │
             ▼
     8.0 Ejecutar contabilidad
             │
             ▼
     9.0 Buscar y realizar un 
	  conteo  de los datos 
	  encontrados en la 
	  tabla TAT320_ACEC
             │
             ▼
       ¿el conteo es diferente a 0?    	       ────────►    	       9.1 Actualizar el estado del folio a     	────────►      9.2 Escribir los datos en la tabla    	
             │  si                               no                        "incidencias cuentas personales"                               TAT285_CTAPERS_BIT con código 1075
             ▼                                                             en la tabla TAT231_LIQUIDACION
     10.0 Buscar y realizar un 
	   conteo de los datos 
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
         y haber de las tablas?                  ────────►                11.1 Borrar los datos de las tablas  	
             │ si                                 no                       TAT321_CONTA_ACEC, TAT320_ACEC,  
             ▼                                                             TAT266_MOV_EXITOSO TAT248_ASIENT_CONT y 		 
      12.0  Escribir los datos                                             escribir en la tabla TAT285_CTAPERS_BIT con código 1075
	  en la tabla TAT285_CTAPERS_BIT 
	  con código 1077
		
             │
             ▼
      13.0  Extraer los datos de 
	 la tabla TAT285_CTAPERS_BIT 
	 en un archivo VIBALIQ_I.txt
	 en la carpeta reportes.
