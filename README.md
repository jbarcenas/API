## PLAN DE ATENCIÓN DE INCIDENCIAS POR REEMBOLSOS
###### .

## INTRODUCCIÓN

Se desea realizar la automatización para recuperar los folios de reembolsos de la conta, utilizando un job que procese un archivo de entrada con folios de liquidacion en estado de "en proceso de ctas personales" para poder ejecutarlo en la conta y generar reportes.

## REQUERIMIENTOS

Se necesesitará un archivo txt con  los folios que estan en estado “en proceso de cuentas personales” con los siguientes campos y separado con | y terminar con esta separación al final de la linea:

* Identificador de viajero
* Identificador de reembolso o folio

Por ejemplo: Identificador de Viajero|Folio|

* El nombre sera contaReembolsos.txt
* Al final del acrhivo colocar una linea en blanco.

## CONTENIDO

Para la construcción de este job se presenta un pequeño digrama de los pasos a seguir:


     1.0.Archivo con los folios 
	  de reembolsos
              │  
              ▼
     2.0 Leer el archivo
              │
              ▼
     3.0 Buscar en la tabla 
	  TAT234_REEMBOLSO 
	  el estado del folio
              │
              ▼
      ¿el folio tiene estado de          ────────►             3.1 Escribir los datos en la tabla TAT285_CTAPERS_BIT
      "en proceso en ctas                  no                   con código 1078
        personales" ?
        
              │ si
              ▼
     4.0 Buscar y realizar un conteo 
	   de las notas encontradas en 
	   la tabla TAT235_NOTA_REEMB
              │
              ▼
       ¿el conteo es igual a 0?           ────────►           4.1 Escribir los datos en la tabla TAT285_CTAPERS_BIT
                                            no                    con código 1076
              │ si                       
              ▼                                                            
     5.0 Buscar y realizar un conteo 
	  de  los datos encontrados en 
	  la tabla TAT320_ACEC
											
             │ 				
             ▼
        ¿el conteo es igual a 0?	      ────────►              5.1 Escribir los datos en la tabla TAT285_CTAPERS_BIT
                                             no                      con código 1075
             │si
             ▼
     6.0 Borrar datos de tabla 
	   TAT248_ASIENT_CONT
             │
             ▼
     7.0 Ejecutar contabilidad
             │
             ▼
     8.0 Buscar y realizar un conteo  
	  de los datos encontrados en la 
	  tabla TAT320_ACEC
             │
             ▼
        ¿el conteo es diferente a 0?      ────────►                8.1 Escribir los datos en la tabla TAT285_CTAPERS_BIT
                                            no                         con código 1075
             │  si                       
             ▼  
     9.0 Buscar y realizar un conteo 
	  de los datos encontrados en 
	  las tablas TAT321_CONTA_ACEC,
	  TAT266_MOV_EXITOSO y 
	  TAT248_ASIENT_CONT
             │
             ▼
       ¿el conteo es mayor a 0?
             │  si                       
             ▼       
      10.0 Realizar la suma de los 
	  importes de debes y haberes 
	  de la tablas TAT321_CONTA_ACEC 
	  y TAT248_ASIENT_CONT
	  
	     │
             ▼
	¿son iguales los debes 
	 y haber de las tablas?		        ────────►                10.1 Borrar los datos de las tablas TAT321_CONTA_ACEC,TAT320_ACEC 
	    					  no			    TAT266_MOV_EXITOSO,TAT248_ASIENT_CONT y escribir en la tabla
	     │  si			        			    TAT285_CTAPERS_BIT con código 1075
             ▼	
	     │  
             ▼
	     
     11.0  Escribir los datos en 
	  la tabla TAT285_CTAPERS_BIT 
	  con código 107  
              │
              ▼
      12.0  Extraer los datos de la 
	  tabla TAT285_CTAPERS_BIT en 
	  un archivo VIBAREEMCP.txt
	  en la carpeta reportes.

