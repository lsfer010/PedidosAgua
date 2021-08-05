
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Producto</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <script type="text/javascript" src="js/validaciones.js"></script>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Editar Producto</h1>
            </section>
            <section>
                <form action="CRUD" method="post" name="formulario_editar_producto" id="formulario" class="was-validated">
                    <input type="hidden" name="id_producto" id="id_producto"/>
                    <legend>Nombre:</legend>
                    <input type="text" name="nombre_producto" id="nombre_producto" class="form-control" maxlength="50" required="true"/>
                    <legend>Unidad Medida:</legend>
                    <input type="text" name="unidad_medida_producto" id="unidad_medida_producto" class="form-control" maxlength="15" required="true"/>
                    <br>
                        <div class="trans">
                            <button type="submit" name="opcion" value="btn_editar_producto_form-0" id="btn_editar-0" class="btn btn-success"/>Editar Producto</button> 
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
        <%   
        if(!(request.getAttribute("id_producto") == null)) { 
            out.println("<script type=\"text/javascript\">"
                    + "document.getElementById(\"id_producto\").value = \"" + request.getAttribute("id_producto").toString() + "\";"
                    + "document.getElementById(\"nombre_producto\").value = \"" + request.getAttribute("nombre_producto").toString() + "\";"
                    + "document.getElementById(\"unidad_medida_producto\").value = \"" + request.getAttribute("unidad_medida_producto").toString() + "\";"
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">"
                    + "document.getElementById(\"id_producto\").value = \"0\""
                    + "</script>");
        }
        %>
    </body>
</html>
