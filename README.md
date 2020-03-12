# SistemaRecibos
Una aplicacion que permite generar recibos de manera automatica

Requerimientos:

  Servidor de aplicaciones Java con JTA (comunicacion del servidor a la base de datos).
  Base de Datos MySql
  Navegador Web

Paquetes adicionales.
Java 13

Instalación:
¿Cómo instalar el ambiente de desarrollo?

Descarge y ejecute con su IDE Java de preferencia (creado en NetBeans)

¿Cómo implementar la solución en producción en un ambiente local o en la nube como Heroku?

Configure su servidor de aplicaciones para implementarlo de manera local (localhost) o en el entorno de red

Configuración:
Configuración del producto (archivos de configuración).

Cree la conexion Pool de su servidor a la base de datos.
Se incluye el archivo pom para la configuracion de las librerias.
Configure el archivo persistens para la conexion al servidor mediante el empleo de JTA
Configure la direccion en que se ejecutara la aplicacion (localhost) o direccion IP.

Uso:

Sección de referencia para usuario final. Manual que se hará referencia para usuarios finales.

Ejecute la aplicación a través de la URL que le indique el administrador.

una vez iniciada la aplicacion ingrese el ID del materail que a a entregar y de clic en buscar, repita el proceso por cada articulo seleccionado.

una vez ingresados todos los articulos haga clic en generar para crear automaticamente el reporte para que le firmen de recibido.

por ultimo de clic en limpiar para realizar un nuevo proceso.

Sección de referencia para usuario administrador.

la configuracion del reporte se encuentra dentro del metodo imprimir() en el paquete web en la clase materialBean.
la aplicacion esta basada en el modelo MVC 
se utilizo JSF, PrimeFace, JTA, Itext.
