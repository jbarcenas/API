## PLAN DE ATENCIÓN DE INCIDENCIAS POR REEMBOLSOS
###### .

## INTRODUCCIÓN

Para realizar la recuperación de folios de reembolsos de la conta se necesita contruir un job que realizce la automatización de esto, utilizando un archivo de entrada que contenga los datos necesarios el cual se podrá ejecutar y generar un reporte. 



## REQUERIMIENTOS

Archivo txt con  los folios que estan en estado “en proceso de cuentas personales” con los siguientes campos y separado con |:

* Folio
* Identificador de viajero
* Identificador de empresa
* Importe


## CONTENIDO

Para la construcción de este job se presenta un pequeño digrama de los pasos a seguir:


     1.0.Archivo con los folios de reembolsos
              │  
              ▼
     2.0 Leer el archivo
              │
              ▼
     3.0 Buscar en la tabla TAT234_REEMBOLSO el estado del folio
              │
              ▼
      ¿el folio tiene estado de       ────────►             3.1 Escribir los datos en lat abla TAT285_CTAPERS_BIT
      "proceso en cuentas               no                   con código 178
        personales" ?
        
              │ si
              ▼
     4.0 Buscar y realizar un conteo de las notas 
          encontradas en la tabla TAT235_NOTA_REEMB
              │
              ▼
       ¿el conteo es igual a 0?        ────────►           4.1 Escribir los datos en la tabla TAT285_CTAPERS_BIT
                                         no                   con código 176
              │ si                       
              ▼                                                            
     5.0 Buscar y realizar un conteo de  
          los datos encontrados en la tabla TAT320_ACEC

             │ si
             ▼

        ¿el conteo es igual a 0?
             │
             ▼
     6.0 Borrar datos de tabla TAT248_ASIENT_CONT
             │
             ▼
     7.0 Ejecutar contabilidad
             │
             ▼
     8.0 Buscar y realizar un conteo  de 
          los datos encontrados en la tabla TAT320_ACEC
             │
             ▼
        ¿el conteo es igual a 0?         ────────►                8.1 Escribir los datos en la tabla TAT285_CTAPERS_BIT
                                           no                         con código 175
             │  si                       
             ▼                                   
     9.0  Escribir los datos en la tabla 
         TAT285_CTAPERS_BIT con código 177

