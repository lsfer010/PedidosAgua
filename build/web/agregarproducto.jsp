
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Producto</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <script type="text/javascript" src="js/validaciones.js"></script>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Agregar Producto</h1>
            </section>
            <section>
                <form action="CRUD" method="post" name="formulario_agregar_producto" id="formulario" class="was-validated">
                    <legend>Nombre:</legend>
                    <input type="text" name="nombre_producto" id="nombre_producto" class="form-control" maxlength="50" required="true"/>
                    <legend>Unidad Medida:</legend>
                    <input type="text" name="unidad_medida_producto" id="unidad_medida_producto" class="form-control" maxlength="15" required="true"/>
                    <br>
                        <div class="trans">
                            <button type="submit" name="opcion" value="btn_agregar_producto_form-0" id="btn_agregar-0" class="btn btn-success"/>Agregar Producto</button> 
                        </div>
                </form>   
            </section>
            <section>
                <form action="index.jsp" method="post">
                    <div class="trans">
                        <button type="submit" class="btn btn-success" id="btn_regresar" name="opcion" value="btn_regresar">Regresar</button>
                    </div>
                </form>
            </section> 
        </div>
    </body>
</html>
