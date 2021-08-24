# Plan de atención a incidencias por reembolso y liquidaciones
###### .

## INTRODUCCIÓN

Para realizar la recuperación de folios de reembolsos de la conta se necesita contruir un job que realizce la automatización de esto, utilizando un archivo de entrada que contenga los datos necesarios el cual se podrá ejecutar y generar un reporte. 



## REQUIRIMIENTOS

Archivo txt con  los folios que estan en estado “en proceso de cuentas personales” con los siguientes campos y separado con |:

* Folio
* Identificador de viajero
* Identificador de empresa
* Importe



## RECOMMENDED



## INSTALLATION
 



## CONTENIDO

Para la contrucción de este job se presenta un pequeño digrama de los pasos a seguir:

-- 1.0 Archivo con los folios
--    de reem. / liq.
-- 
-- 
--            │
--            ▼
--  2.0 Leer el archivo
-- 
-- 
--  el folio tiene estado de               2.1 No se procesa
--   "proceso en cuentas
--     personales"
-- 
-- 
--  3.0 Buscar los datos de la
--     tabla TAT320_ACEC
-- 
-- 
--  3.1 Realizar un conteo de los
--     datos encontrados
-- 
-- 
--  el conteo es igual a 0
-- 
-- 
--  4.0 Borrar datos de tabla
--      TAT248_ASIENT_CONT
-- 
-- 
--  5.0 Ejecutar contabilidad
