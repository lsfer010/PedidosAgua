
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Dirección</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <script type="text/javascript" src="js/validaciones.js"></script>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Agregar Dirección</h1>
            </section>
            <section>
                <form action="CRUD" method="post" name="formulario_agregar_direccion" id="formulario" class="was-validated">
                    <legend>Calle:</legend>
                    <input type="text" name="calle_direccion" id="calle_direccion" class="form-control" maxlength="50" required="true"/>
                    <legend>Número Interior:</legend>
                    <input type="text" name="numero_interior_direccion" id="numero_interior_direccion" class="form-control" maxlength="6"/>
                    <legend>Número Exterior:</legend>
                    <input type="text" name="numero_exterior_direccion" id="numero_exterior_direccion" class="form-control" maxlength="6" required="true"/>
                    <legend>Fraccionamiento o Colonia:</legend>
                    <input type="text" name="colonia_direccion" id="colonia_direccion" class="form-control" maxlength="50" required="true"/>
                    <legend>Referencia:</legend>
                    <textarea name="referencia_direccion" rows="3" cols="50" id="referencia_direccion" class="form-control" maxlength="255"></textarea>
                    <legend>Cliente:</legend>
                    <select name="cliente_id_cliente" id="cliente_id_cliente" class="form-control" required="true">
                        <%
                            String consulta[][] = (String[][]) request.getAttribute("consulta_array");
                            int cantidad_registros = Integer.parseInt(request.getAttribute("cantidad_reg_array").toString());
                            for(int i=0; i<cantidad_registros; i++) {
                                out.println("<option value=\"" + consulta[i][0] + "\">" + consulta[i][1] + "</option>");
                            }
                        %>
                    </select>
                    <br>
                        <div class="trans">
                            <button type="submit" name="opcion" value="btn_agregar_direccion_form-0" id="btn_agregar" class="btn btn-success"/>Agregar Dirección</button> 
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
