
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Pedido</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="css/estilos.css"/>
        <script type="text/javascript" src="js/validaciones.js"></script>
    </head>
    <body>
        <div class="container">
            <section class="titulo">
                <h1>Editar Pedido</h1>
            </section>
            <section>
                <form action="CRUD" method="post" name="formulario_agregar_direccion" id="formulario" class="was-validated">
                    <input type="hidden" name="id_pedido" id="id_pedido"/>
                    <legend>Cantidad:</legend>
                    <input type="number" step="1" name="cantidad_pedido" id="cantidad_pedido" class="form-control" min="1" onkeypress="return validaNumericos(event)" required="true"/> 
                    <legend>Fecha:</legend>
                    <input type="date" name="fecha_pedido" id="fecha_pedido" class="form-control" required="true"/>
                    <legend>Estado Pedido:</legend>
                    <select name="estado_pedido" id="estado_pedido" class="form-control" required="true">
                        <option value="Disponible">Disponible</option>
                        <option value="Pendiente">Pendiente</option>
                        <option value="Entregado">Entregado</option>
                        <option value="Cancelador">Cancelado</option>
                    </select>
                    <legend>Producto:</legend>
                    <select name="producto_id_producto" id="producto_id_producto" class="form-control" required="true">
                        <%
                            String consulta_prod[][] = (String[][]) request.getAttribute("consulta_array_prod");
                            int cantidad_registros_prod = Integer.parseInt(request.getAttribute("cantidad_reg_array_prod").toString());
                            for(int i=0; i<cantidad_registros_prod; i++) {
                                out.println("<option value=\"" + consulta_prod[i][0] + "\">" + consulta_prod[i][1] + " " + consulta_prod[i][2] + "</option>");
                            }
                        %>
                    </select>
                    <legend>Dirección:</legend>
                    <select name="direccion_id_direccion" id="direccion_id_direccion" class="form-control" required="true">
                        <%
                            String consulta_dir[][] = (String[][]) request.getAttribute("consulta_array_dir");
                            int cantidad_registros_dir = Integer.parseInt(request.getAttribute("cantidad_reg_array_dir").toString());
                            for(int i=0; i<cantidad_registros_dir; i++) {
                                out.println("<option value=\"" + consulta_dir[i][0] + "\">" + consulta_dir[i][1] + " " + consulta_dir[i][2] + " " + consulta_dir[i][3] + " " + consulta_dir[i][4] + "</option>");
                            }
                        %>
                    </select>
                    <br>
                        <div class="trans">
                            <button type="submit" name="opcion" value="btn_editar_pedido_form-0" id="btn_editar" class="btn btn-success"/>Editar Pedido</button> 
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
        if(!(request.getAttribute("id_pedido") == null)) { 
            out.println("<script type=\"text/javascript\">"
                    + "document.getElementById(\"id_pedido\").value = \"" + request.getAttribute("id_pedido").toString() + "\";"
                    + "document.getElementById(\"cantidad_pedido\").value = \"" + request.getAttribute("cantidad_pedido").toString() + "\";"
                    + "document.getElementById(\"fecha_pedido\").value = \"" + request.getAttribute("fecha_pedido").toString() + "\";"
                    + "document.getElementById(\"estado_pedido\").value = \"" + request.getAttribute("estado_pedido").toString() + "\";"
                    + "document.getElementById(\"producto_id_producto\").value = \"" + request.getAttribute("producto_id_producto").toString() + "\";"
                    + "document.getElementById(\"direccion_id_direccion\").value = \"" + request.getAttribute("direccion_id_direccion").toString() + "\";"
                    + "</script>");
        } else {
            out.println("<script type=\"text/javascript\">"
                    + "document.getElementById(\"id_pedido\").value = \"0\""
                    + "</script>");
        }
        %>
    </body>
</html>
