
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Cliente</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <script type="text/javascript" src="js/validaciones.js"></script>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Editar Cliente</h1>
            </section>
            <section>
                <form action="CRUD" method="post" name="formulario_agregar_cliente" id="formulario" class="was-validated">
                    <input type="hidden" name="id_cliente" id="id_cliente"/>
                    <legend>RFC:</legend>
                    <input type="text" name="rfc_cliente" id="rfc_cliente" class="form-control" maxlength="13"/>
                    <legend>Nombre Cliente:</legend>
                    <input type="text" name="nombre_cliente" id="nombre_cliente" class="form-control" maxlength="100" required="true"/>
                    <legend>Teléfono del Cliente:</legend>
                    <input type="tel" name="telefono_cliente" id="telefono_cliente" class="form-control" onkeypress="return validaNumericos(event)" maxlength="10" required="true"/>
                    <br>
                        <div class="trans">
                            <button type="submit" name="opcion" value="btn_editar_cliente_form-0" id="btn_editar-0" class="btn btn-success"/>Editar Cliente</button> 
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
        if(!(request.getAttribute("id_cliente") == null)) { 
            out.println("<script type=\"text/javascript\">"
                    + "document.getElementById(\"id_cliente\").value = \"" + request.getAttribute("id_cliente").toString() + "\";"
                    + "document.getElementById(\"rfc_cliente\").value = \"" + request.getAttribute("rfc_cliente").toString() + "\";"
                    + "document.getElementById(\"nombre_cliente\").value = \"" + request.getAttribute("nombre_cliente").toString() + "\";"
                    + "document.getElementById(\"telefono_cliente\").value = \"" + request.getAttribute("telefono_cliente").toString() + "\";"     
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">"
                    + "document.getElementById(\"id_cliente\").value = \"0\""
                    + "</script>");
        }
        %>
    </body>
</html>
