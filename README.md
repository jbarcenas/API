# Plan de atención a incidencias por reembolso 
###### .

## INTRODUCCIÓN

Para realizar la recuperación de folios de reembolsos de la conta se necesita contruir un job que realizce la automatización de esto, utilizando un archivo de entrada que contenga los datos necesarios el cual se podrá ejecutar y generar un reporte. 



## REQUIRIMIENTOS

Archivo txt con  los folios que estan en estado “en proceso de cuentas personales” con los siguientes campos y separado con |:

* Folio
* Identificador de viajero
* Identificador de empresa
* Importe


## CONTENIDO

Para la contrucción de este job se presenta un pequeño digrama de los pasos a seguir:



  
     1.0.Archivo con los folios de reembolsos
            │  
            ▼
     2.0 Leer el archivo
            │
            ▼
     2.2 Buscar en la tabla TAT234_REEMBOLSO 
     el estado del folio
            │
            ▼
    ¿el folio tiene estado de ────────► 2.3 Escribir los datos en la
      "proceso en cuentas       no          tabla TAT285_CTAPERS_BIT
        personales" ?                       con código 178
           │
           ▼
     3.0 Buscar y realizar un conteo de las notas 
     encontradas en la tabla TAT235_NOTA_REEMB
           │
           ▼

     ¿el conteo es igual a 0?           3.1 Escribir los datos en la
                                            tabla TAT285_CTAPERS_BIT
                                            con código 176
                                            
      4.0 Buscar y realizar un conteo de 
      los datos encontrados en la tabla 
      TAT320_ACEC

           │
           ▼

     ¿el conteo es igual a 0?
     
     5.0 Borrar datos de tabla
    TAT248_ASIENT_CONT
    
    6.0 Ejecutar contabilidad
    
    7.0 Buscar y realizar un conteo 
    de los datos encontrados 
    en la tabla TAT320_ACEC




     ¿el conteo es igual a 0?            7.1 Escribir los datos en la
                                             tabla TAT285_CTAPERS_BIT
                                             con código 175
      7.2  Escribir los d tos en la
     tabla TAT285_CTAPERS_BIT
     con código 177

