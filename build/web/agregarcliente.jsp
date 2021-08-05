
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Cliente</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <script type="text/javascript" src="js/validaciones.js"></script>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Agregar Cliente</h1>
            </section>
            <section>
                <form action="CRUD" method="post" name="formulario_agregar_cliente" id="formulario" class="was-validated">
                    <legend>RFC:</legend>
                    <input type="text" name="rfc_cliente" id="rfc_cliente" class="form-control" maxlength="13"/>
                    <legend>Nombre Cliente:</legend>
                    <input type="text" name="nombre_cliente" id="nombre_cliente" class="form-control" maxlength="100" required="true"/>
                    <legend>Teléfono del Cliente:</legend>
                    <input type="tel" name="telefono_cliente" id="telefono_cliente" class="form-control" onkeypress="return validaNumericos(event)" maxlength="10" required="true"/>
                    <br>
                        <div class="trans">
                            <button type="submit" name="opcion" value="btn_agregar_cliente_form-0" id="btn_agregar-0" class="btn btn-success"/>Agregar Cliente</button> 
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
